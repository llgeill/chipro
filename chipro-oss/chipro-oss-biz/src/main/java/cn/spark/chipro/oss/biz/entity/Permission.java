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
 * 权限表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@TableName("OSS_PERMISSION")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "PERMISSION_ID", type = IdType.ID_WORKER_STR)
    private String permissionId;

    /**
     * 权限名称
     */
    @TableField("PERMISSION_NAME")
    private String permissionName;

    /**
     * 权限说明
     */
    @TableField("PERMISSION_NOTE")
    private String permissionNote;

    /**
     * 权限备注
     */
    @TableField("PERMISSION_REMARK")
    private String permissionRemark;

    /**
     * 权限图标
     */
    @TableField("PERMISSION_ICON")
    private String permissionIcon;

    /**
     * 权限路径
     */
    @TableField("PERMISSION_PATH")
    private String permissionPath;

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

    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("UPDATE_PERSON")
    private String updatePerson;


    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionNote() {
        return permissionNote;
    }

    public void setPermissionNote(String permissionNote) {
        this.permissionNote = permissionNote;
    }

    public String getPermissionRemark() {
        return permissionRemark;
    }

    public void setPermissionRemark(String permissionRemark) {
        this.permissionRemark = permissionRemark;
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    @Override
    public String toString() {
        return "Permission{" +
        "permissionId=" + permissionId +
        ", permissionName=" + permissionName +
        ", permissionNote=" + permissionNote +
        ", permissionRemark=" + permissionRemark +
        ", permissionIcon=" + permissionIcon +
        ", permissionPath=" + permissionPath +
        ", createTime=" + createTime +
        ", createPerson=" + createPerson +
        ", updateTime=" + updateTime +
        ", updatePerson=" + updatePerson +
        "}";
    }
}
