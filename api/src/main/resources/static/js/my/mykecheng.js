$(function () {
    var con3Select = $(".con3 .con3Select");
    var con2Select = $(".con2 .con2Select");
    var page = 1;
    con2Select.click(function () {
        page=$(this).index()+1;
        showWzkcList(1,6,page,1);
        con2Select.removeClass("active");
        $(this).addClass("active");
        con3Select.hide();
        con3Select.eq($(this).index()).show();
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

    //上拉加载
    function pullUpAction(){
        setTimeout(function(){
            curpage++;
            showWzkcList(curpage,6,page,0);
        }, 1000)
    }

    //下拉刷新
    function pullDownAction(){
        setTimeout(function(){
            curpage = 1;
            // $("#flag1").html("");// 先清空原来加载的数据
            showWzkcList(1,6,page,1);
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
    // <div class="class">
    //     <img class="classLeft" src="image/miss1.png"/><div class="classRight">如何练就大海的胸怀</div>
    //     </div>



    // 展示文字课程列表
    showWzkcList(1,6,page,1);
    function showWzkcList(pageNum,pageSize,flag,allIscrollState){
        var flagX="flag"+flag;
        if(allIscrollState===1){
            $("#"+flagX).html("");
        }
        $.ajax({
            async: true,
            type: "POST",
            url: $("#basePath").val() + "wap/myteam/myCourseList",
            data: {pageNum:pageNum,pageSize:pageSize,flag:flag},
            dataType: "json",
            success: function (data) {
                if(data != null && data.length > 0){
                    var htmlVal='';
                    for(var i = 0;i < data.length;i++){
                        if ($("#userType").val() == 1){//为我发布的课程
                            htmlVal+='<div class="class" style="background-color: white"><a href="'+$("#basePath").val()+'wap/course/courseDetail?courseType='+data[i].tyval+'&courseId='+data[i].courseId+'">'
                                +'<img class="classLeft" src="'+data[i].courseImg+'"/>'
                                +'<div class="classRight" style="width:3.8rem;height:2rem;line-height: 0.4rem;">'
                                +'<div class="classRName">'
                                +'<div><span style="display: inline-block;width: 3rem;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">'+data[i].courseTitle+'</span>'
                                +'</div>'
                                +'<div style="height: 0.6rem;"></div>'
                                +'</div>'
                                +'<div class="classRSay">'
                                +'<div>发布人：'+data[i].tname+'</div>'
                                +'<div>'+new Date(data[i].createTime).pattern("yyyy-MM-dd")+'</div>'
                                +'</div>'
                                +'</div>'
                                +'</div>';
                        }else {//购买的课程
                            htmlVal+='<div class="class" style="background-color: white"><a href="'+$("#basePath").val()+'wap/course/courseDetail?courseType='+data[i].tyval+'&courseId='+data[i].courseId+'">'
                                +'<img class="classLeft" src="'+data[i].courseImg+'"/>'
                                +'<div class="classRight" style="width:3.8rem;height:2rem;line-height: 0.4rem;">'
                                +'<div class="classRName">'
                                +'<div><span style="display: inline-block;width: 3rem;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">'+data[i].courseTitle+'</span>'
                                +'<span style="color:#ffa42f;float: right;font-size: 0.24rem;">'+checkCourseFinsh(data[i].viewFinishStatus)+'</span>'
                                +'</div>'
                                +'<div style="height: 0.6rem;"></div>'
                                +'</div>'
                                +'<div class="classRSay">'
                                +'<div>发布人：'+data[i].tname+'</div>'
                                +'<div>'+new Date(data[i].createTime).pattern("yyyy-MM-dd")+'</div>'
                                +'</div>'
                                +'</div>'
                                +'</div>';
                        }
                    }
                    $("#"+flagX).append(htmlVal);
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
                console.log("bbb")
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

    function checkCourseFinsh(courseFinsh) {

        if (courseFinsh == 1){
            return "已完成";
        }else {
            return "继续学习";
        }
    }

})