package cn.com.myproject.external;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.recset.service.IRecSettingsService;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JYP on 2017/8/17 0017.
 */
@RestController
@RequestMapping("/recsetting")
public class RecSettingsController {

    @Autowired
    private IRecSettingsService recSettingsService;

    @GetMapping("/gettea")
    public PageInfo<RecSettings> getTeaPage(int pageNum, int pageSize) {
        return recSettingsService.getTeaPage(pageNum, pageSize);
    }

    @GetMapping("/getcour")
    public PageInfo<RecSettings> getCourPage(int pageNum, int pageSize) {
        return recSettingsService.getCourPage(pageNum, pageSize);
    }

    @PostMapping("/delcour")
    public int delcour(String recSetId) {

        int result = 0;
        try {
            recSettingsService.delcour(recSetId);
            result = 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectByRecSetId")
    public RecSettings selectByRecSetId(String recSetId) {

        return recSettingsService.selectByRecSetId(recSetId);
    }

    @GetMapping("allCour")
    public PageInfo<RecSettings> selectAllCour(int pageNum, int pageSize) {
        return recSettingsService.selectAllCourse(pageNum, pageSize);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody RecSettings recSettings) {
        int info = 0;
        try {
            recSettingsService.insert(recSettings);
            info = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }

    @PostMapping("/updateSort")
    public int updateSort(@RequestBody RecSettings recSettings) {
        int info = 0;
        try {
            recSettingsService.updateSort(recSettings);
            info = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }

    @GetMapping("/allTea")
    public PageInfo<RecSettings> selectAllTea(int pageNum, int pageSize) {
        return recSettingsService.selectAllTea(pageNum, pageSize);
    }

    @GetMapping("/selectGoodCour")
    public PageInfo<RecSettings> selectGoodCour(int pageNum, int pageSize) {
        return recSettingsService.selectGoodCour(pageNum, pageSize);
    }

    @GetMapping("/goodCourList")
    public PageInfo<RecSettings> GoodCourList(int pageNum, int pageSize) {
        return recSettingsService.goodCourList(pageNum, pageSize);
    }

    @PostMapping("/hasRecByTag")
    public PageInfo<RecSettings> hasRecByTag(int pageNum, int pageSize, String keyword) {
        return recSettingsService.hasRecByTag(pageNum, pageSize, keyword);
    }

    @PostMapping("/notRecByTag")
    public PageInfo<RecSettings> notRecByTag(int pageNum, int pageSize, String keyword) {
        return recSettingsService.notRecByTag(pageNum, pageSize, keyword);
    }

    @PostMapping("/select_tui_tcour")
    public List<Course> select_today_tcour(int pageNum, int pageSize) {
        return recSettingsService.select_tui_tcour(pageNum, pageSize);
    }

    @PostMapping("/select_today_gcour")
    public List<Course> select_today_gcour(int pageNum, int pageSize) {
        return recSettingsService.select_today_gcour(pageNum, pageSize);
    }

    @PostMapping("/select_today_excellentcour")
    public List<Course> select_today_excellentcour(int pageNum, int pageSize) {
        return recSettingsService.select_today_excellentcour(pageNum, pageSize);
    }

    @PostMapping("/select_today_goods")
    public List<Course> select_today_goods(int pageNum, int pageSize){

        return recSettingsService.select_today_goods(pageNum, pageSize);
    }

    @PostMapping("/select_recomm_teacher")
    public List<User> select_recomm_teacher(int pageNum, int pageSize) {
        return recSettingsService.select_recomm_teacher(pageNum, pageSize);
    }

    @PostMapping("/select_today_gcour_by_label")
    public List<Course> select_today_gcour_by_label(String courseTagId,int pageNum, int pageSize) {
        return recSettingsService.select_today_gcour_by_label(courseTagId,pageNum, pageSize);
    }
}
