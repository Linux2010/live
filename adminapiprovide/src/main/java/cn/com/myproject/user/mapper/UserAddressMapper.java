package cn.com.myproject.user.mapper;

import cn.com.myproject.user.entity.PO.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JYP on 2017/9/13 0013.
 */
@Mapper
public interface UserAddressMapper {

    List<UserAddress> getAddressPage(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize,@Param("userId") String userId);

    void addAddress(UserAddress userAddress);

    void updateAddress(UserAddress userAddress);

    void delAddress(@Param("addId") String addId);

    UserAddress selectById(@Param("addId") String addId);

    /**
     * 查询正在使用的默认地址
     * @param userId
     * @return
     */
    UserAddress selectDefaultAdd(@Param("userId") String userId);

    /**
     * 修改正在默认的地址的状态
     * @param addId
     * @return
     */
    void updateChecked(@Param("addId") String addId);
}
