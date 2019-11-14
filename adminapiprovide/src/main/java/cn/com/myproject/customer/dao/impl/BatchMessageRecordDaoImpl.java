package cn.com.myproject.customer.dao.impl;

import cn.com.myproject.customer.dao.BatchMessageRecordDao;
import cn.com.myproject.customer.entity.PO.MessageRecord;
import cn.com.myproject.sysuser.dao.BatchSysUserRoleDao;
import cn.com.myproject.sysuser.entity.PO.SysUserRole;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/12.
 */
@Repository
public class BatchMessageRecordDaoImpl implements BatchMessageRecordDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void saveBatch(List<MessageRecord> list) {

        SqlSession session =sqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try{
            int i = 0;
            for(MessageRecord messageRecord : list){
                session.insert("cn.com.myproject.customer.mapper.MessageRecordMapper.insertSelective",messageRecord);
                i++;
                if (i % 500 == 0 ) {
                    session.commit();
                    session.clearCache();
                }
            }
            session.commit();
            //清理缓存，防止溢出
            session.clearCache();
        }catch(Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }
}
