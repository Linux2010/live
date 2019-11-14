package cn.com.myproject.goods.controller;
import cn.com.myproject.goods.entity.PO.Coupon;
import cn.com.myproject.goods.entity.PO.Goods;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.goods.ICouponService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LeiJia on 2017/9/26 0021.
 */

@Controller
@RequestMapping(value = "/coupon")
public class CouponController {

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private ICouponService  couponService;

    /**
     * 展示优惠劵首页
     * @return
     */
    @RequestMapping(value = "/coupon_index")
    public String coupon_index(){
        return "/goods/coupon_index";
    }


    /**
     * 展示优惠劵添加首页
     * @return
     */
    @RequestMapping(value = "/coupon_add_index")
    public String coupon_add_index(){
        return "/goods/coupon_add_index";
    }


    /**
     * 展示优惠劵修改首页
     * @return
     */
    @RequestMapping(value = "/coupon_edit_index")
    public ModelAndView coupon_edit_index(String couponId){
        ModelAndView view = new ModelAndView("/goods/coupon_edit_index");
        Coupon coupon = couponService.searchCouponById(couponId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        view.addObject("couponBeginTimeStr",df.format(new Date(coupon.getCouponBeginTime())));
        view.addObject("couponEndTimeStr",df.format(new Date(coupon.getCouponEndTime())));
        view.addObject("coupon",coupon);
        return view;
    }


    /**
     * 展示优惠劵详细首页
     * @return
     */
    @RequestMapping(value = "/coupon_detail_index")
    public ModelAndView coupon_detail_index(String couponId){
        ModelAndView view = new ModelAndView("/goods/coupon_detail_index");
        Coupon coupon = couponService.searchCouponById(couponId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        view.addObject("couponEndTimeStr",df.format(new Date(coupon.getCouponEndTime())));
        view.addObject("coupon",coupon);

        //根据couponId查询优惠商品列表
        List<Goods> goodsList= couponService.searchCouponGoodsList(couponId);
        view.addObject("goodsList",goodsList);
        return view;
    }

    /**
     * 展示优惠劵使用商品列表
     * @return
     */
    @RequestMapping(value = "/coupon_goods_index")
    public ModelAndView goods_coupon_goods_index(String couponId){
        ModelAndView view = new ModelAndView("/goods/coupon_goods_index");
        Coupon coupon = couponService.searchCouponById(couponId);
        view.addObject("coupon",coupon);
        view.addObject("couponId",couponId);
        return view;
    }

    /**
     * 展示优惠劵的发放用户列表
     * @return
     */
    @RequestMapping(value = "/coupon_user_index")
    public ModelAndView user_coupon_index(String couponId){
        ModelAndView view = new ModelAndView("/goods/coupon_user_index");
        Coupon coupon = couponService.searchCouponById(couponId);
        view.addObject("coupon",coupon);
        view.addObject("couponId",couponId);
        return view;
    }

    /**
     * 分页查询优惠劵
     * @param page
     * @param rows
     * @param couponName
     * @return
     */
    @RequestMapping(value = "/searchCouponList")
    @ResponseBody
    public PageInfo<Coupon> searchCouponList(int page, int rows, String couponName){
        PageInfo<Coupon> list =  couponService.searchCouponList(page,rows,couponName);
        return list;
    }


    /**
     * 查询优惠劵列表
     * @return
     */
    @RequestMapping(value = "/getCouponList")
    @ResponseBody
    public List<Coupon> getCouponList(){
        List<Coupon> list =  couponService.getCouponList();
        return list;
    }


    /**
     * 添加优惠劵
     * @param coupon
     * @return
     */
    @RequestMapping(value = "/addCoupon")
    @ResponseBody
    public int addCoupon(MultipartFile file , Coupon coupon,String couponBeginTimeStr,String couponEndTimeStr){
        if(file != null){
            String urlVal = uploadImgService.uploadImg(file,"coupon");
            if(StringUtils.isNotEmpty(urlVal) && StringUtils.isNotBlank(urlVal)){
                coupon.setCouponImgUrl(urlVal);
            }
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if(StringUtils.isNotBlank(couponBeginTimeStr)){
                coupon.setCouponBeginTime(df.parse(couponBeginTimeStr).getTime());
            }
            if(StringUtils.isNotBlank(couponEndTimeStr)){
                coupon.setCouponEndTime(df.parse(couponEndTimeStr).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  couponService.addCoupon(coupon);
    }


    /**
     * 根据ID删除优惠劵
     *
     * @param couponId
     * @return
     */
    @RequestMapping(value = "/removeCoupon")
    @ResponseBody
    public int removeCoupon(String couponId){
        return couponService.removeCoupon(couponId);
    }

    /**
     * 根据goodsId和couponId批量设置商品为优惠商品
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/addCouponGoods")
    @ResponseBody
    public int addCouponGoods(String ids,String couponId){
        if(StringUtils.isBlank(ids)){
            return 0;
        }
       int  result = couponService.addCouponGoods(ids,couponId);
        return result;

    }

    /**
     * 根据goodsId和couponId批量取消商品的优惠优惠劵
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/removeCouponGoods")
    @ResponseBody
    public int removeCouponGoods(String ids,String couponId){
        if(StringUtils.isBlank(ids)){
            return 0;
        }
        int  result = couponService.removeCouponGoods(ids,couponId);
        return result;

    }

    /**
     * 修改优惠劵
     *
     * @param coupon
     * @return
     */
    @RequestMapping(value = "/modifCoupon")
    @ResponseBody
    public int modifCoupon(MultipartFile file ,Coupon coupon,String couponBeginTimeStr,String couponEndTimeStr){
        if(file != null){
            String urlVal = uploadImgService.uploadImg(file,"coupon");
            if(StringUtils.isNotEmpty(urlVal) && StringUtils.isNotBlank(urlVal)){
                coupon.setCouponImgUrl(urlVal);
            }
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

            if(StringUtils.isNotBlank(couponBeginTimeStr)){
                coupon.setCouponBeginTime(df.parse(couponBeginTimeStr).getTime());
            }
            if(StringUtils.isNotBlank(couponEndTimeStr)){
                coupon.setCouponEndTime(df.parse(couponEndTimeStr).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  couponService.modifyCoupon(coupon);

    }

    /**
     * 根据userId和couponId给用户发放优惠劵
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/addUserCoupon")
    @ResponseBody
    public int addUserCoupon(String ids,String couponId){
        if(StringUtils.isBlank(ids)){
            return 0;
        }
        int  result = couponService.addUserCoupon(ids,couponId);
        return result;

    }

    /**
     * 根据userId和couponId取消给用户发放的优惠劵
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/cancelUserCoupon")
    @ResponseBody
    public int cancelUserCoupon(String ids,String couponId){
        if(StringUtils.isBlank(ids)){
            return 0;
        }
        int  result = couponService.cancelUserCoupon(ids,couponId);
        return result;

    }



}
