package cn.com.myproject.user.service;

import cn.com.myproject.user.entity.PO.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JYP on 2017/9/13 0013.
 */
public interface IUserAddressService {

    List<UserAddress> getAddressPage(int pageNum,int pageSize,String userId);

    void addAddress(UserAddress userAddress);

    void updateAddress(UserAddress userAddress);

    void delAddress(String addId);

    UserAddress selectById(String addId);

    UserAddress selectDefaultAdd(String userId);

    void updateChecked(String addId);
}
