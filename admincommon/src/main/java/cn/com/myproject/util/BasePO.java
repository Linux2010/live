package cn.com.myproject.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
public class BasePO implements Serializable{
    @ApiModelProperty(value = "主键ID",required = false)
    private Integer id;
    @ApiModelProperty(value = "创建时间",required = false)
    private Long createTime;
    @ApiModelProperty(value = "状态",required = false)
    private Short status;
    @ApiModelProperty(value = "版本号",required = false)
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取格式化后的时间
     * */
    public String getFCreateTime() {
        if(null != this.createTime){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createTime));
        }
        return "";
    }
}
