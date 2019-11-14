package cn.com.myproject.logi.mapper;

import cn.com.myproject.logi.entity.PO.LogiCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by jyp on 2017/9/13 0013.
 */
@Mapper
public interface LogiCompanyMapper {

    List<LogiCompany> getLogiList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    LogiCompany selectById(@Param("logiId") String logiId);

    void addlogi(LogiCompany logiCompany);

    void updatelogi(LogiCompany logiCompany);

    void dellogi(@Param("logiId") String logiId);

    List<LogiCompany> logiList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    LogiCompany selectComByCode(@Param("code") String code);

    /**
     * 查询快递公司列表
     *
     * @return
     */
    List<LogiCompany> searchLogiList();

}