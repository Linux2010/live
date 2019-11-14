package cn.com.myproject.user.entity.PO;

import cn.com.myproject.user.entity.VO.APITearcherUser;
import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/8/29 0029.
 */
@ApiModel(value = "UserTeacherFocus", description = "用户关注教头对像")
public class UserTeacherFocus extends BasePO {
    @ApiModelProperty(value = "用户关注教头表业务ID")
    private String userTeacherFocusId;
    @ApiModelProperty(value = "关注者用户业务ID")
    private String userId;
    @ApiModelProperty(value = "教头用户业务ID")
    private String teacherId;
    @ApiModelProperty(value = "是否关注0：未关注；1:关注了")
    private int isFocus;
    @ApiModelProperty(value = "我关注或关注我的用户类型 1：教头，2：用户")
    private Integer userType;
    @ApiModelProperty(value = "我关注或关注我的用户头像")
    private String photo;
    @ApiModelProperty(value = "我关注或关注我的用户个性签名")
    private String signature;
    @ApiModelProperty(value = "我关注或关注我的用户真实姓名")
    private String realName;
    @ApiModelProperty(value = "用户身份1:普通用户2：会员用户")
    private String userIdentity;

    public UserTeacherFocus(){

    }
    public UserTeacherFocus(String userId){
        this.userId = userId;
    }
    public UserTeacherFocus(String userId,String teacherId){
        this.userId = userId;
        this.teacherId = teacherId;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserTeacherFocusId() {
        return userTeacherFocusId;
    }

    public void setUserTeacherFocusId(String userTeacherFocusId) {
        this.userTeacherFocusId = userTeacherFocusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }
}
