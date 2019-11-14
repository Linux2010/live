package cn.com.myproject.user.service;

import cn.com.myproject.live.entity.PO.SysDoc;
import com.github.pagehelper.PageInfo;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案Service接口
 */
public interface ISysDocService {

    /**
     * 查询系统文案列表
     *
     * @param pageNum
     * @param pageSize
     * @param docType
     * @return
     */
    PageInfo<SysDoc> searchSdList(int pageNum, int pageSize, int docType);

    /**
     * 根据文案类型查询系统文案内容
     *
     * @param docType
     * @return
     */
    String searchSdcByType(int docType);

    /**
     * 根据ID修改系统文案内容
     *
     * @param sysDoc
     * @return
     */
    boolean modifySdc(SysDoc sysDoc);

}