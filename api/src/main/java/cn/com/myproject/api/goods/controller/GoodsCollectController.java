package cn.com.myproject.api.goods.controller;

import cn.com.myproject.api.service.IGoodsService;
import cn.com.myproject.api.util.CommonUtil;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.GoodsCollect;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/api/goodsCollect")
@Api(value="商品收藏",tags="商品收藏")
public class GoodsCollectController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsCollectController.class);

    @Autowired
    private IGoodsService iGoodsService;

    /**
     * 添加商品收藏
     *
     * @param goodsIdStr
     * @param userId
     * @param isCollect
     * @return
     */
    @PostMapping("/addGoodsCollects")
    @ApiOperation(value = "添加商品收藏", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "goodsIdStr", value = "商品字符串",
                    required = true, dataType = "String",defaultValue = "b2423fcdc08d49f49d5fdb196153221b,b2423fcdc08d49f49d5fdb196153221b"),
            @ApiImplicitParam(paramType="query",name = "isCollect", value = "是否收藏，0是不收藏，1是收藏",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public Message<Boolean> addGoodsCollects(String goodsIdStr, String userId, String isCollect) {
        try {
            if (StringUtils.isBlank(goodsIdStr)) {
                return MessageUtils.getFail("商品字符串能为空");
            }
            if (StringUtils.isBlank(userId)) {
                return MessageUtils.getFail("用户ID不能为空");
            }
            boolean flagVal = true;
            int goodsInsert = 0;
            int isCollectVal = 0;
            if(StringUtils.isNotBlank(isCollect)){
                isCollectVal = Integer.parseInt(isCollect);
            }
            try {
                String[] goodsIdArr = goodsIdStr.split(",");
                // 为字符串数组去除重复
                List<String> strList = CommonUtil.getDistinct(goodsIdArr);
                if(strList!= null && strList.size() > 0){
                    for(int i = 0;i < strList.size();i++){
                        GoodsCollect goodsCollect = iGoodsService.searcUserGoodsCollect(userId, strList.get(i));
                        if (goodsCollect == null) {
                            goodsCollect = new GoodsCollect();
                            String collectId = UUID.randomUUID().toString().replace("-", "");
                            goodsCollect.setCollectId(collectId);
                            goodsCollect.setGoodsId(strList.get(i));
                            goodsCollect.setUserId(userId);
                            goodsCollect.setIscollect(isCollectVal);//是否收藏，0是不收藏，1是收藏
                            flagVal = iGoodsService.addCollect(goodsCollect);

                        } else {
                            goodsCollect.setIscollect(isCollectVal);//是否收藏，0是不收藏，1是收藏
                            flagVal = iGoodsService.updateCollect(goodsCollect);
                        }
                    }
                }
            }catch(Exception e) {
                e.printStackTrace();
                return MessageUtils.getFail(e.getMessage());
            }
                Message<Boolean> message = null;
                if (flagVal) {
                    message = MessageUtils.getSuccess("success");
                    message.setResult(1);
                } else {
                    message = MessageUtils.getFail("success");
                    message.setResult(1);
                }
                message.setData(flagVal);
                return message;
            } catch (RuntimeException e) {
                return MessageUtils.getFail("收藏失败！" + e.getMessage());


            }

        }

}