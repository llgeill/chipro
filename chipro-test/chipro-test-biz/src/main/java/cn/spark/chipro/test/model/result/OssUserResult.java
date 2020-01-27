package cn.spark.chipro.test.model.result;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Data
public class OssUserResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户名称
     */
    private String userNameAlias;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 失效时间
     */
    private Date failureTime;

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
