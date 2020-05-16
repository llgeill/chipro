package cn.spark.chipro.oss.biz.service.impl;

import cn.spark.chipro.oss.biz.entity.Role;
import cn.spark.chipro.oss.biz.mapper.RoleMapper;
import cn.spark.chipro.oss.api.model.params.RoleParam;
import cn.spark.chipro.oss.api.model.result.RoleResult;
import  cn.spark.chipro.oss.biz.service.RoleService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public void add(RoleParam param){
        Role entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(RoleParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(RoleParam param){
        Role oldEntity = getOldEntity(param);
        Role newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RoleResult findBySpec(RoleParam param){
        return null;
    }

    @Override
    public List<RoleResult> findListBySpec(RoleParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(RoleParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Role>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(RoleParam param){
        return param.getRoleId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Role getOldEntity(RoleParam param) {
        return this.getById(getKey(param));
    }

    private Role getEntity(RoleParam param) {
        Role entity = new Role();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
