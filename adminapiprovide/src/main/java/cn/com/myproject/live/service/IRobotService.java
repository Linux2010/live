package cn.com.myproject.live.service;

import cn.com.myproject.live.entity.PO.Robot;
import com.github.pagehelper.PageInfo;

/**
 * Created by liyang-macbook on 2017/7/20.
 */
public interface IRobotService {

    void create();

    PageInfo<Robot> getPage(int pageNum, int pageSize);
}
