package cn.spark.chipro.websocket.biz.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liliguang
 * @description
 * @date 2019/5/21
 */
@Data
public class MessagePO {

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
    private Date messageTime;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 业务主键
     */
    private String businessId;


}
