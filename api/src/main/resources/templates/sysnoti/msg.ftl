<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>站内信</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/set.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <style>
        body{background-color: #f5f5f5;}
        .line{width:100%;padding:0.1rem 0.2rem;box-sizing: border-box;}
        .line p{width:100%;text-align: center;line-height: 0.6rem;}
        .line .con{width:100%;border-radius: 0.2rem;background-color: white;line-height: 0.5rem;padding:0.1rem 0.2rem;box-sizing: border-box}
        .line .con .n{color:#393b3b;font-size: 0.28rem;}
        .line .con .say{color:#666;font-size: 0.26rem;}
    </style>

</head>
<body>
<input type="hidden" id="basePath" value="${ctx}"/>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/message/return_message" style="color: white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">消息列表</span>
</div>
<div id="wrapper" style="z-index: 1;top:1rem;">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
        <div id="msgDiv" style="background-color: #f5f5f5;"></div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
</body>
<script src="${ctx}js/sysnoti/sysnoti.js" type="text/javascript"></script>
</html>
