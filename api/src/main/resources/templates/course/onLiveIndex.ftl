<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>在播课程</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myLive.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/course/onLiveIndex.js?v=2" type="text/javascript"></script>
</head>
<body>
<input type="hidden" id="basePath" value="${ctx!}"/>
<div id="wrapper" style="z-index: 1;top:0rem;">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
        <div id="courseDiv" class='con3'></div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
</body>
</html>