package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.GoodsCollect;
import cn.com.myproject.goods.service.IGoodsCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 李延超 on 2017/9/19.
 */
@RestController
@RequestMapping("/goods")
public class GoodsCollectController {

    public static final Logger logger = LoggerFactory.getLogger(GoodsCollectController.class);

    @Autowired
    private IGoodsCollectService goodsCollectService;

    @GetMapping("/searcUGcollect")
    public GoodsCollect searcUserGoodsCollect(String userId, String goodsId) {
        return goodsCollectService.searchUserGoodsCollect(userId, goodsId);
    }

    @PostMapping("/addCollect")
    public boolean addCollect(@RequestBody GoodsCollect goodsCollect) {
        return goodsCollectService.addCg(goodsCollect);
    }

    @PostMapping("/updateCollect")
    public boolean updateCollect(@RequestBody GoodsCollect goodsCollect) {
        return goodsCollectService.removeCg(goodsCollect);
    }


}