<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/latestNewsDetail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <style type="text/css">
        .lLine{width:100%;border-bottom:0.01rem solid #ccc;border-right: 0;border-left: 0;padding:0.1rem 0;}
        .lLine .lLineBox{margin:0 0.2rem;border-bottom: 0.01rem solid #ccc;padding:0.1rem 0;}
        .lLine .lLineBox .lImg{display:inline-block;width:1rem;height: 1rem;border-radius: 50%;vertical-align: middle}
        .lLine .lLineBox .lImgR1{display:inline-block;width:3rem;vertical-align: middle}
        .lLine .lLineBox .lImgR1 div{line-height: 0.5rem;}
        .lLine .lLineBox .lImgR2{display:inline-block;width:2.4rem;vertical-align: middle}
        .lLine .lLineBox .lImgR2 div{line-height: 0.5rem;text-align: right;}
        .lLine .huifu{padding:0 0.2rem;line-height: 0.5rem;color:red;}
        .lLine .question{padding: 0 0 0 1.2rem;line-height: 0.5rem;}
        .reply{width:100%;position: absolute;bottom:0;z-index: 20;background-color: white;box-sizing: border-box;}
        .reply .replyBox{padding:0.2rem;}
        .reply .replyBox input{border:0.01rem solid #ccc;border-radius: 0.1rem;line-height: 0.5rem;width:5.6rem;vertical-align: middle}
        .reply .replyBox .span{;border-radius: 0.1rem;background-color:#ffa42f;padding:0.2rem 0.2rem;vertical-align: middle;margin-left: 0.2rem;}
        html,body{width: 100%;height: 100%;}
        .scroller{
            background-color: #fff;
            padding-bottom: 1rem;
        }
    </style>
</head>
<body>
<div class="body" style="overflow-y:hidden;">
    <input type="hidden" id="basePath" value="${ctx}"/>
    <input type="hidden" id="informationId" value="${informationId!}"/>
    <input type="hidden" id="isCollect" value="${isCollect!}"/>
    <input type="hidden" id="type" value="${type!}"/>
    <input type="hidden" id="isOperation" value="${isOperation!}"/>
    <input type="hidden" id="totalCcCount" value="${information.commentNum!}"/>
    <input type="hidden" id="agreeNumVal" value="${information.agreeNum!}"/>
    <input type="hidden" id="disagreeNumVal" value="${information.disagreeNum!}"/>
    <input type="hidden" id="commentNum" value="${information.commentNum}"/>
    <div id="wrapper" style="z-index: 1;top:0rem;width: 100%;height: 100%;overflow: hidden;">
        <div class="scroller">
            <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
            <div>
            <#if information??>
                <div class="zixun">
                    <div class="title">${information.title}</div>
                    <div class="time">
                        <span class="span1">
                            发布者：${information.userName}
                        </span>
                        <span class="span1" style="text-align: right;">${createTime}</span>
                    </div>
                    <div class="con">
                    ${information.content!}
                    </div>
                </div>
                <div class="con3">
                    <div class="con3Tit" style="padding: 9px"><span id="totalCcCountSpan">评论（${information.commentNum!}）</span></div>
                    <div id="infoDetails"></div>
                </div>
            </#if>
            </div>
            <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
        </div>
    </div>
    <div class="write" style="z-index: 999;">
        <div class="pencle f">
            <div class="inputBox"><input type="text" id="commentContent" class="input"/></div>
            <div style="display: inline-block;">
                <div class="collectBox" v-if="collection"><img v-on:click="collectioning()" src="${ctx}image/collection.png"/> </div>
                <div class="collectBox" v-else><img v-on:click="collectioning()" src="${ctx}image/collection-norm.png"/> </div>
            </div>
            <div class="agreeBox" v-show="agreeState===0" v-on:click="agreeF()"><img src="${ctx}image/agree.png"/><span class="agreeNum">${information.agreeNum!}</span></div>
            <div class="agreeBox" v-show="agreeState===1"><img src="${ctx}image/agree_red.png"/><span class="agreeNum">${information.agreeNum!}</span></div>
            <div class="againstBox" v-show="disagreeStare===0" v-on:click="disagreeF()"><img src="${ctx}image/disagree.png"/><span class="againstNum">${information.disagreeNum!}</span></div>
            <div class="againstBox" v-show="disagreeStare===1"><img src="${ctx}image/disagree_red.png"/><span class="againstNum">${information.disagreeNum!}</span></div>
        </div>
        <div class="b" style="display: none">
            <textarea id="ccText" class="writeBox4" rows="3" placeholder="写下你的评论..."></textarea>
            <span id="sendCommentContent">发送</span>
        </div>
    </div>
</div>
</body>
<script src="${ctx}js/news/infoDetails.js?v=1" type="text/javascript"></script>
</html>