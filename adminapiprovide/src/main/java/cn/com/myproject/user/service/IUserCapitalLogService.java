package cn.com.myproject.user.service;


import cn.com.myproject.user.entity.PO.UserCapitalLog;

import java.util.Map;
import java.util.List;

public interface IUserCapitalLogService {

    int deleteByPrimaryKey(String bid);

    int insert(UserCapitalLog record);

    int insertSelective(UserCapitalLog record);

    UserCapitalLog selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(UserCapitalLog record);

    int updateByPrimaryKey(UserCapitalLog record);

    List<Map<String, Object>> getUserCapitalAccount(UserCapitalLog record);

    List<UserCapitalLog> selectTopUpList(int pageNum, int pageSize, String userId);

    List<UserCapitalLog> selectWithList(int pageNum, int pageSize, String userId);

    List<UserCapitalLog> selectAllList(int pageNum,int pageSize,String userId);

}