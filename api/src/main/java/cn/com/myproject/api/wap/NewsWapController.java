package cn.com.myproject.api.wap;

import cn.com.myproject.api.service.IInformationservice;
import cn.com.myproject.api.service.INewsService;
import cn.com.myproject.api.service.NewsCommentService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 李延超 on 2017/10/23.
 */
@RequestMapping("/wap/news")
@Controller
public class NewsWapController extends BaseController {

    @Autowired
    private INewsService newsApiService;

    @Autowired
    private IInformationservice  informationservice;

    @Autowired
    private NewsCommentService newsCommentService;

    /**
     * 商业模式view
     *
     * @return
     */
    @RequestMapping("/newsView")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/news/newsList");
        return view;
    }

    /**
     * 商业模式列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/newsList")
    @ResponseBody
    public List<News> newsList(int pageNum, int pageSize){
        List<News> newsList = newsApiService.searchAllNewsList(pageNum, pageSize,"");
        return newsList;
    }

    /**
     * 商业模式详情view
     *
     * @param businessId
     * @return
     */
    @RequestMapping("/newsDetail")
    public ModelAndView details(String businessId){

        News news = newsApiService.searchNewsById(businessId);
        ModelAndView view = new ModelAndView("/news/details");
        view.addObject("news", news);
        return view;
    }

    /**
     * 最新资讯view
     *
     * @return
     */
    @RequestMapping("/informationView")
    public ModelAndView informList()  {
        ModelAndView view=new ModelAndView();
        view.setViewName("/information/informationIntro");
        return view;
    }

    /**
     * 最新资讯列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/informationList")
    @ResponseBody
    public List<Information> informationList(int pageNum, int pageSize){
        List<Information> informList=informationservice.searchInfoList(pageNum, pageSize,"");
        return informList;
    }

    /**
     * 最新资讯详情view
     *
     * @param informationId
     * @return
     */
    @RequestMapping("/informDetail")
    public ModelAndView informDetail(String informationId){
        String userId = "";
        User user = getCurrUser();
        if (user != null){
            userId = user.getUserId();
        }
        ModelAndView view=new ModelAndView("/information/infoDetails") ;
        Information information = informationservice.selectById(informationId, userId);
        view.addObject("informationId", informationId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = formatter.format(information.getCreateTime());
        view.addObject("createTime", createTime);
        view.addObject("information", information);
        NewsCollect newsCollect= newsCommentService.searcUserNewCollect(userId ,informationId);
        PointRecord pointRecord = informationservice.selectinformationById(userId, informationId);//根据用户查询点赞记录表
        if (null == pointRecord){
            view.addObject("type",0);
        }else {
            view.addObject("type", pointRecord.getType());
        }
        int type = 1;
        if (newsCollect == null){//未操作收藏
            type = 1;
            view.addObject("isCollect", type);
        }else if (newsCollect.getIscollect() == 0){//收藏过，又取消了
            type = 2;
            view.addObject("isCollect", type);
        }else {//正在收藏中
            type = 3;
            view.addObject("isCollect", type);
        }
        return view;
    }

    /**
     * 资讯评论列表
     *
     * @param pageNum
     * @param pageSize
     * @param informationId
     * @return
     */
    @RequestMapping("/informationCommentList")
    @ResponseBody
    public List<NewsComment> informationCommentList(int pageNum, int pageSize, String informationId){
        List<NewsComment> informationCommentList = newsCommentService.searchCcList(pageNum, pageSize, informationId);
        if(informationCommentList!=null && informationCommentList.size()>0) {
            for (int i = 0; i < informationCommentList.size(); i++) {
                if (informationCommentList.get(i) != null) {
                    try {//展示之前进行解码
                        if(StringUtils.isNotBlank(informationCommentList.get(i).getNewsContent())){
                            String ccStr = java.net.URLDecoder.decode(informationCommentList.get(i).getNewsContent(), "UTF-8");
                            informationCommentList.get(i).setNewsContent(ccStr);
                        }
                        if(StringUtils.isNotBlank(informationCommentList.get(i).getNewsUsername())){
                            String newsUsernameStr = java.net.URLDecoder.decode(informationCommentList.get(i).getNewsUsername(), "UTF-8");
                            informationCommentList.get(i).setNewsUsername(newsUsernameStr);
                        }
                        if(StringUtils.isNotBlank(informationCommentList.get(i).getSignature())){
                            String signatureStr = java.net.URLDecoder.decode(informationCommentList.get(i).getSignature(), "UTF-8");
                            informationCommentList.get(i).setSignature(signatureStr);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    informationCommentList.get(i).setCrList(newsCommentService.searchCrList(informationCommentList.get(i).getNewsCommentId()));
                }
            }
        }
        return informationCommentList;
    }

    /**
     * 资讯点赞
     *
     * @param type
     * @param informationId
     * @return
     */
    @RequestMapping("/addInformation")
    @ResponseBody
    public Message addInformation(String type, String informationId){
        try {
            String userId = "";
            User user = getCurrUser();
            if (user != null){
                userId = user.getUserId();
            }
            if (StringUtils.isBlank(informationId)){
                return MessageUtils.getFail("资讯id不能为空！");
            }
            Information zixu_information = informationservice.selectByInfoId(informationId);//根据id查询咨询
            if (null == zixu_information){
                return MessageUtils.getFail("不存在该资讯！");
            }
            PointRecord pointRecord = informationservice.selectinformationById(userId, informationId);//根据用户查询点赞记录表
            if (null != pointRecord){
                return MessageUtils.getFail("您已经对该资讯进行过操作了！");
            }else {
                pointRecord = new PointRecord();
            }
            if (Integer.valueOf(type) == 1){//是点赞
                pointRecord.setType(1);
                zixu_information.setAgreeNum(zixu_information.getAgreeNum()+1);
            }
            if (Integer.valueOf(type) == 2){//倒赞
                pointRecord.setType(2);
                zixu_information.setDisagreeNum(zixu_information.getDisagreeNum()+1);
            }
            if (informationservice.updateAgreeNum(zixu_information) != 1){
                return MessageUtils.getFail("操作失败！");
            }
            pointRecord.setUserId(userId);
            pointRecord.setInformationId(informationId);
            pointRecord.setIsOperation(2);//记录已操作
            informationservice.addPointRecord(pointRecord);
            Message message = MessageUtils.getSuccess("success");
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("操作失败！"+e.getMessage());
        }
    }

    /**
     * 资讯收藏  取消收藏
     *
     * @param informationId
     * @param isCollect
     * @return
     */
    @RequestMapping("/collectionedInformation")
    @ResponseBody
    public  Map<String, Object> collectionedInformation(String informationId, String isCollect){//2取消收藏 1、收藏
        Map<String, Object> data = new HashMap<>();
        try {
            String userId = "";
            User user = getCurrUser();
            if (user != null){
                userId = user.getUserId();
            }
            boolean flagVal=false;
            NewsCollect newsCollect= newsCommentService.searcUserNewCollect(userId ,informationId);
            if (null == newsCollect){//空对象
                newsCollect = new NewsCollect();
                newsCollect.setCollectId(UUID.randomUUID().toString().replace("-",""));
                newsCollect.setNewsId(informationId);
                newsCollect.setUserId(userId);
                newsCollect.setIscollect(1);
                flagVal=newsCommentService.addCn(newsCollect);
                if (flagVal){
                    data.put("status", "success");
                    data.put("message", "操作成功！");
                    return data;
                }else {
                    data.put("status", "faile");
                    data.put("message", "操作失败！");
                    return data;
                }
            }else if (newsCollect.getIscollect() == 0){//收藏过但是取消了
                newsCollect.setIscollect(1);
            }else {
                newsCollect.setIscollect(0);
            }
            flagVal = newsCommentService.updatecollect(newsCollect);
            if (flagVal){
                data.put("status", "success");
                data.put("message", "操作成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "操作失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "操作失败！");
            return data;
        }
    }

    @RequestMapping("/addCommcontent")
    @ResponseBody
    public  Map<String,Object> addCommContent(String commtentConten, String informationId){
        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(commtentConten)){
                data.put("status", "faile");
                data.put("message", "评论内容不能为空！");
                return data;
            }
            NewsComment newsComment = new NewsComment();
            String userId = "";
            User user = getCurrUser();
            if (user != null){
                userId = user.getUserId();
            }
            boolean flvg;
            newsComment.setUserId(userId);
            newsComment.setNewsId(informationId);
            newsComment.setNewsContent(commtentConten);
            newsComment.setNewsName(user.getUserName());
            newsComment.setSignature(user.getSignature());
            newsComment.setCommentTime(new Date().getTime());
            flvg = newsCommentService.addcomm(newsComment);
            if (flvg){
                data.put("status", "success");
                data.put("message", "评论成功！");
                return data;
            }else {
                data.put("status", "success");
                data.put("message", "评论失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "评论失败！");
            return data;
        }
    }

}