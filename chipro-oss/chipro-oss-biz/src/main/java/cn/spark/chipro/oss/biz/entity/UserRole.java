package cn.spark.chipro.oss.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@TableName("OSS_USER_ROLE")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "USER_AND_ROLE_ID", type = IdType.ID_WORKER_STR)
    private String userAndRoleId;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private String roleId;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_PERSON", fill = FieldFill.INSERT)
    private String createPerson;


    public String getUserAndRoleId() {
        return userAndRoleId;
    }

    public void setUserAndRoleId(String userAndRoleId) {
        this.userAndRoleId = userAndRoleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "userAndRoleId=" + userAndRoleId +
        ", userId=" + userId +
        ", roleId=" + roleId +
        ", createTime=" + createTime +
        ", createPerson=" + createPerson +
        "}";
    }
}
