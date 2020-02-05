package cn.spark.chipro.oss.biz.service.impl;

import cn.spark.chipro.oss.biz.entity.RolePermission;
import cn.spark.chipro.oss.biz.mapper.RolePermissionMapper;
import cn.spark.chipro.oss.api.model.params.RolePermissionParam;
import cn.spark.chipro.oss.api.model.result.RolePermissionResult;
import  cn.spark.chipro.oss.biz.service.RolePermissionService;
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
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public void add(RolePermissionParam param){
        RolePermission entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(RolePermissionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(RolePermissionParam param){
        RolePermission oldEntity = getOldEntity(param);
        RolePermission newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RolePermissionResult findBySpec(RolePermissionParam param){
        return null;
    }

    @Override
    public List<RolePermissionResult> findListBySpec(RolePermissionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(RolePermissionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<RolePermission>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(RolePermissionParam param){
        return param.getRoleAndPermissionId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private RolePermission getOldEntity(RolePermissionParam param) {
        return this.getById(getKey(param));
    }

    private RolePermission getEntity(RolePermissionParam param) {
        RolePermission entity = new RolePermission();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
