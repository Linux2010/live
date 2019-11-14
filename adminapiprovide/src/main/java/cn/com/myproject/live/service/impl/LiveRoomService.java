package cn.com.myproject.live.service.impl;

import cn.com.myproject.enums.OrderEnum;
import cn.com.myproject.live.entity.PO.Gift;
import cn.com.myproject.live.entity.PO.LiveRoom;
import cn.com.myproject.live.entity.PO.live.entity.GiftBrushOrder;
import cn.com.myproject.live.entity.PO.live.entity.GiftBushLog;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import cn.com.myproject.live.mapper.*;
import cn.com.myproject.live.service.IChatRoomService;
import cn.com.myproject.live.service.ILiveRoomService;
import cn.com.myproject.netease.VO.ResultChatroom;
import cn.com.myproject.netease.VO.ResultInfo;
import cn.com.myproject.netease.VO.channel.IMCreateVO;
import cn.com.myproject.netease.VO.channel.IMDeleteVO;
import cn.com.myproject.netease.VO.channel.IMUpdateVO;
import cn.com.myproject.netease.VO.channel.ResultChannel;
import cn.com.myproject.netease.VO.chatroom.IMGetVO;
import cn.com.myproject.netease.service.IAccountService;
import cn.com.myproject.netease.service.IIMChannelService;
import cn.com.myproject.netease.service.IIMChatroomService;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import cn.com.myproject.user.mapper.UserMapper;
import cn.com.myproject.user.service.IUserService;
import cn.com.myproject.user.service.impl.UserService;
import cn.com.myproject.util.OrderUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
@Service
public class LiveRoomService implements ILiveRoomService {
    private static final Logger logger = LoggerFactory.getLogger(LiveRoomService.class);

    @Autowired
    private IIMChannelService imChannelService;

    @Autowired
    IIMChatroomService iIMChatroomService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private LiveRoomMapper liveRoomMapper;

    @Autowired
    private IChatRoomService  iChatRoomService;

    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private UserService userService;

    @Autowired
    GiftBrushOrderMapper giftBrushOrderMapper;

    @Autowired
    GiftCollectionStatisticsMapper giftCollectionStatisticsMapper;

