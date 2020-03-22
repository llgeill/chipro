package cn.spark.chipro.websocket.biz.service.impl;


import cn.spark.chipro.websocket.biz.config.RabbitMqConfig;
import cn.spark.chipro.websocket.api.model.dto.MessageDTO;
import cn.spark.chipro.websocket.biz.entity.po.MessagePO;
import cn.spark.chipro.websocket.biz.entity.po.MessageUserPO;
import cn.spark.chipro.websocket.api.model.vo.MessageVO;
import cn.spark.chipro.websocket.api.model.vo.NotReadPageVO;
import cn.spark.chipro.websocket.api.model.vo.ReadMessageVO;
import cn.spark.chipro.websocket.biz.handler.WebSocketHandler;
import cn.spark.chipro.websocket.biz.mapper.MessageMapper;
import cn.spark.chipro.websocket.biz.service.PushService;

import cn.spark.chipro.core.page.PageFactory;
import cn.spark.chipro.core.page.PageInfo;
import cn.spark.chipro.core.result.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liliguang
 * @description 推送实现
 * @date 2020-03-22 00:42:26
 */
@Slf4j
@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private MessageMapper messageMapper;


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    @Transactional
    public void push(MessageVO message) {

        List<MessageDTO> messages = saveMessageAndMessageUser(message);

        if (messages != null && messages.size() > 0) {
            messages.forEach(e -> {
                pushMessage(e);
            });
        }
    }


    @Override
    public PageInfo notReadList(NotReadPageVO vo) {
        Page page = PageFactory.defaultPage();
        Page<MessagePO> messagePOS = messageMapper.selectNotReadPage(page, vo);
        return PageFactory.createPageInfo(page);
    }


    @Override
    public Result readMessage(ReadMessageVO readMessage) {
        int i = messageMapper.updateMessageUserByread(readMessage);

        return i > 0 ? Result.success() : Result.error();
    }


    private void pushMessage(MessageDTO message) {
        boolean b = false;
        try {
            b = WebSocketHandler.sendToUser(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("websocket推送异常", e);
        }
        //如果没发成功则通过消息队列发送
        if (!b) {
            amqpTemplate.convertAndSend(RabbitMqConfig.MESSAGE_WEBSOCKET_TOPIC_NAME, message);
        }
    }

    /**
     * 保存数据到数据库
     *
     * @param message
     * @return
     */
    @Transactional
    public List<MessageDTO> saveMessageAndMessageUser(MessageVO message) {
        List<MessageDTO> messages = new ArrayList<>();

        MessagePO messagePo = new MessagePO();

        BeanUtils.copyProperties(message, messagePo);
        messageMapper.insertMessage(messagePo);
        List<String> userIds = message.getUserIds();

        for (int i = 0; i < userIds.size(); i++) {
            MessageUserPO userPo = new MessageUserPO();
            userPo.setUserId(userIds.get(i));
            userPo.setMessageId(messagePo.getMessageId());
            messageMapper.insertMessageUser(userPo);
            //发送消息
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(messagePo, messageDTO);
            BeanUtils.copyProperties(userPo, messageDTO);

            messages.add(messageDTO);
        }

        return messages;
    }
}
