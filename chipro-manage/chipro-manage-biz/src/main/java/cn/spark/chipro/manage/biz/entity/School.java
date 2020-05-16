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
 * 学校
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@TableName("MANAGE_SCHOOL")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学校编码
     */
    @TableId(value = "SCHOOL_ID", type = IdType.ID_WORKER_STR)
    private String schoolId;

    /**
     * 父编码
     */
    @TableField("PID")
    private String pid;

    /**
     * 学校名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 学校编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 学校邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 学校校长
     */
    @TableField("PRINCIPAL")
    private String principal;

    /**
     * 学校地址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 学校电话
     */
    @TableField("MOBILE")
    private String mobile;

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


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        return "School{" +
        "schoolId=" + schoolId +
        ", pid=" + pid +
        ", name=" + name +
        ", code=" + code +
        ", email=" + email +
        ", principal=" + principal +
        ", address=" + address +
        ", mobile=" + mobile +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        "}";
    }
}
