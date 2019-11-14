package cn.com.myproject.service.admincon;


import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by CQC on 2017.8.15.
 */
@FeignClient(value = "admin-api-provide",url = "${feignclient.url}")
public interface IProfitShareRelationService {

    @GetMapping("/profitsharerelation/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("relationId") String relationId);

    @PostMapping("/profitsharerelation/insert")
    int insert(@RequestBody ProfitShareRelation record);

    @PostMapping("/profitsharerelation/insertSelective")
    int insertSelective(@RequestBody ProfitShareRelation record);

    @PostMapping("/profitsharerelation/insertParentProfitShareSelective")
    int insertParentProfitShareSelective(@RequestBody ProfitShareRelation record);

    @GetMapping("/profitsharerelation/selectByPrimaryKey")
    ProfitShareRelation selectByPrimaryKey(@RequestParam("relationId") String relationId);

    @GetMapping("/profitsharerelation/getPage")
    PageInfo<ProfitShareRelationVO> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/profitsharerelation/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody ProfitShareRelation record);

    @PostMapping("/profitsharerelation/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody ProfitShareRelation record);

    @PostMapping("/profitsharerelation/getRelationPage")
    PageInfo<ProfitShareRelationVO> getRelationPage(@RequestBody Map<String,Object> map);

    @GetMapping("/profitsharerelation/getRelationU")
    List<ProfitShareRelation> getRelationU(@RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getRelationD")
    List<ProfitShareRelation> getRelationD(@RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/getRelationListByMap")
    List<ProfitShareRelation> getRelationListByMap(@RequestBody Map<String,Object> filterMap);

    @GetMapping("/profitsharerelation/selectByUserId")
    ProfitShareRelation selectByUserId(@RequestParam("userId") String userId);

    @GetMapping("/profitsharerelation/selectMinusProfitRalationByUserId")
    ProfitShareRelation selectMinusProfitRalationByUserId(@RequestParam("userId") String userId);

    @PostMapping("/profitsharerelation/updateParentRaltion")
    int updateParentRaltion(@RequestBody  ProfitShareRelation profitShareRelation);
}
