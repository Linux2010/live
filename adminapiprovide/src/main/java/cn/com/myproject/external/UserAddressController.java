package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.UserAddress;
import cn.com.myproject.user.service.IUserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JYP on 2017/9/13 0013.
 */
@RestController
@RequestMapping("/user_address")
public class UserAddressController {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private IUserAddressService userAddressService;

    /**
     *查询地址列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    public List<UserAddress> list(int pageNum,int pageSize,String userId){
        return  userAddressService.getAddressPage(pageNum,pageSize,userId);
    };

    /**
     * 收货地址的添加
     * @return
     */
    @PostMapping("/add_address")
    public int add_address(@RequestBody UserAddress userAddress){
        int info = 0;
        try{
            userAddressService.addAddress(userAddress);
            info = 1;
        }catch (Exception e){
            logger.error("adminprovide-UserAddressController-add_address-添加地址:"+e);
        }
        return  info;
    }

    /**
     * 收货地址的修改
     * @return
     */
    @PostMapping("/update_address")
    public int update_address(@RequestBody UserAddress userAddress){
        int info = 0;
        try{
            userAddressService.updateAddress(userAddress);
            info = 1;
        }catch (Exception e){
            logger.error("adminprovide-UserAddressController-update_address-修改地址:"+e);
        }
        return  info;
    }

    /**
     * 删除收货地址
     *
     * @param addId
     * @return
     */
    @PostMapping("/del_address")
    public int del_address(String addId){
        int info = 0;
        try{
            userAddressService.delAddress(addId);
            info = 1;
        }catch (Exception e){
            logger.error("adminprovide-UserAddressController-del_address-删除地址:"+e);
        }
        return  info;
    }

    /**
     * 查询详情
     * @param addId
     * @return
     */
    @PostMapping("/selectById")
    public UserAddress selectById(String addId){
        return userAddressService.selectById(addId);
    }

    /**
     * 查询正在使用的
     * @param userId
     * @return
     */
    @PostMapping("/selectDefaultAdd")
    public UserAddress selectDefaultAdd(String userId){
        return userAddressService.selectDefaultAdd(userId);
    }

    @PostMapping("/updateChecked")
    public void updateChecked(String addId) {userAddressService.updateChecked(addId);
    }


}
