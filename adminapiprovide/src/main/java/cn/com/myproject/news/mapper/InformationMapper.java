package cn.com.myproject.news.mapper;

import cn.com.myproject.user.entity.PO.Information;
import cn.com.myproject.user.entity.PO.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/18.
 */
@Mapper
public interface InformationMapper {
    void  addInformation(@RequestBody Information information );
    void  delInformation(String informationId);
    Information selectById(@Param("informationId") String informationId);
     Information selectByInformationId(@Param("informationId") String informationId,@Param("userId") String userId);
     int searchInfoCountById(@Param("informationId") String informationId);

     List<Information> getAll();
     void  addFabulous(@RequestParam Information information);

     void updateInformationAgree(Information information);

     Information selectByUserId(String userId);

    List<Information> searchInfoList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("title") String title);

    Information selectByInfoId(@Param("informationId") String informationId);

    void  updateAgreeNum(Information information);

    void updateContent(Information information);
    void updateCommentNum(Information information);


    void updateTitle(Information information);

}
