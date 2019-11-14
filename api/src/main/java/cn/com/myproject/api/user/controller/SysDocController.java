package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.service.ISysDocService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案api接口控制器类
 */
@RestController
@RequestMapping(value = "/api/sysDoc")
@Api(value="文案",tags = "系统文案")
public class SysDocController {

    @Autowired
    private ISysDocService iSysDocService;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    /**
     * 根据文案类型查询文案内容
     *
     * @param docType
     * @return
     */
    @PostMapping("/searchSdcByType")
    @ApiOperation(value = "根据文案类型查询文案内容", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "docType",value = "文案类型：1.学分制度、2.激活码制度、3.邀请好友规则、4.积分制度",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public Message<String> searchSdcByType(String docType){
        String contentUrl = jtxyappUrl + "/api/sysDoc/showDcPage?docType="+docType;
        Message<String> message = MessageUtils.getSuccess("success");
        message.setData(contentUrl);
        return message;
    }

    /**
     * 根据文案类型跳转到相应的文案H5页面
     *
     * @param docType
     * @return
     */
    @GetMapping("/showDcPage")
    @ApiOperation(value = "根据文案类型跳转到相应的文案H5页面，APP不可使用", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "docType",value = "文案类型：1.学分制度、2.激活码制度、3.邀请好友规则、4.积分制度",
                    required = true, dataType = "String",defaultValue = "1")
    })
    public ModelAndView showDcPage(String docType){
        ModelAndView view = new ModelAndView("/sysDoc");
        int docTypeVal = 0;
        if(StringUtils.isNotBlank(docType)){
            docTypeVal = Integer.parseInt(docType);
        }
        String docContent = iSysDocService.searchSdcByType(docTypeVal);
        view.addObject("docType",docType);
        view.addObject("docContent",docContent==null?"暂无内容":docContent);
        return view;
    }

}