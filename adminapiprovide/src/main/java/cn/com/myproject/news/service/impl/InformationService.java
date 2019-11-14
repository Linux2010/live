package cn.com.myproject.news.service.impl;

import cn.com.myproject.news.mapper.InformationMapper;
import cn.com.myproject.news.service.ImplInformationService;
import cn.com.myproject.user.entity.PO.Information;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李延超 on 2017/8/18.
 */
@Service
public class InformationService implements ImplInformationService {

   @Autowired
   private InformationMapper inMapper;

   @Override
  public  void updateInformationAgree(@RequestBody Information information){

   inMapper.updateInformationAgree(information);
    }


    @Override
 public    void delInformation(String informationid){
        inMapper.delInformation(informationid);
    }

    @Override
    public  Information selectById(String informationId){
        return  inMapper.selectById(informationId);
    }
    @Override
   public  Information selectByInformationId(String informationId,String userId){
        Information information = inMapper.selectByInformationId(informationId,userId);
        if(information != null){
            information.setCommentNum(inMapper.searchInfoCountById(informationId));
        }
      return  information;
    }

    @Override
    public List<Information> getAll() {

        List<Information> list = inMapper.getAll();
        return list;
    }
 @Override
    public void   addFabulous(@RequestBody Information information){
       inMapper.addFabulous(information);
 }

    @Override
    public void addInformation( Information information) {

        inMapper.addInformation(information);
    }

    @Override
    public Information selectByUserId(String userId) {

        return inMapper.selectByUserId(userId);
    }

    @Override
    public PageInfo<Information> getPage(int pageNum, int pageSize, String title) {
        List<Information> informationList = inMapper.searchInfoList(pageNum, pageSize, title);
        return convert(informationList);

    }

    @Override
    public List<Information> searchInfoList(int pageNum, int pageSize, String title){
        return inMapper.searchInfoList(pageNum, pageSize, title);
    }

    @Override
    public Information selectByInfoId(String informationId){
        return  inMapper.selectByInfoId(informationId);
    }

    @Override
    public void updateAgreeNum(Information information) {

        inMapper.updateAgreeNum(information);
    }

    @Override
    public Information selectInformById(String informationId){
        return inMapper.selectById( informationId);
    }

    @Override
    public void updateContent(Information information) {

        inMapper.updateContent(information);
    }

    @Override
    public void updateCommentNum(Information information){
        inMapper.updateCommentNum(information);
    }

    @Override
    public void updateTitle(Information information) {

        inMapper.updateTitle(information);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<Information> convert(List<Information> list) {
        PageInfo<Information> info = new PageInfo(list);
        List<Information> _list = info.getList();
        info.setList(null);
        List<Information> __list = new ArrayList<>(10);
        PageInfo<Information> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(Information c : _list) {
                __list.add(c);
            }
            _info.setList(__list);
        }
        return _info;
    }

}
