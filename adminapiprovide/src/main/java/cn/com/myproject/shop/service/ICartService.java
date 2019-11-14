package cn.com.myproject.shop.service;

import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.VO.CartVO;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-18
 * desc：购物车Service接口
 */
public interface ICartService {

    /**
     * 查询购物车商品列表
     *
     * @param userId
     * @return
     */
    List<CartVO> searchCgList(String userId);

    /**
     * 加入购物车商品数据
     *
     * @param cart
     * @return
     */
    boolean addCg(Cart cart);

    /**
     * 根据购物车数据ID删除购物车商品
     *
     * @param cartId
     * @return
     */
    boolean removeCgByCId(String cartId);

    /**
     * 根据购物车数据ID更新购物车商品数量
     *
     * @param cart
     * @return
     */
    boolean modifyCgNumByCId(Cart cart);

    /**
     * 更新购物车商品是否选中标记
     *
     * @param cart
     * @return
     */
    boolean modifyCgCk(Cart cart);

    /**
     * 更新购物车所有商品是否选中标记
     *
     * @param cart
     * @return
     */
    boolean modifyAllCgCk(Cart cart);

    /**
     * 根据购物车商品数据ID查询购物车商品数据
     *
     * @param cartId
     * @return
     */
    Cart searchCartById(String cartId);

    /**
     * 根据用户ID查询该用户在购物车中勾选的商品总额
     *
     * @param userId
     * @return
     */
    String searchCgTmByUserId(String userId);

    /**
     * 根据用户ID、商品ID和商品skuId查询购物车商品信息
     *
     * @param userId
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    Cart searchCgInfoByUGSId(String userId,String goodsId,String goodsSpecId);

}