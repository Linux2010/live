package cn.com.myproject.news.service;

import cn.com.myproject.user.entity.PO.Information;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 李延超 on 2017/8/18.
 */


public interface ImplInformationService {
    void  addInformation(Information information);
    void  delInformation(String informationId);
    Information selectById(String informationId);
    Information selectByInformationId(String informationId,String userId);
   List<Information> getAll();
   void addFabulous(Information information);
   void updateInformationAgree(Information information);

   Information selectByUserId(String userId);

    PageInfo<Information> getPage(int pageNum, int pageSize, String title);

    List<Information> searchInfoList(int pageNum, int pageSize, String title);

  Information  selectByInfoId(String informationId);

    void  updateAgreeNum(Information information);

    Information selectInformById(String informationId);

    void updateContent(Information information);

    void  updateCommentNum(Information information);

    void updateTitle(Information information);

}
