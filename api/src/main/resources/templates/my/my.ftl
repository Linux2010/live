<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/my.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <style>
        .v{
            width: 0.6rem;
            /*margin-left: -0.3rem;*/
            /*margin-top: -0.3rem;*/
            position: absolute;
            top:0;
            left:3rem;
            -weikit-transform-origin:left bottom; /*设置右下角为旋转基准点 */
            -moz-transform-origin:left bottom;
            -o-transform-origin:left bottom;
            -ms-transform-origin:left bottom;
            transform-origin:left bottom;

            transform:rotate(-50deg); /*以右下角为基准点顺时针旋转10°*/
            -webkit-transform:rotate(-50deg);
            -moz-transform:rotate(-50deg);
            -o-transform:rotate(-50deg);
            -ms-transform:rotate(-50deg);
        }
        .v1{
            width: 0.4rem;
            position: absolute;
            top:0.95rem;
            left:3.85rem;
            border-radius: 50%;
            /*margin-left: 0.5rem;*/
            /*margin-top: 0.5rem;*/
        }
    </style>
</head>

<body >
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="body_s" style="display: none;">
    <div class="con1">
        <div class="setPar">
            <a href="${ctx}wap/user/setting"> <img src="${ctx}image/set.png" alt=""></a><div id="red_tishi" style="width:0.1rem;height: 0.1rem;position: absolute;top:0.25rem;left: 0.65rem;background-color: red;border-radius: 50%;"></div><a href="${ctx}wap/message/page"><img src="${ctx}image/notice.png" style="float: right;"></a>
        </div>
        <a href="${ctx}wap/user/userInfo" style="display: inline-block;width:100%;position: relative;height: 1.4rem;z-index: 50;top:-0.15rem;">
            <div class="userImg" style="display: flex;align-items: center;box-sizing:border-box;flex-direction: column;justify-content: space-around;"><img src="" id="img_user"><img src="${ctx}image/v.png" class="v" id="v"/>
           <img src="${ctx}image/teacher.png" class="v1" id="v1"/>
            </div>
        </a>
        <div class="userName"></div>
    </div>
    <div class="con2">
        <a href="${ctx}wap/myteam/myCourse">
            <div class="con2Cell">
                <div id="mkNumVal"></div>
                <div >我的课程</div>
            </div>
        </a>
        <a href="${ctx}wap/myteam/myCollection">
            <div class="con2Cell">
                <div id="msNumVal"></div>
                <div >我的收藏</div>
            </div>
        </a>
        <a href="${ctx}wap/user/my/myfollow">
            <div class="con2Cell">
                <div id="myFocus"></div>
                <div >我的关注</div>
            </div>
        </a>
    </div>
    <div class="con4" style="margin:0.2rem 0;">
        <a href="${ctx}wap/user/my/please"><div class="con4Tit">
            <img src="${ctx}image/yaoqing.png"/><span>邀请好友</span>
            <span style="float: right;font-size: 0.24rem">></span>
            <span style="display: inline-block;float: right;padding:0 0.2rem"><img src="${ctx}image/hot.png" style="width:0.4rem;height: 0.4rem"><span style="font-size: 0.24rem;">邀请好友立赚1396学分</span></span>
        </div></a>
    </div>
    <div class="con4">
        <a href="${ctx}wap/user/myBalance"><div class="con4Tit">
            <img src="${ctx}image/purse1.png"/><span>我的钱包</span><span style="float: right;font-size: 0.24rem">></span>
        </div></a>
        <a href="${ctx}wap/myteam/"><div class="con4Tit">
            <img src="${ctx}image/team.png"/><span>我的团队</span><span style="float: right;font-size: 0.24rem">></span>
        </div></a>
        <a href="${ctx}wap/userGroupCard/my/activeCode"><div class="con4Tit">
            <img src="${ctx}image/key.png"/><span>激活码</span><span style="float: right;font-size: 0.24rem">></span>
        </div></a>
    </div>
    <div class="con4">
        <a href="${ctx}wap/topic/my/myTopicsCourse"><div class="con4Tit">
            <img src="${ctx}image/answer.png"/><span>我的答题</span><span style="float: right;font-size: 0.24rem">></span>
        </div></a>
        <a href="${ctx}ios/help.html"><div class="con4Tit">
            <img src="${ctx}image/help.png"/><span>帮助</span><span style="float: right;font-size: 0.24rem">></span>
        </div></a>
    </div>
    <div style="height: 1rem;width: 100%"></div>
    <div class="bottom">
        <a href="${ctx}wap/index">
            <div class="b">
                <img src="${ctx}image/home-norm2.png">
                <span>首页</span>
            </div>
        </a>
        <a href="${ctx}wap/study/study_today">
            <div class="b">
                <img src="${ctx}image/study_norm2.png">
                <span>学习</span>
            </div>
        </a>
        <a href="${ctx}wap/user/set_to_my">
            <div class="b">
                <img src="${ctx}image/my_norm1.png">
                <span style="color:#ffa42f">我的</span>
            </div>
        </a>
    </div>

    <!--////////////////////////  vip   ////////////////////////////////-->
    <div class="mask" style="display: none;">
        <div class="vip">
            <div class="vipTit">VIP充值</div>
            <div class="vipCon"><span>开通服务：</span><span style="float: right;">会员服务</span></div>
            <div class="vipCon">
                <span>开通服务：</span>
                <span style="padding-left: 0.5rem;">
                <input type="radio" name="vip"/><label>月付</label>
                <input type="radio" name="vip"/><label>年付</label>
                <input type="radio" name="vip"/><label>永久</label>
            </span>
            </div>
            <div class="vipCon"><span>开通服务：</span><span style="float: right;">￥100/年</span></div>
            <div class="button"><span>立即购买</span></div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}js/conform.js"></script>
<script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
<script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
<script src="${ctx}js/base.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        $(".spinner").hide();
        $("#body_s").show();

    });
    <#--判断资料是否完善-->
    $.ajax({
        async: false,
        type: "POST",
        url: base+"user/is_impl",
        dataType: "json",
        success: function (data) {
            var result =  data.data.is_complet;
            if(result == 1){
                $("#red_tishi").css("display","none")
            }else{
                $("#red_tishi").css("display","block")
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    });
    <#--获取我的收藏，我的课程，我的关注的数量-->
    $.ajax({
        async: false,
        type: "POST",
        url: base+"user/get_num",
        dataType: "json",
        success: function (data) {
            var result =  data.data;
            if(data.message == "success"){
                $("#mkNumVal").html(result.mkNum);
                $("#msNumVal").html(result.msNum);
                $("#myFocus").html(result.myFocuNumber);
                $(".userName").html(result.userName);
                $("#img_user").attr("src",result.photo);

                if(result.userType == 1){
                    $("#v1").css("display","block");
                    $("#v").css("display","none");
                }else{
                    if(result.userIdentity == 2){
                        $("#v").css("display","block");
                    }else{
                        $("#v").css("display","none");
                    }
                    $("#v1").css("display","none");
                }
            }else{
                alert("获取失败!");
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    });


    <#--判断是否在微信内打开-->
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger') {
        $.ajax({
            async: false,
            type: "POST",
            url: "${ctx}wap/wx/judgmentThePublicOpenId",
            dataType: "text",
            success: function (data) {
                if(null != data && data != ""){//不走添加方法
                    send_message();//调用发送消息方法
                }else{//获取openId保存
                    window.location.href = "${ctx}wap/message/auth";
                }
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        });
    }

    <#--f发送消息-->
    function send_message() {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"message/sendByMessageList",
            success: function (data) {
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        });
    }

</script>
</html>