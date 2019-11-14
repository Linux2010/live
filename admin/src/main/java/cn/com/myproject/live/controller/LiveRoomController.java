package cn.com.myproject.live.controller;

import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.ILiveRoomService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LeiJia on 2017/8/23 0007.
 */
@Controller
@RequestMapping("/liveRoom")
public class LiveRoomController {

    private static final Logger logger = LoggerFactory.getLogger(LiveRoomController.class);

    @Autowired
    ILiveRoomService iLiveRoomService;

    @RequestMapping("/")
    public String liveRoom_index(){
        return  "live/liveRoom_index";
    }

    @RequestMapping("/getLiveRoomsPage")
    @ResponseBody
    public PageInfo<Map<String,Object>> getTeacherUsersPage(int rows, int page, HttpServletRequest request){
        Map<String,Object> paramMap = new LinkedHashMap<>();
        PageInfo<Map<String,Object>>  list = null;
        try{

            String keyword =request.getParameter("keyword");
            String phone = request.getParameter("phone");
            if("" != request.getParameter("status") && null != request.getParameter("status")){
                Integer s = Integer.parseInt(request.getParameter("status").trim());
                paramMap.put("status",s);
            }else{
                paramMap.put("status","");
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
                keyword = URLDecoder.decode(keyword, "UTF-8");
                paramMap.put("keyword",keyword.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(phone)) {
                paramMap.put("phone",phone.trim());
            }
            paramMap.put("pageNum",page);
            paramMap.put("pageSize",rows);
            list =  iLiveRoomService.getLiveRoomsPage(paramMap);
        }catch(Exception e){
            logger.error("查询讲师列表异常"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("查询讲师列表异常"+e.getMessage());
        }

        return list;
    }

    @RequestMapping(value="/updateLiveRoom" ,method = RequestMethod.POST)
    @ResponseBody
    public int  updateLiveRoom(String data,HttpServletRequest request){
        int userUpdateResult =0;
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
            SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
            Map<String,String> map = new HashMap<>();
            map.put("data",data);
            map.put("sysUserId",user.getUserId());
            userUpdateResult = iLiveRoomService.updateLiveRoom(map);
            if(userUpdateResult == 1){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;

    }
    @RequestMapping(value="/insertLiveRoom" ,method = RequestMethod.POST)
    @ResponseBody
    public int  insertLiveRoom(String data,HttpServletRequest request){
        int userInsertResult =0;
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
            SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
            Map<String,String> map = new HashMap<>();
            map.put("data",data);
            map.put("sysUserId",user.getUserId());
            userInsertResult = iLiveRoomService.insertLiveRoom(map);
            if(userInsertResult == 1){
                return 1;
            }else{
                return 0;
            }
        }
        return 0;

    }

    @RequestMapping(value="/selectLiveRoomByRoomId" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectLiveRoomByRoomId(String roomId){
        Map<String,Object> map = iLiveRoomService.selectLiveRoomByRoomId( roomId);
        return map;
    }

    @RequestMapping(value="/selectTeacherLiveRoom" ,method = RequestMethod.POST)
    @ResponseBody
    public LiveRoom selectTeacherLiveRoom(String userId){
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setUserId(userId);
        liveRoom = iLiveRoomService.selectTeacherLiveRoom(liveRoom);
        return liveRoom;
    }
}
