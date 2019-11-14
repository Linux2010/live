package cn.com.myproject.goods.mapper;

import cn.com.myproject.goods.entity.PO.GoodsComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


@Mapper
public interface GoodsCommMapper {

     GoodsComment searcGComm(@Param("reviewerId") String reviewerId,@Param("goodsId") String goodsId);

    /**
     *
     *
     * 添加评论
     * @param goodsComment
     * @return
     */
     int addComm(GoodsComment goodsComment);

     List<GoodsComment> searchGCList(@Param("pageNumKey")int pageNum,
                                          @Param("pageSizeKey")int pageSize,
                                          @Param("goodsId")String goodsId);


     int removeGoodsComment(@Param("goodsCommentId") String goodsCommentId);

     List<GoodsComment> searchGoodsCommentList(@Param("pageNumKey") int  pageNum, @Param("pageSizeKey") int pageSize);

    public GoodsComment searchGoodsCommentById(@Param("goodsCommentId") String goodsCommentId);
}
