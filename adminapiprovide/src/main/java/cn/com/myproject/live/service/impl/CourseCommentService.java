package cn.com.myproject.live.service.impl;

import cn.com.myproject.live.entity.PO.CourseComment;
import cn.com.myproject.live.mapper.CourseCommentMapper;
import cn.com.myproject.live.service.ICourseCommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by JYP on 2017/8/14 0014.
 */
@Service
public class CourseCommentService implements ICourseCommentService {

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Override
    public PageInfo<CourseComment> getPage(int pageNum, int pageSize) {
       List<CourseComment> list =courseCommentMapper.getPage(pageNum, pageSize);
       if(list != null && list.size() > 0){
           for(int i = 0;i < list.size();i++){
               try {
                   // 展示之前进行解码
                   list.get(i).setCommentContent(java.net.URLDecoder.decode(list.get(i).getCommentContent(), "UTF-8"));
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }
           }
       }
        return  new PageInfo(list);
    }

    @Override
    public CourseComment getById(String commid) {
        return courseCommentMapper.getById(commid);
    }

    @Transactional
    @Override
    public void delcomm(String commid) {
        courseCommentMapper.delcomm(commid);
    }

    @Transactional
    @Override
    public void addcomm(CourseComment courseComment) {
        courseComment.setCourseCommentId(UUID.randomUUID().toString().replace("-", ""));//随机产生的
        courseComment.setCourseId(courseComment.getCourseId());
        courseComment.setCommentTime(new Date().getTime());
        courseComment.setUserId("");//获取当前登录人id
        courseCommentMapper.addcomm(courseComment);
    }

    @Override
    public Integer checkcomment(CourseComment courseComment) {
        return courseCommentMapper.checkcomment(courseComment);
    }
}
