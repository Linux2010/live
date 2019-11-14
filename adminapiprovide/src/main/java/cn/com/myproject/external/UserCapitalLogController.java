package cn.com.myproject.external;

import cn.com.myproject.user.entity.PO.UserCapitalLog;
import cn.com.myproject.user.service.IUserCapitalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.8.29
 */
@RestController
@RequestMapping("/usercapitallog")
public class UserCapitalLogController {

    @Autowired
    private IUserCapitalLogService userCapitalLogService;

    @GetMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(String bid) {
        return userCapitalLogService.deleteByPrimaryKey(bid);
    }

    @PostMapping("/insert")
    int insert(@RequestBody UserCapitalLog record) {
        return userCapitalLogService.insert(record);
    }

    @PostMapping("/insertSelective")
    int insertSelective(@RequestBody UserCapitalLog record) {
        return userCapitalLogService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    UserCapitalLog selectByPrimaryKey(String bid) {
        return userCapitalLogService.selectByPrimaryKey(bid);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody UserCapitalLog record) {
        return userCapitalLogService.updateByPrimaryKeySelective(record);
    }

    @GetMapping("/updateByPrimaryKeys")
    int updateByPrimaryKey(@RequestBody UserCapitalLog record) {
        return userCapitalLogService.updateByPrimaryKey(record);
    }

    @PostMapping("/getUserCapitalAccount")
    List<Map<String, Object>> getUserCapitalAccount(@RequestBody UserCapitalLog record) {
        return userCapitalLogService.getUserCapitalAccount(record);
    }

    @GetMapping("/selectTopUpList")
    public List<UserCapitalLog> selectTopUpList(int pageNum, int pageSize, String userId) {
        return userCapitalLogService.selectTopUpList(pageNum, pageSize, userId);
    }

    @GetMapping("/selectWithList")
    public List<UserCapitalLog> selectWithList(int pageNum, int pageSize, String userId) {
        return userCapitalLogService.selectWithList(pageNum, pageSize, userId);
    }

    @GetMapping("/selectAllList")
    public List<UserCapitalLog> selectAllList(int pageNum, int pageSize, String userId) {
        return userCapitalLogService.selectAllList(pageNum, pageSize, userId);
    }

}
