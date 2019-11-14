package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import cn.com.myproject.goods.mapper.GoodsCatMapper;
import cn.com.myproject.goods.service.IGoodsCatService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LeiJia on 2017/9/14 0021.
 */
@Service
public class GoodsCatService implements IGoodsCatService {

    @Autowired
    private GoodsCatMapper goodsCatMapper;

    @Transactional
    public boolean addGoodsCat(GoodsCat goodsCat){
        boolean flagVal = true;
        int countVal = goodsCatMapper.insertGoodsCat(goodsCat);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    @Transactional
    public boolean removeGoodsCat(String catId){
        boolean flagVal = true;
        int countVal = goodsCatMapper.deleteGoodsCat(catId);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    @Transactional
    public boolean modifyGoodsCat(GoodsCat goodsCat){
        boolean flagVal = true;
        int countVal = goodsCatMapper.updateGoodsCat(goodsCat);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    public PageInfo<GoodsCat> searchGoodsCatList(int pageNum, int pageSize, String catName,String parentId){
        List<GoodsCat> ctList = goodsCatMapper.searchGoodsCatList(pageNum,pageSize,catName,parentId);
        return new PageInfo<GoodsCat>(ctList);
    }

    public List<GoodsCat> searchGoodsCatListByLevel(  String parentId,String level){
        List<GoodsCat> ctList = goodsCatMapper.searchGoodsCatListByLevel(parentId,level);
        return ctList;
    }


    public GoodsCat searchGoodsCatById(String catId){
        return goodsCatMapper.searchGoodsCatById(catId);
    }

    public List<GoodsCat> searchGoodsCatByParentId(String parentId){
        return goodsCatMapper.searchGoodsCatByParentId(parentId);
    }

    @Override
    public List<GoodsCatVO> selectGoodsByCatId(int pageNum, int pageSize,String catId, int sequenc) {
        return goodsCatMapper.selectGoodsByCatId(pageNum,pageSize,catId,sequenc);
    }

}
