package cn.com.myproject.netease.VO.channel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/4.
 */
public class IMMergeVO implements Serializable {
    /**
     *合并文件的名称(不能含有空格)
     *必填：是
     * */
    private String outputName;
    /**
     * 待合并的视频文件的ID列表(文件ID类型为long),视频文件数量限制为2-20个
     * 必填：是
     * */
    private List<String> vidList;


    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public List<String> getVidList() {
        return vidList;
    }

    public void setVidList(List<String> vidList) {
        this.vidList = vidList;
    }
}
