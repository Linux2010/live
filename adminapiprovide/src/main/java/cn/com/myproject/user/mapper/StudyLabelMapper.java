package cn.com.myproject.user.mapper;

import cn.com.myproject.user.entity.PO.StudyLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JYP on 2017/8/22 0022.
 */
@Mapper
public interface StudyLabelMapper {

    List<StudyLabel> selectAllLable();

    String getLabelName(@Param("labelId") String labelId);
}
