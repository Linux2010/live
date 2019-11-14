package cn.com.myproject.course.service.Impl;

import cn.com.myproject.course.mapper.CourseTopicExaminationMapper;
import cn.com.myproject.course.mapper.CourseTopicMapper;
import cn.com.myproject.course.mapper.CourseTopicUserAnswerMapper;
import cn.com.myproject.course.service.ICourseTopicService;
import cn.com.myproject.live.entity.PO.CourseTopic;
import cn.com.myproject.live.entity.PO.CourseTopicExamination;
import cn.com.myproject.live.entity.PO.CourseTopicUserAnswer;
import cn.com.myproject.live.entity.VO.CourseRegisterExamVO;
import cn.com.myproject.live.entity.VO.CourseTopicCourseVO;
import cn.com.myproject.live.entity.VO.CourseTopicExaminationVO;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * Created by LeiJia on 2017/8/7 0007.
 */
@Service
public class CourseTopicService implements ICourseTopicService {

    private static final Logger logger = LoggerFactory.getLogger(CourseTopicService.class);

    @Autowired
    private CourseTopicMapper topicMapper;

    @Autowired
    private CourseTopicExaminationMapper courseTopicExaminationMapper;

    @Autowired
    private CourseTopicUserAnswerMapper courseTopicUserAnswerMapper;

    /**
     * 根据课程ID查询考卷ID
     *
     * @param courseId
     * @return
     */
    @Override
    public String selectKjIdByCId(String courseId){
        return courseTopicExaminationMapper.selectKjIdByCId(courseId);
    }

    public  PageInfo<CourseTopicExaminationVO> getPageCourseTopicExaminations(int pageNum, int pageSize , CourseTopicExaminationVO vo){
       return new PageInfo<CourseTopicExaminationVO>(courseTopicExaminationMapper.getPageCourseTopicExaminations(pageNum,pageSize,vo));
    }

    public List<Map<String ,Object>> getStudyLabels(Map<String,Object> map){
       return courseTopicExaminationMapper.getStudyLabels(map);
    }

    public  int insertTopicExamination( Map<String,Object> map){
        return courseTopicExaminationMapper.insertTopicExamination(map);
    }

    public int insertTopic(Map<String, Object> map){
        return topicMapper.insertTopic(map);
    }


    public List<Map<String ,Object>>  getCourseListByCourseTypeId(String courseTypeId){
        return courseTopicExaminationMapper.getCourseListByCourseTypeId(courseTypeId);
    }

