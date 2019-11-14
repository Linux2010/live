package cn.com.myproject.service.goods;
import cn.com.myproject.goods.entity.PO.GoodsComment;
import cn.com.myproject.goods.entity.PO.GoodsCommentReply;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-29
 * desc：商品评论与服务Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IGoodsCommentService {

    @PostMapping("/goodsComm/removeGoodsComment")
    public int removeGoodsComment(@RequestParam("goodsCommentId") String goodsCommentId);


    @GetMapping("/goodsComm/searchGoodsCommentList")
    public PageInfo<GoodsComment> searchGoodsCommentList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize);

    @GetMapping("/goodsComm/searchGoodsCommentById")
    public GoodsComment searchGoodsCommentById(@RequestParam("goodsCommentId") String goodsCommentId);

    @PostMapping("/goodsComm/addGoodsCommentReply")
    public Integer addGoodsCommentReply(@RequestBody GoodsCommentReply goodsCommentReply);

    @PostMapping("/goodsComm/searchGoodsCommentReplyBygoodsCommentId")
    public List<GoodsCommentReply> searchGoodsCommentReplyBygoodsCommentId(@RequestParam("goodsCommentId") String goodsCommentId);
}