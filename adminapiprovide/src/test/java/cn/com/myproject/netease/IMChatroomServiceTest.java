package cn.com.myproject.netease;

import cn.com.myproject.netease.VO.ResultChatroom;
import cn.com.myproject.netease.VO.chatroom.IMGetVO;
import cn.com.myproject.netease.service.IIMChatroomService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liyang-macbook on 2017/8/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IMChatroomServiceTest {
    @Autowired
    private IIMChatroomService iimChatroomService;

    @Test
    public void  testget() {
        IMGetVO vo = new IMGetVO();
        vo.setRoomid(10035164l);
        vo.setNeedOnlineUserCount("true");
        ResultChatroom room = iimChatroomService.get(vo);
        Assert.assertEquals(room.getCode()+"","200");
    }
}
