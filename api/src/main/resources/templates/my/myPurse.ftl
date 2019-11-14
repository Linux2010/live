<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myPurse.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>

</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/set_to_my" style="color: white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">我的钱包</span><span style="width: 20%;display: inline-block;font-size: 0.26rem;text-align: right"><a href=""  id="rule" style="color: white">学分制度</a></span>
</div>
<div class="con2">
    <img src="${ctx}image/purse.png">
</div>
<div class="con3">
    <p id="money"></p>
    <a href="${ctx}wap/bal_log/to_recharge"> <div class="money" style="color: white;background-color: #ffa42f;border-color: transparent;">充值</div></a>
    <a href="${ctx}wap/bal_log/detail"><div class="money"  style="color: white;background-color: #ff6f6f;border-color: transparent;"
    >明细</div></a>
</div>
</body>
<script src="${ctx}js/user/myPurse.js" type="text/javascript"></script>
<script>
    /*积分规则*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"sysDoc/searchSdcByType",
        data :{docType:1},
        dataType: "json",
        success: function (data) {
            var result = data.data;
            $("#rule").attr("href",result);
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
</script>
</html>