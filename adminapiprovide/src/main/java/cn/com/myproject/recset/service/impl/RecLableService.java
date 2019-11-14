package cn.com.myproject.recset.service.impl;

import cn.com.myproject.recset.entity.PO.RecLable;
import cn.com.myproject.recset.entity.PO.RecSettings;
import cn.com.myproject.recset.mapper.RecLableMapper;
import cn.com.myproject.recset.service.IRecLableService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JYP on 2017/8/17 0017.
 */
@Service
public class RecLableService implements IRecLableService {

    @Autowired
    private RecLableMapper recLableMapper;

    @Override
    public PageInfo<RecLable> getPage(int pageNum, int pageSize) {
        List<RecLable> list = recLableMapper.getPage(pageNum, pageSize);
        return convert(list);
    }

    private PageInfo<RecLable> convert(List<RecLable> list) {
        PageInfo<RecLable> info = new PageInfo(list);

        List<RecLable> _list = info.getList();
        info.setList(null);
        List<RecLable> __list = new ArrayList<>(10);

        PageInfo<RecLable> _info = new PageInfo();
        BeanUtils.copyProperties(info, _info);
        if (null != _list && _list.size() != 0) {
            for (RecLable rlab : _list) {
                __list.add(rlab);
            }
            _info.setList(__list);
        }

        return _info;
    }
}
