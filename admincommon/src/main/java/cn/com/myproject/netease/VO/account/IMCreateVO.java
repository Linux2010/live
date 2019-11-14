package cn.com.myproject.netease.VO.account;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
public class IMCreateVO  implements Serializable {
    /**
     * 网易云通信ID，最大长度32字符，必须保证一个
     * APP内唯一（只允许字母、数字、半角下划线_、
     * @、半角点以及半角-组成，不区分大小写，
     * 会统一小写处理，请注意以此接口返回结果中的accid为准）
     * 必填：是
     * */
    private String accid;
    /**
     * 网易云通信ID昵称，最大长度64字符，用来PUSH推送
     * 时显示的昵称
     *
     * 必填：否
     * */
    private String name;
    /**
     * json属性，第三方可选填，最大长度1024字符
     * 必填：否
     * */
    private String props;
    /**
     * 网易云通信ID头像URL，第三方可选填，最大长度1024
     * 必填：否
     * */
    private String icon;
    /**
     * 网易云通信ID可以指定登录token值，最大长度128字符，
     * 并更新，如果未指定，会自动生成token，并在
     * 创建成功后返回
     * 必填：否
     * */
    private String token;

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

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

        if(null != props){
            list = new LinkedList<>();
            list.add(props);
            map.put("props",list);
        }

        if(null != icon){
            list = new LinkedList<>();
            list.add(icon);
            map.put("icon",list);
        }

        if(null != token){
            list = new LinkedList<>();
            list.add(token);
            map.put("token",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
