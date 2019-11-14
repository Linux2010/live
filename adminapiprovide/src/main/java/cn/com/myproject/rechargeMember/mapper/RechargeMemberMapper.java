package cn.com.myproject.rechargeMember.mapper;

import cn.com.myproject.user.entity.PO.RechargeMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0007.
 */
@Mapper
public interface RechargeMemberMapper {


    List<RechargeMember> selectAll(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    RechargeMember selectByUserId(String userId);

    RechargeMember selectByRechargeMemberId(String rechargeMemberId);

    int addRechargeMember(RechargeMember rechargeMember);

    int updateRechargeMember(String rechargeMemberId);

    int delByRechargeMemberId(String rechargeMemberId);

    RechargeMember selectNewByUserId(String userId);

    int setPayFinishRechargeMemberByOrderOn(@Param("orderNo") String orderNo,@Param("transactionId") String transactionId);

    RechargeMember selectByOrderOn(String orderNo);

}
