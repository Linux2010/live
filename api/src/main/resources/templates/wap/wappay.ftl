<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/jquery.cookie.js" type="text/javascript" ></script>
    <style>
        html,body{width:100%;height: 100%}
        .body{width:100%;padding:0.2rem 0.4rem;height: 1.17rem;border:0.01rem solid #ccc;border-left: 0;border-right: 0;box-sizing: border-box;margin-top: 2rem;}
        .body .img1{width:0.65rem;vertical-align: middle;margin-right: 0.1rem;}
        .body .img2{width:0.44rem;float: right;vertical-align: middle;margin-top: 0.15rem;}
        .body .center{display: inline-block;line-height: 0.35rem;vertical-align: middle}
    </style>
</head>
<body >
<div class="body">
    <img class="img1" src="${ctx}image/weixinLogo.png"/>
    <div class="center">
        <p style="font-size: 0.3rem;">微信支付</p>
        <p style="font-size: 0.20rem;color:#666;">使用微信支付，安全便捷</p>
    </div>
    <img class="img2" src="${ctx}image/chosePayStyle.png"/>
</div>

<div class="body" style="border: 0;">
    <div id="pay" onclick="pay()" style="width:100%;font-size:0.26rem;background-color: #6ec850;text-align: center;line-height: 0.7rem;color: white;border-radius: 0.2rem;">确认支付</div></div>
    <div id="success" onclick="success()" style="display: none;
    width: 60%;
    font-size: 0.26rem;
    background-color: #6ec850;
    text-align: center;
    line-height: 0.7rem;
    color: white;
    border-radius: 0.2rem;
    margin:0.3rem 1rem 0.03rem 1.5rem;">支付成功</div></div>
    <div id="fail" onclick="javascript:history.go(-1);" style="display: none;
    width: 60%;
    font-size: 0.26rem;
    background-color: #6ec850;
    text-align: center;
    line-height: 0.7rem;
    color: white;
    border-radius: 0.2rem;
margin:0.3rem 1rem 0.03rem 1.5rem;">重新支付</div></div>


</body>
<script>

    $(function(){
           var v = $.cookie("orderNo");
           if(v != undefined && v == '${orderNo!}') {
               $("#pay").hide();
               $("#success").show();
               $("#fail").show();
           }
    });

    function pay(){
        $("#pay").hide();
        $("#success").show();
        $("#fail").show();
        $.cookie("orderNo", "${orderNo!}");
        location.href="${mweb_url!}";
    }
    function success(){
        location.href=decodeURIComponent("${returnUrl!}");
    }

    function fail () {
        location.href = history.go(-1);
    }

</script>
</html>