package cn.com.myproject.recset.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JYP on 2017/8/17 0017.
 */
public interface IRecSettingsService {

    PageInfo<RecSettings> getTeaPage(int pageNum, int pageSize);

    PageInfo<RecSettings> getCourPage(int pageNum, int pageSize);

    RecSettings selectByRecSetId(String recSetId);

    void delcour(String recSetId);

    PageInfo<RecSettings> selectAllCourse(int pageNum, int pageSize);

    void insert(RecSettings recSettings);

    void updateSort(RecSettings recSettings);

    PageInfo<RecSettings> selectAllTea(int pageNum, int pageSize);

    PageInfo<RecSettings> selectGoodCour(int pageNum, int pageSize);

    PageInfo<RecSettings> goodCourList(int pageNum, int pageSize);

    PageInfo<RecSettings> hasRecByTag(int pageNum, int pageSize, String keyword);

    PageInfo<RecSettings> notRecByTag(int pageNum, int pageSize, String keyword);

    List<Course> select_tui_tcour(int pageNum, int pageSize);

    List<Course> select_today_gcour(int pageNum,int pageSize);

    List<Course> select_today_excellentcour(int pageNum,int pageSize);

    List<Course> select_today_goods(int pageNum, int pageSize);

    List<User> select_recomm_teacher(int pageNum, int pageSize);

    List<Course> select_today_gcour_by_label(String courseTagId,int pageNum, int pageSize);
}
