package cn.spark.chipro.websocket.api.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liliguang
 * @description
 * @date 2019/8/20 17:15
 */
@Data
public class MessageDTO implements Serializable {

    /**
     * 消息主鍵
     */
    private String messageId;
    /**
     * 消息内容
     */
    private String text;

    /**
     * 消息时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date messageTime;

    /**
     * 消息类型
     */

    private String messageType;

    /**
     * 业务主键
     */
    private String businessId;

    /**
     * 消息用户主键
     */
    private String messageUserId;

    /**
     * 用户id
     */
    private String userId;
}
