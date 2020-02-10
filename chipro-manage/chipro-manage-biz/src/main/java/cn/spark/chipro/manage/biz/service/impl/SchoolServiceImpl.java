package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.core.exception.CoreException;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.util.ChineseToSpellUtil;
import cn.spark.chipro.manage.biz.entity.School;
import cn.spark.chipro.manage.biz.entity.SchoolUser;
import cn.spark.chipro.manage.biz.mapper.SchoolMapper;
import cn.spark.chipro.manage.api.model.params.SchoolParam;
import cn.spark.chipro.manage.api.model.result.SchoolResult;
import  cn.spark.chipro.manage.biz.service.SchoolService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.manage.biz.service.SchoolUserService;
import cn.spark.chipro.oss.api.feign.UserFeignService;
import cn.spark.chipro.oss.api.model.params.UserParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学校 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private SchoolUserService schoolUserService;

    @Override
    @Transactional
    public UserParam add(SchoolParam param){
        //保存学校信息
        School entity = getEntity(param);
        //判断学校是否重复
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("NAME",entity.getName());
        School school = this.getOne(queryWrapper);
        if(school==null){
            entity.setCode(ChineseToSpellUtil.getFirstSpell(entity.getName()).toUpperCase());
            this.save(entity);
        }else{
            throw new CoreException(-1,"学校名称重复！");
        }
        //注册一个校长账号
        UserParam userParam = new UserParam();
        userParam.setEmail(param.getEmail());
        userParam.setEmailCode(param.getEmailCode());
        userParam.setUserNameAlias(param.getPrincipal());
        userParam.setMobile(param.getMobile());
        //校长角色id
        userParam.setRole("3");
        Result<UserParam> result =userFeignService.register(userParam);
        if(result.getCode()==1){
            //保存学校校长关系
            SchoolUser schoolUser = new SchoolUser();
            schoolUser.setSchoolId(entity.getSchoolId());
            schoolUser.setUserId(result.getDatas().getUserId());
            //类型 1：校长 2：老师
            schoolUser.setType("1");
            schoolUserService.save(schoolUser);
            //返回账号信息
            return result.getDatas();
        }else{
            throw new CoreException(result.getCode(),result.getMsg());
        }
    }

    @Override
    public void delete(SchoolParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(SchoolParam param){
        School oldEntity = getOldEntity(param);
        School newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SchoolResult findBySpec(SchoolParam param){
        return null;
    }

    @Override
    public List<SchoolResult> findListBySpec(SchoolParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(SchoolParam param){
        Page pageContext=getPageContext();
        QueryWrapper<School>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(SchoolParam param){
        return param.getSchoolId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private School getOldEntity(SchoolParam param) {
        return this.getById(getKey(param));
    }

    private School getEntity(SchoolParam param) {
        School entity = new School();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
