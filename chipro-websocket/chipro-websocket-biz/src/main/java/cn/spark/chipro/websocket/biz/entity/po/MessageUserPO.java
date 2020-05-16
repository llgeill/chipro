package cn.spark.chipro.websocket.biz.entity.po;

import lombok.Data;

/**
 * @author liliguang
 * @description
 * @date 2019/5/29
 */
@Data
public class MessageUserPO {
    /**
     * 消息用户主键
     */
    private String messageUserId;

    /**
     * 消息主键
     */
    private String messageId;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 是否已读
     */
    private Integer isRead;

    /**
     * 是否推送
     */
    private Integer isSend;
}
