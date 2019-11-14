package cn.com.myproject.goods.service;

import cn.com.myproject.goods.entity.PO.GoodsCollect;

/**
 * Created by 李延超 on 2017/9/19.
 */
public interface IGoodsCollectService {
    /**
     * 根据用户和商品查询是否收藏商品
     */

    public GoodsCollect searchUserGoodsCollect(String userId, String goodsId);

    public boolean addCg(GoodsCollect goodsCollect);
    public boolean removeCg(GoodsCollect goodsCollect);


}
