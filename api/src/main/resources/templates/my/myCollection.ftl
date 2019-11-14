<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}css/ace.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myCollection.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/my/myCollection.js"></script>

</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;" id="backMy"><</span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">我的收藏</span>
</div>

<input type="hidden" id="basePath" value="${ctx}"/>

<div id="wrapper" style="z-index: 1;top:1rem;">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>下拉加载...</span></div>
        <div id="myCollection"></div>
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