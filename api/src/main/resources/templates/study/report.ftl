<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/report.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <style>
        html,body{width:100%;height: 100%;}
        #wrapper{width:100%;}
    </style>
</head>
<body>
<div class="con1">
    <div class="con1Select">
        <span ><a href="${ctx}wap/study/study_today"> 今日</a></span>
        <span><a href="${ctx}wap/study/today_recomm"> 推荐</a></span>
        <span class="active">报告</span>
    </div>
</div>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="wrapper" style="top:0.85rem;position: absolute;bottom:1rem">
    <div class="scroller">
        <div class="fresh"><i class="pull_icon"></i><span>下拉加载...</span></div>
<div id="body_s" style="display: none;">
    <div class="con2">
        <img class="con2Img" src=""><img src="" class="v" id="img_v" style="margin-left: -0.3rem; margin-top: 0.25rem;vertical-align: middle;width: 0.4rem;"/>
        <div class="con2Con">
            <div class="usrName"></div><br>
            <div class="usrWrite"></div>
        </div>
        <div class="medal">
            <span>勋章：</span>
            <div class="medalLevel" style=" width: 5.8rem;height: 1rem;overflow: hidden;white-space: nowrap;">
                <img class="level4" src="${ctx}image/l1.png">
                <img class="level4" src="${ctx}image/l2.png">
                <img class="level4" src="${ctx}image/l3.png">
                <img class="level4" src="${ctx}image/l4.png">
                <img class="level4" src="${ctx}image/l5.png">
                <img class="level4" src="${ctx}image/l6.png">
            </div><a href="#">></a>

        </div>
    </div>
    <div class="con4">
        <div class="con4Box">
            <div style="padding-bottom:0.3rem;font-size:0.3rem;text-align: right"><a href="" style="color:#ffa42f" id="scorerule">积分规则</a></div>
            <div class="line1"><span id="min">{{min}}积分</span><span>{{now}}/{{max}}积分</span></div>
            <div class="line2">
                <img src="${ctx}image/jindutiao1_1.png" style="margin-left: 0.05rem;"><img id="jindu" v-bind:style="{width:Vlen}" src="${ctx}image/jindutiao1_3.png"><img src="${ctx}image/jindutiao1_2.png">
            </div>
            <div class="line1"><span>{{minLevel}}级</span><span>{{maxLevel}}级</span></div>
        </div>
    </div>
    <div class="con5">
        <div class="con5Box">
            <div class="line1"><span id="s1" v-bind:style="{height:eve*Vue1+'rem'}"><p>{{Vue1}}</p></span><span id="s2" v-bind:style="{height:eve*Vue2+'rem'}"><p>{{Vue2}}</p></span><span id="s3" v-bind:style="{height:eve*Vue3+'rem'}"><p>{{Vue3}}</p></span><span id="s4" v-bind:style="{height:eve*Vue4+'rem'}"><p>{{Vue4}}</p></span></div>
            <div class="line2"></div>
            <div class="line3"><span id="ss1">已学<br>课程数</span><span id="ss2">已学<br>教师人数</span><span id="ss3">分享<br>课程次数</span><span id="ss4">邀请<br>好友人数</span></div>
            <div class="line4">学习总时长：<span>{{parseInt(Vue5/60)}}</span>分钟<span>{{Vue5%60}}</span>秒</div>
            <div class="line5">课程完成数据分析柱状图</div>
        </div>
    </div>

            <div id="myReport"></div>

    <#--<div class="con3">
       &lt;#&ndash; <div class="con3Item">
            <img class="con3ItemLeft" src="image/kechenging1.png">
            <div class="con3ItemRight">
                <span class="con3ItemTitle">时间不在于你拥有多少，而在于你怎样使用</span>
                <div class="con3ItemBody">
                    <div class="con3ItemTime">
                        <p>主讲老师：艾克</p>
                        <p>3月24日 开始</p>
                    </div>
                    <div class="con3ItemTime" style="text-align:right;">
                        <span>继续学习</span>
                    </div>
                </div>
            </div>
        </div>&ndash;&gt;
    </div>-->
</div>
        <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
    </div>
