package cn.com.myproject.user.entity.VO;

/**
 * Created by liyang-macbook on 2017/6/30.
 */
public enum UserStatus {

    S_0((short)0,"未知"),S_1((short)1,"正常");

    private short id;
    private String name;

    UserStatus(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public short getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
