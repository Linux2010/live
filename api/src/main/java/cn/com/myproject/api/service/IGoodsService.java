package cn.com.myproject.api.service;

import cn.com.myproject.goods.entity.PO.*;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 李延超 on 2017/9/19.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IGoodsService {

    @GetMapping("/goods/searchGoodsById")
    public Goods searchGoodsById(@RequestParam("goodsId")String goodsId);

    @GetMapping("/spec/selectGoodsSpecsByGoodsId")
    public List<GoodsSpec> selectGoodsSpecsByGoodsId(@RequestParam("goodsId") String goodsId);

    @GetMapping("/spec/selectSpecsByGoodsId")
    public List<Spec> selectSpecsByGoodsId(@RequestParam("goodsId") String goodsId);

    @PostMapping("/goods/selectGoodsMonthlySalesByGoodsId")
    public int selectGoodsMonthlySalesByGoodsId(@RequestBody  GoodsSaleMonth goodsSaleMonth);

    @PostMapping("/goodsComm/addComm")
    boolean addComm(@RequestBody GoodsComment goodsComment);

    @GetMapping("/goods/searcUGcollect")
    GoodsCollect searcUserGoodsCollect(@RequestParam("userId") String userId, @RequestParam("goodsId")String goodsId);

    @PostMapping("/goods/addCollect")
    boolean addCollect(@RequestBody GoodsCollect goodsCollect);

    @PostMapping("/goods/updateCollect")
    boolean updateCollect(@RequestBody GoodsCollect goodsCollect);

    @GetMapping("/goodsComm/SearchGCList")
   public List<GoodsComment> searchGCList(@RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize,
                                              @RequestParam("goodsId") String goodsId);

    @PostMapping("/spec/getGoodsSpecDetail")
    public GoodsSpec getGoodsSpecDetail(@RequestBody GoodsSpec spec);
}