package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.goods.entity.PO.GoodsImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    /**
     * 插入商品
     * @param goods
     * @return
     */
     int insertGoods(Goods goods);


    /**
     * 插入商品图片
     * @param img
     * @return
     */
     int insertGoodsImg(GoodsImg img);

    /**
     * 更新商品图片
     * @param img
     * @return
     */
     int updateGoodsImg(GoodsImg img);

    /**
     * 根据goodsId删除商品
     * @param goodsId
     * @return
     */
     int deleteGoods(@Param("goodsId") String goodsId);

    /**
     * 修改商品
     * @param goods
     * @return
     */
     int updateGoods(Goods goods);

    /**
     * 修改商品简介
     * @param goods
     * @return
     */
     int updateGoodsIntro(Goods goods);

    /**
     * 分页查询商品列表
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
     List<Goods> searchGoodsList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("keyword") String keyword);

     List<Goods> searchGoodsListByCatId(@Param("pageNumKey") int pageNum,
                                              @Param("pageSizeKey") int pageSize,
                                              @Param("keyword") String keyword,
                                              @Param("catId") String catId,
                                              @Param("couponId") String couponId);

    /**
     * 查询商品规格数量
     * @param goods
     * @return
     */
     int checkGoodsSpecNum(Goods goods);

    /**
     * 根据ID查询商品信息
     * @param goodsId
     * @return
     */
     Goods searchGoodsById(@Param("goodsId") String goodsId);


    /**
     *  查询商品编号或商品名称已经被占用过
     * @param goods
     * @return
     */
     int checkGoodsIsExist(Goods goods);


    /**
     * 删除s_goods_sku表记录
     * @param goodsId
     * @return
     */
     int deleteGoodsSkuByGoodsId(@Param("goodsId") String goodsId);

    /**
     * 删除s_goods_spec表记录
     * @param goodsId
     * @return
     */
     int deleteGoodsSpecByGoodsId(@Param("goodsId") String goodsId);


    /**
     * 删除s_stock表记录
     * @param goodsId
     * @return
     */
     int deleteStockByGoodsId(@Param("goodsId") String goodsId);


    /**
     * 删除s_stock_log表记录
     * @param goodsId
     * @return
     */
     int deleteStockLogByGoodsId(@Param("goodsId") String goodsId);


    /**
     * 根据商品ID查询月销
     * @param goodsId
     * @return
     */
     int selectGoodsMonthlySalesByGoodsId(String goodsId);
}
