package cn.com.myproject.service;

import cn.com.myproject.AdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by liyang-macbook on 2017/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdminApplication.class)
@WebAppConfiguration
public class UploadImageServiceTest {
    @Autowired
    private IUploadImgService uploadImgService;

    @Test
    public void testuploadImgFile() {
        File file = new File("/Users/liyang-macbook/Downloads/WechatIMG1331.jpeg");
        MultipartFile multi = null;
        try {
            multi = new MockMultipartFile(file.getName(),file.getName(), MediaType.IMAGE_JPEG_VALUE, new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        uploadImgService.uploadImg(multi,"test");
    }
}
