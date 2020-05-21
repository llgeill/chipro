package cn.spark.chipro.manage.api.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 作品点评
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Data
public class ReviewResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 点评编码
     */
    private String reviewId;

    /**
     * 任务编码
     */
    private String taskId;

    /**
     * 学生编码
     */
    private String userId;

    /**
     * 点评内容
     */
    private String reviewContent;

    /**
     * 作品得分
     */
    private String scope;

    /**
     * 作品编码
     */
    private String productionId;

    /**
     * 状态
     */
    private String status;

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
     * 修改编码
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remarks;

}
