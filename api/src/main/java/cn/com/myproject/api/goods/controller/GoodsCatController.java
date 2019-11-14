package cn.com.myproject.api.goods.controller;

import cn.com.myproject.api.service.IGoodsCatService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import com.netflix.discovery.util.StringUtil;
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

import java.util.List;

/**
 * Created by jyp on 2017/9/18 0018.
 */
@RestController
@RequestMapping(value = "/api/goodsCat")
@Api(value="商品分类",tags = "商品分类")
public class GoodsCatController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsCatController.class);

    @Autowired
    private IGoodsCatService goodsCatService;


    /**
     * 查询全部的商品的分类
     *
     * @param parentId
     * @return
     */
    @PostMapping("/getGoodsCatByParentId")
    @ApiOperation(value = "查询商品的全部分类", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级Id",
                    required = true, dataType = "String", defaultValue = "-1")
    })
    public Message<List<GoodsCat>> getGoodsCatByParentId(String parentId) {

        Message message = MessageUtils.getSuccess("success");
        if(StringUtils.isBlank(parentId)){
            message = MessageUtils.getFail("父级Id不能为空!");
        }
        List<GoodsCat> list = goodsCatService.searchGoodsCatByParentId(parentId);
        message.setData(list);
        return message;
    }


    @PostMapping("/selectGoodsByCatId")
    @ApiOperation(value = "根据商品分类查询商品", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "pageNum",
                    required = true, dataType = "String", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "pageSize",
                    required = true, dataType = "String", defaultValue = "10"),
            @ApiImplicitParam(paramType = "query", name = "catId", value = "商品分类Id",
                    required = true, dataType = "String", defaultValue = "72b43b8a02a84a5e972102c564839a9e"),
            @ApiImplicitParam(paramType = "query", name = "sequenc", value = "排序方式(1:销量排序2:价格从高到底3:价格从低到高)",
                    required = false, dataType = "String")
    })
    public Message<List<GoodsCatVO>> selectGoodsByCatId(String pageNum,String pageSize,String catId,String sequenc) {
        Message message = MessageUtils.getSuccess("success");
        if(StringUtils.isBlank(catId)){
            message = MessageUtils.getFail("商品分类Id不能为空!");
        }
       if(StringUtils.isBlank(sequenc)){
            sequenc = "0";//默认的查询全部的数据，不做任何排序的操作
       }
        List<GoodsCatVO> list = goodsCatService.selectGoodsByCatId(Integer.valueOf(pageNum),Integer.valueOf(pageSize),catId,Integer.valueOf(sequenc));
        message.setData(list);
        return message;
    }
}
