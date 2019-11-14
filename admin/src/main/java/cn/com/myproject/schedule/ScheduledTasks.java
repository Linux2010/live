package cn.com.myproject.schedule;


import cn.com.myproject.service.IUserCapitalLogService;
import cn.com.myproject.service.IUserCapitalService;
import cn.com.myproject.service.IUserService;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.com.myproject.recset.controller.RecLableController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * @auther CQC
 * @create 2017.9.19
 */
@Component
public class ScheduledTasks {

    public static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserCapitalService userCapitalService;

    @Autowired
    private IUserCapitalLogService userCapitalLogService;

    //@Scheduled(fixedRate=5000000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + new Date());
    }

    /**
     * 教头评测
     */

    public void teacherEvaluate(){

    }


    private int getALlTeacher(int pageNum,int pageSize){

        if(pageNum>=1 && pageSize >=10){

            Map<String,Object> filterMap = new HashMap<>();
            filterMap.put("pageNum",pageNum);
            filterMap.put("pageSize",pageSize);
            PageInfo<User> userPageInfo = userService.getPageForeach(filterMap);

            if(null != userPageInfo){
                int pages = userPageInfo.getPages();
                List<User> userList = userPageInfo.getList();
                StringBuffer userSB = new StringBuffer();
                for(User user:userList){
                    userSB.append(",").append(user.getUserId());
                }

                UserCapitalLog userCapitalLog = new UserCapitalLog();
                userCapitalLog.setUserIds(String.valueOf(userSB).substring(1));
                List<Map<String,Object>> capitalLogMapList = userCapitalLogService.getUserCapitalAccount(userCapitalLog);
                List<User> updateUserList = new ArrayList<>();
                for(Map<String,Object> capitalLogMap:capitalLogMapList){

                    BigDecimal account = new BigDecimal(String.valueOf(capitalLogMap.get("account")));

                    if(account.compareTo(BigDecimal.ZERO) > 0){
                        
                    }


                    //用户等级
                    User user = new User();
                    user.setUserId(String.valueOf(capitalLogMap.get("userId")));



                }

            }

        }else{
           return -1;
        }
        return pageNum++;
    }

}
