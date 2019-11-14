package cn.com.myproject.external;

import cn.com.myproject.article.service.IArticleService;
import cn.com.myproject.user.entity.PO.Article;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    public static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private IArticleService articleService;

   @PostMapping("/getPage")
   public PageInfo<Article> selectArticles(int pageNum, int pageSize, String keyword){

       return articleService.getPage(pageNum, pageSize, keyword);
   }

   @PostMapping("/addArticle")
    public int  addArticle(@RequestBody Article article){

        int result = 0;
       try {
           article.setArticleId(UUID.randomUUID().toString().replace("-", ""));
            article.setVersion(1);
            article.setStatus((short)1);
            article.setCreateTime(new Date().getTime());
            articleService.addArticle(article);
            result = 1;
       }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
       }
       return result;
   }

   @PostMapping("/delArticleById")
    public int delArticleById(String articleId){

        int result = 0;
        try {
            articleService.delArticleById(articleId);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
   }

   @PostMapping("/selectArticleById")
    public Article selectArticleById(String articleId){
        return articleService.selectArticleById(articleId);
   }

    @PostMapping("/updateArticle")
    public int updateArticle(@RequestBody Article article){

        int result = 0;
        try {
            articleService.updateArticle(article);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/addArticleContent")
    public int addArticleContent(@RequestBody Article article){

        int result = 0;
        try {
            article.setContent(article.getContent());
            article.setShowContent(article.getShowContent());
            articleService.updateContent(article);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @GetMapping("/allArticle")
    public List<Article> allArticle(){

        return articleService.allArticle();
    }

    @PostMapping("/selectByStatus")
    public Article selectByStatus(@RequestBody int status){

        return articleService.selectByStatus(status);
    }

    @PostMapping("/updateStatus")
    public int updateStatus(@RequestBody Article article){

        int result = 0;
        try {
            articleService.updateStatus(article);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectCount")
    public int selectCount(@RequestBody int status){

        return articleService.selectCount(status);
    }
}















