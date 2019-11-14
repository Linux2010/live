package cn.com.myproject.shortVideo.service;


import cn.com.myproject.user.entity.PO.ShortVideo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26 0007.
 */
public interface IShortVideoService {

    PageInfo<ShortVideo> getPage(int pageNum, int pageSize, String keyword);

    void addVideo(ShortVideo shortVideo);

    List<ShortVideo> selectByStatus(int status);

    ShortVideo selectById(String videoId);

    void updateVideo(ShortVideo shortVideo);

    void deleteVideo(String videoId);

    ShortVideo selectByType(int videoType);

    void updateStatus(ShortVideo shortVideo);
}
