<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
</head>
<body>
<div style="text-align:center;">
    <img style="width: 3rem;display: block;margin: 0 auto;margin-top: 3rem;" src="${ctx}image/fail.jpg">
    <h3 style="font-size: 0.6rem;margin: 0.5rem;">支付失败！</h3>
    <div style="width: 1.5rem;background-color: brown; color: white;margin: 0 auto;line-height: 0.6rem;border-radius: 0.3rem;" onclick="aa()">返回</div>
</body>
<script>
    function aa() {
        if("${returnUrl!}"){
            window.location.href = "${returnUrl!}";
        }else{

            window.location.href = "${ctx}wap/index";
        }
    }
</script>
</html>