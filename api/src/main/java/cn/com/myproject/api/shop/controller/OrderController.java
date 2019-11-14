package cn.com.myproject.api.shop.controller;

import cn.com.myproject.api.service.ICartService;
import cn.com.myproject.api.service.IOrderService;
import cn.com.myproject.api.service.ISearchService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.live.entity.VO.JsVo;
import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.PO.Order;
import cn.com.myproject.shop.entity.PO.OrderDetail;
import cn.com.myproject.user.entity.PO.UserCapital;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-19
 * desc：订单控制器类
 */
@RestController
@RequestMapping(value = "/api/order")
@Api(value="订单",tags = "商城订单")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private ISearchService iSearchService;

    @Autowired
    private ICartService iCartService;

    /**
     * 根据用户ID查询优惠券列表
     *
     * @param userId
     * @return
     */
    @PostMapping("/searchCouponList")
    @ApiOperation(value = "根据用户ID查询优惠券列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "useFlag",value = "优惠券类型：1代表可用，2代表不可用",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public Message<List<Coupon>> searchCouponList(String userId,String useFlag){
        int useFlagVal = 0;
        if(StringUtils.isNotBlank(useFlag)){
            useFlagVal = Integer.parseInt(useFlag);
        }
        List<Coupon> cList = iOrderService.searchCouponList(userId,useFlagVal);
        Message<List<Coupon>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 生成订单
     *
     * @param userId
     * @param addressId
     * @param couponId
     * @param orderType
     * @param odList
     * @return
     */
    @PostMapping("/addOrder")
    @ApiOperation(value = "生成订单", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "addressId",value = "收货地址ID",
                    required = true, dataType = "String",defaultValue = "69ea3ec4cdd64c2fb7bd58fcb74cab9d"),
            @ApiImplicitParam(paramType="query",name = "couponId",value = "优惠券ID",
                    required = false, dataType = "String",defaultValue = "e57233673efb4fa3a975207b52428e4a"),
            @ApiImplicitParam(paramType="query",name = "orderType",
                    value = "订单类型：1是金额商品订单，2是银两商品订单",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "odList",
                    value = "商品详情列表以[{goodsId:'9661001e7f634db999225e6f4c831b90',\n" +
                            "goodsSpecId:'7c05d91cb69645acb5db83a21295cb52'}]的形式传递",
                    required = true, dataType = "String",defaultValue = "")
    })
    public Message<JsVo> addOrder(String userId,String addressId,String couponId,String orderType,String odList){

        // 获取用户级别，1是普通用户，2是会员用户
        int userLevel = 0;
        if(StringUtils.isNotBlank(userId)){
            String userLevelStr = iOrderService.searchUserLevel(userId);
            if(StringUtils.isNotBlank(userLevelStr)){
                userLevel = Integer.parseInt(userLevelStr);
            }
        }

        // 获取订单类型，1是金额商品订单，2是银两商品订单
        int orderTypeVal = 0;
        if(orderType != null && !"".equals(orderType)){
            orderTypeVal = Integer.parseInt(orderType);
        }

        // 获取订单金额
        double orderMoney = 0;
        if(orderTypeVal == 1){// 订单类型：1是金额商品订单，2是银两商品订单
            String orderMoneyStr = iCartService.searchCgTmByUserId(userId);
            if(StringUtils.isNotBlank(orderMoneyStr)){
                orderMoney = Double.parseDouble(orderMoneyStr);
            }
        }

        // 获取订单银两
        int orderYl = 0;
        if(orderTypeVal == 2){// 订单类型：1是金额商品订单，2是银两商品订单
            if(StringUtils.isNotBlank(odList)){
                // 获取银两商品信息
                JsonElement jsonElement = new JsonParser().parse(odList);
                JsonArray array = jsonElement.getAsJsonArray();
                Iterator<JsonElement> it = array.iterator();
                GoodsSpec gsInfo = null;
                while(it.hasNext()){
                    JsonElement je = it.next();
                    String goodsId = je.getAsJsonObject().get("goodsId").getAsString();
                    String goodsSpecId = je.getAsJsonObject().get("goodsSpecId").getAsString();
                    gsInfo = iOrderService.searchGoodsSpecInfo(goodsId,goodsSpecId);
                    if(gsInfo != null){
                        if(userLevel == 1){// 1代表普通用户，则获取商品市场价格
                            orderYl += Integer.parseInt(String.valueOf(gsInfo.getUserPrice()));// 银两是整数
                        }
                        if(userLevel == 2){// 2代表会员用户，则获取会员价格
                            orderYl += Integer.parseInt(String.valueOf(gsInfo.getVipPrice()));// 银两是整数
                        }
                    }
                }
            }

            // 验证用户账户余额是否充足
            if(orderYl > 0){
                UserCapital userCapital = iSearchService.selectUserCapitalByUserId(userId);
                if(userCapital == null || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(0))<=0)
                        || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(String.valueOf(orderYl*10)))<=0)){
                    Message<JsVo> message = MessageUtils.getFail("余额不足");
                    JsVo jv = new JsVo();
                    jv.setCourseOrderNo("");
                    jv.setResultVal(0);
                    message.setData(jv);
                    message.setResult(1);
                    return message;
                }
            }
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setAddressId(addressId);
        order.setCouponId(couponId);
        order.setOrderType(orderTypeVal);

        // 存储购物车商品数据ID，用以下单后删除购物车中已下单商品数据
        List<String> cartIdList = null;

        // 如果是金额商品订单，则遍历金额商品详情
        if(orderTypeVal == 1){
            order.setOrderMoney(orderMoney);
            JsonElement jsonElement = new JsonParser().parse(odList);
            JsonArray array = jsonElement.getAsJsonArray();
            Iterator<JsonElement> it = array.iterator();
            List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
            OrderDetail orderDetail = null;
            Cart cart = null;
            cartIdList = new ArrayList<String>();
            // 抽取订单详情信息
            while(it.hasNext()){
                JsonElement je = it.next();
                String goodsId = je.getAsJsonObject().get("goodsId").getAsString();
                String goodsSpecId = je.getAsJsonObject().get("goodsSpecId").getAsString();
                cart = iCartService.searchCgInfoByUGSId(userId,goodsId,goodsSpecId);
                if(cart != null){
                    cartIdList.add(cart.getCartId());
                    orderDetail = new OrderDetail();
                    orderDetail.setGoodsType(orderTypeVal);// 商品类型：1是金额商品，2是银两商品
                    orderDetail.setGoodsMoney(cart.getGoodsMoney());
                    orderDetail.setGoodsId(cart.getGoodsId());
                    orderDetail.setGoodsNum(cart.getGoodsNum());
                    orderDetail.setGoodsSpecId(cart.getGoodsSpecId());
                    orderDetail.setGoodsSpecVal(cart.getGoodsSpecVal());
                    orderDetail.setUserId(userId);
                    orderDetailList.add(orderDetail);
                }
            }
            order.setOrderDetailList(orderDetailList);
        }

        // 如果是银两商品订单，则遍历银两商品详情
        if(orderTypeVal == 2){
            order.setOrderMoney(orderYl);
            // 获取银两商品信息
            JsonElement jsonElement = new JsonParser().parse(odList);
            JsonArray array = jsonElement.getAsJsonArray();
            Iterator<JsonElement> it = array.iterator();
            List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
            OrderDetail orderDetail = null;
            GoodsSpec gsInfo = null;
            while(it.hasNext()){
                JsonElement je = it.next();
                String goodsId = je.getAsJsonObject().get("goodsId").getAsString();
                String goodsSpecId = je.getAsJsonObject().get("goodsSpecId").getAsString();
                gsInfo = iOrderService.searchGoodsSpecInfo(goodsId,goodsSpecId);
                if(gsInfo != null){
                    orderDetail = new OrderDetail();
                    if(userLevel == 1){// 1代表普通用户，则获取商品市场价格
                        orderYl += Integer.parseInt(String.valueOf(gsInfo.getUserPrice()));// 银两是整数
                    }
                    if(userLevel == 2){// 2代表会员用户，则获取会员价格
                        orderYl += Integer.parseInt(String.valueOf(gsInfo.getVipPrice()));// 银两是整数
                    }
                    orderDetail.setGoodsType(orderTypeVal);// 商品类型：1是金额商品，2是银两商品
                    orderDetail.setGoodsMoney(orderYl);
                    orderDetail.setGoodsId(goodsId);
                    orderDetail.setGoodsNum(1);// 银两商品订单默认每次只提交一件商品形成订单
                    orderDetail.setGoodsSpecId(goodsSpecId);
                    StringBuffer goodsSpecVal = new StringBuffer();
                    if(StringUtils.isNotBlank(gsInfo.getSpecName()) && StringUtils.isNotBlank(gsInfo.getValuesName())){
                        goodsSpecVal.append(gsInfo.getSpecName()+":"+gsInfo.getValuesName()+";");
                    }
                    if(StringUtils.isNotBlank(gsInfo.getSpecName1()) && StringUtils.isNotBlank(gsInfo.getValuesName1())){
                        goodsSpecVal.append(gsInfo.getSpecName1()+":"+gsInfo.getValuesName1()+";");
                    }
                    if(StringUtils.isNotBlank(gsInfo.getSpecName2()) && StringUtils.isNotBlank(gsInfo.getValuesName2())){
                        goodsSpecVal.append(gsInfo.getSpecName2()+":"+gsInfo.getValuesName2()+";");
                    }
                    if(StringUtils.isNotBlank(gsInfo.getSpecName3()) && StringUtils.isNotBlank(gsInfo.getValuesName3())){
                        goodsSpecVal.append(gsInfo.getSpecName3()+":"+gsInfo.getValuesName3()+";");
                    }
                    if(StringUtils.isNotBlank(gsInfo.getSpecName4()) && StringUtils.isNotBlank(gsInfo.getValuesName4())){
                        goodsSpecVal.append(gsInfo.getSpecName4()+":"+gsInfo.getValuesName4()+";");
                    }
                    orderDetail.setGoodsSpecVal(goodsSpecVal.toString());
                    orderDetail.setUserId(userId);
                    orderDetailList.add(orderDetail);
                }
            }
            order.setOrderDetailList(orderDetailList);
        }

        // 进行优惠券优惠金额计算
        if(StringUtils.isBlank(couponId)){// 如果未使用优惠券
            order.setOrderYh(0);
            order.setOrderYf(order.getOrderMoney());
        }else{// 如果使用了优惠券，则根据优惠券ID查询优惠金额
            String yhMoneyStr = iOrderService.searchCmById(couponId);// 获取订单使用的优惠券金额
            double yhMoney = 0;
            if(StringUtils.isNotBlank(yhMoneyStr)){
                yhMoney = Double.parseDouble(yhMoneyStr);
            }
            order.setOrderYh(yhMoney);
            order.setOrderYf(order.getOrderMoney() - yhMoney);
        }

        // 新增订单并返回订单编号和新增状态
        JsVo jsVo = iOrderService.addOrder(order);
        Message<JsVo> message = null;
        if(jsVo != null && jsVo.getResultVal() == 1){
            message = MessageUtils.getSuccess("success");
            message.setResult(1);
            // 如果下单成功，则删除购物车中已形成订单的商品数据
            if(cartIdList != null && cartIdList.size() > 0){
                for(int i = 0;i < cartIdList.size();i++){
                    if(!iCartService.removeCgByCId(cartIdList.get(i))){
                        logger.error("生成订单后，购物车商品ID数据"+cartIdList.get(i)+"未从购物车中删除");
                    }
                }
            }
        }else{
            message = MessageUtils.getFail("fail");
            message.setResult(0);
        }
        message.setData(jsVo);
        return message;
    }

}