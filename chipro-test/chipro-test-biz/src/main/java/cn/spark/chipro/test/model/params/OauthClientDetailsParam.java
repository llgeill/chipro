package cn.spark.chipro.test.model.params;

import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@Data
public class OauthClientDetailsParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 资源id集合
     */
    private String resourceIds;

    /**
     * 客户端密码
     */
    private String clientSecret;

    /**
     * 客户端权限
     */
    private String scope;

    /**
     * 客户端授权模式
     */
    private String authorizedGrantTypes;

    /**
     * 客户端重定向uri
     */
    private String webServerRedirectUri;

    /**
     * 用户的权限范围
     */
    private String authorities;

    /**
     * access_token的有效时间(秒)
     */
    private Integer accessTokenValidity;

    /**
     * refresh_token有效期(秒)
     */
    private Integer refreshTokenValidity;

    /**
     * 补充信息(json)
     */
    private String additionalInformation;

    /**
     * 是否自动approval操作
     */
    private String autoapprove;
}
