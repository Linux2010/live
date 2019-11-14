package cn.com.myproject.admincon.service.impl;

import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import cn.com.myproject.admincon.mapper.ProfitShareSettingMapper;
import cn.com.myproject.admincon.service.IProfitShareSettingService;
import com.github.pagehelper.Page;
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
public class ProfitShareSettingService implements IProfitShareSettingService{

    @Autowired
    private ProfitShareSettingMapper profitShareSettingMapper;

    @Override
    public int deleteByPrimaryKey(String setId) {
        return profitShareSettingMapper.deleteByPrimaryKey(setId);
    }

    @Override
    public int insert(ProfitShareSetting record) {
        return profitShareSettingMapper.insert(record);
    }

    @Override
    public int insertSelective(ProfitShareSetting record) {
        return profitShareSettingMapper.insertSelective(record);
    }

    @Override
    public ProfitShareSetting selectByPrimaryKey(String setId) {
        return profitShareSettingMapper.selectByPrimaryKey(setId);
    }

    @Override
    public PageInfo<ProfitShareSettingVO> getPage(int pageNum,int pageSize) {
        List<ProfitShareSetting> profitShareSettingList = profitShareSettingMapper.getPage(pageNum,pageSize);
        return convert(profitShareSettingList);
    }

    @Override
    public int updateByPrimaryKeySelective(ProfitShareSetting record) {
        return profitShareSettingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProfitShareSetting record) {
        return profitShareSettingMapper.updateByPrimaryKey(record);
    }


    private PageInfo<ProfitShareSettingVO> convert(List<ProfitShareSetting> list) {
        PageInfo<ProfitShareSetting> info = new PageInfo(list);
        List<ProfitShareSetting> _list = info.getList();
        info.setList(null);
        List<ProfitShareSettingVO> __list = new ArrayList<>(10);

        PageInfo<ProfitShareSettingVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(ProfitShareSetting profitShareSetting : _list) {
                __list.add(new ProfitShareSettingVO(profitShareSetting));
            }
            _info.setList(__list);
        }
        return _info;
    }
}
