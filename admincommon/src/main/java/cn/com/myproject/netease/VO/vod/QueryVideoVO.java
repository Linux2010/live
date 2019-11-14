package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/19.
 */
public class QueryVideoVO implements Serializable {

    /**
     * 上传文件的对象名列表
     *  必填：是
     * */
    private List<String> objectNames;

    public List<String> getObjectNames() {
        return objectNames;
    }

    public void setObjectNames(List<String> objectNames) {
        this.objectNames = objectNames;
    }
}
