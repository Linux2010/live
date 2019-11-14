<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>首页-教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/index.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}js/base.js"></script>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/tele86.js"></script>
    <script src="${ctx}js/user/share.js?v=0.1"></script>
    <style>
        html, body {
            position: relative;
        }
        body {
            background: #eee;
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
            font-size: 14px;
            color:#000;
            margin: 0;
            padding: 0;
        }
        .swiper-container {
            width: 100%;
            height: 3rem;
            margin-top: 0.82rem;
            z-index: 0;
        }
        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;

            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }
    </style>
</head>
<body>
    <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
    </div>
    <div id="body_s" style="display: none;">
        <div class="top">
            <a href="${ctx}wap/search/home/allSearch"><div class="searchTop"><img class="searchTopImg" src="${ctx}image/search.png"><span class="searchTopLable">每天进步一小步</span> </div></a>
        </div>
        <div class="swiper-container">
            <div class="swiper-wrapper">
            <#if advertiseList??>
                <#list advertiseList as list>
                    <div class="swiper-slide">
                        <#if list.linkType == 2><#--链接到教头详情-->
                            <a href="${ctx}wap/search/wellTeacherHome?teacherId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>
                        <#--<#elseif list.linkType == 3>&lt;#&ndash;链接到商品详情&ndash;&gt;
                            <a href="${ctx}?detailId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>-->
                        <#elseif list.linkType == 4><#--链接到音频课程详情-->
                            <a href="${ctx}wap/course/courseDetail?courseType=ypkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>
                        <#elseif list.linkType == 5><#--链接到视频课程详情-->
                            <a href="${ctx}wap/course/courseDetail?courseType=spkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>
                        <#elseif list.linkType == 6><#--链接到直播课程详情-->
                            <a href="${ctx}wap/course/courseDetail?courseType=zbkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>
                        <#elseif list.linkType == 7><#--链接到文字课程详情-->
                            <a href="${ctx}wap/course/courseDetail?courseType=wzkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>
                        <#else><#--链接到江湖大课详情-->
                            <a href="${ctx}wap/course/courseDetail?courseType=jhdk&courseId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>
                        </#if>
                    </div>
                </#list>
            </#if>
            </div>
            <div class="swiper-pagination"></div>
        </div>
        <input type="hidden" id="userId" value="${userId!}"/>
        <input type="hidden" id="userName" value="${userName!}"/>
        <input type="hidden" id="qrCodePage" value="${userFx!}"/>
        <input type="hidden" id="token" value="${userToken!}" />
        <input type="hidden" id="userIdentity" value="${userIdentity!}">
        <#--<div class="bImg" style="position: fixed;bottom: 0;width:100%">-->
            <#--<a href="${ctx}wap/wx/login"> <img src="${ctx}image/zhuce.png" style="width: 4.38rem;height: 2.46rem;margin: auto;display: block"></a>-->
        <#--</div>-->

    <div class="buyVip" style="background-color: transparent;width:1.3rem;padding:0;
    <#if userIdentity?exists && userIdentity == 2>
        display: none;
    </#if>
    ">
        <div id="becameVip" style="background-color: transparent;margin:0;"><img style="width:1.3rem;height: 1.3rem;" src="${ctx}image/home_vip.png"></div>
    </div>
    <div class="qiandao"><a href="${ctx}wap/sign"><img id="qiandaoImg" src="${ctx}image/qiandao.png" style="width:100%;height: 100%;"></a></div>
    <div class=""></div>
        <div class="con1">
            <div style="display: flex;justify-content:space-between;padding-bottom: 0.2rem;">
                <div class="study" onclick="showSpkcIndex()">
                    <img src="${ctx}image/audio.png"><span>视频课程</span>
                </div>
                <div class="study" onclick="showZbkcIndex()">
                    <img src="${ctx}image/live.png"><span>直播授课</span>
                </div>
            </div>
            <div style="display: flex;justify-content:space-between;">
                <div class="study" onclick="showWzkcIndex()">
                    <img src="${ctx}image/read.png"><span>文字阅读</span>
                </div>
                <div class="study" onclick="showYpkcIndex()">
                    <img src="${ctx}image/vioce.png"><span>音频收听</span>
                </div>
            </div>
        </div>
        <div class="con2">
            <div class="con2Tit">
                <span>精品推荐</span>
            </div>
            <div class="con2Con">
                <a href="${ctx}wap/news/newsView">
                    <div class="cell">
                        <img src="${ctx}image/syms.png">
                        <div class="cellR">
                            <div class="title"><span class="s1">商业模式</span><span class="s2">一站铸就卓越企业</span></div>
                            <p class="p">帮助企业锁定具有潜力并能提供长期利润增长的消费群。</p>
                            <div class="more">更多></div>
                        </div>
                    </div>
                </a>
                <a href="${ctx}wap/news/informationView">
                    <div class="cell">
                        <img src="${ctx}image/zxzx.png">
                        <div class="cellR">
                            <div class="title"><span class="s1">最新资讯</span><span class="s2">掌握趋势，趋势掘金</span></div>
                            <p class="p">商业动态一键掌握，解密趋势背后的商机。</p>
                            <div class="more">更多></div>
                        </div>
                    </div>
                </a>
                <a href="${ctx}wap/course/jhdkIndex">
                    <div class="cell">
                        <img src="${ctx}image/jhdk.png">
                        <div class="cellR">
                            <div class="title"><span class="s1">江湖大课</span><span class="s2">线上学习、线下落地</span></div>
                            <p class="p">以教育，创新世界，品质优课来教头江湖，行业大咖带你落地实践。</p>
                            <div class="more">更多></div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        <div class="con3 fourTeachers">
            <div class="con3Tit">
                <span>名优教头</span><a href="${ctx}wap/search/home/classify"> <span style="float: right;">更多 》</span></a>
            </div>
        </div>
        <div class="con4">
            <div class="con4Tit">
                <span>课程进行中</span><a href="javascript:;" onclick="showOnLiveIndex()"> <span style="float: right;">更多></span></a>
            </div>
            <div class="con4Con" id="onLiveDiv"></div>
        </div>
    <div style="height: 1rem;width: 100%"></div>
    <div class="bottom">
        <a href="${ctx}wap/index">
            <div class="b">
                <img src="${ctx}image/home-norm1.png">
                <span style="color: #ffa42f">首页</span>
            </div>
        </a>
        <a href="${ctx}wap/study/study_today">
            <div class="b">
                <img src="${ctx}image/study_norm2.png">
                <span>学习</span>
            </div>
        </a>
        <a href="javascript:showMyInfo();">
            <div class="b">
                <img src="${ctx}image/my_norm2.png">
                <span>我的</span>
            </div>
        </a>
    </div>

    <!--////////////////////////  vip   ////////////////////////////////-->
        <div class="mask1" style="display: none;">
            <div class="vip">
                <div class="vipTit" style="position: relative">VIP充值<span id="closeVip" style="position: absolute;top: 0.2rem;right: 0.2rem;">X</span></div>
                <div class="vipCon" style="border: 0;font-size: 0.3rem;color: #ffa42f;line-height: 0.8rem;"><div style="width:100%;"><a href="${ctx}wap/userGroupCard/my/activeCode" style="color: #ffa42f;"> 激活码</a><a href="${ctx}wap/remchargeMember/member" style="color: #ffa42f;float: right;"> 会员权益</a></div> </div>
                <div class="vipCon"><span>开通服务：</span><span style="float: right;">会员服务</span></div>
                <div class="vipCon">
                    <span>开通服务：</span>
                    <span style="padding-left: 0.5rem;float: right">
                    <#--<input type="radio" name="vip"/><label>月付</label>-->
                        <input type="radio" checked="checked" name="vip" value="2"/><label>年付</label>
                    <#--<input type="radio" name="vip"/><label>永久</label>-->
                </span>
                </div>
                <div class="vipCon"><span>开通服务：</span><span id="vipPrice" style="float: right;"></span></div>
                <div class="button"><span onclick="now_buy_vip()">立即购买</span></div>
            </div>
        </div>
