package cn.com.myproject.api.service.paynew;


import cn.com.myproject.paynew.entity.VO.PayOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author LeiJia
 * @create 2017.10.27
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IPayService {


    /**
     * 支付宝app支付
     * @param order
     * @return
     */
    @PostMapping("/paynew/app/aliPayOrder")
    public Map<String, String> aliPayOrder(@RequestBody PayOrder order);

    /**
     * 支付宝H5支付
     * @param order
     * @return
     */
    @PostMapping("/paynew/wap/aliWapPayOrder")
    public Map<String, String>  aliWapPayOrder(@RequestBody PayOrder order);

    /**
     * 微信app支付
     * @param order
     * @return
     */
    @PostMapping("/paynew/app/wxPayOrder")
    public Map<String,String> wxPayOrder(@RequestBody PayOrder order);

    /**
     * 微信H5支付
     * @param order
     * @return
     */
    @PostMapping("/paynew/wap/wxWapPayOrder")
    public Map<String,String> wxWapPayOrder(@RequestBody PayOrder order);

    /**
     * 微信公众号支付
     * @param order
     * @return
     */
    @PostMapping("/paynew/jsp/wxJspPayOrder")
    public Map<String,String> wxJspPayOrder(@RequestBody PayOrder order);

    /**
     * 微信公众号支付回调验证
     * @param result
     * @return
     */
    @PostMapping("/paynew/jsp/wxJspCheck")
    public boolean wxJspCheck(@RequestBody  Map<String, String> result );



    /**
     * 微信App支付回调验证
     * @param result
     * @return
     */
    @PostMapping("/paynew/app/wxAppCheck")
    public boolean wxAppCheck(@RequestBody  Map<String, String> result );

}
