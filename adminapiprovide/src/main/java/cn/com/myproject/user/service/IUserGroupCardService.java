package cn.com.myproject.user.service;

import cn.com.myproject.live.entity.PO.UserGroupCard;
import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡Service接口
 */
public interface IUserGroupCardService {

    /**
     * 生成用户团购卡
     *
     * @param userGroupCard
     * @return
     */
    boolean addUgc(UserGroupCard userGroupCard);

    /**
     * 根据ID删除用户团购卡
     *
     * @param id
     * @return
     */
    boolean removeUgcById(int id);

    /**
     * 更新用户团购卡状态为已激活
     *
     * @param userGroupCard
     * @return
     */
    boolean modifyUgcStatus(UserGroupCard userGroupCard);

    /**
     * 激活用户团购卡状态
     *
     * @param userGroupCardVO
     * @return
     */
    boolean activationGroupCard(UserGroupCardVO userGroupCardVO);

    /**
     * 查询用户团购卡列表信息
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @return
     */
    PageInfo<UserGroupCardVO> searchUgcList(int pageNum, int pageSize, String userId, String cardNo, int cardType, int status);

    /**
     * 查找团购卡信息
     *
     * @param userGroupCard
     * @return
     */
    UserGroupCardVO getUserGroupCard(UserGroupCard userGroupCard);

    /**
     * 查询用户团购卡导出列表信息
     *
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @return
     */
    List<UserGroupCardVO> searchExportUgcList(String userId, String cardNo, int cardType, int status);

}