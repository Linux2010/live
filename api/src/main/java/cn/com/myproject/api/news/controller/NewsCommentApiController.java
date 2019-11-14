package cn.com.myproject.api.news.controller;

import cn.com.myproject.api.service.IInformationservice;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.NewsCommentService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.Information;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.entity.PO.NewsCollect;
import cn.com.myproject.user.entity.PO.NewsComment;
import cn.com.myproject.user.entity.VO.NewsReplyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 李延超 on 2017/8/30.
 */
@RestController
@RequestMapping("/api/newsComment")
@Api(value="资讯",tags="资讯相关接口")
public class NewsCommentApiController {


    @Autowired
    private NewsCommentService newsCommentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IInformationservice informationservice;

    @PostMapping("/searchCcList")
    @ApiOperation(value = "根据咨询id查询咨询评论列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "newsId",value = "咨询id",
                    required = true, dataType = "String",defaultValue = "ba17d976c79144108b99ec8898f53981")
    })
    public Message<List<NewsComment>> searchCcList(String pageNum, String pageSize, String newsId){

        try {
            int pageNumVal = 0;
            int pageSizeVal = 0;
            if(StringUtils.isNotBlank(pageNum)){
                pageNumVal = Integer.parseInt(pageNum);
            }
            if(StringUtils.isNotBlank(pageSize)){
                pageSizeVal = Integer.parseInt(pageSize);
            }
            if (StringUtils.isBlank(newsId)){
                return MessageUtils.getFail("咨询id不能为空！");
            }
            Information information = informationservice.selectInfoById(newsId);
            if (null == information){
                return MessageUtils.getFail("没有该咨询！");
            }
            List<NewsComment> informationCommentList = newsCommentService.searchCcList(pageNumVal, pageSizeVal, newsId);
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
                            informationCommentList.get(i).setCommentTime(informationCommentList.get(i).getCreateTime());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if(newsCommentService.searchCrList(informationCommentList.get(i).
                                getNewsCommentId()).get(0).getReplyContent() == null){
                            informationCommentList.get(i).setCrList(new ArrayList<NewsReplyVO>());
                        }else{
                            informationCommentList.get(i).setCrList(newsCommentService.searchCrList(informationCommentList.get(i).getNewsCommentId()));
                        }
                    }
                }
            }
            Message<List<NewsComment>> message = MessageUtils.getSuccess("success");
            message.setData(informationCommentList);
            return message;
        }catch (RuntimeException e){

            return MessageUtils.getFail("获取失败！"+e.getMessage());
        }
    }




    @PostMapping("/addComm")
    @ApiOperation(value = "资讯评论", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "newsId",value = "资讯ID",
                    required = true, dataType = "String",defaultValue = "405891ba592c44ad88c25743d33d8e15"),
            @ApiImplicitParam(paramType="query",name = "commentContent",value = "评论内容",
                    required = true, dataType = "String",defaultValue = "内容不错"),
            @ApiImplicitParam(paramType="query",name = "userId",value = "评论用户的ID",
                    required = true, dataType = "String",defaultValue = "1bb854cf9d594bff89649039c2a33eff")
    })
    public Message addComm(String newsId, String commentContent, String userId){

        if (StringUtils.isBlank(newsId)){
            return MessageUtils.getFail("咨询id不能为空！");
        }
        if (StringUtils.isBlank(commentContent)){
            return MessageUtils.getFail("评论内容不能为空！");
        }
        if (StringUtils.isBlank(userId)){
            return MessageUtils.getFail("用户id不能为空！");
        }
        if(StringUtils.isNotBlank(commentContent)){
            if(commentContent.length() > 100){
                Message<Integer> message = MessageUtils.getFail("评论内容不能超过100字");
                message.setData(0);
                return message;
            }
        }

        Information information = informationservice.selectInfoById(newsId);
        if (null == information){
            return MessageUtils.getFail("没有该咨询！");
        }
        User user = userService.selectById(userId);
        if (null == user){
            return MessageUtils.getFail("没此用户！");
        }
        NewsComment newsComment = new NewsComment();
        newsComment.setNewsCommentId(UUID.randomUUID().toString().replace("-", ""));
        newsComment.setNewsId(newsId);
        try {
            // 存入数据库前进行转码
            newsComment.setNewsContent(java.net.URLEncoder.encode(commentContent, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        newsComment.setUserId(userId);
        newsComment.setCommentTime(new Date().getTime());
        newsComment.setNewsUsername(user.getUserName());
        newsComment.setNewsName(information.getTitle());
        newsComment.setPhoto(user.getPhoto());
        newsComment.setSignature(user.getSignature());
        newsComment.setUserIdentity(user.getUserIdentity());
        newsComment.setCreateTime(new Date().getTime());
        newsComment.setCommentNum(newsComment.getCommentNum()+1);


        boolean flagVal = newsCommentService.addcomm(newsComment);
        if (flagVal){
            Message message = MessageUtils.getSuccess("success");
            return message;
        }else {
            return MessageUtils.getFail("评论失败！");
        }

    }

    @PostMapping("/addCn")
    @ApiOperation(value="收藏资讯",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "newsId", value = "资讯Id",
                    required = true, dataType = "String", defaultValue = "479505f1aa7b45a4bc2a0ee65919c241"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id",
                    required = true, dataType = "String ", defaultValue = "748325edabf54ba983dd3f90fff9898f"),
            @ApiImplicitParam(paramType = "query", name = "isCollect", value = "是否收藏：0是不收藏，1是收藏",
                    required = true, dataType = "String ", defaultValue = "1")
    })
    public  Message<Boolean> addCn(String userId ,String newsId,String isCollect){

        try {
            if (StringUtils.isBlank(newsId)){
                return MessageUtils.getFail("咨询id不能为空！");
            }
            if (StringUtils.isBlank(userId)){
                return MessageUtils.getFail("用户id不能为空");
            }
            Information information=informationservice.selectInfoById(newsId);
            if(null==information){
                return MessageUtils.getFail("没有这条资讯");
            }
            User user=userService.selectById(userId);
            if (null == user){
                return MessageUtils.getFail("没有该用户！");
            }
            boolean flagVal=false;
            NewsCollect newsCollect= newsCommentService.searcUserNewCollect(userId ,newsId);
            if(newsCollect == null ){
                newsCollect = new NewsCollect();
                String collectId=UUID.randomUUID().toString().replace("-","");
                newsCollect.setCollectId(collectId);
                newsCollect.setNewsId(newsId);
                newsCollect.setUserId(userId);
                newsCollect.setIscollect(Integer.valueOf(isCollect)); //文章是否收藏：0是不收藏，1是收藏
                flagVal=newsCommentService.addCn(newsCollect);
            }else{
                newsCollect.setIscollect(Integer.valueOf(isCollect)); //文章是否收藏：0是不收藏，1是收藏
                flagVal = newsCommentService.updatecollect(newsCollect);
            }

            Message<Boolean> message=null;
            if(flagVal){
                message=MessageUtils.getSuccess("success");
            }else{
                message=MessageUtils.getFail("fail");
            }
            message.setData(flagVal);
            return  message;
        }catch (RuntimeException e){

            return MessageUtils.getFail("收藏失败！"+e.getMessage());
        }
    }


}
