package cn.com.myproject.netease;

import cn.com.myproject.netease.VO.ResultInfo;
import cn.com.myproject.netease.VO.ResultUinfos;
import cn.com.myproject.netease.VO.account.IMCreateVO;
import cn.com.myproject.netease.VO.account.IMGetUinfosVO;
import cn.com.myproject.netease.service.IAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private IAccountService accountService;

    @Test
    public void testgetUinfos() {
        IMGetUinfosVO vo = new IMGetUinfosVO();
        List<String> list = new ArrayList<>();
        list.add("6e7d77e7331e4a5680274b4b4f835475");
        vo.setAccids(list);
        ResultUinfos uinfos = accountService.getUinfos(vo);
        Assert.assertEquals(uinfos.getCode()+"","200");
    }
    @Test
    public void testcreate() {
        IMCreateVO vo = new IMCreateVO();
        vo.setAccid("honglan1010");
        vo.setName("honglan1010");
        vo.setToken("honglan1010");
        ResultInfo info = accountService.create(vo);
        Assert.assertEquals(info.getCode()+"","200");
    }
}
