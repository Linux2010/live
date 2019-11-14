package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.UserAddress;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by JYP on 2017/9/13 0013.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IUserAdressService {

    @PostMapping("/user_address/list")
    public List<UserAddress> list(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("userId") String userId);

    @PostMapping("/user_address/add_address")
    public void add_address(@RequestBody UserAddress userAddress);

    @PostMapping("/user_address/update_address")
    public void update_address(@RequestBody UserAddress userAddress);

    @PostMapping("/user_address/del_address")
    public void del_address(@RequestParam("addId") String addId);

    @PostMapping("/user_address/selectById")
    public UserAddress selectById(@RequestParam("addId") String addId);

    @PostMapping("/user_address/selectDefaultAdd")
    public UserAddress selectDefaultAdd(@RequestParam("userId") String userId);

    @PostMapping("/user_address/updateChecked")
    public void updateChecked(@RequestParam("addId") String addId);



}
