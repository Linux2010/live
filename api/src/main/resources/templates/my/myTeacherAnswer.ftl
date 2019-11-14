<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myTeacherAnswer.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <style>
        #wrapper{top:1.6rem;}
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;}
        .con1 span{color:white;}
    </style>
</head>
<body>

<input type="hidden" name="userIdentity" id="userIdentity" value="${userIdentity!}"/>
<div class="con1">
    <a href="javascript:history.go(-1)"> <span style="width: 20%;display: inline-block;"><</span></a><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">教头问答</span>
</div>
<div class="con2">
    <span class="con2Select active">我的提问</span>
    <span class="con2Select">提问我的</span>
</div>
<input type="hidden" id="basePath" value="${ctx}"/>
<div id="wrapper" style="">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>

        <div id="myCourse" class="con3">
       <#--我的提问-->
            <div class="con3Select" id="flag0" style="display: block">
            </div>

        <#--提问我的-->
            <div class="con3Select" id="flag1">
            </div>

        </div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>

<div id="comments" style="display:none">
    <div class="write" style="z-index: 999;" >
        <div class="f" id="bottomDiv">
            <div class="writeBox1">写下你的提问（100字以内）...</div>
        </div>
        <div class="b" style="display: none">
            <textarea class="writeBox4" rows="3" placeholder="写下你的提问（100字以内）..."></textarea>
            <span id="addComm">发送</span>
        </div>
    </div>
</div>
</body>
<script src="${ctx}js/my/myTeacherAnswer.js"></script>
</html>