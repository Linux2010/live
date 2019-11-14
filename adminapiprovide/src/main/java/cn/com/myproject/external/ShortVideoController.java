package cn.com.myproject.external;

import cn.com.myproject.shortVideo.service.IShortVideoService;
import cn.com.myproject.user.entity.PO.ShortVideo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LSG on 2017/9/26 0026.
 */
@RestController
@RequestMapping("/shortVideo")
public class ShortVideoController {

    @Autowired
    private IShortVideoService shortVideoService;

    @GetMapping("/getPage")
    public PageInfo<ShortVideo> getPage(int pageNum, int pageSize, String keyword){

        return shortVideoService.getPage(pageNum, pageSize, keyword);
    }

    @PostMapping("/addVideo")
    public int addVideo(@RequestBody ShortVideo shortVideo){

        int result = 0;
        try {
            shortVideo.setVideoId(UUID.randomUUID().toString().replace("-", ""));
            shortVideo.setStatus((short)2);
            shortVideo.setVersion(1);
            shortVideo.setCreateTime(new Date().getTime());
            shortVideoService.addVideo(shortVideo);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectByStatus")
    public List<ShortVideo> selectByStatus(int status){

        return shortVideoService.selectByStatus(status);
    }

    @PostMapping("/selectById")
    public ShortVideo selectById(String videoId){

        return shortVideoService.selectById(videoId);
    }

    @PostMapping("/updateVideo")
    public int updateVideo(@RequestBody ShortVideo shortVideo){

        int result = 0;
        try {
            shortVideoService.updateVideo(shortVideo);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/deleteVideo")
    public int deleteVideo(String videoId){

        int result = 0;
        try {
            shortVideoService.deleteVideo(videoId);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectByType")
    public ShortVideo selectByType(int videoType){

        return shortVideoService.selectByType(videoType);
    }

    @PostMapping("/updateStatus")
    public int updateStatus(@RequestBody ShortVideo shortVideo){

        int result = 0;
        try {
            shortVideoService.updateStatus(shortVideo);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }
}






























