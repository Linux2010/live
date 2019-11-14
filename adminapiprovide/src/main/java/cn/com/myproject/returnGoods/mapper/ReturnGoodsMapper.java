package cn.com.myproject.returnGoods.mapper;

import cn.com.myproject.user.entity.PO.ReturnGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LSG on 2017/9/14 0014.
 */
@Mapper
public interface ReturnGoodsMapper {

    List<ReturnGoods> allReturnGoods(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<ReturnGoods> selectByStatus(int status);

    void addReturnGoods(ReturnGoods returnGoods);

    void deleteById(String returnGoodsId);

    ReturnGoods selectById(String returnGoodsId);

    void updateReturnGoods(ReturnGoods returnGoods);

    List<ReturnGoods> selectByUserId(String userId);

    ReturnGoods selectByLogisticsNumber(String logisticsNumber);

    List<ReturnGoods> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("status") int status);
}
