package cn.com.myproject.sysuser.dao;


import cn.com.myproject.AdminApiProvideApplication;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by liyang-macbook on 2017/8/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdminApiProvideApplication.class)
@WebAppConfiguration
public class BatchDemoDaoTest {

    @Autowired
    private BatchDemoDao batchDemoDao;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testsaveBatch() {
        List<SysUser> list = new ArrayList<>();
        SysUser user;

        //test
        for(int i=0;i<1020;i++) {
            user = new SysUser();
            user.setLoginName("demo1"+i);
            user.setNickName("demo1"+i);
            user.setPassword("111");
            user.setPhone("1");
            user.setUserId(UUID.randomUUID().toString().replace(",",""));
            user.setUserName("demo1"+i);
            user.setVersion(1);
            user.setStatus((short)1);
            user.setCreateTime(LocalTime.now().toNanoOfDay());
            list.add(user);
        }

        System.out.println("开始-----");
        long l = System.currentTimeMillis();
        batchDemoDao.saveBatch(list);
        System.out.println("结束-----"+(System.currentTimeMillis()-l));


        list = new ArrayList<>();
        for(int i=0;i<1020;i++) {
            user = new SysUser();
            user.setLoginName("demo2"+i);
            user.setNickName("demo2"+i);
            user.setPassword("1111");
            user.setPhone("11");
            user.setUserId(UUID.randomUUID().toString().replace(",",""));
            user.setUserName("demo2"+i);
            user.setVersion(1);
            user.setStatus((short)1);
            user.setCreateTime(LocalTime.now().toNanoOfDay());
            list.add(user);
        }
        System.out.println("开始2-----");
        l = System.currentTimeMillis();
        for(SysUser sysUser : list){
            sysUserMapper.addUsers(sysUser);
        }
        System.out.println("结束2-----"+(System.currentTimeMillis()-l));

    }
}
