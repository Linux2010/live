package cn.com.myproject.live.service;

import cn.com.myproject.live.entity.PO.Gift;
import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public interface ILiveRoomService {
    void create(String roomName, String userId);
    int  create( Map<String,Object> map);
    PageInfo<Map<String,Object>> getLiveRoomsPage(Map<String,Object> map);
    int updateLiveRoomType( Map<String,Object> map);
    int updateLiveRoom( Map<String,Object> map);
    Map<String,Object> selectLiveRoomByRoomId( String roomId);
    LiveRoom hostEntrance(String userId);
    LiveRoom spectatorByRoomId(String roomId);
    Gift getGiftByGiftId(String giftId);
    int  gifBrushLog(String userId, String teacherId,String giftId,String giftNum, String imRoomId  );
    LiveRoom selectTeacherLiveRoom( LiveRoom liveRoom);
    List<GiftCollectionStatistics> giftTotalTopTenList(String imRoomId);
    List<GiftCollectionStatistics>  giftEveryDayTopTenList(String imRoomId);
}
