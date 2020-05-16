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
 * 反馈
 * </p>
 *
 * @author 李利光
 * @since 2020-05-09
 */
@TableName("MANAGE_FEEDBACK")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="FEEDBACK_ID",type = IdType.ID_WORKER_STR)
    private String feedbackId;

    @TableField("USER_NAME_ALIAS")
    private String userNameAlias;

    @TableField("USER_NAME")
    private String userName;

    @TableField("TITLE")
    private String title;

    @TableField("CONTENT")
    private String content;

    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private String updateUser;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField("CONTACT")
    private String contact;

    @TableField("REPLY")
    private String reply;

    @TableField("MOBILE")
    private String mobile;

    @TableField("EMAIL")
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserNameAlias() {
        return userNameAlias;
    }

    public void setUserNameAlias(String userNameAlias) {
        this.userNameAlias = userNameAlias;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Feedback{" +
        "feedbackId=" + feedbackId +
        ", userNameAlias=" + userNameAlias +
        ", userName=" + userName +
        ", title=" + title +
        ", content=" + content +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", contact=" + contact +
        ", reply=" + reply +
        "}";
    }
}
