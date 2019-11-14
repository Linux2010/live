package cn.com.myproject.shop.service.Impl;

import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.VO.CartVO;
import cn.com.myproject.shop.mapper.CartMapper;
import cn.com.myproject.shop.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-18
 * desc：购物车Service接口实现类
 */
@Service
public class CartService implements ICartService{

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartMapper cartMapper;

    /**
     * 查询购物车商品列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<CartVO> searchCgList(String userId){
        return cartMapper.searchCgList(userId);
    }

    /**
     * 加入购物车商品数据
     *
     * @param cart
     * @return
     */
    @Override
    @Transactional
    public boolean addCg(Cart cart){
        boolean flagVal = false;
        try {
            cart.setChecked(0);// 默认未选中
            int countVal = cartMapper.insertCg(cart);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("加入购物车商品数据失败");
            }
        } catch (Exception e) {
            logger.error("加入购物车商品数据失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 根据购物车数据ID删除购物车商品
     *
     * @param cartId
     * @return
     */
    @Override
    @Transactional
    public boolean removeCgByCId(String cartId){
        boolean flagVal = false;
        try {
            int countVal = cartMapper.deleteCgByCId(cartId);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("根据购物车数据ID删除购物车商品失败");
            }
        } catch (Exception e) {
            logger.error("根据购物车数据ID删除购物车商品失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 根据购物车数据ID更新购物车商品数量
     *
     * @param cart
     * @return
     */
    @Override
    @Transactional
    public boolean modifyCgNumByCId(Cart cart){
        boolean flagVal = false;
        try {
            int countVal = cartMapper.updateCgNumByCId(cart);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("根据购物车数据ID更新购物车商品数量失败");
            }
        } catch (Exception e) {
            logger.error("根据购物车数据ID更新购物车商品数量失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 更新购物车商品是否选中标记
     *
     * @param cart
     * @return
     */
    @Override
    @Transactional
    public boolean modifyCgCk(Cart cart){
        boolean flagVal = false;
        try {
            int countVal = cartMapper.updateCgCk(cart);
            if(countVal > 0){
                flagVal = true;
            }else{
                logger.error("更新购物车商品是否选中标记失败");
            }
        } catch (Exception e) {
            logger.error("更新购物车商品是否选中标记失败");
            e.printStackTrace();
        }
        return flagVal;
    }

    /**
     * 更新购物车所有商品是否选中标记
     *
     * @param cart
     * @return
     */
    @Override
    @Transactional
    public boolean modifyAllCgCk(Cart cart){
        String userIdVal = "";
        int checked = 0;
        if(cart != null){
            userIdVal = cart.getUserId();
            checked = cart.getChecked();
        }
        List<String> cartIdList = cartMapper.searchCgIdListByUserId(userIdVal);
        boolean flagVal = true;
        if(cartIdList != null && cartIdList.size() > 0){
            for(int i = 0;i < cartIdList.size();i++){
                String cartIdVal = "";
                if(cartIdList.get(i) != null){
                    cartIdVal = cartIdList.get(i);
                }
                cart.setCartId(cartIdVal);
                try {
                    int countVal = cartMapper.updateCgCk(cart);
                    if(countVal == 0){
                        flagVal = false;
                        logger.error("更新购物车商品是否选中标记失败");
                    }
                } catch (Exception e) {
                    logger.error("更新购物车商品是否选中标记失败");
                    e.printStackTrace();
                }
            }
        }
        return flagVal;
    }

    /**
     * 根据购物车商品数据ID查询购物车商品数据
     *
     * @param cartId
     * @return
     */
    @Override
    public Cart searchCartById(String cartId){
        return cartMapper.searchCartById(cartId);
    }

    /**
     * 根据用户ID查询该用户在购物车中勾选的商品总额
     *
     * @param userId
     * @return
     */
    @Override
    public String searchCgTmByUserId(String userId){
        return cartMapper.searchCgTmByUserId(userId);
    }

    /**
     * 根据用户ID、商品ID和商品skuId查询购物车商品信息
     *
     * @param userId
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    @Override
    public Cart searchCgInfoByUGSId(String userId,String goodsId,String goodsSpecId){
        return cartMapper.searchCgInfoByUGSId(userId,goodsId,goodsSpecId);
    }

}