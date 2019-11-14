package cn.com.myproject.user.controller;

import cn.com.myproject.service.IReturnGoodsService;
import cn.com.myproject.user.entity.PO.ReturnGoods;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LSG on 2017/9/14 0014.
 */
@Controller
@RequestMapping("/returnGoods")
public class ReturnGoodsController {

    @Autowired
    private IReturnGoodsService returnGoodsService;

    @RequestMapping("/")
    public String index(){

        return "returnGoods/returnGoods_index";
    }

    //退货申请列表
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<ReturnGoods> list (int rows, Integer page){

        return returnGoodsService.allReturnGoods(page, rows);
    }

    @RequestMapping("/page")
    @ResponseBody
    public PageInfo<ReturnGoods> page(int rows, int page, String status){

        if (null == status || StringUtils.isBlank(status)){
            return returnGoodsService.allReturnGoods(page, rows);
        }else {
            return returnGoodsService.getPage(page, rows, Integer.valueOf(status));
        }
    }

    @RequestMapping("/selectReturnGoodsById")
    @ResponseBody
    public ReturnGoods selectReturnGoodsById(String returnGoodsId){

        return returnGoodsService.selectById(returnGoodsId);
    }

    //删除退货申请记录
    @RequestMapping("/delReturnGoods")
    @ResponseBody
    public Map<String, Object> deleteById(String returnGoodsId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(returnGoodsId)){
                data.put("status", "faile");
                data.put("message", "退货单号不能为空！");
                return data;
            }
            ReturnGoods returnGoods = returnGoodsService.selectById(returnGoodsId);
            if (null == returnGoods){
                data.put("status", "faile");
                data.put("message", "删除失败，没有该退货项！");
                return data;
            }
            if (returnGoodsService.deleteById(returnGoodsId) == 1){
                data.put("status", "success");
                data.put("message", "删除成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "删除失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "删除失败！");
            return data;
        }
    }

    //同意退货申请操作
    @RequestMapping("/agree")
    @ResponseBody
    public Map<String, Object> agree(String returnGoodsId){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(returnGoodsId)){
                data.put("status", "faile");
                data.put("message", "退货申请编号不能为空！");
                return data;
            }
            ReturnGoods returnGoods = returnGoodsService.selectById(returnGoodsId);
            if (null == returnGoods){
                data.put("status", "faile");
                data.put("message", "操作失败，没有该退货申请！");
                return data;
            }
            returnGoods.setOperationType(1);//同意退货
            returnGoods.setStatus((short)1);
            returnGoods.setHandleTime(new Date().getTime());
            if (returnGoodsService.updateReturnGoods(returnGoods) == 1){
                data.put("status", "success");
                data.put("message", "操作成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "操作失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "操作失败！");
            return data;
        }
    }

    //拒绝退货申请操作
    @RequestMapping("/refuse")
    @ResponseBody
    public Map<String, Object> refuse(String returnGoodsId, String refuseReason){

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(returnGoodsId)){
                data.put("status", "faile");
                data.put("message", "退货申请编号不能为空！");
                return data;
            }
            if (StringUtils.isBlank(refuseReason)){
                data.put("status", "faile");
                data.put("message", "拒绝原因不能为空！");
                return data;
            }
            ReturnGoods returnGoods = returnGoodsService.selectById(returnGoodsId);
            if (null == returnGoods){
                data.put("status", "faile");
                data.put("message", "操作失败，没有该退货申请！");
                return data;
            }
            returnGoods.setRefuseReason(refuseReason);
            returnGoods.setOperationType(2);
            returnGoods.setStatus((short)2);
            returnGoods.setHandleTime(new Date().getTime());
            if (returnGoodsService.updateReturnGoods(returnGoods) == 1){
                data.put("status", "success");
                data.put("message", "操作成功！");
                return data;
            }else {
                data.put("status", "faile");
                data.put("message", "操作失败！");
                return data;
            }
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "操作失败！");
            return data;
        }
    }
}








