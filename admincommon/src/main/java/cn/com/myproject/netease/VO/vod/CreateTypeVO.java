package cn.com.myproject.netease.VO.vod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/17.
 */
public class CreateTypeVO  implements Serializable {

    /**
     * 视频分类的名称
     * 必填：是
     * */
    private String typeName;
    /**
     * 视频分类的描述信息
     * 必填：是
     * */
    private String description;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public static void main(String[] args) {
        List<String> set = new ArrayList<>();
        set.add(null);
        set.add(null);
        System.out.println(set.size());
    }

}
