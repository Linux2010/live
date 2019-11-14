package cn.com.myproject.live.service.impl;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.live.mapper.CourseOrderMapper;
import cn.com.myproject.live.service.ICourseOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * Created by JYP on 2017/8/15 0015.
 */
@Service
public class CourseOrderService implements ICourseOrderService {

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Override
    public PageInfo<CourseOrder> getPageForQuery(int pageNum, int pageSize, Map<String, Object> map) {
        List<CourseOrder> list = courseOrderMapper.getPageForQuery(pageNum, pageSize, map);
        return new PageInfo(list);
    }

    @Override
    public CourseOrder getById(String corderid) {
        return courseOrderMapper.getById(corderid);
    }

    @Transactional
    @Override
    public void addcorder(CourseOrder courseOrder) {
        courseOrder.setCourseOrderId(UUID.randomUUID().toString().replace("-", ""));
        courseOrder.setCourseId(courseOrder.getCourseId());
        courseOrder.setOrderCreateTime(new Date().getTime());
        courseOrder.setTotalMoney(courseOrder.getTotalMoney());
        courseOrder.setOrderStatus(0);//待付款
        courseOrder.setPayStatus(0);//未支付
        courseOrder.setPayType(null);//无支付方式
        courseOrder.setUserId("");//当前登录人
        courseOrderMapper.addcorder(courseOrder);
    }

    @Transactional
    @Override
    public void updatecorder(CourseOrder courseOrder) {
        courseOrder.setOrderStatus(1);//已付款
        courseOrder.setPayStatus(1);//已支付
        courseOrder.setPayType(courseOrder.getPayType());//支付方式
        courseOrderMapper.updatecorder(courseOrder);
    }

    @Override
    public Integer ckeckPurchase(CourseOrder courseOrder) {
        return courseOrderMapper.ckeckPurchase(courseOrder);
    }

    @Override
    public List<Course> select_today_study(int pageNum, int pageSize, String userId) {
        return courseOrderMapper.select_today_study(pageNum,pageSize,userId);
    }


    /**
     * 根据订单编号查询订单金额
     *
     * @param courseOrderNo
     * @return
     */
    @Override
    public CourseOrder searchCoMoneyByCno(String courseOrderNo){
        return courseOrderMapper.searchCoMoneyByCno(courseOrderNo);
    }

}