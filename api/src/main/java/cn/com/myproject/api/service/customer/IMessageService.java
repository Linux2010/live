package cn.com.myproject.api.service.customer;

import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.entity.VO.MessageRecordVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.10.14
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IMessageService {

    @GetMapping("/messagerecord/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("businessId") String businessId);

    @GetMapping("/messagerecord/selectByPrimaryKey")
    MessageRecord selectByPrimaryKey(@RequestParam("businessId") String businessId);

    @GetMapping("/messagerecord/getPage")
    PageInfo<MessageRecordVO> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") int keyword);

    @PostMapping("/messagerecord/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody MessageRecord record);

    @PostMapping("/messagerecord/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody MessageRecord record);

    @GetMapping("/messagerecord/getListByRelationId")
    public List<MessageRecord> getListByRelationId(@RequestParam("relationId") String relationId);

    @PostMapping("/messagerecord/getPageByGP")
    PageInfo<MessageRecordVO> getPageByGP(@RequestBody Map<String,Object> map);

    @PostMapping("/messagerecord/getPageByMap")
    PageInfo<MessageRecordVO> getPageByMap(@RequestBody Map<String,Object> map);

    @PostMapping("/messagerecord/getMessageCount")
    Integer getMessageCount(@RequestBody MessageRecord messageRecord);

    @PostMapping("/messagerecord/updateByTCU")
    int updateByTCU(@RequestBody Map<String,Object> map);

    @PostMapping("/messagerecord/wxbecomelowerlevelnotice")
    public List<MessageRecord> wxbecomelowerlevelnotice(@RequestParam("receiveUserId") String receiveUserId);

}
