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
 * 学校校长
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
@TableName("MANAGE_SCHOOL_USER")
public class SchoolUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SCHOOL_USER_ID", type = IdType.ID_WORKER_STR)
    private String schoolUserId;

    /**
     * 用户编码
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 学校编码
     */
    @TableField("SCHOOL_ID")
    private String schoolId;

    /**
     * 关系类型 1:校长 2:老师
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


    public String getSchoolUserId() {
        return schoolUserId;
    }

    public void setSchoolUserId(String schoolUserId) {
        this.schoolUserId = schoolUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
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
        return "SchoolUser{" +
        "schoolUserId=" + schoolUserId +
        ", userId=" + userId +
        ", schoolId=" + schoolId +
        ", type=" + type +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        "}";
    }
}
