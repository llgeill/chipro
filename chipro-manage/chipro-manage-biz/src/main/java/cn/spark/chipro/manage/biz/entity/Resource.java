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
 * 资源
 * </p>
 *
 * @author 李利光
 * @since 2020-02-09
 */
@TableName("MANAGE_RESOURCE")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编码
     */
    @TableId(value = "RESOURCE_ID", type = IdType.ID_WORKER_STR)
    private String resourceId;

    /**
     * 资源名称
     */
    @TableField("RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源路径
     */
    @TableField("RESOURCE_PATH")
    private String resourcePath;

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
     * 备注
     */
    @TableField("REMARKS")
    private String remarks;


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Resource{" +
        "resourceId=" + resourceId +
        ", resourceName=" + resourceName +
        ", resourcePath=" + resourcePath +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", remarks=" + remarks +
        "}";
    }
}
