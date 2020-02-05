package cn.spark.chipro.oss.biz.service.impl;

import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.util.StringUtil;
import cn.spark.chipro.oss.biz.common.constant.Cache;
import cn.spark.chipro.oss.biz.common.constant.CacheKey;
import cn.spark.chipro.oss.biz.common.constant.CoreExceptionConstant;
import cn.spark.chipro.oss.biz.common.constant.SmsCodeConstant;
import cn.spark.chipro.oss.biz.entity.User;
import cn.spark.chipro.oss.biz.mapper.UserMapper;
import cn.spark.chipro.oss.api.model.params.UserParam;
import cn.spark.chipro.oss.api.model.result.UserResult;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
                //添加随机生成的账号
                String userName = String.valueOf(IdWorker.getId());
                entity.setUserName(userName);
                entity.setFailureTime(getDateAfterYear());
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                entity.setCreatePerson("1");
                this.save(entity);
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
        this.updateById(newEntity);
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

}
