package cn.com.myproject.news.service.impl;

import cn.com.myproject.news.mapper.PointRecordMapper;
import cn.com.myproject.news.service.IPointRecordService;
import cn.com.myproject.user.entity.PO.PointRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LSG on 2017/9/2 0002.
 */
@Service
public class PointRecordService implements IPointRecordService{

    @Autowired
    private PointRecordMapper pointRecordMapper;

    @Override
    public PointRecord selectByUserId(String userId, String informationId) {

        return pointRecordMapper.selectByUserId(userId, informationId);
    }

    @Override
    public void addPointRecord(PointRecord pointRecord) {

        pointRecordMapper.addPointRecord(pointRecord);
    }
}
