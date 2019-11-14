package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.user.IUserCapticalLogService;
import cn.com.myproject.api.service.user.IUserCapticalService;
import cn.com.myproject.api.util.DateUtils;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import cn.com.myproject.user.entity.PO.RechargeMember;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号
 * 会员充值类 会员充值
 * Created by LSG on 2017/8/29 0029.
 */

@RestController
@RequestMapping("/wap/rechargeMember")
@Api(value="会员充值类",tags = "会员充值")
public class WXRechargeMemberController extends BaseController {

    @Autowired
    private IRechargeMemberService rechargeMemberService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserCapticalService userCapticalService;
    @Autowired
    private IUserCapticalLogService userCapticalLogService;

    /**
     * 会员充值相关信息
     * @param type  选择的充值类型：1、月卡 2、年卡 3、永久卡
     * @param payType 支付方式：1、银两支付 2、支付宝 3、微信支付 4、AndroidPay 5、paypal
     * @param payPwd 支付密码
     * @return
     */
    @PostMapping(value = "/rechargeMember")
    @ResponseBody
    public Message rechargeMember(String type, String payType, String payPwd){

        Message message = new Message();
        User currUser  = getCurrUser();
        String userId = "";
        if(currUser != null){
            userId = currUser.getUserId();
        }

        if (StringUtils.isBlank(userId)){
            return MessageUtils.getSuccess("用户id不能为空！");
        }
        User user = userService.selectById(userId);
        if (null == user){
            return MessageUtils.getSuccess("没有该用户！");
        }
        if (StringUtils.isBlank(type) || Integer.valueOf(type) <= 0){
            return MessageUtils.getSuccess("充值类型不能为空！");
        }
        if (StringUtils.isBlank(payType)){
            return MessageUtils.getSuccess("支付方式不能为空！");
        }
        if (user.getExpirationDate() == -1){
            return MessageUtils.getSuccess("您已经是永久会员了！");
        }

        String description = "";//描述
        BigDecimal monthMoney = BigDecimal.ZERO;
        BigDecimal yearMoney = BigDecimal.ZERO;
        BigDecimal foreverMoney = BigDecimal.ZERO;
        String remarkMonth = "";
        String remarkYear = "";
        String remarkForever = "";

       ConfigSetting configSetting4Month = rechargeMemberService.selectConfigSettingBySign("month");
       if(null != configSetting4Month){
           monthMoney = new BigDecimal(String.valueOf(configSetting4Month.getConfig_value()));
           remarkMonth = configSetting4Month.getRemark();
       }

       ConfigSetting configSetting4Yera = rechargeMemberService.selectConfigSettingBySign("year");
       if(null != configSetting4Yera){
           yearMoney = new BigDecimal(String.valueOf(configSetting4Yera.getConfig_value()));
           remarkMonth = configSetting4Yera.getRemark();
       }
       ConfigSetting configSetting4Forever = rechargeMemberService.selectConfigSettingBySign("forever");
       if(null != configSetting4Forever){
           foreverMoney = new BigDecimal(String.valueOf(configSetting4Forever.getConfig_value()));
           remarkForever = configSetting4Forever.getRemark();
       }

        try {

            long expirationTime = 0;//初始化充值时间
            BigDecimal expirationMoney = BigDecimal.ZERO;//初始化所需金额

            long timeoutTime = user.getExpirationDate();

            int rechargeType =  Integer.valueOf(type); // 充值类型 1、月卡2、年卡3、永久

            // 超时时间
            if(1 == user.getUserIdentity()){ // 普通用户
                timeoutTime = new Date().getTime();
            }

            if (rechargeType == 1){//选择月卡
                expirationTime = DateUtils.addDay(timeoutTime,31);
                expirationMoney = monthMoney;
                description = remarkMonth;
            } else if (rechargeType == 2){//选择年卡
                expirationTime = DateUtils.addDay(timeoutTime,372);
                expirationMoney = yearMoney;
                description = remarkYear;
            } else if (rechargeType == 3){//选择永久卡
                expirationTime = -1;
                expirationMoney = foreverMoney;
                description = remarkForever;
            }

            //支付方式
            int payTypeInt = Integer.valueOf(payType);

            BigDecimal expiraMoney = expirationMoney;//充值会员所需要的钱

            RechargeMember rechargeMember = new RechargeMember();

            if(1 == payTypeInt){ // 银两
                UserCapital userCapital = userCapticalService.selectByUserId(userId);
//                if (StringUtils.isBlank(payPwd)){
//                    return MessageUtils.getSuccess("支付密码不能为空！");
//                }
//                if(StringUtils.isBlank(user.getPayPassword())){
//                    return MessageUtils.getSuccess("支付密码为空，请到会员中心进行设置！");
//                }

                BigDecimal userMoney = userCapital.getTael();//获取用户的余额
//                if (!DigestUtils.md5Hex(payPwd).equals(user.getPayPassword())){
//                    return MessageUtils.getFail("充值失败，支付密码不正确！");
//                }
                if (userMoney.compareTo(expiraMoney) == -1){//等于-1表示小于，用户余额不够
                    return MessageUtils.getFail("对不起，余额不足！");
                }
            }else{  // 第三方支付
                rechargeMember.setPayStatus(2);
            }
            //生成订单
            rechargeMember.setUserId(userId);
            rechargeMember.setRechargeMoney(expirationMoney.doubleValue());
            rechargeMember.setRechargeType(Integer.valueOf(type));
            rechargeMember.setExpirationDate(expirationTime);
            rechargeMember.setPayType(payTypeInt);
            String addResult = rechargeMemberService.addRechargeMember(rechargeMember);

            if (StringUtils.isNotBlank(addResult)){
                message = MessageUtils.getSuccess("success");
                Map<String,String> addResultMap = new HashMap<>();
                addResultMap.put("rechargeId",addResult);
                message.setData(addResultMap);
            }else {
                return MessageUtils.getSuccess("充值失败！");
            }
        }catch (RuntimeException e){
            return MessageUtils.getFail("充值失败！"+e.getMessage());
        }
        return message;
    }

    @PostMapping(value = "/configSetting")
    @ApiOperation(value = "系统参数相关信息", produces = "application/json")
    public Message<List<ConfigSetting>> configSettingList(){

        try {
            List<ConfigSetting> list = rechargeMemberService.selectConfigSettings();
            Message message = MessageUtils.getSuccess("请求成功！");
            message.setData(list);
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("请求失败！"+e.getMessage());
        }
    }
}































