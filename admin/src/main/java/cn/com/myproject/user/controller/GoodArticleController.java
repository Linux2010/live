package cn.com.myproject.user.controller;

import cn.com.myproject.service.IGoodArticleService;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.user.entity.PO.GoodArticle;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LSG on 2017/8/23 0023.
 */
@Controller
@RequestMapping("/goodArticle")
public class GoodArticleController {

    @Autowired
    private IGoodArticleService goodArticleService;
    @Autowired
    private IUploadImgService uploadImgService;

    @RequestMapping("/")
    public String index(){

        return "goodArticle/goodArticle_index";
    }

    //好文列表
    @RequestMapping("/allGoodArticle")
    @ResponseBody
    public PageInfo<GoodArticle> getAll(int rows, int page, HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",rows);
        if (org.apache.commons.lang.StringUtils.isNotBlank(request.getParameter("status"))) {
            int status = Integer.parseInt(request.getParameter("status"));
            map.put("status", status);
        }
        return goodArticleService.getPage(map);
    }


    //进行好文添加
    @RequestMapping("/addGood")
    @ResponseBody
    public Map<String, Object> addGoodArticle(MultipartFile file, GoodArticle goodArticle,
                                              HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> data = new HashMap<>();
        try {
            String status_add = request.getParameter("status_add");
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                return data;
            }
            goodArticle.setCover(fileUrl);
            goodArticle.setStatus(new Short(status_add));
            if (goodArticleService.addGoodArticle(goodArticle) != 1){
                data.put("status", "faile");
                data.put("message", "添加失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "添加成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "添加失败！");
            return data;
        }
        return data;
    }

    //删除好文
    @RequestMapping("/delGood")
    @ResponseBody
    public Map<String, Object> delRecommended(String goodArticleId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(goodArticleId)){
                data.put("status", "faile");
                data.put("message", "编号不能为空！");
                return data;
            }
            GoodArticle goodArticle = goodArticleService.selectById(goodArticleId);
            if (null == goodArticle){
                data.put("status", "faile");
                data.put("message", "没有该推荐文章！");
                return data;
            }
            if (goodArticleService.delGoodArticle(goodArticleId) != 1){
                data.put("status", "faile");
                data.put("message", "删除失败！");
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

    //通过ID查询好文
    @RequestMapping("/selectGood")
    @ResponseBody
    public GoodArticle selectGood(String goodArticleId){
        GoodArticle goodArticle = goodArticleService.selectById(goodArticleId);
        return goodArticle;
    }
    //修改好文
    @RequestMapping("/updateGood")
    @ResponseBody
    public Map<String, Object> updateGood(MultipartFile file, GoodArticle goodArticle,
                                          HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> data = new HashMap<>();
        try {
            String goodArticleId = request.getParameter("goodArticleId");
            GoodArticle goodArticle1 = goodArticleService.selectById(goodArticleId);
            if (null == goodArticle1){
                data.put("status", "faile");
                data.put("message", "没有该好文");
                return data;
            }

            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(fileUrl)) {
                goodArticle1.setCover(fileUrl);
            }
            goodArticle1.setTitle(goodArticle.getTitle());
            goodArticle1.setSeqno(goodArticle.getSeqno());
            if (goodArticleService.updateGoodArticle(goodArticle1) != 1){
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

    //添加好文内容
    @RequestMapping(value = "/addGoodArticleContent")
    public ModelAndView addArticleContent(String goodArticleId){
        ModelAndView view = new ModelAndView("/goodArticle/addGoodArticleContent");
        view.addObject("goodArticleId",goodArticleId);
        return view;
    }


    //上传好文内容
    @RequestMapping("/updateContent")
    @ResponseBody
    public Map<String, Object> addContent(GoodArticle goodArticle){

        Map<String, Object> data = new HashMap<>();
        try {
            if (goodArticleService.updateContent(goodArticle) != 1){
                data.put("stauts", "faile");
                data.put("message", "上传失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "上传成功！");
        }catch (RuntimeException e){
            data.put("stauts", "faile");
            data.put("message", "上传失败！");
            return data;
        }
        return data;
    }

    //展示好文内容
    @RequestMapping( value = "/showContent")
    public ModelAndView showContent(String goodArticleId){
        ModelAndView view = new ModelAndView("/goodArticle/showContent");
        view.addObject("goodArticleId",goodArticleId);
        return view;
    }
    //取消推荐
    @RequestMapping("/cancel")
    @ResponseBody
    public Map<String, Object> cancel(String goodArticleId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(goodArticleId)){
                data.put("status", "faile");
                data.put("message", "编号不能为空！");
                return data;
            }
            GoodArticle goodArticle = goodArticleService.selectById(goodArticleId);
            if (null == goodArticle){
                data.put("status", "faile");
                data.put("message", "没有该推荐文章！");
                return data;
            }
            goodArticle.setStatus((short)2);
            if (goodArticleService.updateRecommend(goodArticle) != 1){
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

    //推荐
    @RequestMapping("/recommend")
    @ResponseBody
    public Map<String, Object> recommend(String goodArticleId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(goodArticleId)){
                data.put("status", "faile");
                data.put("message", "编号不能为空！");
                return data;
            }
            GoodArticle goodArticle = goodArticleService.selectById(goodArticleId);
            if (null == goodArticle){
                data.put("status", "faile");
                data.put("message", "没有该推荐文章！");
                return data;
            }
            goodArticle.setStatus((short)1);
            if (goodArticleService.updateRecommend(goodArticle) != 1){
                data.put("status", "success");
                data.put("message", "推荐失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "推荐成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "推荐失败！");
            return data;
        }
        return data;
    }

}




























