$(function () {
    var inputBox = $(".inputBox");
    var f = $(".f");
    var b = $(".b");
    var writeBox4 = $(".writeBox4");
    inputBox.click(function () {
        b.show();f.hide();writeBox4.focus();
        window.addEventListener("click",windowClick,false);
    })
    function windowClick(e) {
        if(e.clientY<($("body").height()-$(".write").height())){
            f.show();b.hide();
        }else{
        }
    }

    // 初始化评论列表
    showZxplList(1,10,$("#informationId").val(),1);

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
            showZxplList(curpage,10,$("#informationId").val(),0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            curpage = 1;
            $("#infoDetails").html("");// 先清空原来加载的数据
            showZxplList(1,10,$("#informationId").val(),1);
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

    // 展示资讯评论列表
    function showZxplList(pageNum,pageSize,informationId,allIscrollState){
        $.ajax({
            async: true,
            type: "POST",
            url: $("#basePath").val() + "wap/news/informationCommentList",
            data: {pageNum:pageNum,pageSize:pageSize, informationId: informationId},
            dataType: "json",
            success: function (data) {
                var htmlVal = "";
                if(data != null && data.length > 0){
                    for(var i = 0;i < data.length;i++){
                        var qmVal = "";
                        if(data[i].signature != null){
                            qmVal = data[i].signature;
                        }
                        var borderVal = "";
                        if(data[i].crList == null){
                            borderVal = "border:0;";
                        }else{
                            if(data[i].crList[0] == null){
                                borderVal = "border:0;";
                            }else{
                                if(data[i].crList[0].replyContent == null){
                                    borderVal = "border:0;";
                                }
                            }
                        }
                        htmlVal += "<div class='lLine'><div class='lLineBox' style='"+borderVal+"'><img class='lImg' src='"+data[i].photo+"'>";
                        if(data[i].userIdentity == 2){
                            htmlVal += "<img src='"+$("#basePath").val()+"image/v.png' style='margin-left: -0.40rem; margin-top: 0.8rem;vertical-align: middle;width: 0.4rem;'/>";
                        }
                        htmlVal += "<div class='lImgR1'><div class='lName'>"+data[i].newsUsername
                            +"</div><div class='lSay'>"+qmVal
                            +"</div></div><div class='lImgR2'><div class='lTime'>"+new Date(data[i].createTime).pattern("yyyy-MM-dd HH:mm:ss")
                            +"</div><div class='lReport' style='color: blue;text-decoration:underline;'></div></div><div class='question'>"+data[i].newsContent+"</div></div>";
                        if(borderVal == ""){// 如果有回复，则拼接回复，否则去掉下边的边框
                            for(var j = 0;j < data[i].crList.length;j++){
                                htmlVal += "<div class='huifu'>"+data[i].crList[j].replyUserName
                                    +"："
                                    +data[i].crList[j].replyContent+"</div>";
                            }
                        }
                        htmlVal += "</div>";
                    }
                    $("#infoDetails").append(htmlVal);
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

    // 添加评论
    $("#sendCommentContent").click(function () {
        var commentContent = $("#ccText").val();
        $("#comentContent").html("");
        if (commentContent == "" || commentContent == undefined || commentContent == null){
            alert("评论内容不能为空！");
            return false;
        }
        $.ajax({
            url:$("#basePath").val() + "wap/news/addCommcontent",
            data:{"commtentConten": commentContent, "informationId": $("#informationId").val()},
            type: "post",
            dataType: "json",
            success: function (data) {
                window.removeEventListener("click",windowClick,false);
                if ("success" == data.status){
                    var nowCcCount = parseInt($("#totalCcCount").val())+1;
                    $("#totalCcCountSpan").text("评论（"+nowCcCount+"）");
                    $("#ccText").val("");
                    // 先清空原来加载的数据
                    $("#infoDetails").html("");
                    curpage = 1;
                    // 展示课程评论
                    showZxplList(1,10,$("#informationId").val(),1);
                    // 隐藏输入框
                    $(".f").show();
                    $(".b").hide();
                    alert("评论成功！");
                    var commentNum = parseInt($("#commentNum").val());
                    commentNum += 1;
                    $("#totalCcCountSpan").text("评论（"+commentNum+"）");
                    $("#commentNum").val(commentNum);
                }else {
                    alert(data.message);
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试！");
            }
        });
    });

});
var body = new Vue({
    el:".body",
    data:{
        collection:true,
        agreeState:0,
        disagreeStare:0,
    },
    methods:{//点赞
        agreeF:function () {
            $.ajax({
                async:true,
                url:$("#basePath").val() + "wap/news/addInformation",
                data:{"informationId": $("#informationId").val() ,"type": 1},
                type:"post",
                dataType:"json",
                success: function (data) {
                    if ("success" == data.message){
                        body.agreeState = 1;
                        // 如果点赞成功，则点赞数量加1
                        var agreeNum = parseInt($("#agreeNumVal").val());
                        agreeNum += 1;
                        $(".agreeNum").text(agreeNum);
                        $("#agreeNumVal").val(agreeNum);
                    }else {
                        alert(data.message);
                    }
                },
                error: function (data) {
                    alert("系统异常！");
                }
            })
        },
        disagreeF:function () {
            $.ajax({
                async:true,
                url:$("#basePath").val() + "wap/news/addInformation",
                data:{"informationId": $("#informationId").val() ,"type": 2},
                type:"post",
                dataType:"json",
                success: function (data) {
                    if ("success" == data.message){
                        body.disagreeStare = 1;
                        // 如果倒赞成功，则倒赞数量加1
                        var disagreeNum = parseInt($("#disagreeNumVal").val());
                        disagreeNum += 1;
                        $(".againstNum").text(disagreeNum);
                        $("#disagreeNumVal").val(disagreeNum);
                    }else {
                        alert(data.message);
                    }
                },
                error: function (data) {
                    alert("系统异常！");
                }
            });
        },
        collectioning:function () {
            var isCollect = 0;
            if (body.collection == false){//取消收藏
                isCollect = 2;
            }else {
                isCollect = 1;
            }
            $.ajax({
                async: false,
                url:$("#basePath").val() + "wap/news/collectionedInformation",
                data:{"informationId": $("#informationId").val() ,"isCollect": isCollect},
                type:"post",
                dataType:"json",
                success: function (data) {
                    if ("success" == data.status){
                        if(isCollect == 2){
                            body.collection = true;
                            alert("收藏成功");
                        }else{
                            body.collection = false;
                            alert("取消收藏");
                        }
                    }else {
                        alert("系统异常，请稍后再试！");
                        alert("收藏失败");
                    }
                },
                error: function (data) {
                    alert("系统异常！");
                }
            });
        }
    }
})
if ($("#isCollect").val() == 3){
    body.collection = true;
}else {
    body.collection = false;
}

if ($("#type").val() == 1){
    body.agreeState = 1;
}else if ($("#type").val() == 2){
    body.disagreeStare = 1;
}else {
}