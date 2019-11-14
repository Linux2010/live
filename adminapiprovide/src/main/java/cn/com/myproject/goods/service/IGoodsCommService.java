package cn.com.myproject.goods.service;

import cn.com.myproject.goods.entity.PO.GoodsComment;
import cn.com.myproject.goods.entity.PO.GoodsCommentReply;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 李延超 on 2017/9/25.
 */
public interface IGoodsCommService {


    /**
     * 添加商品评论
     * @param goodsComment
     * @return
     */
    public boolean addComm(GoodsComment goodsComment);

    List<GoodsComment> searchGCList(int pageNum, int sizeNum, String goodsId);

    public int removeGoodsComment(String goodsCommentId);

    public PageInfo<GoodsComment> searchGoodsCommentList(int pageNum,  int pageSize);

    public GoodsComment searchGoodsCommentById(String goodsCommentId);

    public Integer addGoodsCommentReply(GoodsCommentReply goodsCommentReply);

    public List<GoodsCommentReply> searchGoodsCommentReplyBygoodsCommentId(String goodsCommentId);

}
