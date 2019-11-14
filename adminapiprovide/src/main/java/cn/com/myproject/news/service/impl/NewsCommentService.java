package cn.com.myproject.news.service.impl;

import cn.com.myproject.news.mapper.NewsCommentMapper;
import cn.com.myproject.news.service.ImplNewsCommentService;
import cn.com.myproject.user.entity.PO.PointRecord;
import cn.com.myproject.user.entity.PO.NewsCollect;
import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.PO.NewsReply;
import cn.com.myproject.user.entity.VO.NewsReplyVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李延超 on 2017/8/22.
 */@Service
public class NewsCommentService implements ImplNewsCommentService {

    @Autowired
    private NewsCommentMapper newsCommentMapper;

    @Override
    public PageInfo<NewsComment> getAll(int pageNum,int pageSize) {
        List<NewsComment> list = newsCommentMapper.getAll(pageNum,pageSize);
        if(list != null && list.size() > 0){
            for(int i = 0;i < list.size();i++){
                try {
                    // 展示之前进行解码
                    list.get(i).setNewsContent(java.net.URLDecoder.decode(list.get(i).getNewsContent(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return convert(list);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<NewsComment> convert(List<NewsComment> list) {
        PageInfo<NewsComment> info = new PageInfo(list);
        List<NewsComment> _list = info.getList();
        info.setList(null);
        List<NewsComment> __list = new ArrayList<>(10);
        PageInfo<NewsComment> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(NewsComment c : _list) {
                __list.add(c);
            }
            _info.setList(__list);
        }
        return _info;
    }

    @Override
    public void addNewsComment(NewsComment newsComment) {
        newsCommentMapper.addNewsComment(newsComment);
    }
    @Override
    public void delNewsComment(Integer id){
       newsCommentMapper.delNewsComment(id);
    }
    @Override
    public      NewsComment selectById(Integer id){
        return  newsCommentMapper.selectById(id);
    }

@Override
    public NewsComment getById(Integer id){
        return newsCommentMapper.getById(id);
}


  @Override
    public int updateNumber(PointRecord pointRecord){
        return newsCommentMapper.updateNumber(pointRecord);
  }

  @Override
  public PageInfo<NewsComment> getPage(int pageNum ,int pageSize)  {
        List<NewsComment> list=newsCommentMapper.getPage( pageNum,  pageSize);
        return  convert(list);
    }
    @Transactional
    @Override
    public void delcomm(String newscommentId){
        newsCommentMapper.delcomm(newscommentId);
    }

    @Override
    public NewsComment getById(String newsCommentId){
        return newsCommentMapper.getById(newsCommentId);
    }

//@Transactional
//    @Override
//    public boolean addcomm(NewsComment newsComment){
//    newsComment.setNewsCommentId(UUID.randomUUID().toString().replace("-", ""));//随机产生的
//    newsComment.setNewsId(newsComment.getNewsId());
//    newsComment.setCommentTime(new Date().getTime());
//    newsComment.setUserId("");
//    newsCommentMapper.addcomm(newsComment);
//    }
    @Override
    public Integer checkcomment(NewsComment newsComment){
        return newsCommentMapper.checkcomment(newsComment);
    }

    @Override
    @Transactional
    public boolean addCn(NewsCollect newsCollect){
      boolean flagVal=false;
      int countVal=newsCommentMapper.insertCn(newsCollect);
        if(countVal>0){
            flagVal=true;
        }
       return  flagVal;
    }
    @Override
    @Transactional
    public boolean removeCn(NewsCollect newsCollect){
       boolean flagVal=false;
       int countVal=newsCommentMapper.deleteCn(newsCollect);
       if(countVal>0){
           flagVal=true;
       }
       return  flagVal;
    }
    @Override
    public NewsCollect searcUserNewCollect(String userId ,String newsId){
        return  newsCommentMapper.searcUserNewCollect(userId,newsId);
    }


    /**
     * 根据id查询评论列表
     *
     * @param pageNum
     * @param pageSize
     * @param newsId
     * @return
     */
    @Override
    public List<NewsComment> searchCcList(int pageNum, int pageSize, String newsId){
        List<NewsComment> list = newsCommentMapper.searchCcList(pageNum,pageSize,newsId);
       return  list;
    }

    /**
     * 根据评论ID查询评论回复列表
     *
     * @param newsCommentId
     * @return
     */
    @Override
    public List<NewsReplyVO> searchCrList(String newsCommentId){
        return newsCommentMapper.searchCrList(newsCommentId);
    }

    /**
     * 添加评论
     *
     * @param newsReply
     * @return
     */
    @Override
    @Transactional


    public  void addReply(NewsReply newsReply){
         newsCommentMapper.addReplyAdmin(newsReply);
    }


/**
 * 更改收藏
 *
 */
@Override
 public boolean  updatecollect (NewsCollect collect){
     return  newsCommentMapper.updatecollect(collect);
 }

    @Override
    public void replyNewsComment(NewsComment newsComment) {
        newsCommentMapper.replyNewsComment(newsComment);
    }

    @Override
    public NewsReply selectReply(String replyId) {

     return newsCommentMapper.selectReply(replyId);

    }

    //添加评论
    @Override
    public void addComment(NewsComment newsComment){
      newsCommentMapper.addComm1(newsComment);
    }

    @Override
    public void updateCommentContent(NewsComment newsComment) {
        newsCommentMapper.updateCommentContent(newsComment);
    }
}
