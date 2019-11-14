package cn.com.myproject.user.controller;

import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import cn.com.myproject.service.IUserGroupCardService;
import cn.com.myproject.util.ExcelUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡后台控制器类
 */
@Controller
@RequestMapping("/userGroupCard")
public class UserGroupCardController {

    @Autowired
    private IUserGroupCardService iUserGroupCardService;

    /**
     * 展示用户团购卡列表页面
     *
     * @return
     */
    @RequestMapping("/showUgcListPage")
    public ModelAndView showUgcListPage(){
        ModelAndView view = new ModelAndView("/buiness/user_group_card_index");
        return view;
    }

    /**
     * 展示用户团购卡页面
     *
     * @param userId
     * @param userName
     * @return
     */
    @RequestMapping("/showUgcPage")
    public ModelAndView showUgcPage(String userId,String userName){
        ModelAndView view = new ModelAndView("/buiness/user_group_card");
        view.addObject("userId",userId);
        view.addObject("userName",userName);
        return view;
    }

    /**
     * 查询用户团购卡列表数据
     *
     * @param page
     * @param rows
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @return
     */
    @RequestMapping("/searchUgcList")
    @ResponseBody
    public PageInfo<UserGroupCardVO> searchUgcList(int page, int rows, String userId, String cardNo, String cardType, String status){
        // 转化参数
        int cardTypeVal = 0;
        if(StringUtils.isNotBlank(cardType)){
            cardTypeVal = Integer.parseInt(cardType);
        }
        int statusVal = -1;
        if(StringUtils.isNotBlank(status)){
            statusVal = Integer.parseInt(status);
        }
        return iUserGroupCardService.searchUgcList(page,rows,userId,cardNo,cardTypeVal,statusVal);
    }

    /**
     * 生成用户团购卡
     *
     * @param cardNum
     * @param cardType
     * @return
     */
    @RequestMapping("/addUgc")
    @ResponseBody
    public String addUgc(int cardNum,int cardType,String userId){
        String resultStr = "0";
        if(!iUserGroupCardService.addUgc(cardNum,cardType,userId)){
            resultStr = "1";
        }
        return resultStr;
    }

    /**
     * 根据ID删除用户团购卡
     *
     * @param id
     * @return
     */
    @RequestMapping("/removeUgcById")
    @ResponseBody
    public String removeUgcById(int id){
        String resultStr = "0";
        if(!iUserGroupCardService.removeUgcById(id)){
            resultStr = "1";
        }
        return resultStr;
    }

    /**
     * 导出用户团购卡excel列表
     *
     * @param response
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @throws Exception
     */
    @RequestMapping(value = "exportUgcList")
    public void exportUgcList(HttpServletResponse response, String userId, String cardNo, String cardType, String status) throws Exception {

        // 转化参数
        int cardTypeVal = 0;
        if(StringUtils.isNotBlank(cardType)){
            cardTypeVal = Integer.parseInt(cardType);
        }
        int statusVal = -1;
        if(StringUtils.isNotBlank(status)){
            statusVal = Integer.parseInt(status);
        }

        // 生成导出excel名称
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = "团购卡"+sdf.format(date);

        // 设置导出excel标题
        String[] titles = {"编号","用户名","手机号","卡号","密码","团购卡类型","激活状态","激活者用户名","激活者手机号","激活时间"};

        // 遍历导出excel的内容
        List<UserGroupCardVO> listsChild = iUserGroupCardService.searchExportUgcList(userId,cardNo,cardTypeVal,statusVal);
        List<Map<Integer, String>> lists = new ArrayList<Map<Integer,String>>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserGroupCardVO userGroupCardVO = null;
        for (int i = 0; i < listsChild.size(); i++) {
            userGroupCardVO =  listsChild.get(i);
            if(userGroupCardVO != null){
                Map<Integer, String> paramsLists = new HashMap<Integer, String>();
                String bhStr = "";
                if(userGroupCardVO.getCardNo() != null){
                    bhStr = userGroupCardVO.getCardNo().substring(2);
                }
                paramsLists.put(0, bhStr);
                paramsLists.put(1, userGroupCardVO.getUserName());
                paramsLists.put(2, userGroupCardVO.getPhone());
                paramsLists.put(3, userGroupCardVO.getCardNo());
                paramsLists.put(4, userGroupCardVO.getCardPassword());
                String cardTypeStr = "";
                if(userGroupCardVO.getCardType() == 1){
                    cardTypeStr = "年卡";
                }
                if(userGroupCardVO.getCardType() == 2){
                    cardTypeStr = "月卡";
                }
                paramsLists.put(5, cardTypeStr);
                String statusStr = "";
                if(userGroupCardVO.getStatus() == 0){
                    statusStr = "未激活";
                }
                if(userGroupCardVO.getStatus() == 1){
                    statusStr = "已激活";
                }
                paramsLists.put(6, statusStr);
                paramsLists.put(7, userGroupCardVO.getActiveUserName());
                paramsLists.put(8, userGroupCardVO.getActivePhone());
                String activeTimeStr = "";
                if(userGroupCardVO.getActiveTime() != 0){
                    activeTimeStr = dateFormat.format(userGroupCardVO.getActiveTime());
                }
                paramsLists.put(9, activeTimeStr);
                lists.add(paramsLists);
            }
        }

        // 把导出的内容写入到流里面
        byte[] content = ExcelUtil.writeExcel(titles, lists);
        InputStream is = new ByteArrayInputStream(content);

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

}