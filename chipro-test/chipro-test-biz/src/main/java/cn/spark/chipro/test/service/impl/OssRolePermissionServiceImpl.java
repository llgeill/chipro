package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.test.entity.OssRolePermission;
import cn.spark.chipro.test.mapper.OssRolePermissionMapper;
import cn.spark.chipro.test.model.params.OssRolePermissionParam;
import cn.spark.chipro.test.model.result.OssRolePermissionResult;
import  cn.spark.chipro.test.service.OssRolePermissionService;
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
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Service
public class OssRolePermissionServiceImpl extends ServiceImpl<OssRolePermissionMapper, OssRolePermission> implements OssRolePermissionService {

    @Override
    public void add(OssRolePermissionParam param){
        OssRolePermission entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(OssRolePermissionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(OssRolePermissionParam param){
        OssRolePermission oldEntity = getOldEntity(param);
        OssRolePermission newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public OssRolePermissionResult findBySpec(OssRolePermissionParam param){
        return null;
    }

    @Override
    public List<OssRolePermissionResult> findListBySpec(OssRolePermissionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(OssRolePermissionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<OssRolePermission>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(OssRolePermissionParam param){
        return param.getRoleAndPermissionId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private OssRolePermission getOldEntity(OssRolePermissionParam param) {
        return this.getById(getKey(param));
    }

    private OssRolePermission getEntity(OssRolePermissionParam param) {
        OssRolePermission entity = new OssRolePermission();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
