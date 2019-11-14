<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no"/>
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/getpwd.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/findPwd"><</a></span><span
        style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">重置支付密码</span>
</div>
<div class="con3">
    <div class="con3Con">
        <input class="newpwd" type="password" placeholder="请输入一个6位的新密码" maxlength="6" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
    </div>
    <div class="con3Con">
        <input class="requirepwd" type="password" placeholder="请再次输入" maxlength="6" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
    </div>
    <div class="con3Con">
        <input type="button" value="完成" class="submit">
    </div>
    <input type="hidden" id="phone" value="${phone}"/>
    <input type="hidden" id="code" value="${code}"/>
    <input type="hidden" id="coun_code" value="${coun_code}"/>
</div>
</body>
<script>
    <#--修改支付的密码校验-->
    function check() {
        var result = true;
        if ($(".newpwd").val() == null || $(".newpwd").val() == "") {
            alert("新密码不能为空！");
            result = false;
        } else {
            if ($(".requirepwd").val() == null || $(".requirepwd").val() == "") {
                alert("确认密码不能为空！");
                result = false;
            } else {
                if($(".requirepwd").val().length != 6 || $(".newpwd").val().length != 6 ){
                    alert("请输入6位密码");
                    result = false;
                }else{
                    if ($(".requirepwd").val() != $(".newpwd").val()) {
                        alert("两次密码输入不一样！");
                        result = false;
                    }
                }

            }

        }

        return result;
    }
    <#--修改支付密码的操作-->
    $(".submit").on("click", function () {
        if (!check())return;
        $.ajax({
            async: false,
            type: "POST",
            url: base + "user/findPayPwdbyphone",
            data: {
                'phone': $("#phone").val(),
                'code': $("#code").val(),
                'newpwd': $(".newpwd").val(),
                'countrise_code': $("#coun_code").val()
            },
            dataType: "json",
            success: function (data) {
                alert(data.message);
                if (data.result == 1) {
                    window.location.href = "${ctx}wap/user/set_to_my";
                }
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        })
    })
</script>
</html>
