package cn.com.myproject.goods.service;
import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-13
 * desc：商品分类Service接口
 */
public interface IGoodsCatService {
    public boolean addGoodsCat(GoodsCat goodsCat);

    public boolean removeGoodsCat(String catId);

    public boolean modifyGoodsCat(GoodsCat goodsCat);

    public PageInfo<GoodsCat> searchGoodsCatList(int pageNum,
                                                 int pageSize,
                                                 String catName,
                                                 String parentId);
    public List<GoodsCat> searchGoodsCatListByLevel(String parentId, String level);

    public GoodsCat searchGoodsCatById(String catId);

    public List<GoodsCat> searchGoodsCatByParentId(String parentId);

    public List<GoodsCatVO> selectGoodsByCatId(int pageNum,
                                               int pageSize,
                                               String catId,
                                               int sequenc);

}