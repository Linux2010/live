package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.Article;
import cn.com.myproject.user.entity.PO.News;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.com.myproject.news.service.INewsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 李延超 on 2017/8/14.
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    public static final Logger logger = LoggerFactory.getLogger(NewsController .class);
@Autowired
  private   INewsService newsService;




@GetMapping("/getAll")
public List<News> getAll(){
    List<News> list = newsService.getAll();
    return list;
}
 @PostMapping("/selectNewsById")
    public News  selectNewsById(String businessId){
     return newsService.selectNewsById(businessId);
 }

    @PostMapping("/updateNews")
    public String updateNews(@RequestBody News news){

        String result = "";
        try {
            newsService.updateNews(news);
            result = "1";
        }catch (RuntimeException e){
            e.printStackTrace();
            result = "0";
        }
        return result;
    }



    @PostMapping("/delNewsById")
    public void delNewsById(String businessId) {
        newsService.delNews(businessId);
    }

    @PostMapping("/addNews")
    public int addNews(@RequestBody News news) {
        int result = 0;
        try {
            news.setBusinessId(UUID.randomUUID().toString().replace("-", ""));
            news.setVersion(1);
            news.setStatus((short)1);
            news.setCreateTime(new Date().getTime());
            newsService.addNews(news);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }




    @GetMapping("/searchAllNewsList")
    public PageInfo<News> searchAllNewsList(int pageNum, int pageSize, String title){
        return newsService.getPage(pageNum,pageSize,title);
    }

    @GetMapping("/searchNewsList")
    public List<News> searchNewsList(int pageNum, int pageSize, String title){
        return newsService.searchNewsList(pageNum,pageSize,title);
    }

    @GetMapping("/searchNewsById")
    public News searchCourseById(String businessId){
        return newsService.selectNewsById(businessId);
    }

    @PostMapping("/updateNewsTitle")
    public int updateNewsTitle(@RequestBody News news){

        int result = 0;
        try {
            newsService.updateNewsTitle(news);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

}
