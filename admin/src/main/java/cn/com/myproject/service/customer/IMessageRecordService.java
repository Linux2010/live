package cn.com.myproject.service.customer;

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
 * Created by CQC on 2017.8.18.
 */
@FeignClient(value = "admin-api-provide",url = "${feignclient.url}")
public interface IMessageRecordService {

    @GetMapping("/messagerecord/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("businessId") String businessId);

    @PostMapping("/messagerecord/insert")
    int insert(@RequestBody MessageRecord record);

    @PostMapping("/messagerecord/insertSelective")
    int insertSelective(@RequestBody MessageRecord record);

    @PostMapping("/messagerecord/batchInsertSelective")
    int batchInsertSelective(@RequestBody Map<String,Object> map);

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

    @PostMapping("/messagerecord/insertSendAll")
    int insert4SendAll(@RequestBody Map<String,Object> map);

}
