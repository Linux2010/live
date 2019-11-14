package cn.com.myproject.external;

import cn.com.myproject.news.service.IPointRecordService;
import cn.com.myproject.news.service.ImplInformationService;
import cn.com.myproject.user.entity.PO.Information;
import cn.com.myproject.user.entity.PO.PointRecord;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 李延超 on 2017/8/18.
 */
@RestController
@RequestMapping("/inf")
public class InformationController {
    public static final Logger logger = LoggerFactory.getLogger(InformationController.class);

    @Autowired
    private ImplInformationService infoService;
    @Autowired
    private IPointRecordService pointRecordService;
    @PostMapping("/addInfo")
    public int addInfo(@RequestBody Information information){
        int result = 0;
        try{
            information.setTitle(information.getTitle());
            information.setCreateTime(new Date().getTime());
            information.setInformationId(UUID.randomUUID().toString().replace("-", ""));
            information.setStatus((short)1);
           information.setUserName("教头学院");
            information.setVersion(1);
            infoService.addInformation(information);
            result = 1;
        }catch(RuntimeException e){
            e.printStackTrace();
            result=0;
            return result;
        }
        return result;
    }

    @PostMapping("/fselectById")
    public Information selectById(String informationId, String userId){

        return infoService.selectByInformationId(informationId,userId);
    }
    @PostMapping("/fgs")
    public void delInformation(String informationId){
        infoService.delInformation(informationId);
    }

    @GetMapping("/getAll")
    public List<Information> getAll() {
        List<Information> list = infoService.getAll();
        return list;
    }

    @PostMapping("/addFabulous")
    public  int addFabulous(@RequestBody Information information){
        int result=0;

        try {
            infoService.addFabulous(information);
            result = 1;
        }catch (RuntimeException e){
            result = 0;
            return result;
        }
        return  result;
    }

    @PostMapping("/addApi")
    public void updateInformationAgree(@RequestBody Information information){

        infoService.updateInformationAgree(information);
    }

    @PostMapping("/addPointRecord")
    public int addPointRecord(@RequestBody PointRecord pointRecord){

        int result = 0;
        try {

            pointRecord.setPointRecordId(UUID.randomUUID().toString().replace("-", ""));
            pointRecord.setStatus((short)1);
            pointRecord.setVersion(1);
            pointRecord.setCreateTime(new Date().getTime());
            pointRecordService.addPointRecord(pointRecord);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }

    @PostMapping("/selectByUserId")
    public PointRecord selectByUserId(String userId, String informationId){

        return pointRecordService.selectByUserId(userId, informationId);
    }

    @GetMapping("/searchInfoList")
    public PageInfo<Information> searchInfoList(int pageNum, int pageSize, String title){
        return infoService.getPage(pageNum,pageSize,title);
    }

    @GetMapping("/searchInformationList")
    public List<Information> searchInformationList(int pageNum, int pageSize, String title){
        return infoService.searchInfoList(pageNum,pageSize,title);
    }

    @GetMapping("/selectByInfoId")
    public Information selectByInfoId(String informationId){
        return  infoService.selectByInfoId(informationId);
    }

    @PostMapping("/updateAgreeNum")
    public int updateAgreeNum(@RequestBody Information information){

        int result = 0;
        try {
            infoService.updateAgreeNum(information);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }
    @GetMapping("/selectInformById")
    public Information selectInformById(String informationId){
        return infoService.selectInformById(informationId);
    }

    @PostMapping("/updateContent")
    public int updateContent(@RequestBody Information information){

        int result = 0;
        try {
            information.setContent(information.getContent());
            infoService.updateContent(information);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }
    @PostMapping("/updateCommNum")

    public  int updateCommentNum(@RequestBody Information information){
        int result =0;
        try{
            infoService.updateCommentNum(information);
      result=1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result=0;
            return result;
        }
       return result;
    }

    @PostMapping("/updateTitle")
    public int updateTitle(@RequestBody Information information){
        int result = 0;
        try {
            infoService.updateTitle(information);
            result = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            result = 0;
            return result;
        }
        return result;
    }
}

