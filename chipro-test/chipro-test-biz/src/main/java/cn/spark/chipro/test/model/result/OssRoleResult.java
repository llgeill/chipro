package cn.spark.chipro.test.model.result;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Data
public class OssRoleResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updatePerson;

}
