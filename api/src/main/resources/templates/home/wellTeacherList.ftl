<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>名师</title>
    <#--<base target="_self" href="http://192.168.1.108:90/ws_project/jiaotouxueyuan/">-->
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/classifyList.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
</head>
<body>
<div class="search">
    <div class="searchBox">
        <input type="text"/>
        <span  class="searchBtn">搜索</span>
    </div>
</div>
<input type="hidden" name="userTypeId" id="userTypeId" value="${userTypeId!}"/>
<div id="wrapper" style="z-index: 1;top:1rem;">
    <div class="scroller ">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
        <div id="contentDiv" class='con3 yijiclassify '>
        </div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
</body>
<script src="${ctx}js/home/wellTeacherList.js" type="text/javascript"></script>
</html>