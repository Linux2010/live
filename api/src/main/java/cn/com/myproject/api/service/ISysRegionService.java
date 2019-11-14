package cn.com.myproject.api.service;

import cn.com.myproject.sysuser.entity.PO.SysRegion;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by JYP on 2017/9/1 0001.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface ISysRegionService {

    @GetMapping("/sysregion/select_countries_code")
    public List<SysRegion> select_countries_code();
    @GetMapping("/sysregion/selectByPid")
    public List<SysRegion> selectByPid(@RequestParam("pid") String pid);
}
