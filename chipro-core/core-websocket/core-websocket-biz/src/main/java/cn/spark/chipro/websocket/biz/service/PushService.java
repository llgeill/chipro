package cn.spark.chipro.websocket.biz.service;


import cn.spark.chipro.websocket.biz.entity.vo.MessageVO;
import cn.spark.chipro.websocket.biz.entity.vo.NotReadPageVO;
import cn.spark.chipro.websocket.biz.entity.vo.ReadMessageVO;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;

/**
 * @author liliguang
 * @description 推送接口
 * @date 2019/8/20 16:41
 */
public interface PushService {


    /**
     * 推送到用户
     *
     * @param message
     */
    void push(MessageVO message);

    /**
     * 分页查询 未读消息
     *
     * @param vo          筛选条件
     * @return
     */
    PageInfo notReadList(NotReadPageVO vo);

    /**
     * 阅读消息
     *
     * @param readMessage
     * @return
     */
    Result readMessage(ReadMessageVO readMessage);

}
