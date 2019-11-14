package cn.com.myproject.user.service.impl;

import cn.com.myproject.user.entity.PO.StudyLabel;
import cn.com.myproject.user.mapper.StudyLabelMapper;
import cn.com.myproject.user.service.IStudyLabelService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JYP on 2017/8/22 0022.
 */
@Service
public class StudyLabelService implements IStudyLabelService {

    @Autowired
    private StudyLabelMapper studyLabelMapper;

    @Override
    public List<StudyLabel> selectAllLable() {
        List<StudyLabel> list = studyLabelMapper.selectAllLable();
        return list;
    }

    @Override
    public String getLabelName(String labelId) {
        return studyLabelMapper.getLabelName(labelId);
    }
}
