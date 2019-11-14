$(function(){

    $(".spinner").hide();
    $("#body_s").show();

    var writeBox1 = $(".writeBox1");
    var b = $(".b");
    var f = $(".f");
    var writeBox4 = $(".writeBox4");
    writeBox1.click(function () {
        b.show();f.hide();writeBox4.focus();
    });

    var curpage=1;
    var freshH=$(".fresh").height();
    var moreState=0;
    var freshState=0;
    var myscroll = new iScroll("wrapper",{
        topOffset:freshH,
        onScrollMove:function(){
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
            if (this.y<(this.maxScrollY)) {
                moreState=1;
                moreElement();
            }else{
                moreState=0;
                moreElement();
            }
        },
        onScrollEnd:function(){
            if(freshState===1){
                freshState=2;
                pullDownAction();
                freshElement();
            }
            if (moreState===1) {
                moreState=2;
                moreElement();
                pullUpAction();
            }
        },
        onRefresh:function(){}
    });

    function pullUpAction(){
        setTimeout(function(){
            curpage++;
            showComm(curpage,10,0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            $("#ccDiv").html("");// 先清空原来加载的数据
            curpage = 1;
            showComm(1,10,1);
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

    // 展示课程评论
    function showComm(pageNum,pageSize,allIscrollState){
        $.ajax({
            async: true,
            type: "POST",
            url: $("#basePath").val()+"wap/course/searchCcList",
            data: {courseId:$("#courseId").val(),pageNum:pageNum,pageSize:pageSize},
            dataType: "json",
            success: function (data) {
                if(data != null && data.length > 0){
                    var htmlVal = "";
                    for(var i = 0;i < data.length;i++){
                        var qmVal = "";
                        if(data[i].signature != null){
                            qmVal = data[i].signature;
                        }
                        var borderVal = "";
                        if(data[i].crList == null){
                            borderVal = "border:0;";
                        }else{
                            if(data[i].crList.length == 0){
                                borderVal = "border:0;";
                            }
                        }
                        htmlVal += "<div class='lLine'><div class='lLineBox' style='"+borderVal+"'><img class='lImg' src='"+data[i].photo+"'>";
                        if(data[i].userIdentity == 2){
                            htmlVal += "<img src='"+$("#basePath").val()+"image/v.png' style='margin-left: -0.4rem; margin-top: 0.8rem;vertical-align: middle;width: 0.4rem;'/>";
                        }
                        htmlVal += "<div class='lImgR1'><div class='lName'>"+data[i].accname
                            +"</div><div class='lSay'>"+qmVal
                            +"</div></div><div class='lImgR2'><div class='lTime'>"+new Date(data[i].commentTime).pattern("yyyy-MM-dd HH:mm:ss")
                            +"</div><div class='lReport' style='color: blue;text-decoration:underline;'></div></div><div class='question'>"+data[i].commentContent+"</div></div>";
                        if(data[i].crList != null && data[i].crList.length > 0){// 如果有回复，则拼接回复，否则去掉下边的边框
                            for(var j = 0;j < data[i].crList.length;j++){
                                htmlVal += "<div class='huifu'>"+data[i].crList[j].replayUserName
                                    +"："
                                    +data[i].crList[j].replayContent+"</div>";
                            }
                        }
                        htmlVal += "</div>";
                    }
                    $("#ccDiv").append(htmlVal);
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
                        myscroll.refresh();
                    }else{
                        $('.more .pull_icon').removeClass('flip loading');
                        $('.more span').text('没有更多数据');
                        myscroll.refresh();
                    }
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

    // 发表评论
    $("#addCommSpan").click(function(){
        var commVal = $(".writeBox4").val();
        if(commVal == null || commVal == ""){
            alert("请输入评论内容");
            return false;
        }else{
            $.ajax({
                async: true,
                type: "POST",
                url: $("#basePath").val()+"wap/course/addComm",
                data: {courseId:$("#courseId").val(),commentContent:commVal},
                dataType: "json",
                success: function (data) {
                    if(data != null){
                        if(data.result == 1 && data.message == 'IsAjax') {
                            window.location.href=$("#basePath").val()+"wap/wx/login"
                            return false;
                        }
                        if(data == 0){
                            var nowCcCount = parseInt($("#totalCcCount").val())+1;
                            $("#totalCcCountSpan").text("评论（"+nowCcCount+"）");
                            $("#totalCcCount").val(nowCcCount);
                            // 清空评论数据
                            $(".writeBox4").val("");
                            // 先清空原来加载的数据
                            $("#ccDiv").html("");
                            curpage = 1;
                            // 展示课程评论
                            showComm(1,10,0);
                            // 隐藏输入框
                            $(".f").show();
                            $(".b").hide();
                            alert("评论成功");
                        }else{
                            alert(data);
                        }
                    }
                },
                error:function(data){
                    alert("系统异常，请稍后再试！");
                }
            });
        }
    });

    // 展示课程详情
    $.ajax({
        async: false,
        type: "POST",
        url: $("#basePath").val()+"wap/course/searchCourseById",
        data: {courseId:$("#courseId").val()},
        dataType: "json",
        success: function (data) {

            if(data != null){

                var teaQmStr = "";
                if(data.signature != null){
                    teaQmStr = data.signature;
                }
                var teaDivHtml = "<img class='con2Img' onclick='showTea(\""+data.teacherId+"\")' src='"+data.photo
                    +"'><div class='con2Con'><div class='usrName'>"+data.tname+"</div>";
                if(data.attentionFlag == 1){
                    teaDivHtml += "<span style='background-color: #ffa42f;color:white;border-radius: 0.1rem;padding:0.1rem 0.2rem;line-height: 0.24rem;float: right;margin-top:0.1rem;' onclick='fcTea(\""+data.teacherId+"\",0,this)'>已关注</span>";
                }else{
                    teaDivHtml += "<span style='background-color: #ffa42f;color:white;border-radius: 0.1rem;padding:0.1rem 0.2rem;line-height: 0.24rem;float: right;margin-top:0.1rem;' onclick='fcTea(\""+data.teacherId+"\",1,this)'>关注</span>";
                }
                teaDivHtml += "<div class='usrWrite'>"+teaQmStr+"</div></div><div class='con2Title'>"+data.courseTitle+"</div>";
                $("#teaDiv").html(teaDivHtml);

                $("#timeSpan").html(new Date(data.courseBeginTime).pattern("yyyy-MM-dd HH:mm"));
                $("#cpSpan").html(data.costPrice);
                $("#vpSpan").html(data.vipPrice);
                $("#courseContent").val(data.courseContent);
                $("#totalCcCount").val(data.totalCcCount);
                $("#courseIntro").html("<p>"+data.courseIntro+"</p>");
                $("#totalCcCountSpan").text("评论（"+data.totalCcCount+"）");

                if(data.collectFlag == 1){
                    $("#bottomDiv").append("<div class='writeBox3'><img src='"+$("#basePath").val()+"image/yellow.png' onclick='collCourse(0,this)'></div>");
                }else{
                    $("#bottomDiv").append("<div class='writeBox3'><img src='"+$("#basePath").val()+"image/white.png' onclick='collCourse(1,this)'></div>");
                }

                // 如果是普通会员未购买，则显示立即购买按钮
                if(data.userIdentity == 1 && data.orderFlag == 0 && data.costPrice > 0){
                    $(".payVip").css("display","block");
                }

                // 设置播放器播放路径
                var myPlayer = neplayer("my-video",{
                    "autoplay": false,
                    controlBar:{
                        'volumeMenuButton':true
                    },
                    "poster":data.courseCover
                });

                // 如果是会员，或者以购买的话，则为视频加载地址，否则不加载
                if(data.userIdentity == 2 || data.orderFlag == 1 || data.costPrice == 0){
                    if(data.wyUrlVal != null && data.wyUrlVal != ""){
                        if(new Date().getTime() >= data.courseBeginTime){
                            myPlayer.setDataSource({src:data.wyUrlVal,type:"video/mp4"},{src:data,type:"video/x-flv"},{src:data,type:"application/x-mpegURL"});
                        }
                    }
                }
                // 视频开始播放监听
                myPlayer.on("play",function(){
                    if(new Date().getTime() < data.courseBeginTime){
                        alert("课程还未开始");
                        return false;
                    }else{
                        if(data.wyUrlVal == null || data.wyUrlVal == ""){// 如果没有上传视频，则提示视频还未上传
                            // 释放播放器资源
                            myPlayer.release();
                            // 显示课程封面
                            $("#courseImg").html("<img class='vadioImg' src='"+data.courseCover+"'/>");
                            $("#courseImg").css("display","block");
                            alert("该课程尚未上传视频");
                            return false;
                        }else{
                            if(data.orderFlag == 0){
                                // 如果是会员或者价格为零，则观看该课程时，代表购买了该课程，该操作为以后流程的走通做了铺垫
                                if(data.userIdentity == 2 || data.costPrice == 0){
                                    // 初始化课程分类
                                    $.ajax({
                                        async: false,
                                        type: "POST",
                                        url: $("#basePath").val()+"wap/course/addVipOrder",
                                        data: {courseId:$("#courseId").val()},
                                        dataType: "json",
                                        success: function (data) {
                                            if(data == null || data == "1"){
                                                alert("拥有该课程失败！");
                                                return false;
                                            }
                                        },
                                        error:function(data){
                                            alert("系统异常，请稍后再试！");
                                        }
                                    });
                                }else{
                                    alert("您未购买该课程，请先购买");
                                    myPlayer.reset();
                                    return false;
                                }
                            }
                        }
                    }
                });
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
                        url: $("#basePath").val()+"wap/course/operCourseFinishRecord",
                        data: {courseId:$("#courseId").val(),seeTime:seeTimeVal,courseTime:courseTimeVal,isEnd:isEndVal},
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
                // 视频播放完毕监听
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
                        url: $("#basePath").val()+"wap/course/operCourseFinishRecord",
                        data: {courseId:$("#courseId").val(),seeTime:seeTimeVal,courseTime:courseTimeVal,isEnd:isEndVal},
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
                    if(data.ktYnFlag == 1){// 如果有答题，则提示是否开始答题
                        if(data.dtFlag == 0){// 如果未答过题，则提示是否开始答题
                            if(confirm("是否开始答题")==true){
                                location.href = $("#basePath").val()+"wap/topic/my/myTopic?type=1&courseTopicExaminationId="+data.kjID;
                            }else{
                                return false;
                            }
                        }
                    }
                });
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试！");
        }
    });

    // 展示课程评论
    showComm(1,10,1);

});

// 跳转到讲师详情
function showTea(teaId){
    location.href = $("#basePath").val()+"wap/search/wellTeacherHome?teacherId="+teaId;
}

// 举报
function report(){
    $.ajax({
        async: true,
        type: "POST",
        url: $("#basePath").val()+"wap/course/report",
        dataType: "json",
        success: function (data) {
            if(data != null){
                if(data == 0){
                    alert("举报成功");
                }
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试！");
        }
    });
}

// 关注讲师
function fcTea(teacherId,flagVal,obj){
    if($(obj).text() == "关注"){
        flagVal = "1";
    }else{
        flagVal = "0";
    }
    $.ajax({
        async: true,
        type: "POST",
        url: $("#basePath").val()+"wap/course/fcTea",
        data: {teacherId:teacherId,isFocus:flagVal},
        dataType: "json",
        success: function (data) {
            if(data != null){
                if(data.result == 1 && data.message == 'IsAjax') {
                    window.location.href=$("#basePath").val()+"wap/wx/login"
                    return false;
                }
                if(data == 0){
                    if(flagVal == 0){
                        $(obj).text("关注");
                        alert("取消关注成功");
                    }else{
                        $(obj).text("已关注");
                        alert("关注成功");
                    }
                }else{
                    alert(data);
                }
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试！");
        }
    });
}

// 收藏课程
function collCourse(flagVal,obj){
    if($(obj).attr("src") == $("#basePath").val()+"image/white.png"){
        flagVal = "1";
    }else{
        flagVal = "0";
    }
    $.ajax({
        async: true,
        type: "POST",
        url: $("#basePath").val()+"wap/course/addCc",
        data: {courseId:$("#courseId").val(),flagVal:flagVal},
        dataType: "json",
        success: function (data) {
            if(data != null){
                if(data.result == 1 && data.message == 'IsAjax') {
                    window.location.href=$("#basePath").val()+"wap/wx/login"
                    return false;
                }
                if(data == 0){
                    if(flagVal == 0){
                        $(obj).attr("src",$("#basePath").val()+"image/white.png");
                        alert("取消收藏成功");
                    }else{
                        $(obj).attr("src",$("#basePath").val()+"image/collection.png");
                        alert("收藏成功");
                    }
                }else{
                    alert(data);
                }
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试！");
        }
    });
}

// 进入结算页面
function showJsPage(){
    location.href = $("#basePath").val()+"wap/course/showJsPage?courseType="+$("#courseType").val()+"&courseId="+$("#courseId").val();
}

// 返回视频课程列表
function backFuc(){
    location.href = $("#basePath").val()+"wap/course/spkcIndex";
}