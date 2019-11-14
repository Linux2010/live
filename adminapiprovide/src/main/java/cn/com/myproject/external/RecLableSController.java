package cn.com.myproject.external;

import cn.com.myproject.recset.entity.PO.RecLable;
import cn.com.myproject.recset.service.IRecLableService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyp on 2017/8/17 0017.
 */
@RestController
@RequestMapping("/todayrec")
public class RecLableSController {

    @Autowired
    private IRecLableService iRecLableService;

    @GetMapping("/getPage")
    public PageInfo<RecLable> getPage(int pageNum, int pageSize) {
        return iRecLableService.getPage(pageNum, pageSize);
    }

    ;
}
