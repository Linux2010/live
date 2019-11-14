<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>商业内容</title>
<#--<base target="_self" href="http://192.168.1.108:90/ws_project/jiaotouxueyuan/">-->
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/user/conform.js"></script>

    <style>
        html,body{
            width:100%;
            padding: 0.2rem;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

<#if news??>
    ${news.content}
    <#else>
    暂无内容
</#if>

</body>
<script type="text/javascript" src="${ctx}js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("img").css({"width":"90%"});
        $("a").attr("href","#");
        var a = $("body")[0].clientHeight;
        function eleHeight(x) {
            return ''+x;
        }
        eleHeight(a);

    });

</script>
</html>