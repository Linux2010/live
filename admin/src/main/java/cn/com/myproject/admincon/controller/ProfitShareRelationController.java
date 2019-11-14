package cn.com.myproject.admincon.controller;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import cn.com.myproject.service.IUserService;
import cn.com.myproject.service.admincon.IProfitShareRelationService;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
@RequestMapping("/profitshare/relation")
public class ProfitShareRelationController {
    private static final Logger logger = LoggerFactory.getLogger(ProfitShareRelationController.class);

    @Autowired
    private IProfitShareRelationService profitShareRelationService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index() {
        return "admincon/profit_relation_index";
    }

    @RequestMapping("/add")
    public String add() {
       return "sys/user_add";
    }



    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<ProfitShareRelationVO> list(Integer rows, Integer page) {
        PageInfo<ProfitShareRelationVO> info = profitShareRelationService.getPage(page,rows);
        return info;
    }

    @ResponseBody
    @RequestMapping("/str")
    public String list() {
        String info = "[{\"id\":0,\"username\":\"王三1\",\"level\":0,\"address\":\"USA\",\"isLeaf\":false,\"age\":20,\"expanded\":true,\"password\":\"123\"},{\"id\":1,\"username\":\"王三2\",\"level\":1,\"address\":\"USA\",\"isLeaf\":false,\"age\":21,\"parent\":0,\"expanded\":true,\"password\":\"123\"},{\"id\":2,\"username\":\"王三3\",\"level\":2,\"address\":\"USA\",\"isLeaf\":true,\"age\":22,\"parent\":1,\"expanded\":true,\"password\":\"123\"}]";
        return info;
    }

