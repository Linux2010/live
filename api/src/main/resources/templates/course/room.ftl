<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}im/css/live.css" rel="stylesheet" type="text/css"/>
    <link href="//nos.netease.com/vod163/nep.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}im/js/iscroll5.js" type="text/javascript"></script>
    <style>
        html,body{width:100%;height: 100%;overflow: hidden;}
        body{position: relative;}
        .video{width:100%;height: 6rem;position: fixed;z-index: 10;top:0;}
        .tab{position: fixed;z-index: 50;top:6rem;width:100%;background-color: white;}
        .tabNav{display: flex;align-content: space-around;}
        .tabNav .navCon{width:50%;font-size: 0.3rem;line-height:0.8rem;text-align: center;}
        .tabNav .navCon.active{color:cornflowerblue;border-bottom: 0.1rem solid cornflowerblue;}
        .tabBody{padding:0.2rem;display: none;width:100%;box-sizing: border-box;margin-top:7rem;}
        .tabBody.activity{display: block;}
        .tabBody .wordCon{line-height: 0.5rem;color:red;font-size: 0.26rem;padding:0.1rem 0;}
        .tabBody .wordTimeLine{}
        .tabBody .wordTimeLine .t{}
        .tabBody .wordTimeLine .n{padding:0 0.2rem;}
        .tabBody .wordLine{}
        .tabBody .wordLine .word{text-indent: 2em;display: block}
        .sendCon{position: fixed;bottom: 0;z-index: 50;padding:0.2rem;width:100%;box-sizing: border-box;background-color: white;}
        .sendCon .sendWord{width:4rem;padding:0.2rem;border:0.01rem solid #ccc;border-radius: 0.1rem;line-height: 0.4rem;}
        .sendCon .present{vertical-align: middle;margin:0 0.3rem;}
        .sendCon .sendBtn{float: right;padding:0.1rem 0.2rem;line-height: 0.3rem;vertical-align: middle;margin:0.16rem 0;background-color: #ffa42f;color:red;}
        .tabBody .anchorLine{height: 1.2rem;padding:0.1rem;box-sizing: border-box}
        .tabBody .anchorLine .anchorImg{width:1rem;height: 1rem;display: inline-block;vertical-align: middle;padding-right: 0.1rem;}
        .tabBody .anchorLine .anchorDetail{height: 1rem;display:block;line-height: 0.5rem;vertical-align: middle;max-width: 5.6rem;}
        .tabBody .notice{width:100%;padding:0.2rem 0;}
        .tabBody .noticeTit{width:100%;border-bottom: 0.01rem solid #ccc;line-height: 0.5rem;text-indent: 1em;font-size: 0.3rem;}
        .tabBody .noticeCon{width:100%;line-height: 0.3rem;text-indent: 2em;font-size: 0.26rem;padding:0.1rem 0;}
        .mask{top:0;position: fixed;width:100%;height: 100%;z-index: 1000;display: none}
        .mask .presentNumBox{position: fixed;top:5rem;width:100%;z-index: 10000;}
        .mask .presentCon{width:4rem;margin:0 auto;background-color: navajowhite;border-radius: 0.2rem;padding:0.5rem;box-sizing: border-box;}
        .mask .presentCon .lineSend{display: flex;justify-content: space-around;width:100%;padding:0.2rem 0rem;}
        .mask .presentCon .lineSend div{width:1rem;border-radius: 0.1rem;line-height: 0.5rem;font-size: 0.3rem;text-align: center;background-color: white;}
        .mask .presentCon .lineSend div.sentPresent{background-color: #ffa42f;}
        .presentBox{width:100%;height: 1.5rem;background-color: black;position: fixed;bottom:0;padding:0.2rem;box-sizing: border-box;display: flex;justify-content: space-around;z-index:0}
        .presentBox .presentItem{width:1.5rem;height: 1.1rem;}
        .presentBox .presentItem img{width:1.1rem;height:1.1rem;display: block;margin:0 auto;}
        .presentBox .presentItem img.active{border:0.05rem solid #ffa42f}
    </style>
</head>
<body>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="body_s" style="display: none;">
    <input type="hidden" id="basePath" value="${ctx}"/>
    <input type="hidden" id="imRoomid" value="${imRoomid!}"/>
    <input type="hidden" id="accId" value="${accId!}"/>
    <input type="hidden" id="accidToken" value="${accidToken!}"/>
    <input type="hidden" id="userName" value="${userName!}"/>
    <input type="hidden" id="teaAccid" value="${teaAccid!}"/>
    <input type="hidden" id="jtxyappUrl" value="${jtxyappUrl!}"/>
    <video id="my-video" class="video video-js" x-webkit-airplay="allow" playsinline="true"
           webkit-playsinline = "true" controls  poster="${ctx}image/zhibo.jpg"
           data-setup="{}">
    </video>
    <div class="tab">
        <div class="tabNav">
            <span class="navCon active">直播互动</span>
            <span class="navCon">主播</span>
        </div>
    </div>
    <div class="tabBody activity tabBodyIscroll" style="overflow: hidden;">
        <div id="wrapperWord" style="width:100%;height: 100%">
            <div class="scroller" style="width:100%;">
                <div class="room"></div>
            </div>
        </div>
    </div>
    <div class="sendCon">
        <input class="sendWord" id="editText" type="text"/>
        <span class="present" style="float: right;">
        <img src="${ctx}image/present.png" style="width: 0.8rem;"/>
    </span>
        <span class="sendBtn" id="sentText">发送</span>
    </div>
    <div class="mask">
        <div class="presentNumBox">
            <div class="presentCon">
                <img id="presentType" name="0" src="${ctx}im/chatroom/img/present_flower.png" style="display: block;margin:0.2rem auto">
                <div class="lineNum"><input type="text" id="presentNum" value="1" style="width:100%;line-height: 0.5rem;text-align: center"/></div>
                <div class="lineSend"><div class="closePreshent">取消</div> <div class="sentPresent" id="zhuzi">发送</div></div>
            </div>
        </div>
        <div class="presentBox">
            <div class="presentItem"><img class="active" src="${ctx}im/chatroom/img/present_flower.png"></div>
            <div class="presentItem"><img src="${ctx}im/chatroom/img/present_chocolate.png"></div>
            <div class="presentItem"><img src="${ctx}im/chatroom/img/present_bear.png"></div>
            <div class="presentItem"><img src="${ctx}im/chatroom/img/present_ice_cream.png"></div>
        </div>
    </div>
    <div class="tabBody">
        <div class="anchorLine">
            <img class="anchorImg" src="${teaPhoto!}"/>
            <span id="chatroom-verified">已证实</span>
            <div class="anchorDetail">
                <div class="anchorDetail1"> <span>主播：</span><span>${teaUserName!}</span></div>
                <div class="anchorDetail2"> <span>在线：</span><span id="olSpan"></span></div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}3rd/jquery-1.11.3.min.js"></script>
<script src="${ctx}im/js/config.js"></script>
<script src="${ctx}im/chatroom/dist/js/util.js?v=1"></script>
<script src="${ctx}im/chatroom/dist/js/title.js"></script>
<script src="${ctx}3rd/NIM_Web_SDK_v4.3.0.js"></script>
<script src="//nos.netease.com/vod163/nep.min.js"></script>
<script src="${ctx}im/chatroom/dist/js/link.js"></script>
<script src="${ctx}im/chatroom/dist/js/room.js?v=5"></script>
<script src="${ctx}jquery.barrager/js/jquery.barrager.js"></script>
<script>
    $(".present").click(function () {
        if($("#accId").val() == null || $("#accId").val() == ""){// 如果未登录，则不能发送礼物
            window.location.href=$("#basePath").val()+"wap/wx/login";
        }else{
            $(".mask").show();
        }
    });
    $(".presentBox .presentItem").click(function () {
        $(".presentBox .presentItem img").removeClass("active");
        $(this).children("img").addClass("active");
        $("#presentType").attr("name",$(this).index());
        $("#presentType").attr("src",$(this).children("img").attr("src"));
    });
    $(".closePreshent").click(function () {
        $(".mask").hide();
    });
    $(".spinner").hide();
    $("#body_s").show();
</script>
</html>