package cn.com.myproject.api.shop.controller;

import cn.com.myproject.api.service.ICartService;
import cn.com.myproject.api.service.IOrderService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.VO.CartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by pangdatao on 2017-09-19
 * desc：购物车控制器类
 */
@RestController
@RequestMapping(value = "/api/cart")
@Api(value="购物车",tags = "商城购物车")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @Autowired
    private IOrderService iOrderService;

    /**
     * 加入购物车
     *
     * @param goodsId
     * @param goodsNum
     * @param goodsSpecId
     * @param userId
     * @return
     */
    @PostMapping("/addCg")
    @ApiOperation(value = "加入购物车", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "goodsId",value = "商品ID",
                    required = true, dataType = "String",defaultValue = "9661001e7f634db999225e6f4c831b90"),
            @ApiImplicitParam(paramType="query",name = "goodsNum",value = "商品数量",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "goodsSpecId",value = "商品规格ID",
                    required = true, dataType = "String",defaultValue = "7c05d91cb69645acb5db83a21295cb52"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<Integer> addCg(String goodsId,String goodsNum,String goodsSpecId,String userId){

        // 获取用户级别，1是普通用户，2是会员用户
        int userLevel = 0;
        if(StringUtils.isNotBlank(userId)){
            String userLevelStr = iOrderService.searchUserLevel(userId);
            if(StringUtils.isNotBlank(userLevelStr)){
                userLevel = Integer.parseInt(userLevelStr);
            }
        }

        Cart cart = new Cart();
        cart.setCartId(UUID.randomUUID().toString().replace("-", ""));
        cart.setGoodsId(goodsId);
        cart.setUserId(userId);
        cart.setGoodsType(1);// 默认为金额商品
        cart.setCreateTime(new Date().getTime());// 创建时间默认为当前时间
        cart.setGoodsSpecId(goodsSpecId);
        if(StringUtils.isNotBlank(goodsNum)){
            cart.setGoodsNum(Integer.parseInt(goodsNum));
        }

        // 查询商品规格属性
        GoodsSpec gsInfo = iOrderService.searchGoodsSpecInfo(goodsId,goodsSpecId);

        if(gsInfo != null){

            // 设置商品规格属性值
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
            cart.setGoodsSpecVal(goodsSpecVal.toString());

            // 设置商品价格
            if(userLevel == 1){// 1代表普通用户，则获取商品市场价格
                cart.setGoodsMoney(Double.parseDouble(String.valueOf(gsInfo.getUserPrice())));
            }
            if(userLevel == 2){// 2代表会员用户，则获取会员价格
                cart.setGoodsMoney(Double.parseDouble(String.valueOf(gsInfo.getVipPrice())));
            }

        }

        // 加入购物车
        boolean flagVal = iCartService.addCg(cart);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 根据购物车数据ID删除购物车商品
     *
     * @param cartIds
     * @return
     */
    @PostMapping("/removeCgByCId")
    @ApiOperation(value = "根据购物车数据ID删除购物车商品", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "cartIds",value = "购物车数据ID以逗号分隔拼接的字符串",
                    required = true, dataType = "String",defaultValue = "2c53d219b76943778fb3f291263b9aaf")
    })
    public Message<Integer> removeCgByCId(String cartIds){
        String[] cartIdArr = null;
        if(StringUtils.isNotBlank(cartIds)){
            cartIdArr = cartIds.split(",");
        }
        boolean flagVal = true;
        if(cartIdArr != null && cartIdArr.length > 0){
            for(int i = 0;i < cartIdArr.length;i++){
                if(!iCartService.removeCgByCId(cartIdArr[i])){
                    flagVal = false;
                }
            }
        }
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 根据购物车数据ID更新购物车商品数量
     *
     * @param cartId
     * @param goodsNum
     * @return
     */
    @PostMapping("/modifyCgNumByCId")
    @ApiOperation(value = "根据购物车数据ID更新购物车商品数量", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "cartId",value = "购物车数据ID",
                    required = true, dataType = "String",defaultValue = "2c53d219b76943778fb3f291263b9aaf"),
            @ApiImplicitParam(paramType="query",name = "goodsNum",value = "商品数量(加减之后的数量)",
                    required = true, dataType = "String",defaultValue = "3")
    })
    public Message<Integer> modifyCgNumByCId(String cartId,String goodsNum){
        Cart cart = new Cart();
        cart.setCartId(cartId);
        if(StringUtils.isNotBlank(goodsNum)){
            cart.setGoodsNum(Integer.parseInt(goodsNum));
        }
        boolean flagVal = iCartService.modifyCgNumByCId(cart);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 更新购物车商品是否选中标记
     *
     * @param cartId
     * @param userId
     * @param ckFlag
     * @return
     */
    @PostMapping("/modifyCgCk")
    @ApiOperation(value = "更新购物车商品是否选中标记", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "cartId",value = "购物车数据ID",
                    required = true, dataType = "String",defaultValue = "2c53d219b76943778fb3f291263b9aaf"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "ckFlag",value = "是否选中：0代表未选中，1代表已选中",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public Message<Integer> modifyCgCk(String cartId,String userId,String ckFlag){
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setUserId(userId);
        if(StringUtils.isNotBlank(ckFlag)){
            cart.setChecked(Integer.parseInt(ckFlag));
        }
        boolean flagVal = iCartService.modifyCgCk(cart);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

    /**
     * 根据用户ID查询购物车商品列表
     *
     * @param userId
     * @return
     */
    @PostMapping("/searchCgList")
    @ApiOperation(value = "根据用户ID查询购物车商品列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea")
    })
    public Message<List<CartVO>> searchCgList(String userId){
        List<CartVO> cList = iCartService.searchCgList(userId);
        Message<List<CartVO>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    /**
     * 更新购物车全部商品是否选中标记
     *
     * @param userId
     * @param ckFlag
     * @return
     */
    @PostMapping("/modifyAllCgCk")
    @ApiOperation(value = "更新购物车全部商品是否选中标记", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId",value = "用户ID",
                    required = true, dataType = "String",defaultValue = "252685410947432b9d173f1673a06bea"),
            @ApiImplicitParam(paramType="query",name = "ckFlag",value = "是否选中：0代表未选中，1代表已选中",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public Message<Integer> modifyAllCgCk(String userId,String ckFlag){
        Cart cart = new Cart();
        cart.setUserId(userId);
        if(StringUtils.isNotBlank(ckFlag)){
            cart.setChecked(Integer.parseInt(ckFlag));
        }
        boolean flagVal = iCartService.modifyAllCgCk(cart);
        Message<Integer> message = null;
        int resultVal = 0;
        if(flagVal){
            resultVal = 1;
            message = MessageUtils.getSuccess("success");
        }else{
            message = MessageUtils.getFail("fail");
        }
        message.setData(resultVal);
        return message;
    }

}