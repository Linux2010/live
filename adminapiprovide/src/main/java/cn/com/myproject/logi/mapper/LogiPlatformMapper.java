package cn.com.myproject.logi.mapper;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jyp on 2017/9/14 0014.
 */
@Mapper
public interface LogiPlatformMapper {

    List<LogiPlatform> getPlatList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    LogiPlatform selectById(@Param("platId") String platId);

    void addplat(LogiPlatform logiPlatform);

    void updateplat(LogiPlatform logiPlatform);

    void delplat(@Param("platId") String platId);

    /**
     * 查询正在启用的物流平台
     * @return
     */
    LogiPlatform selectOpenPlat();
}
