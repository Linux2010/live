package cn.com.myproject.netease.service.impl;


import cn.com.myproject.netease.VO.vod.*;
import cn.com.myproject.netease.service.IVodService;
import cn.com.myproject.netease.util.IMHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liyang-macbook on 2017/7/17.
 */
@Service
public class VodService implements IVodService {

    private static long uploadDataSize = 1024 * 1024 * 4;


    private static final String CREATE_TYPE_URL = "https://vcloud.163.com/app/vod/type/create";
    private static final String GET_TYPE_URL = "https://vcloud.163.com/app/vod/type/get";
    private static final String LIST_TYPE_URL = "https://vcloud.163.com/app/vod/type/list";
    private static final String UPDATE_TYPE_URL = "https://vcloud.163.com/app/vod/type/update";
    private static final String DELETE_TYPE_URL = "https://vcloud.163.com/app/vod/type/typeDelete";
    private static final String SET_TYPE_URL = "https://vcloud.163.com/app/vod/type/set";

    private static final String CREATE_PRESET_URL = "https://vcloud.163.com/app/vod/preset/create";
    private static final String GET_PRESET_URL = "https://vcloud.163.com/app/vod/preset/get";
    private static final String LIST_PRESET_URL = "https://vcloud.163.com/app/vod/preset/list";
    private static final String UPDATE_PRESET_URL = "https://vcloud.163.com/app/vod/preset/update";
    private static final String DELETE_PRESET_URL = "https://vcloud.163.com/app/vod/preset/update";

    private static final String CREATE_WATERMARK_URL = "https://vcloud.163.com/app/vod/watermark/create";
    private static final String GET_WATERMARK_URL = "https://vcloud.163.com/app/vod/watermark/get";
    private static final String LIST_WATERMARK_URL = "https://vcloud.163.com/app/vod/watermark/list";
    private static final String UPDATE_WATERMARK_URL = "https://vcloud.163.com/app/vod/watermark/update";
    private static final String DELETE_WATERMARK_URL = "https://vcloud.163.com/app/vod/watermark/delete";

    private static final String GET_VIDEO_URL = "https://vcloud.163.com/app/vod/video/get";
    private static final String LIST_VIDEO_URL = "https://vcloud.163.com/app/vod/video/list";
    private static final String EDIT_VIDEO_URL = "https://vcloud.163.com/app/vod/video/edit";
    private static final String DELETE_SINGLE_VIDEO_URL = "https://vcloud.163.com/app/vod/video/delete_single";
    private static final String DELETE_VIDEO_URL = "https://vcloud.163.com/app/vod/video/videoDelete";
    private static final String DISABLE_VIDEO_URL = "https://vcloud.163.com/app/vod/video/videoDisable";
    private static final String RECOVER_VIDEO_URL = "https://vcloud.163.com/app/vod/video/videoRecover";
    private static final String DELETE_BATCH_VIDEO_URL = "https://vcloud.163.com/app/vod/video/delete";

    private static final String INIT_UPLOAD_URL = "https://vcloud.163.com/app/vod/upload/init";
    private static final String QUERY_VIDEO_URL = "https://vcloud.163.com/app/vod/video/query";

    private static final String CREATE_SNAPSHOT_URL = "https://vcloud.163.com/app/vod/snapshot/create";
    private static final String SET_SNAPSHOT_URL = "https://vcloud.163.com/app/vod/snapshot/set";

    private static final String RESETMULTI_TRANSCODE_URL = "https://vcloud.163.com/app/vod/transcode/resetmulti";

    private static final String SETCALLBACK_UPLOAD_URL = "https://vcloud.163.com/app/vod/upload/setcallback";
    private static final String SETCALLBACK_TRANSCODE_URL = "https://vcloud.163.com/app/vod/transcode/setcallback";

