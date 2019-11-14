package cn.com.myproject.news.service;

import cn.com.myproject.user.entity.PO.PointRecord;

/**
 * Created by LSG on 2017/9/2 0002.
 */
public interface IPointRecordService {

    PointRecord selectByUserId(String userId, String informationId);

    void addPointRecord(PointRecord pointRecord);
}
