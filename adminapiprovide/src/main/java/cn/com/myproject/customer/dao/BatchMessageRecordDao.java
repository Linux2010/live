package cn.com.myproject.customer.dao;

import cn.com.myproject.customer.entity.PO.MessageRecord;

import java.util.List;


public interface BatchMessageRecordDao {
    void saveBatch(List<MessageRecord> list);
}
