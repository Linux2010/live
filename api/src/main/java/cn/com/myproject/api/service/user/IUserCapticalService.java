package cn.com.myproject.api.service.user;


import cn.com.myproject.user.entity.PO.UserCapital;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "admin-api-provide" , url = "${feignclient.url}")
public interface IUserCapticalService {

    @GetMapping("/usercapital/deleteByPrimaryKey")
    int deleteByPrimaryKey(String bid);

    @PostMapping("/usercapital/insert")
    int insert(@RequestBody UserCapital record);

    @PostMapping("/usercapital/insertSelective")
    int insertSelective(@RequestBody UserCapital record);

    @GetMapping("/usercapital/selectByPrimaryKey")
    UserCapital selectByPrimaryKey(String bid);

    @GetMapping("/usercapital/selectByUserId")
    UserCapital selectByUserId(@RequestParam("userId") String userId);

    @PostMapping("/usercapital/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserCapital record);

    @PostMapping("/usercapital/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserCapital record);

}
