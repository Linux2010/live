package cn.com.myproject.api.pay.controller;

import cn.com.myproject.api.service.ICourseOrderService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.pay.IAlipayService;
import cn.com.myproject.api.service.pay.IWxpayService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import cn.com.myproject.user.entity.PO.UserTeacherRewardOrder;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.9.18
 */
@RestController
@Deprecated
@RequestMapping(value = "/api/pay_oldnouse")
@Api(value="第三方支付",tags = "第三方支付")
public class AliPayController {

    private static final Logger logger = LoggerFactory.getLogger(AliPayController.class);

    @Autowired
    private IAlipayService alipayService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    @Autowired
    private IWxpayService wxpayService;

    @Autowired
    private ICourseOrderService iCourseOrderService;

    @PostMapping("/orderPay")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "orderId", value = "订单ID", required = true, dataType = "String",defaultValue = "8d4ea1821c6541e9b51c8d74d5a2bfdd"),
            @ApiImplicitParam(paramType="query",name = "payType", value = "支付方式(1:支付宝；2：微信；)", required = true, dataType = "String",defaultValue = "1")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "List" ) })
    private Message orderPay(String orderId, short payType, HttpServletRequest request){
        Message message = new Message();
        try{

            if(StringUtils.isNotBlank(orderId)){

                BigDecimal money = BigDecimal.ZERO;
                BigDecimal tael = BigDecimal.ZERO;
                String subject = "";
                String createOrderStr = "";

                String firstPlace = orderId.substring(0,1);
                int reInt=0;
                if("a".equals(firstPlace)){ //购买课程订单
                    subject = "购买课程";
                    String moneyStr = "0";
                    if(iCourseOrderService.searchCoMoneyByCno(orderId) != null){
                        if(iCourseOrderService.searchCoMoneyByCno(orderId).getTotalMoney() != null){
                            // 购买课程传递的是金额，单位是人民币
                            moneyStr = String.valueOf(iCourseOrderService.searchCoMoneyByCno(orderId).getTotalMoney());
                        }
                    }
                    money = new BigDecimal(moneyStr);
                }else if("b".equals(firstPlace)){ //打赏教头订单
                    subject = "打赏教头";
                    UserTeacherRewardOrder rewardOrder= userService.getUserTeacherRewardOrder(new UserTeacherRewardOrder(orderId));
                    if(rewardOrder != null){
                     money = new BigDecimal(String.valueOf(rewardOrder.getMoney())).divide(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP); //单位：银两/学分
                    }
                }else if("c".equals(firstPlace)){//购买VIP会员订单
                    subject = "购买VIP";
                    RechargeMember rechargeMember = rechargeMemberService.selectByOrderOn(orderId);
                    money = new BigDecimal(String.valueOf(rechargeMember.getRechargeMoney())).setScale(2,BigDecimal.ROUND_HALF_UP);
                }else if("d".equals(firstPlace)){ //充值银两订单
                    subject = "充值银两";
                    UserRechargeWithraw record = rwService.selectByPrimaryKey(orderId.substring(1));
                    if(null != record){
                        if(1 == record.getAccountType()){//银两账户
                            tael = record.getMoney();
                            money = tael.divide(new BigDecimal("10")).setScale(2,BigDecimal.ROUND_HALF_UP);
                        }
                    }
                }
                Map<String,String> result = new HashMap<>();
                if(1 == payType){ //支付宝支付
                    PayOrder payOrder = new PayOrder();
                    payOrder.setOut_trade_no(orderId);
                    payOrder.setTotal_amount(String.valueOf(money));
                    payOrder.setTimeout_express("24h");
                    payOrder.setBody(subject+orderId);
                    payOrder.setGoods_type("1");
                    payOrder.setSubject(subject);
                    createOrderStr = alipayService.payOrder(payOrder);
                    result.put("createOrder",createOrderStr);
                }
                if(2 == payType){
                    PayOrder payOrder = new PayOrder();
                    payOrder.setOut_trade_no(orderId);
                    payOrder.setTotal_amount(String.valueOf(money));
                    payOrder.setTimeout_express("24h");
                    payOrder.setBody(subject+orderId);
                    payOrder.setSubject(subject);
                    payOrder.setSpbill_create_ip(request.getRemoteAddr());
                    result= wxpayService.appPayOrder(payOrder);
                }
                message = MessageUtils.getSuccess("success");
                message.setData(result);
            }else{
                message = MessageUtils.getSuccess("参数缺失");
            }

        }catch(Exception e){
            if(1== payType){

                logger.error("[{}]时，发生异常，异常信息为:[{}]","app支付宝支付异常",e.getMessage());
            }else if( 2== payType){
                logger.error("[{}]时，发生异常，异常信息为:[{}]","微信app支付异常",e.getMessage());
            }
            message = MessageUtils.getSuccess("支付失败");
        }
        return message;
    }

}
