package cn.spark.chipro.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author: liliguang
 * @date: 2019-11-07 21:26
 * @description: 通过数据库加载客户端详情
 */
@Configuration
public class CustomDBClientDetailsService {

    @Autowired
    private DataSource dataSource;


    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

}
