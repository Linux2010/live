package cn.com.myproject.external;

import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.VO.CartVO;
import cn.com.myproject.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-18
 * desc：购物车服务控制器类
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    /**
     * 查询购物车商品列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/searchCgList")
    public List<CartVO> searchCgList(String userId){
        return iCartService.searchCgList(userId);
    }

    /**
     * 加入购物车商品数据
     *
     * @param cart
     * @return
     */
    @PostMapping("/addCg")
    public boolean addCg(@RequestBody Cart cart){
        return iCartService.addCg(cart);
    }

    /**
     * 根据购物车数据ID删除购物车商品
     *
     * @param cartId
     * @return
     */
    @PostMapping("/removeCgByCId")
    public boolean removeCgByCId(String cartId){
        return iCartService.removeCgByCId(cartId);
    }

    /**
     * 根据购物车数据ID更新购物车商品数量
     *
     * @param cart
     * @return
     */
    @PostMapping("/modifyCgNumByCId")
    public boolean modifyCgNumByCId(@RequestBody Cart cart){
        return iCartService.modifyCgNumByCId(cart);
    }

    /**
     * 更新购物车商品是否选中标记
     *
     * @param cart
     * @return
     */
    @PostMapping("/modifyCgCk")
    public boolean modifyCgCk(@RequestBody Cart cart){
        return iCartService.modifyCgCk(cart);
    }

    /**
     * 更新购物车全部商品是否选中标记
     *
     * @param cart
     * @return
     */
    @PostMapping("/modifyAllCgCk")
    public boolean modifyAllCgCk(@RequestBody Cart cart){
        return iCartService.modifyAllCgCk(cart);
    }

    /**
     * 根据购物车商品数据ID查询购物车商品数据
     *
     * @param cartId
     * @return
     */
    @GetMapping("/searchCartById")
    public Cart searchCartById(String cartId){
        return iCartService.searchCartById(cartId);
    }

    /**
     * 根据用户ID查询该用户在购物车中勾选的商品总额
     *
     * @param userId
     * @return
     */
    @GetMapping("/searchCgTmByUserId")
    public String searchCgTmByUserId(String userId){
        return iCartService.searchCgTmByUserId(userId);
    }

    /**
     * 根据用户ID、商品ID和商品skuId查询购物车商品信息
     *
     * @param userId
     * @param goodsId
     * @param goodsSpecId
     * @return
     */
    @GetMapping("/searchCgInfoByUGSId")
    public Cart searchCgInfoByUGSId(String userId,String goodsId,String goodsSpecId){
        return iCartService.searchCgInfoByUGSId(userId,goodsId,goodsSpecId);
    }

}