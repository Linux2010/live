package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/8/15 0014.
 */

@ApiModel(value = "CourseTopicUserAnswer", description = "考题答案对象")
public class CourseTopicUserAnswer extends BasePO {

    @ApiModelProperty(value = "用户答题表业务ID",required = false)
    private  String courseTopicUserAnswerId;
    @ApiModelProperty(value = "考题试卷业务ID",required = false)
    private  String courseTopicExaminationId;
    @ApiModelProperty(value = "课程考题业务ID",required = true)
    private  String courseTopicId;
    @ApiModelProperty(value = "用户答题表业务ID",required = true)
    private  String  answer;
    @ApiModelProperty(value = "用户的该题是否正确(0:正确;1:错误)",required = false)
    private  int  isRight;
    @ApiModelProperty(value = "选择这个答案的人数/答这道题的人数的比(小数存储)",required = false)
    private  double righRate;
    @ApiModelProperty(value = "答题用户业务ID(答题者)",required = false)
    private  String userId;

    public String getCourseTopicUserAnswerId() {
        return courseTopicUserAnswerId;
    }

    public void setCourseTopicUserAnswerId(String courseTopicUserAnswerId) {
        this.courseTopicUserAnswerId = courseTopicUserAnswerId;
    }

    public String getCourseTopicExaminationId() {
        return courseTopicExaminationId;
    }

    public void setCourseTopicExaminationId(String courseTopicExaminationId) {
        this.courseTopicExaminationId = courseTopicExaminationId;
    }

    public String getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(String courseTopicId) {
        this.courseTopicId = courseTopicId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsRight() {
        return isRight;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }

    public double getRighRate() {
        return righRate;
    }

    public void setRighRate(double righRate) {
        this.righRate = righRate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
