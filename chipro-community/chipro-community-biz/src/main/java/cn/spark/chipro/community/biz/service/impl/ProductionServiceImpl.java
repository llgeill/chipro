package cn.spark.chipro.community.biz.service.impl;

import cn.spark.chipro.community.biz.entity.Production;
import cn.spark.chipro.community.biz.mapper.ProductionMapper;
import cn.spark.chipro.community.api.model.params.ProductionParam;
import cn.spark.chipro.community.api.model.result.ProductionResult;
import  cn.spark.chipro.community.biz.service.ProductionService;
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
 * 作品 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {

    @Override
    public void add(ProductionParam param){
        Production entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ProductionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ProductionParam param){
        Production oldEntity = getOldEntity(param);
        Production newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ProductionResult findBySpec(ProductionParam param){
        return null;
    }

    @Override
    public List<ProductionResult> findListBySpec(ProductionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(ProductionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Production>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(ProductionParam param){
        return param.getProductionId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Production getOldEntity(ProductionParam param) {
        return this.getById(getKey(param));
    }

    private Production getEntity(ProductionParam param) {
        Production entity = new Production();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
