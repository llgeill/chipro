package cn.spark.chipro.manage.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@TableName("test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 测验名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 测验说明(选填)
     */
    @TableField("`explain`")
    private String explain;

    /**
     * 截止时间
     */
    @TableField("deadline")
    private Integer deadline;

    /**
     * 提交次数(0:无次数限制，大于0表示次数限制)
     */
    @TableField("submit_num")
    private Integer submitNum;

    /**
     * 1:批改后立即公布成绩 2:提交截止后公布成绩 3:手动公布成绩
     */
    @TableField("results_reveal_status")
    private Integer resultsRevealStatus;

    /**
     * 是否公布答案、解析、批改评语(1:是，0:否)
     */
    @TableField("results_answer")
    private String resultsAnswer;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private List<Question> questions;


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Integer getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    public Integer getResultsRevealStatus() {
        return resultsRevealStatus;
    }

    public void setResultsRevealStatus(Integer resultsRevealStatus) {
        this.resultsRevealStatus = resultsRevealStatus;
    }

    public String getResultsAnswer() {
        return resultsAnswer;
    }

    public void setResultsAnswer(String resultsAnswer) {
        this.resultsAnswer = resultsAnswer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Test{" +
        "id=" + id +
        ", name=" + name +
        ", explain=" + explain +
        ", deadline=" + deadline +
        ", submitNum=" + submitNum +
        ", resultsRevealStatus=" + resultsRevealStatus +
        ", resultsAnswer=" + resultsAnswer +
        ", createTime=" + createTime +
        "}";
    }
}
