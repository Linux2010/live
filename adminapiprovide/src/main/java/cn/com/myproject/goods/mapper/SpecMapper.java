package cn.com.myproject.goods.mapper;
import cn.com.myproject.goods.entity.PO.Spec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecMapper {
    /**
     * 插入规格
     * @param spec
     * @return
     */
    public int insertSpec(Spec spec);

    /**
     * 根据spec_id删除规格
     * @param specId
     * @return
     */
    public int deleteSpec(@Param("specId") String specId);

    /**
     * 修改规格
     * @param spec
     * @return
     */
    public int updateSpec(Spec spec);

    /**
     * 分页查询规格列表
     * @param pageNum
     * @param pageSize
     * @param specName
     * @return
     */
    public List<Spec> searchSpecList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("specName") String specName);
    /**
     * 查询规格列表
     * @return
     */
    public List<Spec> getSpecList();

    /**
     * 根据ID查询规格信息
     * @param specId
     * @return
     */
    public Spec searchSpecById(@Param("specId") String specId);


    /**
     * 根据goodsId查询规格信息
     * @param goodsId
     * @return
     */
    public List<Spec> selectSpecsByGoodsId(@Param("goodsId") String goodsId);
}
