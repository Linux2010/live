package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.ReturnGoods;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by LSG on 2017/9/14 0014.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IReturnGoodsService {

    @GetMapping("/returnGoods/allReturnGoods")
    PageInfo<ReturnGoods> allReturnGoods(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/returnGoods/selectByStatus")
    List<ReturnGoods> selectByStatus(@RequestParam("status") int status);

    @PostMapping("/returnGoods/addReturnGoods")
    int addReturnGoods(@RequestBody ReturnGoods returnGoods);

    @PostMapping("/returnGoods/selectById")
    ReturnGoods selectById(@RequestParam("returnGoodsId") String returnGoodsId);

    @PostMapping("/returnGoods/deleteById")
    int deleteById(@RequestParam("returnGoodsId") String returnGoodsId);

    @PostMapping("/returnGoods/updateReturnGoods")
    int updateReturnGoods(@RequestBody ReturnGoods returnGoods);

    @PostMapping("/returnGoods/getPage")
    PageInfo<ReturnGoods> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("status") int status);
}
