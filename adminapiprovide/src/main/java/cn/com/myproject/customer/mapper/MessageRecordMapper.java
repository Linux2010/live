package cn.com.myproject.customer.mapper;


import cn.com.myproject.customer.entity.PO.MessageRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageRecordMapper {

    int deleteByPrimaryKey(String messageId);

    int insert(MessageRecord record);

    int insertSelective(MessageRecord record);

    MessageRecord selectByPrimaryKey(String messageId);

    Integer getMessageCount(MessageRecord record);

    List<MessageRecord> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("userId")String keyword);

    int updateByPrimaryKeySelective(MessageRecord record);

    int updateByPrimaryKey(MessageRecord record);

    List<MessageRecord> getListByRelationId(String relationId);

    List<MessageRecord> getMessageByGP(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("map")Map<String,Object> map);

    List<MessageRecord> getPageByMap(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("map")Map<String,Object> map);

    int updateByTCU(@Param("map")Map<String,Object> map);

    /**
     * 微信成为下线的消息通知
     * @param receiveUserId
     * @return
     */
    List<MessageRecord> wxbecomelowerlevelnotice(@Param("receiveUserId") String receiveUserId);

}