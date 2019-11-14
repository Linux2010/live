package cn.com.myproject.netease.service.impl;

import cn.com.myproject.netease.VO.channel.*;
import cn.com.myproject.netease.service.IIMChannelService;
import cn.com.myproject.netease.util.IMHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liyang-macbook on 2017/7/3.
 */
@Service
public class IMChannelService implements IIMChannelService {

    private static final String CREATE_URL = "https://vcloud.163.com/app/channel/create";
    private static final String UPDATE_URL = "https://vcloud.163.com/app/channel/update";
    private static final String DELETE_URL = "https://vcloud.163.com/app/channel/delete";
    private static final String CHANNELSTATS_URL = "https://vcloud.163.com/app/channelstats";
    private static final String CHANNELLIST_URL = "https://vcloud.163.com/app/channellist";
    private static final String ADDRESS_URL = "https://vcloud.163.com/app/address";
    private static final String SETALWAYSRECORD_URL = "https://vcloud.163.com/app/channel/setAlwaysRecord";
    private static final String PAUSE_URL = "https://vcloud.163.com/app/channel/pause";
    private static final String PAUSELIST_URL = "https://vcloud.163.com/app/channellist/pause";
    private static final String RESUME_URL = "https://vcloud.163.com/app/channel/resume";
    private static final String RESUMELIST_URL = "https://vcloud.163.com/app/channellist/resume";
    private static final String VIDEOLIST_URL = "https://vcloud.163.com/app/videolist";
    private static final String VODVIDEOLIST_URL = "https://vcloud.163.com/app/vodvideolist";
    private static final String SETCALLBACK_URL = "https://vcloud.163.com/app/record/setcallback";
    private static final String SETSIGNKEY_URL = "https://vcloud.163.com/app/callback/setSignKey";
    private static final String MERGE_URL = "https://vcloud.163.com/app/video/merge";
    private static final String GETTOKEN_URL = "https://api.netease.im/nimserver/user/getToken.action";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultChannel create(IMCreateVO vo) {

        HttpEntity<IMCreateVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(CREATE_URL,formEntity,  ResultChannel.class);

        return entity.getBody();
    }

    @Override
    public ResultChannel update(IMUpdateVO vo) {
        HttpEntity<IMUpdateVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(UPDATE_URL,formEntity,  ResultChannel.class);

        return entity.getBody();
    }

    @Override
    public ResultChannel delete(IMDeleteVO vo) {
        HttpEntity<IMDeleteVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(DELETE_URL,formEntity,  ResultChannel.class);

        return entity.getBody();
    }

    @Override
    public ResultChannel channelstats(IMDeleteVO vo) {
        HttpEntity<IMDeleteVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(CHANNELSTATS_URL,formEntity,  ResultChannel.class);

        return entity.getBody();

    }

    @Override
    public ResultChannelMap channellist(IMChannellistVO vo) {
        HttpEntity<IMChannellistVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannelMap> entity = restTemplate.postForEntity(CHANNELLIST_URL,formEntity,  ResultChannelMap.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel address(IMDeleteVO vo) {
        HttpEntity<IMDeleteVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(ADDRESS_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel setSetalwaysrecord(IMSetSetalwaysrecordVO vo) {
        HttpEntity<IMSetSetalwaysrecordVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(SETALWAYSRECORD_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel pause(IMDeleteVO vo) {
        HttpEntity<IMDeleteVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(PAUSE_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public ResultChannelList pauseList(IMPauseListVO vo) {
        HttpEntity<IMPauseListVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannelList> entity = restTemplate.postForEntity(PAUSELIST_URL,formEntity,  ResultChannelList.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel resume(IMDeleteVO vo) {
        HttpEntity<IMDeleteVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(RESUME_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public ResultChannelList resumeList(IMPauseListVO vo) {
        HttpEntity<IMPauseListVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannelList> entity = restTemplate.postForEntity(RESUMELIST_URL,formEntity,  ResultChannelList.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel videolist(IMVideolistVO vo) {
        HttpEntity<IMVideolistVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(VIDEOLIST_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public ResultChannelMap vodvideolist(IMVodvideolistVO vo) {
        HttpEntity<IMVodvideolistVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannelMap> entity = restTemplate.postForEntity(VODVIDEOLIST_URL,formEntity,  ResultChannelMap.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel setcallback(IMSetCallbackVO vo) {
        HttpEntity<IMSetCallbackVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(SETCALLBACK_URL,formEntity,  ResultChannel.class);
        return entity.getBody();

    }

    @Override
    public ResultChannel setSignKey(IMSetSignKeyVO vo) {
        HttpEntity<IMSetSignKeyVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(SETSIGNKEY_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public ResultChannel merge(IMMergeVO vo){
        HttpEntity<IMMergeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultChannel> entity = restTemplate.postForEntity(MERGE_URL,formEntity,  ResultChannel.class);
        return entity.getBody();
    }

    @Override
    public GetTokenReturnVO getToken(GetTokenVO vo) {
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        ResponseEntity<GetTokenReturnVO> entity = restTemplate.postForEntity(GETTOKEN_URL,formEntity,  GetTokenReturnVO.class);
        return entity.getBody();
    }

}
