package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.TestQuestion;
import cn.spark.chipro.manage.biz.mapper.TestQuestionMapper;
import cn.spark.chipro.manage.api.model.params.TestQuestionParam;
import cn.spark.chipro.manage.api.model.result.TestQuestionResult;
import  cn.spark.chipro.manage.biz.service.TestQuestionService;
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
public class TestQuestionServiceImpl extends ServiceImpl<TestQuestionMapper, TestQuestion> implements TestQuestionService {

    @Override
    public void add(TestQuestionParam param){
        TestQuestion entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TestQuestionParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TestQuestionParam param){
        TestQuestion oldEntity = getOldEntity(param);
        TestQuestion newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TestQuestionResult findBySpec(TestQuestionParam param){
        return null;
    }

    @Override
    public List<TestQuestionResult> findListBySpec(TestQuestionParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(TestQuestionParam param){
        Page pageContext=getPageContext();
        QueryWrapper<TestQuestion>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    private Serializable getKey(TestQuestionParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private TestQuestion getOldEntity(TestQuestionParam param) {
        return this.getById(getKey(param));
    }

    private TestQuestion getEntity(TestQuestionParam param) {
        TestQuestion entity = new TestQuestion();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
