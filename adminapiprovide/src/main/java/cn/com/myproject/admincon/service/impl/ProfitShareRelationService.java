package cn.com.myproject.admincon.service.impl;

import cn.com.myproject.admincon.entity.PO.ProfitShareRelation;
import cn.com.myproject.admincon.entity.PO.ProfitShareSetting;
import cn.com.myproject.admincon.entity.VO.ProfitShareRelationVO;
import cn.com.myproject.admincon.entity.VO.ProfitShareSettingVO;
import cn.com.myproject.admincon.mapper.ProfitShareRelationMapper;
import cn.com.myproject.admincon.mapper.ProfitShareSettingMapper;
import cn.com.myproject.admincon.service.IProfitShareRelationService;
import cn.com.myproject.admincon.service.IProfitShareSettingService;
import cn.com.myproject.aliyun.push.AliyunPushService;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.customer.mapper.MessageRecordMapper;
import cn.com.myproject.customer.service.Impl.MessageRecordService;
import cn.com.myproject.live.entity.PO.Course;
import cn.com.myproject.live.entity.PO.CourseOrder;
import cn.com.myproject.live.entity.PO.CourseType;
import cn.com.myproject.user.entity.PO.User;
import cn.com.myproject.user.service.impl.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @auther CQC
 * @create 2017.8.15
 */
@Service
public class ProfitShareRelationService implements IProfitShareRelationService{
    private static final Logger logger = LoggerFactory.getLogger(ProfitShareRelationService.class);

    @Autowired
    private ProfitShareRelationMapper profitShareRelationMapper;

    @Autowired
    private MessageRecordService messageRecordService;

    @Autowired
    private AliyunPushService aliyunPushService;

    @Autowired
    private UserService userService;

    @Override
    public int deleteByPrimaryKey(String relationId) {
        return profitShareRelationMapper.deleteByPrimaryKey(relationId);
    }

    @Override
    public int insert(ProfitShareRelation record) {
        ProfitShareRelation profitShareRelation = profitShareRelationMapper.selectByUserId(record.getUserId());
        if(null != profitShareRelation){ //数据已经存在返回-1   （一个会员只能是某个会员的下级）
            return -1;
        }
        return profitShareRelationMapper.insert(record);
    }
    @Override
    public int insertParentProfitShareSelective(ProfitShareRelation record) {
        int insertSelective = profitShareRelationMapper.insertSelective(record);
        if(insertSelective < 1){
            logger.error("添加新的分润关系失败profitShareRelationMapper.insertSelective(record)");
            throw new RuntimeException("添加新的分润关系失败profitShareRelationMapper.insertSelective(record)");
        }
        return insertSelective;
    }

    @Override
    public int insertSelective(ProfitShareRelation record) {
        ProfitShareRelation profitShareRelation = profitShareRelationMapper.selectByUserId(record.getUserId());
        if(null != profitShareRelation){ //数据已经存在返回-1   （一个会员只能是某个会员的下级）
            return -1;
        }
        return profitShareRelationMapper.insertSelective(record);
    }

    @Override
    public ProfitShareRelation selectByPrimaryKey(String relationId) {
        return profitShareRelationMapper.selectByPrimaryKey(relationId);
    }

