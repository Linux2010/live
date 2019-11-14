package cn.com.myproject.live.entity.PO;


import cn.com.myproject.util.BasePO;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
public class ChatRoom extends BasePO {

    /**
     *房间ID
     * */
    private String roomId;
    /**
     * 房间名称
     * */
    private String roomName;
    /**
     *房间公告
     * */
    private String roomRemark;
    /**
     * 房间创建者
     * */
    private String userId;
    /**
     * 房间创建者accid(im的标示)
     * */
    private String imCreator;
    /**
     * 房间名称
     * */
    private String imName;
    /**
     * 房间公告
     * */
    private String imAnnouncement;
    /**
     * 直播地址
     * */
    private String imBroadcasturl;
    /**
     *房间ID
     * */
    private String imRoomid;
    /**
     * 1、打开聊天室 0、关闭聊天室
     * true或false，false:关闭聊天室；true:打开聊天室
     * */
    private Short imValid;
    /**
     * 聊天室是否处于全体禁言状态，全体禁言时仅管理员和创建者可以发言
     * */
    private Short imMuted;
    /**
     *true或false,是否需要发送更新通知事件，默认true
     * */
    private Short imNeednotify;

    /**
     * 聊天室在线人数
     * */
    private Long imOnlineusercount;




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

    public String getRoomRemark() {
        return roomRemark;
    }

    public void setRoomRemark(String roomRemark) {
        this.roomRemark = roomRemark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImCreator() {
        return imCreator;
    }

    public void setImCreator(String imCreator) {
        this.imCreator = imCreator;
    }

    public String getImName() {
        return imName;
    }

    public void setImName(String imName) {
        this.imName = imName;
    }

    public String getImAnnouncement() {
        return imAnnouncement;
    }

    public void setImAnnouncement(String imAnnouncement) {
        this.imAnnouncement = imAnnouncement;
    }

    public String getImBroadcasturl() {
        return imBroadcasturl;
    }

    public void setImBroadcasturl(String imBroadcasturl) {
        this.imBroadcasturl = imBroadcasturl;
    }

    public String getImRoomid() {
        return imRoomid;
    }

    public void setImRoomid(String imRoomid) {
        this.imRoomid = imRoomid;
    }

    public Short getImValid() {
        return imValid;
    }

    public void setImValid(Short imValid) {
        this.imValid = imValid;
    }

    public Short getImMuted() {
        return imMuted;
    }

    public void setImMuted(Short imMuted) {
        this.imMuted = imMuted;
    }

    public Short getImNeednotify() {
        return imNeednotify;
    }

    public void setImNeednotify(Short imNeednotify) {
        this.imNeednotify = imNeednotify;
    }

    public Long getImOnlineusercount() {
        return imOnlineusercount;
    }

    public void setImOnlineusercount(Long imOnlineusercount) {
        this.imOnlineusercount = imOnlineusercount;
    }


}
