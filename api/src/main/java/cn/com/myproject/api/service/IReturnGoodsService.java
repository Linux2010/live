package cn.com.myproject.api.service;

import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.ReturnGoods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LSG on 2017/9/15 0015.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IReturnGoodsService {

    @PostMapping("/returnGoods/selectByUserId")
    List<ReturnGoods> selectByUserId(@RequestParam("userId") String userId);

    @PostMapping("/returnGoods/addReturnGoods")
    int addReturnGoods(@RequestBody ReturnGoods returnGoods);

    @PostMapping("/returnGoods/selectByLogisticsNumber")
    ReturnGoods selectByLogisticsNumber(@RequestParam("logisticsNumber") String logisticsNumber);

    @GetMapping("/goods/searchGoodsById")
    Goods selectByGoodsId(@RequestParam("goodsId") String goodsId);

    @PostMapping("returnGoods/selectById")
    ReturnGoods selectById(@RequestParam("returnGoodsId") String returnGoodsId);
}
