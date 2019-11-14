<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myTeam.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <style>
        html, body {
            position: relative;

        }
        body {
            background: #eee;
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
            font-size: 14px;
            color:#000;
            margin: 0;
            padding: 0;
        }
        .swiper-container {
            width: 100%;
            height: 3rem;
            margin-top: 0.85rem;
        }
        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;

            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }
    </style>
</head>
<body>

<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/set_to_my"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">我的团队</span>
</div>
<div class="con2">
<#if personal_user??>
    <img class="con2Img" src="${personal_user.photo!}">
    <#if personal_user.userIdentity?? && personal_user.userIdentity == 2>
        <img src="${ctx}image/v.png" style="margin-left: -0.3rem; margin-top: 0.25rem;vertical-align: middle;width: 0.4rem;"/>
    </#if>
    <div class="con2Con">
        <div class="usrName"  style="display: block">${personal_user.userName!}</div>
        <div class="usrWrite">${personal_user.signature!}</div>
    </div>
</#if>
</div>
<div class="con22">
    <div class="con22Tit">
        <span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;margin-left: 20%;color:#ffa42f;font-size: 0.34rem;">我的上级</span>
    </div>
        <#if superior_user??>
            <img class="con22Img" src="${superior_user.photo!}"  style="box-sizing: border-box;border: 0.02rem solid #999">
            <#if superior_user.userIdentity?? && superior_user.userIdentity == 2>
                <img src="${ctx}image/v.png" style="margin-left: -0.3rem; margin-top: 0.25rem;vertical-align: middle;width: 0.4rem;"/>
            </#if>
            <div class="con22Con">
                <div class="usrName" style="display: block">${superior_user.userName!}</div>
                <div class="usrWrite">${superior_user.signature!}</div>
            </div>
        </#if>
</div>
<div class="con3">
    <div class="con3Tit">
        <span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;margin-left: 20%;color:#ffa42f;font-size: 0.34rem;">二级用户</span><span style="width: 20%;font-size:0.26rem;color:#999;display: inline-block;text-align: right"><#if  (second_user?size > 0)><a href="${ctx}wap/myteam/second_more_list">更多 》</a></#if></span>
    </div>

    <div class="con3Con">
        <#if second_user??>
                <#list second_user as second>
                    <div><img src="${second.photo!}"  style="box-sizing: border-box;border: 0.02rem solid #999">
                        <#if second.userIdentity?? && second.userIdentity == 2>
                            <img src="${ctx}image/v.png" style="margin-left: -0.3rem; margin-top: -0.25rem;vertical-align: middle;width: 0.4rem;height:0.4rem;"/>
                         </#if>
                        <p>${second.userName!}</p></div>
                    <#--<div><img src="${ctx}image/miss2.png"><p>翱翔的翱翔的鹰翱翔的鹰翱</p></div>
                    <div><img src="${ctx}image/miss3.png"><p>翱翔的鹰</p></div>-->
                </#list>
        </#if>
    </div>
</div>
<div class="con4">
    <div class="con4Tit">
        <span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;margin-left: 20%;color:#ffa42f;font-size: 0.34rem;">三级用户</span>
    </div>
    <div class="con4Con">
        <#if third_user_count??>
        总人数：#{third_user_count!}人
        </#if>
        <!--<div><img src="image/logo.png"><p>翱翔的鹰</p></div>-->
        <!--<div><img src="image/logo.png"><p>翱翔的翱翔的鹰翱翔的鹰翱</p></div>-->
        <!--<div><img src="image/logo.png"><p>翱翔的鹰</p></div>-->
    </div>
</div>
</body>
<script>
</script>
</html>