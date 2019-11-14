package cn.com.myproject.goodArticle;

import cn.com.myproject.user.entity.PO.GoodArticle;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by LSG on 2017/8/23 0023.
 */
public interface IGoodArticleService {

    PageInfo<GoodArticle> getPage(int pageNum, int pageSize, Map<String, Object> map);

    GoodArticle selectById(String goodArticleId);

    void addGoodArticle(GoodArticle goodArticle);

    void updateGoodArticle(GoodArticle goodArticle);

    void delGoodArticle(String goodArticleId);

    void updateRecommend(GoodArticle goodArticle);

    void updateContent(GoodArticle goodArticle);

    List<GoodArticle> select_good_article(int pageNum,int pageSize);
}
