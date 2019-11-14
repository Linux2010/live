package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 李延超 on 2017/8/14.
 */
public class News extends BasePO {

    @ApiModelProperty(value = "新闻")

    private String businessId;

    @ApiModelProperty(value = "新闻标题")

    private String title;
    // 课程内容
    @ApiModelProperty(value = "新闻内容")
    private String content;

    @ApiModelProperty(value = "新闻内容Url")
    private String contentUrl;

    @ApiModelProperty(value = "封面")
    private String photo;

    @ApiModelProperty(value = "缩略图")
    private String thumbnailImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }
}
