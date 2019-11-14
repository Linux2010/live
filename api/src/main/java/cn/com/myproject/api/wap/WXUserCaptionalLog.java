package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.user.IUserCapticalLogService;
import cn.com.myproject.api.service.user.IUserCapticalService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import cn.com.myproject.api.user.controller.UserCapticalLogController;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.enums.ThirdPaySignEnum;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyp on 2017/10/23 0023.
 */
@Controller
@RequestMapping("/wap/bal_log")
public class WXUserCaptionalLog extends  BaseController{

    public static Logger logger = LoggerFactory.getLogger(WXUserCaptionalLog.class);

    @Autowired
    private IUserCapticalLogService userCapticalLogService;

    @Autowired
    private IUserRechargeWithrawService userRechargeWithrawService;

    @RequestMapping("/all_list")
    @ResponseBody
    public Message<List<UserCapitalLog>> all_list(String pageNum, String pageSize){
        Message message = MessageUtils.getSuccess("success");
        // 从session中获取userId
        String userId = "";
        User user = getCurrUser();
        if(user != null){
            userId = user.getUserId();
        }
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<UserCapitalLog> list = userCapticalLogService.selectAllList(pageNumVal,pageSizeVal,userId);
        message.setData(list);
        return message;
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ModelAndView tologin() {
        ModelAndView model = new ModelAndView("/my/balanceDetail");
        return model;
    }

    /**
     * 跳转到充值页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/to_recharge")
    public ModelAndView to_recharge(){
        ModelAndView model = new ModelAndView("/my/recharge");
        return model;
    }

    /**
     * 跳转到选择方式页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/to_choose")
    public ModelAndView to_recharge(Double money){
        ModelAndView model = new ModelAndView("/my/rechargeOrder");
        model.addObject("money",money==null?0:money);
        return model;
    }

    /**
     * 支付
     * @param userId
     * @param tael
     * @param way 充值方式  1 支付宝 2 微信 3 银联 4 银两 5 IOS内购
     * @return
     */
    @ResponseBody
    @RequestMapping("/to_pay_page")
    public Message updateUser(String tael,String way) {
        try {
            // 从session中获取userId
            String userId = "";
            User user = getCurrUser();
            if(user != null){
                userId = user.getUserId();
            }
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
}
