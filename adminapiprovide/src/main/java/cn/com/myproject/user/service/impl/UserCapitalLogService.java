package cn.com.myproject.user.service.impl;

import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.mapper.UserCapitalLogMapper;
import cn.com.myproject.user.mapper.UserCapitalMapper;
import cn.com.myproject.user.service.IUserCapitalLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@Service
public class UserCapitalLogService implements IUserCapitalLogService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserCapitalLogMapper userCapitalLogMapper;

    @Override
    public int deleteByPrimaryKey(String bid) {
        return userCapitalLogMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insert(UserCapitalLog record) {
        return userCapitalLogMapper.insert(record);
    }

    @Override
    public int insertSelective(UserCapitalLog record) {
        return userCapitalLogMapper.insertSelective(record);
    }

    @Override
    public UserCapitalLog selectByPrimaryKey(String bid) {
        return userCapitalLogMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int updateByPrimaryKeySelective(UserCapitalLog record) {
        return userCapitalLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserCapitalLog record) {
        return userCapitalLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getUserCapitalAccount(UserCapitalLog record) {
        return userCapitalLogMapper.getUserCapitalAccount(record);
    }

    @Override
    public List<UserCapitalLog> selectTopUpList(int pageNum, int pageSize, String userId) {
        return userCapitalLogMapper.selectTopUpList(pageSize, pageNum, userId);
    }

    @Override
    public List<UserCapitalLog> selectWithList(int pageNum, int pageSize, String userId) {
        return userCapitalLogMapper.selectWithList(pageNum, pageSize, userId);
    }

    @Override
    public List<UserCapitalLog> selectAllList(int pageNum, int pageSize, String userId) {
        return userCapitalLogMapper.selectAllList(pageNum,pageSize,userId);
    }
}
