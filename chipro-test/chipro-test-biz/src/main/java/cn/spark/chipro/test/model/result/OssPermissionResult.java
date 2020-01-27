package cn.spark.chipro.test.model.result;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Data
public class OssPermissionResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限说明
     */
    private String permissionNote;

    /**
     * 权限备注
     */
    private String permissionRemark;

    /**
     * 权限图标
     */
    private String permissionIcon;

    /**
     * 权限路径
     */
    private String permissionPath;

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
