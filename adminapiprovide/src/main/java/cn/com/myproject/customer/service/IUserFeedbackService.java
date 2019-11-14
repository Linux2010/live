package cn.com.myproject.customer.service;

import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.UserFeedbackVO;
import com.github.pagehelper.PageInfo;

/**
 * @auther CQC
 * @create 2017.8.18
 */
public interface IUserFeedbackService {

    int deleteByPrimaryKey(String businessId);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(String businessId);

    PageInfo<UserFeedbackVO> getPage(int pageNum, int pageSize,String keyword);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);

}
