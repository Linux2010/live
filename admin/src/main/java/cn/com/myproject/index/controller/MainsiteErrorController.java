package cn.com.myproject.index.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liyang-macbook on 2017/6/22.
 * FIXME 需要优化错误信息展示
 */
//@Controller
public class MainsiteErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "errorpage/error-500";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
