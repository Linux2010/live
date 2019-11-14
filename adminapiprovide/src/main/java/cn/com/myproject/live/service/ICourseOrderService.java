package cn.com.myproject.live.service;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by jyp on 2017/8/15 0015.
 */
@Service
public interface ICourseOrderService {
    PageInfo<CourseOrder> getPageForQuery(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("map") Map<String ,Object> map);

    CourseOrder getById(String corderid);

    void addcorder(CourseOrder courseOrder);

    void updatecorder(CourseOrder courseOrder);

    Integer ckeckPurchase(CourseOrder courseOrder);

    List<Course> select_today_study(int pageNum,int pageSize,String userId);

    /**
     * 根据订单编号查询订单金额
     *
     * @param courseOrderNo
     * @return
     */
    CourseOrder searchCoMoneyByCno(String courseOrderNo);

}