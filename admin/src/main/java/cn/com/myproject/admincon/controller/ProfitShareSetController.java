package cn.com.myproject.admincon.controller;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import cn.com.myproject.service.ISysUserService;
import cn.com.myproject.service.IUserRoleService;
import cn.com.myproject.service.admincon.IProfitShareSettingService;
import cn.com.myproject.sysuser.entity.PO.SysUser;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import cn.com.myproject.sysuser.entity.VO.SysUserVO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profitshareset")
public class ProfitShareSetController {
    private static final Logger logger = LoggerFactory.getLogger(ProfitShareSetController.class);

    @Autowired
    private IProfitShareSettingService profitShareSettingService;

    @RequestMapping("/")
    public String index() {
        return "admincon/profit_setting_index";
    }

    @RequestMapping("/add")
    public String add() {
       return "sys/user_add";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<ProfitShareSettingVO> list(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page) {
        PageInfo<ProfitShareSettingVO> info = profitShareSettingService.getPage(page,rows);
        return info;
    }

    @ResponseBody
    @RequestMapping("/addSet")
    public Map<String,Object> addProfitShareSetting(ProfitShareSetting profitShareSetting) {
        Map<String,Object> result = new HashMap();
        try {
            int i = profitShareSettingService.insert(profitShareSetting);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加分润规则",ex.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateSet")
    public Map<String,Object> updateUsers(ProfitShareSetting profitShareSetting) {
        Map<String,Object> result = new HashMap<>();
        try {

            int i = profitShareSettingService.updateByPrimaryKey(profitShareSetting);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改分润规则",e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteSet")
    public Map<String,Object> deleteSet(String id) {
        Map<String,Object> result = new HashMap<>();
        try {
            if(StringUtils.isEmpty(id)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
            int i = profitShareSettingService.deleteByPrimaryKey(id);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","删除分润规则",e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getSetting")
    public Map<String,Object> getSetting(String id) {
        Map<String,Object> result = new HashMap<>();
        try{
            if(StringUtils.isBlank(id)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
            ProfitShareSetting profitShareSetting = profitShareSettingService.selectByPrimaryKey(id);
            if(null != profitShareSetting){
                result.put("status","success");
                result.put("data",profitShareSetting);
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch(Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","获取分润规则",e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

}
