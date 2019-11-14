package cn.com.myproject.api.indexImg.controller;

import cn.com.myproject.api.service.IAdvertiseService;
import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IShortVideoService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.Advertise;
import cn.com.myproject.user.entity.PO.ShortVideo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSG on 2017/8/28 0028.
 */
@RestController
@RequestMapping("/api/index")
@Api(value="首页类",tags = "轮播图")
public class IndexImgController {

    @Autowired
    private IAdvertiseService advertiseService;
    @Autowired
    private IShortVideoService shortVideoService;
    @Autowired
    private ICourseService courseService;
    /**
     * 首页轮播图
     */
    @PostMapping(value = "/advertiseList")
    @ApiOperation(value = "首页轮播图相关信息", produces = "application/json")
    @ApiImplicitParam(paramType="query",name = "type",
            value = "轮播图类型：1、首页轮播图2、学习轮播图3、商城轮播图",
            required = true, dataType = "String",defaultValue = "")
    public Message indexImg(String type){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(type) || Integer.valueOf(type) <= 0){
                return MessageUtils.getFail("轮播图类型有误！");
            }
            List<Advertise> list = new ArrayList<>();
            if (Integer.valueOf(type) == 1){//传过来1 代表是首页的轮播图
                list = advertiseService.selectAdverType(1, 1);
            }
            if (Integer.valueOf(type) == 2){//传过来2 代表是学习的轮播图
                list = advertiseService.selectAdverType(2,1);
            }
            if (Integer.valueOf(type) == 3){//传过来3 代表的是商城的轮播图
                list = advertiseService.selectAdverType(3,1);
            }
            data.put("advertise", list);
            Message message = MessageUtils.getSuccess("获取成功！");
            message.setData(data);
            return message;
        }catch (RuntimeException e){
            Message message = MessageUtils.getFail("获取失败！"+e.getMessage());
            return message;
        }
    }

    @PostMapping(value = "/shortVideo")
    @ApiOperation(value = "app启动视频相关信息", produces = "application/json")
    @ApiImplicitParam(paramType="query",name = "videoType",
            value = "App启动类型：1、ios使用视频 2、Android使用视频",
            required = true, dataType = "String",defaultValue = "")
    public Message<Map<String, Object>> shortVideoMessage(String videoType){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(videoType)){
                return MessageUtils.getFail("选择类型不能为空！");
            }
            ShortVideo shortVideo = new ShortVideo();
            String url = "";
            if (Integer.valueOf(videoType) == 1){//为ios使用视频
                 shortVideo = shortVideoService.selectByType(1);
                 url = courseService.getVideoUrl(Integer.valueOf(shortVideo.getFileId()));
                 shortVideo.setVideoUrl(url);
                 Message message = MessageUtils.getSuccess("获取成功！");
                 data.put("videoUrl", url);
                 message.setData(data);
                 return message;
            }else{
                shortVideo = shortVideoService.selectByType(2);
                url = courseService.getVideoUrl(Integer.valueOf(shortVideo.getFileId()));
                shortVideo.setVideoUrl(url);
                Message message = MessageUtils.getSuccess("获取成功！");
                data.put("videoUrl", url);
                message.setData(data);
                return message;
            }
        }catch (RuntimeException e){
            return MessageUtils.getFail("获取失败！"+e.getMessage());
        }
    }
}










