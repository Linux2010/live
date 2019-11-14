<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>支付课程订单</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}css/ace.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}css/payOrder.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <style>
        body{font-size: 0.24rem;}
        .zhifumimaBox{width:100%;height: 100%;background-color: rgba(0,0,0,0.5);padding-top:1.5rem;position: fixed;top:0;z-index: 100;font-size: 0.28rem;}
        .zhifumima{width:90%;margin:0 5%;background-color: white;border-radius: 0.2rem;overflow: hidden;}
        .zhifumima p{width:100%;line-height: 0.8rem;text-align: center;font-size: 0.3rem;padding-top:0.2rem;}
        .zhifumima .nums{width:100%;padding:0.6rem 0.4rem;box-sizing: border-box;display: flex;justify-content: space-between}
        .zhifumima .line3{width:100%;display: flex;margin-top:0.3rem;border-top:0.01rem solid #ccc;}
        .zhifumima .line3 div{width:50%;line-height: 0.8rem;border-right: 0.01rem solid #ccc;text-align: center}
        .zhifumima .line3 .ensure{border-color: transparent;background-color: #ffa42f;color:white}
        .money {
            color: #ffa42f;
        }
        .zhifumima .nums input[type=password].pwd{border:0.03rem solid #ccc;border-right:0;width:0.975rem;height: 0.8rem;line-height:0.8rem;text-align: center;padding:0; color: #000000!important;
            background: #ffffff!important;}
        .zhifumima .nums input[type=password].pwd:last-child {
            border-right: 0.03rem solid #ccc;
            color:#000000!important;
            background: #ffffff!important;
        }
    </style>
</head>
<body>
<input type="hidden" id="courseId" name="courseId" value="${courseId!}"/>
<input type="hidden" id="totalMoney" name="totalMoney" value="${totalMoney!}"/>
<input type="hidden" id="courseType" name="courseType" value="${courseType!}"/>
<div class="con2">
    <div class="line2">
        <div class="moreL"><span>小计</span></div>
        <div class="more"><span class="money">¥${totalMoney!}</span>元</div>
    </div>
</div>
<div class="type">
    <div class="button2"><span>选择支付方式</span></div>
    <ul>
        <li><label>微信</label><span style="width:2rem;float: right;height:1rem;"><input checked="checked" type="radio" name="type" value="2" style="margin:0.35rem 0 0 0"/></span></li>
        <li id="alipayli" style="display: none;"><label>支付宝</label><span style="width:2rem;float: right;height:1rem;"><input type="radio" name="type" value="1" style="margin:0.35rem 0 0 0"/></span></li>
        <li><label>学分支付</label><span class="money">（￥1元 = 10学分）</span><span style="width:2rem;float: right;height:1rem;"><input type="radio" name="type" value="0" style="margin:0.35rem 0 0 0"/></span></li>
    </ul>
</div>
<div class="zhifumimaBox" style="display: none;">
    <div class="zhifumima">
        <p>请输入支付密码</p>
        <div class="nums" style="overflow: hidden;">
            <input class="text" type="text" style="position: relative;z-index:-1;width:1px;padding:0;border:0" maxlength="6">
            <input class="pwd" type="password" disabled>
            <input class="pwd" type="password" disabled>
            <input class="pwd" type="password" disabled>
            <input class="pwd" type="password" disabled>
            <input class="pwd" type="password" disabled>
            <input class="pwd" type="password" disabled>
        </div>
        <div class="line3">
            <div onclick="anensure()">取消</div>
            <div class="ensure" onclick="ensure()">确定</div>
        </div>
    </div>
</div>
<a href="javascript:;"><div class="button" onclick="payCo()">去支付</div></a>
</body>
<script src="${ctx}js/course/payCo.js?v=2" type="text/javascript"></script>
</html>