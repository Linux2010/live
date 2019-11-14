package cn.com.myproject.news.service.impl;

import cn.com.myproject.news.mapper.NewsMapper;
import cn.com.myproject.news.service.INewsService;
import cn.com.myproject.user.entity.PO.News;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李延超 on 2017/8/15.
 */
@Service
public class NewsService implements INewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void addNews(News news) {

        newsMapper.addNews(news);
    }

    @Override
    public void delNews(String businessId) {
        newsMapper.delNews(businessId);
    }


    @Override
    public void updateNews(News news) {
        newsMapper.updateNews(news);
    }

    @Override
    public News selectNewsById(String businessId) {
        return newsMapper.selectNewsById(businessId);
    }

    @Override
    public List<News> getAll() {
        List<News> list = newsMapper.getAll();
        return list;
    }


    @Override
    public PageInfo<News> getPage(int pageNum, int pageSize, String title) {
        List<News> newsList = newsMapper.getPage(pageNum, pageSize, title);
        return convert(newsList);
    }

    @Override
    public List<News> searchNewsList(int pageNum, int pageSize, String title){
        return newsMapper.getPage(pageNum,pageSize,title);
    }

    @Override
    public int updateNewsTitle(News news) {
     return newsMapper.updateNewsTitle(news);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<News> convert(List<News> list) {
        PageInfo<News> info = new PageInfo(list);
        List<News> _list = info.getList();
        info.setList(null);
        List<News> __list = new ArrayList<>(10);
        PageInfo<News> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(News c : _list) {
                __list.add(c);
            }
            _info.setList(__list);
        }
        return _info;
    }

}