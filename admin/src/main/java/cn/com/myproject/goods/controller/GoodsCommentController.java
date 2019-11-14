package cn.com.myproject.goods.controller;

import cn.com.myproject.goods.entity.PO.GoodsCommentReply;
import cn.com.myproject.goods.entity.PO.GoodsComment;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.goods.IGoodsCommentService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LeiJia on 2017/9/29 0014.
 */
@RequestMapping("/goodsComment")
@Controller
public class GoodsCommentController {

    public static final Logger logger = LoggerFactory.getLogger(GoodsCommentController.class);

    @Autowired
    private IGoodsCommentService goodsCommentService;

    @RequestMapping("/")
    public String index(){
        return "goods/goods_comment_index";
    }


    @RequestMapping("/searchGoodsCommentList")
    @ResponseBody
    public PageInfo<GoodsComment> searchGoodsCommentList(int page, int rows){
       PageInfo<GoodsComment> alllist = goodsCommentService.searchGoodsCommentList(page,rows);
        return alllist;
    }


    @RequestMapping("/searchGoodsCommentById")
    @ResponseBody
    public GoodsComment searchGoodsCommentById(String goodsCommentId){
        return goodsCommentService.searchGoodsCommentById(goodsCommentId);
    }


    @RequestMapping("/removeGoodsComment")
    @ResponseBody
    public Integer removeGoodsComment(String goodsCommentId){
         return goodsCommentService.removeGoodsComment(goodsCommentId);
    }

    @RequestMapping("/addGoodsCommentReply")
    @ResponseBody
    public int addGoodsCommentReply(GoodsCommentReply goodsCommentReply, HttpServletRequest request){
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(context != null){
           SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
           if(user != null){
               goodsCommentReply.setUserId(user.getUserId());
           }
        }
        return goodsCommentService.addGoodsCommentReply(goodsCommentReply);
    }

    @PostMapping("/searchGoodsCommentReplyBygoodsCommentId")
    @ResponseBody
    public List<GoodsCommentReply> searchGoodsCommentReplyBygoodsCommentId(String goodsCommentId){
        return goodsCommentService.searchGoodsCommentReplyBygoodsCommentId(goodsCommentId);
    }
}
