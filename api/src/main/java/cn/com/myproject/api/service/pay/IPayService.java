package cn.com.myproject.api.service.pay;

import cn.com.myproject.pay.entity.VO.PayOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CQC on 2017.8.31.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IPayService {

    @PostMapping("/pay/Ali/goodsorder")
    public String payorder(@RequestBody PayOrder order);

}
