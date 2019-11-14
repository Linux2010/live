package cn.com.myproject.goods.mapper;

import cn.com.myproject.goods.entity.PO.GoodsCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 李延超 on 2017/9/19.
 */
@Mapper
public interface GoodsCollectMapper {
    /**
     *
     * 根据用户和商品查询是否收藏商品
     */
    public GoodsCollect searcUserGoodsCollect(@Param("userId")String userId, @Param("goodsId") String goodsId);


/**
 * 添加收藏
 */
public int addCollect(GoodsCollect goodsCollect);

/**
 * 更新收藏
 */
public int updateCollect(GoodsCollect goodsCollect);




}
