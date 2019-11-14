package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.ChatRoom;
import cn.com.myproject.live.entity.PO.Gift;
import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import cn.com.myproject.netease.VO.ResultChatroomDesc;
import cn.com.myproject.netease.VO.chatroom.IMRequestAddrReturnVO;
import cn.com.myproject.netease.VO.chatroom.IMRequestAddrVO;
import cn.com.myproject.netease.VO.chatroom.IMSendMsgVO;
import net.bytebuddy.asm.Advice;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/27.
 */
@FeignClient(name="admin-api-provide",url = "${feignclient.url}")
public interface IChatRoomService {

    @GetMapping("/chatroom/getList")
    List<ChatRoom> getList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize") int pageSize);

    /**
     *请求聊天室地址
     * */
    @PostMapping("/chatroom/requestAddr")
    IMRequestAddrReturnVO requestAddr(@RequestBody IMRequestAddrVO vo);

    /**
     * 主播直播推流相关信息
     * @param userId
     * @return
     */
    @GetMapping("/liveRoom/hostEntrance")
    public LiveRoom  hostEntrance(@RequestParam("userId") String userId);

    /**
     *  记录主播直播类型
     * @param map
     * @return
     */
    @PostMapping("/liveRoom/updateLiveRoomType")
    public int updateLiveRoomType(@RequestBody Map<String,Object> map);


    /**
     * 观众获取拉流信息相关信息
     * @param roomId
     * @return
     */
    @GetMapping("/liveRoom/spectatorByRoomId")
    public LiveRoom  spectatorByRoomId(@RequestParam("roomId") String roomId);

    /**
     * 根据礼物ID获取礼物信息
     * @param giftId
     * @return
     */
    @GetMapping("/liveRoom/getGiftByGiftId")
    public Gift getGiftByGiftId(@RequestParam("giftId") String giftId);

    /**
     * 礼物发送日志记录
     * @param userId
     * @param teacherId
     * @param giftId
     * @param giftNum
     * @return
     */
    @GetMapping("/liveRoom/gifBrushLog")
    public  int  gifBrushLog(@RequestParam("userId") String userId, @RequestParam("teacherId")  String teacherId,@RequestParam("giftId")  String giftId,@RequestParam("giftNum")  String giftNum,@RequestParam("imRoomId") String imRoomId );

    /**
     * 礼物总榜单Top10
     * @return
     */
    @PostMapping("/liveRoom/giftTotalTopTenList")
    public List<GiftCollectionStatistics> giftTotalTopTenList(@RequestParam("imRoomId") String imRoomId);

    /**
     * 礼物日榜单Top10
     * @return
     */
    @PostMapping("/liveRoom/giftEveryDayTopTenList")
    public List<GiftCollectionStatistics>  giftEveryDayTopTenList(@RequestParam("imRoomId") String imRoomId);
}
