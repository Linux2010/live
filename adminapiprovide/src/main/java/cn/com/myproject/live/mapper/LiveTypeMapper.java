package cn.com.myproject.live.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liyang-macbook on 2017/7/5.
 */
@Mapper
public interface LiveTypeMapper {
    String getTypeName(String typeId);

}
