package cn.com.myproject.api.wap.service;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/11/1.
 */
public interface IGetOrderService {
    Map<String,String> getOrder(String orderNo);
}
