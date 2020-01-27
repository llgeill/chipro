package cn.spark.chipro.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李利光
 * @since 2020-01-27
 */
@TableName("oauth_client_details")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @TableId(value = "CLIENT_ID", type = IdType.ID_WORKER)
    private String clientId;

    /**
     * 资源id集合
     */
    @TableField("RESOURCE_IDS")
    private String resourceIds;

    /**
     * 客户端密码
     */
    @TableField("CLIENT_SECRET")
    private String clientSecret;

    /**
     * 客户端权限
     */
    @TableField("SCOPE")
    private String scope;

    /**
     * 客户端授权模式
     */
    @TableField("AUTHORIZED_GRANT_TYPES")
    private String authorizedGrantTypes;

    /**
     * 客户端重定向uri
     */
    @TableField("WEB_SERVER_REDIRECT_URI")
    private String webServerRedirectUri;

    /**
     * 用户的权限范围
     */
    @TableField("AUTHORITIES")
    private String authorities;

    /**
     * access_token的有效时间(秒)
     */
    @TableField("ACCESS_TOKEN_VALIDITY")
    private Integer accessTokenValidity;

    /**
     * refresh_token有效期(秒)
     */
    @TableField("REFRESH_TOKEN_VALIDITY")
    private Integer refreshTokenValidity;

    /**
     * 补充信息(json)
     */
    @TableField("ADDITIONAL_INFORMATION")
    private String additionalInformation;

    /**
     * 是否自动approval操作
     */
    @TableField("AUTOAPPROVE")
    private String autoapprove;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    @Override
    public String toString() {
        return "OauthClientDetails{" +
        "clientId=" + clientId +
        ", resourceIds=" + resourceIds +
        ", clientSecret=" + clientSecret +
        ", scope=" + scope +
        ", authorizedGrantTypes=" + authorizedGrantTypes +
        ", webServerRedirectUri=" + webServerRedirectUri +
        ", authorities=" + authorities +
        ", accessTokenValidity=" + accessTokenValidity +
        ", refreshTokenValidity=" + refreshTokenValidity +
        ", additionalInformation=" + additionalInformation +
        ", autoapprove=" + autoapprove +
        "}";
    }
}
