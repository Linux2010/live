package cn.com.myproject.external.goods;

import cn.com.myproject.goods.entity.PO.GoodsSpec;
import cn.com.myproject.goods.entity.PO.Spec;
import cn.com.myproject.goods.service.ISpecService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by LeiJia on 2017-09-14
 * desc：规格服务控制器类
 */
@RestController
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private ISpecService specService;

    @PostMapping("/addSpec")
    public boolean addSpec(@RequestBody Spec spec){
        if(StringUtils.isNotBlank(spec.getSpecName())){
            String specId = UUID.randomUUID().toString().replace("-", "");
            spec.setSpecId(specId);// 设置教师分类的ID
            spec.setCreateTime(new Date().getTime());// 默认当前时间
            spec.setVersion(1);// 默认第一版本
            spec.setStatus((short)1);
        }
        return specService.addSpec(spec);
    }

    @PostMapping("/removeSpec")
    public boolean removeSpec(String specId){
        return specService.removeSpec(specId);
    }

    @PostMapping("/modifySpec")
    public boolean modifySpec(@RequestBody Spec spec){
        return specService.modifySpec(spec);
    }

    @GetMapping("/searchSpecList")
    public PageInfo<Spec> searchSpecList(int pageNum,int pageSize,String specName){
        return specService.searchSpecList(pageNum,pageSize,specName);
    }

    @GetMapping("/getSpecList")
    public List<Spec> getSpecList(){
        return specService.getSpecList();
    }

    @GetMapping("/searchSpecById")
    public Spec searchSpecById(String specId){
        return specService.searchSpecById(specId);
    }

    @GetMapping("/selectSpecsByGoodsId")
    public List<Spec> selectSpecsByGoodsId(String goodsId){
        return specService.selectSpecsByGoodsId(goodsId);
    }

    @GetMapping("/selectGoodsSpecsByGoodsId")
    public List<GoodsSpec> selectGoodsSpecsByGoodsId(String goodsId){
        return  specService.selectGoodsSpecsByGoodsId(goodsId);
    }
    @PostMapping("/getGoodsSpecDetail")
    public GoodsSpec getGoodsSpecDetail(@RequestBody  GoodsSpec spec){
        return  specService.getGoodsSpecDetail(spec);
    }
}