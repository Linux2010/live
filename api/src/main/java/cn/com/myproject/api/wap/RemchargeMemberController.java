package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LSG on 2017/9/5 0005.
 */
@RequestMapping("wap/remchargeMember")
@Controller
public class RemchargeMemberController {

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    /**
     * 跳转到支付方式页面
     * @param type  选择的充值类型：1、月卡 2、年卡 3、永久卡
     * @return
     */
    @RequestMapping("/vip/vipOrder")
    public ModelAndView payOrder(String type) {
        ModelAndView view = new ModelAndView("/vipOrder");
        view.addObject("type",type);
        ConfigSetting configSetting =getConfigSetting(type);
        view.addObject("config_value",configSetting.getConfig_value());
        return view;
    }
    public ConfigSetting getConfigSetting(String vip){
        ConfigSetting configSetting;
        if (Integer.valueOf(vip) == 1){
            configSetting = rechargeMemberService.selectConfigSettingBySign("month");
        }else if (Integer.valueOf(vip) == 2){
            configSetting = rechargeMemberService.selectConfigSettingBySign("year");
        }else {
            configSetting = rechargeMemberService.selectConfigSettingBySign("forever");
        }
        return configSetting;
    }

    @RequestMapping("/vip")
    @ResponseBody
    public ConfigSetting selectBySign(String vip){

        ConfigSetting configSetting = getConfigSetting(vip);
        return configSetting;
    }

    @RequestMapping(value = "/member")
    public ModelAndView memberRights(){

        ModelAndView view = new ModelAndView("wap/member_rights");
        return view;
    }
}
