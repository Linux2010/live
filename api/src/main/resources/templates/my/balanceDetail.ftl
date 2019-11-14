<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <style>
        .line{padding:0.2rem;border-bottom:0.02rem solid #ccc}
        .line .tit{line-height: 0.4rem;display: inline-block;font-size: 0.26rem;}
        .line .pay{line-height: 0.8rem;float: right;font-size: 0.28rem}
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;}
        .con1 span{color:white;}
    </style>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/myBalance" style="color: white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">明细</span>
</div>
<a href="#">
    <input type="hidden" id="basePath" value="${ctx}"/>
    <div id="wrapper" style="z-index: 1;top:1rem;">
        <div class="scroller">
            <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
            <div id="balanceDetail" class='con3'></div>
            <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
        </div>
    </div>
</a>
</body>
<script src="${ctx}js/user/balance_log_list.js" type="text/javascript"></script>
</html>
