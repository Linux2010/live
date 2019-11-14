package cn.com.myproject.api.service.pay;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CQC on 2017.8.31.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IPayCallBackService {

    @PostMapping("/paycallback/alipay")
    public String alipay(@RequestBody HttpServletRequest request);

    @PostMapping("/paycallback/paypal")
    public String paypal(@RequestBody HttpServletRequest request);
}
