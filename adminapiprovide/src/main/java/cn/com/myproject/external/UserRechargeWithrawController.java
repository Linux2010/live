package cn.com.myproject.external;

import cn.com.myproject.enums.OrderEnum;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import cn.com.myproject.user.service.impl.UserRechargeWithrawService;
import cn.com.myproject.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.31
 */
@RestController
@RequestMapping("/userrechargewithraw")
public class UserRechargeWithrawController {

    @Autowired
    private UserRechargeWithrawService userRechargeWithrawService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String rwId){
        return userRechargeWithrawService.deleteByPrimaryKey(rwId);
    }

    @PostMapping("/insert")
    int insert(@RequestBody UserRechargeWithraw record){
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        record.setRwId(OrderUtil.createOrderNo(""));
        return userRechargeWithrawService.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody UserRechargeWithraw record){
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        record.setRwId(OrderUtil.createOrderNo(""));
        return userRechargeWithrawService.insertSelective(record);
    }

    @PostMapping("/insertSelectiveReturnUid")
    String insertSelectiveReturnUid(@RequestBody UserRechargeWithraw record){
        String bid = OrderUtil.createOrderNo("");
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        record.setRwId(bid);
        int i = userRechargeWithrawService.insertSelective(record);
        String rwId = "";
        if(i == 1){
            rwId = bid;
        }
        return rwId;
    }

    @GetMapping("/selectByPrimaryKey")
    UserRechargeWithraw selectByPrimaryKey(String rwId){
        return userRechargeWithrawService.selectByPrimaryKey(rwId);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserRechargeWithraw record){
        return userRechargeWithrawService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserRechargeWithraw record){
        return userRechargeWithrawService.updateByPrimaryKey(record);
    }

    @GetMapping("/setPayFinishRecharge")
    int setPayFinishRecharge(String rwId,String transactionId){
        return userRechargeWithrawService.setPayFinishRecharge(rwId,transactionId);
    }

}
