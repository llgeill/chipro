package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Competition;
import cn.spark.chipro.manage.biz.mapper.CompetitionMapper;
import cn.spark.chipro.manage.api.model.params.CompetitionParam;
import cn.spark.chipro.manage.api.model.result.CompetitionResult;
import  cn.spark.chipro.manage.biz.service.CompetitionService;
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
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {

    @Override
    public void add(CompetitionParam param){
        Competition entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CompetitionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CompetitionParam param){
        Competition oldEntity = getOldEntity(param);
        Competition newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CompetitionResult findBySpec(CompetitionParam param){
        return null;
    }

    @Override
    public List<CompetitionResult> findListBySpec(CompetitionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(CompetitionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Competition>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(CompetitionParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Competition getOldEntity(CompetitionParam param) {
        return this.getById(getKey(param));
    }

    private Competition getEntity(CompetitionParam param) {
        Competition entity = new Competition();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
