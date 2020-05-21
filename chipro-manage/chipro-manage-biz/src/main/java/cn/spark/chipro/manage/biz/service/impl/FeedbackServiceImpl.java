package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Feedback;
import cn.spark.chipro.manage.biz.mapper.FeedbackMapper;
import cn.spark.chipro.manage.api.model.params.FeedbackParam;
import cn.spark.chipro.manage.api.model.result.FeedbackResult;
import cn.spark.chipro.manage.biz.service.FeedbackService;
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
 * 反馈 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-05-09
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Override
    public void add(FeedbackParam param){
        Feedback entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FeedbackParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FeedbackParam param){
        Feedback oldEntity = getOldEntity(param);
        Feedback newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public FeedbackResult findBySpec(FeedbackParam param){
        return null;
    }

    @Override
    public List<FeedbackResult> findListBySpec(FeedbackParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(FeedbackParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Feedback>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(FeedbackParam param){
        return param.getFeedbackId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Feedback getOldEntity(FeedbackParam param) {
        return this.getById(getKey(param));
    }

    private Feedback getEntity(FeedbackParam param) {
        Feedback entity = new Feedback();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
