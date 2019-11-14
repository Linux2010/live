package cn.com.myproject.goods.service;
import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.goods.entity.PO.Spec;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-14
 * desc：规格Service接口
 */
public interface ISpecService {
    public boolean addSpec(Spec spec);

    public boolean removeSpec(String specId);

    public boolean modifySpec(Spec spec);

    public PageInfo<Spec> searchSpecList(int pageNum,
                                         int pageSize,
                                         String specName);

    public Spec searchSpecById(String specId);

    public List<Spec> getSpecList();

    public List<Spec> selectSpecsByGoodsId(String goodsId);

    public List<GoodsSpec> selectGoodsSpecsByGoodsId(String goodsId);

    public GoodsSpec getGoodsSpecDetail(GoodsSpec spec);

}