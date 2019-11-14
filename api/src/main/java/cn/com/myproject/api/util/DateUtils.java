package cn.com.myproject.api.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther CQC
 * @create 2017.8.29
 */
public class DateUtils {

    /**
     * 计算两个bigint日期之间相差的天数
     * @param time1
     * @param time2
     * @return
     */
    public static int betwwenDaysTwoCalendarTimeInMillis(Long time1,Long time2){
        Long days=(time2-time1)/(1000*60*60*24);
        return days.intValue();
    }

    /**
     * 获取当前月的第一天时间戳
     * @return
     */
    public  static Long getMonthfirstDayTimeInMillis(){
        Calendar cale =  Calendar.getInstance();
        // 获取当月第一天和最后一天
      //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Long firstDayTimeInMillis;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY,0);
        cale.set(Calendar.MINUTE,0);
        cale.set(Calendar.SECOND,0);
        cale.set(Calendar.MILLISECOND,0);
       // String firstday = format.format(cale.getTime());
        firstDayTimeInMillis =cale.getTimeInMillis()/1000;

        return firstDayTimeInMillis;
    }

    /**
     * @Title: isDateContin
     * @Description: TODO()
     * @param @param oldTime
     * @param @param newTime
     * @param @return
     * @param @throws ParseException
     * @return int -1 ：同一天。 0：昨天 。 1 ：至少是前天。
     * @throws
     * @author CQC
     * @date
     */
    public static int isDateContin(Date oldTime, Date newTime)
            throws ParseException {

        int result = 0;

        if (null != newTime) {
            if (null == newTime) {
                newTime = new Date();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = format.format(newTime);
            Date today = format.parse(todayStr);
            if ((today.getTime() - oldTime.getTime()) > 0
                    && (today.getTime() - oldTime.getTime()) <= 86400000) {
                result = 0;
            } else if ((newTime.getTime() - oldTime.getTime()) > 0 &&(today.getTime() - oldTime.getTime()) <= 0) { // 今天
                result = -1;
            } else { // 至少是前天
                result = 1;
            }
        }
        return result;
    }


    /**
     * 时间相加
     * @param nowDays 需要加的时间
     * @param dayNum  天数
     * @return
     */
    public static long addDay(long nowDays,int dayNum){
        long returnDays = 0l;
        Date date = new Date(nowDays);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(calendar.DATE, dayNum);//把日期往后增加n天.正数往后推,负数往前移动
        returnDays = calendar.getTime().getTime();
        return returnDays;
    }

    public static void main(String str[]){
        try{
            int a = 3;
            short b = 3;
            if( a==b ){
                System.out.println(a);
            }else{
                System.out.println("ddddddddd");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
