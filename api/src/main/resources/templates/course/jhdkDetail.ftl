<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/kecheng.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script type="text/javascript" src="${ctx}js/base.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.4.1&key=33d8f16649ead4f016bfd720d36a7395&plugin=AMap.Geocode"></script>
    <style type="text/css">
        .lLine{width:100%;border-bottom:0.01rem solid #ccc;border-right: 0;border-left: 0;padding:0.1rem 0;}
        .lLine .lLineBox{margin:0 0.2rem;border-bottom: 0.01rem solid #ccc;padding:0.1rem 0;}
        .lLine .lLineBox .lImg{display:inline-block;width:1rem;height: 1rem;border-radius: 50%;vertical-align: middle}
        .lLine .lLineBox .lImgR1{display:inline-block;width:3rem;vertical-align: middle}
        .lLine .lLineBox .lImgR1 div{line-height: 0.5rem;font-size: 0.28rem;}
        .lLine .lLineBox .lImgR2{display:inline-block;width:2.4rem;vertical-align: middle}
        .lLine .lLineBox .lImgR2 div{line-height: 0.5rem;text-align: right;}
        .lLine .huifu{padding:0 0.2rem;line-height: 0.5rem;color:red;}
        .lLine .question{padding: 0 0 0 1.2rem;line-height: 0.5rem;}
        .reply{width:100%;position: absolute;bottom:0;z-index: 20;background-color: white;box-sizing: border-box;}
        .reply .replyBox{padding:0.2rem;}
        .reply .replyBox input{border:0.01rem solid #ccc;border-radius: 0.1rem;line-height: 0.5rem;width:5.6rem;vertical-align: middle}
        .reply .replyBox .span{;border-radius: 0.1rem;background-color:#ffa42f;padding:0.2rem 0.2rem;vertical-align: middle;margin-left: 0.2rem;}
        html,body{width: 100%;height: 100%;overflow: hidden;}
        .scroller{
            background-color: #fff;
            padding-bottom: 2rem;
        }
    </style>
</head>
<body>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="body_s" style="display: none;">
    <input type="hidden" id="basePath" value="${ctx!}"/>
    <input type="hidden" id="courseId" value="${courseId!}"/>
    <input type="hidden" id="courseType" value="${courseType!}"/>
    <input type="hidden" id="totalCcCount"/>
    <input type="hidden" id="xVal"/>
    <input type="hidden" id="yVal"/>
    <div class="con1">
        <div class="con1Select">
            <span style="width: 10%;display: inline-block;" onclick="backFuc()"><</span>
            <span style="width: 90%;display: inline-block;margin: 0 auto;text-align: center;padding-right: 0.64rem;">江湖大课详情</span>
        </div>
    </div>
    <div id="wrapper" style="z-index: 1;top:1rem;width: 100%;height: 100%;overflow: hidden;">
        <div class="scroller">
            <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
            <div>
                <div class="con2" id="teaDiv"></div>
                <div class="radio" id="courseImg"></div>
                <div class="con4">
                    <div class="con4Title">
                        <img src="${ctx}image/lingxing.png" alt=""><span>课程安排</span><img src="${ctx}image/lingxing.png" alt="">
                    </div>
                    <div class="con4Body">
                        <div class="line1" style="border-top: 0.01rem solid #ccc"><img src="${ctx}image/alarm.png"><span style="color:#222" id="timeSpan"></span></div>
                        <div class="line1"><img src="${ctx}image/RMB.png"><span id="cpSpan"></span><a href="javascript:;"><span class="payVip" id="payNow" onclick="showJsPage()" style="display: none;">立即购买</span></a></div>
                        <div class="line1"><img src="${ctx}image/level.png"><span id="vpSpan"></span>(会员可免费观看)<a href="javascript:;"><span class="payVip" id="joinNow" onclick="joinCourse()" style="display: none;">参加报名</span></a></div>
                        <div class="line1">
                            <img src="${ctx}image/dizhi.png">
                            <span id="addressSpan"></span>
                            <div id="mapDiv" style="display: block;width:6rem;height: 3rem; margin: 0 auto 0.2rem;" onclick="showGdMap()"></div>
                        </div>
                    </div>
                </div>
                <div class="con4">
                    <div class="con4Title">
                        <img src="${ctx}image/lingxing.png" alt=""><span>课程内容</span><img src="${ctx}image/lingxing.png" alt="">
                    </div>
                    <div class="con4Con" id="courseIntro"></div>
                </div>
                <div class="con3">
                    <div class="con3Tit"><span id="totalCcCountSpan"></span></div>
                    <div id="ccDiv"></div>
                </div>
            </div>
            <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
        </div>
    </div>
    <div class="write" style="z-index: 999;">
        <div class="f" id="bottomDiv">
            <div class="writeBox1">写下你的评论...</div>
            <div class="writeBox2"><img src="${ctx}image/pinglun.png"> </div>
        </div>
        <div class="b" style="display: none">
            <textarea class="writeBox4" rows="3" placeholder="写下你的评论..."></textarea>
            <span id="addCommSpan">发送</span>
        </div>
    </div>
</div>
<script src="${ctx}js/course/jhdkDetail.js?v=15" type="text/javascript"></script>
</body>
</html>