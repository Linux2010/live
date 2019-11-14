package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.CouponGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponGoodsMapper {
    /**
     * 插入优惠劵商品
     * @param couponGoods
     * @return
     */
    public int insertCouponGoods(CouponGoods couponGoods);

    /**
     * 根据ID删除优惠劵商品
     * @param couponGoodsId
     * @return
     */
    public int deleteCouponGoods(@Param("couponGoodsId") String couponGoodsId);

    /**
     * 修改优惠劵商品
     * @param couponGoods
     * @return
     */
    public int updateCouponGoods(CouponGoods couponGoods);

    /**
     * 分页查询优惠劵商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<CouponGoods> searchCouponGoodsList(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);
    /**
     * 查询优惠劵商品列表
     * @return
     */
    public List<CouponGoods> getCouponGoodsList();

    /**
     * 根据ID查询优惠劵商品
     * @param couponGoodsId
     * @return
     */
    public CouponGoods searchCouponGoodsById(@Param("couponGoodsId") String couponGoodsId);

    /**
     * 查询优惠劵详情
     * @param couponGoods
     * @return
     */
    public CouponGoods searchCouponGoodsDetail(CouponGoods couponGoods);
}
