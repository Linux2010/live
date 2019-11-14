package cn.com.myproject.api.wap;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.user.IUserGroupCardService;
import cn.com.myproject.api.util.DateUtils;
import cn.com.myproject.api.util.Message;
import cn.com.myproject.api.util.MessageUtils;
import cn.com.myproject.live.entity.PO.UserGroupCard;
import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import cn.com.myproject.user.entity.PO.User;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Created by LeiJia on 2017/10/11
 */
@RestController
@RequestMapping("/wap/userGroupCard")
@Api(value = "用户团购卡类", tags = "用户团购卡激活")
public class WXUserGroupCardController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(WXUserGroupCardController.class);

    @Autowired
    private IUserGroupCardService iUserGroupCardService;

    @Autowired
    private IUserService userService;
    /**
     * 激活团卡页面
     * @return
     */
    @RequestMapping("/my/activeCode")
    public ModelAndView activeCode(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/my/activeCode");
        return view;
    }
    /**
     * 激活用户团购卡
     * @param cardNo 团购卡号
     * @param cardPassword 团购卡密码
     * @return
     */
    @RequestMapping(value = "/activationGroupCard", method = RequestMethod.POST, produces = "application/json")
    public Message activationGroupCard(String cardNo,String cardPassword) {
            String userId="";
            User currUser = getCurrUser();
            if(currUser!=null){
                userId = currUser.getUserId();
            }
            try {
                //校验用户是否是vip用户
                User user = userService.selectById(userId);

                if(user == null){
                    return MessageUtils.getFail("非法会员ID!");
                }
                if(user.getUserIdentity() == 2 ){//用户身份1:普通用户2：会员用户
                    return MessageUtils.getFail("已是VIP用户，无法激活该卡!");
                }
                if (StringUtils.isBlank(cardNo)) {
                    return MessageUtils.getFail("团购卡号不能为空!");
                }
                if (StringUtils.isBlank(cardPassword)) {
                    return MessageUtils.getFail("团购卡密码不能为空!");
                }
                //校验卡号和密码
                UserGroupCard userGroupCard = new UserGroupCard();
                userGroupCard.setCardNo(cardNo);
                userGroupCard.setCardPassword(cardPassword);
                UserGroupCardVO userGroupCardVO = iUserGroupCardService.getUserGroupCard(userGroupCard);

                if(userGroupCardVO == null){
                    return  MessageUtils.getFail("卡号或密码不正确！");
                }
                if(userGroupCardVO.getUserId().equals(userId)){
                    return  MessageUtils.getFail("不能激活自己的团购卡！");
                }

                //校验团购卡保质期(团购卡保质期3年）
                if((3*12*31 - DateUtils.betwwenDaysTwoCalendarTimeInMillis(userGroupCardVO.getCreateTime(), Calendar.getInstance().getTimeInMillis())) < 0){
                    return  MessageUtils.getFail("该卡保质期已过！");
                }

                if(userGroupCardVO.getStatus() == 1 || StringUtils.isNotEmpty(userGroupCardVO.getActiveUserId()) || StringUtils.isNotBlank(userGroupCardVO.getActiveUserId())){ //是否激活：0代表未激活，1代表已激活
                    //校验有效期(月卡有效期31天，年卡有效期12*31天）
                    if(userGroupCardVO.getCardType() == 1 && userGroupCardVO.getActiveTime() !=0){ //激活卡类型：1代表年卡，2代表月卡
                        if((1*12*31 - DateUtils.betwwenDaysTwoCalendarTimeInMillis(userGroupCardVO.getActiveTime(), Calendar.getInstance().getTimeInMillis()) )>= 0){
                           if(userId.equals(userGroupCardVO.getActiveUserId())){
                               return  MessageUtils.getFail("有年卡未使用完,不可激活该卡片！");
                           }else{
                               return  MessageUtils.getFail("该激活码已经被使用过！");
                           }
                        }
                    }else if(userGroupCardVO.getCardType() == 2 && userGroupCardVO.getActiveTime() !=0){
                        if((31- DateUtils.betwwenDaysTwoCalendarTimeInMillis(userGroupCardVO.getActiveTime(), Calendar.getInstance().getTimeInMillis())) >= 0){
                            if(userId.equals(userGroupCardVO.getActiveUserId())) {
                                return MessageUtils.getFail("有月卡未使用完,不可激活该卡片！");
                            }else{
                                    return  MessageUtils.getFail("该激活码已经被使用过！");
                             }
                        }
                    }else{
                        return  MessageUtils.getFail("改卡已激活，请勿重复激活！");
                    }
                }


                //校验用户已有团购卡,团购卡质保期与有效期是否过期
                UserGroupCard old_userGroupCard = new UserGroupCard();
                old_userGroupCard.setActiveUserId(userId);
                old_userGroupCard.setStatus((short)1); ////是否激活：0代表未激活，1代表已激活
                UserGroupCardVO old_userGroupCardVO = iUserGroupCardService.getUserGroupCard( old_userGroupCard);
                if(old_userGroupCardVO != null){
                    //校验团购卡保质期(团购卡保质期3年）
                    if(3*12*31 - DateUtils.betwwenDaysTwoCalendarTimeInMillis(old_userGroupCardVO.getCreateTime(), Calendar.getInstance().getTimeInMillis()) >= 0){ //在3年质保期内
                        //校验有效期(月卡有效期31天，年卡有效期12*31天）
                        if(old_userGroupCardVO.getCardType() == 1){ //激活卡类型：1代表年卡，2代表月卡
                            if((1*12*31 - DateUtils.betwwenDaysTwoCalendarTimeInMillis(old_userGroupCardVO.getActiveTime(), Calendar.getInstance().getTimeInMillis()) )>= 0){
                                return  MessageUtils.getFail("有年卡未使用完,不可激活该卡片！");
                            }
                        }else if(old_userGroupCardVO.getCardType() == 2){
                            if((31- DateUtils.betwwenDaysTwoCalendarTimeInMillis(old_userGroupCardVO.getActiveTime(), Calendar.getInstance().getTimeInMillis())) >= 0){
                                return  MessageUtils.getFail("有月卡未使用完,不可激活该卡片！");
                            }
                        }
                    }

                }



                //激活操作
                userGroupCardVO.setUserId(userGroupCardVO.getUserId()); //团购卡主ID
                userGroupCardVO.setActiveUserId(userId); //激活者ID
                boolean flag =  iUserGroupCardService.activationGroupCard(userGroupCardVO);
                if(!flag){
                    return MessageUtils.getFail("激活用户团购卡失败");
                }else{
                    return MessageUtils.getSuccess("success");
                }
            } catch (Exception e) {
                logger.info("激活用户团购卡失败:" + e.getMessage());
                return MessageUtils.getFail("激活用户团购卡失败");
            }
    }
}