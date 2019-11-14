package cn.com.myproject.api.service;

import cn.com.myproject.user.entity.PO.Information;
import cn.com.myproject.user.entity.PO.PointRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/28.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")

 public interface IInformationservice {

 @GetMapping("/inf/getAll")
 List<Information> getAll() ;

 @PostMapping("/inf/addApi")
 void addApi(@RequestBody Information information);

 @PostMapping("/inf/selectByUserId")
 PointRecord selectinformationById(@RequestParam("userId") String userId, @RequestParam("informationId") String informationId);

 @PostMapping("/inf/fselectById")
 Information selectById(@RequestParam("informationId") String informationId,@RequestParam("userId") String userId);

 @PostMapping("/inf/addPointRecord")
 void addPointRecord(@RequestBody PointRecord pointRecord);


 @GetMapping("/inf/searchInformationList")
 public List<Information> searchInfoList(@RequestParam("pageNum") int pageNum,
                                     @RequestParam("pageSize") int pageSize,
                                     @RequestParam("title") String title);
@GetMapping("/inf/selectByInfoId")
  Information selectByInfoId(@RequestParam("informationId") String informationId);

 @PostMapping("/inf/updateAgreeNum")
 int updateAgreeNum(@RequestBody Information information);


 @GetMapping("/inf/selectByInfoId")
 Information selectInfoById(@RequestParam("informationId") String informationId);


 @GetMapping("/inf/selectInformById")
 Information selectInformById(@RequestParam("informationId") String informationId);


}



