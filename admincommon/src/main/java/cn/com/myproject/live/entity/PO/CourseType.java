package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * Created by pangdatao on 2017-08-14
 * desc：课程分类
 */
@ApiModel(value = "CourseType", description = "课程分类")
public class CourseType extends BasePO{

    // 课程分类ID
    @ApiModelProperty(value = "课程分类ID")
    private String courseTypeId;

    // 父分类ID
    @ApiModelProperty(value = "父分类ID")
    private String parentId;

    // 课程分类名称
    @ApiModelProperty(value = "课程分类名称")
    private String typeName;

    // 类别：视频课程、文字课程、音频课程、直播课程、江湖大课
    @ApiModelProperty(value = "类别：视频课程、文字课程、音频课程、直播课程、江湖大课")
    private String typeVal;

    // 分类级别：1代表一级分类，2代表二级分类
    @ApiModelProperty(value = "分类级别：1代表一级分类，2代表二级分类")
    private int typeLevel;

    // 分类图片路径
    @ApiModelProperty(value = "分类图片路径")
    private String typeUrl;

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(String typeVal) {
        this.typeVal = typeVal;
    }

    public int getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(int typeLevel) {
        this.typeLevel = typeLevel;
    }

    public String getTypeUrl() {
        return typeUrl;
    }

    public void setTypeUrl(String typeUrl) {
        this.typeUrl = typeUrl;
    }
}