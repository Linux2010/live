package cn.com.myproject.live.entity.PO;

import cn.com.myproject.util.BasePO;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案
 */
public class SysDoc extends BasePO{

    // 文案类型：1.学分制度、2.激活码制度、3.邀请好友规则、4.积分制度
    private int docType;

    // 文案内容
    private String docContent;

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }
}