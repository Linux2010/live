<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>详情</title>
    <link href="/css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/conform.js"></script>
    <script src="/js/jquery.min.js" type="text/javascript" ></script>
    <script src="/js/vue.min.js" type="text/javascript"></script>
    <style>
        html,body{width:100%;height: 100%}
        .body{width:100%;height:100%;}
        .body .title{width:100%;text-align: center;line-height: 1rem;font-size: 0.4rem;background-color: white;color:#22272a;overflow: hidden;position: fixed;top:0;z-index: 40;}
        .body .time{width:100%;line-height: 0.6rem;text-align: right;margin-bottom: 0.3rem;padding-top: 1rem;font-size: 0.26rem;}
        .body .time b{padding-right: 0.2rem;}
        .body .p{width:100%;box-sizing: border-box;padding: 0.2rem 0.8rem;line-height: 0.6rem;text-indent: 2em;font-size: 0.28rem;}
    </style>
</head>
<body>
<div class="body">
    <#if message??>
        <#if message.title??>
            <div class="title" v-html="title">${message.title}</div>
        </#if>
    <#if message.createTime??>
        <div class="time"><span>发布于：</span><b>${message.createTime?number_to_datetime}</b></div>
    </#if>
     <#if message.content??>
        <div class="p">${message.content}</div>
     </#if>
    </#if>
</div>
</body>
</html>