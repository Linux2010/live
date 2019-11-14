package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.UserSignIn;
import cn.com.myproject.user.service.IUserSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@RestController
@RequestMapping("/usersignin")
public class UserSignInController {

    @Autowired
    private IUserSignInService userSignInService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String bid){
        return userSignInService.deleteByPrimaryKey(bid);
    }

    @PostMapping("/insert")
    int insert(@RequestBody  UserSignIn record){
        record.setSignId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        return userSignInService.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody UserSignIn record){
        record.setSignId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        record.setVersion(1);
        record.setStatus((short)1);
        return userSignInService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    UserSignIn selectByPrimaryKey(String bid){
        return userSignInService.selectByPrimaryKey(bid);
    }

    @GetMapping("/selectByUserId")
    UserSignIn selectByUserId(String userId){
        return userSignInService.selectByUserId(userId);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserSignIn record){
        return userSignInService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserSignIn record){
        return userSignInService.updateByPrimaryKey(record);
    }


}
