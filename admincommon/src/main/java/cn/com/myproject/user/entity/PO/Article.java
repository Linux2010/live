package cn.com.myproject.user.entity.PO;

import cn.com.myproject.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Created by Administrator on 2017/8/7 0007.
 */
@ApiModel(value = "article", description = "文章海报对象")
public class Article extends BasePO {

    @ApiModelProperty(value = "文章id")
    private String articleId;
    @ApiModelProperty(value = "文章标题")
    private String title;
    @ApiModelProperty(value = "文章内容")
    private String content;
    @ApiModelProperty(value = "显示内容")
    private String showContent;
    @ApiModelProperty(value = "海报图片")
    private String img;
    @ApiModelProperty(value = "创建人")
    private String create_user;
    @ApiModelProperty(value = "修改时间")
    private String modify_time;
    @ApiModelProperty(value = "修改人")
    private String modify_user;
    @ApiModelProperty(value = "缩略图")
    private String thumbnailImg;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }
}
