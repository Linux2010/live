package cn.com.myproject.shop.mapper;

import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.VO.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-18
 * desc：购物车Mapper接口
 */
@Mapper
public interface CartMapper {

    /**
     * 查询购物车商品列表
     *
     * @param userId
     * @return
     */
    List<CartVO> searchCgList(@Param("userId") String userId);

    /**
     * 插入购物车商品数据
     *
     * @param cart
     * @return
     */
    int insertCg(Cart cart);

    /**
     * 根据购物车数据ID删除购物车商品
     *
     * @param cartId
     * @return
     */
    int deleteCgByCId(@Param("cartId") String cartId);

    /**
     * 根据购物车数据ID修改购物车商品数量
     *
     * @param cart
     * @return
     */
    int updateCgNumByCId(Cart cart);

    /**
     * 修改购物车商品是否选中标记
     *
     * @param cart
     * @return
     */
    int updateCgCk(Cart cart);

    /**
     * 根据用户ID查询该用户的购物车数据ID列表
     *
     * @param userId
     * @return
     */
    List<String> searchCgIdListByUserId(@Param("userId") String userId);

    /**
     * 根据购物车商品数据ID查询购物车商品数据
     *
     * @param cartId
     * @return
     */
    Cart searchCartById(@Param("cartId") String cartId);

    /**
     * 根据用户ID查询该用户在购物车中勾选的商品总额
     *
     * @param userId
     * @return
     */
    String searchCgTmByUserId(@Param("userId") String userId);

    /**
     * 根据用户ID、商品ID和商品skuId查询购物车商品信息
     *
     * @param userId
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    Cart searchCgInfoByUGSId(@Param("userId") String userId,
                             @Param("goodsId") String goodsId,
                             @Param("goodsSpecId") String goodsSpecId);

}