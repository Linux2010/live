package cn.com.myproject.goods.controller;
import cn.com.myproject.goods.entity.PO.Spec;
import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.goods.ISpecService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by LeiJia on 2017/9/14 0021.
 */

@Controller
@RequestMapping(value = "/spec")
public class SpecController {

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private ISpecService specService;

    /**
     * 展示规格首页
     * @return
     */
    @RequestMapping(value = "/spec_index")
    public String spec_index(){
        return "/goods/spec_index";
    }


    /**
     * 展示规格值页面
     *
     * @return
     */
    @RequestMapping(value = "/spec_values_index")
    public ModelAndView goodsSpecValuesBySpecId(String specId){
        ModelAndView view = new ModelAndView("/goods/spec_values_index");
        Spec spec = specService.searchSpecById(specId);
        if(spec != null){
            view.addObject("specName",spec.getSpecName());
            view.addObject("specId",spec.getSpecId());
        }
        return view;
    }


    /**
     * 分页查询规格
     * @param page
     * @param rows
     * @param specName
     * @return
     */
    @RequestMapping(value = "/searchSpecList")
    @ResponseBody
    public PageInfo<Spec> searchSpecList(int page, int rows, String specName){
        PageInfo<Spec> list =  specService.searchSpecList(page,rows,specName);
        return list;
    }


    /**
     * 查询规格列表
     * @return
     */
    @RequestMapping(value = "/getSpecList")
    @ResponseBody
    public List<Spec> getSpecList(){
        List<Spec> list =  specService.getSpecList();
        return list;
    }


    /**
     * 添加规格
     * @param spec
     * @return
     */
    @RequestMapping(value = "/addSpec")
    @ResponseBody
    public String addSpec(MultipartFile file , Spec spec){

        String result = "1";
        boolean flagVal = specService.addSpec(spec);
        if(flagVal){
            result = "0";
        }
        return result;
    }


    /**
     * 根据specId删除规格
     *
     * @param specId
     * @return
     */
    @RequestMapping(value = "/removeSpec")
    @ResponseBody
    public String removeSpec(String specId){
        String result = "1";
        boolean flagVal = specService.removeSpec(specId);
        if(flagVal){
            result = "0";
        }
        return result;
    }

    /**
     * 修改规格
     *
     * @param spec
     * @return
     */
    @RequestMapping(value = "/modifySpec")
    @ResponseBody
    public String modifySpec(MultipartFile file ,Spec spec){
        String result = "1";
        boolean flagVal = specService.modifySpec(spec);
        if(flagVal){
            result = "0";
        }
        return result;
    }


}
