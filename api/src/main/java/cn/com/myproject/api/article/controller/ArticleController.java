package cn.com.myproject.api.article.controller;

import cn.com.myproject.api.service.IArticleService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LSG on 2017/8/28 0028.
 */
@RestController
@RequestMapping("/api/article")
@Api(value="文章海报类",tags = "文章海报列表")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;
    /**
     * 文章海报列表
     */
    @GetMapping(value = "/articleList")
    @ApiOperation(value = "文章海报相关信息", produces = "application/json")
    public Message articleList(){
        try {
            Article article = articleService.selectByStatus(2);
            article.setContent(jtxyappUrl+"/api/article/selectArticleContent");
            Message message = MessageUtils.getSuccess("获取成功！");
            message.setData(article);
            return message;
        }catch (RuntimeException e){
            Message message = MessageUtils.getFail("获取失败！"+e.getMessage());
            return message;
        }
    }

    /**
     * 查询正在使用的海报内容
     *
     * */
    @GetMapping("/selectArticleContent")
    @ApiOperation(value = "根据文章海报状态查询文章内容，APP不可使用", produces = "application/json")
    public ModelAndView selectArticleContent(){
        ModelAndView view = new ModelAndView("/article/articleContent");
        Article article = articleService.selectByStatus(2);
        String articleContent = "";
        if(article != null){
            articleContent = article.getContent();
        }
        view.addObject("articleContent",articleContent==null?"暂无内容":articleContent);
        return view;
    }
}
