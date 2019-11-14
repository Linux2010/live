package cn.com.myproject.recset.controller;

import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.service.IRecSettingsService;
import cn.com.myproject.service.IStudyLabelService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by jyp on 2017/8/17 0017.
 */
@Controller
@RequestMapping("/recsetting")
public class RecSettingsController {

    public static final Logger logger = LoggerFactory.getLogger(RecSettingsController.class);

    @Autowired
    private IRecSettingsService recSettingsService;

    @Autowired
    private IStudyLabelService studyLabelService;

    @RequestMapping("/")
    public String index() {
        return "recset/tea_list";
    }

    @RequestMapping("/cour")
    public String index1() {
        return "recset/cour_list";
    }

    @RequestMapping("/all_rec")
    public String all_rec() {
        return "recset/cour_all_list";
    }

    @RequestMapping("/all_tea")
    public String all_tea() {
        return "recset/tea_all_list";
    }

    @RequestMapping("/exceCourRec/")
    public String excerec() {
        return "recset/good_cour_list";
    }

    @RequestMapping("/exceCourRec/list")
    public String excerec_list() {
        return "recset/good_cour_all_list";
    }

    @RequestMapping("/todayCourRec/")
    public String today_cour(HttpServletRequest request) {
        request.getSession().setAttribute("study_tag", studyLabelService.selectAllLable());
        request.getSession().setAttribute("return_keyword", "");
        return "recset/today_cour_list";
    }

    @RequestMapping("/return_list")
    public ModelAndView return_list(String return_keyword) {
        ModelAndView model = new ModelAndView("recset/today_cour_list");
        model.addObject("return_keyword", return_keyword);
        return model;
    }

    @RequestMapping("/todayCourRec/list")
    public ModelAndView today_cour_list(String pass_keyword) {
        ModelAndView view = new ModelAndView("recset/today_cour_all_list");
        view.addObject("pass_keyword", pass_keyword);
        return view;
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<RecSettings> getTeaPage(int rows, int page) {
        PageInfo<RecSettings> list = recSettingsService.getTeaPage(page, rows);
        return list;
    }

    @ResponseBody
    @RequestMapping("/list1")
    public PageInfo<RecSettings> getCourPage(int rows, int page) {
        return recSettingsService.getCourPage(page, rows);
    }

    @ResponseBody
    @RequestMapping("/delcour")
    public Map<String, Object> delcour(@RequestParam("recSetId") String recSetId) {

        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isBlank(recSetId)) {
                data.put("status", "faile");
                data.put("message", "编号不能为空！");
                return data;
            }
            if (recSettingsService.delcour(recSetId) != 1) {
                data.put("status", "faile");
                data.put("message", "删除失败！");
                return data;
            }
            data.put("status", "success");
            data.put("message", "删除成功");
        } catch (Exception ex) {
            data.put("status", "faile");
            data.put("message", ex.getMessage());
        }
        return data;
    }

    @RequestMapping("/selectByRecSetId")
    @ResponseBody
    public RecSettings selectByRecSetId(String recSetId) {

        return recSettingsService.selectByRecSetId(recSetId);
    }

    @ResponseBody
    @RequestMapping("/alllist")
    public PageInfo<RecSettings> selectAllCour(int rows, int page) {
        return recSettingsService.selectAllCour(page, rows);
    }

    /**
     * 修改序列值
     *
     * @param recSetId
     * @param recSort
     * @return
     */
    @ResponseBody
    @RequestMapping("/usort")
    public Map<String, Object> updateSort(@RequestParam("recSetId") String recSetId, @RequestParam("recSort") String recSort, @RequestParam("num") int num) {
        Map<String, Object> data = new HashMap<>();
        RecSettings recSettings = new RecSettings();
        try {
            if (StringUtils.isNotBlank(recSetId)) {
                if (num == 1) {
                    recSettings.setRecSort(Integer.parseInt(recSort));
                    recSettings.setRecSetId(recSetId);
                    recSettings.setRecLabType(1);//1代表教头
                } else if (num == 2) {
                    recSettings.setRecSort(Integer.parseInt(recSort));
                    recSettings.setRecSetId(recSetId);
                    recSettings.setRecLabType(2);//2代表今日推荐里的课程
                } else if (num == 5) {
                    recSettings.setRecSort(Integer.parseInt(recSort));
                    recSettings.setRecSetId(recSetId);
                    recSettings.setRecLabType(5);//优质课程
                } else if (num == 6) {
                    recSettings.setRecSort(Integer.parseInt(recSort));
                    recSettings.setRecSetId(recSetId);
                    recSettings.setRecLabType(6);//今日推荐课程
                }
            }
            recSettingsService.updateSort(recSettings);
            data.put("status", "success");
            data.put("message", "修改成功");
        } catch (Exception ex) {
            data.put("status", "faile");
            data.put("message", ex.getMessage());
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/tealist")
    public PageInfo selectAllTea(int rows, int page) {
        return recSettingsService.selectAllTea(page, rows);
    }

    /**
     * 推荐
     *
     * @param objid
     * @return
     */
    @ResponseBody
    @RequestMapping("/recinsert")
    public Map<String, Object> teainsert(@RequestParam("objid") List<String> objid, @RequestParam("mark") int mark) {
        Map<String, Object> data = new HashMap<>();
        try {
            for (String id : objid) {
                RecSettings recSettings = new RecSettings();
                recSettings.setRecSetObjId(id);
                if (mark == 1) {
                    recSettings.setRecLabType(1);
                    recSettings.setRecLabId("todayrec");//今日推荐里的推荐教头
                } else if (mark == 2) {
                    recSettings.setRecLabType(2);
                    recSettings.setRecLabId("todayrec");//今日推荐里的课程推荐
                } else if (mark == 5) {
                    recSettings.setRecLabType(5);
                    recSettings.setRecLabId("ExcellentCourseRec");//优质课程的推荐
                } else if (mark == 6) {
                    recSettings.setRecLabType(6);
                    recSettings.setRecLabId("TodayCourse");//今日课程推荐的推荐
                }
                recSettings.setRecSetId(UUID.randomUUID().toString().replace("-", ""));
                recSettings.setRecSort(0);
                recSettings.setCreateTime(new Date().getTime());
                recSettings.setStatus((short) 1);
                recSettings.setVersion(1);
                recSettingsService.insert(recSettings);
                data.put("status", "success");
                data.put("message", "设置成功");
            }
        } catch (Exception ex) {
            data.put("status", "faile");
            data.put("message", ex.getMessage());
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/goodcourlist")
    public PageInfo<RecSettings> selectGoodCour(int rows, int page) {
        return recSettingsService.selectGoodCour(page, rows);
    }

    @ResponseBody
    @RequestMapping("/allgoodcourlist")
    public PageInfo<RecSettings> goodCourList(int rows, int page) {
        return recSettingsService.goodCourList(page, rows);
    }

    @ResponseBody
    @RequestMapping("/hasRecByTag")
    public PageInfo<RecSettings> hasRecByTag(int rows, int page, HttpServletRequest request) throws Exception {
        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
        }
        return recSettingsService.hasRecByTag(page, rows, keyword);
    }

    @ResponseBody
    @RequestMapping("/notRecByTag")
    public PageInfo<RecSettings> notRecByTag(int rows, int page, HttpServletRequest request) throws Exception {
        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotBlank(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
        }
        return recSettingsService.notRecByTag(page, rows, keyword);
    }

}
