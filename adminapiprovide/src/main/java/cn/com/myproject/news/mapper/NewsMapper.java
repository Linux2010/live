package cn.com.myproject.news.mapper;

import cn.com.myproject.user.entity.PO.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.com.myproject.news.service.INewsService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/14.
 */

@Mapper
public interface NewsMapper {



    //List<News> getAll(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<News> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("title") String title);


    void addNews(@RequestBody News news);

    void delNews(String businessId);

    void updateNews(News news);

    News selectNewsById(String businessId);


    List<News> getAll();

    int updateNewsTitle(News news);
}

