package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModelProperty;

/**
 * 主播开直播
 * Created by liyang-macbook on 2017/8/21.
 */
public class AnchorLiveRoomVO {


    @ApiModelProperty(value="聊天室ID",example = "12345678")
    private String roomId;

    @ApiModelProperty(value="聊天室Name",example = "红懒的直播房间")
    private String name;

    /**
     * 网易IM 标示
     * */
    @ApiModelProperty(value="聊天室所有者（网易IM标示）",example = "1sa23sq2jlkj")
    private String creator;

    @ApiModelProperty(value="在线人数",example = "1000")
    private Long onlineUserCount;

    @ApiModelProperty(value="推流地址",example = "")
    private String broadcastUrl;

    @ApiModelProperty(value="是否禁言 1、全体禁言 0、不禁言",example = "1")
    private Short inAllMuteMode;



    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getOnlineUserCount() {
        return onlineUserCount;
    }

    public void setOnlineUserCount(Long onlineUserCount) {
        this.onlineUserCount = onlineUserCount;
    }

    public String getBroadcastUrl() {
        return broadcastUrl;
    }

    public void setBroadcastUrl(String broadcastUrl) {
        this.broadcastUrl = broadcastUrl;
    }

    public Short getInAllMuteMode() {
        return inAllMuteMode;
    }

    public void setInAllMuteMode(Short inAllMuteMode) {
        this.inAllMuteMode = inAllMuteMode;
    }
}
