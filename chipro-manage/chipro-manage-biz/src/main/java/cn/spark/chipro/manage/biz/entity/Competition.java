package cn.spark.chipro.manage.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LCF
 * @since 2020-04-11
 */
@TableName("competition")
public class Competition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 审核状态
     */
    @TableField("audit_status")
    private String auditStatus;

    /**
     * 大赛名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 大赛详情
     */
    @TableField("desc")
    private String desc;

    /**
     * 报名链接
     */
    @TableField("sign_up_link")
    private String signUpLink;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSignUpLink() {
        return signUpLink;
    }

    public void setSignUpLink(String signUpLink) {
        this.signUpLink = signUpLink;
    }

    @Override
    public String toString() {
        return "Competition{" +
        "id=" + id +
        ", auditStatus=" + auditStatus +
        ", name=" + name +
        ", desc=" + desc +
        ", signUpLink=" + signUpLink +
        "}";
    }
}
