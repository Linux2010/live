package cn.com.myproject.admincon.mapper;


import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProfitShareRelationMapper {

    int deleteByPrimaryKey(String relationId);

    int insert(ProfitShareRelation record);

    int insertSelective(ProfitShareRelation record);

    ProfitShareRelation selectByPrimaryKey(String relationId);

    ProfitShareRelation selectByUserId(String userId);

    ProfitShareRelation selectMinusProfitRalationByUserId(@Param("userId") String userId);

    int updateByPrimaryKeySelective(ProfitShareRelation record);

    int updateByPrimaryKey(ProfitShareRelation record);

    List<ProfitShareRelation> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<ProfitShareRelation> getRelationList(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("map") Map<String, Object> map);

    List<ProfitShareRelation> getRelationU(@Param("userId")String userId);

    List<ProfitShareRelation> getRelationD(@Param("userId")String userId);

    List<ProfitShareRelation> getRelationListByMap(Map<String,Object> map);

    ProfitShareRelation getUpLevelUser(@Param("userId")String userId);

    List<ProfitShareRelation> getSecondLevelUser(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("userId")String userId);

    List<ProfitShareRelation> getThreeLevelUser(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize,@Param("userId")String userId);

    Integer getThreeLevelUserCount(String userId);

    List<ProfitShareRelation> getSecondLevelUserAll(@Param("userId")String userId);

}