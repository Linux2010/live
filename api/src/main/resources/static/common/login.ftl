<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}swiper-3.3.1.min.js" type="text/javascript"></script>
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
    <span style="width: 100%;display: inline-block;margin: 0 auto;text-align: center;">登录</span>
</div>
<div class="con2">
    <p class="p1">欢迎来到教头学院</p>
</div>
<div class="con3">
    <div class="con3Con">
        <input type="text" placeholder="输入手机号" id="phoneNum">
    </div>
    <div class="con3Con">
        <input type="password" placeholder="输入登录密码" id="password">
    </div>
    <div class="con3Con">
        <input type="button" value="登录" style="background-color: #ffa42f;border-color: transparent;margin:0.4rem auto;" id="login">
    </div>
</div>
<div class="con4">
    <div class="con4Con">
        <span>忘记密码</span>
        <a href="common/zhuce.html"> <span style="float:right;">注册新用户</span></a>
    </div>
</div>

</body>
<script>
    $("#login").click(function () {

        var phoneNum = $("#phoneNum").val();
        var password = $("#password").val();
        $.ajax({
            async: false,
            url:"",
            data:{"phone": phoneNum, "password": password},
            type: "post",
            dataType: "json",
            success: function () {
                
            },
            error: function () {
                
            },
        });
    });

</script>
</html>