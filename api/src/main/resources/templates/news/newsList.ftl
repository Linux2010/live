<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>商业内容</title>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/classifyList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/user/conform.js"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <style>
        html,body{
            width:100%;
            padding: 0.2rem;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<input type="hidden" id="basePath" value="${ctx}"/>
<div id="wrapper" style="z-index: 1;top:0rem;">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>下拉加载...</span></div>
        <div class="zhibo"> <div id="newsList"></div></div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}js/news/newsList.js"></script>
</html>