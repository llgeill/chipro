package cn.spark.chipro.manage.api.model.result;

import lombok.Data;

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
public class ClassRoomResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 课室编码
     */
    private String classId;

    /**
     * 课室名称
     */
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
    private String schoolId;

    /**
     * 备注
     */
    private String remarks;

}