    @ResponseBody
    @RequestMapping("/addRelation")
    public Map<String,Object> addProfitShareSetting(ProfitShareRelation profitShareRelation) {
        Map<String,Object> result = new HashMap();
        try {
            int i = profitShareRelationService.insert(profitShareRelation);
            if(i>0){
                result.put("status","success");
            }else if(-1 == i){
                result.put("status","fail");
                result.put("message","此会员已有推荐人");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","添加分润关系",ex.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateRelation")
    public Map<String,Object> updateUsers(ProfitShareRelation profitShareRelation) {
        Map<String,Object> result = new HashMap<>();
        try {

            int i = profitShareRelationService.updateByPrimaryKeySelective(profitShareRelation);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","修改分润关系",e.getMessage());
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRelation")
    public Map<String,Object> delusers(String relationId) {
        Map<String,Object> result = new HashMap<>();
        try {
            if(StringUtils.isBlank(relationId)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
            int i = profitShareRelationService.deleteByPrimaryKey(relationId);
            if(i>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch (Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","删除分润关系",e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getRelation")
    public Map<String,Object> selectUsers(String setId) {
        Map<String,Object> result = new HashMap<>();
        try{
            if(StringUtils.isBlank(setId)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
           ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(setId);
            if(null != profitShareRelation){
                result.put("status","success");
                result.put("data",profitShareRelation);
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }catch(Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","删除分润关系",e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getRelationPage")
    public PageInfo<ProfitShareRelationVO> getRelationPage(Integer rows, Integer page,String userId,String keyword) {

        PageInfo<ProfitShareRelationVO> info = new PageInfo<>();

        Map<String,Object> map = new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",rows);
        map.put("keyword",keyword);
        if(StringUtils.isNotBlank(userId)){
            ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(userId);
            map.put("userId",userId);
            info = profitShareRelationService.getRelationPage(map);
        }else{
            map.put("userId","-1");
            info = profitShareRelationService.getRelationPage(map);
        }
        return info;

    }
    /**
     * 是否有下级
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/isD")
    public Map<String,Object> isD(String relationId) {
        Map<String,Object> result = new HashMap<>();
        try{
            if(StringUtils.isBlank(relationId)){
                result.put("status","fail");
                result.put("message","参数缺失");
                return result;
            }
            ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(relationId);
            if(null != profitShareRelation){
                List<ProfitShareRelation> profitShareRelationListD = profitShareRelationService.getRelationD(profitShareRelation.getUserId());
                if(null != profitShareRelationListD && profitShareRelationListD.size()>0){
                    result.put("status","fail");
                    result.put("message","该用户有分支会员，不可进行此操作");
                    return result;
                }
            }else{
                result.put("status","fail");
                result.put("message","未查询到相关信息");
                return result;
            }
            result.put("status","success");
        }catch(Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","查询下级关系",e.getMessage());
            result.put("status","fail");
            result.put("message","操作失败");
        }
        return result;
    }


    @RequestMapping("/getRelationByBusiId")
    public ModelAndView getRelationByBusiId(String businessId, String level) {
        ModelAndView result = new ModelAndView();
        try{
            if(StringUtils.isBlank(businessId) || StringUtils.isBlank(level)){
                result = new ModelAndView("admincon/profit_relation_index");
                return result;
            }

            if("1".equals(level)){
                result = new ModelAndView("admincon/profit_relation_index_edit");
            }else if("2".equals(level)){
                result = new ModelAndView("admincon/profit_relation_second_edit");
            }else if("3".equals(level)){
                result = new ModelAndView("admincon/profit_relation_three_edit");
            }

            ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(businessId);
            result.addObject("relationIndex",profitShareRelation);

        }catch(Exception e){
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","后台","获取分润关系",e.getMessage());
        }
        return result;
    }

    @RequestMapping("/pageskip")
    public ModelAndView pageSkip(String relationId,String level) {
        ModelAndView modelAndView;
        if("3".equals(level)){
            modelAndView = new ModelAndView("admincon/profit_relation_three");
        }else if("2".equals(level)){
            modelAndView = new ModelAndView("admincon/profit_relation_second");
        }else{
            modelAndView = new ModelAndView("admincon/profit_relation_index");
        }

        ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(relationId);
        String userId = profitShareRelation.getUserId();
        modelAndView.addObject("userId",userId);
        modelAndView.addObject("level",level);
        modelAndView.addObject("relationId",relationId);

        return modelAndView;
    }

    @RequestMapping("/stairAdd")
    public ModelAndView stairAdd() {
        ModelAndView modelAndView =new ModelAndView("admincon/profit_relation_index_add");
        List<User> userList = userService.getUserList();
        Map<String,Object> map = new HashMap<>();
        map.put("parentUserId","-1");
        List<ProfitShareRelation> profitShareRelationList = profitShareRelationService.getRelationListByMap(map);
        List<User> result = new ArrayList<User>();
        boolean flag = false;
        for(User user:userList){
            flag = false;
            for(ProfitShareRelation relation:profitShareRelationList){
                if(user.getUserId().equals(relation.getUserId())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result.add(user);
            }
        }
        modelAndView.addObject("userList",result);
        return modelAndView;
    }

    @RequestMapping("/secondAdd")
    public ModelAndView secondAdd(String relationId) {

        ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(relationId);
        if(null == profitShareRelation){
            ModelAndView modelAndView =new ModelAndView("admincon/profit_relation_second_index");
            return modelAndView;
        }

        ModelAndView modelAndView =new ModelAndView("admincon/profit_relation_second_add");
        List<User> userList = userService.getUserList();

        //排除掉本类下的子类
        Map<String,Object> map = new HashMap<>();
        map.put("parentUserId",profitShareRelation.getUserId());
        List<ProfitShareRelation> profitShareRelationList = profitShareRelationService.getRelationListByMap(map);

        //排除掉所有有子类的类别
        map = new HashMap<>();
        map.put("parentUserIdNQ","-1");
        List<ProfitShareRelation> profitShareRelationSubList = profitShareRelationService.getRelationListByMap(map);


        List<User> result = new ArrayList<User>();
        boolean flag = false;
        for(User user:userList){
            flag = false;
            for(ProfitShareRelation relation:profitShareRelationList){
                if(user.getUserId().equals(relation.getUserId())){
                    flag = true;
                    break;
                }
            }
            for(ProfitShareRelation relation:profitShareRelationSubList){
                if(user.getUserId().equals(relation.getParentUserId())){
                    flag = true;
                    break;
                }
            }

            for(ProfitShareRelation relation:profitShareRelationSubList){
                if(user.getUserId().equals(relation.getUserId())){
                    flag = true;
                    break;
                }
            }
            if(user.getUserId().equals(relationId)){
                flag = true;
            }
            if(!flag){
                result.add(user);
            }
        }
        modelAndView.addObject("profitShareRelation",profitShareRelation);
        modelAndView.addObject("userList",result);
        return modelAndView;
    }

    @RequestMapping("/threeAdd")
    public ModelAndView threeAdd(String relationId) {

        ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(relationId);
        if(null == profitShareRelation){
            ModelAndView modelAndView =new ModelAndView("admincon/profit_relation_second_index");
            return modelAndView;
        }

        ModelAndView modelAndView =new ModelAndView("admincon/profit_relation_three_add");
        List<User> userList = userService.getUserList();

        //排除掉本类下的子类
        Map<String,Object> map = new HashMap<>();
        map.put("parentUserId",profitShareRelation.getUserId());
        List<ProfitShareRelation> profitShareRelationList = profitShareRelationService.getRelationListByMap(map);

        //排除掉所有有子类的类别
        map = new HashMap<>();
        map.put("parentUserIdNQ","-1");
        List<ProfitShareRelation> profitShareRelationSubList = profitShareRelationService.getRelationListByMap(map);


        List<User> result = new ArrayList<User>();
        boolean flag = false;
        for(User user:userList){
            flag = false;
            for(ProfitShareRelation relation:profitShareRelationList){
                if(user.getUserId().equals(relation.getUserId())){
                    flag = true;
                    break;
                }
            }
            for(ProfitShareRelation relation:profitShareRelationSubList){
                if(user.getUserId().equals(relation.getParentUserId())){
                    flag = true;
                    break;
                }
            }

            for(ProfitShareRelation relation:profitShareRelationSubList){
                if(user.getUserId().equals(relation.getUserId())){
                    flag = true;
                    break;
                }
            }
            if(user.getUserId().equals(relationId)){
                flag = true;
            }
            if(!flag){
                result.add(user);
            }
        }
        modelAndView.addObject("profitShareRelation",profitShareRelation);
        modelAndView.addObject("userList",result);
        return modelAndView;
    }

    /**
     * 修改上级分润关系页面
     * @param relationId
     * @return
     */
    @RequestMapping("/editParentRaltion")
    public ModelAndView editParentRaltion(String relationId, String level) {
        ModelAndView result = new ModelAndView();
        try{
            if(StringUtils.isBlank(relationId) ){
                result = new ModelAndView("admincon/profit_relation_index");
                return result;
            }
            result = new ModelAndView("admincon/profit_relation_edit_parent");

            ProfitShareRelation profitShareRelation = profitShareRelationService.selectByPrimaryKey(relationId);
            result.addObject("relationIndex",profitShareRelation);

            //上级分润用户
//            ProfitShareRelation old_profitShareRelation =  profitShareRelationService.selectByUserId(profitShareRelation.getUserId()); //查询用户分润关系parent不为-1的上下级分润关系
//            if(old_profitShareRelation !=null){
//                result.addObject("old_profitShareRelation",old_profitShareRelation);
//            }

//            List<User> users = userService.getNonsubordinateProfitShareRelationUserList(profitShareRelation.getUserId()); //除去当前用户的所有下级用户的用户列表
//            result.addObject("userList",users);
            result.addObject("level",level);
        }catch(Exception e){
            logger.error("[{查询分润关系}]时，发生异常，异常信息为[{"+e.getMessage()+"}]","后台","获取分润关系");
        }
        return result;
    }
    /**
     * 修改上级分润关系
     * @param profitShareRelation
     * @return
     */
   @RequestMapping("/updateParentRaltion")
    @ResponseBody
    public Map<String,Object> updateParentRaltion(ProfitShareRelation profitShareRelation) {
        Map<String,Object> result = new HashMap<>();
        try {
            if(StringUtils.isBlank(profitShareRelation.getCountryCode()) || StringUtils.isEmpty(profitShareRelation.getCountryCode())||StringUtils.isBlank(profitShareRelation.getCountryCode()) || StringUtils.isEmpty(profitShareRelation.getPhone())){
                result.put("status","fail");
                result.put("message","上级用户名手机信息不能为空");
                return result;
            }
            User parentUser = userService.selectByPhoneNum(profitShareRelation.getCountryCode(),profitShareRelation.getPhone());
            if(parentUser == null){
                result.put("status","fail");
                result.put("message","上级用户不存在");
                return result;
            }
            profitShareRelation.setParentUserId(parentUser.getUserId());

            if(userService.selectById(profitShareRelation.getParentUserId()) == null){
                result.put("status","fail");
                result.put("message","该用户不存在");
                return result;
            }
            int profitShareRelationFlag =  profitShareRelationService.updateParentRaltion(profitShareRelation);
            if(profitShareRelationFlag < 1){
                logger.error("修改上级分润关系 profitShareRelationService.updateParentRaltion(profitShareRelation)");
                throw new RuntimeException("修改上级分润关系 profitShareRelationService.updateParentRaltion(profitShareRelation)");
            }
            if(profitShareRelationFlag>0){
                result.put("status","success");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
                return result;
            }
        }catch (Exception e){
            logger.error("[{修改上级分润关系}]时，发生异常，异常信息为[{"+e.getMessage()+"}]","后台");
            result.put("status","fail");
            result.put("message","修改失败");
        }
        return result;
    }
}
