package cn.com.myproject.service;

import cn.com.myproject.recset.entity.PO.RecLable;
import cn.com.myproject.recset.entity.PO.RecSettings;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jyp on 2017/8/17 0017.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IRecLableService {

    @GetMapping("/todayrec/getPage")
    PageInfo<RecLable> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
}
