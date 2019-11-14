package cn.com.myproject.advertise.service.impl;


import cn.com.myproject.advertise.mapper.AdvertiseMapper;
import cn.com.myproject.advertise.service.IAdvertiseService;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.Advertise;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/8/15 0007.
 */
@Service
public class AdvertiseService implements IAdvertiseService{

    @Autowired
    private AdvertiseMapper advertiseMapper;

    @Override
    public PageInfo<Advertise> getPage(int pageNum, int pageSize, Map<String, Object> map) {

        List<Advertise> list = advertiseMapper.getPage(pageNum, pageSize ,map);
        return convert(list);
    }

    @Override
    public Advertise selectAdverById(String adverId) {

        return advertiseMapper.selectAdverById(adverId);
    }

    @Override
    public List<Advertise> selectAll() {

        return advertiseMapper.selectAll();
    }

    @Override
    public List<Advertise> selectAdverType(int type, int status){

        return advertiseMapper.selectAdverType(type, status);
    }

    @Override
    public void addAdver(@RequestBody Advertise advertise) {

        advertiseMapper.addAdver(advertise);
    }

    @Override
    public void updateAdver(@RequestBody Advertise advertise) {

        advertiseMapper.updateAdver(advertise);
    }

    @Override
    public void deleteAdver(String adverId) {

        advertiseMapper.deleteAdver(adverId);
    }

    @Override
    public List<Goods> selectAllGoods() {

        return advertiseMapper.selectAllGoods();
    }

    @Override
    public List<Goods> selectGoodsByKeyword(int pageNum, int pageSize, String keyword) {

        return advertiseMapper.selectGoodsByKeyword(pageNum, pageSize, keyword);
    }

    @Override
    public List<Goods> getPageGoods(int pageNum, int pageSize) {

        return advertiseMapper.getPageGoods(pageNum, pageSize);
    }

    private PageInfo<Advertise> convert(List<Advertise> list) {
        PageInfo<Advertise> info = new PageInfo(list);

        List<Advertise> _list = info.getList();
        info.setList(null);
        List<Advertise> __list = new ArrayList<>(10);

        PageInfo<Advertise> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(Advertise advertise : _list) {
                __list.add(advertise);
            }
            _info.setList(__list);
        }
        return _info;
    }
}
