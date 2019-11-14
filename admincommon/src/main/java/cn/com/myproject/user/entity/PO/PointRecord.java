package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 李延超 on 2017/8/28.
 */
@ApiModel(value = "pointRecord", description = "点赞对象")
public class PointRecord extends BasePO {

    @ApiModelProperty(value = "点赞id")
    private String pointRecordId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "咨询id")
    private String informationId;
    @ApiModelProperty(value = "点赞类型：1、点赞 2、倒赞")
    private  int type;
    @ApiModelProperty(value = "是否操作过： 1、未操作 2、已操作")
    private int isOperation;
    public String getPointRecordId() {
        return pointRecordId;
    }

    public void setPointRecordId(String pointRecordId) {
        this.pointRecordId = pointRecordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsOperation() {
        return isOperation;
    }

    public void setIsOperation(int isOperation) {
        this.isOperation = isOperation;
    }
}
