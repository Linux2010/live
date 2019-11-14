package cn.com.myproject.api.goods.controller;

import cn.com.myproject.api.service.ICouponService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * Created by LeiJia on 2017-09-26
 * desc：优惠劵服务控制器类
 */
@RestController
@RequestMapping("/api/coupon")
@Api(value="个人中心-我的优惠劵",tags = "优惠劵")
public class CouponController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("CouponController");

    @Autowired
    private ICouponService couponService;



    @ApiOperation(value = "个人中心-我的优惠劵", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID", required = true, dataType = "String",defaultValue = "e9929a3d3b7c4cd3b14132d0eed7860f")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Coupon.class, responseContainer = "List" ) })

    @PostMapping("/searchUserCoupons")
    public Message<List<Coupon>> searchUserCoupons(String pageNum , String pageSize , String userId){
        if(StringUtils.isEmpty(userId)||StringUtils.isBlank(userId)){
            return MessageUtils.getSuccess("用户ID不能为空");
        }
        try{
            List<Coupon> list = couponService.searchUserCoupons(Integer.valueOf(pageNum),Integer.valueOf(pageSize),userId);
            Message<List<Coupon>> message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("个人中心-我的优惠劵异常"+e.getMessage());
            return MessageUtils.getFail("个人中心-我的优惠劵异常"+e.getMessage());
        }
    }



    @ApiOperation(value = "个人中心-我的优惠劵-优惠劵优惠商品列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前页数", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页记录数", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "couponId", value = "优惠劵ID", required = true, dataType = "String",defaultValue = "1c3fcefbc436452e93e25c8d5fad442e")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Goods.class, responseContainer = "List" ) })

    @PostMapping("/searchCouponGoods")
    public Message<List<Goods>> searchCouponGoods(String pageNum , String pageSize , String couponId){
        if(StringUtils.isEmpty(couponId)||StringUtils.isBlank(couponId)){
            return MessageUtils.getSuccess("优惠劵ID不能为空");
        }
        try{
            List<Goods> list = couponService.searchCouponGoods(Integer.valueOf(pageNum),Integer.valueOf(pageSize),couponId);
            Message<List<Goods>> message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("查询个人中心-我的优惠劵-优惠劵优惠商品列表劵异常"+e.getMessage());
            return MessageUtils.getFail("查询个人中心-我的优惠劵-优惠劵优惠商品列表异常"+e.getMessage());
        }
    }
}