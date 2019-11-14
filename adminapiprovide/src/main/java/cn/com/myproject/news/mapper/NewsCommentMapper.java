package cn.com.myproject.news.mapper;

import cn.com.myproject.user.entity.PO.*;
import cn.com.myproject.user.entity.VO.NewsCommentVO;
import cn.com.myproject.user.entity.VO.NewsReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/22.
 */
@Mapper
public interface NewsCommentMapper {
    List<NewsComment> getAll(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);
    NewsComment selectById(Integer id);
    void delNewsComment(Integer id);
    void addNewsComment(@RequestBody NewsComment newsComment);
    NewsComment getById(Integer id);
    int updateNumber(@RequestBody PointRecord pointRecord);


    List<NewsComment> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    NewsComment getById(String newsCommentId);

    void delcomm(String newscommentId);
    void addcomm(NewsComment newsComment);
    Integer checkcomment(NewsComment newsComment);




    /**
     * 根据用户ID和资讯ID查询用户是否已收藏该课程
     *
     * @param userId
     * @param
     * @return
     */
    public NewsCollect searcUserNewCollect(@Param("userId") String userId, @Param("newsId") String newsId);


    /**
     * 插入资讯收藏记录
     *
     * @param newsCollect
     * @return
     */
    public int insertCn(NewsCollect newsCollect);

    /**
     * 删除资讯收藏记录
     *
     * @param
     * @return
     */
    public int deleteCn(NewsCollect newsCollect);

    /**
     * 根据课程ID查询课程评论列表
     *
     * @param newsId
     * @return
     */
    public List<NewsComment> searchCcList(@Param("pageNumKey") int pageNum,
                                            @Param("pageSizeKey") int pageSize,
                                            @Param("newsId") String newsId);

    /**
     * 根据评论ID查询评论回复列表
     *
     *ram ccId
     * @return
     */
    public List<NewsReplyVO> searchCrList(@Param("newscommentId") String newscommentId);

    /**
     * 插入回复
     *
     * @param newsReply
     * @return
     */
    void addReplyAdmin(NewsReply newsReply);


    public boolean updatecollect(NewsCollect collect);


    void replyNewsComment(NewsComment newsComment);

    NewsReply selectReply(String replyId);

     void addComm1(NewsComment newsComment);

     void updateCommentContent(NewsComment newsComment);
}
