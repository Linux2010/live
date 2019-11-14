package cn.com.myproject.user.service;

import cn.com.myproject.user.entity.PO.StudyLabel;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JYP on 2017/8/22 0022.
 */
public interface IStudyLabelService {

    List<StudyLabel> selectAllLable();

    String getLabelName(@Param("labelId") String labelId);
}
