package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.GoodsCollect;
import cn.com.myproject.goods.mapper.GoodsCollectMapper;
import cn.com.myproject.goods.service.IGoodsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 李延超 on 2017/9/19.
 */
@Service
public class GoodsCollectService implements IGoodsCollectService {

    @Autowired
    private  GoodsCollectMapper gcMapper;

    /**
     * 根据用户和商品查询是否收藏商品
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public GoodsCollect searchUserGoodsCollect(String userId,String goodsId){
        return gcMapper.searcUserGoodsCollect(userId,goodsId);
    }

    @Override
    @Transactional
    public boolean addCg(GoodsCollect goodsCollect){
        boolean flagVal=false;
        int countVal=gcMapper.addCollect(goodsCollect);
        if(countVal>0){
            flagVal=true;
        }
        return  flagVal;
    }
    @Override
    @Transactional
    public boolean removeCg(@RequestBody GoodsCollect goodsCollect){
        boolean flagVal=false;
        int countVal=gcMapper.updateCollect(goodsCollect);
        if(countVal>0){
            flagVal=true;
        }
        return  flagVal;
    }





}
