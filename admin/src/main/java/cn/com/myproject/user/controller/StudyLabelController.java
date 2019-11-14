package cn.com.myproject.user.controller;

import cn.com.myproject.service.IStudyLabelService;
import cn.com.myproject.user.entity.PO.StudyLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by JYP on 2017/8/22 0022.
 */
@Controller
@RequestMapping("/stu_label")
public class StudyLabelController {

    private static final Logger logger = LoggerFactory.getLogger(StudyLabelController.class);

    @Autowired
    private IStudyLabelService studyLabelService;

    @ResponseBody
    @RequestMapping("/list")
    public List<StudyLabel> selectAllLable(){
        return studyLabelService.selectAllLable();
    }

}
