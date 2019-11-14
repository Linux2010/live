package cn.com.myproject.sysuser.dao;

import cn.com.myproject.sysuser.entity.PO.SysUser;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/12.
 */
public interface BatchDemoDao {
    void saveBatch(List<SysUser> list);
}
