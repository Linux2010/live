package cn.com.myproject.external.admincon;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import cn.com.myproject.admincon.mapper.ProfitShareRelationMapper;
import cn.com.myproject.admincon.service.impl.ProfitShareRelationService;
import cn.com.myproject.admincon.service.impl.ProfitShareSettingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.15
 */
@RestController
@RequestMapping("/profitsharerelation")
public class ProfitShareRelationController {

    @Autowired
    private ProfitShareRelationService profitShareRelationService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String relationId){
        return profitShareRelationService.deleteByPrimaryKey(relationId);
    };

    @PostMapping("/insert")
    int insert(@RequestBody ProfitShareRelation record){
        record.setRelationId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        return profitShareRelationService.insert(record);
    };
    @PostMapping("/insertParentProfitShareSelective")
    public int insertParentProfitShareSelective(@RequestBody ProfitShareRelation record){
        return profitShareRelationService.insertParentProfitShareSelective(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody ProfitShareRelation record){
        record.setRelationId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        return profitShareRelationService.insertSelective(record);
    };

    @GetMapping("/selectByPrimaryKey")
    ProfitShareRelation selectByPrimaryKey(String relationId){
        return profitShareRelationService.selectByPrimaryKey(relationId);
    };

    @GetMapping("/selectByUserId")
    ProfitShareRelation selectByUserId(String userId){
        return profitShareRelationService.selectByUserId(userId);
    }

    @GetMapping("/selectMinusProfitRalationByUserId")
    ProfitShareRelation selectMinusProfitRalationByUserId(String userId){
        return profitShareRelationService.selectMinusProfitRalationByUserId(userId);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody ProfitShareRelation record){
        return profitShareRelationService.updateByPrimaryKeySelective(record);
    };

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody ProfitShareRelation record){
        return profitShareRelationService.updateByPrimaryKey(record);
    };

    @GetMapping("/getPage")
    PageInfo<ProfitShareRelationVO> getPage(int pageNum, int pageSize){
        return profitShareRelationService.getPage(pageNum,pageSize);
    };

    @PostMapping("/getRelationPage")
    PageInfo<ProfitShareRelationVO> getRelationPage(@RequestBody Map<String, Object> map){
        return profitShareRelationService.getRelationPage(Integer.valueOf((Integer) map.get("pageNum")).intValue(),Integer.valueOf((Integer) map.get("pageSize")).intValue(),map);
    };

    @GetMapping("/getRelationU")
    List<ProfitShareRelation> getRelationU(String userId){
        return profitShareRelationService.getRelationU(userId);
    };

    @GetMapping("/getRelationD")
    List<ProfitShareRelation> getRelationD(String userId){
        return profitShareRelationService.getRelationD(userId);
    };

    @PostMapping("/getRelationListByMap")
    List<ProfitShareRelation> getRelationListByMap(@RequestBody Map<String,Object> map){
        return profitShareRelationService.getRelationListByMap(map);
    };

    @GetMapping("/getUpLevelUser")
    ProfitShareRelation getUpLevelUser( String userId){
        return profitShareRelationService.getUpLevelUser(userId);
    }

    @GetMapping("/getSecondLevelUser")
    List<ProfitShareRelation> getSecondLevelUser(int pageNum,int pageSize,String userId){
        return profitShareRelationService.getSecondLevelUser(pageNum,pageSize,userId);
    }

    @GetMapping("/getThreeLevelUser")
    List<ProfitShareRelation> getThreeLevelUser(int pageNum,int pageSize,String userId){
        return profitShareRelationService.getThreeLevelUser(pageNum, pageSize, userId);
    }

    @GetMapping("/addProfitShareRelation")
    int addProfitShareRelation(String referrerId,String registerId){
        return profitShareRelationService.addProfitShareRelation(referrerId,registerId);
    }

    @GetMapping("/getThreeLevelUserCount")
    Integer getThreeLevelUserCount(String userId){
        return profitShareRelationService.getThreeLevelUserCount(userId);
    }

    @GetMapping("/getSecondLevelUserAll")
    List<ProfitShareRelation> getSecondLevelUserAll(String userId){
        return profitShareRelationService.getSecondLevelUserAll(userId);
    }

    @GetMapping("/getSecondLevelUserMap")
    Map<String,Object> getSecondLevelUserMap(int pageNum,int pageSize,String userId){
        return profitShareRelationService.getSecondLevelUserMap(pageNum,pageSize,userId);
    }


    @PostMapping("/updateParentRaltion")
    int updateParentRaltion(@RequestBody  ProfitShareRelation profitShareRelation){
        return profitShareRelationService.updateParentRaltion(profitShareRelation);
    }

}
