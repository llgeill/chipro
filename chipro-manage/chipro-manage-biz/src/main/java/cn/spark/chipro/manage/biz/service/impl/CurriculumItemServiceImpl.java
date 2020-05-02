package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.CurriculumItem;
import cn.spark.chipro.manage.biz.mapper.CurriculumItemMapper;
import cn.spark.chipro.manage.api.model.params.CurriculumItemParam;
import cn.spark.chipro.manage.api.model.result.CurriculumItemResult;
import  cn.spark.chipro.manage.biz.service.CurriculumItemService;
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
 *  服务实现类
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@Service
public class CurriculumItemServiceImpl extends ServiceImpl<CurriculumItemMapper, CurriculumItem> implements CurriculumItemService {

    @Override
    public void add(CurriculumItemParam param){
        CurriculumItem entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CurriculumItemParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CurriculumItemParam param){
        CurriculumItem oldEntity = getOldEntity(param);
        CurriculumItem newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CurriculumItemResult findBySpec(CurriculumItemParam param){
        return null;
    }

    @Override
    public List<CurriculumItemResult> findListBySpec(CurriculumItemParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(CurriculumItemParam param){
        Page pageContext=getPageContext();
        QueryWrapper<CurriculumItem>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(CurriculumItemParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private CurriculumItem getOldEntity(CurriculumItemParam param) {
        return this.getById(getKey(param));
    }

    private CurriculumItem getEntity(CurriculumItemParam param) {
        CurriculumItem entity = new CurriculumItem();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
