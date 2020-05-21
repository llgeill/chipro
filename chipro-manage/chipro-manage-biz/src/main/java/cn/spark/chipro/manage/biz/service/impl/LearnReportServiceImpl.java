package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.api.model.params.LearnReportParam;
import cn.spark.chipro.manage.api.model.result.LearnReportResult;
import cn.spark.chipro.manage.biz.entity.LearnReport;
import cn.spark.chipro.manage.biz.mapper.LearnReportMapper;
import  cn.spark.chipro.manage.biz.service.LearnReportService;
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
 * 学情报告 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-04-12
 */
@Service
public class LearnReportServiceImpl extends ServiceImpl<LearnReportMapper, LearnReport> implements LearnReportService {

    @Override
    public void add(LearnReportParam param){
        LearnReport entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(LearnReportParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(LearnReportParam param){
        LearnReport oldEntity = getOldEntity(param);
        LearnReport newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public LearnReportResult findBySpec(LearnReportParam param){
        return null;
    }

    @Override
    public List<LearnReportResult> findListBySpec(LearnReportParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(LearnReportParam param){
        Page pageContext=getPageContext();
        QueryWrapper<LearnReport>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(LearnReportParam param){
        return param.getLearnReportId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private LearnReport getOldEntity(LearnReportParam param) {
        return this.getById(getKey(param));
    }

    private LearnReport getEntity(LearnReportParam param) {
        LearnReport entity = new LearnReport();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
