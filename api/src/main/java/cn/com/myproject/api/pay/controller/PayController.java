package cn.com.myproject.api.pay.controller;

import cn.com.myproject.api.service.pay.IPayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther CQC
 * @create 2017.8.31
 */
@RestController
@Deprecated
@RequestMapping("/api/pay")
@Api(value="支付",tags = "支付")
public class PayController {

    @Autowired
    private IPayService payService;

    @GetMapping("/payorder")
    public String payorder(PayOrder order){
        return payService.payorder(order);
    }
}
