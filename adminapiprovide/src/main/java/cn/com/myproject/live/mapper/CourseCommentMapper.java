package cn.com.myproject.live.mapper;

import cn.com.myproject.live.entity.PO.CourseComment;
import cn.com.myproject.live.entity.VO.CourseCommentVO;
import cn.com.myproject.live.entity.VO.CourseReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CourseCommentMapper {

    List<CourseComment> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    CourseComment getById(String commid);

    void delcomm(String commid);

    void addcomm(CourseComment courseComment);

    Integer checkcomment(CourseComment courseComment);

    /**
     * 根据课程ID查询课程评论列表
     *
     * @param courseId
     * @return
     */
    public List<CourseCommentVO> searchCcList(@Param("pageNumKey") int pageNum,
                                              @Param("pageSizeKey") int pageSize,
                                              @Param("courseId") String courseId);

    /**
     * 根据评论ID查询评论回复列表
     *
     * @param ccId
     * @return
     */
    public List<CourseReplyVO> searchCrList(@Param("ccId") String ccId);

    /**
     * 插入评论信息
     *
     * @param courseComment
     * @return
     */
    public int insertComm(CourseComment courseComment);

    /**
     * 查询课程评论总数量
     *
     * @param courseId
     * @return
     */
    int searchCcCount(@Param("courseId") String courseId);

}