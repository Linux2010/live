package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.goods.entity.PO.GoodsSaleMonth;
import cn.com.myproject.goods.entity.PO.GoodsSku;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.goods.entity.VO.GoodsSpecStockVO;
import cn.com.myproject.goods.service.IGoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-15
 * desc：商品服务控制器类
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/addGoods")
    public String addGoods(@RequestBody Goods goods){
        return goodsService.addGoods(goods);
    }

    @PostMapping("/removeGoods")
    public int removeGoods(String goodsId){
        return goodsService.removeGoods(goodsId);
    }

    @PostMapping("/modifyGoods")
    public int  modifyGoods(@RequestBody Goods goods){
        return goodsService.modifyGoods(goods);
    }

    @PostMapping("/updateGoodsIntro")
    public int  updateGoodsIntro(@RequestBody Goods goods){
          return goodsService.updateGoodsIntro(goods);
    }


    @GetMapping("/searchGoodsList")
    public PageInfo<Goods> searchGoodsList(int pageNum, int pageSize,String keyword){
        return goodsService.searchGoodsList(pageNum,pageSize,keyword);
    }

    @GetMapping("/searchGoodsListByCatId")
    public PageInfo<Goods> searchGoodsListByCatId(int pageNum, int pageSize,String keyword,String catId,String couponId){
        return goodsService.searchGoodsListByCatId(pageNum,pageSize,keyword,catId,couponId);
    }

    @GetMapping("/searchGoodsById")
    public Goods searchGoodsById(String goodsId){
        return goodsService.searchGoodsById(goodsId);
    }

    @PostMapping("/checkGoodsIsExist")
    public int checkGoodsIsExist(@RequestBody Goods goods){
        return goodsService.checkGoodsIsExist(goods);
    }

    @PostMapping("/addGoodsSpecAndStock")
    public int addGoodsSpecAndStock(@RequestBody GoodsSpecStockVO vo){
           return  goodsService.addGoodsSpecAndStock(vo);
    }

    @PostMapping("/updateGoodsSpecAndStock")
    public int updateGoodsSpecAndStock(@RequestBody GoodsSpecStockVO vo){
        return  goodsService.updateGoodsSpecAndStock(vo);
    }

    @PostMapping("/selectGoodsMonthlySalesByGoodsId")
    public int selectGoodsMonthlySalesByGoodsId(@RequestBody GoodsSaleMonth goodsSaleMonth){
        return  goodsService.selectGoodsMonthlySalesByGoodsId(goodsSaleMonth);
    }
    @PostMapping("/searchGoodsSkuListByGoodsId")
    public List<GoodsSku> searchGoodsSkuListByGoodsId(String goodsId){
        return goodsService.searchGoodsSkuListByGoodsId(goodsId);
    }
}