<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>用户中心</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/user/conform1.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <style>
        html,body{width:100%;height: 100%;background-color: #050402}
        .body{width:100%;height:100%;background: url("${ctx}image/share0.png") no-repeat;background-position: center top;background-size:auto 100%;position: relative;}
        .body .line{width: 100%;height: 1.4rem;background-color: #050402;padding: 0 0.2rem;box-sizing: border-box;position: absolute;top: 75%;text-align: center;}
        .body .erweima{display: inline-block;width: 1.1rem;box-sizing: border-box;height: 1.1rem;border: 0.06rem solid #e2ceb3;vertical-align: middle;margin: 0.1rem 0;}
        .body .nameBox{    display: inline-block;width: 2.54rem;margin: 0.06rem;border: 0.02rem solid #e2ceb3;vertical-align: middle;border-left: 0;border-right: 0;}
        .body .nameBox .d1{line-height: 0.33rem; font-size: 0.16rem; font-weight: bold; color: #c2b17c;text-align: left;}
        .body .nameBox .d1 span{color:#c2b17c}
        .body .name{position: fixed;left: 3.5rem;bottom: 2.3rem;font-size: 0.3rem;font-weight: bold;color: #a19367;}
        .mask{width: 100%;height: 100%;position: fixed;top:0;display: none;background-color: black;opacity: 0.5;}
    </style>
</head>
<body>
<div class="body">
    <div class="line">
        <div class="erweima"><img id="qrImg" src="${qrCodeUrl}" style="width:100%;height: 100%"/></div>
        <div class="nameBox">
            <div class="d1">我是&ensp;<span>${userName}</span></div>
            <div class="d1">邀你一起学习</div>
            <div class="d1">长按二维码加入教头学院</div>
        </div>
    </div>
</div>
</body>
    <script type="text/javascript" src="${ctx}js/jquery.min.js"></script>
</html>