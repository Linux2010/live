package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.GoodsCommentReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface GoodsCommReplyMapper {
     int addGoodsCommentReply(GoodsCommentReply goodsCommentReply);

     int delGoodsCommentReplyByGoodsCommentId(@Param("goodsCommentId") String goodsCommentId);

     List<GoodsCommentReply> searchGoodsCommentReplyBygoodsCommentId(@Param("goodsCommentId") String goodsCommentId);
}
