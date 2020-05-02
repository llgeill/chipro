package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.manage.biz.entity.Question;
import cn.spark.chipro.manage.biz.entity.Test;
import cn.spark.chipro.manage.biz.entity.TestQuestion;
import cn.spark.chipro.manage.biz.mapper.QuestionMapper;
import cn.spark.chipro.manage.biz.mapper.TestMapper;
import cn.spark.chipro.manage.api.model.params.TestParam;
import cn.spark.chipro.manage.api.model.result.TestResult;
import cn.spark.chipro.manage.biz.mapper.TestQuestionMapper;
import  cn.spark.chipro.manage.biz.service.TestService;
import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.util.ToolUtil;
import cn.spark.chipro.manage.biz.vo.TestVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TestQuestionMapper testQuestionMapper;

    @Autowired
    private TestMapper testMapper;

    @Override
    public void add(TestParam param){
        Test entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TestParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TestParam param){
        Test oldEntity = getOldEntity(param);
        Test newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TestResult findBySpec(TestParam param){
        return null;
    }

    @Override
    public List<TestResult> findListBySpec(TestParam param){
        return null;
    }

    @Override
    public PageInfo findPageBySpec(TestParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Test>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        return PageFactory.createPageInfo(page);
    }

    /**
     * 随机生成考卷
     * @param classify  专项分类
     * @param questionQuantity  题目数量
     * @return
     */
    public TestVO createRandomTest(String classify,Integer questionQuantity){
        TestVO result = new TestVO();
        Test test = new Test();
        test.setId(UUID.randomUUID().toString().replace("-",""));
        test.setName("智能组题试卷");
        test.setSubmitNum(1);
        test.setResultsAnswer("1");
        test.setResultsRevealStatus(1);
        test.setCreateTime(new Date());
        result.setName(test.getName());
        result.setResultsAnswer(test.getResultsAnswer());
        result.setSubmitNum(test.getSubmitNum());
        List<Question> questionList = questionMapper.selectList(new QueryWrapper<Question>().eq("classify",classify));
        List<Question> selectedQuestions = new ArrayList<>();
        if (questionList.size() < questionQuantity){
            selectedQuestions.addAll(questionList);
        }else {
            Random random = new Random();
            for(int i = 0;i < questionQuantity;i++){
                int num = random.nextInt(questionList.size());
                Question question = questionList.remove(num);
                selectedQuestions.add(question);
                TestQuestion testQuestion = new TestQuestion();
                testQuestion.setId(UUID.randomUUID().toString().replace("-",""));
                testQuestion.setQuestionId(question.getId());
                testQuestion.setTestId(test.getId());
                testQuestionMapper.insert(testQuestion);
            }
        }
        testMapper.insert(test);
        result.setQuestionList(selectedQuestions);
        return result;
    }

    private Serializable getKey(TestParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return new PageFactory().defaultPage();
    }

    private Test getOldEntity(TestParam param) {
        return this.getById(getKey(param));
    }

    private Test getEntity(TestParam param) {
        Test entity = new Test();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
