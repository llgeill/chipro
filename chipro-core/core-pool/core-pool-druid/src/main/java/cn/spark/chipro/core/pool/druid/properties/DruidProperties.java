package cn.spark.chipro.core.pool.druid.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <h3>chipro</h3>
 * <p>druid属性配置</p>
 *
 * @author : liliguang
 * @date : 2019-11-19 09:56
 **/
@Data
public class DruidProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private Integer initialSize = 2;

    private Integer minIdle = 1;

    private Integer maxActive = 20;

    private Integer maxWait = 60000;

    private Integer timeBetweenEvictionRunsMillis = 60000;

    private Integer minEvictableIdleTimeMillis = 300000;

    private String validationQuery;

    private Boolean testWhileIdle = true;

    private Boolean testOnBorrow = false;

    private Boolean testOnReturn = false;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 20;

    private String filters = "stat";

    private String dataSourceName;

    public void config(DruidDataSource dataSource) {

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize);     //定义初始连接数
        dataSource.setMinIdle(minIdle);             //最小空闲
        dataSource.setMaxActive(maxActive);         //定义最大连接数
        dataSource.setMaxWait(maxWait);             //最长等待时间

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(getValidateQueryByUrl(url));
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Properties createProperties() {
        Properties properties = new Properties();
        properties.put("url", this.url);
        properties.put("username", this.username);
        properties.put("password", this.password);
        properties.put("driverClassName", this.driverClassName);
        properties.put("initialSize", this.initialSize);
        properties.put("maxActive", this.maxActive);
        properties.put("minIdle", this.minIdle);
        properties.put("maxWait", this.maxWait);
        properties.put("poolPreparedStatements", this.poolPreparedStatements);
        properties.put("maxPoolPreparedStatementPerConnectionSize", this.maxPoolPreparedStatementPerConnectionSize);
        properties.put("validationQuery", getValidateQueryByUrl(this.url));
        properties.put("testOnBorrow", this.testOnBorrow);
        properties.put("testOnReturn", this.testOnReturn);
        properties.put("testWhileIdle", this.testWhileIdle);
        properties.put("timeBetweenEvictionRunsMillis", this.timeBetweenEvictionRunsMillis);
        properties.put("minEvictableIdleTimeMillis", this.minEvictableIdleTimeMillis);
        properties.put("filters", this.filters);
        return properties;
    }

    private String getValidateQueryByUrl(String url) {
        if (url.contains("oracle")) {
            return "select 1 from dual";
        } else if (url.contains("postgresql")) {
            return "select version()";
        } else if (url.contains("sqlserver")) {
            return "select 1";
        } else {
            return "select 1";
        }
    }

}

