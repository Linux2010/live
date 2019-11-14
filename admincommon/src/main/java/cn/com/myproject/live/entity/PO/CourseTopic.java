package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/8/14 0014.
 */
@ApiModel(value = "CourseTopic", description = "考题对象")
public class CourseTopic extends BasePO {

    @ApiModelProperty(value = "课程题目业务ID")
    private String courseTopicId;
    @ApiModelProperty(value = "考题试卷业务ID")
    private String courseTopicExaminationId;
    @ApiModelProperty(value = "课程题目名称")
    private String topicName;
    @ApiModelProperty(value = "课程选项1")
    private String topicAvalue;
    @ApiModelProperty(value = "课程选项2")
    private String topicBvalue;
    @ApiModelProperty(value = "课程选项3")
    private String topicCvalue;
    @ApiModelProperty(value = "课程选项4")
    private String topicDvalue;
    @ApiModelProperty(value = "课程选项1学习标签业务ID")
    private String topicAlabelId;
    @ApiModelProperty(value = "课程选项2学习标签业务ID")
    private String topicBlabelId;
    @ApiModelProperty(value = "课程选项3学习标签业务ID")
    private String topicClabelId;
    @ApiModelProperty(value = "课程选项4学习标签业务ID")
    private String topicDlabelId;
    @ApiModelProperty(value = "正确答案(A:选项A；B:选项B;C:选项C;D:选项D ),多个正确答案以英文逗号记录")
    private String rightAnswer;
    @ApiModelProperty(value = "用户选择的这道题的正确答案,多个正确答案以英文逗号记录 如A,B")
    private String userChooseAnswer;

    @ApiModelProperty(value = "本题答题率,后台计算过可以直接展示")
    private String answerRate="100%";

    @ApiModelProperty(value = "题号")
    private int  topicNo;

    @ApiModelProperty(value = "本题答题者业务ID")
    private String answerUserId;

    private String updater;

    private String creater;

    private Long updateTime;

    public CourseTopic(){

    }

    public CourseTopic(String answerUserId,String courseTopicExaminationId){
        this.answerUserId = answerUserId;
        this.courseTopicExaminationId = courseTopicExaminationId;
    }

    public String getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(String answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getAnswerRate() {
        return answerRate;
    }

    public void setAnswerRate(String answerRate) {
        this.answerRate = answerRate;
    }

    public String getUserChooseAnswer() {
        return userChooseAnswer;
    }

    public void setUserChooseAnswer(String userChooseAnswer) {
        this.userChooseAnswer = userChooseAnswer;
    }

    public int getTopicNo() {
        return topicNo;
    }

    public void setTopicNo(int topicNo) {
        this.topicNo = topicNo;
    }

    public String getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(String courseTopicId) {
        this.courseTopicId = courseTopicId;
    }

    public String getCourseTopicExaminationId() {
        return courseTopicExaminationId;
    }

    public void setCourseTopicExaminationId(String courseTopicExaminationId) {
        this.courseTopicExaminationId = courseTopicExaminationId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicAvalue() {
        return topicAvalue;
    }

    public void setTopicAvalue(String topicAvalue) {
        this.topicAvalue = topicAvalue;
    }

    public String getTopicBvalue() {
        return topicBvalue;
    }

    public void setTopicBvalue(String topicBvalue) {
        this.topicBvalue = topicBvalue;
    }

    public String getTopicCvalue() {
        return topicCvalue;
    }

    public void setTopicCvalue(String topicCvalue) {
        this.topicCvalue = topicCvalue;
    }

    public String getTopicDvalue() {
        return topicDvalue;
    }

    public void setTopicDvalue(String topicDvalue) {
        this.topicDvalue = topicDvalue;
    }

    public String getTopicAlabelId() {
        return topicAlabelId;
    }

    public void setTopicAlabelId(String topicAlabelId) {
        this.topicAlabelId = topicAlabelId;
    }

    public String getTopicBlabelId() {
        return topicBlabelId;
    }

    public void setTopicBlabelId(String topicBlabelId) {
        this.topicBlabelId = topicBlabelId;
    }

    public String getTopicClabelId() {
        return topicClabelId;
    }

    public void setTopicClabelId(String topicClabelId) {
        this.topicClabelId = topicClabelId;
    }

    public String getTopicDlabelId() {
        return topicDlabelId;
    }

    public void setTopicDlabelId(String topicDlabelId) {
        this.topicDlabelId = topicDlabelId;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
