package cn.com.myproject.external.pay;

import cn.com.myproject.pay.AliPayProp;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * Created by liyang-macbook on 2017/8/17.
 */
@RestController
@RequestMapping("/paycallback")
public class PayCallBackController  {

    private static final Logger logger = LoggerFactory.getLogger("PayCallBackController");

    @Autowired
    private AliPayProp aliPayProp;

    @PostMapping("/alipay")
    public String alipay(HttpServletRequest request) {
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
                

                return "success";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping("/paypal")
    public String paypal(HttpServletRequest request) {
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

            if(res.equals("VERIFIED")) {
               //检查付款状态
               //检查 txn_id 是否已经处理过
               //检查 receiver_email 是否是您的 PayPal 账户中的 EMAIL 地址
               //检查付款金额和货币单位是否正确
                 //处理其他数据，包括写数据库
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


        return null;
    }
}
