package cn.com.myproject.api.wap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther CQC
 * @create 2017.9.8
 */

@RequestMapping("wap/app")
@Controller
public class ShareController {

    @RequestMapping("/help")
    public ModelAndView help(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("share/help");
        return view;
    }

    @RequestMapping("/share")
    public ModelAndView Share(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("share/share");
        return view;
    }

    @RequestMapping("/download")
    public ModelAndView downLoad(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("share/download");
        return view;
    }
}