    @Override
    public ProfitShareRelation selectByUserId(String userId) {
        return profitShareRelationMapper.selectByUserId(userId);
    }
    @Override
    public  ProfitShareRelation selectMinusProfitRalationByUserId( String userId){
        return profitShareRelationMapper.selectMinusProfitRalationByUserId(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(ProfitShareRelation record) {
        return profitShareRelationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProfitShareRelation record) {
        return profitShareRelationMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<ProfitShareRelationVO> getPage(int pageNum, int pageSize) {
        List<ProfitShareRelation> profitShareRelation = profitShareRelationMapper.getPage(pageNum,pageSize);
        return convert(profitShareRelation);
    }

    @Override
    public PageInfo<ProfitShareRelationVO> getRelationPage(int pageNum, int pageSize,Map<String, Object> map) {
        List<ProfitShareRelation> profitShareRelation = profitShareRelationMapper.getRelationList(pageNum,pageSize,map);
        return convert(profitShareRelation);
    }

    @Override
    public List<ProfitShareRelation> getRelationU(String userId) {
        return profitShareRelationMapper.getRelationU(userId);
    }

    @Override
    public List<ProfitShareRelation> getRelationD(String userId) {
        return profitShareRelationMapper.getRelationD(userId);
    }

    @Override
    public List<ProfitShareRelation> getRelationListByMap(Map<String, Object> map) {
        return profitShareRelationMapper.getRelationListByMap(map);
    }

    @Override
    public ProfitShareRelation getUpLevelUser(String userId) {
        return profitShareRelationMapper.getUpLevelUser(userId);
    }

    @Override
    public List<ProfitShareRelation> getSecondLevelUser(int pageNum,int pageSize,String userId) {
        return profitShareRelationMapper.getSecondLevelUser(pageNum,pageSize,userId);
    }
    @Override
    public Map<String,Object> getSecondLevelUserMap( int pageNum,int pageSize,String userId){
        Map<String,Object> data = new HashMap<>();
        List<ProfitShareRelation>  list = profitShareRelationMapper.getSecondLevelUser(pageNum,pageSize,userId);
        PageInfo<ProfitShareRelation> pageInfo = new PageInfo<ProfitShareRelation>( list );
        data.put("list",list);
        data.put("pageNum",pageNum);
        data.put("pageSize",pageSize);
        data.put("total",pageInfo.getTotal());
        return data;
    }

    @Override
    public List<ProfitShareRelation> getThreeLevelUser(int pageNum, int pageSize, String userId) {
        return profitShareRelationMapper.getThreeLevelUser(pageNum,pageSize,userId);
    }

    @Override
    @Transactional
    public int addProfitShareRelation(String referrerId, String registerId) {

        if(StringUtils.isBlank(registerId)){
            return 0;
        }

        int result = 0;

        ProfitShareRelation regRelation = new ProfitShareRelation();
        regRelation.setStatus((short)1);
        regRelation.setVersion(1);
        regRelation.setCreateTime(new Date().getTime());
        regRelation.setRelationId(UUID.randomUUID().toString().replace("-",""));
        regRelation.setUserId(registerId);
        regRelation.setParentUserId("-1");
        result = profitShareRelationMapper.insertSelective(regRelation);

        if(StringUtils.isNotBlank(referrerId)){
            regRelation = new ProfitShareRelation();
            regRelation.setStatus((short)1);
            regRelation.setVersion(1);
            regRelation.setCreateTime(new Date().getTime());
            regRelation.setRelationId(UUID.randomUUID().toString().replace("-",""));
            regRelation.setUserId(registerId);
            regRelation.setParentUserId(referrerId);
            result = profitShareRelationMapper.insertSelective(regRelation);
            if(result>0){
                pushRelationMessage(registerId,referrerId);
            }
        }
        return result;

    }

    @Override
    public Integer getThreeLevelUserCount(String userId) {
        return profitShareRelationMapper.getThreeLevelUserCount(userId);
    }

    @Override
    public List<ProfitShareRelation> getSecondLevelUserAll(String userId) {
        return profitShareRelationMapper.getSecondLevelUserAll(userId);
    }

    private PageInfo<ProfitShareRelationVO> convert(List<ProfitShareRelation> list) {

        PageInfo<ProfitShareRelation> info = new PageInfo(list);
        List<ProfitShareRelation> _list = info.getList();
        info.setList(null);
        List<ProfitShareRelationVO> __list = new ArrayList<>(10);
        PageInfo<ProfitShareRelationVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(ProfitShareRelation profitShareRelation : _list) {
                __list.add(new ProfitShareRelationVO(profitShareRelation));
            }
            _info.setList(__list);
        }
        return _info;
    }
    @Override
   public  int updateParentRaltion(ProfitShareRelation profitShareRelation){
        //判断所选父级分润关系，有无-1父级分润关系
        ProfitShareRelation minusProfitRalation = selectMinusProfitRalationByUserId(profitShareRelation.getParentUserId());
        if(minusProfitRalation == null){
            //添加-1父级分润关系
            ProfitShareRelation regRelation = new ProfitShareRelation();
            regRelation.setStatus((short)1);
            regRelation.setVersion(1);
            regRelation.setCreateTime(new Date().getTime());
            regRelation.setRelationId(UUID.randomUUID().toString().replace("-",""));
            regRelation.setUserId(profitShareRelation.getParentUserId());
            regRelation.setParentUserId("-1");
            int insertFlag   = insertParentProfitShareSelective(regRelation);
            if(insertFlag < 1){
                logger.error("添加新的分润关系失败profitShareRelationService.insert(new_profitShareRelation)");
                throw new RuntimeException("添加新的分润关系失败profitShareRelationService.insert(new_profitShareRelation)");
            }
        }
        ProfitShareRelation old_profitShareRelation = selectByUserId(profitShareRelation.getUserId()); //查询用户分润关系parent不为-1的上下级分润关系
        if(old_profitShareRelation != null){
            //删除旧的分润关系
            int deleteFlag = deleteByPrimaryKey(old_profitShareRelation.getRelationId());
            if(deleteFlag < 1){
                logger.error("根据分润关系ID删除激活者旧的分润关系失败profitShareRelationService.deleteByPrimaryKey(old_profitShareRelation.getRelationId())");
                throw new RuntimeException("根据分润关系ID删除激活者旧的分润关系失败profitShareRelationService.deleteByPrimaryKey(old_profitShareRelation.getRelationId())");
            }
        }

        //添加新的分润关系
        ProfitShareRelation new_profitShareRelation = new ProfitShareRelation();
        new_profitShareRelation.setRelationId(UUID.randomUUID().toString().replace("-", ""));
        new_profitShareRelation.setUserId(profitShareRelation.getUserId()); //用户ID
        new_profitShareRelation.setParentUserId(profitShareRelation.getParentUserId()); //上级分润用户ID
        new_profitShareRelation.setNo(1); //序号
        new_profitShareRelation.setCreateTime(Calendar.getInstance().getTimeInMillis());
        new_profitShareRelation.setStatus((short)1);
        new_profitShareRelation.setVersion(1);
        int insertFlag =  insert(new_profitShareRelation);
        if(insertFlag < 1){
            logger.error("添加新的分润关系失败profitShareRelationService.insert(new_profitShareRelation)");
            throw new RuntimeException("添加新的分润关系失败profitShareRelationService.insert(new_profitShareRelation)");
        }else{
            pushRelationMessage(profitShareRelation.getUserId(),profitShareRelation.getParentUserId());
        }
        return insertFlag;
   }

    private void pushRelationMessage(String userId,String parentUserId){
        User user = userService.selectById(userId);
        if(null != user){
            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setTitle("我的团队");
            messageRecord.setIntro("用户名为"+user.getLoginName()+"的会员，已成为您的下线");
            messageRecord.setContent("用户名为"+user.getLoginName()+"的会员，已成为您的下线");
            messageRecord.setSendUserId("-1");
            messageRecord.setClassify((short)2);
            messageRecord.setMessageType((short)1);
            messageRecord.setReceiveUserId(parentUserId);
            messageRecord.setRelationId(parentUserId);
            messageRecord.setRelationType((short)4);
            messageRecord.setCreateTime(new Date().getTime());
            messageRecord.setMessageId(UUID.randomUUID().toString().replace("-",""));
            messageRecord.setStatus((short)1);
            messageRecord.setVersion(1);
            messageRecord.setMessageStatus((short)0);
            messageRecordService.insert(messageRecord);

            //发送通知
            aliyunPushService.pushNotice(messageRecord.getTitle(),messageRecord.getIntro(),messageRecord.getReceiveUserId(),"{\"type\":\"relationPush\"");

        }
    }
}