</div>
</body>
<script>
    $(function(){
        $(".spinner").hide();
        $("#body_s").show();
        var swiper = new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true,
            loop: true,
            autoplay:1000
        });
    });

    function showMyInfo(){
        if(!$("#userId").val()){
            location.href='${ctx}wap/wx/login';
        }else{
            location.href='${ctx}wap/user/set_to_my';
        }
    }

    // 展示视频课程首页
    function showSpkcIndex(){
        location.href='${ctx}wap/course/spkcIndex';
    }

    // 展示直播课程首页
    function showZbkcIndex(){
        location.href='${ctx}wap/course/zbkcIndex';
    }

    // 展示文字课程首页
    function showWzkcIndex(){
        location.href='${ctx}wap/course/wzkcIndex';
    }

    // 展示音频课程首页
    function showYpkcIndex(){
        location.href='${ctx}wap/course/ypkcIndex';
    }

    // 展示在播课程首页
    function showOnLiveIndex(){
        location.href='${ctx}wap/course/onLiveIndex';
    }

    $(function () {

        var isLogin = false;

        if (isEmpty($("#userId").val()) || $("#userId").val() == "" || $("#userId").val() == undefined){

        }else{
            isLogin = true;
        }

        var buyVip = $(".buyVip");
        var vipHeight = buyVip["0"].clientHeight;
        var vipWidth = buyVip["0"].clientWidth;
        var bodyHeight = $("body")["0"].clientHeight;
        var bodyWidth = $("body")["0"].clientWidth;
        var vipTop;
        var vipLeft;
        var max_vipTop = bodyHeight-vipHeight;
        var max_vipLeft = bodyWidth-vipWidth;
        var startX,startY;
        buyVip[0].addEventListener("touchstart",function (e) {
            e.stopPropagation();
            e.preventDefault();
            vipTop = buyVip["0"].offsetTop;
            vipLeft = buyVip["0"].offsetLeft;
            startX = e.changedTouches["0"].screenX;
            startY = e.changedTouches["0"].screenY;
        })
        buyVip[0].addEventListener("touchmove",function (e) {
            e.stopPropagation();
            e.preventDefault();
            var x = e.changedTouches["0"].screenX-startX+vipLeft;
            var y = e.changedTouches["0"].screenY-startY+vipTop;
            if(x>max_vipLeft){x=max_vipLeft;}else if(x<0){x=0;}
            if(y>max_vipTop){y=max_vipTop;}else if(y<0){y=0;}
            buyVip.css({"left":x+"px","top":y+"px"})
        });

        if(isLogin) {
            // 展示课程进行中
            $.ajax({
                async: true,
                type: "POST",
                url: "${ctx}wap/course/showOnLiveList",
                data: {pageNum:1,pageSize:3},
                dataType: "json",
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "";
                        for(var i = 0;i < data.length;i++){
                            var costPriceVal = "免费";
                            if(parseInt(data[i].costPrice) > 0){
                                costPriceVal = "¥"+data[i].costPrice;
                            }
                            htmlVal += "<a href='javascript:;' onclick='showCourseDetail(\""+data[i].tyval+"\",\""+data[i].courseId+"\")'><div class='flexCell'><img class='img1' src='"+data[i].courseCover+"'>"
                                    +"<div class='title'><span class='s1'>"+data[i].courseTitle+"</span></div></div></a>";
                        }
                        $("#onLiveDiv").append(htmlVal);
                    }
                },
                error:function(data){
                    alert("系统异常，请稍后再试!");
                }
            });
        }else {
            $("#onLiveDiv").append("&nbsp;&nbsp;&nbsp;暂无课程......");
        }


        //四位名师教头
        $.ajax({
            async: true,
            url: base+"search/home/searchIndexFourTeachers",
            data: {},
            type:"post",
            dataType: "json",
            success: function (data) {
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length > 0){
                    htmlVal+= '<div class="con3Con ">';
                    if(a[0]){
                        htmlVal+= '<a href="'+base+"search/wellTeacherHome?teacherId="+a[0].userId+'">'
                                +'<div class="flexCell">'
                                +'<img src="'+a[0].rectanglePhoto+'">'
                                +'<div class="mask">'+a[0].userName+'</div>'
                                +'</div>'
                                +'</a>';
                    }
                    if(a[1]){
                        htmlVal+= ' <a href="'+base+"search/wellTeacherHome?teacherId="+a[1].userId+'">'
                                +' <div class="flexCell">'
                                +' <img src="'+a[1].rectanglePhoto+'">'
                                +' <div class="mask">'+a[1].userName+'</div>'
                                +' </div>'
                                +' </a>';
                    }
                    htmlVal+= ' </div>';
                    htmlVal+= '<div class="con3Con ">';
                    if(a[2]){

                        htmlVal+= '<a href="'+base+"search/wellTeacherHome?teacherId="+a[2].userId+'">'
                                +'<div class="flexCell">'
                                +'<img src="'+a[2].rectanglePhoto+'">'
                                +'<div class="mask">'+a[2].userName+'</div>'
                                +'</div>'
                                +'</a>';
                    }
                    if(a[3]){

                        htmlVal+= '<a href="'+base+"search/wellTeacherHome?teacherId="+a[3].userId+'">'
                                +' <div class="flexCell">'
                                +' <img src="'+a[3].rectanglePhoto+'">'
                                +' <div class="mask">'+a[3].userName+'</div>'
                                +' </div>'
                                +' </a>';
                    }
                    htmlVal+= '</div>';
                    $(".fourTeachers").append(htmlVal);
                }
            },
            error: function (data) {
            },
        });

    });

    $("#becameVip")[0].addEventListener("touchstart",function (e) {
        e.stopPropagation();
        $(".mask1").css("display","block");
        var value = $("input[name='vip']:checked").val();
        $.ajax({
            async: false,
            url: "${ctx}wap/remchargeMember/vip",
            data: {"vip": value},
            type:"post",
            dataType: "json",
            success: function (data) {
                if (data.config_sign == "month"){
                    $("#vipPrice").html(data.config_value+"/月");
                }else if (data.config_sign == "year"){
                    $("#vipPrice").html(data.config_value+"/年");
                }else {
                    $("#vipPrice").html(data.config_value+"/永久");
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试！");
            }
        });

    });

    $("#x").click(function () {
        $(".mask1").css("display","none")
    })

    function now_buy_vip() {
       $(".mask1").css("display","none")
       var type = $("input[name='vip']:checked").val();
        //跳转到支付页面
        location.href="${ctx}wap/remchargeMember/vip/vipOrder?type="+type;
    }

    function requsetUrl(orderId) {
        window.location.href="${ctx}wap/wx/auth?orderId="+orderId;
    }


    $(".flexCell").click(function () {
        window.location.href = "${ctx}wap/qrCode";
    });

    $("input[name='vip']").on("click",function () {
        var value = $(this).val();
        $.ajax({
            async: false,
            url: "${ctx}wap/remchargeMember/vip",
            data: {"vip": value},
            type:"post",
            dataType: "json",
            success: function (data) {
                if (data.config_sign == "month"){
                    $("#vipPrice").html(data.config_value+"/月");
                }else if (data.config_sign == "year"){
                    $("#vipPrice").html(data.config_value+"/年");
                }else {
                    $("#vipPrice").html(data.config_value+"/永久");
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试！");
            },
        });
    });




    // 展示课程详情
    function showCourseDetail(courseType,couserId){
        location.href = "${ctx}wap/course/courseDetail?courseType="+courseType+"&courseId="+couserId;
    }

    //判断用户签到
    $(function () {
        if (isEmpty($("#userId").val()) || $("#userId").val() == "" || $("#userId").val() == undefined){
            $(".qiandao").css("display", "block");
        }else {
            $.ajax({
                url: "${ctx}wap/isSignIn?userId="+$("#userId").val(),
                async:false,
                type:"post",
                dataType: "json",
                success: function (data) {
                    if(data.result == 1 && data.message == 'IsAjax') {
                        window.location.href="${ctx}wap/wx/login"
                        return false;
                    }
                    if ("true" == data.data.userSignIn){//用户已经签到
                        $(".qiandao").css("display", "none");
                    }else {
                        $(".qiandao").css("display", "block");
                    }

                },
                error: function (data) {
                    alert("系统异常，请稍后再试！");
                }
            })
        }

    });
    $("#closeVip").click(function () {
        $(".mask1").hide();
    })
</script>
</html>