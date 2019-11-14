package cn.com.myproject.api.service;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/9/26 0026.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IDeliverMessageService {

    @GetMapping("/deliverMessage/getMessage")
    public DeliverMessageVO getMessage(@RequestParam("orderNo") String orderNo);
}