</div>
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
</body>
<script>

    $(function(){
        $(".spinner").hide();
        $("#body_s").show();

     /*个人信息*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"user/getUserInfo",
        dataType: "json",
        success: function (data) {
            if (data.result == 1) {
                var post_data = data.data;
                $(".con2Img").attr("src",post_data.photo);
                $(".usrName").html(post_data.loginName);
                $(".usrWrite").html(post_data.signature);
                if(post_data.userType == 1){//教头
                    $("#img_v").attr("src","${ctx}image/teacher.png");
                    $("#img_v").css("border-radius","50%");
                }else{
                    if(post_data.userIdentity == 2){
                        $("#img_v").attr("src","${ctx}image/v.png");
                    }else{
                        $("#img_v").attr("src","${ctx}image/v.png");
                         $("#img_v").css("display","none");
                    }
                }

            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
     /*等级进度条*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"report/intellMessage",
        dataType: "json",
        success: function (data) {
            if (data.result == 1) {
                var Vmin = data.lastIntegral;
                var Vmax = data.lowerIntegral;
                var Vnow =data.presentInteger;
                var VminLevel = data.presentLevel;
                var VmaxLevel = data.lowerLevel;
                var con4 = new Vue({
                    el:'.con4',
                    data:{
                        min:Vmin,
                        max:Vmax,
                        now:Vnow,
                        minLevel:VminLevel,
                        maxLevel:VmaxLevel
                    },
                    computed:{
                        Vlen:function () {
                            if(this.now >= 30000){
                                return [6/(60000-this.min).toFixed(3)]*(60000-this.min)+"rem";
                            }else{
                                return [6/(this.max-this.min).toFixed(3)]*(this.now-this.min)+"rem";
                            }

                        }
                    }
                })
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
     /*柱状图数据*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"report/histogramMessage",
        dataType: "json",
        success: function (data) {
            if (data.message == "获取成功") {
                var d = data.data;
                var Vue1 = d.finish;
                var Vue2 = d.finishTearch;
                var Vue3 = d.shareCourseNum;
                var Vue4 = d.inviteFriendNum;
                var Vue5 = d.totalDuration;
                var con5 = new Vue({
                    el: '.con5',
                    data: {
                        Vue1: Vue1,
                        Vue2: Vue2,
                        Vue3: Vue3,
                        Vue4: Vue4,
                        Vue5: Vue5
                    },
                    computed: {
                        eve: function () {
                            var max = Math.max.apply(null, [this.Vue1, this.Vue2, this.Vue3, this.Vue4]);
                            return (2 / max).toFixed(3);
                        }
                    }
                })
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })

     var curpage=1;
     var freshH=$(".fresh").height();
     var moreState=0;
     var freshState=0;
     var myscroll = new iScroll("wrapper",{
         topOffset:freshH,
         onScrollMove:function(){
             if(this.y >=-freshH){
                 if(this.y>=0){
                     this.minScrollY=0;
                     freshState=1;
                     freshElement();
                 }else if(0>this.y>-freshH){
                     freshState=0;
                     freshElement();
                 }else{
                     console.log("没这种情况")
                 }
             }else{
                 if (this.y<(this.maxScrollY)) {
                     moreState=1;
                     moreElement();
                 }else{
                     moreState=0;
                     moreElement();
                 }
             }
         },
         onScrollEnd:function(){
             if(freshState===1){
                 freshState=2;
                 pullDownAction();
                 freshElement();
             }else if (moreState===1) {
                 moreState=2;
                 moreElement();
                 pullUpAction();
             }else{
                 this.refresh();
             }

         },
         onRefresh:function(){
         }
     });

     function pullUpAction(){
         setTimeout(function(){
             curpage++;
             showWzkcList(curpage,10,0);
         }, 1000)
     }

     function pullDownAction(){
         setTimeout(function(){
             curpage = 1;
             $("#myReport").html("");// 先清空原来加载的数据
             showWzkcList(1,10,1);
         }, 1000)
     }

     function moreElement() {
         switch (moreState){
             case 0:
                 $('.more .pull_icon').removeClass('flip loading');
                 $('.more span').text('上拉加载...');
                 break;
             case 1:
                 $('.more .pull_icon').addClass('flip');
                 $('.more .pull_icon').removeClass('loading');
                 $('.more span').text('释放加载...');
                 break;
             case 2:
                 $('.more .pull_icon').addClass('loading');
                 $('.more span').text('加载中...');
                 break;
             case 3:
                 $('.more .pull_icon').addClass('loading');
                 $('.more span').text('加载成功');
                 break;
             default:
                 $('.more .pull_icon').addClass('loading');
                 $('.more span').text('加载失败！！');
                 break;
         }
     }

     function freshElement() {
         switch (freshState){
             case 0:
                 $('.fresh .pull_icon').show();
                 $('.fresh .pull_icon').removeClass('flip loading');
                 $('.fresh span').text('下拉刷新...');
                 break;
             case 1:
                 $('.fresh .pull_icon').addClass('flip');
                 $('.fresh .pull_icon').removeClass('loading');
                 $('.fresh span').text('释放刷新...');
                 break;
             case 2:
                 $('.fresh .pull_icon').addClass('loading');
                 $('.fresh span').text('刷新中...');
                 break;
             case 3:
                 $('.fresh .pull_icon').hide();
                 $('.fresh span').text('刷新成功');
                 break;
             default:
                 $('.fresh .pull_icon').addClass('loading');
                 $('.fresh span').text('刷新失败！！');
                 break;
         }
     }



     // 展示文字课程列表
     showWzkcList(1,10,1);
     function showWzkcList(pageNum,pageSize,allIscrollState){
         $.ajax({
             async: true,
             type: "POST",
             url: base+"report/studyCourseMessage",
             data: {pageNum:pageNum,pageSize:pageSize},
             dataType: "json",
             success: function (data) {
                 var a = data.data;
                 var html = '<div class="con3">';
                 if(data != null && a.length > 0){
                     for (var i =0; i<a.length; i++){
                         html+= '<a href= "'+ base+'study/today_detail?courseId='+a[i].courseId+'"><div class="con3Item">'
                                 +'<img class="con3ItemLeft" src="'+a[i].courseImg+'">'
                                 +'<div class="con3ItemRight" style="width: 3.9rem">'
                                 +'<span class="con3ItemTitle">'+a[i].courseTitle+'</span>'
                                 +'<div class="con3ItemBody">'
                                 +'<div class="con3ItemTime">'
                                 +'<p>发布者：'+a[i].tname+'</p>'
                                 +'<p>'+new Date(a[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm")+'</p>'
                                 +'</div>'
                                 +'<div class="con3ItemTime" style="text-align:right;">'
                                 +'<a href= "'+ base+'study/today_detail?courseId='+a[i].courseId+'"><span>'+pandaun(a[i].viewFinishStatus)+'</span></a>'
                                 +'</div>'
                                 +'</div>'
                                 +'</div>'
                                 +'</div></a>';
                     }
                     html+='</div>';
                     $("#myReport").append(html);
                     if($("#wrapper").height() > $(".scroller").height()){
                         $(".more").hide();
                     }else{
                         $(".more").show();
                     }
                     if(allIscrollState){
                         freshState = 3;
                         freshElement();
                     }else{
                         moreState=3;
                         moreElement();
                         myscroll.refresh();
                     }
                     setTimeout(function () {
                         myscroll.refresh();
                     }, 1000)
                 }else{
                     if(allIscrollState){
                         freshState = 3;
                         freshElement();
                     }else{
                         moreState=3;
                         // moreElement();
                         $('.more .pull_icon').removeClass('flip loading');
                         $('.more span').text('没有更多数据');
                     }
                     setTimeout(function () {
                         myscroll.refresh();
                     }, 1000)
                 }
             },
             error:function(data){
                 if(allIscrollState){
                     freshState=4;
                     freshElement();
                     myscroll.refresh();
                 }else {
                     moreState=4;
                     moreElement();
                     myscroll.refresh();
                 }
             }
         });
     }
     function pandaun(v) {
         if(v == 0){
             return "继续学习";
         }else{
             return "完成学习";
         }
     }
    });

    /*积分规则*/
    $.ajax({
        async: false,
        type: "POST",
        url: base+"sysDoc/searchSdcByType",
        data :{docType:4},
        dataType: "json",
        success: function (data) {
           var result = data.data;
           $("#scorerule").attr("href",result);
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
</script>
</html>