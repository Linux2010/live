package cn.com.myproject.user.mapper;


import cn.com.myproject.user.entity.PO.UserCapital;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCapitalLogMapper {

    int deleteByPrimaryKey(String capitalLogId);

    int insert(UserCapitalLog record);

    int insertSelective(UserCapitalLog record);

    UserCapitalLog selectByPrimaryKey(String capitalLogId);

    int updateByPrimaryKeySelective(UserCapitalLog record);

    int updateByPrimaryKey(UserCapitalLog record);

    List<Map<String,Object>> getUserCapitalAccount(UserCapitalLog record);

    //查询个人的充值列表
    List<UserCapitalLog> selectTopUpList(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize,@Param("userId") String userId);

    //查询个人的提现列表
    List<UserCapitalLog> selectWithList(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize,@Param("userId") String userId);

    List<UserCapitalLog> selectAllList(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize,@Param("userId") String userId);
}