<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>消息通知</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/set.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/set_to_my" style="color:white;"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">消息通知</span>
</div>
<div class="con2">
    <#--<div class="line2"><div class="img1"><img src="${ctx}image/msg.png"/></div> <span class="s1">系统通知</span><a href="my/systemMessage.html"> <span class="s2">></span></a> </div>
    <div class="line2"><div class="img1"><img src="${ctx}image/word.png"/></div> <span class="s1">消息</span><a href="my/discussMsg.html"> <span class="s2">></span></a> </div>-->
        <a href="${ctx}wap/message/list_page">  <div class="line2"><div class="img1"><img src="${ctx}image/message.png"/></div> <span class="s1">站内信</span><span class="s2"><span style="width: 0.4rem;height: 0.4rem;background-color: red;border-radius: 50%;display: inline-block;line-height: 0.4rem;text-align: center;color: white;margin-right: 0.1rem;" class="mess">${count!}</span> > </div></a>
        <a href="${ctx}wap/search/message/myTeacherAnswer?userId=${userId}">  <div class="line2"><div class="img1"><img src="${ctx}image/wenda.png" style="border-radius: 50%"/></div> <span class="s1">教头问答</span><span class="s2"> > </div></a>
</div>
</body>
<script>
    var h = $(".mess").text();
    window.onload = function(){
        check_(h);
    }
    function check_(v) {
        if(v == 0){
            $(".mess").css("display","none");
            $(".mess").html("");

        }else{
            $(".mess").css("display","inline-block");
            $(".mess").html(v);
        }
    }
</script>
</html>
