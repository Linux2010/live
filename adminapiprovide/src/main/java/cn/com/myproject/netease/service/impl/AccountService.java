package cn.com.myproject.netease.service.impl;


import cn.com.myproject.netease.VO.Result;
import cn.com.myproject.netease.VO.ResultInfo;
import cn.com.myproject.netease.VO.ResultUinfos;
import cn.com.myproject.netease.VO.account.*;
import cn.com.myproject.netease.service.IAccountService;
import cn.com.myproject.netease.util.IMHttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
@Service
public class AccountService implements IAccountService {

    private static String CREATE_URL = "https://api.netease.im/nimserver/user/create.action";

    private static String UPDATE_URL = "https://api.netease.im/nimserver/user/update.action";

    private static String REFRESHTOKEN_URL = "https://api.netease.im/nimserver/user/refreshToken.action";

    private static String BLOCK_URL = "https://api.netease.im/nimserver/user/block.action";

    private static String UNBLOCK_URL = "https://api.netease.im/nimserver/user/unblock.action";

    private static String UPDATEUNFO_URL = "https://api.netease.im/nimserver/user/updateUinfo.action";

    private static String GETUNFOS_URL = "https://api.netease.im/nimserver/user/getUinfos.action";

    private static String SETDONNOP_URL = "https://api.netease.im/nimserver/user/setDonnop.action";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultInfo create(IMCreateVO createVO) {

 //       createVO.setAccid(UUID.randomUUID().toString().replace("-",""));

        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(createVO.toMap() , IMHttpHeaders.get());

        ResponseEntity<ResultInfo> entity = restTemplate.postForEntity(CREATE_URL,formEntity,  ResultInfo.class);
        return   entity.getBody();
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        String url = "https://api.netease.im/nimserver/user/create.action";
//        HttpPost httpPost = new HttpPost(url);
//
//        String appKey = "b6ef7a173c2f9fb5ef43f2787984921c";
//        String appSecret = "b63a571e5494";
//        String nonce =  "12345";
//        String curTime = String.valueOf((new Date()).getTime() / 1000L);
//        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
//
//        // 设置请求的header
//        httpPost.addHeader("AppKey", appKey);
//        httpPost.addHeader("Nonce", nonce);
//        httpPost.addHeader("CurTime", curTime);
//        httpPost.addHeader("CheckSum", checkSum);
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 设置请求的参数
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("accid", "helloworld"));
//        try {
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//            // 执行请求
//            HttpResponse response = httpClient.execute(httpPost);
//
//            // 打印执行结果
//            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;

    }

    @Override
    public Result update(IMUpdateVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(UPDATE_URL,formEntity,  Result.class).getBody();
    }

    @Override
    public ResultInfo refreshToken(IMRefreshTokenVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(REFRESHTOKEN_URL , formEntity,   ResultInfo.class).getBody();
    }

    @Override
    public Result block(IMBlockVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(BLOCK_URL,formEntity,  Result.class).getBody();
    }

    @Override
    public Result unBlock(IMUnBlockVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(UNBLOCK_URL,formEntity,  Result.class).getBody();
    }

    @Override
    public Result updateUinfo(IMUpdateUinfoVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(UPDATEUNFO_URL,formEntity,  Result.class).getBody();
    }

    @Override
    public ResultUinfos getUinfos(IMGetUinfosVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(GETUNFOS_URL , formEntity,  ResultUinfos.class).getBody();
    }

    @Override
    public Result setDonnop(IMSetDonnopVO vo){
        HttpEntity<MultiValueMap<String,String>> formEntity = new HttpEntity<>(vo.toMap() , IMHttpHeaders.get());
        return restTemplate.postForEntity(SETDONNOP_URL,formEntity,  Result.class).getBody();
    }
}
