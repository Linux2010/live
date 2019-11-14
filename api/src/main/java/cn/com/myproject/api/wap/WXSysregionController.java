package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.ISysRegionService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.sysuser.entity.PO.SysRegion;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jyp on 2017/9/26 0026.
 */
@Controller
@RequestMapping("/wap/region")
public class WXSysregionController {

    @Autowired
    private ISysRegionService sysRegionService;


    @RequestMapping(value="/get_address_list")
    @ResponseBody
    public Message<List<SysRegion>> get_address_list(String pid){
        List<SysRegion> countries = sysRegionService.selectByPid(pid);
        Message message = MessageUtils.getSuccess("success");
        message.setData(countries);
        return message;
    }
}
