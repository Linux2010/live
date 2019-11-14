package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by LeiJia on 2017/8/21 0021.
 */
@ApiModel(value = "UserType", description = "教头类别对像")
public class UserType extends BasePO {
    @ApiModelProperty(value = "用户类别业务ID")
    private String userTypeId;
    @ApiModelProperty(value = "类别名称")
    private String typeName;
    @ApiModelProperty(value = "上级类别业务ID")
    private String parentId;
    @ApiModelProperty(value = "创建教头类别的用户业务ID")
    private String userId;
    // 类别：0:会员；1：教头/讲师
    @ApiModelProperty(value = "类别：0:会员；1：教头/讲师")
    private int typeVal;
    // 分类级别：1代表一级分类，2代表二级分类
    @ApiModelProperty(value = "分类级别：1代表一级分类，2代表二级分类")
    private int typeLevel;

    @ApiModelProperty(value = "教头分类图片")
    private String userTypePicture;

    public String getUserTypePicture() {
        return userTypePicture;
    }

    public void setUserTypePicture(String userTypePicture) {
        this.userTypePicture = userTypePicture;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(int typeVal) {
        this.typeVal = typeVal;
    }

    public int getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(int typeLevel) {
        this.typeLevel = typeLevel;
    }
}
