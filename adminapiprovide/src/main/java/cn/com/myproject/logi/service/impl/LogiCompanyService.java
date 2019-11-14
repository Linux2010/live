package cn.com.myproject.logi.service.impl;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import cn.com.myproject.logi.mapper.LogiCompanyMapper;
import cn.com.myproject.logi.service.ILogiCompanyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jyp on 2017/9/13 0013.
 */
@Service
public class LogiCompanyService implements ILogiCompanyService {

    @Autowired
    private LogiCompanyMapper logiCompanyMapper;
    @Override
    public PageInfo<LogiCompany> getLogiList(int pageNum, int pageSize) {
        List<LogiCompany> list = this.logiCompanyMapper.getLogiList(pageNum,pageSize);
        return  new PageInfo(list);
    }

    @Override
    public LogiCompany selectById(String logiId) {
        return logiCompanyMapper.selectById(logiId);
    }

    @Override
    public void addlogi(LogiCompany logiCompany) {
        logiCompanyMapper.addlogi(logiCompany);
    }

    @Override
    public void updatelogi(LogiCompany logiCompany) {
        logiCompanyMapper.updatelogi(logiCompany);
    }

    @Override
    public void dellogi(String logiId) {
        logiCompanyMapper.dellogi(logiId);
    }

    @Override
    public List<LogiCompany> logiList(int pageNum, int pageSize) {
        return logiCompanyMapper.logiList(pageNum,pageSize);
    }

    @Override
    public LogiCompany selectComByCode(String code) {
        return logiCompanyMapper.selectComByCode(code);
    }
}
