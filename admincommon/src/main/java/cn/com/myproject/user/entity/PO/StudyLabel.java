package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

/**
 * Created by JYP on 2017/8/8 0008.
 */
public class StudyLabel extends BasePO{


    private String labelid;
    private String labeltype;
    private String labelname;
    private String courseTagId;

    public String getCourseTagId() {
        return courseTagId;
    }

    public void setCourseTagId(String courseTagId) {
        this.courseTagId = courseTagId;
    }

    public String getLabeltype() {
        return labeltype;
    }

    public void setLabeltype(String labeltype) {
        this.labeltype = labeltype;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }
}
