package cn.com.myproject.logi.controller;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.service.ILogiCompanyService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jyp on 2017/9/14 0014.
 */
@RequestMapping("/logi")
@Controller
public class LogiCompanyController {

    private static final Logger logger = LoggerFactory.getLogger(LogiCompanyController.class);

    @Autowired
    private ILogiCompanyService logiCompanyService;

    @RequestMapping("/index")
    public String index(){
        return "logi/logi_company_index";
    }

    @RequestMapping("/list")
    @ResponseBody
    private PageInfo<LogiCompany> list(int rows, int page){
        return logiCompanyService.getLogiList(page,rows);
    }

    @RequestMapping("/addlogi")
    @ResponseBody
    public Map<String,Object> addlogi(LogiCompany logiCompany){
        Map<String,Object> data = new HashMap<>();
        try{
            logiCompany.setLogiId(UUID.randomUUID().toString().replace("-",""));
            logiCompany.setCreateTime(new Date().getTime());
            logiCompany.setVersion(1);
            logiCompany.setStatus((short)1);
            logiCompanyService.addlogi(logiCompany);
            data.put("message","success");
            data.put("status","success");
        }catch (Exception e){
            logger.error("LogiCompanyController-addlogi-添加物流公司失败:"+e);
            data.put("message","fail");
            data.put("status","fail");
        }
        return data;
    }

    @RequestMapping("/updatelogi")
    @ResponseBody
    public Map<String,Object> updatelogi(LogiCompany logiCompany){
        Map<String,Object> data = new HashMap<>();
        try{
            logiCompanyService.updatelogi(logiCompany);
            data.put("message","success");
            data.put("status","success");
        }catch (Exception e){
            logger.error("LogiCompanyController-updatelogi-修改物流公司失败:"+e);
            data.put("message","fail");
            data.put("status","fail");
        }
        return data;
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public LogiCompany selectById(@RequestParam("logiId") String logiId){

          return logiCompanyService.selectById(logiId);

    }

    @RequestMapping("/dellogi")
    @ResponseBody
    public int dellogi(@RequestParam("logiId") String logiId){
        int info =0;
        try{
            logiCompanyService.dellogi(logiId);
            info = 1;
        }catch (Exception e){
            logger.error("LogiCompanyController-dellogi-删除物流公司:"+e);
        }
        return info;
    }
}
