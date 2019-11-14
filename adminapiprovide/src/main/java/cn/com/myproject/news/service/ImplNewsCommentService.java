package cn.com.myproject.news.service;

import cn.com.myproject.user.entity.PO.PointRecord;
import cn.com.myproject.user.entity.PO.NewsCollect;
import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.PO.NewsReply;
import cn.com.myproject.user.entity.VO.NewsCommentVO;
import cn.com.myproject.user.entity.VO.NewsReplyVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/22.
 */

public interface ImplNewsCommentService {
    PageInfo<NewsComment> getAll(int pageNum,int pageSize);

    NewsComment selectById(Integer id);

    void delNewsComment(Integer id);

    void addNewsComment(NewsComment newsComment);

    NewsComment getById(Integer id);


    int updateNumber(PointRecord pointRecord);

    PageInfo<NewsComment> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize) ;

  NewsComment getById(String NewsCommentId);
  void delcomm(String newsCommentId);
  Integer checkcomment(NewsComment newsComment);

    /**
     * 根据用户ID和课程ID查询用户是否收藏该资讯
     * @param userId
     * @param newsId
     * @return
     */
    public NewsCollect searcUserNewCollect(String userId ,String newsId);


    public boolean addCn(NewsCollect newsCollect);

    public boolean removeCn(NewsCollect newsCollect );


    /**
     * 根据咨询id查询咨询评论
     *
     * @param pageNum
     * @param pageSize
     * @param newsId
     * @return
     */
    public List<NewsComment> searchCcList(int pageNum, int pageSize, String newsId);

    /**
     * 根据评论ID查询评论回复列表
     *
     * @param newsCommentId
     * @return
     */
    public List<NewsReplyVO> searchCrList(String newsCommentId);

    /**
     * 添加回复
     *
     * @param newsReply
     * @return
     */
    void addReply(NewsReply newsReply);


    /**
     *
     * 更新收藏
     */



    public boolean updatecollect(NewsCollect newsCollect);

    void replyNewsComment(NewsComment newsComment);

    NewsReply selectReply(String replyId);

    //添加资讯评论
    void  addComment(NewsComment newsComment);

    void updateCommentContent(NewsComment newsComment);
}