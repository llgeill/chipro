package cn.spark.chipro.oss.biz.model.params;

import cn.spark.chipro.core.util.StringUtil;
import cn.spark.chipro.oss.biz.common.validated.InsertValidated;
import cn.spark.chipro.oss.biz.common.validated.UpdateValidated;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 李利光
 * @since 2020-01-31
 */
@Data
public class UserParam implements Serializable{

    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @NotEmpty(message = "用户id不能为空",groups = UpdateValidated.class)
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
    @NotEmpty(message = "密码不能为空",groups = InsertValidated.class)
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空",groups = InsertValidated.class)
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
    private boolean isEnabled = true;

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

    /**
     * 邮箱验证码
     */
    private String emailCode;

    /**
     * 参数校验
     * @return
     */
    public boolean check(){
        return StringUtil.isNotEmpty(userName) || StringUtil.isNotEmpty(mobile) || StringUtil.isNotEmpty(email);
    }
}
