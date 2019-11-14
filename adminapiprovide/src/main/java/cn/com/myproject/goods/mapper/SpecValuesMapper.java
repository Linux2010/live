package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.SpecValues;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecValuesMapper {

    /**
     * 插入规格值
     * @param specValues
     * @return
     */
    public int insertSpecValues(SpecValues specValues);

    /**
     * 根据goodsSpecValuesId删除规格值
     * @param goodsSpecValuesId
     * @return
     */
    public int deleteSpecValues(@Param("goodsSpecValuesId") String goodsSpecValuesId);

    /**
     * 修改规格值
     * @param specValues
     * @return
     */
    public int updateSpecValues(SpecValues specValues);

    /**
     * 分页查询规格的规格值列表
     * @param pageNum
     * @param pageSize
     * @param valuesName
     * @param specId
     * @return
     */
    public List<SpecValues> searchSpecValuesList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("valuesName") String valuesName,
                                             @Param("specId") String specId);

    /**
     * 根据ID查询规格值信息
     * @param goodsSpecValuesId
     * @return
     */
    public SpecValues searchSpecValuesById(@Param("goodsSpecValuesId") String goodsSpecValuesId);

}
