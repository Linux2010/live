package cn.com.myproject.user.controller;

import cn.com.myproject.service.ICourseService;
import cn.com.myproject.service.IShortVideoService;
import cn.com.myproject.user.entity.PO.ShortVideo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LSG on 2017/9/26 0026.
 */
@Controller
@RequestMapping("/shortVideo")
public class ShortVideoController {

    @Autowired
    private IShortVideoService shortVideoService;
    @Autowired
    private ICourseService courseService;
    //短视频页面
    @RequestMapping(value = "/")
    public String videoIndex(){

        return "shortVideo/video_index";
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public PageInfo<ShortVideo> getPage(int rows, int page, HttpServletRequest request) throws Exception{

        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotBlank(keyword)){
            keyword= URLDecoder.decode(keyword, "utf-8");
        }else {
            keyword = null;
        }
        PageInfo<ShortVideo> list = shortVideoService.getPage(page, rows, keyword);
        return list;
    }

    @RequestMapping("/selectVideo")
    @ResponseBody
    public ShortVideo selectShortVideo(String videoId){

        return shortVideoService.selectById(videoId);
    }

    @RequestMapping(value = "/getVideoUrl")
    @ResponseBody
    public String getVideoUrl(String vId){
        int vIdVal = 0;
        if(StringUtils.isNotBlank(vId)){
            vIdVal = Integer.parseInt(vId);
        }
        return courseService.getVideoUrl(vIdVal);
    }

    @RequestMapping("/addVideo")
    @ResponseBody
    public Map<String, Object> addVideo(ShortVideo shortVideo, HttpServletRequest request){

        Map<String, Object> data = new HashMap<>();
        try {
            String type = request.getParameter("type");
            if (null == type){
                data.put("status", "faile");
                data.put("message", "添加失败,请选择启动视频类型！");
                return data;
            }
            shortVideo.setVideoType(Integer.valueOf(type));
            if (shortVideoService.addVideo(shortVideo) == 1){
                data.put("status", "success");
                data.put("message", "添加成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "添加失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "添加失败！");
            return data;
        }
    }

    @RequestMapping("/delVideo")
    @ResponseBody
    public Map<String, Object> delVideo(String videoId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(videoId)){
                data.put("status", "faile");
                data.put("message", "文件编号不能为空！");
                return data;
            }
            ShortVideo shortVideo = shortVideoService.selectById(videoId);
            if (null == shortVideo){
                data.put("status", "faile");
                data.put("message", "删除失败，没有该视频！");
                return data;
            }
            if (shortVideoService.deleteVideo(videoId) == 1){
                data.put("status", "success");
                data.put("message", "删除成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "删除失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "删除失败！");
            return data;
        }
    }

    @RequestMapping("/updateVideo")
    @ResponseBody
    public Map<String, Object> updateVideo(String fileId, String videoFile, HttpServletRequest request){

        Map<String, Object> data = new HashMap<>();
        try {
            String videoId = request.getParameter("videoId");
            if (StringUtils.isBlank(videoId)){
                data.put("status", "faile");
                data.put("message", "视频id不能为空！");
                return data;
            }
            if (StringUtils.isBlank(fileId)){
                data.put("status", "faile");
                data.put("message", "视频文件编号不能为空！");
                return data;
            }
            if (StringUtils.isBlank(videoFile)){
                data.put("status", "faile");
                data.put("message", "视频文件内容不能为空！");
                return data;
            }
            ShortVideo shortVideo = shortVideoService.selectById(videoId);
            if (null == shortVideo){
                data.put("status", "faile");
                data.put("message", "修改失败，没有该视频文件！");
                return data;
            }
            shortVideo.setFileId(fileId);
            shortVideo.setVideoFile(videoFile);
            if (shortVideoService.updateVideo(shortVideo) == 1){
                data.put("status", "success");
                data.put("message", "修改成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "修改失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "修改失败！");
            return data;
        }
    }

    @RequestMapping("/updateVideoStatus")
    @ResponseBody
    public Map<String, Object> updateVideoStatus(String videoId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(videoId)){
                data.put("status", "faile");
                data.put("message", "视频id不能为空！");
                return data;
            }
            ShortVideo shortVideo = shortVideoService.selectById(videoId);
            if (null == shortVideo){
                data.put("status", "faile");
                data.put("message", "使用失败，没有该视频文件！");
                return data;
            }
            if (StringUtils.isBlank(shortVideo.getFileId())){
                data.put("status", "faile");
                data.put("message", "使用失败，未上传视频！");
                return data;
            }
            ShortVideo typeForVideo = new ShortVideo();
            typeForVideo = shortVideoService.selectByType(shortVideo.getVideoType());//查询出ios正在使用的
            if (null == typeForVideo){
                shortVideo.setStatus((short)2);
                if (shortVideoService.updateStatus(shortVideo) == 1){
                    data.put("status", "success");
                    data.put("message", "使用成功！");
                    return data;
                }else {
                    data.put("status", "faile");
                    data.put("message", "使用失败！");
                    return data;
                }
            }else {
                typeForVideo.setStatus((short)1);
                shortVideoService.updateStatus(typeForVideo);
                shortVideo.setStatus((short)2);
                if (shortVideoService.updateStatus(shortVideo) == 1){
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
