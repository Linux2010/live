package cn.com.myproject.live.service.impl;


import cn.com.myproject.live.entity.PO.Robot;
import cn.com.myproject.live.mapper.RobotMapper;
import cn.com.myproject.live.service.IRobotService;
import cn.com.myproject.netease.VO.ResultInfo;
import cn.com.myproject.netease.VO.account.IMCreateVO;
import cn.com.myproject.netease.service.IAccountService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by liyang-macbook on 2017/7/20.
 */
@Service
public class RobotService implements IRobotService {
    private static final Logger logger = LoggerFactory.getLogger(RobotService.class);
    @Autowired
    private RobotMapper robotMapper;

    @Autowired
    private IAccountService accountService;

    @Transactional
    @Override
    public void create() {
        //1、插入记录
        Robot robot = new Robot();
        String robotId = UUID.randomUUID().toString().replace("-","");
        String name = "游客a"+robotId.substring(28);
        robot.setRobotId(robotId);
        robot.setCreateTime(Calendar.getInstance().getTimeInMillis());
        robot.setVersion(1);
        robot.setStatus((short)1);
        robot.setName(name);
        int num = robotMapper.insert(robot);
        if(num != 1) {
            logger.error("插入数据失败,"+robotId);
            throw new RuntimeException("插入数据失败,"+robotId);
        }
        //2、请求网易云，创建IM账号
        IMCreateVO vo = new IMCreateVO();
        vo.setAccid(robotId);
        vo.setName(name);
        ResultInfo resultInfo = accountService.create(vo);
        if(resultInfo.getCode() != 200) {
            logger.error("创建网易IM账号失败,"+resultInfo.getCode()+","+resultInfo.getDesc());
            throw new RuntimeException("创建网易IM账号失败,"+resultInfo.getCode()+","+resultInfo.getDesc());
        }
        //3、更新记录
        robot = new Robot();
        robot.setName(resultInfo.getInfo().get("name"));
        robot.setAccid(resultInfo.getInfo().get("accid"));
        robot.setAccidToken(resultInfo.getInfo().get("token"));
        robot.setRobotId(robotId);
        num = robotMapper.update(robot);
        if(num != 1) {
            logger.error("更新数据失败,"+robotId);
            throw new RuntimeException("更新数据失败,"+robotId);
        }
    }

    @Override
    public PageInfo<Robot> getPage(int pageNum, int pageSize){
        List<Robot> list = this.robotMapper.getList(pageNum,pageSize);
        return new PageInfo<Robot>(list);
    }
}
