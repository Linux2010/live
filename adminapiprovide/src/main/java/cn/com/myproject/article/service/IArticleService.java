package cn.com.myproject.article.service;

import cn.com.myproject.user.entity.PO.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * Created by Administrator on 2017/8/7 0007.
 */
public interface IArticleService {

    void addArticle(Article article);

    void delArticleById(String articleId);

    void updateArticle(Article article);

    Article selectArticleById(String articleId);

    PageInfo<Article> getPage(int pageNum, int pageSize, String keyword);

    void updateContent(Article article);

    List<Article> allArticle();

    Article selectByStatus(int status);

    void updateStatus(Article article);

    int selectCount(int status);
}
