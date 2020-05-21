package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Resource;
import cn.spark.chipro.manage.biz.mapper.ResourceMapper;
import cn.spark.chipro.manage.api.model.params.ResourceParam;
import cn.spark.chipro.manage.api.model.result.ResourceResult;
import cn.spark.chipro.manage.biz.service.ResourceService;
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
 * 资源 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-09
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public void add(ResourceParam param){
        Resource entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ResourceParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ResourceParam param){
        Resource oldEntity = getOldEntity(param);
        Resource newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ResourceResult findBySpec(ResourceParam param){
        return null;
    }

    @Override
    public List<ResourceResult> findListBySpec(ResourceParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(ResourceParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Resource>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(ResourceParam param){
        return param.getResourceId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Resource getOldEntity(ResourceParam param) {
        return this.getById(getKey(param));
    }

    private Resource getEntity(ResourceParam param) {
        Resource entity = new Resource();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
