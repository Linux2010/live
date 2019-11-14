// 声明存储课程分类ID的数组
var ctArr;

// 声明存储课程分类图片的数组
var ctImgArr;

// 记录当前分类的下标
var indexVal = 0;

$(function(){

    $(".nav1").height(($("body").height()-$(".nav").height())+"px");

    // 初始化课程分类
    $.ajax({
        async: false,
        type: "POST",
        url: $("#basePath").val()+"wap/course/searchCtList",
        data: {typeVal:"spkc",parentId:"-1"},
        dataType: "json",
        success: function (data) {
            if(data != null && data.length > 0){
                // 为课程分类ID数组初始化长度
                ctArr = new Array(data.length);
                // 为课程分类图片数组初始化长度
                ctImgArr = new Array(data.length);
                var htmlVal = "";
                for(var i = 0;i < data.length;i++){
                    // 为课程分类ID数组赋值
                    ctArr[i] = data[i].courseTypeId;
                    // 为课程分类图片数组赋值
                    ctImgArr[i] = data[i].typeUrl;
                    if(i == 0){
                        htmlVal += "<li class='active'><a style='color: rgb(255, 164, 47);'>"+data[i].typeName+"</a></li>";
                    }else{
                        htmlVal += "<li><a>"+data[i].typeName+"</a></li>";
                    }
                }
                $("#ctUl").html(htmlVal);
                // 初始化第一个分类的图片
                $("#ctImg").attr("src",ctImgArr[0]);
                // 默认初始化第一个分类的课程列表
                showSpkcList(1,10,"",0,ctArr[0],1);
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试！");
        }
    });

    var category_nav = $(".category_nav");
    var all_width = category_nav["0"].scrollWidth;
    var scroller = $('#scroller');
    scroller.css({
        'width':all_width + 'px'
    });
    var myscroll1=new iScroll("wrapper",{
        scrollX: true,
        scrollY: false,
        hScrollbar:false,
        vScrollbar : false,
        vScroll:true,
        click : true,
        deceleration:0.001,
    });

    category_nav.on("click","li",function (e) {
        // 先清空原来加载的数据
        $("#courseDiv").html("");
        // 点击分类初始化课程列表
        category_nav.children("li").children("a").css({"color":"#000"});
        $(e.target).css({"color":"#ffa42f"});
        $("#ctImg").attr("src",ctImgArr[$(this).index()]);
        showSpkcList(1,10,"",$(this).index(),ctArr[$(this).index()],1);
    });

    var curpage=1;
    var freshH=$(".fresh").height();
    var moreState=0;
    var freshState=0;
    var myscroll = new iScroll("wrapper1",{
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
            showSpkcList(curpage,10,$("#courseTitle").val(),indexVal,ctArr[indexVal],0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            $("#courseDiv").html("");// 先清空原来加载的数据
            curpage = 1;
            showSpkcList(1,10,$("#courseTitle").val(),indexVal,ctArr[indexVal],1);
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

    // 根据课程标题查询课程列表
    $("#searchCourse").click(function(){
        $("#courseDiv").html("");// 先清空原来加载的数据
        var courseTitleVal = $("#courseTitle").val();
        showSpkcList(1,10,courseTitleVal,indexVal,ctArr[indexVal],1);
    });

    // 展示视频课程列表
    function showSpkcList(pageNum,pageSize,courseTitle,cIndex,courseTypeId,allIscrollState){
        $.ajax({
            async: false,
            type: "POST",
            url: $("#basePath").val() + "wap/course/showSpkcList",
            data: {pageNum:pageNum,pageSize:pageSize,courseTitle:courseTitle,courseTypeId:courseTypeId},
            dataType: "json",
            success: function (data) {
                // 给记录当前分类的下标赋值
                indexVal = cIndex;
                // 拼接视频课程列表内容
                var htmlVal = "";
                if(data != null && data.length > 0){
                    for(var i = 0;i < data.length;i++){
                        var costPriceVal = "免费";
                        if(parseInt(data[i].costPrice) > 0){
                            costPriceVal = "¥"+data[i].costPrice;
                        }
                        var courseTitleStr = data[i].courseTitle;
                        if(courseTitleStr.length > 13){
                            courseTitleStr = courseTitleStr.substring(0,12)+"...";
                        }
                        if(data[i].userIdentity == 2){// 如果当前登录用户是会员，则隐藏价格显示
                            htmlVal += "<a href='javascript:;' onclick='showCourseDetail(\""+data[i].courseId+"\")'><div class='line'>"
                                +"<img class='img1' src='"+data[i].courseImg+"'><div class='img1R'><div class='tit'>"+courseTitleStr+"</div>"
                                +"<div class='user'>教头："+data[i].tname+"</div><div class='null'></div>"
                                +"<div class='data'>"+new Date(data[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm")+"</div></div></div></a>";
                        }else{
                            htmlVal += "<a href='javascript:;' onclick='showCourseDetail(\""+data[i].courseId+"\")'><div class='line'>"
                                +"<img class='img1' src='"+data[i].courseImg+"'><div class='img1R'><div class='tit'>"+courseTitleStr+"</div>"
                                +"<div class='user'>教头："+data[i].tname+"</div><div class='null'></div><div class='price'>"+costPriceVal+"</div>"
                                +"<div class='data'>"+new Date(data[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm")+"</div></div></div></a>";
                        }
                    }
                    $("#courseDiv").append(htmlVal);
                    if($("#wrapper1").height() > $(".scroller1").height()){
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
                    }, 1000);
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
    location.href = $("#basePath").val()+"wap/course/courseDetail?courseType=spkc&courseId="+couserId;
}

// 返回首页
function backFuc(){
    location.href = $("#basePath").val()+"wap/index";
}