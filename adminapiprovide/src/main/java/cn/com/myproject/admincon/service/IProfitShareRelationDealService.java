package cn.com.myproject.admincon.service;

/**
 * @auther CQC （分润处理）
 * @create 2017.9.5
 */
public interface IProfitShareRelationDealService {

    //课程分润
    int dealCourseOrder(String courseOrderId);

    //购买会员分润 订单Id
    int dealBuyMemberType(String rechargeMemberId);

}
