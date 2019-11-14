var curpage=1;
var freshH;
var moreState=0;
var freshState=0;
$(function () {
    freshH=$(".fresh").height();
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
    teacherUserListByUserTypeId(1,10,$(".search .searchBox input").val(), $("#userTypeId").val(),1);
    $(".searchBtn").click(function () {
        $("#contentDiv").empty();
        teacherUserListByUserTypeId(curpage,10,$(".search .searchBox input").val(), $("#userTypeId").val(),1);
    });

    function pullUpAction(){
        setTimeout(function(){
            curpage++;
            teacherUserListByUserTypeId(curpage,10,$(".search .searchBox input").val(), $("#userTypeId").val(),0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            curpage =1;
            $("#contentDiv").html("");// 先清空原来加载的数据
            teacherUserListByUserTypeId(curpage,10,$(".search .searchBox input").val(), $("#userTypeId").val(),0);
        }, 1000)
    }



//获取每个分类下的教头列表
    function teacherUserListByUserTypeId(pageNum,pageSize,keyword,userTypeId,allIscrollState){
        $.ajax({
            async: true,
            url: base + "search/teacherUserListByUserTypeId",
            data: {pageNum:pageNum,pageSize:pageSize,keyword:keyword,userTypeId:userTypeId},
            type: "post",
            dataType: "json",
            success: function (data) {
                $("#classify-ul").empty();
                var a = data.data;
                var htmlVal = "";
                if (a != null && a.length > 0) {
                    for (var i = 0; i < a.length; i++) {
                        htmlVal += '<div class="yijicell"><a href="'+base+"search/wellTeacherHome?teacherId="+a[i].userId+'"><img class="img" src="'+a[i].photo+'"><p class="p">'+a[i].userName+'</p></a> </div>';
                    }
                    $("#contentDiv").append(htmlVal);
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
                    $('.more').show();
                    $('.more span').text('没有更多数据');
                }
            },
            error: function (data) {
                if(allIscrollState){
                    freshState=4;
                    freshElement();
                    myscroll.refresh();
                }else {
                    moreState=4;
                    moreElement();
                    myscroll.refresh();
                }
            },
        });
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
});
