package cn.com.myproject.service;

import cn.com.myproject.user.entity.PO.ShortVideo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LSG on 2017/9/26 0026.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IShortVideoService {

    @GetMapping("/shortVideo/getPage")
    PageInfo<ShortVideo> getPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                                 @RequestParam("keyword") String keyword);

    @PostMapping("/shortVideo/addVideo")
    int addVideo(@RequestBody ShortVideo shortVideo);

    @PostMapping("/shortVideo/selectByStatus")
    List<ShortVideo> selectByStatus(@RequestParam("status") int status);

    @PostMapping("/shortVideo/selectById")
    ShortVideo selectById(@RequestParam("videoId") String videoId);

    @PostMapping("/shortVideo/updateVideo")
    int updateVideo(@RequestBody ShortVideo shortVideo);

    @PostMapping("/shortVideo/deleteVideo")
    int deleteVideo(@RequestParam("videoId") String videoId);

    @PostMapping("/shortVideo/updateStatus")
    int updateStatus(@RequestBody ShortVideo shortVideo);

    @PostMapping("/shortVideo/selectByType")
    ShortVideo selectByType(@RequestParam("videoType") int videoType);
}
