package cn.com.myproject.api.pay.controller;

import cn.com.myproject.api.service.ICourseService;
import cn.com.myproject.api.service.IRechargeMemberService;
import cn.com.myproject.api.service.IUserService;
import cn.com.myproject.api.service.admincon.IProfitShareDealService;
import cn.com.myproject.api.service.pay.IPayCallBackService;
import cn.com.myproject.api.service.user.IUserRechargeWithrawService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @auther CQC
 * @create 2017.8.31
 */
@RestController
@Deprecated
@RequestMapping(value = "/api/paycallback_old_no_use")
@Api(value="支付回调",tags = "支付")
public class PayCallBackController {

    @Autowired
    private AliPayProp aliPayProp;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IRechargeMemberService rechargeMemberService;

    @Autowired
    private IUserRechargeWithrawService rwService;

    @Autowired
    private IProfitShareDealService profitShareDealService;

    @PostMapping("/alipay")
    public String alipay(HttpServletRequest request){

        Map<String,String> params = new HashMap<String,String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        try {

            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean flag = AlipaySignature.rsaCheckV1(params, aliPayProp.getAlipayPublicKey(), "utf-8","RSA2");
            if(flag) {
                //FIXME 进行业务处理

                //原支付请求的商户订单号
                String outTradeNo = params.get("out_trade_no");
                String transaction_id = params.get("transaction_id");

                if(StringUtils.isNotBlank(outTradeNo)){
                    String firstPlace = outTradeNo.substring(0,1);
                    int reInt=0;
                    if("a".equals(firstPlace)){ //课程订单结算订单编号

                        reInt = courseService.modifyPayStatus(outTradeNo,transaction_id);

                    }else if("b".equals(firstPlace)){ //打赏教头结算订单编号

                        reInt = userService.zfbReturnSuccessUpdateRewardOrderStatus(outTradeNo,transaction_id);

                    }else if("c".equals(firstPlace)){//购买VIP会员订单编号

                        reInt = rechargeMemberService.setPayFinishRechargeMemberByOrderOn(outTradeNo,transaction_id);

                    }else if("d".equals(firstPlace)){ //充值银两

                        reInt = rwService.setPayFinishRecharge(outTradeNo.substring(1),transaction_id);

                    }

                    if(0 == reInt){
                        return "fail";
                    }
                }

                return "success";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping("/paypad")
    public String paypad(HttpServletRequest request){
        Enumeration en = request.getParameterNames();
        String str = "";
        while(en.hasMoreElements()){
            String paramName = (String)en.nextElement();
            String paramValue = request.getParameter(paramName);
            try {
                str = str + paramName + "=" + URLEncoder.encode(paramValue, "utf-8") + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        str+="cmd=_notify-validate";
        URL u = null;
        URLConnection uc = null;
        try {
            //在 Sandbox 情况下，设置：
            //u= new URL("https://www.sandbox.paypal.com/cgi-bin/webscr");
            u = new URL("https://www.paypal.com/cgi-bin/webscr");
            uc = u.openConnection();
            uc.setDoOutput(true);
            uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            PrintWriter pw = new PrintWriter(uc.getOutputStream());
            pw.println(str);
            pw.close();

            BufferedReader in= new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String res = in.readLine();
            in.close();

            String itemName = request.getParameter("item_name");
            String itemNumber = request.getParameter("item_number");
            String paymentStatus = request.getParameter("payment_status");
            String paymentAmount = request.getParameter("mc_gross");
            String paymentCurrency = request.getParameter("mc_currency");
            String txnId = request.getParameter("txn_id");
            String receiverEmail = request.getParameter("receiver_email");
            String payerEmail = request.getParameter("payer_email");

            String custom = request.getParameter("custom");
            String transaction_id =  txnId; //交易ID
            if(res.equals("VERIFIED")) {

                //检查付款状态
                //检查 txn_id 是否已经处理过
                //检查 receiver_email 是否是您的 PayPal 账户中的 EMAIL 地址
                //检查付款金额和货币单位是否正确
                //处理其他数据，包括写数据库

                if(!"Completed".equals(paymentStatus)){
                    return "fail";
                }


                if(StringUtils.isNotBlank(custom)){
                    String firstPlace = custom.substring(0,1);
                    int reInt=0;
                    if("a".equals(firstPlace)){ //课程订单结算订单编号

                        reInt = courseService.modifyPayStatus(custom,transaction_id);

                    }else if("b".equals(firstPlace)){ //打赏教头结算订单编号

                        reInt = userService.zfbReturnSuccessUpdateRewardOrderStatus(custom,transaction_id);

                    }else if("c".equals(firstPlace)){//购买VIP会员订单编号

                        reInt = rechargeMemberService.setPayFinishRechargeMemberByOrderOn(custom,transaction_id);

                    }else if("d".equals(firstPlace)){ //充值银两

                        reInt = rwService.setPayFinishRecharge(custom.substring(1),transaction_id);

                    }

                    if(0 == reInt){
                        return "fail";
                    }else{
                        return "success";
                    }

                }


            }else if(res.equals("INVALID")) {
                //非法信息，可以将此记录到您的日志文件中以备调查
            }else {
                //处理其他错误
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
