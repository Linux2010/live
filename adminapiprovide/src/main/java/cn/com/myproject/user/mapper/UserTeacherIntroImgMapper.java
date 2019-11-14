package cn.com.myproject.user.mapper;
import cn.com.myproject.user.entity.PO.UserTeacherIntroImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTeacherIntroImgMapper {

    /**
     * 根据ID查询教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    public UserTeacherIntroImg searchUserTeacherIntroImgById(@Param("teacherIntroImgId") String teacherIntroImgId);

    /**
     * 插入教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    public int insertUserTeacherIntroImg(UserTeacherIntroImg userTeacherIntroImg);

    /**
     * 根据ID删除教头简介图片
     * @param teacherIntroImgId
     * @return
     */
    public int deleteUserTeacherIntroImg(@Param("teacherIntroImgId") String teacherIntroImgId);

    /**
     * 修改教头简介图片
     * @param userTeacherIntroImg
     * @return
     */
    public int updateUserTeacherIntroImg(UserTeacherIntroImg userTeacherIntroImg);

    /**
     * 分页查询教头简介图片列表
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @return
     */
    public List<UserTeacherIntroImg> searchUserTeacherIntroImgList(@Param("pageNumKey") int pageNum,
                                             @Param("pageSizeKey") int pageSize,
                                             @Param("teacherId") String teacherId);

    /**
     * 根据教头用户ID查询教头简介图片URL列表
     * @param teacherId
     * @return
     */
    public List<String> searchTeacherIntroImgs(@Param("teacherId") String teacherId);

}
