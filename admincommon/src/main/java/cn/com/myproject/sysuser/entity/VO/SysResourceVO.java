package cn.com.myproject.sysuser.entity.VO;

import cn.com.myproject.sysuser.entity.PO.SysResource;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther CQC
 * @create 2017.8.4
 */
public class SysResourceVO {

    private String resourceId;
    private String resourceName;
    /**
     * 资源值
     * type=1,url
     * type=2,字符串
     * */
    private String value;

    private Short type;

    private String parentId;

    private String icon;

    private Integer seqno;

    private Integer id;
    private Long createTime;
    private Short status;
    private Integer version;

    public SysResourceVO(SysResource resource) {
        this.resourceId = resource.getResourceId();
        this.resourceName = resource.getResourceName();
        this.value = resource.getValue();
        this.type = resource.getType();
        this.parentId = resource.getParentId();
        this.icon = resource.getIcon();
        this.id = resource.getId();
        this.createTime = resource.getCreateTime();
        this.status = resource.getStatus();
        this.version = resource.getVersion();
        this.seqno = resource.getSeqno();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public String getFCreateTime() {
        if(null != this.createTime){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createTime));
        }
        return "";
    }
}