    @Transactional
    public  int delCourseTopicExam( String course_topic_examination_id ){
        int deleteExamResult = courseTopicExaminationMapper.delCourseTopicExam(course_topic_examination_id);
        if(deleteExamResult == 1) {
            int deleteTopicResult = topicMapper.deleteCourseTopics(course_topic_examination_id);
            if(deleteTopicResult <1) {
                logger.error("删除考题失败courseTopicExaminationMapper.delCourseTopicExam(courseTopic_examination_id)");
                throw new RuntimeException("删除考题失败");
            }
            return 1;
        }else {
            logger.error("删除考卷失败courseTopicExaminationMapper.delCourseTopicExam(courseTopic_examination_id)");
            throw new RuntimeException("删除考卷失败");
        }
    }
    @Transactional
    public int updateTopics(String data,String userId){
        JSONObject object =  JSONObject.fromObject(data);

        Map<String,Object> examMap = new LinkedHashMap<>();
        examMap.put("course_topic_examination_id", object.get("course_topic_examination_id"));
        examMap.put("course_id",object.get("courseId"));
        examMap.put("examination_name",object.get("examinationName"));
        examMap.put("is_register_topic",object.get("isRegisterTopic"));
        examMap.put("update_time",Calendar.getInstance().getTimeInMillis());
        examMap.put("updater",userId);
        int examInsertResult = courseTopicExaminationMapper.updateTopicExamination(examMap);
        JSONArray array = object.getJSONArray("topics");
        if(examInsertResult > 0){
            Iterator<Object> it = array.iterator();
            while (it.hasNext()) {
                JSONObject objectTopic = (JSONObject) it.next();
                Map<String,Object> topicMap = new LinkedHashMap<>();
                topicMap.put("course_topic_id",objectTopic.get("course_topic_id"));
                if(objectTopic.get("id")!=null && objectTopic.get("course_topic_id")!=null){
                    topicMap.put("id", objectTopic.get("id"));
                }else{
                    topicMap.put("course_topic_id", UUID.randomUUID().toString().replace("-",""));
                    topicMap.put("id", UUID.randomUUID().toString().replace("-",""));
                    topicMap.put("create_time", Calendar.getInstance().getTimeInMillis());
                    topicMap.put("creater",userId);
                }
                topicMap.put("course_topic_examination_id", object.get("course_topic_examination_id"));
                topicMap.put("topic_name",objectTopic.get("topicName"));
                topicMap.put("topic_a_value",objectTopic.get("topicAvalue"));
                topicMap.put("topic_b_value", objectTopic.get("topicBvalue"));
                topicMap.put("topic_c_value", objectTopic.get("topicCvalue"));
                topicMap.put("topic_d_value",objectTopic.get("topicDvalue"));
                topicMap.put("topic_a_label_id",objectTopic.get("topicAlabelId"));
                topicMap.put("topic_b_label_id",objectTopic.get("topicBlabelId"));
                topicMap.put("topic_c_label_id",objectTopic.get("topicClabelId"));
                topicMap.put("topic_d_label_id",objectTopic.get("topicDlabelId"));
                topicMap.put("right_answer",objectTopic.get("rightAnwer"));
                topicMap.put("update_time",Calendar.getInstance().getTimeInMillis());
                topicMap.put("updater",userId);
                topicMap.put("topic_no",objectTopic.get("topicNo"));
                int topicInsertResult = 0;
                if(objectTopic.get("id")!=null && objectTopic.get("course_topic_id")!=null){
                    topicInsertResult =  topicMapper.updateTopic(topicMap);
                }else{
                    topicInsertResult =  topicMapper.insertTopic(topicMap);
                }

                if(topicInsertResult <1){
                    logger.error("考卷修改失败，请修改考卷重新添加考题topicMapper.updateTopic(examMap)");
                    throw new RuntimeException("考卷修改失败");
                }
            }
            return 1;

        }else{
            logger.error("考卷修改失败courseTopicExaminationMapper.updateTopicExamination(topicMap)");
            throw new RuntimeException("考卷修改失败");
        }
    }
    @Transactional
    public int insertTopics(String data,String userId){
        JSONObject object =  JSONObject.fromObject(data);
        String course_topic_examination_id = UUID.randomUUID().toString().replace("-","");

        Map<String,Object> examMap = new LinkedHashMap<>();
        examMap.put("course_topic_examination_id", course_topic_examination_id);
        if(object.get("courseId") !=null) examMap.put("course_id",object.get("courseId"));
        if(object.get("examinationName") !=null)examMap.put("examination_name",object.get("examinationName"));
        if(object.get("isRegisterTopic") !=null) examMap.put("is_register_topic",object.get("isRegisterTopic"));
        examMap.put("create_time", Calendar.getInstance().getTimeInMillis());
        if(StringUtils.isNotEmpty(userId))examMap.put("creater",userId);
        examMap.put("update_time",Calendar.getInstance().getTimeInMillis());
        if(StringUtils.isNotEmpty(userId))examMap.put("updater",userId);
        examMap.put("status",1);
        int examInsertResult =  courseTopicExaminationMapper.insertTopicExamination(examMap);
        JSONArray array = object.getJSONArray("topics");
        if(examInsertResult > 0){
            Iterator<Object> it = array.iterator();
            while (it.hasNext()) {
                JSONObject objectTopic = (JSONObject) it.next();
                Map<String,Object> topicMap = new LinkedHashMap<>();
                topicMap.put("course_topic_id", UUID.randomUUID().toString().replace("-",""));
                topicMap.put("course_topic_examination_id",course_topic_examination_id);
                topicMap.put("topic_name",objectTopic.get("topicName") !=null?objectTopic.get("topicName"):object.get("examinationName"));
                topicMap.put("topic_a_value",objectTopic.get("topicAvalue"));
                topicMap.put("topic_b_value", objectTopic.get("topicBvalue"));
                topicMap.put("topic_c_value", objectTopic.get("topicCvalue"));
                topicMap.put("topic_d_value",objectTopic.get("topicDvalue"));
                topicMap.put("topic_a_label_id",objectTopic.get("topicAlabelId"));
                topicMap.put("topic_b_label_id",objectTopic.get("topicBlabelId"));
                topicMap.put("topic_c_label_id",objectTopic.get("topicClabelId"));
                topicMap.put("topic_d_label_id",objectTopic.get("topicDlabelId"));
                topicMap.put("right_answer",objectTopic.get("rightAnwer"));
                topicMap.put("create_time", Calendar.getInstance().getTimeInMillis());
                topicMap.put("creater",userId);
                topicMap.put("update_time",Calendar.getInstance().getTimeInMillis());
                topicMap.put("updater",userId);
                topicMap.put("topic_no",objectTopic.get("topicNo"));
                int topicInsertResult = topicMapper.insertTopic(topicMap);

                if(topicInsertResult <1){
                    logger.error("考卷添加失败，请修改考卷重新添加考题topicMapper.insertTopicExamination(examMap)");
                    throw new RuntimeException("考卷添加失败");
                }
            }
            return 1;

        }else{
            logger.error("考卷添加失败courseTopicExaminationMapper.insertTopic(topicMap)");
            throw new RuntimeException("考卷添加失败");
        }
    }

