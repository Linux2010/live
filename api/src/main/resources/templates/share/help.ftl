<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>用户中心</title>
    <link href="/css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/conform.js"></script>
    <script src="/js/jquery.min.js" type="text/javascript" ></script>
    <script src="/js/vue.min.js" type="text/javascript"></script>
    <style>
        html,body{width:100%;height:100%;padding: 0;margin: 0;background-color: #f5f5f5}
        #accordion{width:100%;}
        #accordion>li{width:100%;color:#666;}
        #accordion>li .link{padding-left: 0.2rem;line-height: 0.9rem;width:100%;overflow: hidden;border-top:1px solid #ccc;font-size: 0.28rem;background-color: white;}
        #accordion>li:last-child .link{border-bottom:1px solid #ccc;}
        #accordion>li .submenu{ display:none;width:100%;padding: 0.3rem 0.2rem 0.2rem 0.6rem;box-sizing: border-box;line-height: 0.5rem;background-color: #f5f5f5;font-size: 0.28rem;}
    </style>
</head>
<body>
<ul id="accordion">
    <li>
        <div class="link">我接收不到短信验证码</div>
        <ul class="submenu">
            <li>1、查看短信黑名单设置，菜单->骚扰拦截->设置，分别查看拦截模式与黑白明单是否有不允许短信到达的号码</li>
            <li>2、重新设置浏览器安全等级或者关闭浏览器“阻止第三方Cookie”的设置，设置->隐私设置->内容设置。</li>
            <li>3、当天不能再登录需要验证手机的服务。</li>
        </ul>
    </li>
    <li>
        <div class="link">我登录不成功</div>
        <ul class="submenu">
            <li>1、用户名错误（大小写或特殊符号）</li>
            <li>2、密码错误（大小写或特殊符号）</li>
            <li>3、验证码错误</li>
            <li>4、网络错误</li>
        </ul>
    </li>
    <li>
        <div class="link">不能购买</div>
        <ul class="submenu">
            <li>1、没有库存</li>
            <li>2、余额不足</li>
            <li>3、网络错误</li>
        </ul>
    </li>
</ul>
</body>
<script type="text/javascript" src="/js/accordion.js"></script>
<script>
    var v1 = new Vue({
        el:"#accordion",
        data:{
        }
    })
</script>
</html>