package cn.com.myproject.goods.service;
import cn.com.myproject.goods.entity.PO.*;
import cn.com.myproject.goods.entity.VO.GoodsSpecStockVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-15
 * desc：商品Service接口
 */
public interface IGoodsService {
    public String addGoods( Goods goods);

    public int removeGoods(String goodsId);

    public int modifyGoods(Goods goods);

    public int  updateGoodsIntro(Goods goods);

    public PageInfo<Goods> searchGoodsList( int pageNum,
                                            int pageSize,
                                            String keyword);
    public PageInfo<Goods> searchGoodsListByCatId(int pageNum, int pageSize,String keyword,String catId,String couponId);

    public Goods searchGoodsById(String goodsId);


    public int checkGoodsIsExist(Goods goods);

    public int addGoodsSpecAndStock(GoodsSpecStockVO vo);

    public int updateGoodsSpecAndStock(GoodsSpecStockVO vo);

    public int selectGoodsMonthlySalesByGoodsId(GoodsSaleMonth goodsSaleMonth);

    public List<GoodsSku> searchGoodsSkuListByGoodsId(String goodsId);

}