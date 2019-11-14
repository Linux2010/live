package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.GoodsComment;
import cn.com.myproject.goods.entity.PO.GoodsCommentReply;
import cn.com.myproject.goods.mapper.GoodsCommMapper;
import cn.com.myproject.goods.mapper.GoodsCommReplyMapper;
import cn.com.myproject.goods.service.IGoodsCommService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by 李延超 on 2017/9/25.
 */
@Service
public class GoodsCommService implements IGoodsCommService {
    private static final Logger logger = LoggerFactory.getLogger(GoodsCommService.class);

    @Autowired
    private GoodsCommMapper goodsCommMapper;

    @Autowired
    private GoodsCommReplyMapper goodsCommReplyMapper;

    @Override
    @Transactional
    public  boolean addComm(GoodsComment goodsComment){
       boolean flagVal=false;

    int countVal=   goodsCommMapper.addComm(goodsComment);
   if(countVal>0){
       flagVal=true;
   }
   return flagVal;
    }


    @Override
    public List<GoodsComment> searchGCList(int pageNum, int pageSize, String goodsId){
        return goodsCommMapper.searchGCList(pageNum,pageSize,goodsId);
    }

    @Transactional
    @Override
    public int removeGoodsComment(String goodsCommentId){

        int i = goodsCommMapper.removeGoodsComment(goodsCommentId);
        if(i<1){
            logger.error("删除商品评论失败goodsCommMapper.removeGoodsComment(goodsCommentId)");
            throw new RuntimeException("删除商品评论失败goodsCommMapper.removeGoodsComment(goodsCommentId)");
        }else{
            if(goodsCommReplyMapper.searchGoodsCommentReplyBygoodsCommentId(goodsCommentId) !=null && goodsCommReplyMapper.searchGoodsCommentReplyBygoodsCommentId(goodsCommentId).size() >0){
                //删除回复
                int j = goodsCommReplyMapper.delGoodsCommentReplyByGoodsCommentId(goodsCommentId);
                if(j<1){
                    logger.error("删除商品评论回复失败goodsCommReplyMapper.delGoodsCommentReplyByGoodsCommentId(goodsCommentId)");
                    throw new RuntimeException("删除商品评论回复失败goodsCommReplyMapper.delGoodsCommentReplyByGoodsCommentId(goodsCommentId)");
                }
            }

        }
        return i;
    }

    @Override
    public PageInfo<GoodsComment> searchGoodsCommentList(int pageNum,  int pageSize){
        List<GoodsComment> commentList =goodsCommMapper.searchGoodsCommentList(pageNum,pageSize) ;
        return  new  PageInfo<GoodsComment> (commentList);
    }

    @Override
    public List<GoodsCommentReply> searchGoodsCommentReplyBygoodsCommentId(String goodsCommentId){
        List<GoodsCommentReply> replyList =  goodsCommReplyMapper.searchGoodsCommentReplyBygoodsCommentId(goodsCommentId);
        return replyList;
    }

    @Override
    public GoodsComment searchGoodsCommentById(String goodsCommentId){
        return goodsCommMapper.searchGoodsCommentById(goodsCommentId);
    }

    @Transactional
    @Override
    public Integer addGoodsCommentReply(GoodsCommentReply goodsCommentReply){
        goodsCommentReply.setGoodsCommentReplyId(UUID.randomUUID().toString().replace("-", ""));
        goodsCommentReply.setReplyTime(Calendar.getInstance().getTimeInMillis());
        int result =  goodsCommReplyMapper.addGoodsCommentReply(goodsCommentReply);
        if(result<1){
            logger.error("商品评论回复失败goodsCommReplyMapper.addGoodsCommentReply(goodsCommentReply)");
            throw new RuntimeException("商品评论回复失败goodsCommReplyMapper.addGoodsCommentReply(goodsCommentReply)");
        }
        return result;
    }
}
