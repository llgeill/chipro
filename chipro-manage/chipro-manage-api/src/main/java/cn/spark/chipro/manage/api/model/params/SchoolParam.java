package cn.spark.chipro.manage.api.model.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 学校
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Data
public class SchoolParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 学校编码
     */
    private String schoolId;

    /**
     * 父编码
     */
    private String pid;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 学校编码
     */
    private String code;

    /**
     * 学校邮箱
     */
    private String email;

    /**
     * 学校校长
     */
    private String principal;

    /**
     * 学校地址
     */
    private String address;

    /**
     * 学校电话
     */
    private String mobile;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remarks;
}
