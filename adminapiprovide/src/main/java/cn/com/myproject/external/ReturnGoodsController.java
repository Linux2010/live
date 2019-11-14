package cn.com.myproject.external;

import cn.com.myproject.returnGoods.service.IReturnGoodsService;
import cn.com.myproject.returnGoods.service.impl.ReturnGoodsService;
import cn.com.myproject.user.entity.PO.ReturnGoods;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by LSG on 2017/9/14 0014.
 */
@RestController
@RequestMapping("/returnGoods")
public class ReturnGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(ReturnGoodsService.class);
    @Autowired
    private IReturnGoodsService returnGoodsService;

    @GetMapping("/allReturnGoods")
    public PageInfo<ReturnGoods> allReturnGoods(int pageNum, int pageSize){

        return returnGoodsService.allReturnGoods(pageNum, pageSize);
    }

    @PostMapping("/selectByStatus")
    public List<ReturnGoods> selectByStatus(int status){

        return returnGoodsService.selectByStatus(status);
    }

    @PostMapping("/addReturnGoods")
    public int addReturnGoods(@RequestBody ReturnGoods returnGoods){

        int result = 0;
        try {
            returnGoods.setReturnGoodsId(UUID.randomUUID().toString().replace("-",""));
            returnGoods.setVersion(1);
            returnGoods.setOperationType(3);//退货申请未操作
            returnGoods.setStatus((short)3);//状态为退款中
            returnGoods.setCreateTime(new Date().getTime());
            returnGoods.setHandleTime(0);
            returnGoods.setRefuseReason(null);
            returnGoodsService.addReturnGoods(returnGoods);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            logger.info("用户申请退货抛出的异常！");
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectById")
    public ReturnGoods selectById(String returnGoodsId){

        return returnGoodsService.selectById(returnGoodsId);
    }
    @PostMapping("/deleteById")
    public int deleteById(String returnGoodsId){

        int result = 0;
        try {
            returnGoodsService.deleteById(returnGoodsId);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            logger.info("删除用户退货申请的记录抛出异常！");
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/updateReturnGoods")
    public int updateReturnGoods(@RequestBody ReturnGoods returnGoods){

        int result = 0;
        try {
            returnGoodsService.updateReturnGoods(returnGoods);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            logger.info("修改退货申请状态抛出异常！");
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectByUserId")
    public List<ReturnGoods> selectByUserId(String userId){

        return returnGoodsService.selectByUserId(userId);
    }

    @PostMapping("/selectByLogisticsNumber")
    public ReturnGoods selectByLogisticsNumber(String logisticsNumber){

        return returnGoodsService.selectByLogisticsNumber(logisticsNumber);
    }

    @PostMapping("/getPage")
    public PageInfo<ReturnGoods> getPage(int pageNum, int pageSize, int status){

        return returnGoodsService.getPage(pageNum, pageSize, status);
    }
}
