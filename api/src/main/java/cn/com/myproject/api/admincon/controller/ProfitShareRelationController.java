package cn.com.myproject.api.admincon.controller;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.admincon.IProfitShareRelationService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.*;

/**
 * @auther CQC
 * @create 2017.9.5
 */

@RestController
@RequestMapping("/api/psr")
@Api(value="分销关系类",tags = "分级关系")
public class ProfitShareRelationController {

    @Autowired
    private IProfitShareRelationService profitShareRelationService;

    @Autowired
    private IUserService userService;

    @PostMapping("/getCurrentUser")
    @ApiOperation(value = "获取当前用户信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348")
    })
    public Message getCurrentUser(String userId){
        Message message = new Message();
        try{
            message = MessageUtils.getSuccess("success");
            User user = userService.selectById(userId);
            message.setData(user);
        }catch(Exception e){
            message = MessageUtils.getFail("fail");
        }
        return message;
    }

    @PostMapping("/getUpLevelUser")
    @ApiOperation(value = "获取上级用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348")
    })
    public Message getUpLevelUser(String userId){
        Message message = new Message();
        try{
            message = MessageUtils.getSuccess("success");
            ProfitShareRelation  profitShareRelation = profitShareRelationService.getUpLevelUser(userId);
            message.setData(profitShareRelation);
        }catch(Exception e){
            message = MessageUtils.getFail("fail");
        }
        return message;
    }

    @PostMapping("/getSecondLevelUser")
    @ApiOperation(value = "获取二级用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348")
    })
    public Message getSecondLevelUser(String pageNum, String pageSize, String userId){
        Message message = new Message();
        message = MessageUtils.getSuccess("success");
        java.util.List<ProfitShareRelation> list = profitShareRelationService.getSecondLevelUser(Integer.valueOf(pageNum).intValue(),Integer.valueOf(pageSize).intValue(),userId);
        message.setData(list);
        return message;
    }

    @PostMapping("/getThreeLevelUser")
    @ApiOperation(value = "获取三级用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348")
    })
    public Message getThreeLevelUser(String pageNum, String pageSize, String userId){
        Message message = new Message();
        message = MessageUtils.getSuccess("success");
        java.util.List<ProfitShareRelation> list = profitShareRelationService.getThreeLevelUser(Integer.valueOf(pageNum).intValue(),Integer.valueOf(pageSize).intValue(),userId);
        message.setData(list);
        return message;
    }

    @PostMapping("/getThreeLevelUserCount")
    @ApiOperation(value = "获取三级用户人数", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID",
                    required = true, dataType = "String",defaultValue = "cf052dc4301f4170adddd3e9c889c348")
    })
    public Message getThreeLevelUserCount(String userId){
        Message message = new Message();
        message = MessageUtils.getSuccess("success");
        Integer count  = profitShareRelationService.getThreeLevelUserCount(userId);
        Map<String,String> map = new HashMap<>();
        map.put("count",String.valueOf(count));
        message.setData(map);
        return message;
    }

}