package cn.com.myproject.news.mapper;

import cn.com.myproject.user.entity.PO.PointRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LSG on 2017/9/2 0002.
 */
@Mapper
public interface PointRecordMapper {

    PointRecord selectByUserId(@Param("userId") String userId, @Param("informationId") String informationId);

    void addPointRecord(PointRecord pointRecord);
}


