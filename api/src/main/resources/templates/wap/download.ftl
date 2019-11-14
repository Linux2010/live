<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>用户中心</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <style>
        html,body{width:100%;height: 100%;background-color: #050402}
        body{background-color: white;}
        .span{width:4rem;line-height: 0.9rem;background-color: #48bfe4;font-size:0.4rem;color:white;text-align: center;margin:0rem auto;border-radius: 0.1rem;display: block;}
    </style>
</head>
<body>
    <input type="hidden" id="basePath" value="${ctx}"/>
    <img src="${ctx}image/shareLogo.png" style="width:2rem;height: 2rem;display: block;padding:2rem;margin:0 auto;padding-bottom: 0.8rem;padding-top:3rem;">
    <div class="span open">下载APP</div>
</body>
<script>
    $(function () {
        var open = $(".open");
        open.click(function () {
            if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                // 跳转到苹果二维码下载页面
                window.location.href=$("#basePath").val()+"ios/download.html";
                // 跳转到苹果AppStore下载页面
                // window.location.href="https://itunes.apple.com/cn/app/id1272069690";
            } else if (/(Android)/i.test(navigator.userAgent)) {
                // 跳转到360市场下载页面
                // window.location.href="http://zhushou.360.cn/detail/index/soft_id/3894190?recrefer=SE_D_%E6%95%99%E5%A4%B4%E5%AD%A6%E9%99%A2";
                // 跳转到安卓二维码下载页面
                window.location.href=$("#basePath").val()+"ios/android_download.html";
            }
        });
    });
</script>
</html>