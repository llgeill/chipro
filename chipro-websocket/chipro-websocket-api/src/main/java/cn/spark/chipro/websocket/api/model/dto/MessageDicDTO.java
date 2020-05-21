package cn.spark.chipro.websocket.api.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 消息业务字典
 * @Author liliguang
 * @Date 2019/9/21 17:01
 */
@Data
public class MessageDicDTO implements Serializable {

    private Integer id;

    private String name;

    private String code;

    private Integer type;
}
