package cn.com.myproject.course.controller;

import cn.com.myproject.live.entity.PO.CourseType;
import cn.com.myproject.service.ICourseTypeService;
import cn.com.myproject.service.IUploadImgService;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类控制器类
 */
@Controller
@RequestMapping(value = "/courseType")
public class CourseTypeController {

    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private IUploadImgService uploadImgService;

    /**
     * 展示课程分类首页
     *
     * @return
     */
    @RequestMapping(value = "/courseTypeIndex")
    public String courseTypeIndex(){
        return "/course/course_type_index";
    }

    /**
     * 展示课程分类子页面
     *
     * @return
     */
    @RequestMapping(value = "/courseTypeChildren")
    public ModelAndView courseTypeChildren(String parentId){
        ModelAndView view = new ModelAndView("/course/course_type_children");
        CourseType ct = courseTypeService.searchCtById(parentId);
        if(ct != null){
            view.addObject("parentId",parentId);
            view.addObject("typeVal",ct.getTypeVal());
            view.addObject("typeName",ct.getTypeName());
        }
        return view;
    }

    /**
     * 添加课程分类
     *
     * @param file
     * @param typename_add
     * @param status_add
     * @param parentId
     * @param typeLevel
     * @param typeVal
     * @return
     */
    @RequestMapping(value = "/addCt")
    @ResponseBody
    public String addCt(MultipartFile file,String typename_add,int status_add,
                        String parentId,int typeLevel,String typeVal){
        String result = "1";
        CourseType courseType = new CourseType();
        courseType.setTypeName(typename_add);
        courseType.setStatus((short)status_add);
        courseType.setParentId(parentId);
        courseType.setTypeLevel(typeLevel);
        courseType.setTypeVal(typeVal);
        String urlVal = uploadImgService.uploadImg(file,"courseType");
        if(StringUtils.isNotBlank(urlVal)){
            courseType.setTypeUrl(urlVal);
        }
        boolean flagVal = courseTypeService.addCourseType(courseType);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 根据courseTypeId删除课程分类
     *
     * @param courseTypeId
     * @return
     */
    @RequestMapping(value = "/removeCt")
    @ResponseBody
    public String removeCt(String courseTypeId){
        String result = "1";
        int courseCount = courseTypeService.searchCourseCountByCtId(courseTypeId);
        if(courseCount > 0){// 如果课程分类下已经上传课程，则不能删除
            result = "2";
        }else{
            boolean flagVal = courseTypeService.removeCourseType(courseTypeId);
            if(flagVal){
                result = "0";
            }
        }
        return result;
    }

    /**
     * 修改课程分类信息
     *
     * @param file
     * @param typename_update
     * @param status_update
     * @param courseTypeId
     * @return
     */
    @RequestMapping(value = "/modifyCt")
    @ResponseBody
    public String modifyCt(MultipartFile file, String typename_update, int status_update, String courseTypeId){
        String result = "1";
        CourseType courseType = new CourseType();
        courseType.setCourseTypeId(courseTypeId);
        courseType.setTypeName(typename_update);
        courseType.setStatus((short)status_update);
        String urlVal = uploadImgService.uploadImg(file,"courseType");
        if(StringUtils.isNotBlank(urlVal)){
            courseType.setTypeUrl(urlVal);
        }
        boolean flagVal = courseTypeService.modifyCourseType(courseType);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 分页查询课程分类信息
     *
     * @param page 当前第几页
     * @param rows 每页多少条
     * @param typeName 分类名称
     * @param typeVal 分类所属类别
     * @param parentId 父节点ID
     * @return
     */
    @RequestMapping(value = "/searchCtList")
    @ResponseBody
    public PageInfo<CourseType> searchCtList(int page,int rows,String typeName,String typeVal,String parentId){
        return courseTypeService.searchCtList(page,rows,typeName,typeVal,parentId);
    }

    /**
     * 根据父节点查询子节点信息
     *
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/searchCtByParentId")
    @ResponseBody
    public String searchCtByParentId(String parentId,String typeVal){
        List<CourseType> ctList = courseTypeService.searchCtByParentId(parentId,typeVal);
        JsonArray jArr = new JsonArray();
        if(!ctList.isEmpty()){
            JsonObject jsonObject = null;
            for(int i = 0;i < ctList.size();i++){
                jsonObject = new JsonObject();
                if(ctList.get(i) != null){
                    jsonObject.addProperty("id",ctList.get(i).getCourseTypeId());
                    jsonObject.addProperty("text",ctList.get(i).getTypeName());
                    if("-1".equals(parentId)){// 如果是父节点了，则默认闭合
                        jsonObject.addProperty("state","closed");
                    }
                    jArr.add(jsonObject);
                }
            }
        }
        return jArr.toString();
    }

}