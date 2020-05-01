//package cn.spark.chipro.websocket.biz.service.impl;
//
//import cn.spark.chipro.core.result.Result;
//
//
//import cn.spark.chipro.websocket.api.feign.WebSocketFeignService;
//import cn.spark.chipro.websocket.api.model.vo.MessageVO;
//import cn.spark.chipro.websocket.biz.entity.po.MessagePO;
//import cn.spark.chipro.websocket.biz.mapper.HmilyUtilMapper;
//import cn.spark.chipro.websocket.biz.mapper.MessageMapper;
//import cn.spark.chipro.websocket.biz.service.PushService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.hmily.annotation.Hmily;
//import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Slf4j
//public class TccHmilyTestServiceImpl extends ServiceImpl<MessageMapper, MessagePO> {
//
//
//    @Autowired
//    private HmilyUtilMapper hmilyUtilMapper;
//
//    @Autowired
//    private PushService pushService;
//
//    private String id;
//
//
//    /**
//     * 	try幂等校验
//     * 	try悬挂处理
//     * 	插入一条数据并且发送通知
//     */
//    @Transactional
//    @Hmily(confirmMethod="confirmMethod",cancelMethod="cancelMethod")
//    public void tryMethod(MessageVO messageVO){
//        //获取全局事务id
//        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
//        log.info("websocket try begin 开始执行...xid:{}",transId);
//    }
//
//    @Transactional
//    public void confirmMethod(MessageVO messageVO){
//        //获取全局事务id
//        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
//        log.info("websocket confirm begin 开始执行...xid:{}",transId);
//        //幂等性判断
//        if(hmilyUtilMapper.isExistConfirm(transId)>0){
//            log.info("websocket confirm 已经执行，无需重复执行...xid:{}",transId);
//            return ;
//        }
//        //业务处理
//        pushService.push(messageVO);
//        //增加一条confirm日志，用于幂等
//        hmilyUtilMapper.addConfirm(transId);
//        log.info("bank2 websocket end 结束执行...xid:{}",transId);
//    }
//
//    @Transactional
//    public void cancelMethod(MessageVO messageVO){
//        //获取全局事务id
//        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
//        log.info("websocket cancel begin 开始执行...xid:{}",transId);
//    }
//}
