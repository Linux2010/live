package cn.com.myproject.service;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/9/14 0014.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface ILogiPlatformService {

    @PostMapping("/plat/list")
    PageInfo<LogiPlatform> getPlatList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/plat/selectById")
    LogiPlatform selectById(@RequestParam("platId") String platId);

    @PostMapping("/plat/addplat")
    void addplat(LogiPlatform logiPlatform);

    @PostMapping("/plat/updateplat")
    void updateplat(LogiPlatform logiPlatform);

    @PostMapping("/plat/delplat")
    void delplat(@RequestParam("platId") String platId);
}
