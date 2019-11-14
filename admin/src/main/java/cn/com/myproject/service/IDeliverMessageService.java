package cn.com.myproject.service;

import cn.com.myproject.logi.entity.PO.DeliverMessage;
import cn.com.myproject.logi.entity.VO.OrderDeliverVO;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jyp on 2017/9/28 0028.
 */
@FeignClient(name = "admin-api-provide", url = "${feignclient.url}")
public interface IDeliverMessageService {

    @GetMapping("/deliverMessage/selectOrderDeliverMessage")
    public PageInfo<OrderDeliverVO> selectOrderDeliverMessage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);

    @GetMapping("/deliverMessage/updateDeliNo")
    public int updateDeliNo(@RequestParam("deliNo") String deliNo, @RequestParam("deliId") String deliId);

    @GetMapping("/deliverMessage/selectByDeliNo")
    public DeliverMessage selectByDeliNo(@RequestParam("deliNo") String deliNo);
}
