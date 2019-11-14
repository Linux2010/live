package cn.com.myproject.service;

import cn.com.myproject.recset.entity.PO.RecSettings;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JYP on 2017/8/17 0017.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IRecSettingsService {

    @GetMapping("/recsetting/gettea")
    PageInfo<RecSettings> getTeaPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @GetMapping("/recsetting/getcour")
    PageInfo<RecSettings> getCourPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/recsetting/delcour")
    int delcour(@RequestParam("recSetId") String recSetId);

    @PostMapping("/recsetting/selectByRecSetId")
    RecSettings selectByRecSetId(@RequestParam("recSetId") String recSetId);

    @GetMapping("/recsetting/allCour")
    PageInfo<RecSettings> selectAllCour(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/recsetting/insert")
    void insert(@RequestBody RecSettings recSettings);

    @PostMapping("/recsetting/updateSort")
    void updateSort(@RequestBody RecSettings recSettings);

    @GetMapping("/recsetting/allTea")
    PageInfo<RecSettings> selectAllTea(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @GetMapping("/recsetting/selectGoodCour")
    PageInfo<RecSettings> selectGoodCour(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @GetMapping("/recsetting/goodCourList")
    PageInfo<RecSettings> goodCourList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/recsetting/hasRecByTag")
    PageInfo<RecSettings> hasRecByTag(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("keyword") String keyword);

    @PostMapping("/recsetting/notRecByTag")
    PageInfo<RecSettings> notRecByTag(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("keyword") String keyword);
}
