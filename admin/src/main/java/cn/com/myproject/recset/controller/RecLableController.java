package cn.com.myproject.recset.controller;

import cn.com.myproject.recset.entity.PO.RecLable;
import cn.com.myproject.service.IRecLableService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jyp on 2017/8/17 0017.
 */
@Controller
@RequestMapping("/todayrec")
public class RecLableController {

    public static final Logger logger = LoggerFactory.getLogger(RecLableController.class);

    @Autowired
    private IRecLableService iRecLableService;

    @RequestMapping("/")
    public String index(){
        return "recset/today_rec_home";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<RecLable> getPage(int rows,int page){
        return iRecLableService.getPage(page,rows);
    }
}
