package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.service.IUserCapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@RestController
@RequestMapping("/usercapital")
public class UserCapitalController {

    @Autowired
    private IUserCapitalService userCapitalService;

    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String bid){
        return userCapitalService.deleteByPrimaryKey(bid);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody UserCapital record){
        return userCapitalService.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody UserCapital record){
        return userCapitalService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    UserCapital selectByPrimaryKey(String bid){
        return userCapitalService.selectByPrimaryKey(bid);
    }

    @GetMapping("/selectByUserId")
    UserCapital selectByUserId(String userId){
        return userCapitalService.selectByUserId(userId);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserCapital record){
        return userCapitalService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserCapital record){
        return userCapitalService.updateByPrimaryKey(record);
    }

    @PostMapping("/getUserCapitalList")
    List<UserCapital> getUserCapitalList(UserCapital record){
        return userCapitalService.getUserCapitalList(record);
    }

}