    private static final String FLOW_STATS_URL = "https://vcloud.163.com/app/vod/stats/flow";
    private static final String BAND_STATS_URL = "https://vcloud.163.com/app/vod/stats/band";
    private static final String STORAGE_STATS_URL = "https://vcloud.163.com/app/vod/stats/storage";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultVod createType(CreateTypeVO vo) {
        HttpEntity<CreateTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(CREATE_TYPE_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod getType(GetTypeVO vo) {
        HttpEntity<GetTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(GET_TYPE_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod listType(ListTypeVO vo) {
        HttpEntity<ListTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(LIST_TYPE_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod updateType(UpdateTypeVO vo) {
        HttpEntity<UpdateTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(UPDATE_TYPE_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod deleteType(DeleteTypeVO vo) {
        HttpEntity<DeleteTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DELETE_TYPE_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod setType(SetTypeVO vo) {
        HttpEntity<SetTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(SET_TYPE_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod createPreSet(CreatePreSetVO vo) {
        HttpEntity<CreatePreSetVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(CREATE_PRESET_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod getPreSet(GetPreSetVO vo) {
        HttpEntity<GetPreSetVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(GET_PRESET_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod listPreSet(ListPreSetVO vo) {
        HttpEntity<ListPreSetVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(LIST_PRESET_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod updatePreSet(UpdatePreSetVO vo) {
        HttpEntity<UpdatePreSetVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(UPDATE_PRESET_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod deletePreSet(DeleteTypeVO vo) {
        HttpEntity<DeleteTypeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DELETE_PRESET_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod createWaterMark(CreateWaterMarkVO vo) {
        HttpEntity<CreateWaterMarkVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(CREATE_WATERMARK_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod getWaterMark(GetWaterMarkVO vo) {
        HttpEntity<GetWaterMarkVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(GET_WATERMARK_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod listWaterMark(ListWaterMarkVO vo) {
        HttpEntity<ListWaterMarkVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(LIST_WATERMARK_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod updateWaterMark(UpdateWaterMarkVO vo) {
        HttpEntity<UpdateWaterMarkVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(UPDATE_WATERMARK_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod deleteWaterMark(DeleteWaterMarkVO vo) {
        HttpEntity<DeleteWaterMarkVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DELETE_WATERMARK_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod getVideo(GetVideoVO vo) {
        HttpEntity<GetVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(GET_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod listVideo(ListVideoVO vo) {
        HttpEntity<ListVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(LIST_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod editVideo(EditVideoVO vo) {
        HttpEntity<EditVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(EDIT_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod deleteSingleVideo(DeleteSingleVideoVO vo) {
        HttpEntity<DeleteSingleVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DELETE_SINGLE_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod deleteVideo(DeleteVideoVO vo) {
        HttpEntity<DeleteVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DELETE_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod disableVideo(DisableVideoVO vo) {
        HttpEntity<DisableVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DISABLE_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod recoverVideo(RecoverVideoVO vo) {
        HttpEntity<RecoverVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(RECOVER_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod deleteBatchVideo(DeleteBatchVideoVO vo) {
        HttpEntity<DeleteBatchVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(DELETE_BATCH_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod initUpload(InitUploadVO vo) {
        HttpEntity<InitUploadVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(INIT_UPLOAD_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public GetLbsReturnVO getLbs(String bucket) {
        ResponseEntity<GetLbsReturnVO> entity = restTemplate.getForEntity("http://wanproxy.127.net/lbs?version=1.0&bucketname="+bucket,GetLbsReturnVO.class);
        return entity.getBody();
    }


    @Override
    public void uploadFile(UploadFileVO vo,InputStream inputStream,Long size) {
        HttpEntity formEntity;

        /*当前分片在整个对象中的起始偏移量    此参数必填*/
        Long offset = new Long(0);
		     /*上传上下文         此参数必填*/
        String context = null;

        /*上传文件剩余大小*/
        Long remainderSize = size;
        /*分片上传视频*/
        try {
            while(remainderSize > 0){
                byte buffer[];
                String url;
                if(remainderSize > 0){

                    //logger.info("[UploadVideo] : 文件剩余大小： " + remainderSize);
                /* 判读是否是最后一片 */
                    if (remainderSize <= uploadDataSize) {
                        url = vo.getUploadHost() + "/" + vo.getBucket() + "/" + vo.getObject() + "?offset="	+ offset + "&complete=" + "true" + "&version=1.0";
                        /* 如果是最后一片，申请的数组大小要根据实际情况 */
                        buffer = new byte[remainderSize.intValue()];
                    } else {
                        url = vo.getUploadHost() + "/" + vo.getBucket() + "/" + vo.getObject() + "?offset="	+ offset + "&complete=" + "false" + "&version=1.0";
                        buffer = new byte[(int) uploadDataSize];
                    }
                    /* 如果不是第一次传输，需要加入此参数 */
                    if (null != context) {
                        url = url + "&context=" + context;
                    }

                    int len = inputStream.read(buffer);
                    formEntity = new HttpEntity<>(buffer,IMHttpHeaders.getUpload(vo.getX_nos_token()));
                    ResponseEntity<UploadFileResultVO> entity = restTemplate.postForEntity(url,formEntity,UploadFileResultVO.class);
                    UploadFileResultVO uploadFileResultVO = entity.getBody();
                    if(null == uploadFileResultVO.getErrMsg() || "".equals(uploadFileResultVO.getErrMsg().trim())){
                        context = uploadFileResultVO.getContext();
                        offset = uploadFileResultVO.getOffset();
                        remainderSize  = size - offset;
                    }else{
                        throw new RuntimeException("upload videoFragment fail. errMsg: " + uploadFileResultVO.getErrMsg() +
                                " requestID: " + uploadFileResultVO.getRequestId());
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }
        }
    }


    @Override
    public ResultVod queryVideo(QueryVideoVO vo) {
        HttpEntity<QueryVideoVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(QUERY_VIDEO_URL,formEntity,  ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod createSnapshot(CreateSnapshotVO vo) {
        HttpEntity<CreateSnapshotVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(CREATE_SNAPSHOT_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod setSnapshot(SetSnapshotVO vo) {
        HttpEntity<SetSnapshotVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(SET_SNAPSHOT_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod resetmultiTranscode(ResetMultiTranscodeVO vo) {
        HttpEntity<ResetMultiTranscodeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(RESETMULTI_TRANSCODE_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod setCallbackUpload(SetCallbackUploadVO vo) {
        HttpEntity<SetCallbackUploadVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(SETCALLBACK_UPLOAD_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod setCallbackTrancode(SetCallbackTranscodeVO vo) {
        HttpEntity<SetCallbackTranscodeVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(SETCALLBACK_TRANSCODE_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod flowStats(FlowStatsVO vo) {
        HttpEntity<FlowStatsVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(FLOW_STATS_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod bandStats(BandStatsVO vo) {
        HttpEntity<BandStatsVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(BAND_STATS_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

    @Override
    public ResultVod storageStats(StorageStatsVO vo) {
        HttpEntity<StorageStatsVO> formEntity = new HttpEntity<>(vo , IMHttpHeaders.getJSON());
        ResponseEntity<ResultVod> entity = restTemplate.postForEntity(STORAGE_STATS_URL,formEntity,ResultVod.class);
        return entity.getBody();
    }

}
