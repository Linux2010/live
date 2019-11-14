package cn.com.myproject.external;

import cn.com.myproject.course.service.Impl.CourseFinishRecordService;
import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@RestController
@RequestMapping("/courseFinishRecord")
public class CourseFinishRecordController {

    @Autowired
    private CourseFinishRecordService courceFinishRecordService;

    @GetMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String cfrId){
        return courceFinishRecordService.deleteByPrimaryKey(cfrId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody CourseFinishRecord record){
        record.setCfrId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        return courceFinishRecordService.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody CourseFinishRecord record){
        record.setCfrId(UUID.randomUUID().toString().replace("-",""));
        record.setCreateTime(new Date().getTime());
        return courceFinishRecordService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    public CourseFinishRecord selectByPrimaryKey(String cfrId){
        return courceFinishRecordService.selectByPrimaryKey(cfrId);
    }

    @GetMapping("/selectByCourseId")
    public CourseFinishRecord selectByCourseId(String courseId){
        return courceFinishRecordService.selectByCourseId(courseId);
    }

    @GetMapping("/selectByUserId")
    public List<CourseFinishRecord> selectByUserId(String userId){
        return courceFinishRecordService.selectByUserId(userId);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody CourseFinishRecord record){
        return courceFinishRecordService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody CourseFinishRecord record){
        return courceFinishRecordService.updateByPrimaryKey(record);
    }

    @GetMapping("/getFinishNum")
    public int getFinishNum(String userId){
        return courceFinishRecordService.getFinishNum(userId);
    }

    @GetMapping("/getFinishTeacherNum")
    public int getFinishTeacherNum(String userId){
        return courceFinishRecordService.getFinishTeacherNum(userId);
    }

    @GetMapping("/getTotalDuration")
    public long getTotalDuration(String userId){
        return courceFinishRecordService.getTotalDuration(userId);
    }

    @GetMapping("/selectByCourseIdAndUserId")
    CourseFinishRecord selectByCourseIdAndUserId(String userId,String courseId){
        return courceFinishRecordService.selectByCourseIdAndUserId(userId,courseId);
    }

    @GetMapping("/searchCuCountByPId")
    public int searchCuCountByPId(String userId){
        return courceFinishRecordService.searchCuCountByPId(userId);
    }

}