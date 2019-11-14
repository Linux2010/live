package cn.com.myproject.external;

import cn.com.myproject.logi.entity.PO.LogiPlatform;
import cn.com.myproject.logi.service.ILogiPlatformService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jyp on 2017/9/14 0014.
 */
@RestController
@RequestMapping("/plat")
public class LogiPlatformController {

    private static final Logger logger = LoggerFactory.getLogger(LogiPlatformController.class);

    @Autowired
    private ILogiPlatformService logiPlatformService;


    @PostMapping("/list")
    public PageInfo<LogiPlatform> list(int pageNum, int pageSize) {
        return logiPlatformService.getPlatList(pageNum, pageSize);
    }

    @PostMapping("/selectById")
    public LogiPlatform selectById(String platId) {
        return logiPlatformService.selectById(platId);
    }

    @PostMapping("/addplat")
    public int addplat(@RequestBody LogiPlatform logiPlatform) {
        int info = 0;
        try {
            logiPlatformService.addplat(logiPlatform);
            info = 1;
        } catch (Exception e) {
            logger.error("LogiPlatformController-addplat-添加物流平台失败:" + e);
        }
        return info;
    }

    @PostMapping("/updateplat")
    public int updateplat(@RequestBody LogiPlatform logiPlatform) {
        int info = 0;
        try {
            logiPlatformService.updateplat(logiPlatform);
            info = 1;
        } catch (Exception e) {
            logger.error("LogiPlatformController-updateplat-修改物流平台失败:" + e);
        }
        return info;
    }

    @PostMapping("/delplat")
    public int delplat(String platId) {
        int info = 0;
        try {
            logiPlatformService.delplat(platId);
            info = 1;
        } catch (Exception e) {
            logger.error("LogiPlatformController-delplat-删除物流平台失败:" + e);
        }
        return info;
    }

    @GetMapping("/selectOpenPlat")
    public LogiPlatform selectOpenPlat(){
        return logiPlatformService.selectOpenPlat();
    }
}
