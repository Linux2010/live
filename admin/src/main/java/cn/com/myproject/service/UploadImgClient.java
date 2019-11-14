package cn.com.myproject.service;

import cn.com.myproject.config.MultiFeignConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/3.
 */
//@FeignClient(value = "admin-api-provide",configuration = MultiFeignConfig.class)

public interface UploadImgClient {

//    @PostMapping(value = "/uploadImg",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String uploadImg(@RequestPart("upfile") MultipartFile file);

    @RequestLine("POST /uploadImg")
    String uploadImg(@Param("upfile") MultipartFile file,@Param("dir") String dir);


    @RequestLine("POST /netease/video/upload")
    Map<String,Object> uploadNeteaseVideoFile(@Param("file") MultipartFile file);
}
