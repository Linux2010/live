<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/tuijian.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
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
<input type="hidden" id="userIdentity" value="${userIdentity!}"/>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="body_s" style="display: none;">
    <div class="con1">
        <div class="con1Select">
            <span ><a href="${ctx}wap/study/study_today">今日</a></span>
            <span class="active">推荐</span>
            <span><a href="${ctx}wap/study/report_page">报告</a></span>
        </div>
    </div>
    <div style="width: 100%;height: 0.85rem;"></div>
    <#--<div class="con2" style="position: relative;">
        <img src="${ctx}image/kechenging2.png" alt="" style="width:100%;height: 3rem;"/>
    </div>-->
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <#if advertiseList??>
            <#list advertiseList as list>
                <div class="swiper-slide">
                    <#if list.linkType == 2><#--链接到教头详情-->
                        <a href="${ctx}wap/search/wellTeacherHome?teacherId=${list.detailId}"><img src="${list.adverImg}" style="height: 3.4rem;width: 7.5rem;"></a>
                    <#--<#elseif list.linkType == 3>&lt;#&ndash;链接到商品详情&ndash;&gt;
                        <a href="${ctx}?detailId=${list.detailId}"><img src="${list.adverImg}" style="width: 100%;"></a>-->
                    <#elseif list.linkType == 4><#--链接到音频课程详情-->
                        <a href="${ctx}wap/course/courseDetail?courseType=ypkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="height: 3.4rem;width: 7.5rem;"></a>
                    <#elseif list.linkType == 5><#--链接到视频课程详情-->
                        <a href="${ctx}wap/course/courseDetail?courseType=spkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="height: 3.4rem;width: 7.5rem;"></a>
                    <#elseif list.linkType == 6><#--链接到直播课程详情-->
                        <a href="${ctx}wap/course/courseDetail?courseType=zbkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="height: 3.4rem;width: 7.5rem;"></a>
                    <#elseif list.linkType == 7><#--链接到文字课程详情-->
                        <a href="${ctx}wap/course/courseDetail?courseType=wzkc&courseId=${list.detailId}"><img src="${list.adverImg}" style="height: 3.4rem;width: 7.5rem;"></a>
                    <#else><#--链接到江湖大课详情-->
                        <a href="${ctx}wap/course/courseDetail?courseType=jhdk&courseId=${list.detailId}"><img src="${list.adverImg}" style="height: 3.4rem;width: 7.5rem;"></a>
                    </#if>
                </div>
            </#list>
        </#if>
        </div>
        <div class="swiper-pagination"></div>
    </div>
    <div class="con3">
           <div class="con3Tit">
               <span>优质课程</span><a href="${ctx}wap/study/excellent_page"><span style="float: right;">更多 》</span></a>
           </div>
        <div id="html_course">

        </div>
    </div>
        <div class="con3">
               <div class="con3Tit">
                   <span>精英教头</span><a href="${ctx}wap/study/teacher_page"><span style="float: right;">更多 》</span></a>
               </div>
            <#list tealist as data>
                <#if data_index == 0>
                    <div class="con3Item" style="border:0;">
                        <a href="${ctx}wap/search/wellTeacherHome?teacherId=${data.userId}">
                             <img class="con3ItemLeft" src="${data.rectanglePhoto}" style="width:3.2rem;height:auto;">
                        </a>
                        <div class="con3ItemRight" style="width:3.5rem;">
                            <span class="con3ItemTitle">${data.realName!}</span>
                            <div class="con3ItemBody">
                                <div class="con3ItemTime" style="text-align:right;width: 100%;font-size: 0.26rem;" >
                                    <p>视频课程：${data.spkcNum}篇</p>
                                    <p>音频课程：${data.ypkcNum}篇</p>
                                    <p>文字课程：${data.wzkcNum}篇</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
        </#list>

                <div class="con3Con">
                    <#list tealist as tea>
                        <#if tea_index != 0>
                            <div class="flexCell">
                                <a href="${ctx}wap/search/wellTeacherHome?teacherId=${tea.userId}">
                                    <img src="${tea.photo}"style="width:2.2rem;">
                                </a>
                                <div style="text-align: center">${tea.realName!}</div>
                            </div>
                        </#if>
                    </#list>
                </div>
        </div>

    <div class="con3" style="height: auto">
            <div class="con3Tit">
                <span>优选商品</span><a href="javascript:void(0)"><span style="float: right;">更多 》</span></a>
            </div>
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
                <img src="${ctx}image/study_norm1.png">
                <span style="color: #ffa42f">学习</span>
            </div>
        </a>
        <a href="${ctx}wap/user/set_to_my">
            <div class="b">
                <img src="${ctx}image/my_norm2.png">
                <span>我的</span>
            </div>
        </a>
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
    var listRLiLine = $(".swiper-wrapper");
    var html ="";
    /*轮播图*/
//    $.ajax({
//        async: false,
//        type: "POST",
//        url: base+"report/indexImg",
//        dataType: "json",
//        success: function (data) {
//            var a = data.data;
//            for (var i =0; i<a.length; i++){
//                html+= '<div class="swiper-slide"><img src="'+a[i].adverImg+'" style="width: 100%;"></div>';
//            }
//            listRLiLine.html(html);
//        },
//        error: function (data) {
//            alert("系统异常，请稍后重试");
//        }
//    })


    var listRLiLine1 = $("#html_course");
    var htmlVal="";
    /*优质课程推荐*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"study/youzhicourse",
        dataType: "json",
        success: function (data) {
            var a = data;
            if(a != null && a.length > 0){
                for (var i =0; i<a.length; i++){
                    htmlVal+= '<a href="${ctx}wap/course/courseDetail?courseType='+a[i].tyval+'&courseId='+a[i].courseId+'"><div class="con3Item">'
                            +'<img class="con3ItemLeft" src="'+a[i].courseImg+'">'
                            +'<div class="con3ItemRight">'
                            +'<span class="con3ItemTitle">'+a[i].courseTitle+'</span>'
                            +'<div class="con3ItemBody">'
                            +'<div class="con3ItemTime" style="width: 55%">'
                            +'<p  style="width: 110px">主讲教头：'+a[i].tname+'</p>'
                            +'<p style="width: 110px">'+new Date(a[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm")+'</p>'
                            +'</div>'
                            +'<div class="con3ItemTime" style="text-align:right;width: 42%">';
                            if ($("#userIdentity").val() == 2){
                                console.log($("#userIdentity").val());
                                htmlVal+='<a><b></b></a>';
                            }else {
                                htmlVal+='<a><b>'+check_(a[i].costPrice)+'</b></a>';
                            }
                    htmlVal+='</div>'
                            +'</div>'
                            +'</div>'
                            +'</div></a>';
                }
                listRLiLine1.html(htmlVal);
            }

        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
    function check_(v) {
        if(v == 0){
            return "免费";
        }else{
            return "￥"+v;
        }
    }
</script>
</html>