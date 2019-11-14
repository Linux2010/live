<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/classifyList.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/course/zbkcIndex.js?v=3" type="text/javascript"></script>
    <style type="text/css">
        .con1{background-color: #22272a;padding:0 0.2rem;font-size: 0.34rem;line-height: 0.45rem;position: fixed;top:0;width:100%;z-index: 1;}
        .con1 .con1Select{margin:0 auto;display: flex;}
        .con1 .con1Select span{border-bottom:0.03rem solid transparent;text-align: center;padding: 0.2rem 0;color:white;}
        .con1 .con1Select span.active{border-color:#ffa42f;color:#ffa42f}
    </style>
</head>
<body>
<input type="hidden" id="basePath" value="${ctx!}"/>
<div class="con1">
    <div class="con1Select">
        <span style="width: 10%;display: inline-block;" onclick="backFuc()"><</span>
        <span style="width: 90%;display: inline-block;margin: 0 auto;text-align: center;padding-right: 0.64rem;">直播课程</span>
    </div>
</div>
<div id="wrapper" style="z-index: 1;top:1rem;">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
        <div>
            <div class="search">
                <div class="searchBox">
                    <input type="text" id="courseTitle"/>
                    <span id="searchSpan">搜索</span>
                </div>
            </div>
            <div id="courseDiv" class='con3'></div>
        </div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
</body>
</html>