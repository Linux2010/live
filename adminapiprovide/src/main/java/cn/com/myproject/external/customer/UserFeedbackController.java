package cn.com.myproject.external.customer;

import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.UserFeedbackVO;
import cn.com.myproject.customer.service.Impl.UserFeedbackService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.18
 */
@RestController
@RequestMapping("/userfeedback")
public class UserFeedbackController {

    @Autowired
    private UserFeedbackService userFeedbackService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String businessId){
        return userFeedbackService.deleteByPrimaryKey(businessId);
    }

    @PostMapping("/insert")
    int insert(@RequestBody UserFeedback record){
        record.setFeedbackId(UUID.randomUUID().toString().replace("-",""));
        return userFeedbackService.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody UserFeedback record){
        record.setFeedbackId(UUID.randomUUID().toString().replace("-",""));
        return userFeedbackService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    UserFeedback selectByPrimaryKey(String businessId){
        return userFeedbackService.selectByPrimaryKey(businessId);
    }

    @GetMapping("/getPage")
    PageInfo<UserFeedbackVO> getPage(int pageNum, int pageSize,String keyword){return userFeedbackService.getPage(pageNum,pageSize,keyword); }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserFeedback record){
        return userFeedbackService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserFeedback record){
        return userFeedbackService.updateByPrimaryKey(record);
    }


}
