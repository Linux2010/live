package cn.com.myproject.shortVideo.mapper;


import cn.com.myproject.user.entity.PO.ShortVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LSG on 2017/9/26 0007.
 */
@Mapper
public interface ShortVideoMapper {

    List<ShortVideo> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("keyword") String keyword);

    void addVideo(ShortVideo shortVideo);

    List<ShortVideo> selectByStatus(int status);

    ShortVideo selectById(String videoId);

    void updateVideo(ShortVideo shortVideo);

    void deleteVideo(String videoId);

    ShortVideo selectByType(int videoType);

    void updateStatus(ShortVideo shortVideo);
}

