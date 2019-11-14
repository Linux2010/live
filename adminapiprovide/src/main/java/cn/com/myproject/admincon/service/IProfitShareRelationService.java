package cn.com.myproject.admincon.service;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.8.15
 */
public interface IProfitShareRelationService {

    int deleteByPrimaryKey(String relationId);

    int insert(ProfitShareRelation record);

    int insertParentProfitShareSelective(ProfitShareRelation record);

    int insertSelective(ProfitShareRelation record);

    ProfitShareRelation selectByPrimaryKey(String relationId);

    ProfitShareRelation selectByUserId(String userId);

    ProfitShareRelation selectMinusProfitRalationByUserId( String userId);

    int updateByPrimaryKeySelective(ProfitShareRelation record);

    int updateByPrimaryKey(ProfitShareRelation record);

    PageInfo<ProfitShareRelationVO> getPage(int pageNum, int pageSize);

    PageInfo<ProfitShareRelationVO> getRelationPage(int pageNum, int pageSize,Map<String, Object> map);

    List<ProfitShareRelation> getRelationU(String userId);

    List<ProfitShareRelation> getRelationD(String userId);

    List<ProfitShareRelation> getRelationListByMap(Map<String,Object> map);

    ProfitShareRelation getUpLevelUser( String userId);

    List<ProfitShareRelation> getSecondLevelUser( int pageNum,int pageSize,String userId);

    List<ProfitShareRelation> getThreeLevelUser( int pageNum,int pageSize,String userId);

    int addProfitShareRelation(String referrerId,String registerId);

    Integer getThreeLevelUserCount(String userId);

    List<ProfitShareRelation> getSecondLevelUserAll(String userId);

    Map<String,Object> getSecondLevelUserMap( int pageNum,int pageSize,String userId);

   int updateParentRaltion(ProfitShareRelation profitShareRelation);

}
