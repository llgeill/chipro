package cn.spark.chipro.community.api.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 作品
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Data
public class ProductionResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 作品编码
     */
    private String productionId;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 作品名称
     */
    private String name;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 说明
     */
    private String instruction;

    /**
     * 标签
     */
    private String label;

    /**
     * 手机键盘
     */
    private String mobileKeyboard;

    /**
     * 资源编码
     */
    private String resourceId;

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
