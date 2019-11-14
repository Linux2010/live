package cn.com.myproject.api.news.controller;

import cn.com.myproject.api.service.INewsService;
import cn.com.myproject.api.service.IUploadImgService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.News;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李延超 on 2017/8/28.
 */
@RestController
@RequestMapping("/api/news")
@Api(value="商业模式类",tags = "商业模式列表")
public class  NewsApiController {

  @Autowired
  private INewsService newsApiService;

  @Autowired
  private IUploadImgService uploadImgService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

//    @PostMapping(value = "/searchNewsInfoList")
//    @ApiOperation(value = "商业模式相关信息", produces = "application/json")
//    public Message<List<News>> searchNewsInfoList(){
//
//        try {
//            List<News> list = newsApiService.getAll();
//            for (News news:list) {
//                news.setContentUrl(jtxyappUrl+"/api/news/searchNewsContent?businessId="+news.getBusinessId() );
//            }
//            Message message = MessageUtils.getSuccess("获取成功！");
//            message.setData(list);
//            return message;
//        }catch (RuntimeException e){
//            Message message = MessageUtils.getFail("请求失败！"+ e.getMessage());
//            return  message;
//        }
//    }

    @PostMapping("/index")
    @ApiOperation(value = "商业模式相关信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "title", value = "新闻名称",
                    required = false, dataType = "String",defaultValue = "")
    })
    public Message<List<News>> index(String pageNum, String pageSize, String Title, HttpServletRequest request, MultipartFile file){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        String fileUrl=uploadImgService.uploadImg(file);

        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        // 查询新闻列表信息
        List<News> cList = newsApiService.searchAllNewsList(pageNumVal,pageSizeVal,"");
        if(cList != null && cList.size() > 0){
            for(int i = 0;i < cList.size();i++){
                if(cList.get(i) != null){
                    cList.get(i).setContentUrl(jtxyappUrl+"/api/news/searchNewsContent?businessId="+cList.get(i).getBusinessId());
                }
            }
        }
        Message<List<News>> message = MessageUtils.getSuccess("success");
        message.setData(cList);
        return message;
    }

    @GetMapping("/searchNewsContent")
    @ApiOperation(value = "根据新闻ID查询文字课程内容，APP不可使用", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "businessId",value = "新闻ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15")
    })
    public ModelAndView searchNewsContent(String businessId){
        ModelAndView view = new ModelAndView("/news/business");
        News news = newsApiService.searchNewsById(businessId);
        String wzkcContent = "";
        if(news != null){
            wzkcContent = news.getContent();
        }
        view.addObject("content",wzkcContent==null?"暂无内容":wzkcContent);
        return view;
    }



}

