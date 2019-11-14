package cn.com.myproject.api.goods.controller;

import cn.com.myproject.api.service.IGoodsService;
import cn.com.myproject.api.service.IUploadImgService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.goods.entity.PO.GoodsComment;
import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by 李延超 on 2017/9/20.
 */
@RestController
@RequestMapping(value = "/api/goodsComment")
@Api(value = "商品评论",tags = "商品评论")
public class GoodsCommentController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsCommentController.class);

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadImgService uploadImgService;


    @PostMapping("/searchGcommList")
    @ApiOperation(value = "根据商品id查询商品评论列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前第几页",
                    required = true, dataType = "String", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String", defaultValue = "10"),
            @ApiImplicitParam(paramType = "query", name = "goodsId", value = "商品id",
                    required = true, dataType = "String", defaultValue = "10b6a72260d0436684588965a53a189e")
    })
    public Message<List<GoodsComment>> searchGcommList(String pageNum, String pageSize, String goodsId) {
        try {
            int pageNumVal = 0;
            int pageSizeVal = 0;
            if (StringUtils.isNotBlank(pageNum)) {
                pageNumVal = Integer.parseInt(pageNum);
            }
            if (StringUtils.isNotBlank(pageSize)) {
                pageSizeVal = Integer.parseInt(pageSize);
            }
            if (StringUtils.isBlank(goodsId)) {
                return MessageUtils.getFail("商品Id不能为空");
            }
            Goods goods = goodsService.searchGoodsById(goodsId);
            if (null == goods) {
                return MessageUtils.getFail("没有这个商品");
            }
            List<GoodsComment> gcList = goodsService.searchGCList(pageNumVal, pageSizeVal, goodsId);
            Map<String, Object> map = new HashMap<>();
            for (GoodsComment goodsComment : gcList) {
                map.put("photo", goodsComment.getPhoto() == null ? "" : goodsComment.getPhoto());
                map.put("photo1", goodsComment.getPhoto1() == null ? "" : goodsComment.getPhoto1());
                map.put("photo2", goodsComment.getPhoto2() == null ? "" : goodsComment.getPhoto2());
                map.put("photo3", goodsComment.getPhoto3() == null ? "" : goodsComment.getPhoto3());
                map.put("photo4", goodsComment.getPhoto4() == null ? "" : goodsComment.getPhoto4());
                goodsComment.setImgUrl(map);
                map = new HashMap<>();
            }
            Message<List<GoodsComment>> message = MessageUtils.getSuccess("success");
            message.setData(gcList);
            return message;
        } catch (RuntimeException e) {
            return MessageUtils.getFail("失败!" + e.getMessage());
        }

    }


    @PostMapping("/addGoodsComm")
    @ApiOperation(value = "商品评论", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "goodsId", value = "商品ID",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "goodsComment", value = "商品评论内容",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "reviewerId", value = "评论人ID",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "goodsStars", value = "商品质量评价星星数量(5:好评，3-5中评,1-3差评",
                    required = true, dataType = "int", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "logiStars", value = "物流服务评价星星数量(5:好评，3-5中评,1-3差评",
                    required = true, dataType = "int", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "serviceStars", value = "服务态度评价星星数量(5:好评，3-5中评,1-3差评",
                    required = true, dataType = "int", defaultValue = "5"),
            @ApiImplicitParam(paramType = "query", name = "reviewerType", value = "评论是否匿名0：匿名；1：实名",
                    required = true, dataType = "int", defaultValue = "1")
    })
    public Message addGoodsComm(String goodsId, String goodsComment, String reviewerId, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, int reviewerType, int goods_stars, int logi_stars, int service_stars) {
        if (StringUtils.isBlank(goodsId)) {
            return MessageUtils.getFail("商品ID不能为空");
        }
        if (StringUtils.isBlank(goodsComment)) {
            return MessageUtils.getFail("商品评论内容不能为空");
        }
        if (StringUtils.isBlank(reviewerId)) {
            return MessageUtils.getFail("评论人ID不能为空");
        }
        Goods goods = goodsService.searchGoodsById(goodsId);
        if (null == goods) {
            return MessageUtils.getFail("没有这个商品");
        }

        User user = userService.selectById(reviewerId);
        if (null == user) {
            return MessageUtils.getFail("没有这个评论人");
        }

        String fileUrl1 = uploadImgService.uploadImg(file1);
        String fileUrl2 = uploadImgService.uploadImg(file2);
        String fileUrl3 = uploadImgService.uploadImg(file3);
        String fileUrl4 = uploadImgService.uploadImg(file4);
        String fileUrl5 = uploadImgService.uploadImg(file5);

        GoodsComment goodsComm = new GoodsComment();
        goodsComm.setGoodsCommentId(UUID.randomUUID().toString().replace("-", ""));
        goodsComm.setGoodsId(goodsId);
        goodsComm.setGoodsComment(goodsComment);
        goodsComm.setReviewerId(reviewerId);
        goodsComm.setUserPhoto(user.getPhoto());
        goodsComm.setGoodsName(goods.getGoodsName());
        goodsComm.setReviewerType(reviewerType);
        goodsComm.setPhoto(fileUrl1);
        goodsComm.setPhoto1(fileUrl2);
        goodsComm.setPhoto2(fileUrl3);
        goodsComm.setPhoto3(fileUrl4);
        goodsComm.setPhoto4(fileUrl5);
        goodsComm.setGoodsStars(goods_stars);
        goodsComm.setLogiStars(logi_stars);
        goodsComm.setServiceStars(service_stars);
        goodsComm.setCreateTime(new Date().getTime());
        goodsComm.setStatus((short) 1);
        goodsComm.setVersion(1);
        if (logi_stars == 5) {
            goodsComm.setGoodCommNum(goodsComm.getGoodCommNum() + 1);
        } else if (logi_stars == 3 || logi_stars == 4) {
            goodsComm.setMidCommNum(goodsComm.getMidCommNum() + 1);
        } else if (logi_stars == 1 || logi_stars == 2) {
            goodsComm.setBadCommNum(goodsComm.getBadCommNum() + 1);
        }

        if (fileUrl1 != null) {
            goodsComm.setShowPhoto(goodsComm.getShowPhoto() + 1);
        }


        goodsComm.setAllComm(goods.getCommNum() + 1);
        boolean flagVal = goodsService.addComm(goodsComm);
        if (flagVal) {
            Message message = MessageUtils.getSuccess("success");
            return message;
        } else {
            return MessageUtils.getFail("评论失败");
        }
    }

}
