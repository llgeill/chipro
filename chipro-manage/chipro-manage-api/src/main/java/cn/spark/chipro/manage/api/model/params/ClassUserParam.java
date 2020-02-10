package cn.spark.chipro.manage.api.model.params;

import cn.spark.chipro.manage.api.model.validated.InsertValidated;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 课室老师
 * </p>
 *
 * @author 李利光
 * @since 2020-02-07
 */
@Data
public class ClassUserParam implements Serializable{

    private static final long serialVersionUID = 1L;


    private String classUserId;

    /**
     * 用户编码
     */
    @NotEmpty(message = "用户编码不能为空",groups = InsertValidated.class)
    private String userId;

    /**
     * 课室编码
     */
    @NotEmpty(message = "课室编码不能为空",groups = InsertValidated.class)
    private String classRoomId;

    /**
     * 关系类型
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
