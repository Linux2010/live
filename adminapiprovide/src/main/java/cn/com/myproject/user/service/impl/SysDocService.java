package cn.com.myproject.user.service.impl;

import cn.com.myproject.live.entity.PO.SysDoc;
import cn.com.myproject.user.mapper.SysDocMapper;
import cn.com.myproject.user.service.ISysDocService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案Service接口实现类
 */
@Service
public class SysDocService implements ISysDocService{

    @Autowired
    private SysDocMapper sysDocMapper;

    /**
     * 查询系统文案列表
     *
     * @param pageNum
     * @param pageSize
     * @param docType
     * @return
     */
    @Override
    public PageInfo<SysDoc> searchSdList(int pageNum, int pageSize, int docType){
        List<SysDoc> sysDocList = sysDocMapper.selectSdList(pageNum,pageSize,docType);
        return convert(sysDocList);
    }

    /**
     * 根据文案类型查询系统文案内容
     *
     * @param docType
     * @return
     */
    @Override
    public String searchSdcByType(int docType){
        return sysDocMapper.selectSdcByType(docType);
    }

    /**
     * 根据ID修改系统文案内容
     *
     * @param sysDoc
     * @return
     */
    @Override
    @Transactional
    public boolean modifySdc(SysDoc sysDoc){
        boolean flagVal = false;
        if(sysDoc != null){
            sysDoc.setCreateTime(new Date().getTime());// 设置修改时间为当前时间
        }
        int countVal = sysDocMapper.updateSdc(sysDoc);
        if(countVal > 0){
            flagVal = true;
        }
        return flagVal;
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<SysDoc> convert(List<SysDoc> list) {
        PageInfo<SysDoc> info = new PageInfo(list);
        List<SysDoc> _list = info.getList();
        info.setList(null);
        List<SysDoc> __list = new ArrayList<SysDoc>(10);
        PageInfo<SysDoc> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(SysDoc sysDoc : _list) {
                __list.add(sysDoc);
            }
            _info.setList(__list);
        }
        return _info;
    }

}