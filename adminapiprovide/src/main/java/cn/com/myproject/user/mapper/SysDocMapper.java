package cn.com.myproject.user.mapper;

import cn.com.myproject.live.entity.PO.SysDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/*
 * Created by pangdatao on 2017-10-16
 * desc：系统文案Mapper接口
 */
@Mapper
public interface SysDocMapper {

    /**
     * 查询系统文案列表
     *
     * @param pageNum
     * @param pageSize
     * @param docType
     * @return
     */
    List<SysDoc> selectSdList(@Param("pageNumKey") int pageNum,
                              @Param("pageSizeKey") int pageSize,
                              @Param("docType") int docType);

    /**
     * 根据文案类型查询系统文案内容
     *
     * @param docType
     * @return
     */
    String selectSdcByType(@Param("docType") int docType);

    /**
     * 根据ID修改系统文案内容
     *
     * @param sysDoc
     * @return
     */
    int updateSdc(SysDoc sysDoc);

}