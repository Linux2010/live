package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.SpecValues;
import cn.com.myproject.goods.service.ISpecValueService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/*
 * Created by LeiJia on 2017-09-14
 * desc：规格服务控制器类
 */
@RestController
@RequestMapping("/specValues")
public class SpecValueController {

    @Autowired
    private ISpecValueService specValueService;

    @PostMapping("/addSpecValues")
    public boolean addSpecValues(@RequestBody SpecValues spec){
        if(StringUtils.isNotBlank(spec.getValuesName())){
            String goods_spec_values_id = UUID.randomUUID().toString().replace("-", "");
            spec.setGoodsSpecValuesId(goods_spec_values_id);// 设置教师分类的ID
            spec.setCreateTime(new Date().getTime());// 默认当前时间
            spec.setVersion(1);// 默认第一版本
            spec.setStatus((short)1);
        }
        return specValueService.addSpecValues(spec);
    }

    @PostMapping("/removeSpecValues")
    public boolean removeSpecValues(String goodsSpecValuesId){
        return specValueService.removeSpecValues(goodsSpecValuesId);
    }

    @PostMapping("/modifySpecValues")
    public boolean modifySpecValues(@RequestBody SpecValues spec){
        return specValueService.modifySpecValues(spec);
    }

    @GetMapping("/searchSpecValuesList")
    public PageInfo<SpecValues> searchSpecValuesList(int pageNum,int pageSize,String valuesName,String specId){
        return specValueService.searchSpecValuesList(pageNum,pageSize,valuesName,specId);
    }

    @GetMapping("searchSpecValuesById")
    public SpecValues searchSpecValuesById(String goodsSpecValuesId){
        return specValueService.searchSpecValuesById(goodsSpecValuesId);
    }


}