package cn.com.myproject.returnGoods.service;

import cn.com.myproject.user.entity.PO.ReturnGoods;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by LSG on 2017/9/14 0014.
 */
public interface IReturnGoodsService {

    PageInfo<ReturnGoods> allReturnGoods(int pageNum, int pageSize);

    List<ReturnGoods> selectByStatus(int status);

    void addReturnGoods(ReturnGoods returnGoods);

    void deleteById(String returnGoodsId);

    ReturnGoods selectById(String returnGoodsId);

    void updateReturnGoods(ReturnGoods returnGoods);

    List<ReturnGoods> selectByUserId(String userId);

    ReturnGoods selectByLogisticsNumber(String logisticsNumber);

    PageInfo<ReturnGoods> getPage(int pageNum, int pageSize, int status);
}
