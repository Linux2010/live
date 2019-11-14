package cn.com.myproject.goods.controller;

import cn.com.myproject.goods.entity.PO.GoodsCat;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.goods.IGoodsCatService;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by LeiJia on 2017/9/13 0021.
 */

@Controller
@RequestMapping(value = "/goodsCat")
public class GoodsCatController {

    @Autowired
    private IGoodsCatService goodsCatService;

    @Autowired
    private IUploadImgService uploadImgService;

    /**
     * 展示商品分类首页
     *
     * @return
     */
    @RequestMapping(value = "/goodsCatIndex")
    public String goodsCatIndex(){
        return "/goods/goods_cat_index";
    }

    /**
     * 展示商品分类子页面
     *
     * @return
     */
    @RequestMapping(value = "/goodsCatChildren")
    public ModelAndView goodsCatChildren(String parentId){
        ModelAndView view = new ModelAndView("/goods/goods_cat_children");
        GoodsCat goodsCat = goodsCatService.searchGoodsCatById(parentId);
        if(goodsCat != null){
            view.addObject("catName",goodsCat.getCatName());
        }
        view.addObject("parentId",parentId);
        return view;
    }

    /**
     * 添加商品分类
     *
     * @param goodsCat
     * @return
     */
    @RequestMapping(value = "/addGoodsCat")
    @ResponseBody
    public String addGoodsCat(MultipartFile file ,GoodsCat goodsCat){

        String result = "1";
        String urlVal = uploadImgService.uploadImg(file,"goods");
        if(StringUtils.isNotBlank(urlVal)){
            goodsCat.setImageUrl(urlVal);
        }
        boolean flagVal = goodsCatService.addGoodsCat(goodsCat);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 根据catId删除商品分类
     *
     * @param catId
     * @return
     */
    @RequestMapping(value = "/removeGoodsCat")
    @ResponseBody
    public String removeGoodsCat(String catId){
        String result = "1";
        boolean flagVal = goodsCatService.removeGoodsCat(catId);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 修改商品分类信息
     *
     * @param goodsCat
     * @return
     */
    @RequestMapping(value = "/modifyGoodsCat")
    @ResponseBody
    public String modifyGoodsCat(MultipartFile file ,GoodsCat goodsCat){
        String result = "1";
        String urlVal = uploadImgService.uploadImg(file,"goods");
        if(StringUtils.isNotBlank(urlVal)){
            goodsCat.setImageUrl(urlVal);
        }
        boolean flagVal = goodsCatService.modifyGoodsCat(goodsCat);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 分页查询商品分类信息
     * @param page
     * @param rows
     * @param catName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/searchGoodsCatList")
    @ResponseBody
    public PageInfo<GoodsCat> searchGoodsCatList(int page, int rows, String catName, String parentId){
        return goodsCatService.searchGoodsCatList(page,rows,catName,parentId);
    }

    @RequestMapping(value = "/searchGoodsCateByParentId")
    @ResponseBody
    public String searchGoodsCatByParentId(String parentId){
        List<GoodsCat> goodsCatList = goodsCatService.searchGoodsCatByParentId(parentId);
        JsonArray jArr = new JsonArray();
        if(!goodsCatList.isEmpty()){
            JsonObject jsonObject = null;
            for(int i = 0;i < goodsCatList.size();i++){
                jsonObject = new JsonObject();
                if(goodsCatList.get(i) != null){
                    jsonObject.addProperty("id",goodsCatList.get(i).getCatId());
                    jsonObject.addProperty("text",goodsCatList.get(i).getCatName());
                    if("-1".equals(parentId)){// 如果是父节点了，则默认闭合
                        jsonObject.addProperty("state","closed");
                    }
                    jArr.add(jsonObject);
                }
            }
        }
        return jArr.toString();
    }

}
