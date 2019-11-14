package cn.com.myproject.api.mallIndex.controller;


import cn.com.myproject.api.service.IMallService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;

/**
 * Created by LSG on 2017/9/15 0015.
 */
@RestController
@RequestMapping("/api/mallIndex")
@Api(value="商城首页",tags = "商城首页")
public class MallIndexController {

    @Autowired
    private IMallService mallService;

    @PostMapping(value = "/selectGoodsByKeyword")
    @ApiOperation(value = "APP首页商品模糊查询", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页数",
                    required = true, dataType = "String", defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize",value = "每页显示条数",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "keyword",value = "关键字",
                    required = false, dataType = "String",defaultValue = "")
    })
    public Message<List<Goods>> selectGoodsByKeyword(String pageNum, String pageSize, String keyword) throws Exception{

        try {
            if (Integer.valueOf(pageNum) <= 0 || Integer.valueOf(pageSize) <= 0){
                pageNum = "1";
                pageSize = "10";
            }
            String str = "";
            if (StringUtils.isNotBlank(keyword)){
                str = URLDecoder.decode(keyword, "UTF-8");
            }
            List<Goods> list = mallService.searchGoodsList(Integer.valueOf(pageNum), Integer.valueOf(pageSize), str.trim());
            Message message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("查询失败！"+e.getMessage());
        }
    }
}
