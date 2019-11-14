package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.StudyLabel;
import cn.com.myproject.user.service.IStudyLabelService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JYP on 2017/8/22 0022.
 */
@RestController
@RequestMapping("/stu_label")
public class StudyLabelController {

    @Autowired
    private IStudyLabelService studyLabelService;

    @GetMapping("/all_label")
    public List<StudyLabel> selectAllLable() {
        return studyLabelService.selectAllLable();
    }

    @PostMapping("/getLabelName")
    public String getLabelName(String labelId){
        return  studyLabelService.getLabelName(labelId);
    }
}
