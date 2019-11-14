package cn.com.myproject.aliyun.oss;

import com.aliyun.oss.OSSClient;

import java.io.InputStream;

/**
 * Created by liyang-macbook on 2017/7/24.
 */
public interface IAliyunOssService {

    OSSClient create();

    void shutDown(OSSClient client);

    String upload(String key, InputStream inputStream);
}
