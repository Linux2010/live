package cn.com.myproject.shortVideo.service.impl;


import cn.com.myproject.shortVideo.mapper.ShortVideoMapper;
import cn.com.myproject.shortVideo.service.IShortVideoService;
import cn.com.myproject.user.entity.PO.ShortVideo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26 0007.
 */
@Service
public class ShortVideoService implements IShortVideoService {

    @Autowired
    private ShortVideoMapper shortVideoMapper;

    @Override
    public PageInfo<ShortVideo> getPage(int pageNum, int pageSize, String keyword) {

        List<ShortVideo> list = shortVideoMapper.getPage(pageNum, pageSize, keyword);
        return convert(list);
    }

    @Override
    public void addVideo(ShortVideo shortVideo) {

        shortVideoMapper.addVideo(shortVideo);
    }

    @Override
    public List<ShortVideo> selectByStatus(int status) {

        return shortVideoMapper.selectByStatus(status);
    }

    @Override
    public ShortVideo selectById(String videoId) {

        return shortVideoMapper.selectById(videoId);
    }

    @Override
    public void updateVideo(ShortVideo shortVideo) {

        shortVideoMapper.updateVideo(shortVideo);
    }

    @Override
    public void deleteVideo(String videoId) {

        shortVideoMapper.deleteVideo(videoId);
    }

    @Override
    public ShortVideo selectByType(int videoType) {

        return shortVideoMapper.selectByType(videoType);
    }

    @Transactional
    @Override
    public void updateStatus(ShortVideo shortVideo) {

        shortVideoMapper.updateStatus(shortVideo);
    }

    private PageInfo<ShortVideo> convert(List<ShortVideo> list) {
        PageInfo<ShortVideo> info = new PageInfo(list);

        List<ShortVideo> _list = info.getList();
        info.setList(null);
        List<ShortVideo> __list = new ArrayList<>(10);

        PageInfo<ShortVideo> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(ShortVideo shortVideo : _list) {
                __list.add(shortVideo);
            }
            _info.setList(__list);
        }
        return _info;
    }
}
