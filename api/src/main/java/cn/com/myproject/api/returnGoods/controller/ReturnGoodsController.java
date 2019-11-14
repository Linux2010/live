package cn.com.myproject.api.returnGoods.controller;

import cn.com.myproject.api.service.IReturnGoodsService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.ReturnGoods;
import cn.com.myproject.user.entity.PO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by LSG on 2017/9/15 0015.
 */
@RestController
@RequestMapping("/api/returnGoods")
@Api(value="退货管理类",tags = "退货")
public class ReturnGoodsController {

    @Autowired
    private IReturnGoodsService returnGoodsService;
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/allReturnGoods")
    @ApiOperation(value = "用户退货申请列表", produces = "application/json")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id",
            required = true, dataType = "String", defaultValue = "")
    public Message<List<ReturnGoods>> allReturnGoods(String userId){

        try {
            List<ReturnGoods> list = returnGoodsService.selectByUserId(userId);
            Message message = MessageUtils.getSuccess("查询成功！");
            message.setData(list);
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("查询失败！"+e.getMessage());
        }
    }

    @PostMapping(value = "/askForReturnGoods")
    @ApiOperation(value = "退货申请", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "goodsId",value = "商品id",
                    required = true, dataType = "String",defaultValue = ""),
    })
    public Message returnGoods(String userId, String goodsId){

        try {
            if (StringUtils.isBlank(userId)){
                return MessageUtils.getFail("用户id不能为空！");
            }
            if (StringUtils.isBlank(goodsId)){
                return MessageUtils.getFail("商品id不能为空！");
            }
            User user  = userService.selectById(userId);
            if (null == user){
                return MessageUtils.getFail("没有该用户！");
            }
            Goods goods = returnGoodsService.selectByGoodsId(goodsId);
            if (null == goods){
                return MessageUtils.getFail("没有该商品！");
            }
            BigDecimal price = new BigDecimal(0);
            if (user.getUserIdentity() == 2){//该用户为会员
                price = goods.getPrice();//会员价
            }else {
                price = goods.getMktPrice();//市场价
            }
            ReturnGoods returnGoods = new ReturnGoods();
            returnGoods.setUserId(userId);
            returnGoods.setGoodsId(goodsId);
            returnGoods.setUserRealName(user.getRealName());
            returnGoods.setGoodsName(goods.getGoodsName());
            returnGoods.setMoney(price);
            if (returnGoodsService.addReturnGoods(returnGoods) == 1){
                Message message = MessageUtils.getSuccess("申请成功！");
                return message;
            }else {
                return MessageUtils.getFail("申请失败！");
            }
        }catch (RuntimeException e){
            return MessageUtils.getFail("申请失败"+e.getMessage());
        }
    }

    @PostMapping(value = "/selectReturnGoods")
    @ApiOperation(value = "退货申请查询", produces = "application/json")
    @ApiImplicitParam(paramType = "query", name = "returnGoodsId", value = "退货申请编号",
            required = true, dataType = "String", defaultValue = "")
    public Message returnGoodsAsk(String returnGoodsId){

        try {
            if (StringUtils.isBlank(returnGoodsId)){
                return MessageUtils.getFail("退货单号不能为空！");
            }
            ReturnGoods returnGoods = returnGoodsService.selectById(returnGoodsId);
            if (null == returnGoods){
                return MessageUtils.getFail("没有该退货信息！");
            }
            Message message = MessageUtils.getSuccess("查询成功！");
            message.setData(returnGoods);
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("查询失败！"+e.getMessage());
        }
    }
}


















