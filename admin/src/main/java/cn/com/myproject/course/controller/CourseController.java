package cn.com.myproject.course.controller;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.service.ICourseService;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.user.entity.PO.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/*
 * Created by pangdatao on 2017-08-23
 * desc：课程控制器类
 */
@Controller
@RequestMapping(value = "/course")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUploadImgService uploadImgService;

    /**
     * 查询讲师列表
     *
     * @return
     */
    @RequestMapping("/searchTeaList")
    @ResponseBody
    public List<User> searchTeaList(){
        return courseService.searchTeaList();
    }

    /**
     * 根据courseId查询课程信息
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/searchCourseById")
    @ResponseBody
    public Course searchCourseById(String courseId){
        return courseService.searchCourseById(courseId);
    }

    /**
     * 发布课程
     *
     * @param c
     * @return
     */
    @RequestMapping("/addC")
    @ResponseBody
    public String addC(MultipartFile file, MultipartFile file1,
                       String courseBeginTimeVal,String courseEndTimeVal,Course c){
        String result = "1";
        String urlVal = uploadImgService.uploadImg(file,"course");
        if(StringUtils.isNotBlank(urlVal)){
            c.setCourseCover(urlVal);
        }
        String urlVal1 = uploadImgService.uploadImg(file1,"course");
        if(StringUtils.isNotBlank(urlVal1)){
            c.setCourseImg(urlVal1);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if(StringUtils.isNotBlank(courseBeginTimeVal)){
                c.setCourseBeginTime(df.parse(courseBeginTimeVal).getTime());
            }
            if(StringUtils.isNotBlank(courseEndTimeVal)){
                c.setCourseEndTime(df.parse(courseEndTimeVal).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean flagVal = courseService.addCourse(c);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 删除课程
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/removeC")
    @ResponseBody
    public String removeC(String courseId){
        String result = "1";
        boolean flagVal = false;
        if(StringUtils.isNotBlank(courseId)){
            String[] idArr = courseId.split(",");
            if(idArr.length > 1){// 批量删除
                boolean flag = true;
                for(int i = 0;i < idArr.length;i++){
                    if(courseService.removeCourse(idArr[i]) == false){
                        flag = false;
                    }
                }
                flagVal = flag;
            }else{// 单个删除
                flagVal = courseService.removeCourse(courseId);
            }
        }
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 修改课程
     *
     * @param c
     * @return
     */
    @RequestMapping(value = "/modifyC")
    @ResponseBody
    public String modifyC(MultipartFile file,MultipartFile file1,
                          String courseBeginTimeVal,String courseEndTimeVal,Course c){
        String result = "1";
        String urlVal = uploadImgService.uploadImg(file,"course");
        if(StringUtils.isNotBlank(urlVal)){
            c.setCourseCover(urlVal);
        }
        String urlVal1 = uploadImgService.uploadImg(file1,"course");
        if(StringUtils.isNotBlank(urlVal1)){
            c.setCourseImg(urlVal1);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if(StringUtils.isNotBlank(courseBeginTimeVal)){
                c.setCourseBeginTime(df.parse(courseBeginTimeVal).getTime());
            }
            if(StringUtils.isNotBlank(courseEndTimeVal)){
                c.setCourseEndTime(df.parse(courseEndTimeVal).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean flagVal = courseService.modifyCourse(c);
        if(flagVal){
            result = "0";
        }
        return  result;
    }

    /**
     * 上传视频课程内容
     *
     * @param courseId
     * @param videoId
     * @param objName
     * @return
     */
    @RequestMapping(value = "/modifyV")
    @ResponseBody
    public String modifyV(String courseId,String videoId,String objName){
        String result = "1";
        Course c = new Course();
        c.setCourseId(courseId);
        c.setVideoId(videoId);
        c.setObjName(objName);
        boolean flagVal = courseService.modifyVideo(c);
        if(flagVal){
            result = "0";
        }
        return  result;
    }

    /**
     * 上传音频课程内容
     *
     * @param file
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/modifyY")
    @ResponseBody
    public String modifyV(MultipartFile file,String courseId){
        logger.debug("开始上传音频");
        String result = "1";
        Map<String,Object> returnMap = uploadImgService.uploadNeteaseVideoFile(file);
        logger.debug("上传音频结束，开始处理其他数据");
        Course c = new Course();
        c.setCourseId(courseId);
        if(returnMap != null){
            c.setVideoId(returnMap.get("vid").toString());
            c.setObjName(returnMap.get("objectName").toString());
        }
        boolean flagVal = courseService.modifyVideo(c);
        if(flagVal){
            result = "0";
        }
        return  result;
    }

    /**
     * 根据vId获取视频或音频播放路径
     *
     * @param vId
     * @return
     */
    @RequestMapping(value = "/getVideoUrl")
    @ResponseBody
    public String getVideoUrl(String vId){
        int vIdVal = 0;
        if(StringUtils.isNotBlank(vId)){
            vIdVal = Integer.parseInt(vId);
        }
        return courseService.getVideoUrl(vIdVal);
    }

    /**
     * 根据courseId查询课程考题
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/searchCtListByCId")
    @ResponseBody
    public List<CourseTopic> searchCtListByCId(String courseId){
        return courseService.searchCtListByCId(courseId);
    }

    /**
     * 根据courseId查询课程考题数量
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/searchCtCountByCId")
    @ResponseBody
    public int searchCtCountByCId(String courseId){
        return courseService.searchCtCountByCId(courseId);
    }

}