<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/set.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/user/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <style>
        *{font-size: 0.3rem;}
    </style>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href = "${ctx}wap/user/set_to_my" style="color:white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">设置</span>
</div>
<div class="con2">
    <a href="${ctx}wap/user/userInfo"> <div class="line1" style="display: flex;"><span class="s1">账户管理<span id="red_span" style="color: #ffa42f;">(资料待完善)</span></span> <span class="s2">></span> </div></a>
    <a href="${ctx}wap/user/updatePwdPage"> <div class="line1"><span class="s1">登录密码</span><span class="s2">></span></div></a>
    <a href="${ctx}wap/user/payPwdPage"> <div class="line1"><span class="s1">支付密码</span><span class="s2">></span></div></a>
<#-- <div class="line1"><span class="s1">清理缓存（1.6M）</span><span class="s2">></span> </div>-->
    <a href="${ctx}ios/convention.html"><div class="line1"><span class="s1">社区公约</span><span class="s2">></span> </div></a>
    <!--<div class="line1"><span class="s1">邀请好友</span><span class="s2">></span> </div>-->
   <#-- <div class="line1"><span class="s1">帮助</span><a href="common/help.html"> <span class="s2">></span></a></div>-->
    <a href="${ctx}ios/about_coach.html"><div class="line1" style="display: flex"><span class="s1">关于教头</span> <span class="s2">></span> </div></a>
</div>
<div class="button" style="width:90%;margin:3rem 5% 0;background-color: #ffa42f;color:white;font-size: 0.32rem;border-radius: 0.2rem;line-height: 0.7rem;text-align: center">退出登录</div>
</body>
<script src="${ctx}js/user/set.js" type="text/javascript"></script>
</html>
