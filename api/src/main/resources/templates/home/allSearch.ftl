<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/allSearch.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js"></script>
    <style>
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;}
        .con1 span{color:white;}
    </style>
</head>
<body>
<input type="hidden" id="userIdentity" name="userIdentity" value="${userIdentity!}"/>
<div class="search">
    <div class="searchBox">
        <div class="searchType">
            <span class="searchTypeBtn">
                <span class="searchTypeName">教头</span><img src="${ctx}image/xiasanjiao.png">
            </span>
            <div class="searchTypeList" style="display: none">
                <ul>
                    <li style="border:none;"><img class="listImg" src="${ctx}image/book.png"><span class="list">课程</span></li>
                    <li><img class="listImg" src="${ctx}image/we.png"><span class="list">教头</span></li>
                    <#--<li><img class="listImg" src="${ctx}image/pack.png"><span class="list">商品</span></li>-->
                </ul>
                <img src="${ctx}image/shangsanjiao.png" class="returnsanjiao"/>
            </div>
        </div>
        <input type="text"/>
    </div>
    <span class="searchBtn">搜索</span>
</div>
<#--<div class="hotsearch">热搜</div>-->
<div class="searchCon">
    <#--<p class="p">搜索结果</p>-->
    <div class="kong" style="display: none">
        <img src="${ctx}image/kong.png" style="width: 6.2rem;margin: 1rem auto;display: block;"/>
        <p style="width:100%;text-align: center;font-size: 0.26rem;line-height: 0.8rem;">没有搜索到内容</p>
    </div>
    <div class="searchs">
        <input type="hidden" name="userIdentity" id="userIdentity" value="${userIdentity!}"/>
        <div id="wrapper" style="z-index: 1;top:1rem;">
            <div class="scroller">
                <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
                <div id="contentDiv" class='con3'>
                </div>
                <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
            </div>
        </div>

    </div>
</div>
</body>
<script>
</script>
<script src="${ctx}js/home/allSearch.js" type="text/javascript"></script>
</html>