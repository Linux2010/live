package cn.com.myproject.api.service.user;

import cn.com.myproject.live.entity.PO.UserGroupCard;
import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LeiJia on 2017/7/27.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IUserGroupCardService {

    @PostMapping("/userGroupCard/getUserGroupCard")
    public UserGroupCardVO getUserGroupCard(@RequestBody UserGroupCard userGroupCard);

    @PostMapping("/userGroupCard/activationGroupCard")
    public boolean activationGroupCard(@RequestBody UserGroupCardVO userGroupCardVO);

}