package cn.com.myproject.util;

import java.util.Random;

/*
 * Created by pangdatao on 2017-10-10
 * desc：用户团购卡工具类
 */
public class UserGroupCardUtil {

    /**
     * 生成团购卡号
     *
     * @param cardNum
     * @return
     */
    public static String getCardNo(int cardNum){
        // 生成前面两位大写随机字母，不包含I、O、Z和SB
        String preStr = "";
        if(!"SB".equals(getRandomStr(2))){
            preStr = getRandomStr(2);
        }
        // 生成后面6位递增数字，不包含4
        String nextStr = "";
        if(String.valueOf(cardNum).indexOf("4") == -1){
            if(String.valueOf(cardNum).length() == 6){
                nextStr = String.valueOf(cardNum);
            }else if(String.valueOf(cardNum).length() == 5){
                nextStr = "0"+String.valueOf(cardNum);
            }else if(String.valueOf(cardNum).length() == 4){
                nextStr = "00"+String.valueOf(cardNum);
            }else if(String.valueOf(cardNum).length() == 3){
                nextStr = "000"+String.valueOf(cardNum);
            }else if(String.valueOf(cardNum).length() == 2){
                nextStr = "0000"+String.valueOf(cardNum);
            }else if(String.valueOf(cardNum).length() == 1){
                nextStr = "00000"+String.valueOf(cardNum);
            }else{
                nextStr = String.valueOf(cardNum);
            }
        }
        return preStr+nextStr;
    }

    /**
     * 生成团购卡密码
     *
     * @return
     */
    public static String getCardPassword(){
        return getRandomNum(6);
    }

    /**
     * 生成随机数字
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成随机字符
     *
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        String base = "ABCDEFGHJKLMNPQRSTUVWXY";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}