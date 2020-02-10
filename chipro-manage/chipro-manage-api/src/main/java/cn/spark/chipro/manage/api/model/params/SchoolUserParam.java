package cn.spark.chipro.manage.api.model.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 学校校长
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
@Data
public class SchoolUserParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String schoolUserId;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 学校编码
     */
    private String schoolId;

    /**
     * 关系类型 1:校长 2:老师
     */
    private String type;

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
