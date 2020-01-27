package cn.spark.chipro.test.model.params;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Data
public class OssRolePermissionParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private String roleAndPermissionId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createPerson;
}
