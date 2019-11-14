package cn.com.myproject.api.goods.controller;

import cn.com.myproject.api.service.IGoodsService;
import cn.com.myproject.api.util.DateUtils;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.goods.entity.PO.GoodsSaleMonth;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.goods.entity.PO.Spec;
import cn.com.myproject.goods.entity.VO.GoodsSpecVO;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeiJia on 2017/9/15 0021.
 */

@RestController
@RequestMapping(value = "/api/goods")
@Api(value="商城-商品详情",tags = "商品详情")
public class GoodsController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("GoodsController");

    @Autowired
    private IGoodsService goodsService;

    /**
     * 展示商品详情
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "商城-商品详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "goodsId", value = "商品ID", required = true, dataType = "String",defaultValue = "5f9cc4e7a5e24767aae4df0e9d628d89")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Goods.class, responseContainer = "Goods" ) })

    @PostMapping(value = "/goodsDetail")
    public Message<Goods> goodsDetail(String goodsId){
        if(StringUtils.isBlank(goodsId) || StringUtils.isEmpty(goodsId)){
            return MessageUtils.getSuccess("商品ID不能为空");
        }
        try{

            Goods goods = goodsService.searchGoodsById(goodsId);
            //商品spec列表
            List<Spec> specs = goodsService.selectSpecsByGoodsId(goodsId);
            goods.setSpecs(specs);

            //商品sku列表
            //List<GoodsSpec> goosSpecList = goodsService.selectGoodsSpecsByGoodsId(goodsId);
            //goods.setGoodsSpecs(goosSpecList);


            //商品月销
            GoodsSaleMonth goodsSaleMonth = new GoodsSaleMonth();
            goodsSaleMonth.setGoodsId(goodsId);
            goodsSaleMonth.setStateTime(DateUtils.getMonthfirstDayTimeInMillis());
            int month_sale_num = goodsService.selectGoodsMonthlySalesByGoodsId(goodsSaleMonth);
            goods.setMonthSaleNum(month_sale_num);
            Message<Goods> message =MessageUtils.getSuccess("success");
            message.setData(goods);
            return message;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("查询商城-商品详情异常"+e.getMessage());
            return MessageUtils.getFail("查询商城-商品详情异常"+e.getMessage());

        }

    }


    /**
     * 获取商品GoodsSpec对象
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "商城-商品详情-获取商品GoodsSpec对象（根据商品ID、商品的规格ID与规格值ID值查询商品的价格、库存、goods_spec_id）", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "goodsId", value = "商品ID", required = true, dataType = "String",defaultValue = "5f9cc4e7a5e24767aae4df0e9d628d89"),
            @ApiImplicitParam(paramType="query",name = "specValuesListStr", value = "用户选择的规格与规格值ID,JSON字符串", required = true, dataType = "String",defaultValue = "[\n" +
                    "    {\n" +
                    "        \"specId\": \"ab224267c20941628b5d6eda7ab19ddc\",\n" +
                    "        \"goodsSpecValuesId\": \"6c61aa1d72474a33a40dd74304d2cdb9\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"specId\": \"c967a88cf50641c2a9138ea476098ca6\",\n" +
                    "        \"goodsSpecValuesId\": \"f237deaa1fbe43798e943d53abcb9319\"\n" +
                    "    }\n" +
                    "]")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = GoodsSpec.class, responseContainer = "GoodsSpec" ) })

    @PostMapping(value = "/getGoodsSpecDetail")
    public Message<GoodsSpec> getGoodsSpecDetail(String goodsId,String specValuesListStr){
        if(StringUtils.isBlank(goodsId) || StringUtils.isEmpty(goodsId)){
            return MessageUtils.getSuccess("商品ID不能为空");
        }
        try{
            List<GoodsSpecVO> goodsSpecVOArrayList = new ArrayList<GoodsSpecVO>();
            try{
                specValuesListStr =specValuesListStr.replace("\\n","");
                goodsSpecVOArrayList = JSONArray.parseArray(specValuesListStr, GoodsSpecVO.class);
            } catch(Exception e){
                e.printStackTrace();
                logger.error("specValuesListStr解析异常"+e.getMessage());
                return MessageUtils.getFail("specValuesListStr解析异常"+e.getMessage());
            }
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setGoodsId(goodsId);
            if(goodsSpecVOArrayList != null && goodsSpecVOArrayList.size()>0){
                for(int i = 0;i<goodsSpecVOArrayList.size();i++){
                    switch (i){
                        case 0:
                            goodsSpec.setSpecId(goodsSpecVOArrayList.get(0).getSpecId());
                            goodsSpec.setGoodsSpecValuesId(goodsSpecVOArrayList.get(0).getGoodsSpecValuesId());
                            break;
                        case 1:
                            goodsSpec.setSpecId1(goodsSpecVOArrayList.get(1).getSpecId());
                            goodsSpec.setGoodsSpecValuesId1(goodsSpecVOArrayList.get(1).getGoodsSpecValuesId());
                            break;
                        case 2:
                            goodsSpec.setSpecId2(goodsSpecVOArrayList.get(2).getSpecId());
                            goodsSpec.setGoodsSpecValuesId2(goodsSpecVOArrayList.get(2).getGoodsSpecValuesId());
                            break;
                        case 3:
                            goodsSpec.setSpecId3(goodsSpecVOArrayList.get(3).getSpecId());
                            goodsSpec.setGoodsSpecValuesId3(goodsSpecVOArrayList.get(3).getGoodsSpecValuesId());
                            break;
                        case 4:
                            goodsSpec.setSpecId4(goodsSpecVOArrayList.get(4).getSpecId());
                            goodsSpec.setGoodsSpecValuesId4(goodsSpecVOArrayList.get(4).getGoodsSpecValuesId());
                            break;
                    }

                }
            }
            GoodsSpec spec = goodsService.getGoodsSpecDetail(goodsSpec);
            Message<GoodsSpec> message =MessageUtils.getSuccess("success");
            message.setData(spec);
            return message;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("商城-商品详情-获取商品sku对象异常"+e.getMessage());
            return MessageUtils.getFail("商城-商品详情-获取商品GoodsSpec对象异常"+e.getMessage());

        }

    }


    /**
     * 商城-商品详情-商品sku对应的月销数量
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "商城-商品详情-商品sku对应的月销数量", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "goodsId", value = "商品ID", required = true, dataType = "String",defaultValue = "5f9cc4e7a5e24767aae4df0e9d628d89"),
            @ApiImplicitParam(paramType="query",name = "goodsSpecId", value = "商品skuID", required = true, dataType = "String",defaultValue = "7c05d91cb69645acb5db83a21295cb52")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Integer.class, responseContainer = "Integer" ) })

    @PostMapping(value = "/selectGoodsMonthlySaleNum")
    public Message<Integer> selectGoodsMonthlySaleNum(String goodsId,String goodsSpecId){
        if(StringUtils.isBlank(goodsId) || StringUtils.isEmpty(goodsId)){
            return MessageUtils.getSuccess("商品ID不能为空");
        }
        if(StringUtils.isBlank(goodsSpecId) || StringUtils.isEmpty(goodsSpecId)){
            return MessageUtils.getSuccess("商品skuID不能为空");
        }
        try{


            //商品月销
            GoodsSaleMonth goodsSaleMonth = new GoodsSaleMonth();
            goodsSaleMonth.setGoodsId(goodsId);
            goodsSaleMonth.setGoodsSpecId(goodsSpecId);
            goodsSaleMonth.setStateTime(DateUtils.getMonthfirstDayTimeInMillis());
            int month_sale_num = goodsService.selectGoodsMonthlySalesByGoodsId(goodsSaleMonth);
            Message<Integer> message =MessageUtils.getSuccess("success");
            message.setData(month_sale_num);
            return message;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("查询商城-商品详情-商品sku对应的月销数量异常"+e.getMessage());
            return MessageUtils.getFail("查询商城-商品详情-商品sku对应的月销数量异常"+e.getMessage());

        }

    }


}
