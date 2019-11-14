package cn.com.myproject.netease.service.impl;


import cn.com.myproject.netease.VO.*;
import cn.com.myproject.netease.VO.chatroom.*;
import cn.com.myproject.netease.service.IIMChatroomService;
import cn.com.myproject.netease.util.IMHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liyang-macbook on 2017/6/29.
 */
@Service
public class IMChatroomService implements IIMChatroomService {

    private static final String CREATE_URL = "https://api.netease.im/nimserver/chatroom/create.action";
    private static final String GET_URL = "https://api.netease.im/nimserver/chatroom/get.action";
    private static final String UPDATE_URL = "https://api.netease.im/nimserver/chatroom/update.action";
    private static final String TOGGLECLOSESTAT_URL = "https://api.netease.im/nimserver/chatroom/toggleCloseStat.action";
    private static final String SETMEMEBERROLE_URL = "https://api.netease.im/nimserver/chatroom/setMemberRole.action";
    private static final String REQUESTADDR_URL = "https://api.netease.im/nimserver/chatroom/requestAddr.action";
    private static final String SENDMSG_URL = "https://api.netease.im/nimserver/chatroom/sendMsg.action";
    private static final String ADDROBOT_URL = "https://api.netease.im/nimserver/chatroom/addRobot.action";
    private static final String REMOVEROBOT_URL = "https://api.netease.im/nimserver/chatroom/removeRobot.action";
    private static final String TEMPORARYMUTE_URL = "https://api.netease.im/nimserver/chatroom/temporaryMute.action";
    private static final String QUEUEOFFER_URL = "https://api.netease.im/nimserver/chatroom/queueOffer.action";
    private static final String QUEUEPOLL_URL = "https://api.netease.im/nimserver/chatroom/queuePoll.action";
    private static final String QUEUELIST_URL = "https://api.netease.im/nimserver/chatroom/queueList.action";
    private static final String QUEUEDROP_URL = "https://api.netease.im/nimserver/chatroom/queueDrop.action";
    private static final String QUEUEINIT_URL = "https://api.netease.im/nimserver/chatroom/queueInit.action";
    private static final String MUTEROOM_URL = "https://api.netease.im/nimserver/chatroom/muteRoom.action";
    private static final String TOPN_URL = "https://api.netease.im/nimserver/stats/chatroom/topn.action";
    private static final String MEMBERSBYPAGE_URL = "https://api.netease.im/nimserver/chatroom/membersByPage.action";
    private static final String QUERYMEMBERS_URL = "https://api.netease.im/nimserver/chatroom/queryMembers.action";
    private static final String UPDATEMYROOMROLE_URL = "https://api.netease.im/nimserver/chatroom/updateMyRoomRole.action";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ResultChatroom create(IMCreateVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroom> entity = restTemplate.postForEntity(CREATE_URL,formEntity, ResultChatroom.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroom get(IMGetVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroom> entity = restTemplate.postForEntity(GET_URL,formEntity,  ResultChatroom.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroom update(IMUpdateVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroom> entity = restTemplate.postForEntity(UPDATE_URL,formEntity,  ResultChatroom.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc toggleCloseStat(IMToggleCloseStatVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(TOGGLECLOSESTAT_URL,formEntity, ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc setMemberRole(IMSetMemberRoleVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(SETMEMEBERROLE_URL,formEntity,  ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public IMRequestAddrReturnVO requestAddr(IMRequestAddrVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<IMRequestAddrReturnVO> entity = restTemplate.postForEntity(REQUESTADDR_URL,formEntity,  IMRequestAddrReturnVO.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc sendMsg(IMSendMsgVO vo) {
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(SENDMSG_URL,formEntity, ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc addRobot(IMAddRobotVO vo) {
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(ADDROBOT_URL,formEntity,  ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc removeRobot(IMAddRobotVO vo) {
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(REMOVEROBOT_URL,formEntity,  ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc temporaryMute(IMTemporaryMuteVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(TEMPORARYMUTE_URL,formEntity, ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public Result queueOffer(IMQueueOfferVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<Result> entity = restTemplate.postForEntity(QUEUEOFFER_URL,formEntity, Result.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc queuePoll(IMQueuePollVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(QUEUEPOLL_URL,formEntity,  ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc queueList(IMQueuePollVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(QUEUELIST_URL,formEntity, ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public Result queueDrop(IMQueuePollVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<Result> entity = restTemplate.postForEntity(QUEUEDROP_URL,formEntity, Result.class);
        return entity.getBody();
    }

    @Override
    public Result queueInit(IMQueueInitVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<Result> entity = restTemplate.postForEntity(QUEUEINIT_URL,formEntity, Result.class);
        return entity.getBody();
    }

    @Override
    public ResultChatroomDesc muteRoom(IMMuteRoomVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultChatroomDesc> entity = restTemplate.postForEntity(MUTEROOM_URL,formEntity, ResultChatroomDesc.class);
        return entity.getBody();
    }

    @Override
    public ResultData topn(IMTopnVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultData> entity = restTemplate.postForEntity(TOPN_URL,formEntity,  ResultData.class);
        return entity.getBody();
    }

    @Override
    public ResultDescData membersByPage(IMMembersByPageVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultDescData> entity = restTemplate.postForEntity(MEMBERSBYPAGE_URL,formEntity,  ResultDescData.class);
        return entity.getBody();
    }

    @Override
    public ResultDescData queryMembers(IMQueryMembersVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<ResultDescData> entity = restTemplate.postForEntity(QUERYMEMBERS_URL,formEntity, ResultDescData.class);
        return entity.getBody();
    }

    @Override
    public Result updateMyRoomRole(IMUpdateMyRoomRoleVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<Result> entity = restTemplate.postForEntity(UPDATEMYROOMROLE_URL,formEntity, Result.class);
        return entity.getBody();
    }
}
