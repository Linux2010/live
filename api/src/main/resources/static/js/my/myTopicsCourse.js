$(function () {
    var userIdentity =$("#userIdentity").val();
    var con3Select = $(".con3 .con3Select");
    var con2Select = $(".con2 .con2Select");
    var page = 0;

    var con2 = $(".con2 .con2Select ");
    var con3Select = $(".con3 .con3Select");
    con3Select.click(function () {
        page=$(this).index();
        myTopicsCourseList( 1,10,page,1) //默认查询我的关注
        con3Select.removeClass("active");
        $(this).addClass("active");
        con2.hide();
        con2.eq($(this).index()).show();
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
            myTopicsCourseList( curpage,10, page,0)
        }, 1000);
    }

    //下拉刷新
    function pullDownAction(){
        setTimeout(function(){
            curpage =1;
            var con2 = $(".con2 .con2Select ");
            con2.html("");// 先清空原来加载的数据
            myTopicsCourseList( curpage,10, page,0)
        }, 1000);
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

    myTopicsCourseList( 1,10, page,1)
    /**
     * 我的答题-我的课程列表
     * @param pageNum
     * @param pageSize
     * @param type 课程类别0：我做过考题，1：我没做过考题
     * @param  allIscrollState
     * @return
     */
    function  myTopicsCourseList( pageNum,  pageSize,  flag,allIscrollState){
        var flagX="flag"+flag;
        if(allIscrollState===1 ||  pageNum ==1){
            $("#"+flagX).html("");
        }
        $.ajax({
            async: true,
            type: "POST",
            url:  base+"topic/myTopicsCourseList",
            data: {pageNum:pageNum,pageSize:pageSize,type:flag},
            dataType: "json",
            success: function (data) {
                console.log(data)
                var a  = data.data;
                if(a != null && a.length > 0){
                    var htmlVal='';
                    for(var i = 0;i < a.length;i++){
                        var courseFbTime;//课程发布时间
                        if(a[i].courseBeginTime){
                            courseFbTime  = new Date(a[i].courseBeginTime).pattern("yyyy-MM-dd HH:mm:ss")
                        }
                        var price;
                        if(userIdentity == 2) { //用户身份1:普通用户2：会员用户
                            price = a[i].vipPrice;
                        }else if(userIdentity == 1){
                            price = a[i].costPrice;
                        }else if(a[i].vipPrice == null || a[i].costPrice == null ||a[i].vipPrice == 0  || a[i].costPrice == 0){
                            price ='免费';
                        }else{
                            price = a[i].costPrice;
                        }
                        htmlVal+='<a href="'+base+"topic/my/myTopic?type="+flag+"&courseTopicExaminationId="+a[i].courseTopicExaminationId+'"><div class="con2">'
                            +'<div class="con2Item">'
                            +'<img class="con2ItemLeft" src="'+a[i].courseCover+'">'
                            +'<div class="con2ItemRight">'
                            +' <span class="con2ItemTitle">'+a[i].courseTitle+'</span>'
                            +' <div class="con2ItemBody">'
                            +'<div class="con2ItemTime">'
                            +'<p>讲师：'+a[i].realName+'</p>'
                            +'<p>'+courseFbTime+'开始</p>'
                            +' </div>'
                            +'<a href="'+base+"topic/my/myTopic?type="+flag+"&courseTopicExaminationId="+a[i].courseTopicExaminationId+'"><span>查看</span></a>'
                            +'</div>'
                            +' </div>'
                            +'</div>'
                            +' </div></a>';
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


})