package cn.com.myproject.api.service.pay;

import cn.com.myproject.pay.entity.VO.PayOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by LeiJia on 2017.9.21.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IWxpayService {

    @PostMapping("/wxAppPay/appPayOrder")
    Map<String,String> appPayOrder(@RequestBody PayOrder order);
}
