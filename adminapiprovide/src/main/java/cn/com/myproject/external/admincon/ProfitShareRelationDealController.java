package cn.com.myproject.external.admincon;

import cn.com.myproject.admincon.service.IProfitShareRelationDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther CQC
 * @create 2017.9.6
 */
@RestController
@RequestMapping("/profitsharerelationdeal")
public class ProfitShareRelationDealController {

    @Autowired
    private IProfitShareRelationDealService profitShareRelationDealService;

    //课程分润
    @GetMapping("/dealCourseOrder")
    int dealCourseOrder(String courseOrderId){
        return profitShareRelationDealService.dealCourseOrder(courseOrderId);
    }

    //购买会员分润 订单Id
    @GetMapping("/dealBuyMemberType")
    int dealBuyMemberType(String rechargeMemberId){
        return profitShareRelationDealService.dealBuyMemberType(rechargeMemberId);
    }


}
