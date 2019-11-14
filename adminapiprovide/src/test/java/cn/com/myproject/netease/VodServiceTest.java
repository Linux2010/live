package cn.com.myproject.netease;


import cn.com.myproject.netease.VO.vod.*;
import cn.com.myproject.netease.service.IVodService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liyang-macbook on 2017/7/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VodServiceTest {

    @Autowired
    private IVodService vodService;

    @Test
    public void testcreateType() {
        CreateTypeVO vo = new CreateTypeVO();
        vo.setTypeName("娱乐");
        vo.setDescription("测试");
        ResultVod vod = vodService.createType(vo);
        Assert.assertEquals(vod.getCode()+"","200");
    }

    @Test
    public void testgetLbs() {
        GetLbsReturnVO vo = vodService.getLbs("vodk32ywxdf");
        System.out.println(vo.getLbs());
    }

    @Test
    public void testUploadFile() throws FileNotFoundException {
        InitUploadVO vo = new InitUploadVO();
        vo.setOriginFileName("鬼吹灯之黄皮子坟01.mp4");
        vo.setUserFileName("鬼吹灯之黄皮子坟01.mp4");
        vo.setUserDefInfo("myproject.com.cn");
        ResultVod resultVod = vodService.initUpload(vo);
        if(resultVod.getCode() != 200) {
            System.out.println(resultVod.getCode()+","+resultVod.getMsg());
        }
        String xNosToken = resultVod.getRet().get("xNosToken").toString();
        String bucket = resultVod.getRet().get("bucket").toString();
        String object = resultVod.getRet().get("object").toString();
        System.out.println(xNosToken+","+bucket+","+object);
        GetLbsReturnVO getLbsReturnVO = vodService.getLbs(bucket);
        if(StringUtils.isBlank(getLbsReturnVO.getLbs())){
            System.out.println(getLbsReturnVO.getCode()+","+getLbsReturnVO.getMessage());
        }
        String host = getLbsReturnVO.getUpload().get(0);
        System.out.println(host);
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setX_nos_token(xNosToken);
        uploadFileVO.setBucket(bucket);
        uploadFileVO.setObject(object);
        uploadFileVO.setUploadHost(host);
        File file = new File("/Users/liyang-macbook/Downloads/鬼吹灯之黄皮子坟01.mp4");
        InputStream inputStream = new FileInputStream(file);

        vodService.uploadFile(uploadFileVO,inputStream,file.length());


        QueryVideoVO queryVideoVO = new QueryVideoVO();
        List<String> list = new LinkedList<>();
        list.add(object);
        queryVideoVO.setObjectNames(list);
        ResultVod resultVod1 = vodService.queryVideo(queryVideoVO);

        if(resultVod1.getCode() != 200) {

            System.out.println(resultVod1.getCode()+","+resultVod1.getMsg());
        }

        List<HashMap<String,String>> _list = (List<HashMap<String, String>>) resultVod1.getRet().get("list");

        System.out.println(_list.get(0).get("objectName"));
    }

    @Test
    public void testGson() {
        String str = "[{\"objectName\":\"33cf71b1-86ac-4555-a071-d70db07b9685.mp4\",\"vid\":1008}]";
        Gson gson = new GsonBuilder().create();

        List<HashMap<String,String>> _list = gson.fromJson(str,new TypeToken<List<HashMap<String,String>>>(){}.getType());

        System.out.println(_list.size());
    }
}
