package cn.com.myproject.recset.mapper;

import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.user.entity.PO.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jyp on 2017/8/17 0017.
 */
@Mapper
public interface RecSettingsMapper {
    //查询已经推荐的教头
    List<RecSettings> getTeaPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //查询已经推荐的课程
    List<RecSettings> getCourPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    RecSettings selectByRecSetId(String recSetId);

    //删除推荐的
    void delcour(String recSetId);

    //查询所有的没有推荐的课程
    List<RecSettings> selectAllCourse(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //推荐的添加
    void insert (RecSettings recSettings);

    //修改序列值
    void updateSort(RecSettings recSettings);

    //所有的未推荐的教头
    List<RecSettings> selectAllTea(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //查询推荐的优质课程
    List<RecSettings> selectGoodCour(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //设置优质课程的列表
    List<RecSettings> goodCourList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //根据标签查询已经推荐的
    List<RecSettings> hasRecByTag(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("keyword") String keyword);

    //根据标签查询未推荐的
    List<RecSettings> notRecByTag(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("keyword") String keyword);

    //api展示t推荐优质课程
    List<Course> select_tui_tcour(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //api展示今日今日推荐课程select_today_excellentcour
    List<Course> select_today_gcour(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);

    //api展示今日优质课程
    List<Course> select_today_excellentcour(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);

    //api展示今日优质商品
    List<Course> select_today_goods(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);

    //api展示今日优质商品
    List<User> select_recomm_teacher(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    //根据用户标签推送
    List<Course> select_today_gcour_by_label(@Param("courseTagId") String courseTagId,@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

}
