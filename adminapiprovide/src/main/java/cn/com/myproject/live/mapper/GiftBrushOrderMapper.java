package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.Gift;
import cn.com.myproject.live.entity.PO.live.entity.GiftBrushOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@Mapper
public interface GiftBrushOrderMapper {
     int insertGiftOrder(GiftBrushOrder order);
}
