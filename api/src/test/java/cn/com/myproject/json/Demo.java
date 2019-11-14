package cn.com.myproject.json;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyang-macbook on 2017/9/9.
 */
public class Demo {
    public static void main(String[] args) {
//        String fileName = "/Users/liyang-macbook/Downloads/74825_19761.mid";
//        File file = new File(fileName);
//        InputStream in = null;
//        String _str = "";
//        try {
//            // 一次读一个字节
//            in = new FileInputStream(file);
//            int tempbyte;
//            while ((tempbyte = in.read()) != -1) {
//                _str += Integer.toHexString(tempbyte);
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        String fileName1 = "/Users/liyang-macbook/Downloads/212346_21063.mid";
//        File file1 = new File(fileName1);
//        InputStream in1 = null;
//        String _str1 = "";
//        try {
//            // 一次读一个字节
//            in1 = new FileInputStream(file1);
//            int tempbyte1;
//            while ((tempbyte1 = in1.read()) != -1) {
//                _str1 += Integer.toHexString(tempbyte1);
//            }
//            in1.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        System.out.println(_str);
//        System.out.println(_str1);
//        if(_str.equals(_str1) ){
//            System.out.println("一致");
//        }else {
//            System.out.println("不一致");
//        }
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("wx2f3027943ae38987"); // 设置微信公众号的appid
        config.setSecret("9bde8d38d38d83c8ddd01947c7acf819"); // 设置微信公众号的app corpSecret
        config.setToken("..."); // 设置微信公众号的token
        config.setAesKey("..."); // 设置微信公众号的EncodingAESKey

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);





        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oMT9Ds4NWGttsQLiC0qX6I8qG51M")
                .templateId("hCKBlNGW6t75G-FGdWt_FvmWG8oxjnkA2_36_P2sEwk").build();


        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("first", "您有新的下线加入", "#FF00FF"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("keyword1","111", "#FF00FF"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("keyword2", dateFormat.format(new Date()), "#FF00FF"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("keyword3","111", "#FF00FF"));
        templateMessage.addWxMpTemplateData(
                new WxMpTemplateData("remark", RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
        templateMessage.setUrl("");
        try {
            String msgId = wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }
}
