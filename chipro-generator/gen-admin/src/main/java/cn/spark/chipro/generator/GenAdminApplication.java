package cn.spark.chipro.generator;

import cn.spark.chipro.generator.modular.model.DatabaseInfo;
import cn.spark.chipro.generator.modular.repository.DatabaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * 代码生成器启动类
 *
 * @author fengshuonan
 * @Date 2019/2/11 15:17
 */
@SpringBootApplication
public class GenAdminApplication {

    @Autowired
    private DatabaseInfoRepository databaseInfoRepository;

    public static void main(String[] args) {
        SpringApplication.run(GenAdminApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            DatabaseInfo databaseInfo = new DatabaseInfo();
            databaseInfo.setCreateTime(new Date());
            databaseInfo.setDbName("本地数据源");
            databaseInfo.setJdbcDriver("com.mysql.jdbc.Driver");
            databaseInfo.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/chipro?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=CTT");
            databaseInfo.setUserName("root");
            databaseInfo.setPassword("root");
            this.databaseInfoRepository.save(databaseInfo);
        };
    }
}

