package cn.com.myproject.external;

import cn.com.myproject.live.entity.PO.Gift;
import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import cn.com.myproject.live.service.ILiveRoomService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*
 * Created by LeiJia on 2017-08-23
 * desc：直播间服务控制器类
 */
@RestController
@RequestMapping("/liveRoom")
public class LiveRoomController {

    @Autowired
    private ILiveRoomService iLiveRoomService;

    @PostMapping("/getLiveRoomsPage")
    public PageInfo<Map<String,Object>> getLiveRoomsPage(@RequestBody Map<String,Object> map){
        PageInfo<Map<String,Object>> list = iLiveRoomService.getLiveRoomsPage(map);
        return list;
    }
    @PostMapping("/updateLiveRoom")
    public int updateLiveRoom(@RequestBody  Map<String,Object> map){
        return iLiveRoomService.updateLiveRoom(map);
    }
    @PostMapping("/updateLiveRoomType")
    public int updateLiveRoomType(@RequestBody  Map<String,Object> map){
        return iLiveRoomService.updateLiveRoomType(map);
    }

    @PostMapping("/insertLiveRoom")
    public int insertLiveRoom(@RequestBody  Map<String,Object> map){
        return iLiveRoomService.create(map);
    }

    @PostMapping("/selectLiveRoomByRoomId")
    public Map<String,Object> selectLiveRoomByRoomId( String roomId){
        return iLiveRoomService.selectLiveRoomByRoomId(roomId);
    }

    @GetMapping("/hostEntrance")
    public LiveRoom hostEntrance(String userId){
        return iLiveRoomService.hostEntrance(userId);
    }


    @GetMapping("/spectatorByRoomId")
    public LiveRoom spectatorByRoomId(String roomId){
        return iLiveRoomService.spectatorByRoomId(roomId);
    }

    @GetMapping("/getGiftByGiftId")
    public Gift getGiftByGiftId(@RequestParam("giftId") String giftId){
        return iLiveRoomService.getGiftByGiftId(giftId);
    }

    @GetMapping("/gifBrushLog")
    public  int  gifBrushLog(@RequestParam("userId") String userId, @RequestParam("teacherId")  String teacherId,@RequestParam("giftId")  String giftId,@RequestParam("giftNum")  String giftNum ,@RequestParam("imRoomId") String imRoomId ){
        return iLiveRoomService.gifBrushLog(userId,teacherId,giftId,giftNum ,imRoomId );
    }

    @PostMapping("/selectTeacherLiveRoom")
    public LiveRoom selectTeacherLiveRoom(@RequestBody LiveRoom liveRoom){
        return iLiveRoomService.selectTeacherLiveRoom(liveRoom);
    }


    @PostMapping("/giftTotalTopTenList")
    public List<GiftCollectionStatistics> giftTotalTopTenList(@RequestParam("imRoomId") String imRoomId){
        return this.iLiveRoomService.giftTotalTopTenList(imRoomId);
    }

    @PostMapping("/giftEveryDayTopTenList")
    public List<GiftCollectionStatistics>  giftEveryDayTopTenList(@RequestParam("imRoomId") String imRoomId){
        return iLiveRoomService.giftEveryDayTopTenList(imRoomId);
    }

}