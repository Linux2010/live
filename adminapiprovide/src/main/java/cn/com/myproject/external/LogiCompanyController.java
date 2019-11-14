package cn.com.myproject.external;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.logi.service.ILogiCompanyService;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jyp on 2017/9/13 0013.
 */
@RestController
@RequestMapping("/logi")
public class LogiCompanyController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogiCompanyController.class);

    @Autowired
    private ILogiCompanyService logiCompanyService;

    @PostMapping("/list")
    public PageInfo<LogiCompany> list(int pageNum, int pageSize){
        return  logiCompanyService.getLogiList(pageNum,pageSize);
    }

    @PostMapping("/selectById")
    public LogiCompany selectById(@RequestBody String logiId){
        return logiCompanyService.selectById(logiId);
    }

    @PostMapping("/addlogi")
    public int addlogi(@RequestBody LogiCompany logiCompany){
        int info =0;
        try{
            logiCompanyService.addlogi(logiCompany);
            info = 1;
        }catch (Exception e){
            logger.error("logi-addlogi-添加物流公司:"+e);
        }
        return info;
    }

    @PostMapping("/updatelogi")
    public int updatelogi(@RequestBody LogiCompany logiCompany){
        int info =0;
        try{
            logiCompanyService.updatelogi(logiCompany);
            info = 1;
        }catch (Exception e){
            logger.error("logi-updatelogi-修改物流公司:"+e);
        }
        return info;
    }

    @PostMapping("/dellogi")
    public int dellogi(String logiId){
        int info =0;
        try{
            logiCompanyService.dellogi(logiId);
            info = 1;
        }catch (Exception e){
            logger.error("logi-dellogi-删除物流公司:"+e);
        }
        return info;
    }

    @PostMapping("/logi_list")
    public List<LogiCompany> logi_list(int pageNum, int pageSize){
        return  logiCompanyService.logiList(pageNum,pageSize);
    }

    @PostMapping("/selectComByCode")
    public LogiCompany selectComByCode(String code){
        return  logiCompanyService.selectComByCode(code);
    }
}
