package cn.com.myproject.advertise.mapper;

import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.Advertise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/8/15 0007.
 */
@Mapper
public interface AdvertiseMapper {

    List<Advertise> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("map") Map<String, Object> map);

    Advertise selectAdverById(String adverId);

    List<Advertise> selectAll();

    List<Advertise> selectAdverType(@Param("type")int type, @Param("status")int status);

    void addAdver(@RequestBody Advertise advertise);

    void updateAdver(@RequestBody Advertise advertise);

    void deleteAdver(String adverId);

    //轮播图专用
    List<Goods> selectAllGoods();

    List<Goods> selectGoodsByKeyword(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("keyword") String keyword);

    List<Goods> getPageGoods(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);
}
