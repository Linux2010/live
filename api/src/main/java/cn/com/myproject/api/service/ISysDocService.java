package cn.com.myproject.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案api接口Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ISysDocService {

    @GetMapping("/sysDoc/searchSdcByType")
    String searchSdcByType(@RequestParam("docType") int docType);

}