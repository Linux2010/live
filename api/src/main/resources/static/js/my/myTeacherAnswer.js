$(function () {
    var con3Select = $(".con3 .con3Select");
    var con2Select = $(".con2 .con2Select");
    var page = 0;
    con2Select.click(function () {
        page=$(this).index();
        questionOrReplyList(1,10, page,1);
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
            questionOrReplyList(curpage,10,page,0) //默认查询我的提问
        }, 1000)
    }

    //下拉刷新
    function pullDownAction(){
        setTimeout(function(){
            curpage=1;
            questionOrReplyList(curpage,10, page,1) //默认查询我的提问
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


    // 展示我的提问列表
    questionOrReplyList( curpage,10, page,1)
    function questionOrReplyList(pageNum,pageSize,flag,allIscrollState){
        var flagX="flag"+flag;
        if(allIscrollState===1 || pageNum == 1){
            $("#"+flagX).html("");
        }
        $.ajax({
            async: true,
            type: "POST",
            url: base + "search/questionOrReplyList",
            data: {pageNum:pageNum,pageSize:pageSize,type:flag},
            dataType: "json",
            success: function (data) {
                console.log(data)
                var a = data.data;
                if(a != null && a.length > 0){
                    var htmlVal='';
                    for(var i = 0;i < a.length;i++){
                        if(a[i].questionTime){
                            questionTime  = new Date(a[i].questionTime).pattern("yyyy-MM-dd HH:mm:ss")
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
                        var name ="";
                        if(a[i].realName){
                            name= a[i].realName;
                        }else{
                            if(a[i].userName){
                                name= a[i].userName;
                            }
                        }
                        var signature="";
                        if(a[i].signature){
                            signature = a[i].signature;
                        }
                        var str="";
                        if(flag==1){

                            str = '  <a href="#">回复</a>'
                        }
                        var border="";
                        var replyList = a[i].replyList;
                        if(!replyList ||replyList.length ==0){
                            border="border:0";
                        }
                         htmlVal +='<a><div class="lLine" userTeacherQuestionId="'+a[i].userTeacherQuestionId+'" teacherId="'+a[i].teacherId+'">'
                             +'<div class="lLineBox" style="'+border+'" >'
                             +'<img class="lImg" src="'+a[i].photo+'">'
                             +'<div class="lImgR1">'
                             +' <div class="lName">'+name+'</div>'
                             +' <div class="lSay">'+signature+'</div>'
                             +' <div class="lSay">'+a[i].question+'</div>'
                             +'</div>'
                             +' <div class="lImgR2">'
                             +'<div class="lTime">'+questionTime+'</div>'
                             +' <div class="lReport"></div>'
                             +'</div>'
                             +'</div></a>';
                        //教头回复
                        if(replyList && replyList.length >0){
                            for (var j = 0; j < replyList.length; j++) {
                                htmlVal+='<div class="huifu">' + replyList[j].userName + ':<span style="color:black:">' + replyList[j].reply + '</span></div>';
                            }
                        }
                        htmlVal+= '</div>';
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

    function reply(){
        $("#comments").show();
    }
    var userTeacherQuestionId="";
    $("#flag1").on("click",".lLine",function(){
        $("#comments").show();
        userTeacherQuestionId =$(this).attr("userTeacherQuestionId");
    });



    /**
     * 消息列表-教头答复
     * @param teacherId
     * @param reply 问题（100字以内）
     * @param userTeacherQuestionId 用户提问教头表业务ID
     * @return
     */
    function teacherReplyUserQuestion(  teacherId ,  reply, userTeacherQuestionId){
        $("#comments").hide();
        $.ajax({
            async: true,
            url: base + "search/teacherReplyUserQuestion",
            data: {userTeacherQuestionId: userTeacherQuestionId,teacherId: teacherId, reply: reply},
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    $(".writeBox4").val("");
                    // 我的答复
                    questionOrReplyList( curpage,10, page,1)
                }else{
                    alert("请求失败："+data.message);
                }
            },
            error: function (data) {
                alert("请求异常："+data.message);
            },
        });
    }

    var f = $(".f");
    var b = $(".b");
    f.click(function () {
        $(this).hide();
        b.show();
        $(".writeBox4").focus();
        bottomH = $(".write").height();
        window.addEventListener("click",windowClick,false);
    });
    function windowClick(e) {
        if(e.clientY<($("body").height()-bottomH)){
            f.show();b.hide();
        }else{
        }
    }

    $("#addComm").click(function () {
        if(!$(".writeBox4").val()){
            alert("回复内容不能为空！");
            return false;
        }
        if($(".writeBox4").val().length >100){
            alert("回复内容100字以内哦！");
            return false;
        }
        b.hide();
        f.show();
        teacherReplyUserQuestion($("#flag1 .lLine").attr("teacherId") , $(".writeBox4").val(), userTeacherQuestionId);
    });

});