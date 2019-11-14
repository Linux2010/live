package cn.com.myproject.user.controller;

import cn.com.myproject.service.IConfigSettingService;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.IUserService;
import cn.com.myproject.user.entity.PO.ConfigSetting;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.util.ChartGraphics;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

@Controller
@RequestMapping("/configSetting")
public class ConfigSettingController {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ArticleController.class);

    @Autowired
    private IConfigSettingService configSettingService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadImgService uploadImgService;

    @RequestMapping("/")
    public String index(){

        return "configSetting/configSetting_index";
    }

    @RequestMapping("/page")
    @ResponseBody
    public PageInfo<ConfigSetting> getPage(int rows, int page, HttpServletRequest request) throws Exception{

        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotBlank(keyword)){
             keyword= URLDecoder.decode(keyword, "utf-8");
        }else {
            keyword = null;
        }
        PageInfo<ConfigSetting> list = configSettingService.getPage(page, rows ,keyword);
        return list;
    }

    //设置的全部内容
    @RequestMapping("/list")
    @ResponseBody
    public List<ConfigSetting> selectConfigSettings(){

        return configSettingService.selectConfigSettings();
    }

    //添加设置内容
    @RequestMapping("/addConfigSetting")
    @ResponseBody
    public Map<String, Object> addConfigSetting(ConfigSetting configSetting){

        Map<String, Object> data = new HashMap<>();
        try {
            if (configSettingService.addConfigSetting(configSetting) != 1){
                data.put("status", "faile");
                data.put("message", "添加失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message","添加成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message","添加失败！");
            return data;
        }
        return data;
    }

    //删除设置内容
    @RequestMapping("/delConfigSettingById")
    @ResponseBody
    public Map<String, Object> delConfigSettingById(String configId){

        Map<String, Object> data = new HashMap<>();
        try {
           if (StringUtils.isBlank(configId)){
               data.put("status", "faile");
               data.put("message", "编号id不能为空！");
               return data;
           }
            ConfigSetting configSetting = configSettingService.selectConfigSettingById(configId);
            if (null == configSetting){
                data.put("status", "faile");
                data.put("message", "没有该设置！");
                return data;
            }
            if (configSettingService.delConfigSettingById(configId) != 1){
                data.put("status", "faile");
                data.put("message", "删除失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "删除成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "删除失败！");
            return data;
        }
        return data;
    }


    //根据id查询设置
    @RequestMapping("/selectConfigSettingById")
    @ResponseBody
    public ConfigSetting selectConfigSettingById(String configId){
        ConfigSetting configSetting = configSettingService.selectConfigSettingById(configId);
        return configSetting;
    }


    //修改设置
    @RequestMapping("/updateConfigSetting")
    @ResponseBody
    public Map<String, Object> updateConfigSetting(ConfigSetting configSetting, HttpServletRequest request){

        Map<String, Object> data = new HashMap<>();
        try {
            String configId = request.getParameter("config_id");
            ConfigSetting config = configSettingService.selectConfigSettingById(configId);
            if (null == config){
                data.put("status", "faile");
                data.put("message", "没有该设置！");
                return data;
            }
            config.setConfig_value(configSetting.getConfig_value());
            config.setConfig_name(configSetting.getConfig_name());
            config.setRemark(configSetting.getRemark());
            if (configSettingService.updateConfigSetting(config) != 1){
                data.put("status", "faile");
                data.put("message", "修改失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "修改成功！");
        }catch (RuntimeException e){
            data.put("status", "faile");
            data.put("message", "修改失败！");
            return data;
        }
        return data;
    }

    /**
     * 二维码生成器页面
     * @return
     */
    @RequestMapping("/qrCode")
    public ModelAndView  qrCode(){
        ModelAndView view = new ModelAndView("/configSetting/configSetting_qrCode_index");
      //  List<User> userList = userService.getUserList(); //查询只有二维码的用户
        ConfigSetting configSetting0 =  configSettingService.selectConfigSettingBySign("share0");
        ConfigSetting configSetting1 =  configSettingService.selectConfigSettingBySign("share1");
        ConfigSetting configSetting2 =  configSettingService.selectConfigSettingBySign("share2");
        view.addObject("share0",configSetting0.getConfig_value());
        view.addObject("share1",configSetting1.getConfig_value());
        view.addObject("share2",configSetting2.getConfig_value());
     //   view.addObject("userList",userList);
        return view;

    }

    @ResponseBody
    @RequestMapping("/getTweetyUserList")
    public List<User> getTweetyUserList(int pageNum,int pageSize){
        List<User> userList =  userService.getTweetyUserList(pageNum,pageSize);
        return userList;
    }


    /**
     * 二维码生成器，合成邀请好友海报二维码图片
     * @param share0 海报图片
     * @param share1 海报图片
     * @param share2 海报图片
     * @param countryCode 手机区号
     * @param phone 手机号
     * @return
     */
    @RequestMapping("batchQrCode")
    @ResponseBody
    public Map<String,Object>  batchQrCode(String share0,String share1,String share2,String countryCode,String phone){
        Map<String,Object> result = new HashMap<>();
        Long start = System.currentTimeMillis();
        logger.info("admin 合成邀请好友海报二维码图片开始时间："+start);
        if(StringUtils.isBlank(countryCode) || StringUtils.isEmpty(countryCode)||StringUtils.isBlank(phone) || StringUtils.isEmpty(phone)){
            result.put("status","fail");
            result.put("message","用户名手机信息不能为空");
            return result;
        }
        String qrCodeImgurl ="";
        String urlVal0="";
        String urlVal1="";
        String urlVal2="";
        User user = userService.selectByPhoneNum(countryCode,phone);
        if(user == null){
            result.put("status","fail");
            result.put("message","用户不存在");
            return result;
        }
        try {
            //第一张海报
            if(StringUtils.isNotEmpty(share0)){
                urlVal0 =userService.createImg(user,share0,1);
                if(StringUtils.isNotBlank(urlVal0)){
                    qrCodeImgurl +=urlVal0+",";
                }
            }
            //第二张海报
            if(StringUtils.isNotEmpty(share1)){
                urlVal1 =userService.createImg(user,share1,2);
                if(StringUtils.isNotBlank(urlVal1)){
                    qrCodeImgurl +=urlVal1+",";
                }

            }

            //第三张海报
            if(StringUtils.isNotEmpty(share2)){
                urlVal2 = userService.createImg(user,share2,3);
                if(StringUtils.isNotBlank(urlVal2)){
                    qrCodeImgurl +=urlVal2+",";
                }

            }

            //记录合成图片
            if(StringUtils.isNotBlank(qrCodeImgurl)){
                user.setQrCodeImgUrl(qrCodeImgurl);
                int updateQrCode =  userService.updateQrCodeImg(user); //记录合成的海报分享二维码图片

                if(updateQrCode>0){
                    result.put("status","success");
                }else{
                    logger.error("记录合成的海报分享二维码图片失败  userService.updateQrCodeImg(user)");
                    result.put("status","fail");
                    result.put("message","操作失败");
                    return result;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[{记录合成的海报分享二维码图片失败}]时，发生异常，异常信息为[{"+e.getMessage()+"}]");
            result.put("status","fail");
            result.put("message","修改失败");
        }

        Long end = System.currentTimeMillis();
        logger.info("admin 合成邀请好友海报二维码图片结束时间："+end + "，合计用时 end-start" + (end-start) +" 毫秒");
        return result;

    }

    /**
     * 合成图片
     * @param user
     * @param shareImg
     * @return
     */
    public String  createImg(User user,String shareImg,int imgNumber){
        String urlVal ="";
        try{

            ChartGraphics cg = new ChartGraphics();
            String url = user.getQrCodeUrl();//二维码图片
            String createImageUrl = this.getClass().getResource("/static").getPath()+"/temp/";
            if(!new File(createImageUrl).exists()){
                new File(createImageUrl).mkdirs();
            }
            String tempPng =UUID.randomUUID().toString().replace("-", "")+".png";
            createImageUrl = createImageUrl+tempPng;
            logger.info("name======"+user.getUserName());
            cg.graphicsGeneration(shareImg,url,user.getUserName(),createImageUrl,imgNumber);//合成海报与用户分享二维码码为一个图片
            //上传到网易云服务器上
            File  imageFile = new File(createImageUrl);
            FileInputStream input = new FileInputStream(imageFile);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    imageFile.getName(), MediaType.IMAGE_PNG_VALUE, IOUtils.toByteArray(input));
            urlVal = uploadImgService.uploadImg(multipartFile,"qrCodeImg");
            if(StringUtils.isNotEmpty(urlVal)){
                imageFile.delete();
            }
        }catch (Exception e){
            logger.error("合成的海报分享二维码图片失败"+e.getMessage());
            e.printStackTrace();
        }
        return urlVal;
    }


    /**
     * 批量合成系统用户二维码
     * @return
     */
    @RequestMapping("batchAllUserQrCodeImg")
    @ResponseBody
    public ModelAndView batchAllUserQrCodeImg(){
        ModelAndView view = new ModelAndView("/configSetting/batchUsersQrCodeImg");
        boolean flag = userService.batchAllUserQrCodeImg();
        if(flag)
            view.addObject("result","success");
        else
            view.addObject("result","false");
        return view;
    }
    /**
     * 批量合成系统用户二维码
     * @return
     */
    @RequestMapping("batchAllUserQrCodes")
    @ResponseBody
    public int batchAllUserQrCodes(){
        try{
            userService.batchAllUserQrCodeImg();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("[{批量合成用户二维码}]时，发生异常，异常信息为[{"+e.getMessage()+"}]");
            return -1;
        }
        return 1;

    }

    /**
     * 批量合成系统用户海报分享二维码
     * @return
     */
    @RequestMapping("batchAllUserShareQrCodeImgs")
    @ResponseBody
    public int  batchAllUserShareQrCodeImgs(String share0,String share1,String share2){
        Long start = System.currentTimeMillis();
        logger.info("admin 批量合成系统用户海报分享二维码开始时间："+start);

        ConfigSetting configSetting0 =  configSettingService.selectConfigSettingBySign("share0");
        ConfigSetting configSetting1 =  configSettingService.selectConfigSettingBySign("share1");
        ConfigSetting configSetting2 =  configSettingService.selectConfigSettingBySign("share2");
        share0 = configSetting0.getConfig_value();
        share1 = configSetting1.getConfig_value();
        share2 = configSetting2.getConfig_value();
        List<User> users = userService.getUserList();
        for(User user : users){
            String qrCodeImgurl ="";
            String urlVal0="";
            String urlVal1="";
            String urlVal2="";
            try {
                //第一张海报
                if(StringUtils.isNotEmpty(share0)){
                    urlVal0 =userService.createImg(user,share0,1);
                    if(StringUtils.isNotBlank(urlVal0)){
                        qrCodeImgurl +=urlVal0+",";
                    }
                }
                //第二张海报
                if(StringUtils.isNotEmpty(share1)){
                    urlVal1 =userService.createImg(user,share1,2);
                    if(StringUtils.isNotBlank(urlVal1)){
                        qrCodeImgurl +=urlVal1+",";
                    }

                }

                //第三张海报
                if(StringUtils.isNotEmpty(share2)){
                    urlVal2 = userService.createImg(user,share2,3);
                    if(StringUtils.isNotBlank(urlVal2)){
                        qrCodeImgurl +=urlVal2+",";
                    }

                }

                //记录合成图片
                if(StringUtils.isNotBlank(qrCodeImgurl)){
                    user.setQrCodeImgUrl(qrCodeImgurl);
                    userService.updateQrCodeImg(user); //记录合成的海报分享二维码图片
                }

                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("[{批量合成用户海报分享二维码失败}]时，发生异常，异常信息为[{"+e.getMessage()+"}]");
                }
        }

        Long end = System.currentTimeMillis();
        logger.info("admin 批量合成系统用户海报分享二维码结束时间："+end + "，合计用时 end-start" + (end-start) +" 毫秒");
        return 1;
    }
}






























