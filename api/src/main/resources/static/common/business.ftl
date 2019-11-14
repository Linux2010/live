<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>商业模式</title>
    <base target="_self" href="http://192.168.1.108:90/ws_project/jiaotouxueyuan/">
    <link href="css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="css/classifyList.css" rel="stylesheet" type="text/css"/>
    <link href="css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/conform.js"></script>
    <script src="js/jquery.min.js" type="text/javascript" ></script>
    <script src="js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="js/iscroll.js" type="text/javascript"></script>
    <style>

    </style>
</head>
<body>
<!--<div class="search">-->
    <!--<div class="searchBox">-->
        <!--<input type="text"/>-->
        <!--<span>搜索</span>-->
    <!--</div>-->
<!--</div>-->
<a href="#">
    <div class="zhibo">
        <div class="line">
            <img class="img" src="image/kechenging0.png">

            <div class="priceLine">
                <if #newsList??>
                    <#list newsList as list>
                        <span class="s1">${list.title}</span><span style="float: right;font-size: 0.3rem;">2017-05-22 12:00:00</span>
                    </#list>
                </if>
            </div>
        </div>


        <!---->
        <!--<div class="line">-->
            <!--<img class="img" src="image/kechenging0.png">-->
            <!--<div class="priceLine"><span class="s1">从爱因斯坦到霍金</span><span style="float: right;font-size: 0.3rem">2017-05-22 12:00:00</span> </div>-->
        <!--</div>-->
    </div>
</a>
</body>
</html>