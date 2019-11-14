package cn.com.myproject.logi.service;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by jyp on 2017/9/13 0013.
 */
public interface ILogiCompanyService {

    PageInfo<LogiCompany> getLogiList(int pageNum, int pageSize);

    LogiCompany selectById(String logiId);

    void addlogi(LogiCompany logiCompany);

    void updatelogi(LogiCompany logiCompany);

    void dellogi(String logiId);

    List<LogiCompany> logiList(int pageNum, int pageSize);

    LogiCompany selectComByCode(String code);
}
