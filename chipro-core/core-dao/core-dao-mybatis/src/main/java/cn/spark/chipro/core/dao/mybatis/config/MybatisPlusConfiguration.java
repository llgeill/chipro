package cn.spark.chipro.core.dao.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>mybatis-plus配置</p>
 *
 * @author : liliguang
 * @date : 2019-11-04 15:26
 **/
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
//开启事务
@EnableTransactionManagement(proxyTargetClass = true)
//扫描mapper代理类
@MapperScan(basePackages = {"cn.spark.chipro.**.mapper"})
public class MybatisPlusConfiguration {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
        return paginationInterceptor;
    }


    /**
     * 自定义公共字段自动注入
     */
    @ConditionalOnMissingBean
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new AutoMetaObjectHandler();
    }
}
