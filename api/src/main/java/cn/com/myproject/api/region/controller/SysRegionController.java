package cn.com.myproject.api.region.controller;

import cn.com.myproject.api.service.ISysRegionService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.sysuser.entity.PO.SysRegion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JYP on 2017/9/1 0001.
 */
@RestController
@RequestMapping("/api/region")
@Api(value="级联类",tags = "级联")
public class SysRegionController {

    @Autowired
    private ISysRegionService sysRegionService;

    @RequestMapping(value="/get_countries_code_list",method = RequestMethod.POST)
    @ApiOperation(value = "获取区号", produces = "application/json")
    public Message<List<SysRegion>> get_countries_code(){
        List<SysRegion> countries_code = sysRegionService.select_countries_code();
        Message message = MessageUtils.getSuccess("success");
        message.setData(countries_code);
        return  message;
    }

    @PostMapping(value="/get_address_list")
    @ApiOperation(value = "获取地址信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pid", value = "pid", required = true, dataType = "String",defaultValue = "0")
    })
    public Message<List<SysRegion>> get_address_list(String pid){
        List<SysRegion> countries = sysRegionService.selectByPid(pid);
        Message message = MessageUtils.getSuccess("success");
        message.setData(countries);
        return message;
    }


}
