package cn.com.myproject.live.service;




import cn.com.myproject.live.entity.PO.ChatRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
public interface IChatRoomService {
    void createChatRoom(String userId, String accid, String roomName, String roomRemark);

    List<ChatRoom> getList(int pageNum, int pageSize);
}
