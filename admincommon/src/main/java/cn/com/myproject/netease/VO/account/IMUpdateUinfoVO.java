package cn.com.myproject.netease.VO.account;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/29.
 */
public class IMUpdateUinfoVO {
    /**
     * 用户帐号，最大长度32字符，必须保证一个APP内唯一
     */
    private String accid;
    /***
     * 用户昵称，最大长度64字符
     */
    private String name;
    /***
     * 用户icon，最大长度1024字符
     */
    private String icon;
    /***
     * 用户签名，最大长度256字符
     */
    private String sign;
    /**
     * 用户email，最大长度64字符
     */
    private String email;
    /***
     * 用户生日，最大长度16字符
     */
    private String birth;
    /**
     * 用户mobile，最大长度32字符，只支持国内号码
     */
    private String mobile;
    /***
     * 用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
     */
    private String gender;
    /**
     * 用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串
     */
    private String ex;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != accid){
            list = new LinkedList<>();
            list.add(accid);
            map.put("accid",list);
        }

        if(null != name){
            list = new LinkedList<>();
            list.add(name);
            map.put("name",list);
        }

        if(null != icon){
            list = new LinkedList<>();
            list.add(icon);
            map.put("icon",list);
        }

        if(null != sign){
            list = new LinkedList<>();
            list.add(sign);
            map.put("sign",list);
        }

        if(null != email){
            list = new LinkedList<>();
            list.add(email);
            map.put("email",list);
        }

        if(null != birth){
            list = new LinkedList<>();
            list.add(birth);
            map.put("birth",list);
        }

        if(null != mobile){
            list = new LinkedList<>();
            list.add(mobile);
            map.put("mobile",list);
        }

        if(null != gender){
            list = new LinkedList<>();
            list.add(gender);
            map.put("gender",list);
        }

        if(null != ex){
            list = new LinkedList<>();
            list.add(ex);
            map.put("ex",list);
        }



        return CollectionUtils.toMultiValueMap(map);
    }
}
