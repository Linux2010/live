package cn.com.myproject.user.service;
import cn.com.myproject.user.entity.PO.UserType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
 * Created by LeiJia on 2017-08-21
 * desc：教师分类Service接口
 */
public interface IUserTypeService {
    public boolean addUserType(UserType userType);

    public boolean removeUserType(String userTypeId);

    public boolean modifyUserType(UserType userType);

    public PageInfo<UserType> searchUserTypeList(int pageNum,
                                                int pageSize,
                                                 String typeName,
                                                int typeVal,
                                                String parentId);
    public List<UserType> searchTeacherTypeList( int typeVal, String parentId,String level);

    public UserType searchUserTypeById(String userTypeId);

    public List<UserType> searchUserTypeByParentId(String parentId);

}