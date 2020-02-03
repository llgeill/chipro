package cn.spark.chipro.oss.biz.service.impl;

import cn.spark.chipro.oss.biz.entity.UserRole;
import cn.spark.chipro.oss.biz.mapper.UserRoleMapper;
import cn.spark.chipro.oss.biz.model.params.UserRoleParam;
import cn.spark.chipro.oss.biz.model.result.UserRoleResult;
import  cn.spark.chipro.oss.biz.service.UserRoleService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void add(UserRoleParam param){
        UserRole entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(UserRoleParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(UserRoleParam param){
        UserRole oldEntity = getOldEntity(param);
        UserRole newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public UserRoleResult findBySpec(UserRoleParam param){
        return null;
    }

    @Override
    public List<UserRoleResult> findListBySpec(UserRoleParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(UserRoleParam param){
        Page pageContext=getPageContext();
        QueryWrapper<UserRole>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(UserRoleParam param){
        return param.getUserAndRoleId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private UserRole getOldEntity(UserRoleParam param) {
        return this.getById(getKey(param));
    }

    private UserRole getEntity(UserRoleParam param) {
        UserRole entity = new UserRole();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
