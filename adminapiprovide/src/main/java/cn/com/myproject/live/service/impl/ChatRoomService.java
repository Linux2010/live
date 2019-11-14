package cn.com.myproject.live.service.impl;


import cn.com.myproject.live.entity.PO.ChatRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import cn.com.myproject.live.mapper.ChatRoomMapper;
import cn.com.myproject.live.service.IChatRoomService;
import cn.com.myproject.netease.VO.ResultChatroom;
import cn.com.myproject.netease.VO.chatroom.IMCreateVO;
import cn.com.myproject.netease.service.IIMChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@Service
public class ChatRoomService implements IChatRoomService {

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Autowired
    private IIMChatroomService imChatroomService;

    @Override
    @Transactional
    public void createChatRoom(String userId,String accid,String roomName,String roomRemark) {
        //1、插入数据
        ChatRoom room = new ChatRoom();
        room.setUserId(userId);
        room.setRoomId(UUID.randomUUID().toString().replace("-",""));
        room.setRoomName(roomName);
        room.setRoomRemark(roomRemark);
        room.setStatus((short)1);
        room.setVersion(1);
        room.setCreateTime(Calendar.getInstance().getTimeInMillis());

        int num = chatRoomMapper.insert(room);
        if(num != 1) {
            throw new RuntimeException("插入数据失败");
        }

        //2、调用网易接口，创建聊天室
        IMCreateVO vo = new IMCreateVO();
        vo.setCreator(accid);
        vo.setName(roomName);
        vo.setAnnouncement(roomRemark);
        ResultChatroom resultChatroom = imChatroomService.create(vo);
        if(resultChatroom.getCode() != 200) {
            throw new RuntimeException("调用网易创建聊天室接口失败,"+resultChatroom.getCode()+","+resultChatroom.getDesc());
        }

        //3、更新数据
        room = new ChatRoom();
        HashMap<String,String> map = resultChatroom.getChatroom();
        room.setImName(map.get("name"));
        room.setImCreator(accid);
        room.setImAnnouncement(map.get("announcement"));
        room.setImRoomid(map.get("roomid"));
        room.setImMuted((short)0);
        room.setImValid((short)1);
        room.setImNeednotify((short)1);
        room.setUserId(userId);
        num = chatRoomMapper.updateIMValue(room);
        if(num < 1) {
            throw new RuntimeException("更新数据失败");
        }
    }

    @Override
    public List<ChatRoom> getList(int pageNum,  int pageSize) {
        return this.chatRoomMapper.getList(pageNum,pageSize);
    }
}
