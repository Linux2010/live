package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.Information;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
/**
 * Created by 李延超 on 2017/8/18.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")

public interface IInformationService {
    @PostMapping("/inf/addInfo")
    int  addInfo(@RequestBody Information information);
    @PostMapping("/inf/fgs")
        void delInformation(@RequestParam("informationId") String informationId );

    @GetMapping("/inf/selectByInfoId")
    Information selectinformation(@RequestParam("informationId") String informationId);

    @GetMapping("/inf/getAll")
    List<Information>getAll();

    @PostMapping("/inf/updateContent")
    int updateContent(@RequestBody Information information);

    @PostMapping("/inf/updateTitle")
    int updateTitle(@RequestBody Information information);

    @GetMapping("/inf/searchInfoList")
    PageInfo<Information> searchInfoList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam("title") String title);

}