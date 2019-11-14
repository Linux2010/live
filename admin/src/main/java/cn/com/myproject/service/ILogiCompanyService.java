package cn.com.myproject.service;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jyp on 2017/9/14 0014.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ILogiCompanyService {

    @PostMapping("/logi/list")
    PageInfo<LogiCompany> getLogiList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    @PostMapping("/logi/selectById")
    LogiCompany selectById(@RequestParam("logiId") String logiId);
    @PostMapping("/logi/addlogi")
    void addlogi(@RequestBody LogiCompany logiCompany);
    @PostMapping("/logi/updatelogi")
    void updatelogi(@RequestBody LogiCompany logiCompany);
    @PostMapping("/logi/dellogi")
    void dellogi(@RequestParam("logiId") String logiId);
}
