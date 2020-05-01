package cn.spark.chipro.manage.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 学情报告
 * </p>
 *
 * @author 李利光
 * @since 2020-04-12
 */
@TableName("MANAGE_LEARN_REPORT")
public class LearnReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LEARN_REPORT_ID", type = IdType.ID_WORKER)
    private String learnReportId;

    /**
     * 学情报告名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 详细点评
     */
    @TableField("DETAIL_REVIEW")
    private String detailReview;

    /**
     * 视频
     */
    @TableField("VIDEO")
    private String video;

    /**
     * 图片
     */
    @TableField("PHOTO")
    private String photo;

    /**
     * 任务
     */
    @TableField("TASK")
    private String task;

    /**
     * 学生id
     */
    @TableField("USER_ID")
    private Integer userId;

    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private String updateUser;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;


    public String getLearnReportId() {
        return learnReportId;
    }

    public void setLearnReportId(String learnReportId) {
        this.learnReportId = learnReportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailReview() {
        return detailReview;
    }

    public void setDetailReview(String detailReview) {
        this.detailReview = detailReview;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "LearnReport{" +
        "learnReportId=" + learnReportId +
        ", name=" + name +
        ", detailReview=" + detailReview +
        ", video=" + video +
        ", photo=" + photo +
        ", task=" + task +
        ", userId=" + userId +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}";
    }
}
