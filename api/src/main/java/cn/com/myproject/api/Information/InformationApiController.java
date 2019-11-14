package cn.com.myproject.api.Information;

import cn.com.myproject.api.service.IInformationservice;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.user.entity.PO.Information;
import cn.com.myproject.user.entity.PO.PointRecord;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.DomainEvents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李延超 on 2017/8/28.
 */
@RestController
@RequestMapping("/api/information")
@Api(value="资讯",tags="资讯列表")

public class InformationApiController {
    @Autowired
    private IInformationservice informservice;

    @Value("${jtxyapp.url}")
    private String jtxyappUrl;

    @PostMapping(value = "/searchInfoList")
    @Deprecated
    @ApiOperation(value = "资讯相关信息", produces = "application/json")
    public Message index() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Information> list = informservice.getAll();
         data.put("Information",list);
         Message message= MessageUtils.getSuccess("获取成功!");
         message.setData(data);
        return message;
        }catch (RuntimeException e){
            Message message=MessageUtils.getFail("请求失败"+e.getMessage());
            return message;
        }
    }

    @PostMapping("/index")
    @ApiOperation(value = "资讯列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum",value = "当前第几页",
                    required = true, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页多少条",
                    required = true, dataType = "String",defaultValue = "10"),
            @ApiImplicitParam(paramType="query",name = "title", value = "资讯名称",
                    required = false, dataType = "String",defaultValue = "")
    })
    public Message<List<Information>> index(String pageNum, String pageSize, String Title, HttpServletRequest request){
        int pageNumVal = 0;
        int pageSizeVal = 0;
        if(StringUtils.isNotBlank(pageNum)){
            pageNumVal = Integer.parseInt(pageNum);
        }
        if(StringUtils.isNotBlank(pageSize)){
            pageSizeVal = Integer.parseInt(pageSize);
        }
        // 查询资讯列表信息
        List<Information> iList = informservice.searchInfoList(pageNumVal,pageSizeVal,"");
        if(iList != null && iList.size() > 0){
            for(int i = 0;i < iList.size();i++){
                if(iList.get(i) != null){
                    iList.get(i).setInformationIntroUrl(jtxyappUrl+"/api/information/informationIntro?informationId="+iList.get(i).getInformationId());
                }
            }
        }
        Message<List<Information>> message = MessageUtils.getSuccess("success");
        message.setData(iList);
        return message;
    }

    @PostMapping(value = "/addInformation")
    @ApiOperation(value = "点赞相关信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "informationId",value = "资讯id",
                    required = true, dataType = "String", defaultValue = ""),
            @ApiImplicitParam(paramType="query",name = "type",value = "点赞类型 1、点赞 2、倒赞",
                    required = true, dataType = "String",defaultValue = ""),

    })
    public Message addInformation(String userId, String type, String informationId){

        try {
            if (StringUtils.isBlank(userId)){
                return MessageUtils.getFail("用户id不能为空！");
            }
            if (StringUtils.isBlank(informationId)){
                return MessageUtils.getFail("资讯id不能为空！");
            }
            Information zixu_information = informservice.selectByInfoId(informationId);//根据id查询咨询
            if (null == zixu_information){
                return MessageUtils.getFail("不存在该资讯！");
            }
            PointRecord pointRecord = informservice.selectinformationById(userId, informationId);//根据用户查询点赞记录表
            if (null != pointRecord){
                return MessageUtils.getFail("您已经对该资讯进行过操作了！");
            }else {
                pointRecord = new PointRecord();
            }

            if (Integer.valueOf(type) == 1){//是点赞
                pointRecord.setType(1);
                zixu_information.setAgreeNum(zixu_information.getAgreeNum()+1);
            }
            if (Integer.valueOf(type) == 2){//倒赞
                pointRecord.setType(2);
                zixu_information.setDisagreeNum(zixu_information.getDisagreeNum()+1);
            }
            if (informservice.updateAgreeNum(zixu_information) != 1){
                return MessageUtils.getFail("操作失败！");
            }
            pointRecord.setUserId(userId);
            pointRecord.setInformationId(informationId);
            pointRecord.setIsOperation(2);//记录已操作
            informservice.addPointRecord(pointRecord);
            Message message = MessageUtils.getSuccess("success");
            return message;
        }catch (RuntimeException e){
            return MessageUtils.getFail("操作失败！"+e.getMessage());
        }
    }




    /**
     * 精品推荐-最新资讯-资讯详情
     * @param informationId
     * @return
     */
    @ApiOperation(value = "精品推荐-最新资讯-资讯详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "informationId", value = "资讯业务ID", required = true, dataType = "String",defaultValue = "aa92c057e8fc4624b4afe8ceb84ed1f3"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户业务ID", required = true, dataType = "String",defaultValue = "54e6e44eb84f4f4f8795fb7de2979010")
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Information.class, responseContainer = "List" ) })
    @PostMapping("/informationDetail")
    public Message informationDetail(String informationId, String userId,HttpServletRequest request){

        try{
            if (StringUtils.isBlank(informationId)){
                return MessageUtils.getFail("咨询id不能为空！");
            }
            Information information = informservice.selectById(informationId, userId);
            if (null == information){
                return MessageUtils.getFail("没有该咨询！");
            }
            information.setInformationIntroUrl(jtxyappUrl+ "/api/information/informationIntro?informationId="+informationId);
            PointRecord pointRecord = informservice.selectinformationById(userId, informationId);
            if (null == pointRecord){
                information.setOperationType(0);
                information.setIsOperation(1);
            }else {
                int type = pointRecord.getType();//操作类型
                int  isOperation = pointRecord.getIsOperation();//是否操作过
                information.setIsOperation(isOperation);
                information.setOperationType(type);
            }
            Message message = MessageUtils.getSuccess("success");
            message.setData(information);
            return message;
        }catch(Exception e){
            return MessageUtils.getFail("获取失败！"+e.getMessage());
        }
    }
    /**
     * 精品推荐-最新资讯-资讯详情内容页面
     * @param informationId
     * @return
     */
    @ApiOperation(value = "精品推荐-最新资讯-资讯详情内容页面(app不许调用，资讯详情有url）", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "informationId", value = "教头ID", required = true, dataType = "String",defaultValue = "479505f1aa7b45a4bc2a0ee65919c241"),
    })
    @ApiResponses({ @ApiResponse( code = 200, message = "Success", response = Information.class, responseContainer = "List" ) })
    @GetMapping ("/informationIntro")
    public ModelAndView informationIntro(String informationId  ){
        ModelAndView view = new ModelAndView("/information/informIntro");

        Information information = informservice.selectInformById(informationId);
        view.addObject("informationIntro",information.getContent()==null?"无资讯内容附件":information.getContent());
        return view;
    }


}