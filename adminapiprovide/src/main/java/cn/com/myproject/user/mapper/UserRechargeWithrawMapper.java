package cn.com.myproject.user.mapper;

import cn.com.myproject.user.entity.PO.UserRechargeWithraw;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRechargeWithrawMapper {

    int deleteByPrimaryKey(String rwId);

    int insert(UserRechargeWithraw record);

    int insertSelective(UserRechargeWithraw record);

    UserRechargeWithraw selectByPrimaryKey(String rwId);

    int updateByPrimaryKeySelective(UserRechargeWithraw record);

    int updateByPrimaryKey(UserRechargeWithraw record);

}