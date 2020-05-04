package cn.spark.chipro.community.api.model.params;

import cn.spark.chipro.community.api.model.validated.InsertValidated;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.BitSet;
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
public class ProductionParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 作品编码
     */
    private String productionId;

    /**
     * 用户编码
     */
    @NotEmpty(message = "用户编码不能为空",groups = InsertValidated.class)
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
    @NotEmpty(message = "资源编码不能为空",groups = InsertValidated.class)
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

    /**
     * 发布状态 0：未发布 1：已发布
     */
    @NotEmpty(message = "发布状态不能为空",groups = InsertValidated.class)
    private String status;


    /**
     * 图片地址
     */
    private String image;



    /**
     * 点赞数量
     */
    private String glike;

    /**
     * 点击
     */
    private String click;

}
