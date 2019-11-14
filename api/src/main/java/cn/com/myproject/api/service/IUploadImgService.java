package cn.com.myproject.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * Created by liyang-macbook on 2017/7/27.
 */
public interface IUploadImgService {

    /**
     * 上传图片
     * @param file 文件
     * @param dir 目录，可以为空
     * */
    String uploadImg(MultipartFile file, String dir);

    String uploadImg(MultipartFile file);

    Map<String,Object> uploadNeteaseVideoFile(MultipartFile file);

}
