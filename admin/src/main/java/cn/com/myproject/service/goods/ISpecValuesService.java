package cn.com.myproject.service.goods;
import cn.com.myproject.goods.entity.PO.SpecValues;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Created by LeiJia on 2017-09-14
 * desc：规格值Service接口
 */
@FeignClient(name = "admin-api-provide",url = "${feignclient.url}")
public interface ISpecValuesService {

    @PostMapping("/specValues/addSpecValues")
    public boolean addSpecValues(@RequestBody SpecValues spec);

    @PostMapping("/specValues/removeSpecValues")
    public boolean removeSpecValues(@RequestParam("goodsSpecValuesId") String goodsSpecValuesId);

    @PostMapping("/specValues/modifySpecValues")
    public boolean modifySpecValues(@RequestBody SpecValues spec);

    @GetMapping("/specValues/searchSpecValuesList")
    public PageInfo<SpecValues> searchSpecValuesList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam("valuesName") String valuesName,
                                         @RequestParam("specId") String specId);

    @GetMapping("/specValues/searchSpecValuesById")
    public SpecValues searchSpecValuesById(@RequestParam("goodsSpecValuesId") String goodsSpecValuesId);
}