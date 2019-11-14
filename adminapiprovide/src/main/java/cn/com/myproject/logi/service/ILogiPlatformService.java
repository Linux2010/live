package cn.com.myproject.logi.service;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jyp on 2017/9/14 0014.
 */
public interface ILogiPlatformService {

    PageInfo<LogiPlatform> getPlatList(int pageNum, int pageSize);

    LogiPlatform selectById(String platId);

    void addplat(LogiPlatform logiPlatform);

    void updateplat(LogiPlatform logiPlatform);

    void delplat(String platId);

    LogiPlatform selectOpenPlat();
}
