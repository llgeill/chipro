package cn.spark.chipro.manage.api.model.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 学情报告
 * </p>
 *
 * @author 李利光
 * @since 2020-04-12
 */
@Data
public class LearnReportParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String learnReportId;

    /**
     * 学情报告名称
     */
    private String name;

    /**
     * 详细点评
     */
    private String detailReview;

    /**
     * 视频
     */
    private String video;

    /**
     * 图片
     */
    private String photo;

    /**
     * 任务
     */
    private String task;

    /**
     * 学生id
     */
    private Integer userId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
