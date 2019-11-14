package cn.com.myproject.service.goods;
import cn.com.myproject.goods.entity.PO.GoodsCat;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-13
 * desc：商品分类Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IGoodsCatService {

    @PostMapping("/goodsCat/addGoodsCat")
    public boolean addGoodsCat(@RequestBody GoodsCat goodsCat);

    @PostMapping("/goodsCat/removeGoodsCat")
    public boolean removeGoodsCat(@RequestParam("catId") String catId);

    @PostMapping("/goodsCat/modifyGoodsCat")
    public boolean modifyGoodsCat(@RequestBody GoodsCat goodsCat);

    @GetMapping("/goodsCat/searchGoodsCatList")
    public PageInfo<GoodsCat> searchGoodsCatList(@RequestParam("pageNum") int pageNum,
                                                 @RequestParam("pageSize") int pageSize,
                                                 @RequestParam("catName") String catName,
                                                 @RequestParam("parentId") String parentId);

    @GetMapping("/goodsCat/searchGoodsCatById")
    public GoodsCat searchGoodsCatById(@RequestParam("catId") String catId);

    @GetMapping("/goodsCat/searchGoodsCatByParentId")
    public List<GoodsCat> searchGoodsCatByParentId(@RequestParam("parentId") String parentId);

}