package cn.com.myproject.live.entity.PO;


import cn.com.myproject.util.BasePO;

/**
 * 直播分类
 * Created by liyang-macbook on 2017/7/5.
 */
public class LiveType extends BasePO {
    /**
     *
     * */
    private String typeId;
    private String typeName;
    private String typeRemark;
    /**
     *  1、视频直播
     *  2、视频点播
     *  3、音频直播
     *  4、音频点播
     *  5、文字点播
     * */
    private Short type;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeRemark() {
        return typeRemark;
    }

    public void setTypeRemark(String typeRemark) {
        this.typeRemark = typeRemark;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}
