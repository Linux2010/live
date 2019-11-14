package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.Gift;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@Mapper
public interface GiftMapper {

    Gift getGiftByGiftId(String giftId);

}
