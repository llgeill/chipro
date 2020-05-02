package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Question;
import cn.spark.chipro.manage.biz.mapper.QuestionMapper;
import cn.spark.chipro.manage.api.model.params.QuestionParam;
import cn.spark.chipro.manage.api.model.result.QuestionResult;
import  cn.spark.chipro.manage.biz.service.QuestionService;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public void add(QuestionParam param){
        Question entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QuestionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QuestionParam param){
        Question oldEntity = getOldEntity(param);
        Question newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QuestionResult findBySpec(QuestionParam param){
        return null;
    }

    @Override
    public List<QuestionResult> findListBySpec(QuestionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(QuestionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Question>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(QuestionParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Question getOldEntity(QuestionParam param) {
        return this.getById(getKey(param));
    }

    private Question getEntity(QuestionParam param) {
        Question entity = new Question();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
