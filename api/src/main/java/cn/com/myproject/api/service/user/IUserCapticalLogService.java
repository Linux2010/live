package cn.com.myproject.api.service.user;

import cn.com.myproject.user.entity.PO.UserCapitalLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IUserCapticalLogService {

    @GetMapping("/usercapticallog/deleteByPrimaryKey")
    int deleteByPrimaryKey(String bid);

    @PostMapping("/usercapticallog/insert")
    int insert(@RequestBody UserCapitalLog record);

    @PostMapping("/usercapitallog/insertSelective")
    int insertSelective(@RequestBody UserCapitalLog record);

    @GetMapping("/usercapticallog/deleteByPrimaryKey")
    UserCapitalLog selectByPrimaryKey(String bid);

    @PostMapping("/usercapticallog/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserCapitalLog record);

    @PostMapping("/usercapticallog/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserCapitalLog record);

    @GetMapping("/usercapitallog/selectTopUpList")
    List<UserCapitalLog> selectTopUpList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("userId")  String userId);

    @GetMapping("/usercapitallog/selectWithList")
    List<UserCapitalLog> selectWithList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("userId") String userId);

    @GetMapping("/usercapitallog/selectAllList")
    List<UserCapitalLog> selectAllList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("userId") String userId);
}
