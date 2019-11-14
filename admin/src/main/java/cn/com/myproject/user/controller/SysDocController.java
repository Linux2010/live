package cn.com.myproject.user.controller;

import cn.com.myproject.live.entity.PO.SysDoc;
import cn.com.myproject.service.ISysDocService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案后台控制器类
 */
@RequestMapping("/sysDoc")
@Controller
public class  SysDocController {

    @Autowired
    private ISysDocService iSysDocService;

    @RequestMapping("/showSdIndex")
    public ModelAndView showSdIndex(){
        ModelAndView view = new ModelAndView("/buiness/sys_doc_index");
        return view;
    }

    @RequestMapping("/showSdEdit")
    public ModelAndView showSdEdit(int id,int docType){
        ModelAndView view = new ModelAndView("/buiness/sys_doc_edit");
        view.addObject("id",id);
        view.addObject("docType",docType);
        return view;
    }

    @RequestMapping("/showSdDetail")
    public ModelAndView showSdDetail(int docType){
        ModelAndView view = new ModelAndView("/buiness/sys_doc_detail");
        view.addObject("docType",docType);
        return view;
    }

    @RequestMapping("/searchSdList")
    @ResponseBody
    public PageInfo<SysDoc> selectSdList(int page, int rows, String docType){
        int docTypeVal = 0;
        if(StringUtils.isNotBlank(docType)){
            docTypeVal = Integer.parseInt(docType);
        }
        return iSysDocService.searchSdList(page,rows,docTypeVal);
    }

    @RequestMapping("/searchSdcByType")
    @ResponseBody
    public String searchSdcByType(int docType){
        return iSysDocService.searchSdcByType(docType);
    }

    @RequestMapping("/modifySdc")
    @ResponseBody
    public String modifySdc(SysDoc sysDoc){
        String resultStr = "1";
        if(iSysDocService.modifySdc(sysDoc)){
            resultStr = "0";
        }
        return resultStr;
    }

}