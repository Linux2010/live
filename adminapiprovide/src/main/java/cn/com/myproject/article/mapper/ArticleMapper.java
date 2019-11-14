package cn.com.myproject.article.mapper;

import cn.com.myproject.user.entity.PO.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LSG on 2017/8/7 0007.
 */
@Mapper
public interface ArticleMapper {

    void addArticle(Article article);

    void delArticleById(String articleId);

    void updateArticle(Article article);

    Article selectArticleById(String articleId);

    List<Article> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("keyword") String keyword);

    void updateContent(Article article);

    List<Article> allArticle();

    Article selectByStatus(int status);

    void updateStatus(Article article);

    int selectCount(int status);
}

