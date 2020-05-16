package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.test.entity.OssPermission;
import cn.spark.chipro.test.mapper.OssPermissionMapper;
import cn.spark.chipro.test.model.params.OssPermissionParam;
import cn.spark.chipro.test.model.result.OssPermissionResult;
import  cn.spark.chipro.test.service.OssPermissionService;
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
 * @since 2020-01-27
 */
@Service
public class OssPermissionServiceImpl extends ServiceImpl<OssPermissionMapper, OssPermission> implements OssPermissionService {

    @Override
    public void add(OssPermissionParam param){
        OssPermission entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(OssPermissionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(OssPermissionParam param){
        OssPermission oldEntity = getOldEntity(param);
        OssPermission newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public OssPermissionResult findBySpec(OssPermissionParam param){
        return null;
    }

    @Override
    public List<OssPermissionResult> findListBySpec(OssPermissionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(OssPermissionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<OssPermission>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(OssPermissionParam param){
        return param.getPermissionId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private OssPermission getOldEntity(OssPermissionParam param) {
        return this.getById(getKey(param));
    }

    private OssPermission getEntity(OssPermissionParam param) {
        OssPermission entity = new OssPermission();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
