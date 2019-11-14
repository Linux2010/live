<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myfollow.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/my/myfollow.js"></script>
    <style>
    </style>
</head>
<body>

<div class="con1">
    <a href="javascript:history.go(-1)"><span style="width: 20%;display: inline-block;" id="backMy"><</span></a><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">我的关注</span>
</div>
<div class="con3">
    <span class="con3Select active">关注我的</span>
    <span class="con3Select">我的关注</span>
</div>
<div id="wrapper" style="">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>

        <div id="myCourse" class="con2">
        <#--关注我的-->
            <div class="con2Select" id="flag0" style="display: block;  width: 100%;">
            </div>

        <#--我的关注-->
            <div class="con2Select" id="flag1" style="display: none;  width: 100%;">

            </div>

        </div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
</body>
<script>
    $("#backMy").click(function () {
        window.location.href = "${ctx}wap/user/set_to_my";
    })
</script>
</html>