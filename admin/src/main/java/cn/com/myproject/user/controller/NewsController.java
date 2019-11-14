package cn.com.myproject.user.controller;

import cn.com.myproject.service.INewsServer;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.user.entity.PO.News;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by 李延超 on 2017/8/15.
 */

    @RequestMapping("/news")
    @Controller
    public class NewsController {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(cn.com.myproject.user.controller.NewsController.class);
    @Autowired
    private INewsServer newsService;

    @Autowired
    private IUploadImgService uploadImgService;

    @RequestMapping("/")
    public String index() {

        return "news/new_business";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<News> getAll(int page, int rows, String keyword) {
        return newsService.searchAllNewsList(page,rows,keyword);
    }

    //添加标题和封面
    @RequestMapping("/addNews")
    @ResponseBody
    public Map<String, Object> add(News news ,MultipartFile file,HttpServletResponse response) {

        Map<String, Object> data = new HashMap<>();

        try {
            String fileUrl=uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {

                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                    return data;
                }
            news.setPhoto(fileUrl);
            news.setThumbnailImg(fileUrl+"?x-oss-process=image/resize,w_200/blur,r_3,s_2");
            if (   newsService.addNews(news) != 1){
                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                    return data;
                }      data.put("status", "success");
            data.put("message", "添加成功!");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "添加失败！");
            return data;
        }
        return data;
    }

    @RequestMapping("/delOperation")
    @ResponseBody
    public String delete( String businessId) {

        String result = "";
        try {
            if (StringUtils.isBlank(businessId) ) {
                result = "0";
                return result;
            }
            News news = newsService.selectNewsById(businessId);
            if (null == news) {
                result = "0";
                return result;
            }
            newsService.delNewsById(businessId);
            result = "1";
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = "0";
        }
        return result;
    }

    @RequestMapping("/updateOperation")
    @ResponseBody
    public String update(News news, HttpServletRequest request) {

        String result = "";
        try {
            //String title = request.getParameter("title");
            String content = request.getParameter("content");
            String businessId = request.getParameter("businessId");
            //news.setTitle(title);
            news.setContent(content);
            news.setBusinessId(businessId);
            newsService.updateNews(news);
            result = "1";
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = "0";
        }
        return result;
    }

    @RequestMapping("/selectOperation")
    @ResponseBody
    public News select(String businessId) {

        return newsService.selectNewsById(businessId);
    }

    @RequestMapping(value = "/newsContent11")
    public ModelAndView showNewsContent(String businessId) {
        ModelAndView view = new ModelAndView("/news/showNewsContent");
        view.addObject("businessId", businessId);
        return view;
    }

    @RequestMapping("/add_news_content")
    public ModelAndView add_news_content(String businessId){
        ModelAndView model = new ModelAndView("news/add_news_buiness");
        model.addObject("businessId",businessId);
        return model;
    }

    @RequestMapping("/updateNewsTitle")
    @ResponseBody
    public  Map<String, Object> updateTitle(MultipartFile file,News news, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        try {
            String businessId = request.getParameter("businessId");
            if (StringUtils.isBlank(businessId)){
                data.put("status", "faile");
                data.put("message", "编号不能为空！");
                return data;
            }

            News news1 = newsService.selectNewsById(businessId);
            if (null == news1){
                data.put("status", "faile");
                data.put("message", "修改失败，没有！");
                return data;
            }
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(fileUrl)) {
                news1.setPhoto(fileUrl);
                news1.setThumbnailImg(fileUrl+"?x-oss-process=image/resize,w_200/blur,r_3,s_2");
            }
            news1.setTitle(news.getTitle());
            if (newsService.updateNewsTitle(news1) != 1){
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


}



