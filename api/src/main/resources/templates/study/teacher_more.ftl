<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/todayLearn.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <script src="${ctx}js/study/teacher_more.js"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <style>
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;}
        .con1 span{color:white;}
    </style>
</head>
<body>


<input type="hidden" id="basePath" value="${ctx}"/>

<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/study/today_recomm" style="color: white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">精英教头</span>
</div>

<div id="wrapper" style="z-index: 1;top:1rem;">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
        <div id="teacherDiv" class='con3'></div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>

</body>
<script>
</script>
</html>