package cn.com.myproject.api.live.controller;


import cn.com.myproject.api.service.IChatRoomService;
import cn.com.myproject.api.service.ISearchService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.api.wap.BaseController;
import cn.com.myproject.live.entity.PO.ChatRoom;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.Gift;
import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import cn.com.myproject.live.entity.VO.AnchorLiveRoomVO;
import cn.com.myproject.live.entity.VO.SpectatorLiveRoomVO;
import cn.com.myproject.netease.VO.chatroom.IMRequestAddrReturnVO;
import cn.com.myproject.netease.VO.chatroom.IMRequestAddrVO;
import cn.com.myproject.user.entity.Constant;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import io.swagger.annotations.*;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/1.
 */
@RestController
@RequestMapping("/api/chatroom")
@Api(value="聊天室",tags = "直播")
public class ChatroomController extends BaseController {

    @Autowired
    private IChatRoomService chatRoomService;

    @Autowired
    private ISearchService iSearchService;


    @GetMapping("/homeList")
    public Message homeList(){

        List<ChatRoom> list = chatRoomService.getList(1,12);
        Message message = MessageUtils.getSuccess("获取成功");
        message.setData(list);

        return message;
    }

    @ApiOperation(value = "主播直播推流相关信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "登录名(教头用户ID)", required = true, dataType = "String",defaultValue = "fb59bafef3be4b4bba40456686f377d3"),
            @ApiImplicitParam(paramType="query",name = "type", value = "直播类型 -1：未开始；1:音频;2:视频", required = true, dataType = "String",defaultValue = "2")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = AnchorLiveRoomVO.class, responseContainer = "List" ) })
    @PostMapping("/hostEntrance")
    public Message<AnchorLiveRoomVO> hostEntrance(String userId,String type) {
        if( iSearchService.getAPITeacherUserByUserId(userId) == null){
            return MessageUtils.getFail("该教头用户不存在!");
        }
        LiveRoom room = chatRoomService.hostEntrance(userId);
        if(room == null ){
            return MessageUtils.getSuccess("该直播间不存在");
        }
        AnchorLiveRoomVO vo = new AnchorLiveRoomVO();
        vo.setRoomId(room.getImRoomId());
        vo.setName(room.getRoomName());
        vo.setCreator(room.getImCreator());
        vo.setOnlineUserCount(Long.valueOf(room.getOnlineusercount()));
        vo.setInAllMuteMode((short)0);
        vo.setBroadcastUrl(room.getPushurl());
        Map<String ,Object> map = new LinkedHashMap<>();
        map.put("roomId",room.getRoomId());
        map.put("type",type);
        int i = chatRoomService.updateLiveRoomType(map);
        if(i <1 ){
            return MessageUtils.getSuccess("记录直播类型异常");
        }
        Message<AnchorLiveRoomVO> message = MessageUtils.getSuccess("获取成功");
        message.setData(vo);
        return message;
    }

    @PostMapping("/spectator")
    @ApiOperation(value = "观众拉流相关信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "imRoomId", value = "网易聊天室ID", required = true, dataType = "String",defaultValue = "11080454")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = SpectatorLiveRoomVO.class, responseContainer = "List" ) })
    public Message<SpectatorLiveRoomVO> spectator(String imRoomId) {
        LiveRoom room = chatRoomService.spectatorByRoomId(imRoomId);
        if(room == null ){
            return MessageUtils.getSuccess("该直播间不存在");
        }
        SpectatorLiveRoomVO vo = new SpectatorLiveRoomVO();
        vo.setPullUrl(room.getRtmppullurl());
        vo.setType((short)room.getType());
        vo.setRoomId(room.getImRoomId());
        vo.setName(room.getRoomName());
        vo.setOnlineUserCount(Long.valueOf(room.getOnlineusercount()));
        Message<SpectatorLiveRoomVO> message = MessageUtils.getSuccess("获取成功");
        message.setData(vo);
        return message;
    }
    @PostMapping("/roomDetail")
    @ApiOperation(value = "直播间详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "imRoomId", value = "网易聊天室ID", required = true, dataType = "String",defaultValue = "11080454")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = LiveRoom.class, responseContainer = "List" ) })
    public Message<LiveRoom> roomDetail(String imRoomId) {
        LiveRoom room = chatRoomService.spectatorByRoomId(imRoomId);
        if(room == null ){
            return MessageUtils.getSuccess("该直播间不存在");
        }
        Message<LiveRoom> message = MessageUtils.getSuccess("获取成功");
        message.setData(room);
        return message;
    }

    @GetMapping("/requestAddress")
    public Message requestAddress(Long roomid, HttpSession httpSession) {
        IMRequestAddrVO vo = new IMRequestAddrVO();
        vo.setRoomid(roomid);
        vo.setAccid("54525df2196540ef924b6ee1a69658ee");
        IMRequestAddrReturnVO result = chatRoomService.requestAddr(vo);
        if(result.getCode() == 200){
            Message message = MessageUtils.getSuccess("获取成功");
            message.setData(result.getAddr());
            return message;
        }
        return MessageUtils.getFail("获取失败");
    }

    /**
     * 直播-直播课程列-视频或音频课程列表分页查询
     * @param zbType
     * @return
     * @author LeiJia
     */
    @ApiOperation(value = "直播-直播课程列-视频或音频课程列表分页查询", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "zbType", value = "直播类型：sp代表视频，yp代表音频", required = true, dataType = "String",defaultValue = "yp"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "教师ID", required = true, dataType = "String",defaultValue = "594c1664b3364700b64511d23bf4d339")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Course.class, responseContainer = "List" ) })
    @PostMapping("/searchCourses")
    public  Message<List<Course>> searchCourses(String zbType,String userId ) {
        if( iSearchService.getAPITeacherUserByUserId(userId) == null){
            return MessageUtils.getFail("该教头用户不存在!");
        }
        List<Course> courseList = null;
        try{
            courseList =iSearchService.searchLiveCourseList("zbkc","",zbType,userId);
        }catch(Exception e){
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
        Message<List<Course>> message=  MessageUtils.getSuccess("success");
        message.setData(courseList);
        return  message;
    }
    /**
     *直播-发送礼物日志记录
     * @param userId
     * @param imRoomId
     * @param giftId
     * @param giftNum
     * @return
     */
    @ApiOperation(value = "直播-发送礼物日志记录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "会员用户Id", required = true, dataType = "String",defaultValue = "fgdg"),
            @ApiImplicitParam(paramType="query",name = "imRoomId", value = "网易聊天室ID", required = true, dataType = "String",defaultValue = "11080454"),
            @ApiImplicitParam(paramType="query",name = "giftId", value = "礼物ID", required = true, dataType = "String",defaultValue = "0"),
            @ApiImplicitParam(paramType="query",name = "giftNum", value = "礼物数量", required = true, dataType = "String",defaultValue = "1")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Message.class, responseContainer = "List" ) })
    @PostMapping("/gifBrushLog")
    public  Message  gifBrushLog(String userId, String imRoomId, String giftId, String giftNum ) {
        try{

            LiveRoom room = chatRoomService.spectatorByRoomId(imRoomId);
            if(room == null ){
                return MessageUtils.getSuccess("该直播间不存在");
            }
            String teacherId = room.getUserId();
            if( iSearchService.getAPITeacherUserByUserId(teacherId) == null){
                return MessageUtils.getFail("该教头用户不存在!");
            }
            Gift gift = new Gift(giftId);
            gift = chatRoomService.getGiftByGiftId(giftId);
            if(gift == null){
                return MessageUtils.getSuccess("礼物不存在");
            }
            BigDecimal money = new BigDecimal(giftNum).multiply(gift.getVirtualMoney());
            //查询会员用户资金记录
            UserCapital userCapital = iSearchService.selectUserCapitalByUserId(userId);
            if(userCapital == null || (userCapital != null && userCapital.getTael().compareTo(new BigDecimal(0))<=0)  || (userCapital != null && userCapital.getTael().compareTo(money)<=0)){
                return MessageUtils.getSuccess("余额不足");
            }
            int i =chatRoomService.gifBrushLog(userId,teacherId,giftId,giftNum,imRoomId);
            if (i ==1){
                return MessageUtils.getSuccess("success");
            }else{
                return MessageUtils.getFail("success");
            }
        }catch(Exception e){
            e.printStackTrace();
            return MessageUtils.getFail(e.getMessage());
        }
    }


    /**
     * 礼物总榜单Top10
     * @return
     */
    @ApiOperation(value = "直播-礼物总榜单Top10", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "imRoomId", value = "网易聊天室ID", required = true, dataType = "String",defaultValue = "11080454")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = GiftCollectionStatistics.class, responseContainer = "List" ) })
    @PostMapping("/giftTotalTopTenList")
    public Message<List<GiftCollectionStatistics>> giftTotalTopTenList(String imRoomId){
        LiveRoom room = chatRoomService.spectatorByRoomId(imRoomId);
        if(room == null){
            return MessageUtils.getSuccess("该直播间不存在");
        }
        Message<List<GiftCollectionStatistics>> message = MessageUtils.getSuccess("success");
        List<GiftCollectionStatistics> list = chatRoomService.giftTotalTopTenList(imRoomId);
        message.setData(list);
        return message;
    }

    /**
     * 礼物日榜单Top10
     * @return
     */
    @ApiOperation(value = "直播- 礼物日榜单Top10", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "imRoomId", value = "网易聊天室ID", required = true, dataType = "String",defaultValue = "11080454")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = GiftCollectionStatistics.class, responseContainer = "List" ) })
    @PostMapping("/giftEveryDayTopTenList")
    public Message<List<GiftCollectionStatistics>>  giftEveryDayTopTenList(String imRoomId){
        LiveRoom room = chatRoomService.spectatorByRoomId(imRoomId);
        if(room == null){
            return MessageUtils.getSuccess("该直播间不存在");
        }
        Message<List<GiftCollectionStatistics>> message = MessageUtils.getSuccess("success");
        List<GiftCollectionStatistics> list = chatRoomService.giftEveryDayTopTenList(imRoomId);
        message.setData(list);
        return message;
    }
}
