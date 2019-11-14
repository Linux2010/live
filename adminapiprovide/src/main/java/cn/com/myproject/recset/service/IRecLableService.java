package cn.com.myproject.recset.service;

import cn.com.myproject.recset.entity.PO.RecLable;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by JYP on 2017/8/17 0017.
 */
public interface IRecLableService {

    PageInfo<RecLable> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);
}
