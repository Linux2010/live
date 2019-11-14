package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMapper {
    /**
     * 插入商品库存
     * @param stock
     * @return
     */
    public int insertStock(Stock stock);

    /**
     * 根据stockId删除商品库存
     * @param stockId
     * @return
     */
    public int deleteStock(@Param("stockId") String stockId);

    /**
     * 修改商品库存日志
     * @param stock
     * @return
     */
    public int updateStock(Stock stock);

    /**
     * 分页查询商品库存
     * @param pageNum
     * @param pageSize
     * @param goodsId
     * @return
     */
    public List<Stock> searchStockList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("goodsId") String goodsId);

    /**
     * 根据ID查询商品库存日志信息
     * @param stockId
     * @return
     */
    public Stock searchStockById(@Param("stockId") String stockId);

    /**
     * 查询库存详情
     * @param stock
     */
    public Stock  searchStockDetail(Stock stock);
}
