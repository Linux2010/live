package cn.com.myproject.goods.service.impl;

import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.CouponClaimUser;
import cn.com.myproject.goods.entity.PO.CouponGoods;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.goods.mapper.CouponClaimUserMapper;
import cn.com.myproject.goods.mapper.CouponGoodsMapper;
import cn.com.myproject.goods.mapper.CouponMapper;
import cn.com.myproject.goods.service.ICouponService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by LeiJia on 2017/9/26 0021.
 */
@Service
public class CouponService implements ICouponService {

    @Autowired
    private CouponMapper couponMapper;


    @Autowired
    private CouponGoodsMapper couponGoodsMapper;

    @Autowired
    private CouponClaimUserMapper couponClaimUserMapper;

    @Transactional
    @Override
    public int addCoupon(Coupon coupon){
        int countVal = couponMapper.insertCoupon(coupon);
        return countVal;
    }

    @Transactional
    @Override
    public int removeCoupon(String couponId){
        int countVal = couponMapper.deleteCoupon(couponId);
        return countVal;
    }
    @Transactional
    @Override
    public int addCouponGoods(String ids,String couponId){
        int result = 1;
        String[] goodsIds ={};
        if(StringUtils.isNotBlank(ids)){
            goodsIds = ids.split(",");
        }
        for(String goodsId:goodsIds){
            CouponGoods couponGoods = new CouponGoods(couponId,goodsId);
            couponGoods = couponGoodsMapper.searchCouponGoodsDetail(couponGoods);
            if (couponGoods == null){ //没有找到该优惠劵商品
                couponGoods = new CouponGoods( UUID.randomUUID().toString().replace("-", ""),couponId,goodsId);
                couponGoodsMapper.insertCouponGoods(couponGoods);
            }else{ //找到该优惠劵商品，不再处理
                continue;
            }
        }
        return result;
    }
    @Transactional
    @Override
    public int removeCouponGoods(String ids,String couponId){
        int result = 1;
        String[] goodsIds ={};
        if(StringUtils.isNotBlank(ids)){
            goodsIds = ids.split(",");
        }
        for(String goodsId:goodsIds){
            CouponGoods couponGoods = new CouponGoods(couponId,goodsId);
            couponGoods = couponGoodsMapper.searchCouponGoodsDetail(couponGoods);
            if (couponGoods == null){ //没有找到该优惠劵商品，不再处理
                continue;
            }else{ //找到该优惠劵商品,执行删除该优惠商品操作
                couponGoodsMapper.deleteCouponGoods(couponGoods.getCouponGoodsId());
            }
        }
        return result;
    }

    @Transactional
    @Override
    public int addUserCoupon(String ids,String couponId){
        int result = 1;
        String[] userIds ={};
        if(StringUtils.isNotBlank(ids)){
            userIds = ids.split(",");
        }
        for(String userId:userIds){
            CouponClaimUser couponClaimUser = new CouponClaimUser(couponId,userId);
            couponClaimUser = couponClaimUserMapper.searchCouponClaimUserDetail(couponClaimUser);
            if (couponClaimUser == null){ //没有找到该优惠劵商品
                couponClaimUser = new CouponClaimUser( UUID.randomUUID().toString().replace("-", ""),couponId,userId);
                couponClaimUser.setClaimTime(Calendar.getInstance().getTimeInMillis());
                couponClaimUser.setIsClaim(0); //是否认领:0：未认领；1：认领了
                couponClaimUser.setStatus((short)1);
                couponClaimUser.setVersion(1);
                couponClaimUserMapper.insertCouponClaimUser(couponClaimUser);
            }else{ //找到该优惠劵商品，不再处理
                continue;
            }
        }
        return result;
    }
    @Transactional
    @Override
    public int cancelUserCoupon(String ids,String couponId){
        int result = 1;
        String[] userIds ={};
        if(StringUtils.isNotBlank(ids)){
            userIds = ids.split(",");
        }
        for(String userId:userIds){
            CouponClaimUser cuponClaimUser = new CouponClaimUser(couponId,userId);
            cuponClaimUser = couponClaimUserMapper.searchCouponClaimUserDetail(cuponClaimUser);
            if (cuponClaimUser == null){ //没有找到该用户的优惠劵，不再处理
                continue;
            }else{ //找到该用户的优惠劵,执行删除该用户的优惠劵操作
                couponClaimUserMapper.deleteCouponClaimUser(cuponClaimUser.getCouponClaimUserId());
            }
        }
        return result;
    }
    @Transactional
    @Override
    public int modifyCoupon(Coupon coupon){
        int countVal = couponMapper.updateCoupon(coupon);
        return countVal;
    }
    @Override
    public PageInfo<Coupon> searchCouponList(int pageNum, int pageSize, String couponName){
        List<Coupon> ctList = couponMapper.searchCouponList(pageNum,pageSize,couponName);
        return new PageInfo<Coupon>(ctList);
    }

    @Override
    public Coupon searchCouponById(String couponId){
        return couponMapper.searchCouponById(couponId);
    }

    @Override
    public  List<Goods> searchCouponGoodsList(String couponId){
        return couponMapper.searchCouponGoodsList(couponId);
    }

    @Override
    public  List<Coupon> searchUserCoupons(int pageNum ,int pageSize ,String userId){
        return couponMapper.searchUserCoupons(pageNum,pageSize,userId);
    }
    @Override
    public List<Goods> searchCouponGoods(int pageNum , int pageSize ,String couponId){
        return couponMapper.searchCouponGoods(pageNum,pageSize,couponId);
    }



}
