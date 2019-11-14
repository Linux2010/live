<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/goon.css" rel="stylesheet" type="text/css"/>
    <link href="//nos.netease.com/vod163/nep.min.css?v=1" rel="stylesheet">
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <style>
        .line{padding:0.2rem;border-bottom:0.02rem solid #ccc}
        .line .tit{line-height: 0.4rem;display: inline-block;font-size: 0.26rem;}
        .line .pay{line-height: 0.8rem;float: right;font-size: 0.28rem}
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;position: fixed;top: 0;z-index: 10;width: 100%;}
        .con1 span{color:white;}

        video::-webkit-media-controls {
            overflow: hidden !important;
        }
        video::-webkit-media-controls-enclosure {
            width: calc(100% + 32px);
            margin-left: auto;
        }

    </style>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/study/study_today" style="color: white"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">课程详情</span>
</div>
<input type="hidden" value="${courseId}" id="courseId"/>
<div id="cc" class="cc">

</div>



</body>
<script src="//nos.netease.com/vod163/nep.min.js"></script>
<script>
    var listRLiLine = $(".cc");
    var html ="";
    /*我的课程学习情况*/
    $.ajax({
        async: false,
        type: "POST",
        url: base + "study/getTodayStudyDetails",
        data: { 'courseId': $("#courseId").val()},
        dataType: "json",
        success: function (data) {
            var a = data.data;
            if(a != null){
                html+='<div class="radio" style="margin-top: 0.8rem">';
                     if(a.tyval == "wzkc"){
                html += '<img class="vadioImg" src="'+a.courseCover+'"/>'
                      }else if(a.tyval == "spkc"){
                html += ' <video id="my-video" class="video-js radio vjs-big-play-centered vjs-fluid"  x-webkit-airplay="allow" webkit-playsinline controls preload="auto" data-setup={}></video>'
                       }else if(a.tyval == "ypkc"){
                html += ' <div class="radio" id="courseImg"><img class="vadioImg" src="'+a.courseCover+'"/></div><audio id="my-audio" style="width: 100%;" controls="controls"></audio>'
                     }else if(a.tyval == "jhdk"){
                         html += '<img class="vadioImg" src="'+a.courseCover+'"/>'
                     }else if(a.tyval == "zbkc"){
                         html += '<img class="vadioImg" src="'+a.courseCover+'"/>'
                     }
                html+='</div>'
                        +'<div class="con2">'
                        +'<div class="user">'
                        +'<img class="con2Img" src="'+a.photo+'">'
                        +'<div class="con2Con">'
                        +' <div class="usrName">'+a.tname+'</div>'
                        +'<div class="usrWrite"></div>'
                        +'</div>'
                        +'</div>'
                        +'<div class="con2Title">'+a.courseTitle+'</div>'
                        +'</div>'
                        +' <div class="con2" style="margin:0.2rem 0;">'
                        +' <div class="con4Title">'
                        +'<img src="${ctx}image/lingxing.png" alt=""><span>课程安排</span><img src="${ctx}image/lingxing.png" alt="">'
                        +' </div>'
                        +'<div class="con2list">'
                        +' <p>'+a.courseIntro+'</p>'
                        +' </div>'
                        +'</div>'
                        +'<div class="con3">';
                        if(a.tyval == "ypkc" || a.tyval == "spkc"){//视频课程
                            if(a.viewFinishStatus == 0){
                                html+=' <span class="goon studyContinue">继续学习</span>'
                            }
                        }else if(a.tyval == "wzkc"){
                            if(a.viewFinishStatus == 0){
                                html+=' <a><span class="goon wenzi">继续学习</span></a>'
                            }
                        }else if(a.tyval == "jhdk"){
                            html+=' <a><span class="goon jhdk"></span></a>'
                        }else if(a.tyval == "zbkc"){
                            if(a.viewFinishStatus == 0){
                                html+=' <a><span class="goon zhibo">继续学习</span></a>'
                            }
                        }
                        html+=' </div>';
                listRLiLine.html(html);

                if(a.tyval == "spkc"){
                    // 设置播放器播放路径
                    var myPlayer;
                    if(a.wyUrlVal != null && a.wyUrlVal != ""){
                        //获取咱们组件的基类，所有组件都要继承自这个类。
                        var Component = neplayer.getComponent("Component");
                        myPlayer = neplayer("my-video",{
                            "autoplay": false,
                            controlBar:{
                                'volumeMenuButton':true
                            },
                            "poster":a.courseCover
                        });
                    }
                    if(a.wyUrlVal != null && a.wyUrlVal != ""){
                        if(new Date().getTime() >= a.courseBeginTime) {
                            myPlayer.setDataSource({src: a.wyUrlVal, type: "video/mp4"}, {
                                src: a,
                                type: "video/x-flv"
                            }, {src: a, type: "application/x-mpegURL"});
                        }
                    }
                    if(a.wyUrlVal != null && a.wyUrlVal != ""){
                            myPlayer.on("play",function(){
                                if(new Date().getTime() < a.courseBeginTime){
                                    alert("未到开播时间");
                                    return false;
                                }else{
                                    if(a.wyUrlVal == null || a.wyUrlVal == ""){// 如果没有上传视频，则提示视频还未上传
                                        alert("该课程尚未上传视频");
                                        return false;
                                    }
                                    var time = totalTime();
                                    if(a.viewFinishStatus == 0){
                                        myPlayer.seekTo(time*1000);//在上次结束的时间开始播放 myPlayer.getCurrentPosition()
                                        // myPlayer.seekTo(myPlayer.getCurrentPosition()/1000);
                                    }else{
                                        myPlayer.seekTo(0);//从0开始播放
                                        isEndVal = 1;
                                    }
                                }

                            });

                        //s
                        // 视频暂停播放监听
                        myPlayer.on("pause",function(){
                            var isEndVal = 0;
                            if(myPlayer.getCurrentPosition() == myPlayer.getDuration()){
                                isEndVal = 1;
                            }
                            var seeTimeVal = parseInt(myPlayer.getCurrentPosition()/1000);// 视频播放时长
                            var courseTimeVal = parseInt(myPlayer.getDuration()/1000);// 视频总时长
                            // 记录视频播放时间
                            $.ajax({
                                async: false,
                                type: "POST",
                                url: "${ctx}wap/course/operCourseFinishRecord",
                                data: {courseId:a.courseId,seeTime:seeTimeVal,courseTime:courseTimeVal,isEnd:isEndVal},
                                dataType: "json",
                                success: function (data) {

                                    if(data == null || data == "1"){
                                        alert("记录视频播放位置失败");
                                        return false;
                                    }
                                },
                                error:function(data){
                                    alert("系统异常，请稍后再试！");
                                }
                            });
                        });
                        // 开发播放
                        $(".goon.studyContinue").click(function(){
                            if(new Date().getTime() < a.courseBeginTime){
                                alert("未到开播时间");
                                return false;
                            }else{
                                var time = totalTime();
                                myPlayer.seekTo(time*1000);
                                myPlayer.play();
                            }
                        });
                        myPlayer.on("ended",function(){
                            var isEndVal = 0;
                            if(myPlayer.getCurrentPosition() == myPlayer.getDuration()){
                                isEndVal = 1;
                            }
                            var seeTimeVal = parseInt(myPlayer.getCurrentPosition()/1000);// 视频播放时长
                            var courseTimeVal = parseInt(myPlayer.getDuration()/1000);// 视频总时长
                            // 记录视频播放时间
                            $.ajax({
                                async: false,
                                type: "POST",
                                url: "${ctx}wap/course/operCourseFinishRecord",
                                data: {courseId:a.courseId,seeTime:seeTimeVal,courseTime:courseTimeVal,isEnd:isEndVal},
                                dataType: "json",
                                success: function (data) {

                                    if(data == null || data == "1"){
                                        alert("记录视频播放位置失败");
                                        return false;
                                    }
                                },
                                error:function(data){
                                    alert("系统异常，请稍后再试！");
                                }
                            });
                        });
                    }
                }else if(a.tyval == "wzkc"){
                    //文字开始学习
                    var wenzi = a.courseContent;

                    $(".goon.wenzi").click(function(){

                        if(new Date().getTime() < a.courseBeginTime){
                            alert("未到开播时间");
                            return false;
                        }else{
                            // 更新文字课程文章是否观看的标记
                            $.ajax({
                                async: false,
                                type: "POST",
                                url: "${ctx}wap/course/modifyWzkcFlag",
                                data: {courseId:a.courseId},
                                dataType: "json",
                                success: function (data) {
                                    if(data != null && data == 0){
                                        if(wenzi == null || wenzi == ""){
                                            alert("该课程尚未上传文章");
                                            return false;
                                        }else{
                                            window.location.href =wenzi;
                                        }
                                    }
                                },
                                error:function(data){
                                    alert("系统异常，请稍后再试！");
                                }
                            });
                        }
                        //点击的时候才出来答题
                        if(a.ktYnFlag == 1){// 如果有答题，则提示是否开始答题
                            if(a.dtFlag == 0){// 如果未答过题，则提示是否开始答题
                                if(a.version == 2){// 代表着已观看了文章
                                    if(confirm("是否开始答题")==true){
                                        location.href = "${ctx}wap/topic/my/myTopic?type=1&courseTopicExaminationId="+a.kjId;
                                    }else{
                                        return false;
                                    }
                                }
                            }
                        }
                    });


                }else if(a.tyval == "ypkc"){

                    // 设置播放器播放路径
                    var player = document.getElementById("my-audio");
                    // 如果是会员，或者以购买的话，则为音频加载地址，否则不加载
                    if(a.wyUrlVal != null && a.wyUrlVal != ""){
                            if(new Date().getTime() >= a.courseBeginTime) {
                                player.src = a.wyUrlVal;
                            }
                        }
                    // 播放监听事件
                    player.addEventListener("play",function(){

                        if(new Date().getTime() < a.courseBeginTime){
                            alert("未到开播时间");
                            return false;
                        }else{
                            if(a.wyUrlVal == null || a.wyUrlVal == ""){// 如果没有上传音频，则提示音频还未上传
                                alert("该课程尚未上传音频");
                                return false;
                            }
                            var yptime = totalTime();
                            if(a.viewFinishStatus == 0){
                                player.currentTime = yptime;//在上次结束的时间开始播放 myPlayer.getCurrentPosition()
                                // myPlayer.seekTo(myPlayer.getCurrentPosition()/1000);
                            }else{
                                player.currentTime = 0;//从0开始播放
                                isEndVal = 1;
                            }
                        }


                    });
                    // 暂停监听事件
                    player.addEventListener("pause",function(){
                        var isEndVal = 0;
                        if(player.currentTime == player.duration){
                            isEndVal = 1;
                        }
                        var seeTimeVal = parseInt(player.currentTime);// 音频播放时长
                        var courseTimeVal = parseInt(player.duration);// 音频总时长
                        // 记录音频播放时间
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "${ctx}wap/course/operCourseFinishRecord",
                            data: {courseId:a.courseId,seeTime:seeTimeVal,courseTime:courseTimeVal,isEnd:isEndVal},
                            dataType: "json",
                            success: function (data) {
                                if(data == null || data == "1"){
                                    alert("记录音频播放位置失败");
                                    return false;
                                }
                            },
                            error:function(data){
                                alert("系统异常，请稍后再试！");
                            }
                        });
                    });
                    // 开发播放
                    $(".goon.studyContinue").click(function(){
                        if(new Date().getTime() < a.courseBeginTime){
                            alert("未到开播时间");
                            return false;
                        }else{
                            var yptime = totalTime();
                            player.play();
                            player.currentTime = a.yptime;
                        }

                    });
                    // 播放完毕监听事件
                    player.addEventListener("ended",function(){
                        var isEndVal = 0;
                        if(player.currentTime == player.duration){
                            isEndVal = 1;
                        }
                        var seeTimeVal = parseInt(player.currentTime);// 音频播放时长
                        var courseTimeVal = parseInt(player.duration);// 音频总时长
                        // 记录视频播放时间
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "${ctx}wap/course/operCourseFinishRecord",
                            data: {courseId:a.courseId,seeTime:seeTimeVal,courseTime:courseTimeVal,isEnd:isEndVal},
                            dataType: "json",
                            success: function (data) {
                                if(data == null || data == "1"){
                                    alert("记录视频播放位置失败");
                                    return false;
                                }
                            },
                            error:function(data){
                                alert("系统异常，请稍后再试！");
                            }
                        });
                        if(a.ktYnFlag == 1){// 如果有答题，则提示是否开始答题
                            if(confirm("是否开始答题")==true){
                                location.href = "${ctx}wap/topic/my/myTopic?type=1&courseTopicExaminationId="+a.kjID;
                            }else{
                                return false;
                            }
                        }
                    });
                }else if(a.tyval == "zbkc"){
                    $(".goon.zhibo").click(function(){
                        if(new Date().getTime() < a.courseBeginTime){
                            alert("未到开播时间");
                            return false;
                        }else{
                           window.location.href = "${ctx}wap/course/showRoom?teaId="+a.teacherId+"&imRoomid="+a.imRoomid;
                        }

                    });
                }


            }

        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })

    function totalTime() {
        var totalTime;
        $.ajax({
            async: false,
            type: "POST",
            url: base + "study/getTodayStudyDetails",
            data: { 'courseId': $("#courseId").val()},
            dataType: "json",
            success: function (data) {
                var a = data.data;
                totalTime = a.pauseDuration
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        })
        return totalTime;
    }
/*    window.onload= function () {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "study/getTodayStudyDetails",
            data: {'courseId': $("#courseId").val()},
            dataType: "json",
            success: function (data) {
                var a = data.data;
                if (a.ktYnFlag == 1) {// 如果有答题，则提示是否开始答题
                    if (a.dtFlag == 0) {// 如果未答过题，则提示是否开始答题
                        if (confirm("是否开始答题") == true) {
                            location.href = "wap/topic/my/myTopic?type=1&courseTopicExaminationId=" + a.kjID;
                        } else {
                            return false;
                        }
                    }
                }

            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        })
    }*/
</script>
</html>