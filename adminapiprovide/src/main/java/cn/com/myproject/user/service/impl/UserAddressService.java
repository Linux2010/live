package cn.com.myproject.user.service.impl;

import cn.com.myproject.user.entity.PO.UserAddress;
import cn.com.myproject.user.mapper.UserAddressMapper;
import cn.com.myproject.user.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by JYP on 2017/9/13 0013.
 */
@Service
public class UserAddressService implements IUserAddressService{

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getAddressPage(int pageNum, int pageSize,String userId) {
        return userAddressMapper.getAddressPage(pageNum,pageSize,userId);
    }

    @Override
    public void addAddress(UserAddress userAddress) {
        userAddress.setAddId(UUID.randomUUID().toString().replace("-", ""));
        userAddress.setCreateTime(new Date().getTime());
        userAddress.setStatus((short)1);
        userAddress.setVersion(1);
        userAddressMapper.addAddress(userAddress);
    }

    @Override
    public void updateAddress(UserAddress userAddress) {
        userAddressMapper.updateAddress(userAddress);
    }

    @Override
    public void delAddress(String accId) {
        userAddressMapper.delAddress(accId);
    }

    @Override
    public UserAddress selectById(String addId) {
        return userAddressMapper.selectById(addId);
    }

    @Override
    public UserAddress selectDefaultAdd(String userId) {
        return userAddressMapper.selectDefaultAdd(userId);
    }

    @Override
    public void updateChecked(String addId) {
         userAddressMapper.updateChecked(addId);
    }
}
