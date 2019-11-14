package cn.com.myproject.service;

import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.Advertise;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IAdvertiseService {

    @PostMapping("/advertise/getPage")
    PageInfo<Advertise> getPage(@RequestBody Map<String, Object> map);

    @PostMapping("/advertise/selectAdverById")
    Advertise selectAdverById(@RequestParam("adverId") String adverId);

    @GetMapping("/advertise/selectAll")
    List<Advertise> selectAll();

    @PostMapping("/advertise/selectAdverType")
    List<Advertise> selectAdverType(@RequestParam("type") Integer type);

    @PostMapping("/advertise/addAdver")
    int addAdver(@RequestBody Advertise advertise);

    @PostMapping("/advertise/updateAdver")
    int updateAdver(@RequestBody Advertise advertise);

    @PostMapping("/advertise/deleteAdver")
    int deleteAdver(@RequestParam("adverId") String adverId);

    @GetMapping("/advertise/selectAllGoods")
    List<Goods> selectAllGoods();
}
