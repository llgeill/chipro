package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Review;
import cn.spark.chipro.manage.biz.mapper.ReviewMapper;
import cn.spark.chipro.manage.api.model.params.ReviewParam;
import cn.spark.chipro.manage.api.model.result.ReviewResult;
import  cn.spark.chipro.manage.biz.service.ReviewService;
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
 * 作品点评 服务实现类
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Override
    public void add(ReviewParam param){
        Review entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ReviewParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ReviewParam param){
        Review oldEntity = getOldEntity(param);
        Review newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ReviewResult findBySpec(ReviewParam param){
        return null;
    }

    @Override
    public List<ReviewResult> findListBySpec(ReviewParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(ReviewParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Review>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(ReviewParam param){
        return param.getReviewId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Review getOldEntity(ReviewParam param) {
        return this.getById(getKey(param));
    }

    private Review getEntity(ReviewParam param) {
        Review entity = new Review();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
