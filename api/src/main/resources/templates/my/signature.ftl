<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/coupon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
</head>
<body>
<div class="con2">
    <span style="width: 20%;display: inline-block;"><</span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">个性签名</span>
</div>
<div class="con3">
    <div class="line1">
        <textarea placeholder="15个字以内" rows="3" id="signature" maxlength="15">${userInfo.signature!}</textarea>
    </div>
    <p class="p">添加个性签名，向教头学院展示更有趣的你。(* 最多15个字符)</p>
</div>
<div class="sub">确定</div>
</body>
<script>
    /*修改签名*/
    $(".sub").on("click",function () {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"user/updateSignature",
            data: {'signature':$("#signature").val()},
            dataType: "json",
            success: function (data) {
                if(data.result == 1){
                    alert("修改成功!");
                    window.location.href="${ctx}wap/user/userInfo";
                }
            },
            error: function (data) {
                alert( "系统异常，请稍后重试");
            }
        })
    })
</script>
</html>