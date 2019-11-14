<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/sign.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}ios/js/conform1.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
</head>
<body>

<div class="signBox">
    <div class="title1">每日签到送积分</div>
    <div class="title2">签到换积分 签到换奖品</div>
    <img class="date" src="${ctx}image/sign_date.png"/>
    <img class="button" onclick="success()" src="${ctx}image/sign_btn.png"/>
    <div class="line1">签到积分细则：</div>
    <div class="line2">每签到一次送20积分，签到完成积分直接冲到个人账户。</div>
</div>
<div class="success" style="display: none;">
    <img src="${ctx}image/signSuccess.png" alt="">
    <div class="word">获得20积分</div>
</div>
</body>
<script>
    var mask = $(".success");
    function success() {
        mask.show();
        $.ajax({
            async: false,
            url: "${ctx}wap/signIn",
            type: "post",
            dataType: "json",
            success: function (data) {
                if ("success" == data.message){//签到成功
                    mask.show();
                    alert("打卡成功！");
                    setTimeout(function () {
                        window.location.href = "${ctx}wap/index";
                    },800)
                }else {
                    alert(data.message);
                    mask.hide();
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试！");
            }
        })

    }

    mask.click(function () {
        $(this).hide();
    })


</script>
</html>