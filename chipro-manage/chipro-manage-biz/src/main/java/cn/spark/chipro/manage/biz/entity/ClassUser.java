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
 * 课室老师
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
@TableName("MANAGE_CLASS_USER")
public class ClassUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CLASS_USER_ID", type = IdType.ID_WORKER_STR)
    private String classUserId;

    /**
     * 用户编码
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 课室编码
     */
    @TableField("CLASS_ROOM_ID")
    private String classRoomId;

    /**
     * 关系类型
     */
    @TableField("TYPE")
    private String type;

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


    public String getClassUserId() {
        return classUserId;
    }

    public void setClassUserId(String classUserId) {
        this.classUserId = classUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "ClassUser{" +
        "classUserId=" + classUserId +
        ", userId=" + userId +
        ", classRoomId=" + classRoomId +
        ", type=" + type +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        "}";
    }
}
