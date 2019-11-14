package cn.com.myproject.external;

import cn.com.myproject.advertise.service.impl.AdvertiseService;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.user.entity.PO.Advertise;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@RestController
@RequestMapping("/advertise")
public class AdvertiseController {

    @Autowired
    private AdvertiseService advertiseService;

    @PostMapping("/getPage")
    public PageInfo<Advertise> getPage(@RequestBody Map<String, Object> map) throws UnsupportedEncodingException {

        if(!map.isEmpty() && map.get("keyword")!=null && org.apache.commons.lang.StringUtils.isNotBlank(map.get("keyword").toString())){

            map.put("keyword", URLDecoder.decode(map.get("keyword").toString(),"UTF-8"));
        }
        return advertiseService.getPage(Integer.valueOf(map.get("pageNum") +""), Integer.valueOf(map.get("pageSize") +""), map);
    }

    @GetMapping("/selectAll")
    public List<Advertise> selectAll(){

        return advertiseService.selectAll();
    }

    @PostMapping("/selectAdverById")
    public Advertise selectAdverById(String adverId){

        return advertiseService.selectAdverById(adverId);
    }

    @PostMapping("/selectAdverType")
    public List<Advertise> selectAdverType(int type, int status){


        return advertiseService.selectAdverType(type, status);
    }

    @PostMapping("/addAdver")
    public int addAdver(@RequestBody Advertise advertise){

        int result = 0;
        try {
            advertise.setAdverId(UUID.randomUUID().toString().replace("-", ""));
            advertise.setCreateTime(new Date().getTime());
            advertise.setVersion(1);
            advertise.setName(advertise.getName());
            advertise.setAdverName(advertise.getAdverName());
            advertise.setAdverPlace(advertise.getAdverPlace());
            advertise.setLinkAddress(advertise.getLinkAddress());
            advertiseService.addAdver(advertise);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }
    @PostMapping("/updateAdver")
    public int updateAdver(@RequestBody Advertise advertise){

        int result = 0;
        try {
            advertiseService.updateAdver(advertise);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @PostMapping("/deleteAdver")
    public int deleteAdver(String adverId){

        int result = 0;
        try{
            advertiseService.deleteAdver(adverId);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @GetMapping("/selectAllGoods")
    public List<Goods> selectAllGoods(){

        return advertiseService.selectAllGoods();
    }

    @PostMapping("/selectGoodsByKeyword")
    public List<Goods> selectGoodsByKeyword(int pageNum, int pageSize, String keyword){

        return advertiseService.selectGoodsByKeyword(pageNum, pageSize, keyword);
    }

    @GetMapping("/getPageGoods")
    public List<Goods> getPageGoods(int pageNum, int pageSize){

        return advertiseService.getPageGoods(pageNum, pageSize);
    }
}









































