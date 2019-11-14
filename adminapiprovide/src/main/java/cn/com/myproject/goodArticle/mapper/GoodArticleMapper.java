package cn.com.myproject.goodArticle.mapper;


import cn.com.myproject.user.entity.PO.GoodArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


/**
 * Created by LSG on 2017/8/23 0007.
 */
@Mapper
public interface GoodArticleMapper {

    List<GoodArticle> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("map") Map<String, Object> map);

    GoodArticle selectById(String goodArticleId);

    void addGoodArticle(@RequestBody GoodArticle goodArticle);

    void updateGoodArticle(@RequestBody GoodArticle goodArticle);

    void delGoodArticle(String goodArticleId);

    void updateRecommend(@RequestBody GoodArticle goodArticle);

    void updateContent(@RequestBody GoodArticle goodArticle);

    List<GoodArticle> select_good_article(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);
}
