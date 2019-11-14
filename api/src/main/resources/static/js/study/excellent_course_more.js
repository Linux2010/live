$(function(){

    // 初始展示优质课程列表
    showcourseList(1,10,1);

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
            showcourseList(curpage,10,0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            curpage = 1;
            $("#courseDiv").html("");// 先清空原来加载的数据
            showcourseList(1,10,1);
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

    // 展示优质课程列表
    function showcourseList(pageNum,pageSize,allIscrollState){
        $.ajax({
            async: false,
            type: "POST",
            url: $("#basePath").val()+"wap/study/getCourPage",
            data:{pageNum:pageNum,pageSize:pageSize},
            dataType: "json",
            success: function (data) {
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length>0){
                for (var i =0; i<a.length; i++){
                    htmlVal+= '<a href='+$("#basePath").val()+'wap/course/courseDetail?courseType='+a[i].tyval+'&courseId='+a[i].courseId+'><div class="con3Item">'
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
                        htmlVal+='<a><b></b></a>';
                    }else {
                        htmlVal+='<a><b>'+check_(a[i].costPrice)+'</b></a>';
                    }
                        htmlVal+='</div>'
                        +'</div>'
                        +'</div>'
                        +'</div></a>';
                }
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
});
function check_(v) {
    if(v == 0){
        return "免费";
    }else{
        return "￥"+v;
    }
}