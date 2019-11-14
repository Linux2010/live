package cn.com.myproject.external.netease;

import cn.com.myproject.netease.VO.vod.*;
import cn.com.myproject.netease.service.IVodService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/21.
 */
@RestController
@RequestMapping("/netease/video")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private IVodService vodService;

    @RequestMapping("/upload")
    public HashMap<String,Object> upload(MultipartFile file) {
        logger.info("进入文件上传");
        InitUploadVO vo = new InitUploadVO();
        vo.setOriginFileName(file.getOriginalFilename());
        vo.setUserFileName(file.getOriginalFilename());
        vo.setUserDefInfo("1");
        ResultVod resultVod = vodService.initUpload(vo);
        if(resultVod.getCode() != 200) {
            logger.error(resultVod.getCode()+","+resultVod.getMsg());
            return null;
        }
        String xNosToken = resultVod.getRet().get("xNosToken").toString();
        String bucket = resultVod.getRet().get("bucket").toString();
        String object = resultVod.getRet().get("object").toString();
        logger.info(xNosToken+","+bucket+","+object);
        GetLbsReturnVO getLbsReturnVO = vodService.getLbs(bucket);
        if(StringUtils.isBlank(getLbsReturnVO.getLbs())){
            logger.error(getLbsReturnVO.getCode()+","+getLbsReturnVO.getMessage());
            return null;
        }
        String host = getLbsReturnVO.getUpload().get(0);
        logger.info(host);
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setX_nos_token(xNosToken);
        uploadFileVO.setBucket(bucket);
        uploadFileVO.setObject(object);
        uploadFileVO.setUploadHost(host);


        try {
            vodService.uploadFile(uploadFileVO,file.getInputStream(),file.getSize());
        } catch (IOException e) {
            logger.error("",e);
            e.printStackTrace();
            return null;
        }


        QueryVideoVO queryVideoVO = new QueryVideoVO();
        List<String> list = new LinkedList<>();
        list.add(object);
        queryVideoVO.setObjectNames(list);
        ResultVod resultVod1 = vodService.queryVideo(queryVideoVO);

        if(resultVod1.getCode() != 200) {
            logger.error(resultVod1.getCode()+","+resultVod1.getMsg());
            return null;
        }
        List<HashMap<String,Object>> _list = (List<HashMap<String, Object>>) resultVod1.getRet().get("list");
        logger.info("objectName:"+_list.get(0).get("objectName")+",vid:"+_list.get(0).get("vid"));
        return _list.get(0);
    }
}
