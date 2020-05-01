package cn.spark.chipro.test;

import io.seata.core.rpc.netty.RmMessageListener;
import io.seata.rm.datasource.DataCompareUtils;
import io.seata.rm.datasource.undo.UndoLogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.swing.undo.UndoManager;


/**
 * @author liliguang
 * @description 测试启动器
 * @date 2019/11/13
 */
@SpringCloudApplication
@EnableFeignClients("cn.spark.chipro")
public class TestApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class, args);
    }
}
