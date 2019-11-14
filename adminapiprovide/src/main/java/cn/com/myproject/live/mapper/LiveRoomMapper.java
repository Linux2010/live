package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.LiveRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@Mapper
public interface LiveRoomMapper {
    List<LiveRoom> getList();
    int insert(LiveRoom room);
    int update(LiveRoom room);
    List<Map<String,Object>> getLiveRoomsPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,
                                              @Param("map") Map<String,Object> map);

    int updateLiveRoom(@Param("map") Map<String,Object> map);

    Map<String,Object> selectLiveRoomByRoomId(@Param("roomId") String roomId);

    LiveRoom hostEntrance(@Param("userId") String userId);

    LiveRoom spectatorByRoomId(@Param("roomId")  String roomId);

    LiveRoom selectTeacherLiveRoom(LiveRoom liveRoom);

}
