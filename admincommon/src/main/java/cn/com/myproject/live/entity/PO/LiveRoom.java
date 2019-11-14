package cn.com.myproject.live.entity.PO;


import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
public class LiveRoom extends BasePO {
    /**
     * 房间ID
     * */
    @ApiModelProperty(value = "直播间业务房间ID")
    private String roomId;
    /**
     * 房间名字
     * */
    @ApiModelProperty(value = "房间名字")
    private String roomName;
    /**
     *网易直播频道ID
     * */
    @ApiModelProperty(value = "网易直播频道ID")
    private String cid;
    /**
     *网易直播频道创建时间戳
     * */
    @ApiModelProperty(value = "网易直播频道创建时间戳")
    private Long ctime;

    @ApiModelProperty(value = "推流地址")
    private String pushurl; //推流地址

    @ApiModelProperty(value = "http拉流地址")
    private String httppullurl;

    @ApiModelProperty(value = "hlsp拉流地址")
    private String hlspullurl;

    @ApiModelProperty(value = "rtmp拉流地址")
    private String rtmppullurl;
    /**
     *网易直播间名称
     * */
    @ApiModelProperty(value = "网易直播间名称")
    private String liveName;
    /**
     * 网易直播间类型 ( 0 : rtmp)
     * */
    @ApiModelProperty(value = "网易直播间类型 ( 0 : rtmp)")
    private Short liveType;


    @ApiModelProperty(value = "头用户业务ID")
    private String userId;

    @ApiModelProperty(value = "直播间在线人数")
    private int onlineusercount;

    private String creater;

    /**
     * 网易IM 标示
     * value="聊天室所有者（网易IM标示）",example = "1sa23sq2jlkj")
     * */

    @ApiModelProperty(value = "聊天室所有者（网易IM标示）")
    private String imCreator;

    @ApiModelProperty(value = "聊天室roomId")
    private String imRoomId;

    @ApiModelProperty(value = "直播类型 -1：未开始；1:音频;2:视频")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImRoomId() {
        return imRoomId;
    }

    public void setImRoomId(String imRoomId) {
        this.imRoomId = imRoomId;
    }

    public String getImCreator() {
        return imCreator;
    }

    public void setImCreator(String imCreator) {
        this.imCreator = imCreator;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public int getOnlineusercount() {
        return onlineusercount;
    }

    public void setOnlineusercount(int onlineusercount) {
        this.onlineusercount = onlineusercount;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getPushurl() {
        return pushurl;
    }

    public void setPushurl(String pushurl) {
        this.pushurl = pushurl;
    }

    public String getHttppullurl() {
        return httppullurl;
    }

    public void setHttppullurl(String httppullurl) {
        this.httppullurl = httppullurl;
    }

    public String getHlspullurl() {
        return hlspullurl;
    }

    public void setHlspullurl(String hlspullurl) {
        this.hlspullurl = hlspullurl;
    }

    public String getRtmppullurl() {
        return rtmppullurl;
    }

    public void setRtmppullurl(String rtmppullurl) {
        this.rtmppullurl = rtmppullurl;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public Short getLiveType() {
        return liveType;
    }

    public void setLiveType(Short liveType) {
        this.liveType = liveType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
