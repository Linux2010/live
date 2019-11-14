package cn.com.myproject.logi.controller;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import cn.com.myproject.service.ILogiPlatformService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyp on 2017/9/14 0014.
 */
@Controller
@RequestMapping("/plat")
public class LogiPlatformController {

    private static final Logger logger = LoggerFactory.getLogger(LogiPlatformController.class);

    @Autowired
    private ILogiPlatformService logiPlatformService;

    @RequestMapping("/index")
    public String index(){
        return "logi/platfrom_list_index";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<LogiPlatform> list(int rows,int page){
        return logiPlatformService.getPlatList(page,rows);
    }

    @RequestMapping("/addplat")
    @ResponseBody
    public Map<String,Object> addplat(@RequestParam("name") String name,@RequestParam("code") String code,@RequestParam("status") String status,@RequestParam("type") String type,@RequestParam("key") String key,@RequestParam("customer") String customer){
        Map<String,Object> data = new HashMap<>();
        try{
            String param ="{\'keyid\':\'"+key+"\',\'user\':\'"+type+"\',\'code\':\'"+customer+"\'}";
            LogiPlatform logiPlatform = new LogiPlatform();
            logiPlatform.setIsOpen(Integer.valueOf(status));
            logiPlatform.setPlatformName(name);
            logiPlatform.setCode(code);
            logiPlatform.setConfig(param);
            logiPlatformService.addplat(logiPlatform);
            data.put("status","success");
            data.put("message","success");
        }catch (Exception e){
            logger.error("admin-LogiPlatformController-addplat-添加物流平台失败:"+e);
            data.put("status","fail");
            data.put("message","fail");
        }
        return data;
    }

    @RequestMapping("/updateplat")
    @ResponseBody
    public Map<String,Object> updateplat(LogiPlatform logiPlatform){
        Map<String,Object> data = new HashMap<>();
        try{
            logiPlatformService.updateplat(logiPlatform);
            data.put("status","success");
            data.put("message","success");
        }catch (Exception e){
            logger.error("admin-LogiPlatformController-updateplat-修改物流平台失败:"+e);
            data.put("status","fail");
            data.put("message","fail");
        }
        return data;
    }

    @RequestMapping("/delplat")
    @ResponseBody
    public Map<String,Object> delplat(@RequestParam("platId") String platId){
        Map<String,Object> data = new HashMap<>();
        try{
            logiPlatformService.delplat(platId);
            data.put("status","success");
            data.put("message","success");
        }catch (Exception e){
            logger.error("admin-LogiPlatformController-delplat-删除物流平台失败:"+e);
            data.put("status","fail");
            data.put("message","fail");
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/selectById")
    public LogiPlatform selectById(@RequestParam("platId") String platId){
       return logiPlatformService.selectById(platId);

    }

    @RequestMapping("/update_plat_status")
    @ResponseBody
    public Map<String,Object> update_plat_status (@RequestParam("platId") String platId,@RequestParam("status") String status){
        Map<String,Object> data = new HashMap<>();
        LogiPlatform logiPlatform = new LogiPlatform();
        logiPlatform = logiPlatformService.selectById(platId);
        if(null == logiPlatform){
            data.put("status","fail");
            data.put("message","此条信息不存在");
        }
        try{
            logiPlatform.setPlatId(platId);
            logiPlatform.setCode(logiPlatform.getCode());
            logiPlatform.setConfig(logiPlatform.getConfig());
            logiPlatform.setPlatformName(logiPlatform.getPlatformName());
            logiPlatform.setIsOpen(Integer.valueOf(status));
            logiPlatformService.updateplat(logiPlatform);
            data.put("status","success");
            data.put("message","success");
        }catch (Exception e){
            logger.error("admin-LogiPlatformController-updateplat-修改物流平台失败:"+e);
            data.put("status","fail");
            data.put("message","fail");
        }
        return data;
    }


}
