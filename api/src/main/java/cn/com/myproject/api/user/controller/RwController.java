package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.enums.ThirdPaySignEnum;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.8.31
 */
@RestController
@RequestMapping("/api/user")
@Api(value="充值提现",tags = "充值提现")
public class RwController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IUserRechargeWithrawService userRechargeWithrawService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value="/recharge",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "账户充值", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "userId", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "tael", value = "充值银两数", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "way", value = "充值方式  1 支付宝 2 微信 3 银联 4 银两 5 IOS内购", required = true, dataType = "String")
    })
    public Message updateUser(String userId,String tael,String way) {
        try {

            if(StringUtils.isBlank(userId) || StringUtils.isBlank(tael)){
                return MessageUtils.getFail("账户充值失败，参数不能为空");
            }
            UserRechargeWithraw recharge = new UserRechargeWithraw();
            recharge.setUserId(userId);
            recharge.setMoney(new BigDecimal(tael));
            recharge.setWay((short)Integer.valueOf(way).intValue());
            recharge.setOpStatus((short) 0);
            recharge.setAccountType((short) 1);
            recharge.setOperateType((short) 1);
            String rwid = userRechargeWithrawService.insertSelectiveReturnUid(recharge);
            if(StringUtils.isBlank(rwid)){
                return MessageUtils.getFail("账户充值失败，提交失败" );
            }
            Message message = MessageUtils.getSuccess("数据生成成功");
            Map<String,String> map = new HashMap<String,String>();
            map.put("rwId", ThirdPaySignEnum.CHARGE_TAEL.getKey()+rwid);
            message.setData(map); //返回提交订单的id
            return message;
        } catch (Exception e) {
            return MessageUtils.getFail("账户充值失败，提交失败" );
        }
    }

    @RequestMapping(value="/withdraw",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "账户提现", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "userId", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "tael", value = "提现银两数", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "payPwd", value = "支付密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "way", value = "提现方式  1 支付宝 2 微信 3 银联 4 银两 5 IOS内购", required = true, dataType = "String")
    })
    public Message withdraw(String userId,String tael,String payPwd,String way) {
        try {

            if(StringUtils.isBlank(payPwd)){
                return MessageUtils.getFail("提现申请失败，支付密码不能为空");
            }

            if(StringUtils.isBlank(userId)){
                return MessageUtils.getFail("提现申请失败，用户Id不能为空");
            }

            User user = userService.selectById(userId);
            if(null == user){
                return MessageUtils.getFail("提现申请失败，会员不存在");
            }
            if(1 != user.getStatus()){
                return MessageUtils.getFail("提现申请失败，会员状态不符");
            }

            if(StringUtils.isBlank(user.getPayPassword())){
                return MessageUtils.getFail("提现申请失败，支付密码为空，请先到会员中心设置支付密码");
            }

            if(!user.getPayPassword().equals(DigestUtils.md5Hex(payPwd))){
                return MessageUtils.getFail("提现申请失败，支付密码错误");
            }
            if(StringUtils.isBlank(userId) || StringUtils.isBlank(tael)){
                return MessageUtils.getFail("提现申请失败，参数不能为空");
            }
            UserRechargeWithraw recharge = new UserRechargeWithraw();
            recharge.setUserId(userId);
            recharge.setMoney(new BigDecimal(tael));
            recharge.setWay((short)Integer.valueOf(way).intValue());
            recharge.setOpStatus((short) 0);
            recharge.setAccountType((short) 1);
            recharge.setOperateType((short) 2);
            int result = userRechargeWithrawService.insertSelective(recharge);
            Message message = new Message();
            if(0 == result){
                message =  MessageUtils.getFail("提现申请失败");
            }else if(-1 == result){
                message =  MessageUtils.getFail("提现申请失败,账户银两不足");
            }else if(1 == result){
                message = MessageUtils.getSuccess("数据生成成功");
            }
            return message;
        } catch (Exception e) {
            return MessageUtils.getFail("提交失败" );
        }
    }
}
