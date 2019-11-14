package cn.com.myproject.article.service.impl;

import cn.com.myproject.article.mapper.ArticleMapper;
import cn.com.myproject.article.service.IArticleService;
import cn.com.myproject.user.entity.PO.Article;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
@Service
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {

        articleMapper.addArticle(article);
    }

    @Override
    public void delArticleById(String articleId) {

        articleMapper.delArticleById(articleId);
    }

    @Override
    public void updateArticle(Article article) {

        articleMapper.updateArticle(article);
    }

    @Override
    public Article selectArticleById(String articleId) {

        return articleMapper.selectArticleById(articleId);
    }

    @Override
    public PageInfo<Article> getPage(int pageNum, int pageSize, String keyword) {

        List<Article> list = articleMapper.getPage(pageNum, pageSize, keyword);
        return convert(list);
    }

    @Override
    public void updateContent(Article article) {

        articleMapper.updateContent(article);
    }

    @Override
    public List<Article> allArticle() {

        return articleMapper.allArticle();
    }

    private PageInfo<Article> convert(List<Article> list) {
        PageInfo<Article> info = new PageInfo(list);

        List<Article> _list = info.getList();
        info.setList(null);
        List<Article> __list = new ArrayList<>(10);

        PageInfo<Article> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(Article article : _list) {
                __list.add(article);
            }
            _info.setList(__list);
        }
        return _info;
    }

    @Override
    public Article selectByStatus(int status) {
        return articleMapper.selectByStatus(status);
    }

    @Override
    public void updateStatus(Article article) {

        articleMapper.updateStatus(article);
    }

    @Override
    public int selectCount(int status) {
        return articleMapper.selectCount(status);
    }
}
