package cn.com.myproject.goodArticle.service.impl;

import cn.com.myproject.goodArticle.IGoodArticleService;
import cn.com.myproject.goodArticle.mapper.GoodArticleMapper;
import cn.com.myproject.user.entity.PO.GoodArticle;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by LSG on 2017/8/23 0007.
 */
@Service
public class GoodArticleService implements IGoodArticleService {

    @Autowired
    private GoodArticleMapper goodArticleMapper;

    @Override
    public PageInfo<GoodArticle> getPage(int pageNum, int pageSize, Map<String, Object> map) {

        List<GoodArticle> list = goodArticleMapper.getPage(pageNum, pageSize, map);
        return convert(list);
    }

    @Override
    public GoodArticle selectById(String goodArticleId) {

        return goodArticleMapper.selectById(goodArticleId);
    }

    @Override
    public void addGoodArticle(@RequestBody GoodArticle goodArticle) {

        goodArticleMapper.addGoodArticle(goodArticle);
    }

    @Override
    public void updateGoodArticle(@RequestBody GoodArticle goodArticle) {

        goodArticleMapper.updateGoodArticle(goodArticle);
    }

    @Override
    public void delGoodArticle(String goodArticleId) {

        goodArticleMapper.delGoodArticle(goodArticleId);
    }

    @Override
    public void updateRecommend(GoodArticle goodArticle) {

        goodArticleMapper.updateRecommend(goodArticle);
    }


    @Override
    public void updateContent(GoodArticle goodArticle) {

        goodArticleMapper.updateContent(goodArticle);
    }

    @Override
    public List<GoodArticle> select_good_article(int pageNum, int pageSize) {
        return goodArticleMapper.select_good_article(pageNum,pageSize);
    }

    private PageInfo<GoodArticle> convert(List<GoodArticle> list) {
        PageInfo<GoodArticle> info = new PageInfo(list);

        List<GoodArticle> _list = info.getList();
        info.setList(null);
        List<GoodArticle> __list = new ArrayList<>(10);

        PageInfo<GoodArticle> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(GoodArticle goodArticle : _list) {
                __list.add(goodArticle);
            }
            _info.setList(__list);
        }
        return _info;
    }
}
