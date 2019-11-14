package cn.com.myproject.user.mapper;
import cn.com.myproject.user.entity.PO.UserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserTypeMapper {

    /**
     * 插入讲师分类
     *
     * @param userType
     * @return
     */
    public int insertUserType(UserType userType);

    /**
     * 根据userTypeId删除讲师分类
     *
     * @param userTypeId
     * @return
     */
    public int deleteUserType(@Param("userTypeId") String userTypeId);

    /**
     * 修改讲师分类
     *
     * @param userType
     * @return
     */
    public int updateUserType(UserType userType);

    /**
     * 分页查询讲师分类列表
     *
     * @param pageNum
     * @param pageSize
     * @param typeName
     * @param typeVal
     * @return
     */
    public List<UserType> searchUserTypeList(@Param("pageNumKey") int pageNum,
                                         @Param("pageSizeKey") int pageSize,
                                         @Param("typeName") String typeName,
                                         @Param("typeVal") int typeVal,
                                         @Param("parentId") String parentId);
    /**
     * 查询讲师分类列表
     * @param typeVal
     * @return
     */
    public List<UserType> searchTeacherTypeList( @Param("typeVal") int typeVal,
                                             @Param("parentId") String parentId,
                                              @Param("level") String level);

    /**
     * 根据ID查询讲师分类信息
     *
     * @param userTypeId
     * @return
     */
    public UserType searchUserTypeById(@Param("userTypeId") String userTypeId);

    /**
     * 根据父节点查询子节点信息
     *
     * @param parentId
     * @return
     */
    public List<UserType> searchUserTypeByParentId(@Param("parentId") String parentId);
}
