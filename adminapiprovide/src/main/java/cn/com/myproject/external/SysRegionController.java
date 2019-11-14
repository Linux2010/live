package cn.com.myproject.external;

import cn.com.myproject.sysuser.entity.PO.SysRegion;
import cn.com.myproject.sysuser.service.ISysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@RestController
@RequestMapping("/sysregion")
public class SysRegionController {

    @Autowired
    private ISysRegionService sysRegionService;

    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String regionId){
        return sysRegionService.deleteByPrimaryKey(regionId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody SysRegion record){
        return sysRegionService.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody SysRegion record){
        return sysRegionService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    public SysRegion selectByPrimaryKey(String regionId){
        return sysRegionService.selectByPrimaryKey(regionId);
    }

    @GetMapping("/selectByPid")
    public List<SysRegion> selectByPid(String pid){
        return sysRegionService.selectByPid(pid);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody SysRegion record){
        return sysRegionService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody SysRegion record){
        return sysRegionService.updateByPrimaryKey(record);
    }

    @GetMapping("/select_countries_code")
    public List<SysRegion> select_countries_code(){
        return sysRegionService.select_countries_code();
    }

}
