package cn.com.myproject.api.service.admincon;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import cn.com.myproject.user.entity.PO.Advertise;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 *
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IProfitShareRelationService {

    @GetMapping("/profitsharerelation/getUpLevelUser")
    public ProfitShareRelation getUpLevelUser(@RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getSecondLevelUser")
    public List<ProfitShareRelation> getSecondLevelUser(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getThreeLevelUser")
    public List<ProfitShareRelation> getThreeLevelUser(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getThreeLevelUserCount")
    public Integer getThreeLevelUserCount(@RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getSecondLevelUserAll")
    public List<ProfitShareRelation> getSecondLevelUserAll(@RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getSecondLevelUserMap")
    public Map<String,Object> getSecondLevelUserMap(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("userId") String userId);

}
