package cn.com.myproject.live.entity.VO;

import io.swagger.annotations.ApiModelProperty;

/**
 * 主播开直播
 * Created by liyang-macbook on 2017/8/21.
 */
public class SpectatorLiveRoomVO {


    @ApiModelProperty(value="聊天室ID",example = "12345678")
    private String roomId;

    @ApiModelProperty(value="聊天室Name",example = "红懒的直播房间")
    private String name;


    @ApiModelProperty(value="在线人数",example = "1000")
    private Long onlineUserCount;

    @ApiModelProperty(value="拉流地址",example = "rtmp://")
    private String pullUrl;

    @ApiModelProperty(value = "http拉流地址")
    private String httppullurl;

    @ApiModelProperty(value = "hlsp拉流地址")
    private String hlspullurl;

    @ApiModelProperty(value="直播类型 2、视频直播 1、音频直播",example = "1")
    private Short type;


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



    public Long getOnlineUserCount() {
        return onlineUserCount;
    }

    public void setOnlineUserCount(Long onlineUserCount) {
        this.onlineUserCount = onlineUserCount;
    }


    public String getPullUrl() {
        return pullUrl;
    }

    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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
}