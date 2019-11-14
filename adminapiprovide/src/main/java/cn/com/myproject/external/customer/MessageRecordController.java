package cn.com.myproject.external.customer;

import cn.com.myproject.customer.entity.PO.MessageRecord;

import cn.com.myproject.customer.entity.VO.MessageRecordVO;
import cn.com.myproject.customer.service.Impl.MessageRecordService;

import cn.com.myproject.sms.ISendSmsService;
import com.github.pagehelper.PageInfo;
import com.sun.imageio.plugins.common.I18N;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.18
 */
@RestController
@RequestMapping("/messagerecord")
public class MessageRecordController {

    @Autowired
    private MessageRecordService service;

    @Autowired
    private ISendSmsService smsService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String businessId){
        return service.deleteByPrimaryKey(businessId);
    }

    @PostMapping("/insert")
    int insert(@RequestBody MessageRecord record){
        record.setMessageId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        return service.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody MessageRecord record){
        record.setMessageId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        return service.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    MessageRecord selectByPrimaryKey(String businessId){
        return service.selectByPrimaryKey(businessId);
    }

    @GetMapping("/getPage")
    PageInfo<MessageRecordVO> getPage(int pageNum, int pageSize,String keyword){return service.getPage(pageNum,pageSize,keyword); }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody MessageRecord record){
        return service.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody MessageRecord record){
        return service.updateByPrimaryKey(record);
    }

    @GetMapping("/getListByRelationId")
    public List<MessageRecord> getListByRelationId(String relationId) {
        return service.getListByRelationId(relationId);
    }

    @PostMapping("/batchInsertSelective")
    public int batchInsertSelective(@RequestBody Map<String,Object> map){
       return service.batchInsertSelective(map);
    }

    @PostMapping("/getPageByGP")
    PageInfo<MessageRecordVO> getPageByGP(@RequestBody Map<String,Object> map){
        return service.getPageByGP(map);
    }

    @PostMapping("/getPageByMap")
    PageInfo<MessageRecordVO> getPageByMap(@RequestBody Map<String,Object> map){
        return service.getPageByMap(map);
    }

    @PostMapping("/insertSendAll")
    int insert4SendAll(@RequestBody Map<String,Object> map){
        return service.insert4SendAll(Integer.valueOf(String.valueOf(map.get("pageNum"))).intValue(),Integer.valueOf(String.valueOf(map.get("pageSize"))).intValue(),map);
    }

    @PostMapping("/getMessageCount")
    Integer getMessageCount(@RequestBody MessageRecord messageRecord){
        return service.getMessageCount(messageRecord);
    }


    @PostMapping("/updateByTCU")
    int updateByTCU(@RequestBody Map<String,Object> map){
        return service.updateByTCU(map);
    }


    @PostMapping("/wxbecomelowerlevelnotice")
    List<MessageRecord> wxbecomelowerlevelnotice(String receiveUserId){
        return service.wxbecomelowerlevelnotice(receiveUserId);
    }

}