    public CourseTopicExaminationVO getExamAndTopicsInfoByCourseTopicExamId(String courseTopicExaminationId){
        return courseTopicExaminationMapper.getExamAndTopicsInfoByCourseTopicExamId(courseTopicExaminationId);
    }

    //根据课程ID获取考卷信息
    public CourseTopicExamination selectTopicsByCourseTopicCourseId(String courseId){
        return courseTopicExaminationMapper. selectTopicsByCourseTopicCourseId(courseId);
    }

    public  Map<String ,Object> selectRegisterCourseTopicExamination(){
        return courseTopicExaminationMapper.selectRegisterCourseTopicExamination();
    }

    public CourseRegisterExamVO getRegisterById(String courseTopicExaminationId) {
        return courseTopicExaminationMapper.getRegisterById(courseTopicExaminationId);
    }

    @Override
    public List<CourseTopic> getExam(String courseTopicExaminationId) {
        return topicMapper.getExam(courseTopicExaminationId);
    }
    @Override
    public List<CourseTopic> searchCourseTopicsByCourseId(String courseId){
        return topicMapper.searchCourseTopicsByCourseId(courseId);
    }
    @Override
    @Transactional
    public int  submitCourseTopicsAnswer(CourseTopicExamination examination){
        String courseTopicExaminationId =examination.getCourseTopicExaminationId();
        List<CourseTopicUserAnswer> answersList =  examination.getAnswerList();
        for(int i=0;i<answersList.size();i++){
            CourseTopicUserAnswer answer = answersList.get(i);
            answer.setCourseTopicExaminationId(courseTopicExaminationId);
            answer.setCreateTime(Calendar.getInstance().getTimeInMillis());
            answer.setUserId(examination.getUserId());
            answer.setCourseTopicUserAnswerId(UUID.randomUUID().toString().replace("-",""));
            answer.setStatus((short)1);
            answer.setVersion(1);
            int userAnswerResult =  courseTopicUserAnswerMapper.insertUserAnswer(answer);
            if(userAnswerResult <1){
                logger.error("提交考卷答案失败courseTopicUserAnswerMapper.insertUserAnswer(answer)");
                throw new RuntimeException("提交考卷答案失败courseTopicUserAnswerMapper.insertUserAnswer(answer)");
            }
        }
        return 1;
    }
    @Override
    public List<CourseTopicCourseVO> getUserTopicCourseList(int pageNum,int pageSize,String userId){
         return courseTopicUserAnswerMapper.getUserTopicCourseList(pageNum,pageSize,userId);
    }

    @Override
    public  List<CourseTopicCourseVO>  getUserTopicCourseNoAnswerTheTopicsList(int pageNum,int pageSize,String userId){
        return courseTopicUserAnswerMapper.getUserTopicCourseNoAnswerTheTopicsList(pageNum,pageSize,userId);
    }

    @Override
    public  List<CourseTopic> selectUserCourseTopicsAndChooseAnswers(CourseTopic topic){
        List<CourseTopic> topicList = topicMapper.selectUserCourseTopicsAndChooseAnswers(topic);
        return topicList;
    }

    @Override
    public CourseTopic selectRegisterAnswerById(String courseTopicId, String courseTopicExaminationId) {

        return topicMapper.selectRegisterAnswerById(courseTopicId, courseTopicExaminationId);
    }

    @Override
    public CourseTopic selectTopicLable(CourseTopic courseTopic) {
        return topicMapper.selectTopicLable(courseTopic);
    }

}
