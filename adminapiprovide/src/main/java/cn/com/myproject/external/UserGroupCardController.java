package cn.com.myproject.external;

import cn.com.myproject.live.entity.PO.UserGroupCard;
import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import cn.com.myproject.user.service.IUserGroupCardService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡服务控制器类
 */
@RestController
@RequestMapping("/userGroupCard")
public class UserGroupCardController {

    @Autowired
    private IUserGroupCardService iUserGroupCardService;

    @PostMapping("/addUgc")
    public boolean addUgc(int cardNum,int cardType,String userId){
        UserGroupCard userGroupCard = new UserGroupCard();
        userGroupCard.setCardType(cardType);
        userGroupCard.setUserId(userId);
        boolean flagVal = true;
        if(cardNum > 0){
            for(int i = 0;i < cardNum;i++){
                if(!iUserGroupCardService.addUgc(userGroupCard)){
                    flagVal = false;
                }
            }
        }
        return flagVal;
    }

    @PostMapping("/removeUgcById")
    public boolean removeUgcById(int id){
        return iUserGroupCardService.removeUgcById(id);
    }

    @PostMapping("/searchUgcList")
    public PageInfo<UserGroupCardVO> searchUgcList(int pageNum, int pageSize, String userId, String cardNo, int cardType, int status){
        return iUserGroupCardService.searchUgcList(pageNum,pageSize,userId,cardNo,cardType,status);
    }

    @PostMapping("/searchExportUgcList")
    public List<UserGroupCardVO> searchExportUgcList(String userId, String cardNo, int cardType, int status){
        return iUserGroupCardService.searchExportUgcList(userId,cardNo,cardType,status);
    }

    @PostMapping("/activationGroupCard")
    public boolean activationGroupCard(@RequestBody UserGroupCardVO userGroupCardVO){
        return iUserGroupCardService.activationGroupCard(userGroupCardVO);
    }

    @PostMapping("/getUserGroupCard")
    public UserGroupCardVO getUserGroupCard(@RequestBody UserGroupCard userGroupCard){
        return iUserGroupCardService.getUserGroupCard(userGroupCard);
    }

}