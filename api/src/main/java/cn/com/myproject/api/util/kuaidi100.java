package cn.com.myproject.api.util;

import feign.Request;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class kuaidi100 {

    private static Logger logger = LoggerFactory.getLogger(kuaidi100.class);

   // private static String key = "OEdotBow1703";
   // private static String customer = "27C23FA22071F968EE7FEDA4D8150DAD";

    public static Map query_logi(String com,String num,String key,String customer){
        Map map = new HashMap();
        String param ="{\"com\":\""+com+"\",\"num\":\""+num+"\"}";
        String sign = MD5.encode(param+key+customer);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("param",param);
        params.put("sign",sign);
        params.put("customer",customer);
        String resp = null;
        try {
            resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            map = JsonUtil.toMap(resp);
            //System.out.print(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("订单物流查询:"+e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 测试物流
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
       // kuaidi100.query_logi("baishiwuliu","71230328484485");
    }



}
