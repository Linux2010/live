package cn.com.myproject.customer.service;

import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.entity.VO.MessageRecordVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.8.15
 */
public interface IMessageRecordService {

    int deleteByPrimaryKey(String businessId);

    int insert(MessageRecord record);

    int insertSelective(MessageRecord record);

    int batchInsertSelective(Map<String,Object> map);

    MessageRecord selectByPrimaryKey(String businessId);

    Integer getMessageCount(MessageRecord record);

    PageInfo<MessageRecordVO> getPage(int pageNum, int pageSize, String keyword);

    int updateByPrimaryKeySelective(MessageRecord record);

    int updateByPrimaryKey(MessageRecord record);

    List<MessageRecord> getListByRelationId(String relationId);

    List<MessageRecord> getMessageByGP(int pageNum, int pageSize,Map<String,Object> map);

    PageInfo<MessageRecordVO> getPageByGP(Map<String,Object> map);

    PageInfo<MessageRecordVO>  getPageByMap(Map<String,Object> map);

    int insert4SendAll(int pageNum,int pageSize,Map<String,Object> map);

    int updateByTCU(@Param("map")Map<String,Object> map);

    List<MessageRecord> wxbecomelowerlevelnotice(String receiveUserId);
}
