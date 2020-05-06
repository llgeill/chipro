package cn.spark.chipro.manage.biz.service.impl;

import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.manage.api.model.params.QuestionParam;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
    public Result add(TestParam param){
        Test entity = getEntity(param);
        List<QuestionParam> questionParamList = param.getQuestions();
        this.saveOrUpdate(entity);
        if(questionParamList == null){
            return Result.success();
        }
        if (questionParamList.size()>0){
            for (QuestionParam item :questionParamList){
                Question question = new Question();
                ToolUtil.copyProperties(item,question);
                if (question.getId() != null && questionMapper.selectById(question.getId())!=null){
                    questionMapper.updateById(question);
                }else {
                    questionMapper.insert(question);
                }
                TestQuestion testQuestion = new TestQuestion();
                testQuestion.setQuestionId(question.getId());
                testQuestion.setTestId(entity.getId());
                testQuestionMapper.insert(testQuestion);

            }

            return Result.success();
        }
        return Result.error("题目为空");
    }

    @Override
    public void delete(TestParam param){
        Map<String,Object> map = new HashMap<>();
        map.put("test_id",param.getId());
        testMapper.deleteByMap(map);
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
        List<TestResult> testResultList = new ArrayList<>();
        testMapper.selectList(new QueryWrapper<Test>().eq("submit_num",1)).forEach(test -> {
            TestResult testResult = new TestResult();
            ToolUtil.copyProperties(test,test);
            testResultList.add(testResult);
        });
        return testResultList;
    }

    @Override
    public PageInfo findPageBySpec(TestParam param){
        Page pageContext=getPageContext();
        QueryWrapper<Test>objectQueryWrapper=new QueryWrapper<>();
        IPage page=this.page(pageContext,objectQueryWrapper);
        List<Test> testList = page.getRecords();
        testList.forEach(test -> {
            List<TestQuestion> testQuestionList = testQuestionMapper.selectList(new QueryWrapper<TestQuestion>()
                    .select("question_id").eq("test_id",test.getId()));
            if(testQuestionList!=null && testQuestionList.size()>0){
                List<String>questionsId = testQuestionList.stream().map(testQuestion -> testQuestion.getQuestionId()).collect(Collectors.toList());
                List<Question> questionList = questionMapper.selectBatchIds(questionsId);
                test.setQuestions(questionList);
            }

        });
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
//        test.setId(UUID.randomUUID().toString().replace("-",""));
        test.setName("智能组题试卷");
        test.setSubmitNum(1);
        test.setResultsAnswer("1");
        test.setResultsRevealStatus(1);
        test.setCreateTime(new Date());
        result.setName(test.getName());
        result.setResultsAnswer(test.getResultsAnswer());
        result.setSubmitNum(test.getSubmitNum());
        testMapper.insert(test);
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
//                testQuestion.setId(UUID.randomUUID().toString().replace("-",""));
                testQuestion.setQuestionId(question.getId());
                testQuestion.setTestId(test.getId());
                testQuestionMapper.insert(testQuestion);
            }
        }

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
