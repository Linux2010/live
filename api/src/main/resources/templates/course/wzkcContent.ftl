<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>文字课程内容</title>
    <link href="/css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/user/conform.js"></script>
    <style>
        html,body{
            width:100%;
            padding: 0.2rem;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

<!--富文本内容-->
${wzkcContent}

</body>
<script type="text/javascript" src="/js/jquery.min.js"></script>
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