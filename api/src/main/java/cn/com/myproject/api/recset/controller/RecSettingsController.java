package cn.com.myproject.api.recset.controller;

import cn.com.myproject.api.service.IGoodArticleService;
import cn.com.myproject.api.service.IRecSettingsService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.user.entity.PO.GoodArticle;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.VO.APITearcherUser;
import cn.com.myproject.user.entity.VO.RecommVO;
import cn.com.myproject.user.entity.VO.StudyTodayVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyp on 2017/8/30 0030.
 */
@RestController
@RequestMapping("/api/recset")
@Api(value="推荐类",tags = "学习")
public class RecSettingsController {

    @Autowired
    private IRecSettingsService settingsService;

    @Autowired
    private IGoodArticleService goodArticleService;

    @Autowired
    private IUserService userService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     *推荐-优质课程_展示
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/today_tcourse")
    @ApiOperation(value = "推荐-优质课程-展示", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "3")
    })
   public Message<List<Course>> getCourPage(String pageNum, String pageSize){
       int pageNumVal = 0;
       int pageSizeVal = 0;
       if(StringUtils.isNotBlank(pageNum)){
           pageNumVal = Integer.parseInt(pageNum);
       }
       if(StringUtils.isNotBlank(pageSize)){
           pageSizeVal = Integer.parseInt(pageSize);
       }
       String status = "1";
        List<Course> list = settingsService.select_tui_tcour(pageNumVal,pageSizeVal);
       Message<List<Course>> message = MessageUtils.getSuccess("success");
       message.setData(list);
       return message;
    }

   /* *//**
     *今日-今日推荐_展示
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    @PostMapping("/today_gcourse")
    @ApiOperation(value = "今日-今日推荐-展示", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "3")
    })
    public Message<List<RecSettings>> hasRecByTag(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }


        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<RecSettings> list = settingsService.select_today_gcour(pageNumVal,pageSizeVal);
        Message<List<RecSettings>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }*/

    /**
     *推荐-优质课程_更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/today_tcourse_list")
    @ApiOperation(value = "推荐-优质课程-更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10")
    })
    public Message<List<Course>> getCourPage_list(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<Course> list = settingsService.select_tui_tcour(pageNumVal,pageSizeVal);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     *今日_今日推荐_更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/today_gcourse_list")
    @ApiOperation(value = "今日_今日课程-更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id",
            required = true, dataType = "String",defaultValue = "fgdg")
    })
    public Message<List<Course>> hasRecByTag_list(String pageNum, String pageSize,String userId){
        Message<List<Course>> message = new Message<>();
        if(StringUtils.isBlank(userId)){
          return MessageUtils.getFail("tokenIsNull");
      }else{
          int pageNumVal = 0;
          int pageSizeVal = 0;
          if(StringUtils.isNotBlank(pageNum)){
              pageNumVal = Integer.parseInt(pageNum);
          }
          if(StringUtils.isNotBlank(pageSize)){
              pageSizeVal = Integer.parseInt(pageSize);
          }
          User curMemmber = userService.selectById(userId);
          if(null == curMemmber){
              return MessageUtils.getFail("没有此用户的信息!");
          }else{
              List<Course> list = new ArrayList<>();
              if(curMemmber.getUserIdentity() == 1){//普通用户
                  list = settingsService.select_today_gcour(pageNumVal,pageSizeVal);
              }else if(curMemmber.getUserIdentity() == 2){//会员用户
                  if(curMemmber.getLabelId() != null){
                      String s[] = curMemmber.getLabelId().split(",");
                      for(int i= 0;i<s.length;i++){
                          list.addAll(settingsService.select_today_gcour_by_label(s[i],pageNumVal,pageSizeVal));
                      }
                  }else{
                      list = settingsService.select_today_gcour(pageNumVal,pageSizeVal);
                  }
              }
              message = MessageUtils.getSuccess("success");
              message.setData(list);
          }
          return message;
      }
    }


   /* *//**
     *今日_优质课程_展示
     * @param pageNum
     * @param pageSize
     * @return
     *//*
    @PostMapping("/today_exce")
    @ApiOperation(value = "今日-优质课程-展示", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "3")
    })
    public Message<List<RecSettings>> select_today_excellentcour(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<RecSettings> list = settingsService.select_today_excellentcour(pageNumVal,pageSizeVal);
        Message<List<RecSettings>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }*/

    /**
     *今日_优质课程_更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/today_exce_list")
    @ApiOperation(value = "今日-优质课程-更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10")
    })
    public Message<List<Course>> select_today_exc_list(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<Course> list = settingsService.select_today_excellentcour(pageNumVal,pageSizeVal);
        Message<List<Course>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     *今日_优质课程_更多
     * @return
     */
    @PostMapping("/study_today_list")
    @ApiOperation(value = "学习-今日", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "3"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id",
                    required = true, dataType = "String",defaultValue = "ss2")
    })
    public Message study_today(String pageNum, String pageSize,String userId,HttpServletRequest request){
       if(StringUtils.isBlank(userId)){
           return MessageUtils.getFail("tokenIsNull");
       }else{
           Message<StudyTodayVO> message = MessageUtils.getSuccess("success");
           StudyTodayVO data = new StudyTodayVO();
           int pageNumVal = 0;
           int pageSizeVal = 0;
           if(StringUtils.isNotBlank(pageNum)){
               pageNumVal = Integer.parseInt(pageNum);
           }

           if(StringUtils.isNotBlank(pageSize)){
               pageSizeVal = Integer.parseInt(pageSize);
           }

           List<Course> todayrecomlist = new ArrayList<Course>();
           //判断用户是会员还是普通用户
           User curMemmber = userService.selectById(userId);
           if(null == curMemmber){
               return MessageUtils.getFail("没有此用户!");
           }else{
               if(curMemmber.getUserIdentity() == 1){//普通用户
                   // settingsService.select_today_gcour(pageNumVal,pageSizeVal);//今日课程--随机推送
                   todayrecomlist.addAll(settingsService.select_today_gcour(pageNumVal,pageSizeVal));
                   data.setTodayrecomlist(todayrecomlist);//今日课程
               }else if(curMemmber.getUserIdentity() == 2){//会员用户
                   if(curMemmber.getLabelId() != null){
                       String s[] = curMemmber.getLabelId().split(",");
                       for(int i= 0;i<s.length;i++){
                           //settingsService.select_today_gcour_by_label(s[i],pageNumVal,pageSizeVal);//今日课程--根据用户标签
                           todayrecomlist.addAll(settingsService.select_today_gcour_by_label(s[i],pageNumVal,pageSizeVal));
                           data.setTodayrecomlist(todayrecomlist);//今日课程
                       }
                   }else{
                       todayrecomlist.addAll(settingsService.select_today_gcour(pageNumVal,pageSizeVal));
                       data.setTodayrecomlist(todayrecomlist);//今日课程（没有标签的）
                   }
               }
               List<Course> excecourlist = settingsService.select_today_excellentcour(pageNumVal,pageSizeVal);//优质课程
               List<GoodArticle> goodarticlelist = goodArticleService.select_good_article(pageNumVal,pageSizeVal); //好文推荐
               List<Course> todaystudy = goodArticleService.select_today_study(pageNumVal,pageSizeVal,userId); //今日学习
               for (GoodArticle goodArticle: goodarticlelist) {
                   goodArticle.setContentUrl(jtxyappUrl+"/api/recset/goodArticleInfo?goodArticleId="+goodArticle.getGoodArticleId());
               }


               data.setExcecourlist(excecourlist);//优质课程
               data.setGoodArticleList(goodarticlelist);//好文推荐

               data.setCourseOrderList(todaystudy);//今日学习
               message.setData(data);
           }
           return message;
       }
    }

    /**
     *今日_好文推荐_更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/good_article_list")
    @ApiOperation(value = "今日-好文推荐-更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10")
    })
    public Message<List<GoodArticle>> good_article_list(String pageNum, String pageSize,HttpServletRequest request){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<GoodArticle> list = goodArticleService.select_good_article(pageNumVal,pageSizeVal);
        for (GoodArticle goodArticle: list) {
            goodArticle.setContentUrl(jtxyappUrl+"/api/recset/goodArticleInfo?goodArticleId="+goodArticle.getGoodArticleId());
        }
        Message<List<GoodArticle>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     *今日-今日学习-更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/today_study_list")
    @ApiOperation(value = "今日-今日学习-更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id",
                    required = true, dataType = "String",defaultValue = "ss2")
    })
    public Message<List<Course>> today_study_list(String pageNum, String pageSize,String userId){
        if(StringUtils.isBlank(userId)){
            return MessageUtils.getFail("tokenIsNull");
        }else{
            int pageNumVal = 0;
            int pageSizeVal = 0;
            if(StringUtils.isNotBlank(pageNum)){
                pageNumVal = Integer.parseInt(pageNum);
            }
            if(StringUtils.isNotBlank(pageSize)){
                pageSizeVal = Integer.parseInt(pageSize);
            }
            List<Course> list = goodArticleService.select_today_study(pageNumVal,pageSizeVal,userId);
            Message<List<Course>> message = MessageUtils.getSuccess("success");
            message.setData(list);
            return message;
        }
    }

    @PostMapping("/good_article_detail")
    @ApiOperation(value = "今日-好文推荐-详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "goodArticleId",value = "好文Id",
                    required = true, dataType = "String",defaultValue = "058cf5fa01a9440297ad7a7de7603ed9")
    })
    public Message<GoodArticle> good_article_detail(String goodArticleId, HttpServletRequest request){

        GoodArticle goodArticle = new GoodArticle();
        goodArticle = goodArticleService.selectById(goodArticleId);
        goodArticle.setContentUrl(jtxyappUrl+ "/api/recset/goodArticleInfo?goodArticleId="+goodArticleId);
        Message message = MessageUtils.getSuccess("success");
        message.setData(goodArticle);
        return message;
    }

    /**
     * 好文详情页面
     * @return
     */
    @ApiOperation(value = "好文详情页面", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "goodArticleId", value = "好文ID", required = true, dataType = "String",defaultValue = "058cf5fa01a9440297ad7a7de7603ed9"),
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = GoodArticle.class, responseContainer = "List" ) })
    @GetMapping("/goodArticleInfo")
    public ModelAndView goodArticleInfo(String goodArticleId){
        ModelAndView modelAndView = new ModelAndView("/article/goodArticleIntro");
        GoodArticle goodArticle  =  goodArticleService.selectById(goodArticleId);
        modelAndView.addObject("goodArticelInfo",goodArticle.getContent()==null?"无简介附件":goodArticle.getContent());
        return modelAndView;
    }

    /**
     * 优质商品
     * */
    @PostMapping("/qualityGoods")
    @ApiOperation(value = "今日-今日推荐-优质商品-更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10")
    })
    public Message<List<Course>> qualityGoods(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        try {
            List<Course> qualityGoods = settingsService.select_today_goods(pageNumVal, pageSizeVal);
            Message message = MessageUtils.getSuccess("获取成功！");
            message.setData(qualityGoods);
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("获取失败！");
        }
    }

    /**
     *推荐_精英教头_更多
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/recomm_tea_list")
    @ApiOperation(value = "推荐_精英教头_更多", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10")
    })
    public Message<List<User>> recomm_tea_list(String pageNum, String pageSize){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<User> list = settingsService.select_recomm_teacher(pageNumVal,pageSizeVal);
        Message<List<User>> message = MessageUtils.getSuccess("success");
        message.setData(list);
        return message;
    }

    /**
     *推荐
     * @return
     */
    @PostMapping("/recomment_home")
    @ApiOperation(value = "推荐", produces = "application/json")
    public Message recomment_home(){
        List<User> tealist = settingsService.select_recomm_teacher(1,4);//优质课程
        List<Course> goodreclist = settingsService.select_tui_tcour(1,2);//今日课程
        List<Course> goodselectlist = settingsService.select_today_goods(1,2);//优选商品
        Message<RecommVO> message = MessageUtils.getSuccess("success");
        RecommVO data = new RecommVO();
        data.setTeacherList(tealist);//精英教头
        data.setRecommCourList(goodreclist);//优质课程
        data.setGoodselectCourList(goodselectlist);//优选商品
        message.setData(data);
        return message;
    }

}
