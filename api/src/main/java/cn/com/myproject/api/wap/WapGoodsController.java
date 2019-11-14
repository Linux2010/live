package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.IGoodsCatService;
import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LSG on 2017/9/29 0029.
 */
@RequestMapping("wap/goods")
@Controller
public class WapGoodsController {

    @Autowired
    private IGoodsCatService goodsCatService;


    @RequestMapping("/goodsList")
    public ModelAndView list(String catId){

        ModelAndView view = new ModelAndView("home/goodsList");
        view.addObject("catId",catId);
        return view;
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<GoodsCatVO> goodsDetail(String pageNum, String pageSize, String catId, String sequenc, String keyword){

        if (StringUtils.isBlank(sequenc)){
            sequenc = "0";
        }
        List<GoodsCatVO> list = goodsCatService.selectGoodsByCatId(Integer.valueOf(pageNum),Integer.valueOf(pageSize), catId,Integer.valueOf(sequenc));
        return list;
    }

    @RequestMapping("/detailsByCatId")
    @ResponseBody List<GoodsCatVO> detailsByCatId(String catId){

        List<GoodsCatVO> list = goodsCatService.selectGoodsByCatId(1,10,catId,0);

        return list;
    }

}
