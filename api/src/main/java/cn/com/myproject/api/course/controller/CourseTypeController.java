package cn.com.myproject.api.course.controller;

import cn.com.myproject.api.service.ICourseTypeService;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.CourseType;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/*
 * Created by pangdatao on 2017-08-26
 * desc：课程类型控制器类
 */
@RestController
@RequestMapping(value = "/api/courseType")
@Api(value="课程分类",tags = "课程管理")
public class CourseTypeController {

    @Autowired
    private ICourseTypeService courseTypeService;

    /**
     * 查询课程分类列表
     *
     * @param typeVal
     * @param parentId
     * @return
     */
    @PostMapping("/searchAllCtList")
    @ApiOperation(value = "查询课程分类列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "typeVal",
                    value = "课程类别：spkc指视频课程，wzkc指文字课程，ypkc指音频课程，zbkc指直播课程",
                    required = true, dataType = "String",defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "parentId", value = "课程分类父节点ID",
                    required = true, dataType = "String",defaultValue = "")
    })
    public Message<List<CourseType>> searchAllCtList(String typeVal, String parentId){
        List<CourseType> ctList = courseTypeService.searchAllCtList(typeVal,parentId);
        Message<List<CourseType>> message = MessageUtils.getSuccess("success");
        message.setData(ctList);
        return message;
    }

}