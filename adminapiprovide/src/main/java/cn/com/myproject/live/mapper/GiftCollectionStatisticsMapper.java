package cn.com.myproject.live.mapper;
import cn.com.myproject.live.entity.PO.live.entity.GiftCollectionStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LeiJia on 2017/7/5.
 */
@Mapper
public interface GiftCollectionStatisticsMapper {

     int insertGiftCollectionStatistics(GiftCollectionStatistics statistics);

     int updateGiftCollectionStatistics(GiftCollectionStatistics statistics);

     GiftCollectionStatistics getGiftCollectionStatisticsByUserId(GiftCollectionStatistics statistics);

     List<GiftCollectionStatistics> giftTotalTopTenList(@Param("imRoomId") String imRoomId);

     List<GiftCollectionStatistics>  giftEveryDayTopTenList(@Param("imRoomId") String imRoomId);
}
