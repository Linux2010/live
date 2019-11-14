package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;

/**
 * Created by 李延超 on 2017/8/24.
 */
public class NewsReply extends BasePO {
    private String replyId;
    private String replyContent;
    private long replyTime;
    private String userName;
    private  String replyUserId;
    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }


    public long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplyUserId() {
        return replyUserId;
    }


    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }
}