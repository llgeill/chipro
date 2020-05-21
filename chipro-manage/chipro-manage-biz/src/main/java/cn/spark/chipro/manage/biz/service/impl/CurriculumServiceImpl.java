package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.api.model.params.CurriculumItemParam;
import cn.spark.chipro.manage.biz.entity.Curriculum;
import cn.spark.chipro.manage.biz.entity.CurriculumItem;
import cn.spark.chipro.manage.biz.mapper.CurriculumItemMapper;
import cn.spark.chipro.manage.biz.mapper.CurriculumMapper;
import cn.spark.chipro.manage.api.model.params.CurriculumParam;
import cn.spark.chipro.manage.api.model.result.CurriculumResult;
import  cn.spark.chipro.manage.biz.service.CurriculumService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@Service
public class CurriculumServiceImpl extends ServiceImpl<CurriculumMapper, Curriculum> implements CurriculumService {

    @Autowired
    private CurriculumItemMapper curriculumItemMapper;

    @Override
    public void add(CurriculumParam param){
        Curriculum entity = getEntity(param);
        this.saveOrUpdate(entity);
        if (param.getCurriculumItems()!=null && param.getCurriculumItems().size()>0){
            List<CurriculumItemParam> curriculumItems = param.getCurriculumItems();
            curriculumItems.forEach(curriculumItemParam -> {
                CurriculumItem curriculumItem = new CurriculumItem();
                ToolUtil.copyProperties(curriculumItemParam,curriculumItem);
                curriculumItemMapper.insert(curriculumItem);
            });

        }
    }

    @Override
    public void delete(CurriculumParam param){
        Map<String,Object> map = new HashMap<>();
        map.put("curriculum_id",param.getId());
        curriculumItemMapper.deleteByMap(map);
        this.removeById(getKey(param));
    }

    @Override
    public void update(CurriculumParam param){
        Curriculum oldEntity = getOldEntity(param);
        Curriculum newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CurriculumResult findBySpec(CurriculumParam param){
        return null;
    }

    @Override
    public List<CurriculumResult> findListBySpec(CurriculumParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(CurriculumParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Curriculum>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        List<Curriculum>curriculumList = page.getRecords();
        curriculumList.forEach(curriculum ->{
            curriculum.setCurriculumItems(curriculumItemMapper.selectList(new QueryWrapper<CurriculumItem>()
                    .eq("curriculum_id",curriculum.getId())));
        });
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(CurriculumParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Curriculum getOldEntity(CurriculumParam param) {
        return this.getById(getKey(param));
    }

    private Curriculum getEntity(CurriculumParam param) {
        Curriculum entity = new Curriculum();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
