package cn.com.myproject.netease.VO.chatroom;

import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/6/29.
 */
public class IMSendMsgVO implements Serializable {

    /**
     *聊天室id
     * */
    private Long roomid;

    /**
     *客户端消息id，使用uuid等随机串，msgId相同的消息会被客户端去重
     * */
    private String msgId;

    /**
     *消息发出者的账号accid
     * */
    private String fromAccid;

    /**
     * 消息类型：
     * 0: 表示文本消息，
     * 1: 表示图片，
     * 2: 表示语音，
     * 3: 表示视频，
     * 4: 表示地理位置信息，
     * 6: 表示文件，
     * 10: 表示Tips消息，
     * 100: 自定义消息类型
     * */
    private Integer msgType = 0;

    /**
     *重发消息标记，0：非重发消息，1：重发消息，如重发消息会按照msgid检查去重逻辑
     * */
    private Integer resendFlag = 0 ;

    /**
     *消息内容，格式同消息格式示例中的body字段,长度限制4096字符
     * */
    private String attach;

    /**
     *消息扩展字段，内容可自定义，请使用JSON格式，长度限制4096字符
     * */
    private String ext;

    /**
     *本消息是否需要过自定义反垃圾系统。true或false, 默认false
     * */
    private String antispam;

    /**
     * 自定义的反垃圾内容, JSON格式，长度限制同attach字段，不能超过4096字符，antispamCustom示例：
     *
     * {"type":1,"data":"custom content"}
     *
     * 字段说明：
     * 1. type: 1:文本，2：图片，3视频;
     * 2. data: 文本内容or图片地址or视频地址。
     * */
    private String antispamCustom;


    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromAccid() {
        return fromAccid;
    }

    public void setFromAccid(String fromAccid) {
        this.fromAccid = fromAccid;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getResendFlag() {
        return resendFlag;
    }

    public void setResendFlag(Integer resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAntispam() {
        return antispam;
    }

    public void setAntispam(String antispam) {
        this.antispam = antispam;
    }

    public String getAntispamCustom() {
        return antispamCustom;
    }

    public void setAntispamCustom(String antispamCustom) {
        this.antispamCustom = antispamCustom;
    }


    public MultiValueMap<String,String> toMap(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list;
        if(null != roomid){
            list = new LinkedList<>();
            list.add(roomid+"");
            map.put("roomid",list);
        }

        if(null != msgId){
            list = new LinkedList<>();
            list.add(msgId);
            map.put("msgId",list);
        }

        if(null != fromAccid){
            list = new LinkedList<>();
            list.add(fromAccid);
            map.put("fromAccid",list);
        }

        if(null != msgType){
            list = new LinkedList<>();
            list.add(msgType+"");
            map.put("msgType",list);
        }

        if(null != resendFlag){
            list = new LinkedList<>();
            list.add(resendFlag+"");
            map.put("resendFlag",list);
        }

        if(null != attach){
            list = new LinkedList<>();
            list.add(attach);
            map.put("attach",list);
        }

        if(null != ext){
            list = new LinkedList<>();
            list.add(ext);
            map.put("ext",list);
        }

        if(null != antispam){
            list = new LinkedList<>();
            list.add(antispam);
            map.put("antispam",list);
        }

        if(null != antispamCustom){
            list = new LinkedList<>();
            list.add(antispamCustom+"");
            map.put("antispamCustom",list);
        }

        return CollectionUtils.toMultiValueMap(map);
    }
}
