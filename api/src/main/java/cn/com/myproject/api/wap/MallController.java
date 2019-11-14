package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.IGoodsCatService;
import cn.com.myproject.goods.entity.PO.GoodsCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSG on 2017/9/27 0027.
 */
@RequestMapping("wap/mall")
@Controller
public class MallController {

    @Autowired
    private IGoodsCatService goodsCatService;
    @RequestMapping("/index")
    public ModelAndView index(){

        ModelAndView view = new ModelAndView("shop/shop");
        return view;
    }

    @RequestMapping("/goodsType")
    public ModelAndView goodsType(HttpServletRequest request){

        List<GoodsCat> list = goodsCatService.searchGoodsCatByParentId("-1");
        ModelAndView view = new ModelAndView("shop/homeClassify");
        view.addObject("goodsCatList", list);
        return view;
    }

    @RequestMapping("/twoForGoodsCat")
    @ResponseBody
    public List<GoodsCat> catTwo(String parentId){
         return goodsCatService.searchGoodsCatByParentId(parentId);
    }
}
