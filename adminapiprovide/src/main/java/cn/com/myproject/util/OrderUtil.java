package cn.com.myproject.util;

import java.util.Date;
import java.util.Random;

/*
 * Created by pangdatao on 2017-09-15
 * desc：订单工具类
 */
public class OrderUtil {


    /**
     * 生成订单编号
     *
     * @return
     */
    public static String createOrderNo(String bjStr){
        // 订单编号由特殊标记+当前时间时间戳+六位随机字符组成
        String orderNo = bjStr+new Date().getTime()+getRandomString(6);
        return orderNo;
    }

    /**
     * 生成随机字符
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args){
        System.out.println(createOrderNo("a"));
    }

}