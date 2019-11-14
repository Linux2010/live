package cn.com.myproject.user.service;

import cn.com.myproject.user.entity.PO.UserRechargeWithraw;

/**
 * @auther CQC
 * @create 2017.8.31
 */
public interface IUserRechargeWithrawService {

    int deleteByPrimaryKey(String rwId);

    int insert(UserRechargeWithraw record);

    int insertSelective(UserRechargeWithraw record);

    UserRechargeWithraw selectByPrimaryKey(String rwId);

    int updateByPrimaryKeySelective(UserRechargeWithraw record);

    int updateByPrimaryKey(UserRechargeWithraw record);

    int setPayFinishRecharge(String rwId,String transactionId);

}
