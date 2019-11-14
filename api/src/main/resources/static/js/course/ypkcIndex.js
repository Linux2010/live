$(function(){

    // 初始化音频课程列表
    showYpkcList(1,10,"",1);

    // 点击搜索链接
    $("#searchSpan").click(function(){
        $("#courseDiv").html("");// 先清空原来加载的数据
        showYpkcList(1,10,$("#courseTitle").val(),1);
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
            showYpkcList(curpage,10,$("#courseTitle").val(),0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            $("#courseDiv").html("");// 先清空原来加载的数据
            showYpkcList(1,10,$("#courseTitle").val(),1);
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

    // 展示音频课程列表
    function showYpkcList(pageNum,pageSize,courseTitle,allIscrollState){
        $.ajax({
            async: true,
            type: "POST",
            url: $("#basePath").val() + "wap/course/showYpkcList",
            data: {pageNum:pageNum,pageSize:pageSize,courseTitle:courseTitle},
            dataType: "json",
            success: function (data) {
                if(data != null && data.length > 0){
                    var htmlVal = "<div class='word'>";
                    for(var i = 0;i < data.length;i++){
                        var introStr = data[i].courseIntro;
                        if(introStr.length > 48){
                            introStr = introStr.substring(0,47)+"...";
                        }
                        var costPriceVal = "免费";
                        if(parseInt(data[i].costPrice) > 0){
                            costPriceVal = "¥"+data[i].costPrice;
                        }
                        var courseTitleStr = data[i].courseTitle;
                        if(courseTitleStr.length > 18){
                            courseTitleStr = courseTitleStr.substring(0,17)+"...";
                        }
                        if(data[i].userIdentity == 2) {// 如果当前登录用户是会员，则隐藏价格显示
                            htmlVal += "<div class='line' onclick='showCourseDetail(\""+data[i].courseId+"\")'><img class='img' src='"+data[i].courseImg+"'>"
                                +"<div class='priceLine'><span class='s1'>"+courseTitleStr+"</span></div>"
                                +"<div class='pLine'>"+introStr
                                +"</div><div class='dataLine'><span class='s1'>"+data[i].tname
                                +"</span><span class='s2'>"+new Date(data[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm")+"</span></div></div>";
                        }else{
                            htmlVal += "<div class='line' onclick='showCourseDetail(\""+data[i].courseId+"\")'><img class='img' src='"+data[i].courseImg+"'>"
                                +"<div class='priceLine'><span class='s1'>"+courseTitleStr+"</span><span class='s2'>"+costPriceVal+"</span></div>"
                                +"<div class='pLine'>"+introStr
                                +"</div><div class='dataLine'><span class='s1'>"+data[i].tname
                                +"</span><span class='s2'>"+new Date(data[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm")+"</span></div></div>";
                        }
                    }
                    htmlVal += "</div>";
                    $("#courseDiv").append(htmlVal);
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
                    $('.more .pull_icon').removeClass('flip loading');
                    $('.more span').text('没有更多数据');
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
});

// 展示课程详情
function showCourseDetail(couserId){
    location.href = $("#basePath").val()+"wap/course/courseDetail?courseType=ypkc&courseId="+couserId;
}

// 返回首页
function backFuc(){
    location.href = $("#basePath").val()+"wap/index";
}