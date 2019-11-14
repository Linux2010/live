package cn.com.myproject.live.service;


import cn.com.myproject.live.entity.PO.CourseComment;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liyang-macbook on 2017/6/30.
 */

public interface ICourseCommentService {

    PageInfo<CourseComment> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    CourseComment getById(String commid);

    void delcomm(String commid);

    void addcomm(CourseComment courseComment);

    Integer checkcomment(CourseComment courseComment);
}


