package cn.com.myproject.user.controller;

import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.service.IAdvertiseService;
import cn.com.myproject.service.ICourseService;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.IUserService;
import cn.com.myproject.user.entity.PO.Advertise;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@RequestMapping("/advertise")
@Controller
public class AdvertiseController {

    @Autowired
    private IAdvertiseService advertiseService;

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;
    /**
     * 首页广告
     * */
    @RequestMapping("/indexAdvertise")
    public String index(){

        return "advertise/advertise_index";
    }

    //首页广告列表
    @RequestMapping("/getAdvertiseIndex")
    @ResponseBody
    public PageInfo<Advertise> getPage(int page, int rows, HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String,Object> map = new HashMap<>();
        String keyword = request.getParameter("keyword");
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        map.put("type", 1);
        return advertiseService.getPage(map);
    }

    //所有广告
    @RequestMapping("/list")
    @ResponseBody
    public List<Advertise> list(){

        return advertiseService.selectAll();
    }

    //首页广告添加
    @RequestMapping("/addIndexAdvertise")
    @ResponseBody
    public Map<String, Object> addIndexAdvertise(MultipartFile file, Advertise advertise, HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> data = new HashMap<>();
        try {
            String status_add = request.getParameter("status_add");
            String type = request.getParameter("type_add");
            String linkType = request.getParameter("linkType");
            String detail_id = request.getParameter("detailId");
            String name = request.getParameter("name");
            advertise.setName(name);
            Short status = new Short(status_add);
            advertise.setStatus(status);
            advertise.setType(Integer.valueOf(type));
            advertise.setLinkType(Integer.valueOf(linkType));
            advertise.setDetailId(detail_id);
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                return data;
            }
            advertise.setAdverImg(fileUrl);
            if (advertiseService.addAdver(advertise) != 1){
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

//添加学习广告
    @RequestMapping("/addStudyAdvertise")
    @ResponseBody
    public Map<String, Object> addStudyAdvertise(MultipartFile file, Advertise advertise, HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> data = new HashMap<>();
        try {
            String status_add = request.getParameter("status_add");
            String deatailId = request.getParameter("detailId");
            Short status = new Short(status_add);
            advertise.setStatus(status);
            advertise.setType(2);
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                return data;
            }
            advertise.setAdverImg(fileUrl);
            advertise.setDetailId(deatailId);
            if (advertiseService.addAdver(advertise) != 1){
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

    //商城广告添加
    @RequestMapping("/addMallAdvertise")
    @ResponseBody
    public Map<String, Object> addMallAdvertise(MultipartFile file, Advertise advertise, HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> data = new HashMap<>();
        try {
            String status_add = request.getParameter("status_add");
            Short status = new Short(status_add);
            advertise.setStatus(status);
            advertise.setType(3);
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
                data.put("status", "faile");
                data.put("message", "上传图片失败！");
                return data;
            }
            advertise.setAdverImg(fileUrl);
            if (advertiseService.addAdver(advertise) != 1){
                data.put("status", "faile");
                data.put("message", "添加失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "添加成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "添加失败！");
        }
        return data;
    }

    //通过id查询广告
    @RequestMapping("/selectAdverById")
    @ResponseBody
    public Advertise selectAdverById(String adverId){

        return advertiseService.selectAdverById(adverId);
    }

    //删除广告
    @RequestMapping("/deleteAdver")
    @ResponseBody
    public Map<String, Object> deleteAdver(String adverId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(adverId)){
                data.put("status" , "faile");
                data.put("message","广告编号不能为空！");
                return data;
            }
            Advertise advertise = advertiseService.selectAdverById(adverId);
            if (null == advertise){
                data.put("status" , "faile");
                data.put("message","没有该广告！");
                return data;
            }
            if (advertiseService.deleteAdver(adverId) != 1){
                data.put("status", "faile");
                data.put("message", "删除失败！");
                return data;
            }
            data.put("status" , "success");
            data.put("message", "删除成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "删除失败！");
            return data;
        }
        return data;
    }

    //首页广告修改
    @RequestMapping("/updateAdver")
    @ResponseBody
    public Map<String, Object> updateAdver(MultipartFile file, Advertise advertise,
                                           HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> data = new HashMap<>();
        try {
            String adverId = request.getParameter("adver_id");
            String name = request.getParameter("name");
            String linkType = request.getParameter("linkType");
            Advertise adver = advertiseService.selectAdverById(adverId);
            if (null == adver){
                data.put("status", "faile");
                data.put("message", "没有该广告！");
                return data;
            }
            String fileUrl = uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(fileUrl)) {
                adver.setAdverImg(fileUrl);
            }
            adver.setStatus(advertise.getStatus());
            if (adver.getType() == 1){//如果是首页的轮播图可以修改类型
                adver.setType(advertise.getType());
            }
            adver.setName(name);
            adver.setDetailId(advertise.getDetailId());
            adver.setCreateTime(advertise.getCreateTime());
            adver.setAdverName(advertise.getAdverName());
            adver.setLinkType(Integer.valueOf(linkType));
            if (advertiseService.updateAdver(adver) != 1){
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


    /**
     * 学习广告
     * */
    @RequestMapping("/studyAdvertise")
    public String studyIndex(){

        return "advertise/studyAdvertise";
    }

    //学习广告列表
    @RequestMapping("/getStudyAdvertise")
    @ResponseBody
    public PageInfo<Advertise> getStudyAdvertise(int page, int rows, HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String,Object> map = new HashMap<>();
        String keyword = request.getParameter("keyword");
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        map.put("type", 2);
        return advertiseService.getPage(map);
    }

    /**
     * 商城广告
     * */
    @RequestMapping("/mallAdvertise")
    public String mallAdvertise(){

        return "advertise/mallAdvertise";
    }

    //商城广告展示列表
    @RequestMapping("/getMallAdvertise")
    @ResponseBody
    public PageInfo<Advertise> mallAdvertise(int page, int rows, HttpServletRequest request) throws UnsupportedEncodingException {

        Map<String,Object> map = new HashMap<>();
        String keyword = request.getParameter("keyword");
        if (org.apache.commons.lang.StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        map.put("pageNum",page);
        map.put("pageSize",rows);
        map.put("type", 3);
        return advertiseService.getPage(map);
    }

    //查询所有的课程
    @RequestMapping("/selectAllCourse")
    @ResponseBody
    public List<Course> selectAllCourse(){

        return courseService.selectAllCourse();
    }

    //查询所有的教头
    @RequestMapping("/selectByUserType")
    @ResponseBody
    public List<User> selectTeacher(){

        return userService.selectByUserType(1);
    }

   //查询所有类别的课程
    @RequestMapping("/selectCategory")
    @ResponseBody
    public List<Course> selectCategory(int type){

        if (type == 4){
            return courseService.selectCategory("ypkc");
        }else if (type == 5){
            return courseService.selectCategory("spkc");
        }else if (type == 6){
            return courseService.selectCategory("zbkc");
        }else if (type == 7){
            return courseService.selectCategory("wzkc");
        }else {
            return courseService.selectCategory("jhdk");
        }
    }

    //查询所有的商品
    @RequestMapping("/goodsList")
    @ResponseBody
    public List<Goods> goodsList(String type){

        List<Goods> list = advertiseService.selectAllGoods();
        return list;
    }
    //添加轮播图页面
    @RequestMapping(value = "/addAdver")
    public ModelAndView addAdver(){

        ModelAndView view = new ModelAndView("advertise/addAdvertise");
        return view;
    }

    //修改首页轮播图页面
    @RequestMapping(value = "/updatePage")
    public ModelAndView updatePage(String adverId){

        ModelAndView view = new ModelAndView("advertise/updateIndexAdvertise");
        view.addObject("adverId", adverId);
        return view;
    }

    //修改学习轮播图页面
    @RequestMapping(value = "/updateStudyImg")
    public ModelAndView updateStudyImg(String adverId){

        ModelAndView view = new ModelAndView("advertise/updateStudyAdvertise");
        view.addObject("adverId", adverId);
        return view;
    }

    //修改商城轮播图页面
    @RequestMapping(value = "/updateMallImg")
    public ModelAndView updateMallImg(String adverId){

        ModelAndView view = new ModelAndView("advertise/updateMallAdvertise");
        view.addObject("adverId", adverId);
        return view;
    }
}
