package cn.com.myproject.user.mapper;

import cn.com.myproject.live.entity.PO.UserGroupCard;
import cn.com.myproject.live.entity.VO.UserGroupCardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-10-09
 * desc：用户团购卡Mapper接口
 */
@Mapper
public interface UserGroupCardMapper {

    /**
     * 插入用户团购卡信息
     *
     * @param userGroupCard
     * @return
     */
    int insertUgc(UserGroupCard userGroupCard);

    /**
     * 插入用户团购卡数量信息
     *
     * @param cardNum
     * @return
     */
    int insertUgcn(@Param("cardNum") int cardNum);

    /**
     * 根据ID删除用户团购卡信息
     *
     * @param id
     * @return
     */
    int deleteUgcById(int id);

    /**
     * 修改用户团购卡状态
     *
     * @param userGroupCard
     * @return
     */
    int updateUgcStatus(UserGroupCard userGroupCard);

    /**
     * 更新用户团购卡数量信息
     *
     * @param cardNum
     * @return
     */
    int updateUgcn(@Param("cardNum") int cardNum);

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
    List<UserGroupCardVO> selectUgcList(@Param("pageNumKey") int pageNum,
                                        @Param("pageSizeKey") int pageSize,
                                        @Param("userId") String userId,
                                        @Param("cardNo") String cardNo,
                                        @Param("cardType") int cardType,
                                        @Param("status") int status);

    /**
     * 查询用户团购卡导出列表信息
     *
     * @param userId
     * @param cardNo
     * @param cardType
     * @param status
     * @return
     */
    List<UserGroupCardVO> selectExportUgcList(@Param("userId") String userId,
                                              @Param("cardNo") String cardNo,
                                              @Param("cardType") int cardType,
                                              @Param("status") int status);

    /**
     * 查询用户团购卡数量信息
     *
     * @return
     */
    Integer selectUgcn();


    /**
     * 查找团卡信息
     * @param userGroupCard
     * @return
     */
    UserGroupCardVO getUserGroupCard(UserGroupCard userGroupCard);

}