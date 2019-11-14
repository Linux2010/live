package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.GoodsComment;
import cn.com.myproject.goods.entity.PO.GoodsCommentReply;
import cn.com.myproject.goods.service.IGoodsCommService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/goodsComm")
public class GoodsCommController {
    public static final Logger logger = LoggerFactory.getLogger(GoodsCommController.class);

    @Autowired
    private IGoodsCommService goodsCommService;


//    @GetMapping("/searcGComm")
//    public List<GoodsComment> searcGComm(String goodsId){
//        return goodsCommService
//    }

    @PostMapping("/addComm")
    public boolean addComm(@RequestBody GoodsComment goodsComment) {
        return goodsCommService.addComm(goodsComment);
    }

    @GetMapping("/SearchGCList")
    public List<GoodsComment> searchGCLIst(int pageNum, int pageSize, String goodsId) {
       return goodsCommService.searchGCList(pageNum,pageSize,goodsId);
    }

    @PostMapping("/removeGoodsComment")
    public int removeGoodsComment(String goodsCommentId){
        return goodsCommService.removeGoodsComment(goodsCommentId);
    }

    @GetMapping("/searchGoodsCommentList")
    public PageInfo<GoodsComment> searchGoodsCommentList(int pageNum,  int pageSize){
        return goodsCommService.searchGoodsCommentList(pageNum,pageSize);
    }

    @GetMapping("/searchGoodsCommentById")
    public GoodsComment searchGoodsCommentById(String goodsCommentId){
        return goodsCommService.searchGoodsCommentById(goodsCommentId);
    }

    @PostMapping("/addGoodsCommentReply")
    public Integer addGoodsCommentReply(@RequestBody GoodsCommentReply goodsCommentReply){
        return goodsCommService.addGoodsCommentReply(goodsCommentReply);

    }
    @PostMapping("/searchGoodsCommentReplyBygoodsCommentId")
    public List<GoodsCommentReply> searchGoodsCommentReplyBygoodsCommentId(String goodsCommentId){
        return goodsCommService.searchGoodsCommentReplyBygoodsCommentId(goodsCommentId);
    }
}