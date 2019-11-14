package cn.com.myproject.advertise.service;

import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.Advertise;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/15 0007.
 */
public interface IAdvertiseService {

    PageInfo<Advertise> getPage(int pageNum, int pageSize, Map<String, Object> map);

    Advertise selectAdverById(String adverId);

    List<Advertise> selectAll();

    List<Advertise> selectAdverType(int type, int status);

    void addAdver(Advertise advertise);

    void updateAdver(Advertise advertise);

    void deleteAdver(String adverId);

    List<Goods> selectAllGoods();

    List<Goods> selectGoodsByKeyword(int pageNum, int pageSize, String keyword);

    List<Goods> getPageGoods(int pageNum, int pageSize);
}
