package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.GoodsSaleMonth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsSaleMonthMapper {
    /**
     * 插入商品月销统计
     * @param goodsSaleMonth
     * @return
     */
    public int insertGoodsSaleMonth(GoodsSaleMonth goodsSaleMonth);

    /**
     * 修改商品月销统计
     * @param goodsSaleMonth
     * @return
     */
    public int updateGoodsSaleMonth(GoodsSaleMonth goodsSaleMonth);



    /**
     * 根据ID查询商品月销统计记录
     * @param goodsSaleMonthId
     * @return
     */
    public GoodsSaleMonth searchGoodsSaleMonthById(@Param("goodsSaleMonthId") String goodsSaleMonthId);


    /**
     * 查询商品月销统计详情
     * @param goodsSaleMonth
     * @return
     */
    public GoodsSaleMonth searchGoodsSaleMonthDetail(GoodsSaleMonth goodsSaleMonth);


}
