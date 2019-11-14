package cn.com.myproject.recset.mapper;

import cn.com.myproject.recset.entity.PO.RecLable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jyp on 2017/8/17 0017.
 */
@Mapper
public interface RecLableMapper {

    List<RecLable> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);
}
