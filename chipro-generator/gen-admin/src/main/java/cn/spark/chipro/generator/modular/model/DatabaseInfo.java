package cn.spark.chipro.generator.modular.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据源实体
 * </p>
 *
 * @author fengshuonan
 * @since 2019-01-11
 */
@Entity
@Table(name = "DATABASE_INFO")
@Data
public class DatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @Column(name = "DB_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "databaseInfo_seq")
    @SequenceGenerator(name = "databaseInfo_seq", sequenceName = "databaseInfo_seq", allocationSize = 1)
    private Long dbId;

    /**
     * 数据源名称
     */
    @Column(name = "DB_NAME", unique = true)
    @NotNull
    private String dbName;

    /**
     * jdbc的驱动类型
     */
    @Column(name = "JDBC_DRIVER", length = 500)
    @NotNull
    private String jdbcDriver;

    /**
     * 数据库连接的账号
     */
    @Column(name = "USER_NAME", length = 500)
    @NotNull
    private String userName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", length = 500)
    @NotNull
    private String password;

    /**
     * jdbc的url
     */
    @Column(name = "JDBC_URL", length = 500)
    @NotNull
    private String jdbcUrl;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createTime = new Date();

}
