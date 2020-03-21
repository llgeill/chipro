package cn.spark.chipro.websocket.api.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author liliguang
 * @description 消息推送
 * @date 2019/8/20 16:45
 */
@Data
public class MessageVO {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    @Size(min = 1, message = "用户id不能小于1")
    private List<String> userIds;


    /**
     * 消息内容
     */
    private String text;

    /**
     * 消息时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
