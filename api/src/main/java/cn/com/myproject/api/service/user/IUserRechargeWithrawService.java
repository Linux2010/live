package cn.com.myproject.api.service.user;

import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther CQC
 * @create 2017.8.31
 */
@FeignClient(name = "admin-api-provide" , url = "${feignclient.url}")
public interface IUserRechargeWithrawService {

    @GetMapping("/userrechargewithraw/deleteByPrimaryKey")
    int deleteByPrimaryKey(String rwId);

    @PostMapping("/userrechargewithraw/insert")
    int insert(@RequestBody UserRechargeWithraw record);

    @PostMapping("/userrechargewithraw/insertSelective")
    int insertSelective(@RequestBody UserRechargeWithraw record);

    @PostMapping("/userrechargewithraw/insertSelectiveReturnUid")
    String insertSelectiveReturnUid(@RequestBody UserRechargeWithraw record);

    @GetMapping("/userrechargewithraw/selectByPrimaryKey")
    UserRechargeWithraw selectByPrimaryKey(@RequestParam("rwId") String rwId);

    @PostMapping("/userrechargewithraw/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserRechargeWithraw record);

    @PostMapping("/userrechargewithraw/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserRechargeWithraw record);

    @GetMapping("/userrechargewithraw/setPayFinishRecharge")
    int setPayFinishRecharge(@RequestParam("rwId") String rwId,@RequestParam("transactionId") String transactionId);


}
