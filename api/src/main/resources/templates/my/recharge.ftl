<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/base.js" type="text/javascript" ></script>
    <style>
        body{font-size: 0.3rem;}
        .mes{padding:0.3rem 0.5rem;line-height: 0.3rem;font-size: 0.30rem;}
        .mes .money{float: right;}
        .classify{padding:0.3rem 0;font-size: 0.28rem;background-color: #f5f5f5;}
        .classify .tit{padding:0 0.5rem;}
        .classify .classifyBox{display: flex;flex-wrap:wrap;justify-content: space-around;padding:0 0.2rem; padding-top:0.3rem;}
        .classify .classifyBox .classifyItem{width:2rem;border:0.01rem solid #ccc;color:#ccc;border-radius: 0.2rem;padding:0.2rem;margin-bottom:0.2rem;line-height: 0.5rem;text-align: center;box-sizing: border-box;}
        .classify .classifyBox .classifyItem .n{font-size: 0.28rem;}
        .classify .classifyBox .classifyItem.active div{color:#ffa42f;}
        .classify .classifyBox .classifyItem.active div span{color:#ffa42f;}
        .classify .classifyBox .classifyItem.active{font-size: 0.28rem;color:#ffa42f;border-color:#ffa42f}
        .other{padding:0.2rem 0.3rem;border:0.01rem solid #ccc;border-left: 0;border-right: 0;font-size: 0.3rem;}
        .other .num{width:4rem;margin:0 0.2rem;outline: none;border:0.01rem solid #ccc;border-radius:0.1rem;line-height: 0.3rem;padding:0.2rem;box-sizing: border-box;}
        .all{padding:0.2rem 0.3rem;margin:0.2rem 0;border:0.01rem solid #ccc;border-left: 0;border-right: 0;line-height: 0.5rem;}
        .pay{margin:0.5rem 0.2rem;text-align: center;line-height: 0.9rem;background-color: #ffa42f;color:white;border-radius: 0.2rem;}
        .line{padding:0.2rem;border-bottom:0.02rem solid #ccc}
        .line .tit{line-height: 0.4rem;display: inline-block;font-size: 0.26rem;}
        .line .pay{line-height: 0.8rem;float: right;font-size: 0.28rem}
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;}
        .con1 span{color:white;}
    </style>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/myBalance" style="color: white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">充值</span>
</div>
<div class="mes">
    <span class="name"></span><span class="money"></span>
</div>
<div class="classify">
    <div class="tit">充值数目 （10学分=1元)</div>
    <div class="classifyBox">
        <div class="classifyItem active">
            <div>60学分</div>
            <div class="n">￥<span>6.0</span></div>
        </div>
        <div class="classifyItem">
            <div>180学分</div>
            <div class="n">￥<span>18.0</span></div>
        </div>
        <div class="classifyItem">
            <div>500学分</div>
            <div class="n">￥<span>50.0</span></div>
        </div>
        <div class="classifyItem">
            <div>880学分</div>
            <div class="n">￥<span>88.0</span></div>
        </div>
        <div class="classifyItem">
            <div>1180学分</div>
            <div class="n">￥<span>118.0</span></div>
        </div>
        <div class="classifyItem">
            <div>2180学分</div>
            <div class="n">￥<span>218.0</span></div>
        </div>
    </div>
</div>
<div class="other">
    <label>其它数量：</label><input class="num" type="text" placeholder="请输入充值学分" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" /><span >学分</span>
</div>
<div class="all"><span>金额：</span><span style="color:red">￥</span><span class="allMomey" style="color:red">6</span></div>
<div class="pay">去支付</div>
</body>
<script >
    var classifyItem = $(".classifyBox .classifyItem");
    var num = $(".num");
    classifyItem.click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        $(".allMomey").html($(this).children(".n").children("span").html());
        num.val('');
    })
    num.change(function () {
        $(".allMomey").html($(this).val()/10);
    })
    num.focus(function () {
        classifyItem.removeClass("active");
    })
    num.blur(function () {
        classifyItem.eq(0).addClass("active");
    })
    num[0].addEventListener('keyup', function () {
        var r = /^[1-9]\d*$/;
        console.log($(".num").val());
         if($(".num").val() != ""){
           if(!r.test($(".num").val())){
               alert("输入金额错误!");
               $(".num").val("");
               return false;
           }
       }
       $(".allMomey").html($(".num").val()/10);
    }, false);

    <#--获取信息和余额-->
    $.ajax({
        async: false,
        type: "POST",
        url: base+"user/get_num",
        dataType: "json",
        success: function (data) {
            var result =  data.data;
            if(data.message == "success"){
                $(".name").html("充值账号 : "+result.loginName);
                $(".money").html(result.money+"学分");
            }else{
                alert("获取失败!");
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })

    $(".pay").on("click",function () {
        var r = /^[1-9]\d*$/;
        if($(".num").val() != ""){
            if(!r.test($(".num").val())){
                alert("输入金额错误!");
                $(".num").val("");
                return;
            }
        }
        window.location.href="${ctx}wap/bal_log/to_choose?money="+($(".allMomey").html()*10);
    })
</script>
</html>