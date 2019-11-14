<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>结算</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/baoming.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/course/jiesuan.js" type="text/javascript"></script>
</head>
<body>
<input type="hidden" id="basePath" value="${ctx!}"/>
<input type="hidden" id="courseId" value="${courseId!}"/>
<input type="hidden" id="courseType" value="${courseType!}"/>
<div class="balance">
    <span class="s1" id="totalMoney"></span><div class="s2" onclick="showPayCoPage()">结算</div>
</div>
<div class="body">
    <div class="line1">
        <div class="p1" id="nameDiv"></div>
        <div class="p1" id="phoneDiv"></div>
        <div class="p1" id="mailDiv"></div>
    </div>
    <div class="line2">
        <img class="img1" id="tPhoto"/><span class="name" id="tName"></span>
        <div class="con3">
                <div class="con3Item">
                    <img class="con3ItemLeft" id="courseCover">
                    <div class="con3ItemRight">
                        <span class="con3ItemTitle" id="courseTitle"></span>
                        <div class="con3ItemBody">
                            <p id="courseBeginTime" style="line-height: 0.5rem"></p>
                            <p id="costPrice" style="font-size: 0.34rem;color:#ffa42f;text-align: right"></p>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>
</body>
</html>