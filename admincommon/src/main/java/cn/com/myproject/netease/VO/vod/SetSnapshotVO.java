package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class SetSnapshotVO implements Serializable {

    /**
     * 视频Id
     * 必填：是
     * */
    private Integer vid;
    /**
     * 封面设置方法：1表示使用截图URL，2表示使用本地上传图片
     * 必填：是
     * */
    private Integer type;
    /**
     * type值为1，则代表截图URL；type值为2，则代表本地图片路径
     * 必填：是
     * */
    private String path;
    /**
     * type值为2时，需填写，代表本地图片数据的base64编码字符串数据
     * 必填：否
     * */
    private String data;


    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
