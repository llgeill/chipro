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
 * 角色权限关系表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@TableName("OSS_ROLE_PERMISSION")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ROLE_AND_PERMISSION_ID", type = IdType.ID_WORKER_STR)
    private String roleAndPermissionId;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private String roleId;

    /**
     * 权限id
     */
    @TableField("PERMISSION_ID")
    private String permissionId;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("CREATE_PERSON")
    private String createPerson;


    public String getRoleAndPermissionId() {
        return roleAndPermissionId;
    }

    public void setRoleAndPermissionId(String roleAndPermissionId) {
        this.roleAndPermissionId = roleAndPermissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
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
        return "RolePermission{" +
        "roleAndPermissionId=" + roleAndPermissionId +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        ", createTime=" + createTime +
        ", createPerson=" + createPerson +
        "}";
    }
}
