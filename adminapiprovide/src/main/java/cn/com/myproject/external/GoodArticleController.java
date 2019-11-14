package cn.com.myproject.external;

import cn.com.myproject.goodArticle.service.impl.GoodArticleService;
import cn.com.myproject.user.entity.PO.GoodArticle;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LSG on 2017/8/23 0023.
 */
@RestController
@RequestMapping("/goodArticle")
public class GoodArticleController {

    @Autowired
    private GoodArticleService goodArticleService;


    @PostMapping("/getPage")
    public PageInfo<GoodArticle> getPage(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException {

        if (!map.isEmpty() && map.get("keyword") != null && StringUtils.isNotBlank(map.get("keyword").toString())) {

            map.put("keyword", URLDecoder.decode(map.get("keyword").toString(), "UTF-8"));
        }

        return goodArticleService.getPage(Integer.valueOf(map.get("pageNum") + ""), Integer.valueOf(map.get("pageSize") + ""), map);
    }

    @PostMapping("/selectById")
    public GoodArticle selectById(String goodArticleId) {

        return goodArticleService.selectById(goodArticleId);
    }

    @PostMapping("/addGoodArticle")
    public int addGoodArticle(@RequestBody GoodArticle goodArticle) {

        int result = 0;
        try {
            goodArticle.setGoodArticleId(UUID.randomUUID().toString().replace("-", ""));
            goodArticle.setTitle(goodArticle.getTitle());
            goodArticle.setSeqno(goodArticle.getSeqno());
            goodArticle.setCreateTime(new Date().getTime());
            goodArticle.setVersion(1);
            goodArticleService.addGoodArticle(goodArticle);
            result = 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/updateGoodArticle")
    public int updateGoodArticle(@RequestBody GoodArticle goodArticle) {

        int result = 0;
        try {
            goodArticle.setTitle(goodArticle.getTitle());
            goodArticle.setSeqno(goodArticle.getSeqno());
            goodArticleService.updateGoodArticle(goodArticle);
            result = 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/delGoodArticle")
    public int delGoodArticle(String goodArticleId) {

        int result = 0;
        try {
            goodArticleService.delGoodArticle(goodArticleId);
            result = 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/updateRecommend")
    public int updateRecommend(@RequestBody GoodArticle goodArticle) {

        int result = 0;
        try {
            goodArticleService.updateRecommend(goodArticle);
            result = 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/updateContent")
    public int updateContent(@RequestBody GoodArticle goodArticle) {

        int result = 0;
        try {
            goodArticle.setContent(goodArticle.getContent());
            goodArticleService.updateContent(goodArticle);
            result = 1;
        } catch (RuntimeException e) {
            result = 0;
            return result;
        }
        return result;
    }

    @GetMapping("/select_good_article")
    public List<GoodArticle> select_good_article(int pageNum, int pageSize) {
        return goodArticleService.select_good_article(pageNum, pageSize);
    }
}


























