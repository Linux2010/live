package cn.com.myproject.api.service.pay;

import cn.com.myproject.pay.entity.VO.PayOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by CQC on 2017.9.18.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IAlipayService {

    @PostMapping("/alipay/alipayorder")
    String payOrder(@RequestBody PayOrder order);
}
