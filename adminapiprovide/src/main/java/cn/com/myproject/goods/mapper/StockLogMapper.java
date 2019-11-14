package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.StockLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockLogMapper {
    /**
     * 插入商品库存日志
     * @param sockLog
     * @return
     */
    public int insertStockLog(StockLog sockLog);

    /**
     * 根据stockLogIds删除商品库存日志
     * @param stockLogId
     * @return
     */
    public int deleteStockLog(@Param("stockLogId") String stockLogId);

    /**
     * 修改商品库存日志
     * @param sockLog
     * @return
     */
    public int updateStockLog(StockLog sockLog);

    /**
     * 分页查询商品库存日志
     * @param pageNum
     * @param pageSize
     * @param goodsId
     * @return
     */
    public List<StockLog> searchStockLogList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("goodsId") String goodsId);

    /**
     * 根据ID查询商品库存日志信息
     * @param stockLogId
     * @return
     */
    public StockLog searchStockLogById(@Param("stockLogId") String stockLogId);

    /**
     * 查询日志详情
     * @param log
     * @return
     */
    public StockLog searchStockLogDetail(StockLog log);
}
