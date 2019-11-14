package cn.com.myproject.user.controller;

import cn.com.myproject.service.IArticleService;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.user.entity.PO.Article;
import cn.com.myproject.util.Message;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

@RequestMapping("/article")
@Controller
public class ArticleController {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ArticleController.class);
    @Autowired
    private IArticleService articleService;

    @Autowired
    private IUploadImgService uploadImgService;

    @RequestMapping("/")
    public String index(){
        return "article/article_index";
    }


    @RequestMapping("/richText")

    public String richText(){

        return "article/article_richText";
    }

    @RequestMapping("/page")
    @ResponseBody
    public PageInfo<Article> getPage(int rows, Integer page, HttpServletRequest request) throws Exception{

        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotBlank(keyword)){
            keyword= URLDecoder.decode(keyword, "utf-8");
        }else {
            keyword = null;
        }
        PageInfo<Article> list = articleService.getPage(page, rows ,keyword);
        return list;
    }

    //添加文章海报
    @RequestMapping("/addOperation")
    @ResponseBody
    public Map<String, Object> add(MultipartFile file , Article article, HttpServletResponse response){

        Map<String, Object> data = new HashMap<>();
        try {
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                return data;
            }
            article.setImg(fileUrl);
            article.setThumbnailImg(fileUrl+"?x-oss-process=image/resize,w_200/blur,r_3,s_2");
            if (articleService.addArticle(article) != 1){
                data.put("status", "faile");
                data.put("message", "添加失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "添加成功!");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "添加失败！");
            return data;
        }
        return data;
    }

    //删除文章海报
    @RequestMapping("/delOperation")
    @ResponseBody
    public Map<String, Object> delete(String articleId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(articleId)){
                data.put("status", "faile");
                data.put("message", "编号不能为空！");
                return data;
            }
           Article article = articleService.selectArticleById(articleId);
            if (null == article){
                data.put("status", "faile");
                data.put("message", "没有该海报！");
                return data;
            }
            if (articleService.delArticleById(articleId) != 1){
                data.put("status", "faile");
                data.put("message", "没有该海报！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "删除成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "删除失败！");
            return data;
        }
        return data;
    }


    //修改文章海报
    @RequestMapping("/updateOperation")
    @ResponseBody
    public Map<String, Object> update(MultipartFile file, Article article,HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> data = new HashMap<>();
        try {
            String articleId = request.getParameter("articleId_update");
            if (StringUtils.isBlank(articleId)){
                data.put("status", "faile");
                data.put("message", "文章编号不能为空！");
                return data;
            }
            Article article1 = articleService.selectArticleById(articleId);
            if (null == article1){
                data.put("status", "faile");
                data.put("message", "修改失败，没有该文章！");
                return data;
            }
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(fileUrl)) {
                article1.setImg(fileUrl);
                article1.setThumbnailImg(fileUrl+"?x-oss-process=image/resize,w_200/blur,r_3,s_2");
            }
            article1.setTitle(article.getTitle());
            if (articleService.updateArticle(article1) != 1){
                data.put("status", "faile");
                data.put("message", "修改失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "修改成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "修改失败！");
            return data;
        }
      return data;
    }

    //根据id查询文章海报
    @RequestMapping("/selectOperation")
    @ResponseBody
    public Article select(String articleId) {

        return articleService.selectArticleById(articleId);
    }

    //添加文章内容
    @RequestMapping(value = "/addArticleContent")
    public ModelAndView addArticleContent(String articleId){
        ModelAndView view = new ModelAndView("/article/addArticleContent");
        view.addObject("articleId",articleId);
        return view;
    }

   //上传文章内容
    @RequestMapping("/addContent")
    @ResponseBody
    public Map<String, Object> addArticleContent(Article article){

        Map<String, Object> data = new HashMap<>();
        try {
            if (articleService.addArticleContent(article) != 1){
                data.put("status", "faile");
                data.put("message", "上传失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message","上传成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "上传失败！");
            return data;
        }
        return data;
    }

    //展示文章内容
    @RequestMapping(value = "/showArticleContent")
    public ModelAndView showArticleContent(String articleId){
        ModelAndView view = new ModelAndView("/article/showArticleContent");
        view.addObject("articleId",articleId);
        return view;
    }

    //取消使用该海报
    @RequestMapping("/noUseThis")
    @ResponseBody
    public Map<String, Object> noUseThis(String articleId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(articleId)){
                data.put("status", "faile");
                data.put("message", "文章编号不能为空！");
                return data;
            }
            Article article = articleService.selectArticleById(articleId);
            if (null == article){
                data.put("status", "faile");
                data.put("message", "没有该文章海报！");
                return data;
            }
            article.setStatus((short)1);
            if (articleService.updateStatus(article) != 1){
                data.put("status", "faile");
                data.put("message", "取消失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "取消成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "取消失败！");
            return data;
        }
        return data;
    }

    //使用海报操作
    @RequestMapping("/useThis")
    @ResponseBody
    public Map<String, Object> useThis(String articleId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(articleId)){
                data.put("status", "faile");
                data.put("message", "文章编号不能为空！");
                return data;
            }
            Article article = articleService.selectArticleById(articleId);
            if (null == article){
                data.put("status", "faile");
                data.put("message", "没有该文章海报！");
                return data;
            }
            if (null == article.getContent()){
                data.put("status", "faile");
                data.put("message", "该海报还未上传内容，请上传后使用！");
                return data;
            }
            if (articleService.selectCount(2) == 1){
                Article article1 = articleService.selectByStatus((short)2);//正在使用的
                articleService.updateStatus(article1);
                if (articleService.updateStatus(article) == 1){
                    data.put("status", "success");
                    data.put("message", "使用成功！");
                    return data;
                }else {
                    data.put("status", "faile");
                    data.put("message", "使用失败！");
                    return data;
                }
            }else {
                if (articleService.updateStatus(article) == 1){
                    data.put("status", "success");
                    data.put("message", "使用成功！");
                    return data;
                }else {
                    data.put("status", "faile");
                    data.put("message", "使用失败！");
                    return data;
                }
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "使用失败！");
            return data;
        }
    }
}
