package cn.spark.chipro.manage.api.model.params;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 资源
 * </p>
 *
 * @author 李利光
 * @since 2020-02-09
 */
@Data
public class ResourceParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 资源编码
     */
    private String resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源路径
     */
    private String resourcePath;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remarks;
}
