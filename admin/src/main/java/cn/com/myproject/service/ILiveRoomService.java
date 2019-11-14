package cn.com.myproject.service;


import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * Created by LeiJia on 2017/8/23.
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ILiveRoomService {

    @PostMapping("/liveRoom/getLiveRoomsPage")
    PageInfo<Map<String,Object>> getLiveRoomsPage(@RequestBody Map<String, Object> map);

    @PostMapping("/liveRoom/updateLiveRoom")
    int  updateLiveRoom(@RequestBody Map<String, String> map);

    @PostMapping("/liveRoom/insertLiveRoom")
    int insertLiveRoom(@RequestBody Map<String, String> map);

    @PostMapping("/liveRoom/selectLiveRoomByRoomId")
    Map<String,Object> selectLiveRoomByRoomId(@RequestParam("roomId") String roomId);

    @PostMapping("/liveRoom/selectTeacherLiveRoom")
    LiveRoom selectTeacherLiveRoom(@RequestBody LiveRoom liveRoom);

}
