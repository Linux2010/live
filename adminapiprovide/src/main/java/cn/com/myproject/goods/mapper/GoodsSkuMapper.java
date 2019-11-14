package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.GoodsSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsSkuMapper {
    /**
     * 插入商品sku
     * @param goodsSku
     * @return
     */
    public int insertGoodsSku(GoodsSku goodsSku);

    /**
     * 根据goods_sku_id删除商品sku
     * @param goodsSkuId
     * @return
     */
    public int deleteGoodsSku(@Param("goodsSkuId") String goodsSkuId);

    /**
     * 修改商品sku
     * @param goodsSku
     * @return
     */
    public int updateGoodsSku(GoodsSku goodsSku);

    /**
     * 分页查询商品sku列表
     * @param pageNum
     * @param pageSize
     * @param goodsId
     * @return
     */
    public List<GoodsSku> searchGoodsSkuList(@Param("pageNumKey") int pageNum,
                                        @Param("pageSizeKey") int pageSize,
                                        @Param("goodsId") String goodsId);

    /**
     * 根据ID查询商品sku信息
     * @param goodsSkuId
     * @return
     */
    public GoodsSku searchGoodsSkuById(@Param("goodsSkuId") String goodsSkuId);


    public GoodsSku searchGoodsSkuDetail(GoodsSku sku);


    /**
     *查询商品sku列表
     * @param goodsId
     * @return
     */
    public List<GoodsSku> searchGoodsSkuListByGoodsId(  @Param("goodsId") String goodsId);
}
