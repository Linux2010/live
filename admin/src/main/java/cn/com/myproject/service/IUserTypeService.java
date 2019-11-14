package cn.com.myproject.service;
import cn.com.myproject.user.entity.PO.UserType;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * Created by LeiJia on 2017-08-21
 * desc：教师分类Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface IUserTypeService {

    @PostMapping("/userType/addUserType")
    public boolean addUserType(@RequestBody UserType userType);

    @PostMapping("/userType/removeUserType")
    public boolean removeUserType(@RequestParam("userTypeId") String userTypeId);

    @PostMapping("/userType/modifyUserType")
    public boolean modifyUserType(@RequestBody UserType userType);

    @GetMapping("/userType/searchUserTypeList")
    public PageInfo<UserType> searchUserTypeList(@RequestParam("pageNum") int pageNum,
                                                 @RequestParam("pageSize") int pageSize,
                                                 @RequestParam("typeName") String typeName,
                                                 @RequestParam("typeVal") int typeVal,
                                                 @RequestParam("parentId") String parentId);

    @GetMapping("/userType/searchUserTypeById")
    public UserType searchUserTypeById(@RequestParam("userTypeId") String userTypeId);

    @GetMapping("/userType/searchUserTypeByParentId")
    public List<UserType> searchUserTypeByParentId(@RequestParam("parentId") String parentId);

}