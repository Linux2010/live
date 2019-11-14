package cn.com.myproject.service;

import cn.com.myproject.live.entity.PO.SysDoc;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案后台Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ISysDocService {

    @GetMapping("/sysDoc/searchSdList")
    PageInfo<SysDoc> searchSdList(@RequestParam("pageNum") int pageNum,
                                  @RequestParam("pageSize") int pageSize,
                                  @RequestParam("docType") int docType);

    @GetMapping("/sysDoc/searchSdcByType")
    String searchSdcByType(@RequestParam("docType") int docType);

    @PostMapping("/sysDoc/modifySdc")
    boolean modifySdc(@RequestBody SysDoc sysDoc);

}