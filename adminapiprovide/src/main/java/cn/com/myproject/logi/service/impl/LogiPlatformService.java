package cn.com.myproject.logi.service.impl;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import cn.com.myproject.logi.mapper.LogiPlatformMapper;
import cn.com.myproject.logi.service.ILogiPlatformService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by JYP on 2017/9/14 0014.
 */
@Service
public class LogiPlatformService implements ILogiPlatformService{

    @Autowired
    private LogiPlatformMapper logiPlatformMapper;

    @Override
    public PageInfo<LogiPlatform> getPlatList(int pageNum, int pageSize) {
        List<LogiPlatform> list = logiPlatformMapper.getPlatList(pageNum,pageSize);
        return new PageInfo(list);
    }

    @Override
    public LogiPlatform selectById(String platId) {
        return logiPlatformMapper.selectById(platId);
    }

    @Override
    public void addplat(LogiPlatform logiPlatform) {
        logiPlatform.setPlatId(UUID.randomUUID().toString().replace("-",""));
        logiPlatformMapper.addplat(logiPlatform);
    }

    @Override
    public void updateplat(LogiPlatform logiPlatform) {
        logiPlatformMapper.updateplat(logiPlatform);
    }

    @Override
    public void delplat(String platId) {
        logiPlatformMapper.delplat(platId);
    }

    @Override
    public LogiPlatform selectOpenPlat() {
        return logiPlatformMapper.selectOpenPlat();
    }
}
