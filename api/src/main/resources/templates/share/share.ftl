<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>用户中心</title>
    <link href="/css/reset.css" rel="stylesheet" type="text/css"/>
    <script  src="/js/conform.js" type="text/javascript"></script>
    <script src="/js/jquery.min.js" type="text/javascript" ></script>
    <style>
        html,body{width:100%;height: 100%}
        .body{width:100%;height:100%;background: url("/image/top.png");background-position: center;background-size:auto 100%;}
        .body span{display: inline-block;padding-top: 4rem;width: 100%;}
        .body div{width:4rem;height: 1rem;color: white;font-weight: bold;font-size:0.35rem;text-align: center;line-height: 1rem;border-radius: 0.3rem;margin: 0 auto;box-sizing: content-box;}
        .body .down{background-color: transparent;height: 1rem; }
        .body .open{margin-top: 0.8rem;background-color: #ffa42f;}
        .mask{width: 100%;height: 100%;position: fixed;top:0;display: none;background-color: black;opacity: 0.5;}
    </style>
</head>
<body>
<div class="body">
    <span>
        <div class="down"></div>
        <div class="open">打开APP</div>
    </span>
</div>
<div class="mask">
    <img src="/image/mask.png" alt="" style="width: 5rem;float: right;">
</div>
</body>
<script>
    $(function () {
        function GetRequest() {
            var url = location.search;
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for(var i = 0; i < strs.length; i ++) {
                    theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        }
        var type = GetRequest()["type"];
        var id = GetRequest()["id"];
        var open = $(".open");
        function turn() {
            if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                var a = window.location.href = "coachcollege://"+type+"/"+id;
                var two = window.setTimeout(function(){
                    window.location.href = "http://www.alibaba.com";
                },5000);
//                window.setTimeout(function(){
//                    clearTimeout(two)
//                },2500)
            } else if (/(Android)/i.test(navigator.userAgent)) {
                //alert(navigator.userAgent);
                alert('这是Android');
            } else {

            }
        }
        function is_weixn(){
            var ua = navigator.userAgent.toLowerCase();
            if(ua.match(/MicroMessenger/i)=='micromessenger') {
                open.click(function () {
                    $(".mask").show();
                    turn();
                })
            } else {
                console.log(navigator);
                open.click(function () {
                    turn();
                })
            }
        }
        is_weixn();
    })
</script>
</html>