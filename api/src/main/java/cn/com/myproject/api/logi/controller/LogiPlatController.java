package cn.com.myproject.api.logi.controller;

import cn.com.myproject.api.service.ILogiPlatformService;
import cn.com.myproject.logi.entity.PO.LogiPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jyp on 2017/9/18 0018.
 */
@RestController
@RequestMapping("/api/plat")
public class LogiPlatController {

    private static final Logger logger = LoggerFactory.getLogger(LogiPlatController.class);

    @Autowired
    private ILogiPlatformService logiPlatformService;

    @ResponseBody
    @RequestMapping("/selectisOpen")
    public LogiPlatform selectisOpen(){
        return logiPlatformService.selectOpenPlat();
    }
}
