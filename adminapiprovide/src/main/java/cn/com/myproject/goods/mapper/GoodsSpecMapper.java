package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsSpecMapper {
    /**
     * 插入商品spec
     * @param goodsSpec
     * @return
     */
    public int insertGoodsSpec(GoodsSpec goodsSpec);

    /**
     * 根据specId删除商品spec
     * @param specId
     * @return
     */
    public int deleteGoodsSpec(@Param("specId") String specId);

    /**
     * 修改商品spec
     * @param goodsSpec
     * @return
     */
    public int updateGoodsSpec(GoodsSpec goodsSpec);

    /**
     * 分页查询商品spec列表
     * @param pageNum
     * @param pageSize
     * @param goodsId
     * @return
     */
    public List<GoodsSpec> searchGoodsSpecList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("goodsId") String goodsId);

    /**
     * 根据ID查询商品spec信息
     * @param goodsSpecId
     * @return
     */
    public GoodsSpec searchGoodsSpecById(@Param("goodsSpecId") String goodsSpecId);

    /**
     * 根据goodsId查询商品GoodsSpec信息
     * @param goodsId
     * @return
     */
    public List<GoodsSpec> selectGoodsSpecsByGoodsId(@Param("goodsId") String goodsId);


    /**
     * 商品GoodsSpec详细信息
     * @param spec
     * @return
     */
    public GoodsSpec getGoodsSpecDetail(GoodsSpec spec);

    /**
     * 根据商品ID和skuId查询商品信息
     *
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    GoodsSpec searchGoodsSpecInfo(@Param("goodsId") String goodsId,@Param("goodsSpecId") String goodsSpecId);

}