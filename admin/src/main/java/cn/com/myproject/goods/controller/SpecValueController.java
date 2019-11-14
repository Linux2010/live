package cn.com.myproject.goods.controller;
import cn.com.myproject.goods.entity.PO.SpecValues;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.goods.ISpecService;
import cn.com.myproject.service.goods.ISpecValuesService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by LeiJia on 2017/9/14 0021.
 */

@Controller
@RequestMapping(value = "/specValues")
public class SpecValueController {

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private ISpecValuesService specValuesService;

    @Autowired
    private ISpecService specService;


    /**
     * 分页查询规格值
     * @param page
     * @param rows
     * @param valuesName
     * @param specId
     * @return
     */
    @RequestMapping(value = "/searchSpecValuesList")
    @ResponseBody
    public PageInfo<SpecValues> searchSpecValuesList(int page, int rows, String valuesName,String specId){
        return specValuesService.searchSpecValuesList(page,rows,valuesName,specId);
    }


    /**
     * 添加规格值
     * @param specValue
     * @return
     */
    @RequestMapping(value = "/addSpecValues")
    @ResponseBody
    public String addSpecValues(MultipartFile file , SpecValues specValue){

        String result = "1";
        String urlVal = uploadImgService.uploadImg(file,"goods");
        if(StringUtils.isNotBlank(urlVal)){
            specValue.setImageUrl(urlVal);
        }
        boolean flagVal = specValuesService.addSpecValues(specValue);
        if(flagVal){
            result = "0";
        }
        return result;
    }


    /**
     * 修改规格值
     * @param specValue
     * @return
     */
    @RequestMapping(value = "/modifySpecValues")
    @ResponseBody
    public String modifySpecValues(MultipartFile file ,SpecValues specValue){
        String result = "1";
        if(StringUtils.isBlank(specValue.getGoodsSpecValuesId())|| StringUtils.isEmpty(specValue.getGoodsSpecValuesId())){
            result = "1";
            return result;
        }
        String urlVal = uploadImgService.uploadImg(file,"goods");
        if(StringUtils.isNotBlank(urlVal)&& StringUtils.isNotEmpty(urlVal)){
            specValue.setImageUrl(urlVal);
        }
        boolean flagVal = specValuesService.modifySpecValues(specValue);
        if(flagVal){
            result = "0";
        }
        return result;
    }


    /**
     * 根据specId删除规格值
     * @param goodsSpecValuesId
     * @return
     */
    @RequestMapping(value = "/removeSpecValues")
    @ResponseBody
    public String removeSpecValues(String goodsSpecValuesId){
        String result = "1";
        boolean flagVal = specValuesService.removeSpecValues(goodsSpecValuesId);
        if(flagVal){
            result = "0";
        }
        return result;
    }

}
