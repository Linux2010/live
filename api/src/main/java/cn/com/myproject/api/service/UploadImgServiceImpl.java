package cn.com.myproject.api.service;


import cn.com.myproject.api.util.FeignSpringFormEncoder;
import feign.Feign;
import feign.Request;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/3.
 */
@Service
public class UploadImgServiceImpl implements IUploadImgService {

    @Autowired
    private LoadBalancerFeignClient loadBalancerFeignClient;

    @Override
    public String uploadImg(MultipartFile file,String dir) {
        Request.Options options =  new Request.Options(10000,60000);
        UploadImgClient client =  Feign.builder()
                .encoder(new FeignSpringFormEncoder()).options(options)
                .client(loadBalancerFeignClient)
                .target(UploadImgClient.class, "http://admin-api-provide");

        //测试
//        UploadImgClient client =  Feign.builder()
//                .encoder(new FeignSpringFormEncoder())
//                .target(UploadImgClient.class, "http://127.0.0.1:3333");
        return client.uploadImg(file,dir);
    }

    @Override
    public String uploadImg(MultipartFile file) {
        return uploadImg(file,"");
    }

    @Override
    public Map<String, Object> uploadNeteaseVideoFile(MultipartFile file) {
        Request.Options options =  new Request.Options(10000,3600000);
        UploadImgClient client =  Feign.builder()
                .encoder(new FeignSpringFormEncoder())
                .client(loadBalancerFeignClient)
                .decoder(new GsonDecoder()).options(options)
                .target(UploadImgClient.class, "http://admin-api-provide");

        //测试

//       UploadImgClient client =  Feign.builder()
//               .encoder(new FeignSpringFormEncoder())
//                .decoder(new GsonDecoder()).options(options)
//               .target(UploadImgClient.class, "http://127.0.0.1:3333");

        return client.uploadNeteaseVideoFile(file);
    }
}