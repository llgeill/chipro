package cn.spark.chipro.oss.biz.service.impl;

import cn.spark.chipro.oss.biz.entity.Permission;
import cn.spark.chipro.oss.biz.mapper.PermissionMapper;
import cn.spark.chipro.oss.biz.model.params.PermissionParam;
import cn.spark.chipro.oss.biz.model.result.PermissionResult;
import  cn.spark.chipro.oss.biz.service.PermissionService;
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
 * 权限表 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public void add(PermissionParam param){
        Permission entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PermissionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PermissionParam param){
        Permission oldEntity = getOldEntity(param);
        Permission newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PermissionResult findBySpec(PermissionParam param){
        return null;
    }

    @Override
    public List<PermissionResult> findListBySpec(PermissionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(PermissionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Permission>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(PermissionParam param){
        return param.getPermissionId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Permission getOldEntity(PermissionParam param) {
        return this.getById(getKey(param));
    }

    private Permission getEntity(PermissionParam param) {
        Permission entity = new Permission();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
