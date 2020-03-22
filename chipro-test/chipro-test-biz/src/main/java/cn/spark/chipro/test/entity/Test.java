package cn.spark.chipro.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@TableName("TEST")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "TEST_ID", type = IdType.ID_WORKER_STR)
    private String testId;

    /**
     * 名称
     */
    @TableField("TEST_NAME")
    private String testName;

    /**
     * 内容
     */
    @TableField("TEST_CONTENT")
    private String testContent;

    /**
     * 创建日期
     */
    @TableField("CREATE_DATE")
    private Date createDate;

    /**
     * 创建人
     */
    @TableField("CREATE_PERSON")
    private String createPerson;

    /**
     * 更新日期
     */
    @TableField("UPDATE_DATE")
    private Date updateDate;

    /**
     * 更新人
     */
    @TableField("UPDATE_PERSON")
    private Date updatePerson;


    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Date updatePerson) {
        this.updatePerson = updatePerson;
    }

    @Override
    public String toString() {
        return "Test{" +
        "testId=" + testId +
        ", testName=" + testName +
        ", testContent=" + testContent +
        ", createDate=" + createDate +
        ", createPerson=" + createPerson +
        ", updateDate=" + updateDate +
        ", updatePerson=" + updatePerson +
        "}";
    }
}
