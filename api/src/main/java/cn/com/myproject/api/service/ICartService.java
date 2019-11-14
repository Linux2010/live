package cn.com.myproject.api.service;

import cn.com.myproject.shop.entity.PO.Cart;
import cn.com.myproject.shop.entity.VO.CartVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-09-19
 * desc：购物车Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICartService {

    @GetMapping("/cart/searchCgList")
    List<CartVO> searchCgList(@RequestParam("userId") String userId);

    @PostMapping("/cart/addCg")
    boolean addCg(@RequestBody Cart cart);

    @PostMapping("/cart/removeCgByCId")
    boolean removeCgByCId(@RequestParam("cartId") String cartId);

    @PostMapping("/cart/modifyCgNumByCId")
    boolean modifyCgNumByCId(@RequestBody Cart cart);

    @PostMapping("/cart/modifyCgCk")
    boolean modifyCgCk(@RequestBody Cart cart);

    @PostMapping("/cart/modifyAllCgCk")
    boolean modifyAllCgCk(@RequestBody Cart cart);

    @GetMapping("/cart/searchCartById")
    Cart searchCartById(@RequestParam("cartId") String cartId);

    @GetMapping("/cart/searchCgTmByUserId")
    String searchCgTmByUserId(@RequestParam("userId") String userId);

    @GetMapping("/cart/searchCgInfoByUGSId")
    Cart searchCgInfoByUGSId(@RequestParam("userId") String userId,
                             @RequestParam("goodsId") String goodsId,
                             @RequestParam("goodsSpecId") String goodsSpecId);

}