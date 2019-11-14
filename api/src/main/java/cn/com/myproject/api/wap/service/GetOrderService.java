package cn.com.myproject.api.wap.service;

import cn.com.myproject.api.service.ICourseOrderService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.paynew.IPayService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import cn.com.myproject.user.entity.PO.UserTeacherRewardOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/11/1.
 */
@Service
public class GetOrderService implements IGetOrderService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    @Autowired
    private ICourseOrderService iCourseOrderService;


    @Override
    public Map<String, String> getOrder(String orderNo) {
        String order_body  = "";
        String order_money  = "";
        String user_id="";

        String firstPlace = orderNo.substring(0,1);
        int reInt=0;
        if("a".equals(firstPlace)){ //课程订单结算订单编号
            order_body = "教头学院-购买课程";
            CourseOrder courseOrder = iCourseOrderService.searchCoMoneyByCno(orderNo);
            if(courseOrder != null){
                if(courseOrder.getTotalMoney() != null){
                    // 购买课程传递的是金额，单位是人民币
                    order_money = String.valueOf(iCourseOrderService.searchCoMoneyByCno(orderNo).getTotalMoney());
                }
            }
            user_id = courseOrder.getUserId();
        }else if("b".equals(firstPlace)){ //打赏教头结算订单编号
            UserTeacherRewardOrder order = new UserTeacherRewardOrder();
            order.setUserTeacherRewardOrderId(orderNo);
            order =  userService.getUserTeacherRewardOrder(order);
            if(order != null){
                order_money = new BigDecimal(String.valueOf(order.getMoney())).divide(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP).toString(); //单位：银两/学分
                order_body="教头学院-用户打赏";
            }

            user_id = order.getUserId();
        }else if("c".equals(firstPlace)){//购买VIP会员订单编号
            RechargeMember rechargeMember = rechargeMemberService.selectByOrderOn(orderNo);//支付金额
            if(rechargeMember != null) {
                order_money =String.valueOf(rechargeMember.getRechargeMoney());
                order_body="教头学院-购买会员";
            }

            user_id = rechargeMember.getUserId();

        }else if("d".equals(firstPlace)){ //充值银两
            UserRechargeWithraw rechargewith = rwService.selectByPrimaryKey(orderNo.substring(1));
            if(rechargewith != null){
                order_money =new BigDecimal(String.valueOf(rechargewith.getMoney())).divide(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                order_body="教头学院-充值积分";
            }

            user_id = rechargewith.getUserId();
        }
        if(StringUtils.isBlank(order_body)) {
            return null;
        }
        Map<String,String> map  = new HashMap<>();
        map.put("order_body",order_body);
        map.put("order_money",order_money);
        map.put("user_id",user_id);
        return map;
    }
}
