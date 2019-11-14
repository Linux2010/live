package cn.com.myproject.goods.controller;

import cn.com.myproject.goods.entity.PO.*;
import cn.com.myproject.goods.entity.VO.GoodsSpecStockVO;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.goods.IGoodsService;
import cn.com.myproject.service.goods.ISpecService;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by LeiJia on 2017/9/15 0021.
 */

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IUploadImgService uploadImgService;


    @Autowired
    private ISpecService specService;

    /**
     * 展示商品首页
     *
     * @return
     */
    @RequestMapping(value = "/goods_index")
    public String goods_index(){
        return "/goods/goods_index";
    }



    /**
     * 展示商品添加页面
     *
     * @return
     */
    @RequestMapping(value = "/goods_add_index")
    public String goods_add_index(){
        return "/goods/goods_add_index";
    }

    /**
     * 展示商品修改页面
     *
     * @return
     */
    @RequestMapping(value = "/goods_edit_index")
    public ModelAndView goods_add_index(String goodsId){
        ModelAndView view = new ModelAndView("/goods/goods_edit_index");
        Goods goods = goodsService.searchGoodsById(goodsId);
        view.addObject("goods",goods);
        view.addObject("goodsType",goods.getGoodsType());
        view.addObject("isSoldOut",goods.getIsSoldOut());
        view.addObject("catId",goods.getCatId());
        List<GoodsSpec> goodsSpecs = goodsService.selectGoodsSpecsByGoodsId(goodsId);
        List<GoodsSku> goodsSkus = goodsService.searchGoodsSkuListByGoodsId(goodsId);
        List<Spec> specs =  specService.getSpecList();
        view.addObject("goodsSpecs",goodsSpecs);
        view.addObject("goodsSkus",goodsSkus);
        view.addObject("specs",specs);
        return view;
    }
    /**
     * 展示商品详情页面
     *
     * @return
     */
    @RequestMapping(value = "/goods_detail_index")
    public ModelAndView goods_detail_index(String goodsId){
        ModelAndView view = new ModelAndView("/goods/goods_detail_index");
        Goods goods = goodsService.searchGoodsById(goodsId);
        List<GoodsSpec> goodsSpecs = goodsService.selectGoodsSpecsByGoodsId(goods.getGoodsId());
        List<GoodsSku> goodsSkus = goodsService.searchGoodsSkuListByGoodsId(goodsId);
        List<Spec> specs =  specService.getSpecList();
        goods.setGoodsSpecs(goodsSpecs);
        view.addObject("goods",goods);
        view.addObject("goodsType",goods.getGoodsType());
        view.addObject("isSoldOut",goods.getIsSoldOut());
        view.addObject("catId",goods.getCatId());
        view.addObject("goodsSpecs",goodsSpecs);
        view.addObject("goodsSkus",goodsSkus);
        view.addObject("specs",specs);
        //查询商品的规格信息
        return view;
    }

    /**
     * 展示商品规格修改详页面
     *
     * @return
     */
    @RequestMapping(value = "/goods_spec_edit_index")
    public ModelAndView goods_spec_edit_index(String goodsId){
        ModelAndView view = new ModelAndView("/goods/goods_spec_edit_index");
        Goods goods = goodsService.searchGoodsById(goodsId);
        view.addObject("goods",goods);
        view.addObject("goodsType",goods.getGoodsType());
        view.addObject("isSoldOut",goods.getIsSoldOut());
        view.addObject("catId",goods.getCatId());
        return view;
    }
    /**
     * 展示商品specs列表
     *
     * @return
     */
    @RequestMapping(value = "/selectSpecsByGoodsId")
    @ResponseBody
    public  List<Spec> selectSpecsByGoodsId(String goodsId){
        List<Spec> specs = goodsService.selectSpecsByGoodsId(goodsId);
        return specs;
    }

    /**
     * 展示商品规格库存列表
     *
     * @return
     */
    @RequestMapping(value = "/selectGoodsSpecsByGoodsId")
    @ResponseBody
    public  List<GoodsSpec> selectGoodsSpecsByGoodsId(String goodsId){
        List<GoodsSpec> goodsSpecs = goodsService.selectGoodsSpecsByGoodsId(goodsId);
        return goodsSpecs;
    }

    /**
     * 展示商品规格添加详页面
     *
     * @return
     */
    @RequestMapping(value = "/goods_spec_add_index")
    public ModelAndView goods_spec_add_index(String goodsId){
        ModelAndView view = new ModelAndView("/goods/goods_spec_add_index");
        Goods goods = goodsService.searchGoodsById(goodsId);
        view.addObject("goods",goods);
        view.addObject("goodsType",goods.getGoodsType());
        view.addObject("isSoldOut",goods.getIsSoldOut());
        view.addObject("catId",goods.getCatId());
        return view;
    }

    /**
     * 展示商品简介修改页面
     *
     * @return
     */
    @RequestMapping(value = "/goods_intro_index")
    public ModelAndView goods_intro_index(String goodsId){
        ModelAndView view = new ModelAndView("/goods/goods_intro_index");
    //    Goods goods = goodsService.searchGoodsById(goodsId);
        view.addObject("goodsId",goodsId);
    //    view.addObject("intro", goods.getIntro());
        return view;
    }
    /**
     * 展示商品详情
     *
     * @return
     */
    @RequestMapping(value = "/goodsDetail")
    @ResponseBody
    public Goods goodsDetail(String goodsId){
        Goods goods = goodsService.searchGoodsById(goodsId);
        return goods;
    }




    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/addGoods")
    @ResponseBody
    public String addGoods(MultipartFile file1 ,MultipartFile file2 ,MultipartFile file3 ,MultipartFile file4 ,MultipartFile file5 ,
                           Integer first1,Integer first2,Integer first3,Integer first4,Integer first5,
                           Integer seqno1,Integer seqno2,Integer seqno3,Integer seqno4,Integer seqno5 ,Goods goods,HttpServletRequest request){

        List<GoodsImg>  imgs = goods.getImgs();
        String urlVal1 = uploadImgService.uploadImg(file1,"goods");
        if(StringUtils.isNotBlank(urlVal1)&&StringUtils.isNotEmpty(urlVal1) && first1 != null && seqno1 != null){
            GoodsImg img1 = new GoodsImg(urlVal1,first1,seqno1);
            imgs.add(img1);
        }
        String urlVal2 = uploadImgService.uploadImg(file2,"goods");
        if(StringUtils.isNotBlank(urlVal2)&&StringUtils.isNotEmpty(urlVal2)&& first2 != null && seqno2 != null){
            GoodsImg img2 = new GoodsImg(urlVal2,first2,seqno2);
            imgs.add(img2);
        }
        String urlVal3 = uploadImgService.uploadImg(file3,"goods");
        if(StringUtils.isNotBlank(urlVal3)&&StringUtils.isNotEmpty(urlVal3)&& first3 != null && seqno3 != null){
            GoodsImg img3= new GoodsImg(urlVal3,first3,seqno3);
            imgs.add(img3);
        }
        String urlVa14 = uploadImgService.uploadImg(file4,"goods");
        if(StringUtils.isNotBlank(urlVa14)&&StringUtils.isNotEmpty(urlVa14)&& first4 != null && seqno4 != null){
            GoodsImg img4 = new GoodsImg(urlVa14,first4,seqno4);
            imgs.add(img4);
        }
        String urlVal5 = uploadImgService.uploadImg(file5,"goods");
        if(StringUtils.isNotBlank(urlVal5)&&StringUtils.isNotEmpty(urlVal5)&& first5 != null && seqno5 != null){
            GoodsImg img5 = new GoodsImg(urlVal5,first5,seqno5);
            imgs.add(img5);
        }
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null) {
            SecurityUser user = (SecurityUser) context.getAuthentication().getPrincipal();
            goods.setCreater(user.getUserId());
        }
        String  goodsId = goodsService.addGoods(goods);

        return goodsId;
    }

    /**
     * 根据goodsId删除商品
     *
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/removeGoods")
    @ResponseBody
    public int removeGoods(String goodsId){
        return goodsService.removeGoods(goodsId);
    }

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/modifyGoods")
    @ResponseBody
    public int modifyGoods(MultipartFile file1 ,MultipartFile file2 ,MultipartFile file3 ,MultipartFile file4 ,MultipartFile file5 ,Goods goods){
        String result = "1";
        List<GoodsImg>  imgs = goods.getImgs();
        String urlVal1 = uploadImgService.uploadImg(file1,"goods");
        if(StringUtils.isNotBlank(urlVal1)&&StringUtils.isNotEmpty(urlVal1) && imgs.size()>0){
            imgs.get(0).setUrl(urlVal1);
        }
        String urlVal2 = uploadImgService.uploadImg(file2,"goods");
        if(StringUtils.isNotBlank(urlVal2)&&StringUtils.isNotEmpty(urlVal2)  && imgs.size()>1){
            imgs.get(1).setUrl(urlVal2);
        }
        String urlVal3 = uploadImgService.uploadImg(file3,"goods");
        if(StringUtils.isNotBlank(urlVal3)&&StringUtils.isNotEmpty(urlVal3)  && imgs.size()>2){
            imgs.get(2).setUrl(urlVal3);
        }
        String urlVa14 = uploadImgService.uploadImg(file4,"goods");
        if(StringUtils.isNotBlank(urlVa14)&&StringUtils.isNotEmpty(urlVa14)  && imgs.size()>3){
            imgs.get(3).setUrl(urlVa14);
        }
        String urlVal5 = uploadImgService.uploadImg(file5,"goods");
        if(StringUtils.isNotBlank(urlVal5)&&StringUtils.isNotEmpty(urlVal5)  && imgs.size()>4){
            imgs.get(4).setUrl(urlVal5);
        }
        return goodsService.modifyGoods(goods);
    }

    /**
     * 修改商品简介信息
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/updateGoodsIntro")
    @ResponseBody
    public int updateGoodsIntro(Goods goods){
        return  goodsService.updateGoodsIntro(goods);
    }

    /**
     * 分页查询商品信息
     * @param page
     * @param rows
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/searchGoodsList")
    @ResponseBody
    public PageInfo<Goods> searchGoodsList(int page, int rows,String keyword) throws UnsupportedEncodingException {
        if(StringUtils.isNotEmpty(keyword) && StringUtils.isNoneBlank(keyword)){
            keyword =  URLDecoder.decode(keyword, "utf-8");
        }
        return goodsService.searchGoodsList(page,rows,keyword);
    }

    /**
     * 分页优惠劵下的商品列表
     * @param page
     * @param rows
     * @param keyword
     * @param catId 商品分类ID
     * @return
     */
    @RequestMapping(value = "/searchGoodsListByCatId")
    @ResponseBody
    public PageInfo<Goods> searchGoodsListByCatId(int page, int rows,String keyword,String catId,String couponId) throws UnsupportedEncodingException {
        if(StringUtils.isNotEmpty(keyword) && StringUtils.isNoneBlank(keyword)){
            keyword =  URLDecoder.decode(keyword, "utf-8");
        }
        return goodsService.searchGoodsListByCatId(page,rows,keyword,catId,couponId);
    }

    /**
     *  查询商品编号或商品名称已经被占用过
     * @param goods
     * @return
     */
    @RequestMapping(value = "/checkGoodsIsExist")
    @ResponseBody
    public int checkGoodsIsExist(Goods goods){
        int result =0;
        if((StringUtils.isNotEmpty(goods.getGoodsNumber())&& StringUtils.isNotBlank(goods.getGoodsNumber()))
                || (StringUtils.isNotEmpty(goods.getGoodsName()) && StringUtils.isNotBlank(goods.getGoodsName()))){
            result = goodsService.checkGoodsIsExist(goods);
        }

        return result;
    }

    /**
     *  添加商品规格与对应规格价格和库存
     * @param vo
     * @return
     */
    @RequestMapping(value = "/addGoodsSpecAndStock")
    @ResponseBody
    public int addGoodsSpecAndStock(@RequestBody  GoodsSpecStockVO vo){
        int result =0;
        result = goodsService.addGoodsSpecAndStock(vo);
        return result;
    }
    /**
     *  查询商品指定规格与规格值得库存与价格
     * @param spec
     * @return
     */
    @RequestMapping(value = "/getGoodsSpecDetail")
    @ResponseBody
    public GoodsSpec getGoodsSpecDetail(GoodsSpec spec){
        GoodsSpec result = goodsService.getGoodsSpecDetail(spec);
        return result;
    }

    /**
     *  更新商品规格与对应规格价格和库存
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateGoodsSpecAndStock")
    @ResponseBody
    public int updateGoodsSpecAndStock(@RequestBody  GoodsSpecStockVO vo){
        int result =0;
        result = goodsService.updateGoodsSpecAndStock(vo);
        return result;
    }



}
