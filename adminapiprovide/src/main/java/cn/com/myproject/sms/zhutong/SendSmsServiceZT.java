package cn.com.myproject.sms.zhutong;


import cn.com.myproject.sms.ISendSmsService;
import cn.com.myproject.sms.zhutong.util.HttpRequest;
import cn.com.myproject.sms.zhutong.util.MD5Gen;
import cn.com.myproject.sms.zhutong.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Component
public class SendSmsServiceZT implements ISendSmsService {

	private static final Logger logger = LoggerFactory.getLogger(SendSmsServiceZT.class);


	@Autowired
	private ZhuTongSmsProp zhuTongSmsProp;


	private boolean _sendSms(String mobile,String content,String proucetId) {
		String tkey = TimeUtil.getNowTime("yyyyMMddHHmmss");
		try{
			content=URLEncoder.encode(content+zhuTongSmsProp.getSignature(),"utf-8");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("转码错误",e);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("username=").append(zhuTongSmsProp.getUsername())
				.append("&password=").append(MD5Gen.getMD5(MD5Gen.getMD5(zhuTongSmsProp.getPassword())+tkey))
				.append("&tkey=").append(tkey).append("&mobile=").append(mobile).append("&content=")
				.append(content).append("&productid=").append(proucetId)
				.append("&xh");
		try{
			String ret= HttpRequest.sendPost(zhuTongSmsProp.getUrl(), sb.toString());
			if(ret.startsWith("1,")) {
				return true;
			}else {
				logger.error("发送失败，错误代码："+ret);
			}
		}catch (Exception e){
			logger.error("转码错误",e);
		}
		return false;
	}

	private boolean _batchSendSms(List<String> mobile,String content,String proucetId) {
		String tkey = TimeUtil.getNowTime("yyyyMMddHHmmss");
		try{
			content=URLEncoder.encode(content+zhuTongSmsProp.getSignature(),"utf-8");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("转码错误",e);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("username=").append(zhuTongSmsProp.getUsername())
				.append("&password=").append(MD5Gen.getMD5(MD5Gen.getMD5(zhuTongSmsProp.getPassword())+tkey))
				.append("&tkey=").append(tkey).append("&mobile=").append(StringUtils.join(mobile,",")).append("&content=")
				.append(content).append("&productid=").append(proucetId)
				.append("&xh");
		try{
			String ret= HttpRequest.sendPost(zhuTongSmsProp.getBatchurl(), sb.toString());
			if(ret.startsWith("1,")) {
				return true;
			}else {
				logger.error("发送失败，错误代码："+ret);
			}
		}catch (Exception e){
			logger.error("转码错误",e);
		}
		return false;
	}

	@Override
	public boolean sendSms(String mobile,String content) {
		return _sendSms(mobile,content,zhuTongSmsProp.getProductid());
	}

	@Override
	public boolean sendSmsNotify(String mobile, String content) {
		return _sendSms(mobile,content,zhuTongSmsProp.getProductid2());
	}

	@Override
	public boolean sendSmsMarketing(String mobile, String content) {
		return _sendSms(mobile,content,zhuTongSmsProp.getProductid3());
	}

	@Override
	public boolean batchSendSms(List<String> mobile, String content) {
		return _batchSendSms(mobile,content,zhuTongSmsProp.getProductid());
	}

	@Override
	public boolean batchSendSmsNotify(List<String> mobile, String content) {
		return _batchSendSms(mobile,content,zhuTongSmsProp.getProductid2());
	}

	@Override
	public boolean batchSendSmsMarketing(List<String> mobile, String content) {
		return _batchSendSms(mobile,content,zhuTongSmsProp.getProductid3());
	}


	private boolean _sendGSms(String mobile,String content,String proucetId) {
		//String tkey = TimeUtil.getNowTime("yyyyMMddHHmmss");
		try{
			content=URLEncoder.encode(content+zhuTongSmsProp.getSignature(),"utf-8");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("转码错误",e);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("username=").append(zhuTongSmsProp.getGusername())
				.append("&password=").append(MD5Gen.getMD5(zhuTongSmsProp.getGpassword()))
				.append("&mobile=").append(StringUtils.join(mobile,",")).append("&content=")
				.append(content).append("&productid=").append(proucetId);
		try{
			String ret= HttpRequest.sendPost(zhuTongSmsProp.getGurl(), sb.toString());
			if(ret.startsWith("1,")) {
				return true;
			}else {
				logger.error("发送失败，错误代码："+ret);
			}
		}catch (Exception e){
			logger.error("",e);
		}

		return false;
	}

	@Override
	public boolean sendGsms(String mobile,String content) {
		return _sendGSms(mobile,content,zhuTongSmsProp.getProductIdG());
	}

	@Override
	public boolean batchSendGsms(List<String> mobile, String content) {
		return _sendGSms(StringUtils.join(mobile,","),content,zhuTongSmsProp.getProductIdG());
	}

}
