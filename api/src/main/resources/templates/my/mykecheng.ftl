<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myClass.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/my/mykecheng.js"></script>
    <style>
        #wrapper{top:1.6rem;background-color: #f5f5f5;}
        .scroller{background-color: #f5f5f5;}
    </style>
</head>
<body>
<input type="hidden" id="basePath" value="${ctx}"/>
<input type="hidden" id="userType" value="${userType!}"/>
<div class="con1">
    <span style="width: 20%;display: inline-block;" id="backMy"><</span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">我的课程</span>
</div>
<div class="con2">
    <span class="con2Select active">发布的课程</span>
    <span class="con2Select">购买的课程</span>
</div>
<div id="wrapper" style="">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>

        <div id="myCourse" class="con3">
       <#--发布的课程-->
            <div class="con3Select" id="flag1" style="display: block;background-color: #f5f5f5;">
                <#--<div class="class">-->
                    <#--<img class="classLeft" src="${ctx}image/miss1.png"/>-->
                <#--</div>-->
                <#--<div class="class">-->
                    <#--<img class="classLeft" src="${ctx}image/miss2.png"/><div class="classRight">如何练就毛泽东的胸怀</div>-->
                <#--</div>-->
            </div>

        <#--我的毕业课程-->
            <div class="con3Select" id="flag2" style="background-color: #f5f5f5;">

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