package cn.com.myproject.api.service;

import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.goods.entity.VO.GoodsCatVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by jyp on 2017/9/18 0018.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IGoodsCatService {

    @GetMapping("/goodsCat/searchGoodsCatByParentId")
    public List<GoodsCat> searchGoodsCatByParentId(@RequestParam("parentId") String parentId);

    @GetMapping("/goodsCat/selectGoodsByCatId")
    public List<GoodsCatVO> selectGoodsByCatId(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("catId") String catId, @RequestParam("sequenc") int sequenc);

}
