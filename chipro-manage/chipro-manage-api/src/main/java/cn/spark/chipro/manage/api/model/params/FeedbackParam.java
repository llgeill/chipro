package cn.spark.chipro.manage.api.model.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 反馈
 * </p>
 *
 * @author 李利光
 * @since 2020-05-09
 */
@Data
public class FeedbackParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String feedbackId;

    private String userNameAlias;

    private String userName;

    private String title;

    private String content;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String contact;

    private String reply;

    private String mobile;

    private String email;

    private String userId;
}
