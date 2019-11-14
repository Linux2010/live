package cn.com.myproject.external;

import cn.com.myproject.enums.OrderEnum;
import cn.com.myproject.enums.ThirdPaySignEnum;
import cn.com.myproject.rechargeMember.service.IRechargeMemberService;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.util.OrderUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LSG on 2017/8/29 0029.
 */
@RestController
@RequestMapping("/rechargeMember")
public class RechargeMemberController {

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @GetMapping("/selectAll")
    public PageInfo<RechargeMember> selectAll(int pageNum, int pageSize){

        return rechargeMemberService.selectAll(pageNum, pageSize);
    }

    @PostMapping("/selectByUserId")
    public RechargeMember selectByUserId(String userId){

        return rechargeMemberService.selectByUserId(userId);
    }

    @PostMapping("/selectByRechargeMemberId")
    public RechargeMember selectByRechargeMemberId(String rechargeMemberId){

        return rechargeMemberService.selectByRechargeMemberId(rechargeMemberId);
    }

    @PostMapping("/addRechargeMember")
    public String addRechargeMember(@RequestBody RechargeMember rechargeMember){
        String result = null;
        try {
            rechargeMember.setVersion(1);
            rechargeMember.setRechargeTime(new Date().getTime());
            rechargeMember.setCreateTime(new Date().getTime());
            String  rechargeMemberId = UUID.randomUUID().toString().replace("-","");
            rechargeMember.setOrderNo(OrderUtil.createOrderNo(OrderEnum.huiyuanchongzhi.getKey()));//订单编号
            rechargeMember.setRechargeMemberId(rechargeMemberId);
            rechargeMember.setStatus((short)1);
            int i = rechargeMemberService.addRechargeMember(rechargeMember);
            if(i>0){
                result = rechargeMember.getOrderNo();
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @PostMapping("/updateRechargeMember")
    public int updateRechargeMember(String rechargeMemberId){

        int result = 0;
        try {
            rechargeMemberService.updateRechargeMember(rechargeMemberId);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/delByRechargeMemberId")
    public int delByRechargeMemberId(String rechargeMemberId){

        int result = 0;
        try {
            rechargeMemberService.delByRechargeMemberId(rechargeMemberId);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectNewByUserId")
    public RechargeMember selectNewByUserId(String userId) {

        return rechargeMemberService.selectNewByUserId(userId);
    }

    @GetMapping("/setPayFinishRechargeMemberByOrderOn")
    int setPayFinishRechargeMemberByOrderOn(String orderNo,String transactionId){
        return rechargeMemberService.setPayFinishRechargeMemberByOrderOn(orderNo,transactionId);
    }

    @PostMapping("/selectByOrderOn")
    public RechargeMember selectByOrderOn(String orderNo){

        return rechargeMemberService.selectByOrderOn(orderNo);
    }

}















