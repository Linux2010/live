package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import cn.com.myproject.goods.service.IGoodsCatService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by LeiJia on 2017-09-13
 * desc：商品分类服务控制器类
 */
@RestController
@RequestMapping("/goodsCat")
public class GoodsCatController {

    @Autowired
    private IGoodsCatService goodsCatService;

    @PostMapping("/addGoodsCat")
    public boolean addGoodsCat(@RequestBody GoodsCat goodsCat){
        if(StringUtils.isNotBlank(goodsCat.getCatName())){
            String catId = UUID.randomUUID().toString().replace("-", "");
            goodsCat.setCatId(catId);// 设置教师分类的ID
            goodsCat.setCreateTime(new Date().getTime());// 默认当前时间
            goodsCat.setVersion(1);// 默认第一版本
            goodsCat.setStatus((short)1);
        }
        return goodsCatService.addGoodsCat(goodsCat);
    }

    @PostMapping("/removeGoodsCat")
    public boolean removeGoodsCat(String catId){
        return goodsCatService.removeGoodsCat(catId);
    }

    @PostMapping("/modifyGoodsCat")
    public boolean modifyGoodsCat(@RequestBody GoodsCat goodsCat){
        return goodsCatService.modifyGoodsCat(goodsCat);
    }

    @GetMapping("/searchGoodsCatList")
    public PageInfo<GoodsCat> searchGoodsCatList(int pageNum,int pageSize,String catName,String parentId){
        return goodsCatService.searchGoodsCatList(pageNum,pageSize,catName,parentId);
    }

    @GetMapping("searchGoodsCatById")
    public GoodsCat searchGoodsCatById(String catId){
        return goodsCatService.searchGoodsCatById(catId);
    }

    @GetMapping("/searchGoodsCatListByLevel")
    public List<GoodsCat> searchGoodsCatListByLevel(String parentId ,String level){
        return goodsCatService.searchGoodsCatListByLevel(parentId,level);
    }
    @GetMapping("/searchGoodsCatByParentId")
    public List<GoodsCat> searchGoodsCatByParentId(String parentId){
        return  goodsCatService.searchGoodsCatByParentId(parentId);
    }

    @GetMapping("/selectGoodsByCatId")
    public List<GoodsCatVO> selectGoodsByCatId(int pageNum, int pageSize, String catId, int sequenc){
        return goodsCatService.selectGoodsByCatId(pageNum,pageSize,catId,sequenc);
    }

}