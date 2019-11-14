<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>用户中心</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <style>
        html,body{width:100%;height: 100%}
        .body{width:100%;height:100%;}
        .body .title{width:100%;text-align: center;line-height: 1rem;font-size: 0.4rem;background-color: #22272a;color: white;overflow: hidden;position: fixed;top:0;z-index: 40;}
        .body .time{width:100%;line-height: 0.6rem;text-align: right;margin-bottom: 0.3rem;padding-top: 1rem;font-size: 0.26rem;}
        .body .time b{padding-right: 0.2rem;}
        .body .p{width:100%;box-sizing: border-box;padding: 0.2rem 0.8rem;line-height: 0.5rem;text-indent: 2em;font-size: 0.28rem;}
    </style>
</head>
<body >
    <div style="text-align:center;">
        <img style="width: 3rem;display: block;margin: 0 auto;margin-top: 3rem;" src="${ctx}image/payFail.png">
        <h3 style="font-size: 0.6rem;margin: 0.5rem;">支付失败！</h3>
        <div style=""><a href="<#if backUrl??>${backUrl!}<#else>javascript:go(-1)</#if>"> 返回</a></div>
    </div>
</body>
<script>
    var v1 = new Vue({
        el:".body",
        data:{
            title:"会员权益",
            time:"",
            p:""
        }
    });
</script>
</html>