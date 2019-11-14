package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther LeiJia
 * @create 2017.10.11
 */
@ApiModel(value = "UserTeacherIntroImg", description = "教头用户简介图片象")
public class UserTeacherIntroImg extends BasePO{

    @ApiModelProperty(value = "商品图片表业务ID")
    private String teacherIntroImgId;

    @ApiModelProperty(value = "教头ID")
    private String teacherId;

    @ApiModelProperty(value = "图片URL")
    private String url;

    @ApiModelProperty(value = "序号")
    private int  seqno;

    public String getTeacherIntroImgId() {
        return teacherIntroImgId;
    }

    public void setTeacherIntroImgId(String teacherIntroImgId) {
        this.teacherIntroImgId = teacherIntroImgId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }
}
