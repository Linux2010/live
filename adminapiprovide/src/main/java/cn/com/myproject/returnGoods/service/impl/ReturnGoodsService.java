package cn.com.myproject.returnGoods.service.impl;

import cn.com.myproject.returnGoods.mapper.ReturnGoodsMapper;
import cn.com.myproject.returnGoods.service.IReturnGoodsService;
import cn.com.myproject.user.entity.PO.ReturnGoods;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSG on 2017/9/14 0014.
 */
@Service
public class ReturnGoodsService implements IReturnGoodsService{

    @Autowired
    private ReturnGoodsMapper returnGoodsMapper;
    @Override
    public PageInfo<ReturnGoods> allReturnGoods(int pageNum, int pageSize) {

        List<ReturnGoods>  list = returnGoodsMapper.allReturnGoods(pageNum, pageSize);
        return convert(list);
    }

    @Override
    public List<ReturnGoods> selectByStatus(int status) {

        return returnGoodsMapper.selectByStatus(status);
    }

    @Override
    public void addReturnGoods(ReturnGoods returnGoods) {
        returnGoodsMapper.addReturnGoods(returnGoods);
    }

    @Override
    public void deleteById(String returnGoodsId) {
        returnGoodsMapper.deleteById(returnGoodsId);
    }

    @Override
    public ReturnGoods selectById(String returnGoodsId) {

        return returnGoodsMapper.selectById(returnGoodsId);
    }

    @Override
    public List<ReturnGoods> selectByUserId(String userId) {

        return returnGoodsMapper.selectByUserId(userId);
    }

    @Override
    public ReturnGoods selectByLogisticsNumber(String logisticsNumber) {

        return returnGoodsMapper.selectByLogisticsNumber(logisticsNumber);
    }

    @Override
    public void updateReturnGoods(ReturnGoods returnGoods) {

        returnGoodsMapper.updateReturnGoods(returnGoods);
    }

    @Override
    public PageInfo<ReturnGoods> getPage(int pageNum, int pageSize, int status) {

        List<ReturnGoods> list = returnGoodsMapper.getPage(pageNum, pageSize, status);
        return convert(list);
    }

    private PageInfo<ReturnGoods> convert(List<ReturnGoods> list) {
        PageInfo<ReturnGoods> info = new PageInfo(list);

        List<ReturnGoods> _list = info.getList();
        info.setList(null);
        List<ReturnGoods> __list = new ArrayList<>(10);

        PageInfo<ReturnGoods> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(ReturnGoods returnGoods : _list) {
                __list.add(returnGoods);
            }
            _info.setList(__list);
        }
        return _info;
    }
}