    @Autowired
    GiftBrushLogMapper giftBrushLogMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    /**
     * @param roomName 直播间名称
     * @param userId 讲师ID
     */
    public void create(String roomName,String userId) {
        //1、插入数据
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setRoomId(UUID.randomUUID().toString().replace("-",""));
        liveRoom.setRoomName(roomName);
        liveRoom.setCtime(Calendar.getInstance().getTimeInMillis());
        liveRoom.setStatus((short)1);
        liveRoom.setVersion(1);
        liveRoom.setUserId(userId);
        int num = liveRoomMapper.insert(liveRoom);
        if(num != 1) {
            throw new RuntimeException("插入数据失败,"+userId);
        }
        //2、调用网易接口，创建直播频道
        IMCreateVO vo = new IMCreateVO();
        vo.setName(liveRoom.getRoomName());
        ResultChannel resultChannel = imChannelService.create(vo);
        if(resultChannel.getCode() != 200) {
            throw new RuntimeException("调用网易创建直播频道接口失败,"+resultChannel.getCode()+","+resultChannel.getMsg());
        }
        //3、更新数据
        liveRoom = new LiveRoom();
        liveRoom.setUserId(userId);
        liveRoom.setCid(resultChannel.getRet().get("cid"));
        liveRoom.setCtime(Long.valueOf(resultChannel.getRet().get("ctime")));
        liveRoom.setHlspullurl(resultChannel.getRet().get("hlsPullUrl"));
        liveRoom.setHttppullurl(resultChannel.getRet().get("httpPullUrl"));
        liveRoom.setPushurl(resultChannel.getRet().get("pushUrl"));
        liveRoom.setRtmppullurl(resultChannel.getRet().get("rtmpPullUrl"));
        liveRoom.setLiveName(resultChannel.getRet().get("name"));
        liveRoom.setLiveType((short)1);
        num = liveRoomMapper.update(liveRoom);
        if(num != 1) {
            throw new RuntimeException("更新数据失败,"+userId);
        }

    }
    //查询直播间列表
    public PageInfo<Map<String,Object>> getLiveRoomsPage(Map<String,Object> map){
        List<Map<String,Object>> list = null;
        try{
            if(!map.isEmpty() && map.get("keyword")!=null && org.apache.commons.lang.StringUtils.isNotBlank(map.get("keyword").toString())){
                map.put("keyword", URLDecoder.decode(map.get("keyword").toString(),"UTF-8"));
            }
        }catch(Exception e){
            logger.error("编码格式解析错误,"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("编码格式解析错误"+e.getMessage());
        }
        try{

            list = liveRoomMapper.getLiveRoomsPage(Integer.valueOf(map.get("pageNum").toString()),Integer.valueOf(map.get("pageSize").toString()), map);
        }catch(Exception e){
            logger.error("直播间列表查询异常,"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("直播间列表查询异常,"+e.getMessage());
        }
        for(Map<String,Object> mapa :list){
            mapa.put("status",0);
            /*try{

                if(mapa.get("cid") != null ){
                    // 获取直播间信息
                    IMDeleteVO vo = new IMDeleteVO();
                    vo.setCid(mapa.get(("cid")).toString());
                    ResultChannel resultChannel = imChannelService.channelstats( vo); //  * status	int	频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
                    if(resultChannel.getCode() != 200) {
                        logger.error("调用网易获取直播间信息接口失败,"+resultChannel.getCode()+","+resultChannel.getMsg());
                        throw new RuntimeException("调用网易获取直播间信息失败,"+resultChannel.getCode()+","+resultChannel.getMsg());
                    }
                    mapa.put("status",resultChannel.getRet().get("status"));
                }else{
                    logger.error(mapa.get("cid")+"用户的cid为空");
                }
            }catch(Exception e){
                logger.error("获取直播间信息异常,"+e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("获取直播间信息异常,"+e.getMessage());
            }*/

          /* 2017.09.10直播间在线人数屏蔽
           try{

                if(mapa.get("imRoomId") != null ){

                    IMGetVO imGetVO = new IMGetVO();
                    imGetVO.setNeedOnlineUserCount("true");//是否需要返回在线人数，true或false，默认false
                    imGetVO.setRoomid(Long.valueOf(mapa.get("imRoomId").toString()));
                    ResultChatroom resultChatroom = iIMChatroomService.get(imGetVO);
                    if(resultChatroom.getCode() != 200) {
                        logger.error("调用网易查询聊天室信息接口失败,"+resultChatroom.getCode());
                        throw new RuntimeException("调用网易查询聊天室信息接口失败,"+resultChatroom.getCode());
                    }
                    mapa.put("onlineusercount",resultChatroom.getChatroom().get("onlineusercount"));

                }else{
                    logger.error(mapa.get("user_id")+"用户的imroomId为空");
                }
            }catch(Exception e){
                logger.error("获取直播间信息异常,"+e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("获取直播间信息异常,"+e.getMessage());
            }*/
        }
        return new PageInfo<Map<String,Object>>(list);
    }
    //更新直播间
    @Transactional
    public int updateLiveRoomType( Map<String,Object> jsonObject){
        String roomId ="";
        if(jsonObject.get("roomId") != null) roomId = jsonObject.get("roomId").toString();
        else  throw new RuntimeException("直播间ID不能为空，liveRoomMapper.updateLiveRoom(map)");
       String type="";
       if(jsonObject.get("type") != null) type = jsonObject.get("type").toString();
        else  throw new RuntimeException("直播type不能为空，liveRoomMapper.updateLiveRoom(map)");
        Map<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("roomId",roomId);
        paramMap.put("type",type);
        int i= liveRoomMapper.updateLiveRoom(paramMap);
        if(i<1){
            logger.error("更新直播间直播类型信息失败，liveRoomMapper.updateLiveRoom(paramMap)");
            throw new RuntimeException("更新直播间直播类型信息失败，liveRoomMapper.updateLiveRoom(map)");
        }
        return 1;
    }

    //更新直播间
    @Transactional
    public int updateLiveRoom( Map<String,Object> map){
        String data = map.get("data")!=null ? map.get("data").toString():"";
        String creater=map.get("sysUserId")!=null ? map.get("sysUserId").toString():"";; //直播间创建管理员业务ID
        JSONObject jsonObject = JSONObject.fromObject(data);
        String roomName=""; //直播间名称
        String roomId=""; //直播间业务ID
        String cid="";//频道ID
        int onlineusercount =0; //在线人数
        int status=0;//直播间状态0：已关闭;1:已开启
        if(jsonObject.get("roomName") != null)  roomName = jsonObject.get("roomName").toString();
        else  throw new RuntimeException("直播间名称不能为空，liveRoomMapper.updateLiveRoom(map)");
        if(jsonObject.get("roomId") != null) roomId = jsonObject.get("roomId").toString();
        else  throw new RuntimeException("直播间ID不能为空，liveRoomMapper.updateLiveRoom(map)");
        if(jsonObject.get("onlineusercount") != null) onlineusercount = jsonObject.getInt("onlineusercount");
        if(jsonObject.get("status") != null) status = jsonObject.getInt("status");
        else  throw new RuntimeException("直播间status不能为空，liveRoomMapper.updateLiveRoom(map)");
        if(jsonObject.get("cid") != null) cid = jsonObject.get("cid").toString();
        else  throw new RuntimeException("直播间cid不能为空，liveRoomMapper.updateLiveRoom(map)");
        Map<String,Object> paramMap = new LinkedHashMap<>();
        paramMap.put("status",status);
        paramMap.put("roomName",roomName);
        paramMap.put("roomId",roomId);
        paramMap.put("onlineusercount",onlineusercount);
        int i= liveRoomMapper.updateLiveRoom(paramMap);
        if(i<1){
            logger.error("更新直播间信息失败，liveRoomMapper.updateLiveRoom(paramMap)");
            throw new RuntimeException("更新直播间信息失败，liveRoomMapper.updateLiveRoom(map)");
        }else{
            //请求网易云更新直播间名称
            IMUpdateVO vo = new IMUpdateVO();
            vo.setName(roomName);
            vo.setCid(cid);
            ResultChannel resultChannel = imChannelService.update(vo);
            if(resultChannel.getCode() != 200) {
                throw new RuntimeException("调用网易更新直播频道接口失败,"+resultChannel.getCode()+","+resultChannel.getMsg());
            }
            //请求网易更新直播间状态
            return 1;
        }
    }
    //新增直播间
    @Transactional
    public int create( Map<String,Object> map){
        String data = map.get("data")!=null ? map.get("data").toString():"";
        String creater=map.get("sysUserId")!=null ? map.get("sysUserId").toString():"";; //直播间创建管理员业务ID
        JSONObject jsonObject = JSONObject.fromObject(data);
        String roomName=""; //直播间名称
        String userId=""; //教师用户业务ID
        int onlineusercount =0; //在线人数
        if(jsonObject.get("roomName") != null)  roomName = jsonObject.get("roomName").toString();
        if(jsonObject.get("userId") != null) userId = jsonObject.get("userId").toString();
        if(jsonObject.get("onlineusercount") != null) onlineusercount = jsonObject.getInt("onlineusercount");
        //1、插入数据
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setRoomId(UUID.randomUUID().toString().replace("-",""));
        Long currentTimes = Calendar.getInstance().getTimeInMillis();
        liveRoom.setRoomName(roomName);
        liveRoom.setCtime(currentTimes);
        liveRoom.setStatus((short)1);
        liveRoom.setVersion(1);
        liveRoom.setUserId(userId);
        liveRoom.setCreater(creater);
        liveRoom.setCreateTime(currentTimes);
        liveRoom.setOnlineusercount(onlineusercount);
        int num = liveRoomMapper.insert(liveRoom);
        if(num != 1) {
            logger.error("插入数据失败,"+userId);
            throw new RuntimeException("插入数据失败,"+userId);
        }
        //2、调用网易接口，创建直播频道
        IMCreateVO vo = new IMCreateVO();
        vo.setName(liveRoom.getRoomName());
        ResultChannel resultChannel = imChannelService.create(vo);
        if(resultChannel.getCode() != 200) {
            logger.error("调用网易创建直播频道接口失败,"+resultChannel.getCode()+","+resultChannel.getMsg());
            throw new RuntimeException("调用网易创建直播频道接口失败,"+resultChannel.getCode()+","+resultChannel.getMsg());
        }
        //3、更新数据
        liveRoom = new LiveRoom();
        liveRoom.setUserId(userId);
        liveRoom.setCid(resultChannel.getRet().get("cid"));
        liveRoom.setCtime(Long.valueOf(resultChannel.getRet().get("ctime")));
        liveRoom.setHlspullurl(resultChannel.getRet().get("hlsPullUrl"));
        liveRoom.setHttppullurl(resultChannel.getRet().get("httpPullUrl"));
        liveRoom.setPushurl(resultChannel.getRet().get("pushUrl"));
        liveRoom.setRtmppullurl(resultChannel.getRet().get("rtmpPullUrl"));
        liveRoom.setLiveName(resultChannel.getRet().get("name"));
        liveRoom.setLiveType((short)1);
        num = liveRoomMapper.update(liveRoom);
        if(num < 1) {
            logger.error("修改直播间信息失败，liveRoomMapper.updateLiveRoom(map)");
            throw new RuntimeException("新增直播间信息失败，liveRoomMapper.updateLiveRoom(map)");
        }else{

            //查询教师信息
            Map<String,Object> teacherMap = iUserService.selectUserByUserId(userId);
            String accid="";
            if(teacherMap!= null&& teacherMap.size()>0){
                if(teacherMap.get("accid")!=null){
                    accid =teacherMap.get("accid").toString();
                }
            }
            //创建聊天室
            if(org.apache.commons.lang.StringUtils.isBlank(accid)){
                logger.error("讲师accid获取异常，创建直播间失败");
                //throw new RuntimeException("讲师accid获取异常，创建直播间失败");
                accid = creatImUser(userId);
                if(!org.apache.commons.lang.StringUtils.isNotBlank(accid)){
                    logger.error("讲师accid获取异常，创建直播间失败");
                    throw new RuntimeException("讲师accid获取异常，创建直播间失败");
                }
            }
            iChatRoomService.createChatRoom(userId,accid,roomName,roomName+"的聊天室");
            return 1;
        }
    }

    /**
     * 创建IM账号
     * @param userId
     * @return
     */
    public String  creatImUser(String userId){
        APITearcherUser   user = userService.selectTeacherUserByUserId( userId);
        //为用户创建IM账号
        cn.com.myproject.netease.VO.account.IMCreateVO vo =new cn.com.myproject.netease.VO.account.IMCreateVO();
        String accid =UUID.randomUUID().toString().replace("-", "");
        vo.setAccid(accid);
        vo.setName(user.getNickName());
        ResultInfo result = accountService.create(vo);
        if (result.getCode() == 200) {
            Map<String, String> map = result.getInfo();
            int updatenum = userMapper.updateAccId(user.getUserId(), map.get("accid"), map.get("token"));
            if (updatenum != 1) {
                throw new RuntimeException("更新失败");
            }
        } else {
            logger.error("插入用户失败userMapper.insert(user)");
            throw new RuntimeException("创建网易IM账号失败,code:"+result.getCode()+",message="+result.getDesc()+",NickName="+user.getNickName());
        }

        return  accid;
    }
     @Override
     public Map<String,Object> selectLiveRoomByRoomId( String roomId){
        return liveRoomMapper.selectLiveRoomByRoomId(  roomId);
    }

    @Override
    public LiveRoom hostEntrance(String userId){
        return liveRoomMapper.hostEntrance(  userId);
    }

    @Override
    public LiveRoom spectatorByRoomId(String roomId){
        LiveRoom liveRoom = liveRoomMapper.spectatorByRoomId(roomId);
        try{
            IMGetVO imGetVO = new IMGetVO();
            imGetVO.setNeedOnlineUserCount("true");// 是否需要返回在线人数，true或false，默认false
            imGetVO.setRoomid(Long.valueOf(roomId));
            ResultChatroom resultChatroom = iIMChatroomService.get(imGetVO);
            if(resultChatroom.getCode() != 200) {
                logger.error("调用网易查询聊天室信息接口失败,"+resultChatroom.getCode());
                throw new RuntimeException("调用网易查询聊天室信息接口失败,"+resultChatroom.getCode());
            }
            liveRoom.setOnlineusercount(Integer.valueOf(resultChatroom.getChatroom().get("onlineusercount")));
        }catch(Exception e){
            logger.error("获取直播间信息异常,"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("获取直播间信息异常,"+e.getMessage());
        }
        return liveRoom;
    }

    @Override
    public Gift getGiftByGiftId(String giftId){
        return giftMapper.getGiftByGiftId(giftId);
    }

    @Override
    @Transactional
    public int  gifBrushLog(String userId, String teacherId,String giftId,String giftNum ,String imRoomId ){
        Gift gift = giftMapper.getGiftByGiftId(giftId);
        BigDecimal money = new BigDecimal(giftNum).multiply(gift.getVirtualMoney());
        //银两支付记录状态
        int payResult = userService.yinliang(teacherId,userId,money.toString(),"赠送主播礼物");
        //订单记录状态
        int orderInsert=0;

        //礼物统计与刷礼物日志记录
        int giftCollectionStatics =0;
        //产生礼物订单记录
        GiftBrushOrder order = new GiftBrushOrder();
        if(payResult == 1){
            order.setOrderStatus(payResult == 1?1:0);//订单状态（0：未支付；1：已支付)
            order.setPayStatus(payResult == 1?1:0);//订单状态（0：代付款；1：已购买)
            order.setGiftWorth(gift.getVirtualMoney());
            order.setPayWay(0);//支付方式(0:银两支付；1:支付宝；2：微信；3：Apple Pay 4：Andriod Pay)
            order.setGiftId(giftId);
            order.setUserId(userId);
            String uuid= UUID.randomUUID().toString().replace("-", "");
            order.setGiftBrushOrderId(uuid);
            order.setCreateTime(Calendar.getInstance().getTimeInMillis());
         //   order.setGiftOrderId("f"+uuid);//订单编号优化2017-09-15
            order.setGiftOrderId(OrderUtil.createOrderNo(OrderEnum.livegift.getKey()));
            order.setStatus((short)1);
            order.setGiftNumber(giftNum);
            order.setGiftAllWorth(money);
            order.setVersion(1);
            order.setImRoomId(imRoomId);
            orderInsert = giftBrushOrderMapper.insertGiftOrder(order);

        }

        //更新礼物统计表
        giftCollectionStatics = userGiftBushLogAndStatistics(userId,teacherId,gift,giftNum,money,imRoomId);

        if(payResult == 1 && orderInsert == 1 && giftCollectionStatics ==1 ){
            return 1;
        }else{
            logger.info("礼物日志记录与订单记录异常 giftBrushOrderMapper.insertGiftOrder(order)");
            throw new RuntimeException("礼物日志记录与订单记录异常 giftBrushOrderMapper.insertGiftOrder(order)");
        }
    }

    public int userGiftBushLogAndStatistics(String userId, String teacherId,Gift gift,String giftNum, BigDecimal totalMoney, String imRoomId ){


        GiftBushLog log = new GiftBushLog();
        log.setGiftBrushLogId( UUID.randomUUID().toString().replace("-", ""));
        log.setGiftId(gift.getGiftId());
        log.setGiftNumber(giftNum);
        log.setGiftWorth(gift.getVirtualMoney());
        log.setGiftAllWorth(totalMoney);
        log.setTeacherId(teacherId);
        log.setCreateTime(Calendar.getInstance().getTimeInMillis());
        log.setStatus((short)1);
        log.setVersion(1);
        log.setTeacherId(teacherId);
        log.setUserId(userId);
        log.setImRoomId(imRoomId);
        int giftBrushLogInsert =giftBrushLogMapper.insertGiftBushLog(log);

        //查询用户送出礼物统计表
        GiftCollectionStatistics userStatistics = new GiftCollectionStatistics(userId,0,imRoomId);//赠收类别：0:会员赠送;1:教头接受礼物
        userStatistics = giftCollectionStatisticsMapper.getGiftCollectionStatisticsByUserId(userStatistics);

        int userGfitCollectionStatics =0;
        if(userStatistics == null ){
            userStatistics = new GiftCollectionStatistics();
            userStatistics.setGiftAllNumber( Integer.valueOf(giftNum) );
            userStatistics.setGiftAllWorth(totalMoney);
            userStatistics.setType(0);
            Long time = Calendar.getInstance().getTimeInMillis();
            userStatistics.setCreateTime(time);
            userStatistics.setUpdateTime(time);
            userStatistics.setStatus((short)1);
            userStatistics.setVersion(1);
            userStatistics.setUserId(userId);
            userStatistics.setImRoomId(imRoomId);
            //添加礼物统计记录
            userStatistics.setGiftCollectionStatisticsId(UUID.randomUUID().toString().replace("-", ""));
            userGfitCollectionStatics = giftCollectionStatisticsMapper.insertGiftCollectionStatistics(userStatistics);
        }else{
            //更新礼物统计表
            userStatistics.setGiftAllNumber(userStatistics.getGiftAllNumber() + Integer.valueOf(giftNum) );
            userStatistics.setGiftAllWorth(userStatistics.getGiftAllWorth().add(totalMoney));
            userStatistics.setType(0);
            userStatistics.setUpdateTime(Calendar.getInstance().getTimeInMillis());
            userStatistics.setStatus((short)1);
            userStatistics.setVersion(1);
            userStatistics.setUserId(userId);
            userStatistics.setImRoomId(imRoomId);
            userGfitCollectionStatics = giftCollectionStatisticsMapper.updateGiftCollectionStatistics(userStatistics);
        }

        //查询教头的收到的礼物统计表
        GiftCollectionStatistics teacherStatistics = new GiftCollectionStatistics(teacherId,1, imRoomId);//赠收类别：0:会员赠送;1:教头接受礼物
        teacherStatistics = giftCollectionStatisticsMapper.getGiftCollectionStatisticsByUserId(teacherStatistics);

        int teacherGiftCollectionStatics =0;
        if(teacherStatistics == null ){
            teacherStatistics = new GiftCollectionStatistics();
            teacherStatistics.setGiftAllNumber( Integer.valueOf(giftNum) );
            teacherStatistics.setGiftAllWorth(totalMoney);
            teacherStatistics.setType(1);
            Long time = Calendar.getInstance().getTimeInMillis();
            teacherStatistics.setCreateTime(time);
            teacherStatistics.setUpdateTime(time);
            teacherStatistics.setStatus((short)1);
            teacherStatistics.setVersion(1);
            teacherStatistics.setUserId(teacherId);
            teacherStatistics.setImRoomId(imRoomId);
            //添加礼物统计记录
            teacherStatistics.setGiftCollectionStatisticsId(UUID.randomUUID().toString().replace("-", ""));
            teacherGiftCollectionStatics = giftCollectionStatisticsMapper.insertGiftCollectionStatistics(teacherStatistics);
        }else{
            //更新礼物统计表

            teacherStatistics.setGiftAllNumber(userStatistics.getGiftAllNumber() + Integer.valueOf(giftNum) );
            teacherStatistics.setGiftAllWorth(userStatistics.getGiftAllWorth().add(totalMoney));
            teacherStatistics.setType(1);
            teacherStatistics.setUpdateTime(Calendar.getInstance().getTimeInMillis());
            teacherStatistics.setStatus((short)1);
            teacherStatistics.setVersion(1);
            teacherStatistics.setUserId(teacherId);
            teacherStatistics.setImRoomId(imRoomId);
            teacherGiftCollectionStatics = giftCollectionStatisticsMapper.updateGiftCollectionStatistics(teacherStatistics);
        }

        if(giftBrushLogInsert == 1 && teacherGiftCollectionStatics == 1){
            return 1;
        }else{
            logger.info("礼物统计表记录异常giftCollectionStatisticsMapper.insertGiftCollectionStatistics(teacherStatistics)");
            throw new RuntimeException("礼物统计表记录异常giftCollectionStatisticsMapper.insertGiftCollectionStatistics(teacherStatistics)");
        }
    }
    @Override
    public LiveRoom selectTeacherLiveRoom( LiveRoom liveRoom){
        return liveRoomMapper.selectTeacherLiveRoom(liveRoom);
    }



    @Override
    public List<GiftCollectionStatistics> giftTotalTopTenList( String imRoomId){
        return this.giftCollectionStatisticsMapper.giftTotalTopTenList(imRoomId);
    }

    @Override
    public List<GiftCollectionStatistics>  giftEveryDayTopTenList( String imRoomId){
        return this.giftCollectionStatisticsMapper.giftEveryDayTopTenList(imRoomId);
    }
}
