package cn.com.myproject.rechargeMember.service;

import cn.com.myproject.user.entity.PO.RechargeMember;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2017/8/29 0007.
 */
public interface IRechargeMemberService {

    PageInfo<RechargeMember> selectAll(int pageNum, int pageSize);

    RechargeMember selectByUserId(String userId);

    RechargeMember selectByRechargeMemberId(String rechargeMemberId);

    int addRechargeMember(RechargeMember rechargeMember);

    void updateRechargeMember(String rechargeMemberId);

    void delByRechargeMemberId(String rechargeMemberId);

    RechargeMember selectNewByUserId(String userId);

    int setPayFinishRechargeMemberByOrderOn(String orderNo,String transactionI);

    RechargeMember selectByOrderOn(String orderNo);
}
