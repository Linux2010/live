package cn.com.myproject.service.admincon;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by CQC on 2017.8.15.
 */
@FeignClient(value = "admin-api-provide",url = "${feignclient.url}")
public interface IProfitShareSettingService {

    @GetMapping("/profitshareset/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("setId") String setId);

    @PostMapping("/profitshareset/insert")
    int insert(@RequestBody ProfitShareSetting record);

    @PostMapping("/profitshareset/insertSelective")
    int insertSelective(@RequestBody ProfitShareSetting record);

    @GetMapping("/profitshareset/selectByPrimaryKey")
    ProfitShareSetting selectByPrimaryKey(@RequestParam("setId") String setId);

    @GetMapping("/profitshareset/getPage")
    PageInfo<ProfitShareSettingVO> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @PostMapping("/profitshareset/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody ProfitShareSetting record);

    @PostMapping("/profitshareset/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody ProfitShareSetting record);

}
