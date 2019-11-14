package cn.com.myproject.external;

import cn.com.myproject.live.entity.PO.SysDoc;
import cn.com.myproject.user.service.ISysDocService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案后台服务控制器类
 */
@RestController
@RequestMapping("/sysDoc")
public class SysDocController {

    @Autowired
    private ISysDocService iSysDocService;

    @GetMapping("/searchSdList")
    public PageInfo<SysDoc> searchSdList(int pageNum, int pageSize, int docType){
        return iSysDocService.searchSdList(pageNum,pageSize,docType);
    }

    @GetMapping("/searchSdcByType")
    public String searchSdcByType(int docType){
        return iSysDocService.searchSdcByType(docType);
    }

    @PostMapping("/modifySdc")
    public boolean modifySdc(@RequestBody SysDoc sysDoc){
        return iSysDocService.modifySdc(sysDoc);
    }

}