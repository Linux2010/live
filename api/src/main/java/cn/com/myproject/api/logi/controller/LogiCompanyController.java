package cn.com.myproject.api.logi.controller;

import cn.com.myproject.api.service.IDeliverMessageService;
import cn.com.myproject.api.service.ILogiCompanyService;
import cn.com.myproject.api.service.ILogiPlatformService;
import cn.com.myproject.api.util.kuaidi100;
import cn.com.myproject.logi.entity.PO.LogiPlatform;
import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.logi.entity.VO.DeliverMessageVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyp on 2017/9/18 0018.
 */
@RestController
@RequestMapping("/api/logi")
@Api(value = "订单的物流查询", tags = "订单的物流查询")
public class LogiCompanyController {

    private static final Logger logger = LoggerFactory.getLogger(LogiCompanyController.class);

    @Autowired
    private ILogiCompanyService logiCompanyService;

    @Autowired
    private ILogiPlatformService logiPlatformService;

    @Autowired
    private IDeliverMessageService deliverMessageService;

   /* @RequestMapping(value="/logi_list",method = RequestMethod.POST , produces = "application/json")
    @ApiOperation(value = "物流公司列表", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "pageNum", value = "当前第几页", required = false, dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name = "pageSize", value = "每页显示多少条", required = false, dataType = "String",defaultValue = "10")
    })
    public Message<List<logiCompany>> logi_list(String pageNum,String pageSize){
        Message message = new MessageUtils().getSuccess("success");
        List<logiCompany> list = logiCompanyService.logi_list(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        message.setData(list);
        return message;
    }*/

    /**
     * 物流信息
     *
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "/deliver_message", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "物流信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderNo", value = "订单号", required = false, dataType = "String", defaultValue = "o150632501930427lhdr"),
    })
    public Map deliver_message(String orderNo) {

        Map map = new HashMap();
        if (StringUtils.isBlank(orderNo)) {
            map.put("result", 0);
            map.put("message", "没有此订单的信息!");
            return map;
        }
        DeliverMessageVO deliverMessage = deliverMessageService.getMessage(orderNo);
        if (null == deliverMessage) {
            map.put("result", 0);
            map.put("message", "没有此订单的信息!");
            return map;
        }
        LogiCompany company = logiCompanyService.selectComByCode(deliverMessage.getCode());
        if (null == company) {
            map.put("result", 0);
            map.put("message", "没有此物流公司的信息,请核对后操作!");
            return map;
        }
        Map data = new HashMap();
        LogiPlatform logiPlatform = logiPlatformService.selectOpenPlat();
        if (null == logiPlatform) {
            map.put("result", 0);
            map.put("message", "没有此平台的信息,请核对后操作!");
        }
        JsonElement jsonElement = new JsonParser().parse(logiPlatform.getConfig());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String keyid_ = jsonObject.get("keyid").toString();
        String key = keyid_.substring(1, keyid_.length() - 1);
        String customer_ = jsonObject.get("code").toString();
        String customer = customer_.substring(1, customer_.length() - 1);
       /* data = kuaidi100.query_logi(deliverMessage.getCode(), deliverMessage.getDeliNo(), key, customer).;
        data.put("company", company.getName());
        map.put("wuliuresult",data);
        map.put("wuliulist",data.get("data"));
        map.put("result", 1);
        map.put("message", "success");*/
        data = kuaidi100.query_logi(deliverMessage.getCode(), deliverMessage.getDeliNo(), key, customer);
        Map<String,Object> message = new HashMap<>();
        map.put("wuliulist",data.get("data"));
        map.put("message",data.get("message"));
        map.put("nu",data.get("nu"));
        map.put("ischeck",data.get("ischeck"));
        map.put("condition",data.get("condition"));
        map.put("com",data.get("com"));
        map.put("company",company.getName());
        map.put("status",data.get("status"));
        map.put("state",data.get("state"));
        message.put("data",map);
        message.put("result", 1);
        message.put("message", "success");
        return message;
    }
}
