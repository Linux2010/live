package cn.com.myproject.admincon.service;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @auther CQC
 * @create 2017.8.15
 */
public interface IProfitShareSettingService {

    int deleteByPrimaryKey(String setId);

    int insert(ProfitShareSetting record);

    int insertSelective(ProfitShareSetting record);

    ProfitShareSetting selectByPrimaryKey(String setId);

    PageInfo<ProfitShareSettingVO> getPage(int pageNum,int pageSize);

    int updateByPrimaryKeySelective(ProfitShareSetting record);

    int updateByPrimaryKey(ProfitShareSetting record);

}
