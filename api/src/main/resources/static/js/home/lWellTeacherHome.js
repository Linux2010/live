
$(function () {
    $("#wrapper1").height(window.innerHeight-$(".con9").height());
    var teacher = teacherDetail();//教头详情
    $(".more > a").attr("href",teacher.teacherIntroUrl); //简介富文本链接
    var allPage = 6;
    var curpage0=1;
    var curpage1=1;
    var curpage2=1;
    var freshH=$(".fresh").height();
    var moreState=0;
    var freshState=0;
    var myscroll = new iScroll("wrapper1",{
        topOffset:freshH,
        onScrollMove:function(){
            if(this.y >=-freshH){
                //console.log(this.y)
                if(this.y>=0){
                    this.minScrollY=0;
                    freshState=1;
                    freshElement();
                }else if(0>this.y>-freshH){
                    freshState=0;
                    freshElement();
                }else{
                   // console.log("没这种情况")
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
                console.log("end1")
                freshState=2;
                pullDownAction();
                freshElement();
            }else if (moreState===1) {//////////////////////fresh-mor
                console.log("end2")
                moreState=2;
                moreElement();
                pullUpAction();
            }else{
                this.refresh();
            }
            //////////////////////////////////////////////////

        },
        onRefresh:function(){
        }
    });





    var v1 = new Vue({
        el:'.v1',
        data:{
            pic:teacher.photo,          //用户头像
            name:teacher.userName,      //用户名称
            say:teacher.signature,      //个性签名
            level:teacher.userAwards,   //教头等级奖项
            present:teacher.userIntroText,  //简介
            interest:teacher.isFocus,      //是否关注
            reward:0,
            shangjin:$(".mask1 .mask1Box .line1 input").val(), //赏金
            select1:0,
            select2:0,
        },
        methods:{
            interesting:function () {
                if(!this.interest){
                    this.interest = 1;
                }else {
                    this.interest = 0;
                }
                focusOrCancelFocusTeahcer(this.interest); //关注或取消关注教头
            },//关注
            rewarding:function (a) {
                if(!this.reward){
                    this.reward = 1;
                }else {
                    this.reward = 0;
                }
                if(a){
                    if(this.shangjin!==''){
                        if(!isMoney(this.shangjin) || this.shangjin <=0){
                            alert("请输入正确的金额");
                            this.shangjin='';
                            return false;
                        }else{

                            //跳转到支付页面
                            location.href=base+ "search/teahcerReward/payOrder?&teacherId="+$("#teacherId").val()+"&money="+this.shangjin;
                        }

                    }else{
                        alert("请输入打赏金额。")
                        this.reward=1;
                    }
                }
            },//打赏
            select1ing:function (b) {
                this.select1=b;
                if(b==2){

                    Vue.nextTick(function () {
                        $("#comments").show();
                        questionAndReplyList(1,allPage,$("#teacherId").val(),1,b);
                        myscroll.scrollTo(-($(".scroller").height()-$(".v1").height()),0);
                        myscroll.refresh();
                    });
                }else{

                    Vue.nextTick(function () {
                        $("#comments").hide();
                        courseRelaseDynamicList(1,allPage,b,$("#teacherId").val(),1);
                        myscroll.scrollTo(-($(".scroller").height()-$(".v1").height()),0);
                        myscroll.refresh();
                    });
                }
            },
            select2ing:function (c) {
                this.select2=c;
            }
        }
    });

    var isMoney = function isMoney(money){
        var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        //var money = "520.100";
        //000 错
        //0 对
        //0. 错
        //0.0 对
        //050 错
        //00050.12错
        //70.1 对
        //70.11 对
        //70.111错
        //500 正确
        if (reg.test(money)) {
            return true;
        }else{
            return false;
        }
    }

    // //讲师简介图片列表
    v1.$nextTick(function () {
        var teacherIntroImgs = teacher.teacherIntroImgs;
        if(teacherIntroImgs){
            $.each(teacherIntroImgs,function (i,img) {
                $(".images #wrapper ul").append('<li class=""><a href="'+img+'"><img src="'+img+'"> </a></li>');
            });
        }
        $("#scroller").width($(".category_nav").width()+"px");
        var myscroll1 = new iScroll("wrapper",function () {

        })
    });
    //上拉加载
    function pullUpAction(){
        setTimeout(function(){
            if(v1.select1===0){
                curpage0++;
                courseRelaseDynamicList(curpage0,allPage,v1.select1,$("#teacherId").val(),0);
            }else if(v1.select1===1){
                curpage1++;
                courseRelaseDynamicList(curpage1,allPage,v1.select1,$("#teacherId").val(),0);
            }else {
                curpage2++;
                questionAndReplyList(curpage2,allPage,$("#teacherId").val(),0,this.select1)
            }
        }, 1000)
    }

    //下拉刷新
    function pullDownAction(){
        setTimeout(function(){
            if(v1.select1==2){
                questionAndReplyList(1,allPage,$("#teacherId").val(),1,this.select1)
            }else{
                courseRelaseDynamicList(1,allPage,v1.select1,$("#teacherId").val(),1);
            }
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

    // 教头动态
    courseRelaseDynamicList(1,allPage,v1.select1,$("#teacherId").val(),1);
    /**
     *  教头动态与作品
     * @param pageNum
     * @param pageSize
     * @param allIscrollState
     * @param type  查询动态还是作品，0：动态；1：作品
     * @returns {*}
     */
    function courseRelaseDynamicList(pageNum,pageSize,flag,teacherId,allIscrollState){
        var flagX="flag"+flag;
        console.log( $("#"+flagX).html());
        if(allIscrollState===1){
            $("#"+flagX).html("");
        }
        $.ajax({
            async: true,
            url: base+"search/courseRelaseDynamicList",
            data: {pageNum:pageNum,pageSize:pageSize,type: flag,teacherId:teacherId},
            type:"post",
            dataType: "json",
            success: function (data) {
              //  console.log(data)
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length > 0){
                    for (var i =0; i<a.length; i++){

                        var userIdentity = $("#userIdentity").val();
                        var courseFbTime;//课程发布时间
                        if(a[i].createTime){
                            courseFbTime  = new Date(a[i].createTime).pattern("yyyy-MM-dd HH:mm:ss")
                        }
                        var price;
                        if(userIdentity == 2) { //用户身份1:普通用户2：会员用户
                            price='';
                        }else if(userIdentity == 1){
                            if(a[i].costPrice == null  || a[i].costPrice == 0) {
                                price = '免费';
                            }else{
                                price = a[i].costPrice;
                                price ="¥&nbsp;"+price;
                            }
                        }else{
                            if(a[i].costPrice == null  || a[i].costPrice == 0) {
                                price = '免费';
                            }else{
                                price = a[i].costPrice;
                                price ="¥&nbsp;"+price;
                            }
                        }
                        var courseIntro ="";
                        if(a[i].courseIntro){
                            courseIntro =a[i].courseIntro;
                        }
                        var detailUrl =base+"course/courseDetail?courseType="+a[i].tyval+"&courseId="+a[i].courseId;
                        if(flag===0){
                            htmlVal+= '<a href="'+detailUrl+'">'
                                +'<div class="sele0Con" onclick="javascript:location.href='+detailUrl+'">'
                                +'<div class="usr"><img src="'+a[i].courseImg+'"></div>'
                                +'<div class="usrR">'
                                +'<p class="line1"><span>'+a[i].tname+'</span>&ensp;<span>预发布课程</span>&ensp;<span style="color:#ffa42f">'+a[i].courseTitle+'</span></p>'
                                +'<div class="line2">'
                                +'<div class="line2Con">'+courseIntro+'</div>'
                                +'<div class="line2Time"><span>'+new Date(a[i].courseFbTime).pattern("yyyy-MM-dd HH:mm:ss")+'</span><span style="float: right">'+a[i].orderNumber+'人预约</span></div>'
                                +'</div>'
                                // +'<div class="time">9小时前</div>'
                                // +'</div>'
                                +'</div></a>';
                        }else{
                            htmlVal+= '<a href="'+detailUrl+'">'
                                +'<div class="con3Item">'
                                +'<img class="con3ItemLeft" src="'+a[i].courseImg+'">'
                                +'<div class="con3ItemRight">'
                                +'<span class="con3ItemTitle">'+a[i].courseTitle+'</span>'
                                +'<div class="con3ItemBody">'
                                + '<p>主讲教头：'+a[i].tname+'</p>'
                                +' </div>'
                                +'<div class="con3ItemBody">'
                                +' <div class="con3ItemTime con3ItemTimeMoney ">'
                                +'  <p style="color: #ffa42f">'+price+'</p>'
                                +' </div>'
                                +' <div class="con3ItemTime con3ItemTimeMan" style="text-align:right;">'
                                +'<b>'+courseFbTime+'</b>'
                                +'</div>'
                                +' </div>'
                                +'  </div>'
                                +'</div>'
                                +' </a>';
                        }
                    }
                    $("#"+flagX).append(htmlVal);
                    if($(".searchs").height() > $(".scroller").height()){
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

    /**
     * 教师详情-问答-用户与教头的问答列表
     * @param pageNum
     * @param pageSize
     * @param teacherId
     * @param allIscrollState
     * @return
     */
    function questionAndReplyList(pageNum,pageSize,teacherId,allIscrollState,flag) {
        var flagX="flag"+flag;
        if(allIscrollState===1){
            $("#"+flagX).html("");
        }
        $.ajax({
            async: true,
            url: base+"search/questionAndReplyList",
            data: {pageNum:pageNum,pageSize:pageSize,teacherId: teacherId},
            type:"post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message == 'IsAjax') {
                    window.location.href=base+"wx/login"
                    return false;
                }
                //console.log(data)
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length > 0){
                    for (var i =0; i<a.length; i++) {
                        //我的提问
                        htmlVal += ' <div class="line2">'
                            + ' <div class="line2Say">'
                            + ' <span class="say">' + a[i].question + '</span>'
                            + '<div style="position:absolute;width:0.7rem;right:-1.1rem;top:0.15rem;border-left:0.2rem solid #d8952e;border-right:0.2rem solid transparent;border-top:0.2rem solid transparent;border-bottom:0.2rem solid transparent;height: 0;"></div>'
                            + ' </div>'
                            + '<img class="line2Pic" src="' + a[i].photo + '">'
                            + '</div>';

                        //教头回复
                        var replyList = a[i].replyList;
                        if(replyList && replyList.length >0){
                            for (var j = 0; j < replyList.length; j++) {

                                htmlVal += ' <div class="line1">'
                                    + '<img class="line1Pic" src="' + replyList[j].photo + '">'
                                    + '<div class="line1Say">'
                                    + '<span class="say">' + replyList[j].reply + '</span>'
                                    + '<div style="position:absolute;width:0.7rem;left:-1.1rem;top:0.15rem;border-right:0.2rem solid #d8952e;border-left:0.2rem solid transparent;border-top:0.2rem solid transparent;border-bottom:0.2rem solid transparent;height: 0;"></div>'
                                    + ' </div>'
                                    + ' </div>';
                            }
                        }
                    }
                    $("#"+flagX).append(htmlVal);
                    if($(".searchs").height() > $(".scroller").height()){
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


    /**
     * 教头详情
     * @returns {*}
     */
    function  teacherDetail(){
        var teacher = null;
        $.ajax({
            async: false,
            type: "POST",
            url: base + "search/teacherDetail",
            data: {teacherId: $("#teacherId").val(),},
            dataType: "json",
            success: function (data) {
                teacher = data.data;
            },
            error: function () {
                teacher =data.data;
            }
        });
        return teacher;
    }


    /**
     * 关注教头
     * @returns {*}
     */
    function  focusOrCancelFocusTeahcer(isFocus){
        $.ajax({
            async: false,
            type: "POST",
            url: base + "search/focusOrCancelFocusTeahcer",
            data: {teacherId: $("#teacherId").val(),isFocus:isFocus}, //是否关注0：取消关注；1:关注
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message == 'IsAjax') {
                    window.location.href=base+"wx/login"
                    return false;
                }
                if(data.result==1 && data.message=="success") {
                    location.reload();
                }else{
                    alert(data.message);
                }
            },
            error: function () {
                alert(data.message);
            }
        });
    }



    /**
     * 教师详情-问答-会员提问
     * @param teacherId
     * @param question 问题（100字以内）
     * @return
     */
    function userAnswerTeacherQuestion( teacherId,  question){
        $(".writeBox4").val("");
        $.ajax({
            async: true,
            url: base + "search/userAnswerTeacherQuestion",
            data: {question: question,teacherId: teacherId},
            type: "post",
            dataType: "json",
            success: function (data) {
                window.removeEventListener("click",windowClick,false);
                if(data.result == 1 && data.message=="success"){
                    Vue.nextTick(function () {
                        // 教头问答
                        questionAndReplyList(1,allPage,$("#teacherId").val(),1,2);
                        myscroll.scrollTo(-($(".scroller").height()-$(".v1").height()),0);
                        myscroll.refresh();
                    });
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
            alert("提问问题不能为空！");
            return false;
        }
        if($(".writeBox4").val().length >100){
            alert("提问问题100字以内哦！");
            return false;
        }
        b.hide();
        f.show();
        userAnswerTeacherQuestion( $("#teacherId").val(),$(".writeBox4").val());
    });

});