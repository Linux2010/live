package cn.com.myproject.api.service;

import cn.com.myproject.goods.entity.PO.Goods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LSG on 2017/9/19 0019.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IMallService {

    @PostMapping("/advertise/selectGoodsByKeyword")
    List<Goods> searchGoodsList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                                @RequestParam("keyword") String keyword);

    @GetMapping("/advertise/getPageGoods")
    List<Goods> getPageGoods(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

}
