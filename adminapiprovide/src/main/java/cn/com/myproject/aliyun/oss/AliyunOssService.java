package cn.com.myproject.aliyun.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import static org.bouncycastle.crypto.tls.ConnectionEnd.client;

/**
 * Created by liyang-macbook on 2017/7/24.
 */

@Component
public class AliyunOssService implements IAliyunOssService {
    private static Logger logger = LoggerFactory.getLogger(AliyunOssService.class);

    @Autowired
    private AliyunOssProp aliyunOssProp;

    @Override
    public OSSClient create() {
        return new OSSClient(aliyunOssProp.getEndpoint(),aliyunOssProp.getAccessKeyId(),aliyunOssProp.getAccessKeySecret());
    }

    @Override
    public void shutDown(OSSClient client) {
        client.shutdown();
    }

    @Override
    public String upload(String key,InputStream inputStream) {
        OSSClient ossClient = this.create();
        try {
            PutObjectResult result = ossClient.putObject(aliyunOssProp.getBucketName(),key,inputStream);
            logger.info(result.getRequestId());
            logger.info(result.getETag());
            this.shutDown(ossClient);
            return "https://appphoto.doers.cn/"+key;
        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorCode());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            logger.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "";

    }
}
