package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.UserType;
import cn.com.myproject.user.service.IUserTypeService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * Created by LeiJia on 2017-08-21
 * desc：教师分类服务控制器类
 */
@RestController
@RequestMapping("/userType")
public class UserTypeController {

    @Autowired
    private IUserTypeService userTypeService;

    @PostMapping("/addUserType")
    public boolean addUserType(@RequestBody UserType userType){
        if(StringUtils.isNotBlank(userType.getTypeName())){
            String user_type_id = UUID.randomUUID().toString().replace("-", "");
            userType.setUserTypeId(user_type_id);// 设置教师分类的ID
            userType.setCreateTime(new Date().getTime());// 默认当前时间
            userType.setVersion(1);// 默认第一版本
        }
        return userTypeService.addUserType(userType);
    }

    @PostMapping("/removeUserType")
    public boolean removeUserType(String userTypeId){
        return userTypeService.removeUserType(userTypeId);
    }

    @PostMapping("/modifyUserType")
    public boolean modifyUserType(@RequestBody UserType userType){
        return userTypeService.modifyUserType(userType);
    }

    @GetMapping("/searchUserTypeList")
    public PageInfo<UserType> searchUserTypeList(int pageNum,int pageSize,String typeName,int typeVal,String parentId){
        return userTypeService.searchUserTypeList(pageNum,pageSize,typeName,typeVal,parentId);
    }

    @GetMapping("searchUserTypeById")
    public UserType searchUserTypeById(String userTypeId){

        return userTypeService.searchUserTypeById(userTypeId);
    }

    @GetMapping("searchUserTypeByParentId")
    public List<UserType> searchUserTypeByParentId(String parentId){
        return userTypeService.searchUserTypeByParentId(parentId);
    }
    @GetMapping("/searchTeacherTypeList")
    public List<UserType> searchTeacherTypeList(int typeVal,String parentId ,String level){
        return userTypeService.searchTeacherTypeList(typeVal,parentId,level);
    }

}