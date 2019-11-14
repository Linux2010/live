package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.SpecValues;
import cn.com.myproject.goods.mapper.SpecValuesMapper;
import cn.com.myproject.goods.service.ISpecValueService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LeiJia on 2017/9/14 0021.
 */
@Service
public class SpecValueService implements ISpecValueService{

    @Autowired
    private SpecValuesMapper specValuesMapper;

    @Transactional
    public boolean addSpecValues(SpecValues  specValues){
        boolean flagVal = true;
        int countVal = specValuesMapper.insertSpecValues(specValues);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    @Transactional
    public boolean removeSpecValues(String goodsSpecValuesId){
        boolean flagVal = true;
        int countVal = specValuesMapper.deleteSpecValues(goodsSpecValuesId);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    @Transactional
    public boolean modifySpecValues(SpecValues specValues){
        boolean flagVal = true;
        int countVal = specValuesMapper.updateSpecValues(specValues);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    public PageInfo<SpecValues> searchSpecValuesList(int pageNum, int pageSize, String valuesName,String specId){
        List<SpecValues> ctList = specValuesMapper.searchSpecValuesList(pageNum,pageSize,valuesName, specId);
        return new PageInfo<SpecValues>(ctList);
    }


    public SpecValues searchSpecValuesById(String goodsSpecValuesId){
        return specValuesMapper.searchSpecValuesById(goodsSpecValuesId);
    }


}
