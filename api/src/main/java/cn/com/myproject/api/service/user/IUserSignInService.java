package cn.com.myproject.api.service.user;

import cn.com.myproject.user.entity.PO.UserSignIn;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@FeignClient(name = "admin-api-provide" , url = "${feignclient.url}")
public interface IUserSignInService {

    @GetMapping("/usersignin/deleteByPrimaryKey")
    int deleteByPrimaryKey(String bid);

    @PostMapping("/usersignin/insert")
    int insert(@RequestBody UserSignIn record);

    @PostMapping("/usersignin/insertSelective")
    int insertSelective(@RequestBody UserSignIn record);

    @GetMapping("/usersignin/selectByPrimaryKey")
    UserSignIn selectByPrimaryKey(@RequestParam("bid") String bid);

    @GetMapping("/usersignin/selectByUserId")
    UserSignIn selectByUserId(@RequestParam("userId") String userId);

    @PostMapping("/usersignin/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserSignIn record);

    @PostMapping("/usersignin/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserSignIn record);

}
