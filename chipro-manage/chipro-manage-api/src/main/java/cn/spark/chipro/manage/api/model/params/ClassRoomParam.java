package cn.spark.chipro.manage.api.model.params;

import cn.spark.chipro.manage.api.model.validated.InsertValidated;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 课室
 * </p>
 *
 * @author 李利光
 * @since 2020-02-05
 */
@Data
public class ClassRoomParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 课室编码
     */
    private String classId;

    /**
     * 课室名称
     */
    @NotEmpty(message = "课室名称不能为空",groups = InsertValidated.class)
    private String name;

    /**
     * 课室编码
     */
    private String code;

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
     * 学校编码
     */
    @NotEmpty(message = "学校编码不能为空",groups = InsertValidated.class)
    private String schoolId;

    /**
     * 备注
     */
    private String remarks;
}
