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
 * 任务
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@TableName("MANAGE_TASK")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编码
     */
    @TableId(value = "TASK_ID", type = IdType.ID_WORKER)
    private String taskId;

    /**
     * 任务名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 指派课室
     */
    @TableField("ASSIGNED_CLASS")
    private String assignedClass;

    /**
     * 任务标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 作品提交数量
     */
    @TableField("WORKS_COUNT")
    private String worksCount;

    /**
     * 作品要求
     */
    @TableField("WORKS_REQUIRE")
    private String worksRequire;

    /**
     * 编辑器版本
     */
    @TableField("EDITOR")
    private String editor;

    /**
     * 资源编码
     */
    @TableField("RESOURCE_ID")
    private String resourceId;

    /**
     * 截止时间
     */
    @TableField("DEADLINE")
    private Date deadline;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private String updateUser;

    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("REMARKS")
    private String remarks;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignedClass() {
        return assignedClass;
    }

    public void setAssignedClass(String assignedClass) {
        this.assignedClass = assignedClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorksCount() {
        return worksCount;
    }

    public void setWorksCount(String worksCount) {
        this.worksCount = worksCount;
    }

    public String getWorksRequire() {
        return worksRequire;
    }

    public void setWorksRequire(String worksRequire) {
        this.worksRequire = worksRequire;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Task{" +
        "taskId=" + taskId +
        ", name=" + name +
        ", assignedClass=" + assignedClass +
        ", title=" + title +
        ", worksCount=" + worksCount +
        ", worksRequire=" + worksRequire +
        ", editor=" + editor +
        ", resourceId=" + resourceId +
        ", deadline=" + deadline +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        "}";
    }
}
