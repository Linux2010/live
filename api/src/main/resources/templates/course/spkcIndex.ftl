<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/videoCourseList.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll2.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}js/conform.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <title>教头学院</title>
    <style type="text/css">
        html,body{
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
        .nav1{
            width: 100%;
            overflow: hidden;
        }
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
        <span style="width: 90%;display: inline-block;margin: 0 auto;text-align: center;padding-right: 0.64rem;">视频课程</span>
    </div>
</div>
<nav class="nav" style="margin-top: 1rem;">
    <div id="wrapper">
        <div id="scroller">
            <ul class="category_nav" id="ctUl"></ul>
        </div>
    </div>
</nav>
<div class="nav1">
    <div id="wrapper1">
        <div class="scroller1">
            <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
            <div class="category_nav">
                <div class="search">
                    <div class="searchBox">
                        <input type="text" id="courseTitle"/>
                        <span id="searchCourse">搜索</span>
                    </div>
                </div>
                <img id="ctImg" style="width:100%;height: 3.4rem;">
                <div id="courseDiv" class="lines"></div>
            </div>
            <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
<script src="${ctx}js/course/spkcIndex.js?v=2" type="text/javascript"></script>
</html>