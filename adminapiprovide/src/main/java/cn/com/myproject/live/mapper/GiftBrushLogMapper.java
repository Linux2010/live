package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.live.entity.GiftBushLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@Mapper
public interface GiftBrushLogMapper {
     int insertGiftBushLog(GiftBushLog log);
}
