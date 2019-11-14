package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class CreateSnapshotVO implements Serializable {


    /**
     * 视频Id
     * 必填：是
     *
     * */
    private Integer vid;
    /**
     * 截图尺寸，包含以下几种值：
     * 0表示原始视频比例，1表示640360，2表示400300，3表示320180，
     * 4表示200150，5表示1280720，6表示960720，7表示360640，
     * 8表示300400，9表示180320，10表示150200，11表示7201280，
     * 12表示720960，13表示180180，14表示360360，15表示720*720
     *
     *  必填：是
     * */
    private Integer size;
    /***
     * 截图偏移，包含以下几种值：
     * 0表示视频第一秒，1表示时间轴10%位置，2表示时间轴20%位置，
     * 3表示时间轴30%位置，4表示时间轴40%位置，5表示时间轴50%位置，
     * 6表示时间轴60%位置，7表示时间轴70%位置，8表示时间轴80%位置，
     * 9表示时间轴90%位置
     *
     * 必填：是
     * */
    private Integer offset;


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
