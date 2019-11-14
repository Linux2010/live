package cn.com.myproject.service;

import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IUserGroupCardService {

    @PostMapping("/userGroupCard/addUgc")
    boolean addUgc(@RequestParam("cardNum") int cardNum,
                   @RequestParam("cardType") int cardType,
                   @RequestParam("userId") String userId);

    @PostMapping("/userGroupCard/removeUgcById")
    boolean removeUgcById(@RequestParam("id") int id);

    @PostMapping("/userGroupCard/searchUgcList")
    PageInfo<UserGroupCardVO> searchUgcList(@RequestParam("pageNum") int pageNum,
                                            @RequestParam("pageSize") int pageSize,
                                            @RequestParam("userId") String userId,
                                            @RequestParam("cardNo") String cardNo,
                                            @RequestParam("cardType") int cardType,
                                            @RequestParam("status") int status);

    @PostMapping("/userGroupCard/searchExportUgcList")
    List<UserGroupCardVO> searchExportUgcList(@RequestParam("userId") String userId,
                                              @RequestParam("cardNo") String cardNo,
                                              @RequestParam("cardType") int cardType,
                                              @RequestParam("status") int status);

}