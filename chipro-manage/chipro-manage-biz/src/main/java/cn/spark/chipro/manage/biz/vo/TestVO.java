package cn.spark.chipro.manage.biz.vo;

import cn.spark.chipro.manage.biz.entity.Question;

import java.util.List;

/**
 * @author liaochaofan
 * @date 2020/4/12 18:53
 */
public class TestVO {

    /**
     * 测验名称
     */
    private String name;

    /**
     * 测验说明(选填)
     */
    private String explain;

    /**
     * 提交次数(0:无次数限制，大于0表示次数限制)
     */
    private Integer submitNum;

    /**
     * 是否公布答案、解析、批改评语(1:是，0:否)
     */
    private String resultsAnswer;

    private List<Question> questionList;

    public String getResultsAnswer() {
        return resultsAnswer;
    }

    public void setResultsAnswer(String resultsAnswer) {
        this.resultsAnswer = resultsAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void setQuestionListItem(Question question){
        this.questionList.add(question);
    }
}
