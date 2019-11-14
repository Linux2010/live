package cn.com.myproject.api.service;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by JYP on 2017/9/18 0018.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface ILogiCompanyService {

    @PostMapping("/logi/logi_list")
    public List<LogiCompany> logi_list(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/logi/selectComByCode")
    public LogiCompany selectComByCode(@RequestParam("code") String code);

}
