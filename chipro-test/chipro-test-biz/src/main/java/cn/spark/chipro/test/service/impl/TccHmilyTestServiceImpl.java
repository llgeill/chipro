package cn.spark.chipro.test.service.impl;

import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.test.entity.Test;
import cn.spark.chipro.test.mapper.HmilyUtilMapper;
import cn.spark.chipro.test.mapper.TestMapper;
import cn.spark.chipro.test.model.params.TestParam;
import cn.spark.chipro.websocket.api.feign.WebSocketFeignService;
import cn.spark.chipro.websocket.api.model.vo.MessageVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TccHmilyTestServiceImpl extends ServiceImpl<TestMapper, Test> {

    @Autowired
    private WebSocketFeignService webSocketFeignService;

    @Autowired
    private HmilyUtilMapper hmilyUtilMapper;

    private String id;


    /**
     * 	try幂等校验
     * 	try悬挂处理
     * 	插入一条数据并且发送通知
     */
    @Transactional
    @Hmily(confirmMethod="confirmMethod",cancelMethod="cancelMethod")
    public void tryMethod(){
        //打印全局事务ID
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("******** Test Service Begin try ..."+transId);
        //幂等判断 判断local_try_log表中是否有try日志记录，如果有则不再执行
        if(hmilyUtilMapper.isExistTry(transId)>0){
            log.info("test服务 try 已经执行，无需重复执行,xid:{}",transId);
            return ;
        }
        //try悬挂处理，如果cancel、confirm有一个已经执行了，try不再执行
        if(hmilyUtilMapper.isExistConfirm(transId)>0 || hmilyUtilMapper.isExistCancel(transId)>0){
            log.info("test服务 try悬挂处理  cancel或confirm已经执行，不允许执行try,xid:{}",transId);
            return ;
        }
        //业务操作
        Test testParam1 = new Test();
        testParam1.setTestContent("11111");
        testParam1.setTestName("测试Hmily分布式事务");
        this.save(testParam1);
        id=testParam1.getTestId();
        //插入try执行记录,用于幂等判断
        hmilyUtilMapper.addTry(transId);

        //远程调用李四，转账
        MessageVO messageVO = new MessageVO();
        List<String> ids = new ArrayList<>();
        ids.add("1");
        messageVO.setUserIds(ids);
        messageVO.setMessageTime(new Date());
        messageVO.setText("测试分布式事务");
        Result push = webSocketFeignService.push(messageVO);
        if(push.getCode()==null || push.getCode()!=1){
            throw new RuntimeException("Test 远程调用WebSocket微服务失败,xid:{}"+transId);
        }
        //流程结束
        log.info("Test服务 try end 结束执行...xid:{}",transId);
    }

    @Transactional
    public void confirmMethod(){
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("Test confirm begin 开始执行...xid:{}",transId);
    }

    @Transactional
    public void cancelMethod(){
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("Test cancel begin 开始执行...xid:{}",transId);
        //	cancel幂等校验
        if(hmilyUtilMapper.isExistCancel(transId)>0){
            log.info("Test cancel 已经执行，无需重复执行,xid:{}",transId);
            return ;
        }
        //cancel空回滚处理，如果try没有执行，cancel不允许执行
        if(hmilyUtilMapper.isExistTry(transId)<=0){
            log.info("Test 空回滚处理，try没有执行，不允许cancel执行,xid:{}",transId);
            return ;
        }
        //业务处理，此处是把新增的数据删除
        this.removeById(id);
        //插入一条cancel的执行记录
        hmilyUtilMapper.addCancel(transId);
        log.info("Test cancel end 结束执行...xid:{}",transId);
    }
}
