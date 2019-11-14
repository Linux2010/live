package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.goods.entity.PO.Spec;
import cn.com.myproject.goods.mapper.GoodsSpecMapper;
import cn.com.myproject.goods.mapper.SpecMapper;
import cn.com.myproject.goods.service.ISpecService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LeiJia on 2017/9/14 0021.
 */
@Service
public class SpecService implements ISpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Transactional
    @Override
    public boolean addSpec(Spec spec){
        boolean flagVal = true;
        int countVal = specMapper.insertSpec(spec);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    @Transactional
    @Override
    public boolean removeSpec(String sepcId){
        boolean flagVal = true;
        int countVal = specMapper.deleteSpec(sepcId);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    @Transactional
    @Override
    public boolean modifySpec(Spec spec){
        boolean flagVal = true;
        int countVal = specMapper.updateSpec(spec);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    @Override
    public PageInfo<Spec> searchSpecList(int pageNum, int pageSize, String specName){
        List<Spec> ctList = specMapper.searchSpecList(pageNum,pageSize,specName);
        return new PageInfo<Spec>(ctList);
    }

    @Override
    public Spec searchSpecById(String specId){
        return specMapper.searchSpecById(specId);
    }

    @Override
    public List<Spec> getSpecList(){
        List<Spec> ctList = specMapper.getSpecList();
        return ctList;
    }

    @Override
    public List<Spec> selectSpecsByGoodsId(String goodsId){
        List<Spec> ctList = specMapper.selectSpecsByGoodsId(goodsId);
        return ctList;
    }

    @Override
    public List<GoodsSpec> selectGoodsSpecsByGoodsId( String goodsId){
        List<GoodsSpec> ctList = goodsSpecMapper.selectGoodsSpecsByGoodsId(goodsId);
        return ctList;
    }

    @Override
    public GoodsSpec getGoodsSpecDetail(GoodsSpec spec){
        GoodsSpec goodsSpec = goodsSpecMapper.getGoodsSpecDetail(spec);
        return goodsSpec;
    }
}
