package cn.com.myproject.service.goods;
import cn.com.myproject.goods.entity.PO.*;
import cn.com.myproject.goods.entity.VO.GoodsSpecStockVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/*
 * Created by LeiJia on 2017-09-15
 * desc：商品Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IGoodsService {

    @PostMapping("/goods/addGoods")
    public String addGoods(@RequestBody Goods goods);

    @PostMapping("/goods/removeGoods")
    public int removeGoods(@RequestParam("goodsId") String goodsId);

    @PostMapping("/goods/modifyGoods")
    public int modifyGoods(@RequestBody Goods goods);

    @PostMapping("/goods/updateGoodsIntro")
    public int updateGoodsIntro(@RequestBody Goods goods);

    @GetMapping("/goods/searchGoodsList")
    public PageInfo<Goods> searchGoodsList(@RequestParam("pageNum") int pageNum,
                                           @RequestParam("pageSize") int pageSize,
                                           @RequestParam("keyword") String keyword);

    @GetMapping("/goods/searchGoodsListByCatId")
    public PageInfo<Goods> searchGoodsListByCatId(@RequestParam("pageNum") int pageNum,
                                           @RequestParam("pageSize") int pageSize,
                                           @RequestParam("keyword") String keyword,
                                           @RequestParam("catId") String catId,
                                           @RequestParam("couponId") String couponId);

    @GetMapping("/goods/searchGoodsById")
    public Goods searchGoodsById(@RequestParam("goodsId") String goodsId);

    @PostMapping("/goods/checkGoodsIsExist")
    public int checkGoodsIsExist(@RequestBody Goods goods);

    @PostMapping("/goods/addGoodsSpecAndStock")
    public int addGoodsSpecAndStock(@RequestBody GoodsSpecStockVO vo);


    @PostMapping("/goods/updateGoodsSpecAndStock")
    public int updateGoodsSpecAndStock(@RequestBody GoodsSpecStockVO vo);

    @GetMapping("/spec/selectSpecsByGoodsId")
    public List<Spec> selectSpecsByGoodsId(@RequestParam("goodsId") String goodsId);

    @PostMapping("/spec/getGoodsSpecDetail")
    public GoodsSpec getGoodsSpecDetail(@RequestBody GoodsSpec spec);


    @GetMapping("/spec/selectGoodsSpecsByGoodsId")
    public List<GoodsSpec> selectGoodsSpecsByGoodsId(@RequestParam("goodsId") String goodsId);

    @PostMapping("/goods/searchGoodsSkuListByGoodsId")
    public List<GoodsSku> searchGoodsSkuListByGoodsId(@RequestParam("goodsId")String goodsId);
}