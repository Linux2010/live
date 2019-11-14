package cn.com.myproject.api.service.admincon;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther CQC
 * @create 2017.9.6
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IProfitShareDealService {

    @GetMapping("/profitsharedeal/dealCourseOrder")
    int dealCourseOrder(String courseOrderId);

    @GetMapping("/profitsharedeal/dealBuyMemberType")
    int dealBuyMemberType(String rechargeMemberId);

}
