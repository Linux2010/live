package cn.com.myproject.user.service.impl;

import cn.com.myproject.live.entity.PO.CourseType;
import cn.com.myproject.user.entity.PO.UserType;
import cn.com.myproject.user.mapper.UserTypeMapper;
import cn.com.myproject.user.service.IUserTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeiJia on 2017/8/21 0021.
 */
@Service
public class UserTypeService implements IUserTypeService {

    @Autowired
    private UserTypeMapper userTypeMapper;

    @Transactional
    public boolean addUserType(UserType userType){
        boolean flagVal = true;
        int countVal = userTypeMapper.insertUserType(userType);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }

    @Transactional
    public boolean removeUserType(String userTypeId){
        boolean flagVal = true;
        int countVal = userTypeMapper.deleteUserType(userTypeId);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    @Transactional
    public boolean modifyUserType(UserType userType){
        boolean flagVal = true;
        int countVal = userTypeMapper.updateUserType(userType);
        if(countVal == 0){
            flagVal = false;
        }
        return flagVal;
    }
    public PageInfo<UserType> searchUserTypeList(int pageNum, int pageSize, String typeName, int typeVal, String parentId){
        List<UserType> ctList = userTypeMapper.searchUserTypeList(pageNum,pageSize,typeName,typeVal,parentId);
        return new PageInfo<UserType>(ctList);
    }

    public List<UserType> searchTeacherTypeList( int typeVal, String parentId,String level){
        List<UserType> ctList = userTypeMapper.searchTeacherTypeList(typeVal,parentId,level);
        return ctList;
    }


    public UserType searchUserTypeById(String userTypeId){
        return userTypeMapper.searchUserTypeById(userTypeId);
    }

    public List<UserType> searchUserTypeByParentId(String parentId){
        return userTypeMapper.searchUserTypeByParentId(parentId);
    }

}
