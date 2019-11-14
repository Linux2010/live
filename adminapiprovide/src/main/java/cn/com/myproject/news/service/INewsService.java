package cn.com.myproject.news.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.user.entity.PO.News;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/15.
 */


public interface INewsService {
    void addNews(News news);
    void delNews(String businessId);
    void updateNews(News news);
    News selectNewsById(String businessId);
    List<News> getAll();
    PageInfo<News> getPage(int pageNum, int pageSize, String title);
    List<News> searchNewsList(int pageNum, int pageSize, String title);
    int updateNewsTitle(News news);
}
