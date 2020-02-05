package cn.spark.chipro.community.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 作品
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@TableName("COMMUNITY_PRODUCTION")
public class Production implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作品编码
     */
    @TableId(value = "PRODUCTION_ID", type = IdType.ID_WORKER)
    private String productionId;

    /**
     * 用户编码
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 作品名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 介绍
     */
    @TableField("INTRODUCE")
    private String introduce;

    /**
     * 说明
     */
    @TableField("INSTRUCTION")
    private String instruction;

    /**
     * 标签
     */
    @TableField("LABEL")
    private String label;

    /**
     * 手机键盘
     */
    @TableField("MOBILE_KEYBOARD")
    private String mobileKeyboard;

    /**
     * 资源编码
     */
    @TableField("RESOURCE_ID")
    private String resourceId;

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


    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMobileKeyboard() {
        return mobileKeyboard;
    }

    public void setMobileKeyboard(String mobileKeyboard) {
        this.mobileKeyboard = mobileKeyboard;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
        return "Production{" +
        "productionId=" + productionId +
        ", userId=" + userId +
        ", name=" + name +
        ", introduce=" + introduce +
        ", instruction=" + instruction +
        ", label=" + label +
        ", mobileKeyboard=" + mobileKeyboard +
        ", resourceId=" + resourceId +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        "}";
    }
}
