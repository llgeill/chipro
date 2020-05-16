package cn.spark.chipro.websocket.biz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.spark.chipro.websocket.api.model.dto.MessageDTO;
import cn.spark.chipro.websocket.biz.entity.po.MessagePO;
import cn.spark.chipro.websocket.biz.entity.po.MessageUserPO;
import cn.spark.chipro.websocket.api.model.vo.NotReadPageVO;
import cn.spark.chipro.websocket.api.model.vo.ReadMessageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * @author liliguang
 * @description
 * @date 2020-03-21 19:06:33
 */
public interface MessageMapper extends BaseMapper<MessagePO> {

    public void insertMessage(MessagePO message);


    List<MessageDTO> selectMessageByUserId(String userId);

    List<MessageDTO> selectMessByUserId(String userId);

    public void insertMessageUser(MessageUserPO messageUser);


    public void updateMessageUser(String messageUserId);


    Page<MessagePO> selectNotReadPage(@Param("page") Page page, @Param("notReadPageVO")NotReadPageVO notReadPageVO);


    int updateMessageUserByread(ReadMessageVO readMessage);

    @Select("select USER_ID from OSS_USER")
    List<String> findUserIDs();
}
