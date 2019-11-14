package cn.com.myproject.external.admincon;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import cn.com.myproject.admincon.service.impl.ProfitShareSettingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.15
 */
@RestController
@RequestMapping("/profitshareset")
public class ProfitShareSettingController {

    @Autowired
    private ProfitShareSettingService profitShareSettingService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String setId){
        return profitShareSettingService.deleteByPrimaryKey(setId);
    }

    @PostMapping("/insert")
    int insert(@RequestBody ProfitShareSetting record){
        record.setSetId(UUID.randomUUID().toString().replace("-",""));
        return profitShareSettingService.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody ProfitShareSetting record){
        record.setSetId(UUID.randomUUID().toString().replace("-",""));
        return profitShareSettingService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    ProfitShareSetting selectByPrimaryKey(String setId){
        return profitShareSettingService.selectByPrimaryKey(setId);
    }

    @GetMapping("/getPage")
    PageInfo<ProfitShareSettingVO> getPage(int pageNum, int pageSize){return profitShareSettingService.getPage(pageNum,pageSize); }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody ProfitShareSetting record){
        return profitShareSettingService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody ProfitShareSetting record){
        return profitShareSettingService.updateByPrimaryKey(record);
    }


}
