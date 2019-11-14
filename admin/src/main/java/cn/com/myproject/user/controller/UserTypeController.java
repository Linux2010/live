package cn.com.myproject.user.controller;

import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.user.entity.PO.UserType;
import cn.com.myproject.service.IUserTypeService;
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

/**
 * Created by LeiJia on 2017/8/21 0021.
 */

@Controller
@RequestMapping(value = "/userType")
public class UserTypeController {

    @Autowired
    private IUserTypeService userTypeService;

    @Autowired
    private IUploadImgService uploadImgService;

    /**
     * 展示教师分类首页
     *
     * @return
     */
    @RequestMapping(value = "/userTypeIndex")
    public String userTypeIndex(){
        return "/user/teacher_type_index";
    }

    /**
     * 展示教师分类子页面
     *
     * @return
     */
    @RequestMapping(value = "/userTypeChildren")
    public ModelAndView teacherTypeChildren(String parentId){
        ModelAndView view = new ModelAndView("/user/teacher_type_children");
        UserType userType = userTypeService.searchUserTypeById(parentId);
        if(userType != null){
            view.addObject("typeVal",userType.getTypeVal());
            view.addObject("typeName",userType.getTypeName());
        }
        view.addObject("parentId",parentId);
        return view;
    }

    /**
     * 添加教师分类
     *
     * @param userType
     * @return
     */
    @RequestMapping(value = "/addUserType")
    @ResponseBody
    public String addTeacherType(MultipartFile file ,UserType userType){

        String result = "1";
        //UserType userType = new UserType();
        String urlVal = uploadImgService.uploadImg(file,"useType");
        if(StringUtils.isNotBlank(urlVal)){
            userType.setUserTypePicture(urlVal);
        }
        boolean flagVal = userTypeService.addUserType(userType);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 根据teacherTypeId删除用户分类
     *
     * @param userTypeId
     * @return
     */
    @RequestMapping(value = "/removeUserType")
    @ResponseBody
    public String removeTeacherType(String userTypeId){
        String result = "1";
        boolean flagVal = userTypeService.removeUserType(userTypeId);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 修改用户分类信息
     *
     * @param userType
     * @return
     */
    @RequestMapping(value = "/modifyUserType")
    @ResponseBody
    public String modifyUserType(MultipartFile file ,UserType userType){
        String result = "1";
        String urlVal = uploadImgService.uploadImg(file,"useType");
        if(StringUtils.isNotBlank(urlVal)){
            userType.setUserTypePicture(urlVal);
        }
        boolean flagVal = userTypeService.modifyUserType(userType);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 分页查询用户分类信息
     *
     * @param page 当前第几页
     * @param rows 每页多少条
     * @param typeName 分类名称
     * @param typeVal 分类所属类别
     * @param parentId 父节点ID
     * @return
     */
    @RequestMapping(value = "/searchUserTypeList")
    @ResponseBody
    public PageInfo<UserType> searchUserTypeList(int page, int rows, String typeName, int typeVal, String parentId){
        return userTypeService.searchUserTypeList(page,rows,typeName,typeVal,parentId);
    }

    @RequestMapping(value = "/searchUserTypeByParentId")
    @ResponseBody
    public String searchUserTypeByParentId(String parentId){
        List<UserType> userTypeList = userTypeService.searchUserTypeByParentId(parentId);
        JsonArray jArr = new JsonArray();
        if(!userTypeList.isEmpty()){
            JsonObject jsonObject = null;
            for(int i = 0;i < userTypeList.size();i++){
                jsonObject = new JsonObject();
                if(userTypeList.get(i) != null){
                    jsonObject.addProperty("id",userTypeList.get(i).getUserTypeId());
                    jsonObject.addProperty("text",userTypeList.get(i).getTypeName());
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
