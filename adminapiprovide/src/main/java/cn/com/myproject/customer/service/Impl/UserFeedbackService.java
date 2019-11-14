package cn.com.myproject.customer.service.Impl;


import cn.com.myproject.customer.entity.PO.UserFeedback;
import cn.com.myproject.customer.entity.VO.UserFeedbackVO;
import cn.com.myproject.customer.mapper.UserFeedbackMapper;
import cn.com.myproject.customer.service.IUserFeedbackService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther CQC
 * @create 2017.8.15
 */
@Service
public class UserFeedbackService implements IUserFeedbackService {

    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    @Override
    public int deleteByPrimaryKey(String businessId) {
        return userFeedbackMapper.deleteByPrimaryKey(businessId);
    }

    @Override
    public int insert(UserFeedback record) {
        return userFeedbackMapper.insert(record);
    }

    @Override
    public int insertSelective(UserFeedback record) {
        return userFeedbackMapper.insertSelective(record);
    }

    @Override
    public UserFeedback selectByPrimaryKey(String businessId) {
        return userFeedbackMapper.selectByPrimaryKey(businessId);
    }

    @Override
    public PageInfo<UserFeedbackVO> getPage(int pageNum, int pageSize, String keyword) {
        List<UserFeedback> userFeedbackList = userFeedbackMapper.getPage(pageNum,pageSize,keyword);
        return convert(userFeedbackList);
    }

    @Override
    public int updateByPrimaryKeySelective(UserFeedback record) {
        return userFeedbackMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserFeedback record) {
        return userFeedbackMapper.updateByPrimaryKey(record);
    }


    private PageInfo<UserFeedbackVO> convert(List<UserFeedback> list) {
        PageInfo<UserFeedback> info = new PageInfo(list);
        List<UserFeedback> _list = info.getList();
        info.setList(null);
        List<UserFeedbackVO> __list = new ArrayList<>(10);

        PageInfo<UserFeedbackVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(UserFeedback userFeedback : _list) {
                __list.add(new UserFeedbackVO(userFeedback));
            }
            _info.setList(__list);
        }
        return _info;
    }
}
