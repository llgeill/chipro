package cn.spark.chipro.oss.biz.model.params;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Data
public class UserRoleParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    private String userAndRoleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createPerson;
}
