package cn.com.myproject.service.customer;

import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.UserFeedbackVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by CQC on 2017.8.18.
 */
@FeignClient(value = "admin-api-provide",url = "${feignclient.url}")
public interface IUserFeedbackService {

    @GetMapping("/userfeedback/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("businessId") String businessId);

    @PostMapping("/userfeedback/insert")
    int insert(@RequestBody UserFeedback record);

    @PostMapping("/userfeedback/insertSelective")
    int insertSelective(@RequestBody UserFeedback record);

    @GetMapping("/userfeedback/selectByPrimaryKey")
    UserFeedback selectByPrimaryKey(@RequestParam("businessId") String businessId);

    @GetMapping("/userfeedback/getPage")
    PageInfo<UserFeedbackVO> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") String keyword);

    @PostMapping("/userfeedback/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserFeedback record);

    @PostMapping("/userfeedback/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody UserFeedback record);

}
