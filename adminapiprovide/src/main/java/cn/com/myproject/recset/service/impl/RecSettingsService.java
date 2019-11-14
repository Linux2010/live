package cn.com.myproject.recset.service.impl;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.recset.mapper.RecSettingsMapper;
import cn.com.myproject.recset.service.IRecSettingsService;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by JYP on 2017/8/17 0017.
 */
@Service
public class RecSettingsService implements IRecSettingsService {

    @Autowired
    private RecSettingsMapper recSettingsMapper;

    @Override
    public PageInfo<RecSettings> getTeaPage(int pageNum, int pageSize) {
        List<RecSettings> list = recSettingsMapper.getTeaPage(pageNum, pageSize);
        return new PageInfo(list);
    }

    @Override
    public PageInfo<RecSettings> getCourPage(int pageNum, int pageSize) {
        List<RecSettings> list = recSettingsMapper.getCourPage(pageNum, pageSize);
        return new PageInfo(list);
    }

    @Override
    @Transactional
    public void delcour(String recSetId) {
        recSettingsMapper.delcour(recSetId);
    }

    @Override
    public PageInfo<RecSettings> selectAllCourse(int pageNum, int pageSize) {
        List<RecSettings> list = recSettingsMapper.selectAllCourse(pageNum, pageSize);
        return new PageInfo(list);
    }

    @Override
    @Transactional
    public void insert(RecSettings recSettings) {
        recSettingsMapper.insert(recSettings);
    }

    @Override
    @Transactional
    public void updateSort(RecSettings recSettings) {
        recSettingsMapper.updateSort(recSettings);
    }

    @Override
    public PageInfo<RecSettings> selectAllTea(int pageNum, int pageSize) {
        List<RecSettings> list = recSettingsMapper.selectAllTea(pageNum, pageSize);
        return new PageInfo(list);
    }

    @Override
    public PageInfo<RecSettings> selectGoodCour(int pageNum, int pageSize) {
        List<RecSettings> list = recSettingsMapper.selectGoodCour(pageNum, pageSize);
        return new PageInfo(list);
    }

    @Override
    public PageInfo<RecSettings> goodCourList(int pageNum, int pageSize) {
        List<RecSettings> list = recSettingsMapper.goodCourList(pageNum, pageSize);
        return new PageInfo(list);
    }

    @Override
    public PageInfo<RecSettings> hasRecByTag(int pageNum, int pageSize, String keyword) {
        List<RecSettings> list = recSettingsMapper.hasRecByTag(pageNum, pageSize, keyword);
        return new PageInfo(list);
    }

    @Override
    public PageInfo<RecSettings> notRecByTag(int pageNum, int pageSize, String keyword) {
        List<RecSettings> list = recSettingsMapper.notRecByTag(pageNum, pageSize, keyword);
        return new PageInfo(list);
    }

    @Override
    public List<Course> select_tui_tcour(int pageNum, int pageSize) {
        return recSettingsMapper.select_tui_tcour(pageNum,pageSize);
    }

    @Override
    public List<Course> select_today_gcour(int pageNum, int pageSize) {
        return recSettingsMapper.select_today_gcour(pageNum,pageSize);
    }

    @Override
    public List<Course> select_today_excellentcour(int pageNum, int pageSize) {
        return recSettingsMapper.select_today_excellentcour(pageNum,pageSize);
    }

    @Override
    public List<User> select_recomm_teacher(int pageNum, int pageSize) {
        List<User> users = recSettingsMapper.select_recomm_teacher(pageNum,pageSize);
        if(users !=null && users.size() >0){
            if(StringUtils.isNotEmpty(users.get(0).getRectanglePhoto()))
              users.get(0).setPhoto(users.get(0).getRectanglePhoto());
        }
        return users;
    }

    @Override
    public List<Course> select_today_gcour_by_label(String courseTagId,int pageNum,int pageSize) {
        return recSettingsMapper.select_today_gcour_by_label(courseTagId,pageNum,pageSize);
    }

    @Override
    public RecSettings selectByRecSetId(String recSetId) {

        return recSettingsMapper.selectByRecSetId(recSetId);
    }

    @Override
    public List<Course> select_today_goods(int pageNum, int pageSize) {

        return recSettingsMapper.select_today_goods(pageNum, pageSize);
    }
}
