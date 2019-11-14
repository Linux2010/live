package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsCatMapper {

    /**
     * 插入商品分类
     * @param goodsCat
     * @return
     */
    public int insertGoodsCat(GoodsCat goodsCat);

    /**
     * 根据catId删除商品分类
     * @param catId
     * @return
     */
    public int deleteGoodsCat(@Param("catId") String catId);

    /**
     * 修改商品分类
     * @param goodsCat
     * @return
     */
    public int updateGoodsCat(GoodsCat goodsCat);

    /**
     * 分页查询商品分类列表
     * @param pageNum
     * @param pageSize
     * @param catName
     * @param parentId
     * @return
     */
    public List<GoodsCat> searchGoodsCatList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("catName") String catName,
                                             @Param("parentId") String parentId);
    /**
     * 查询讲师商品列表
     * @param level
     * @return
     */
    public List<GoodsCat> searchGoodsCatListByLevel( @Param("parentId") String parentId,@Param("level") String level);

    /**
     * 根据ID查询商品分类信息
     * @param catId
     * @return
     */
    public GoodsCat searchGoodsCatById(@Param("catId") String catId);


    /**
     * 根据父级菜单查询全部的商品分类
     * @param parentId
     * @return
     */
    public List<GoodsCat> searchGoodsCatByParentId(@Param("parentId") String parentId);

    /**
     * 根据商品分类查询商品并按照规则排序
     * @param sequenc
     * @return
     */
    public List<GoodsCatVO> selectGoodsByCatId(@Param("pageNumKey") int pageNum,
                                               @Param("pageSizeKey") int pageSize,
                                               @Param("catId") String catId,
                                               @Param("sequenc") int sequenc);
}
