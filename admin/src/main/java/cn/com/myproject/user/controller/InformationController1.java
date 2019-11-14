package cn.com.myproject.user.controller;

import cn.com.myproject.service.IInformationService;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.user.entity.PO.News;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.com.myproject.user.entity.PO.Information;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李延超 on 2017/8/18.
 */
@RequestMapping("/inform")
@Controller
public class InformationController1 {

    @Autowired
    private IInformationService informService;

    @Autowired
    private IUploadImgService uploadImgService;

    @RequestMapping("/")
    public String index() {

        return "news/information";
    }

    @RequestMapping("/addinfo")
    @ResponseBody
    public Map<String ,Object> addInfo(MultipartFile file, Information information,HttpServletResponse response, HttpServletRequest request){
        Map<String ,Object> data=new HashMap<>();
        try{

            String fileUrl=uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options","SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isBlank(fileUrl)) {
                data.put("status", "fail");
                data.put("message", "上传图片失败！");
                return data;
            }
            information.setPhoto(fileUrl);
            if (informService.addInfo(information) != 1){
                data.put("status","fail");
                data.put("message","添加失败");
                return data;
            }
            data.put("status","success");
            data.put("message","添加成功");
        }catch(RuntimeException e){
            data.put("status","fail");
            data.put("message","添加失败");
            return data;
        }
        return data;
    }
    @RequestMapping("/deleteinfo")
    @ResponseBody
    public Map<String, Object> delete(String informationId){

        Map<String, Object> result = new HashMap<>();
        try{
            if(StringUtils.isBlank(informationId)){
                result.put("status","fail");
                result.put("message", "编号不能为空！");
                return result;

            }
            Information information=informService.selectinformation(informationId);
            if(null==information){
                result.put("status", "fail");
                result.put("message", "没有该资讯！");
                return result;
            }
            informService.delInformation(informationId);
            result.put("status", "success");
            result.put("message","删除成功！");
        }catch (RuntimeException e){
            result.put("status", "fail");
            result.put("message", "删除失败！");
            return result;
        }
        return result;
    }

    @RequestMapping("/select")
    @ResponseBody
    public Information select(String informationId){
        return informService.selectinformation(informationId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Information> getAll(int page,int rows,String keyword) {
        return informService.searchInfoList(page,rows,keyword);
    }

    @RequestMapping(value="/chakan")
    public ModelAndView showInformationContent(String informationId){
        ModelAndView view=new ModelAndView("/news/showInformationContent");
        view.addObject("informationId",informationId);
        return view;
    }

    @RequestMapping(value = "/addContent")
    public ModelAndView addContent(String informationId){
        ModelAndView view=new ModelAndView("/news/addContent");
        view.addObject("informationId",informationId);
        return view;
    }

    @RequestMapping("/updateContent")
    @ResponseBody
    public Map<String, Object> updateContent(Information information){

        Map<String, Object> result = new HashMap<>();

        try {
            if (informService.updateContent(information) != 1){
                result.put("status", "fail");
                result.put("message", "上传失败！");
                return result;
            }
            result.put("status", "success");
            result.put("message", "上传成功！");
        }catch (RuntimeException e){
            result.put("status", "fail");
            result.put("message", "上传失败！");
            return result;
        }
        return result;
    }

    @RequestMapping("/updateTitle")
    @ResponseBody
    public Map<String, Object> updateTitle(Information information,MultipartFile file, HttpServletRequest request,
                                           HttpServletResponse response){
        Map<String, Object> data = new HashMap<>();

        try {
            String informationId= request.getParameter("informationId");
            if (StringUtils.isBlank(informationId)){
                data.put("status", "fail");
                data.put("message", "修改失败！");
                return data;
            }
            Information information1 = informService.selectinformation(informationId);
            if (null == information1){
                data.put("status", "fail");
                data.put("message", "修改失败！");
                return data;
            }
            String fileUrl=uploadImgService.uploadImg(file);
            response.setHeader("X-Frame-Options","SAMEORIGIN");
            if(org.apache.commons.lang3.StringUtils.isNotBlank(fileUrl)) {
                information1.setPhoto(fileUrl);
            }
            information1.setTitle(information.getTitle());
            if (informService.updateTitle(information1) ==1){
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
}
