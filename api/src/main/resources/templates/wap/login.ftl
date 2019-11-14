<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>登录-教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script type="text/javascript"  src="${ctx}js/validator.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/base.js"></script>
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
        .con3 .con3Con .span1{text-align: center;display: inline-block;width:1.5rem;border-bottom-left-radius: 0.1rem;border:0.01rem solid #ffa42f;border-right:0;border-top-left-radius: 0.1rem;background-color: #ffa42f;vertical-align:middle;font-size:0.3rem;color:white;line-height:0.745rem}
        .con3 .con3Con .span2{text-align: center;display: inline-block;width:2rem;border-bottom-right-radius: 0.1rem;border-top-right-radius: 0.1rem;background-color: #ccc;vertical-align:middle;color:white;font-size:0.3rem;line-height:0.78rem;border:0.01rem;border-right: 0;}
        .con3 .con3Con .input1{text-align: center;display: inline-block;width:4.65rem;border-bottom-left-radius: 0;border-top-left-radius: 0;line-height: 0.77rem;vertical-align: middle;border:0.01rem solid #ccc}
        .con3 .con3Con .input2{text-align: center;display: inline-block;width:3.5rem;border-bottom-right-radius: 0;border-top-right-radius: 0;line-height: 0.77rem;vertical-align: middle;border:0.01rem solid #ccc}
    </style>
</head>
<body>
<div class="con1">
    <span style="width: 100%;display: inline-block;margin: 0 auto;text-align: center;">登录</span>
</div>
<div class="con2">
    <p class="p1">欢迎来到教头学院</p>
</div>
<p style="text-align: center;color: red;">${message!}</p>
<div class="con3">
    <form action="${ctx}wap/wx/userLogin" method="post" id="userLoginForm">
        <div class="con3Con">
            <div style="margin: 0.2rem auto;">
                <input type="hidden" name="flagVal" value="0">
                <input type="hidden" id="counttriesCode" name="counttriesCode"/>
                <span class="span1" id="sel_city">选择地区</span><input class="input1" type="text" id="phoneNum" name="phoneNum" placeholder="请输入手机号">
            </div>
        </div>
        <div class="con3Con">
            <input type="password" placeholder="输入登录密码" id="password" name="password">
        </div>
        <div class="con3Con">
            <input type="button" onclick="subFuc()" value="登录" style="background-color: #ffa42f;border-color: transparent;margin:0.4rem auto;" id="login">
        </div>
    </form>
</div>
<div class="con4">
    <div class="con4Con">
        <a href="${ctx}wap/user/findPwd"> <span>忘记密码?</span></a>
        <a href="${ctx}wap/wx/zhuce"> <span style="float:right;">注册新用户</span></a>
    </div>
</div>

</body>
<script>
    var jsonStr = "";
    $.ajax({
        url: "${ctx}wap/wx/countryNumber",
        type: "POST",
        dataType: "json",
        async: false,
        success: function (data) {
            if (data != null && data.length > 0 ) {
                for (var i = 0; i < data.length; i++) {
                    jsonStr += "{\"num\":\"+" + data[i].international + "\",\"name\":\"" + data[i].regionName + "\"},";
                }
                jsonStr = jsonStr.substring(0, jsonStr.length - 1);
                jsonStr = "[" + jsonStr + "]";
            }
        },
        error: function (data) {
            console.info("error");
        }
    })



    // 手机号区域
    var nameEl = document.getElementById('sel_city');
    function creatList(obj, list){
        obj.forEach(function(item, index, arr){
            var temp = new Object();
            temp.text = item.name;
            temp.num = item.num;
            temp.value = index;
            list.push(temp);
        })
    }

    var data = [];
    var picker;
    var text;
    if(jsonStr.length>1){
        creatList(JSON.parse(jsonStr), data);
    }
    picker = new Picker({
        data: [data],
        selectedIndex:[0],
        title: '地址选择'
    });
    picker.on('picker.select', function (selectedVal, selectedIndex) {
        text = data[selectedIndex[0]].num;
        nameEl.innerText = text;
    });
    nameEl.addEventListener('click', function () {
        if(jsonStr.length < 1){
            alert("未设置地区区号");
            return;
        }

        picker.show();
    });

    // 提交表单
    function subFuc(){
        if($("#sel_city").text() != "选择地区"){
            $("#counttriesCode").val($("#sel_city").text().substr(1,3));// 给区号赋值
        }else{
            alert("请选择地区");
            return false;
        }
        var phoneNumVal = $("#phoneNum").val();
        var passwordVal = $("#password").val();
        if(phoneNumVal == null || phoneNumVal == ""){// 验证手机号不为空
            alert("请输入手机号");
            return false;
        }
        if(passwordVal == null || passwordVal == ""){// 验证密码不为空
            alert("请输入密码");
            return false;
        }
        $("#userLoginForm").submit();// 提交表单
    }
</script>
</html>