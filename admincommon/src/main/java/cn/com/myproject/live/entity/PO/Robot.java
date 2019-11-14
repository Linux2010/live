package cn.com.myproject.live.entity.PO;


import cn.com.myproject.util.BasePO;

/**
 * 机器人账号
 * Created by liyang-macbook on 2017/7/20.
 */
public class Robot extends BasePO {


    private String robotId;
    private String accid;
    private String name;
    private String accidToken;
    private String icon;

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccidToken() {
        return accidToken;
    }

    public void setAccidToken(String accidToken) {
        this.accidToken = accidToken;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
