package cn.com.myproject.api.user.controller;

import cn.com.myproject.api.service.user.IUserCapticalLogService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.UserCapitalLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jyp on 2017/10/9 0009.
 */
@RestController
@RequestMapping("/api/captionlog")
@Api(value="个人流水账单",tags = "个人流水账单")
public class UserCapticalLogController {

    public static Logger logger = LoggerFactory.getLogger(UserCapticalLogController.class);

    @Autowired
    private IUserCapticalLogService userCapticalLogService;

    /*@RequestMapping(value="/topup_list",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "我的充值记录列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前第几页", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页显示多少条", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id", required = true, dataType = "String",defaultValue = "9cf4475dc7244fb19f831c52ded04657")
    })
    public Message<List<UserCapitalLog>> topup_list(String pageNum, String pageSize, String userId){
        Message message = MessageUtils.getSuccess("success");
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<UserCapitalLog> list = userCapticalLogService.selectTopUpList(pageNumVal,pageSizeVal,userId);
        message.setData(list);
        return message;
    }

    @RequestMapping(value="/with_list",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "我的提现记录列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前第几页", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页显示多少条", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id", required = true, dataType = "String",defaultValue = "9cf4475dc7244fb19f831c52ded04657")
    })
    public Message<List<UserCapitalLog>> with_list(String pageNum, String pageSize, String userId){
        Message message = MessageUtils.getSuccess("success");
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<UserCapitalLog> list = userCapticalLogService.selectWithList(pageNumVal,pageSizeVal,userId);
        message.setData(list);
        return message;
    }*/

    @RequestMapping(value="/all_list",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "我的流水日志列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前第几页", required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页显示多少条", required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户Id", required = true, dataType = "String",defaultValue = "9cf4475dc7244fb19f831c52ded04657")
    })
    public Message<List<UserCapitalLog>> all_list(String pageNum, String pageSize, String userId){
        Message message = MessageUtils.getSuccess("success");
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        List<UserCapitalLog> list = userCapticalLogService.selectAllList(pageNumVal,pageSizeVal,userId);
        message.setData(list);
        return message;
    }
}
