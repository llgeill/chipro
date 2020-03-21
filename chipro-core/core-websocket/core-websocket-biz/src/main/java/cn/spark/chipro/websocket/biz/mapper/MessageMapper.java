package cn.spark.chipro.websocket.biz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.spark.chipro.websocket.api.model.dto.MessageDTO;
import cn.spark.chipro.websocket.biz.entity.po.MessagePO;
import cn.spark.chipro.websocket.biz.entity.po.MessageUserPO;
import cn.spark.chipro.websocket.biz.entity.vo.NotReadPageVO;
import cn.spark.chipro.websocket.biz.entity.vo.ReadMessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author liliguang
 * @description
 * @date 2020-03-21 19:06:33
 */
public interface MessageMapper extends BaseMapper<MessagePO> {

    public void insertMessage(MessagePO message);


    public List<MessageDTO> selectMessageByUserId(String userId);

    public void insertMessageUser(MessageUserPO messageUser);


    public void updateMessageUser(String messageUserId);


    Page<MessagePO> selectNotReadPage(@Param("page") Page page, NotReadPageVO read);


    int updateMessageUserByread(ReadMessageVO readMessage);
}
