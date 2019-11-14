package cn.com.myproject.api.service;

import cn.com.myproject.live.entity.PO.CourseFinishRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther CQC
 * @create 2017.9.1
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ICourseFinishRecordService {

    @GetMapping("/courseFinishRecord/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String cfrId);

    @PostMapping("/courseFinishRecord/insert")
    public int insert(@RequestBody CourseFinishRecord record);

    @PostMapping("/courseFinishRecord/insertSelective")
    public int insertSelective(@RequestBody CourseFinishRecord record);

    @GetMapping("/courseFinishRecord/selectByPrimaryKey")
    public CourseFinishRecord selectByPrimaryKey(String cfrId);

    @GetMapping("/courseFinishRecord/selectByCourseId")
    public CourseFinishRecord selectByCourseId(@RequestParam("courseId") String courseId);

    @GetMapping("/courseFinishRecord/selectByUserId")
    public List<CourseFinishRecord> selectByUserId(@RequestParam("userId") String userId);

    @PostMapping("/courseFinishRecord/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody CourseFinishRecord record);

    @PostMapping("/courseFinishRecord/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody CourseFinishRecord record);

    @GetMapping("/courseFinishRecord/getFinishNum")
    public int getFinishNum(@RequestParam("userId") String userId);

    @GetMapping("/courseFinishRecord/getFinishTeacherNum")
    public int getFinishTeacherNum(@RequestParam("userId") String userId);

    @GetMapping("/courseFinishRecord/getTotalDuration")
    public long getTotalDuration(@RequestParam("userId") String userId);

    @GetMapping("/courseFinishRecord/selectByCourseIdAndUserId")
    public CourseFinishRecord selectByCourseIdAndUserId(@RequestParam("userId") String userId,@RequestParam("courseId") String courseId);

    @GetMapping("/courseFinishRecord/searchCuCountByPId")
    int searchCuCountByPId(@RequestParam("userId") String userId);

}