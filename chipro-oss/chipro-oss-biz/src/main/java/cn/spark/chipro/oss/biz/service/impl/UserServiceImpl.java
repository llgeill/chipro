package cn.spark.chipro.oss.biz.service.impl;

import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.util.ExcelUtil;
import cn.spark.chipro.core.util.StringUtil;
import cn.spark.chipro.manage.api.feign.ManageFeignService;
import cn.spark.chipro.manage.api.model.params.ClassUserParam;
import cn.spark.chipro.oss.biz.common.constant.Cache;
import cn.spark.chipro.oss.biz.common.constant.CacheKey;
import cn.spark.chipro.oss.biz.common.constant.CoreExceptionConstant;
import cn.spark.chipro.oss.biz.common.constant.SmsCodeConstant;
import cn.spark.chipro.oss.biz.entity.User;
import cn.spark.chipro.oss.biz.entity.UserRole;
import cn.spark.chipro.oss.biz.mapper.UserMapper;
import cn.spark.chipro.oss.api.model.params.UserParam;
import cn.spark.chipro.oss.api.model.result.UserResult;
import cn.spark.chipro.oss.biz.service.UserRoleService;
import  cn.spark.chipro.oss.biz.service.UserService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ManageFeignService manageFeignService;

    @Value("${oss.user.default.password}")
    private String defaultPassword;

    @Value("${oss.user.default.role}")
    private String defaultRole;


    private boolean isDebug = log.isDebugEnabled();


    @Override
    public void add(UserParam param){
        User entity = getEntity(param);
        this.save(entity);
    }

    @Override
    @Transactional
    public User register(UserParam param){
        //判断账号是否重复
        boolean repeatAccount = repeatAccount(param);
        if(!repeatAccount){
            //参数校验以及邮箱验证码校验
            if(param.check()&&checkEmailCode(param)){
                User entity = getEntity(param);
                //随机生成账号
                if(StringUtil.isEmpty(param.getUserName())){
                    entity.setUserName(userAccount());
                }
                //设置密码
                if(StringUtil.isEmpty(param.getPassword())){
                    entity.setPassword(passwordEncoder.encode(defaultPassword));
                }else{
                    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                }
                entity.setFailureTime(getDateAfterYear());
                entity.setCreatePerson("1");
                //保存
                this.save(entity);
                UserRole userRole = new UserRole();
                //添加用户角色
                if(StringUtil.isEmpty(param.getRole())){
                    userRole.setRoleId(defaultRole);
                }else{
                    userRole.setRoleId(param.getRole());
                }
                userRole.setUserId(entity.getUserId());
                userRole.setCreatePerson(entity.getUserId());
                userRole.setCreateTime(new Date());
                this.userRoleService.save(userRole);
                //返回账号信息
                return entity;
            }else{
                //参数校验异常
                throw new CoreException(-1, CoreExceptionConstant.PARAM_CHECK);
            }
        }else{
            //账号重复异常
            throw new CoreException(-1,CoreExceptionConstant.ACCOUNT_REPEAT);
        }
    }


    /**
     * 通过excel表的学生信息批量添加学生账号
     *
     * @param multipartFile excel文件表
     * @param classRoomId 课室id
     * @return
     */
    @Override
    @Transactional
    public List<User> batchRegisterStuAccount(MultipartFile multipartFile,String classRoomId){
        List<User> userList = ExcelUtil.importExcel(multipartFile, 7, 1, User.class);
        List<UserRole> userRoleList = new ArrayList<>();
        List<ClassUserParam> classUserParamList = new ArrayList<>();
        if(userList!=null&&userList.size()>0){
            userList.forEach(entity -> {
                entity.setUserName(userAccount());
                entity.setPassword(passwordEncoder.encode(defaultPassword));
                entity.setFailureTime(getDateAfterYear());
            });
            //批量添加账号获取账号id
            this.saveBatch(userList);
            //批量赋予学生角色
            userList.forEach(entity->{
                //添加学生角色
                UserRole userRole = new UserRole();
                //添加用户角色
                userRole.setRoleId("5");
                userRole.setUserId(entity.getUserId());
                userRoleList.add(userRole);
                //添加课室学生关系
                ClassUserParam classUserParam = new ClassUserParam();
                classUserParam.setUserId(entity.getUserId());
                classUserParam.setClassRoomId(classRoomId);
                classUserParamList.add(classUserParam);
            });
            userRoleService.saveBatch(userRoleList);
            //添加学生关系
            manageFeignService.batchAdd(classUserParamList);
            return userList;
        }else{
            throw new CoreException(-1,"学生数据为空！");
        }
    }

    @Override
    @Transactional
    public void forgetPass(UserParam param){
        //检查邮箱验证码
        if(checkEmailCode((param))){
            if(StringUtil.isEmpty(param.getPassword())||StringUtil.isEmpty(param.getUserId())){
                throw new CoreException(-1,"缺少关键参数!");
            }
            //更新密码
            this.update(param);
        }
    }

    @Override
    @Cacheable(value = Cache.CHIPRO_OSS, key = "'" + CacheKey.USER_REPEAT_ACCOUNT + "'+#param.userName+#param.mobile+#param.email")
    public boolean repeatAccount(UserParam param) {
        //检查是否有重复账号(账号重复、邮箱重复、手机号重复)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtil.isNotEmpty(param.getUserName())){
            queryWrapper.or().eq("USER_NAME",param.getUserName());
        }
        if(StringUtil.isNotEmpty(param.getMobile())){
            queryWrapper.or().eq("MOBILE",param.getMobile());
        }
        if(StringUtil.isNotEmpty(param.getEmail())){
            queryWrapper.or().eq("EMAIL",param.getEmail());
        }
        List<User> userList = this.list(queryWrapper);
        return userList != null && userList.size() > 0;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = Cache.CHIPRO_OSS, key = "'" + CacheKey.USER_ALIAS_NAME + "'+#param.userId"),
            @CacheEvict(value = Cache.CHIPRO_OSS, key = "'" + CacheKey.USER_REPEAT_ACCOUNT + "'+#param.userName+#param.mobile+#param.email")
    })
    public void delete(UserParam param){
        this.removeById(getKey(param));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = Cache.CHIPRO_OSS, key = "'" + CacheKey.USER_ALIAS_NAME + "'+#param.userId"),
            @CacheEvict(value = Cache.CHIPRO_OSS, key = "'" + CacheKey.USER_REPEAT_ACCOUNT + "'+#param.userName+#param.mobile+#param.email")
    })
    public void update(UserParam param){
        User oldEntity = getOldEntity(param);
        User newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        if(StringUtil.isNotEmpty(newEntity.getPassword())){
            newEntity.setPassword(passwordEncoder.encode(newEntity.getPassword()));
        }
        this.updateById(newEntity);
    }

    @Override
    public void resetPass(UserParam param){
        if(StringUtil.isEmpty(param.getPassword())){
            throw new CoreException(-1,"密码不能为空");
        }else{
            User user = new User();
            user.setUserId(param.getUserId());
            user.setPassword(passwordEncoder.encode(param.getPassword()));
            this.updateById(user);
        }
    }

    @Override
    public UserResult findBySpec(UserParam param){
        return null;
    }

    @Override
    public List<UserResult> findListBySpec(UserParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(UserParam param){
        Page pageContext=getPageContext();
        QueryWrapper<User>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    @Override
    @Cacheable(value = Cache.CHIPRO_OSS, key = "'" + CacheKey.USER_ALIAS_NAME + "'+#userId")
    public String getUserNameById(String userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("USER_NAME_ALIAS").eq("USER_ID",userId);
        User one = this.getOne(queryWrapper);
        if(one!=null&&StringUtil.isNotEmpty(one.getUserNameAlias())){
            return one.getUserNameAlias();
        }else{
            return "";
        }
    }

    private Serializable getKey(UserParam param){
        return param.getUserId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private User getOldEntity(UserParam param) {
        return this.getById(getKey(param));
    }

    private User getEntity(UserParam param) {
        User entity = new User();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    /**
     * 检验邮箱验证码是否正确
     * @param userParam
     * @return
     */
    private boolean checkEmailCode(UserParam userParam){
        //获取缓存key值
        String cachedKey = SmsCodeConstant.EMAIL_CODE_CACHE_PREFIX + userParam.getEmail();
        // 验证验证码
        String smsCodeCached = redisTemplate.opsForValue().get(cachedKey);
        if (StringUtil.isEmpty(smsCodeCached)) {
            if (isDebug) {
                log.debug("用户没有发送验证码_smsCodeCached:[{}]", smsCodeCached);
            }
            throw new CoreException(-1,"用户没有发送验证码");
        }

        if (StringUtil.isEmpty(userParam.getEmailCode())) {
            if (isDebug) {
                log.debug("验证码不能为空");
            }
            throw new CoreException(-1,"验证码不能为空");
        }

        if (!userParam.getEmailCode().equals(smsCodeCached)) {
            if (isDebug) {
                log.debug("验证码不匹配_emailcode:[{}],emailCodeCached:[{}]", userParam.getEmailCode(), smsCodeCached);
            }
            throw new CoreException(-1,"验证码不正确或已失效");
        } else {
            //删除验证码
            redisTemplate.delete(cachedKey);
        }
        return true;
    }

    /**
     * 获取一年后的日期
     * @return
     */
    private Date getDateAfterYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        Date time = calendar.getTime();
        return time;
    }

    /**
     * 生成不重复的随机账号
     * @return
     */
    private String userAccount(){
        int count = this.count();
        return randomCode()+randomCode()+ count;
    }

    /**
     * 生成随机码
     * @return 随机码
     */
    private static String randomCode(){
        int flag = new Random().nextInt(999);
        if (flag < 100){
            flag += 100;
        }
        return Integer.toString(flag);
    }

}
