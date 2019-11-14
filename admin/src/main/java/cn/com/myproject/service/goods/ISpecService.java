package cn.com.myproject.service.goods;
import cn.com.myproject.goods.entity.PO.Spec;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * Created by LeiJia on 2017-09-14
 * desc：规格Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ISpecService {

    @PostMapping("/spec/addSpec")
    public boolean addSpec(@RequestBody Spec spec);

    @PostMapping("/spec/removeSpec")
    public boolean removeSpec(@RequestParam("specId") String sepcId);

    @PostMapping("/spec/modifySpec")
    public boolean modifySpec(@RequestBody Spec spec);

    @GetMapping("/spec/searchSpecList")
    public PageInfo<Spec> searchSpecList(@RequestParam("pageNum") int pageNum,
                                                 @RequestParam("pageSize") int pageSize,
                                                 @RequestParam("specName") String specName);


    @GetMapping("/spec/getSpecList")
    public List<Spec> getSpecList();

    @GetMapping("/spec/searchSpecById")
    public Spec searchSpecById(@RequestParam("specId") String specId);
}