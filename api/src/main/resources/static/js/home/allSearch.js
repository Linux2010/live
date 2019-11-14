var curpage=1;
var freshH;
var moreState=0;
var freshState=0;
var userIdentity =$("#userIdentity").val();
$(function () {
        freshH=$(".fresh").height();
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
        searchTeachers(1,10,1, $(".search .searchBox input").val());

        var searchTypeList = $(".search .searchTypeList");
        var searchTypeLi = $(".search .searchTypeList li");
        var searchTypeName = $(".search .searchTypeName");
        var searchTypeBth = $(".search .searchTypeBtn");
        searchTypeBth.click(function () {
            searchTypeList.toggle();
        })
        searchTypeLi.click(function () {
            var html = $(this).children("span").html();
            searchTypeName.html(html);
            searchTypeList.hide();
            var keyword = $(".search .searchBox input").val();
            curpage =1;
            if(html =='教头'){
                searchTeachers(curpage,10,1,keyword);
            }else if (html =='课程'){
                searchCourses(curpage,10,1,keyword);
            }else if (html =='商品'){

            }
        });
        $(".searchBtn").click(function () {
            var searchName = $(".search .searchTypeName").html();
            var keyword = $(".search .searchBox input").val();
            curpage =1;
            if(searchName =='教头'){
                searchTeachers(curpage,10,1,keyword);
            }else if (searchName =='课程'){
                searchCourses(curpage,10,1,keyword);
            }else if (searchName =='商品'){

            }
        });




    /**
     * 查询教头列表
     * @param keyword
     */
    function  searchTeachers(pageNum,pageSize,allIscrollState,keyword) {
        if(pageNum == 1){
            $("#contentDiv").html("");
        }
        $.ajax({
            async: true,
            url: base+"search/home/searchTeachers",
            data: {pageNum:pageNum,pageSize:pageSize,"keyword": keyword},
            type:"post",
            dataType: "json",
            success: function (data) {
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length > 0){
                    for (var i =0; i<a.length; i++){
                        var signature =a[i].signature?a[i].signature:"";
                        var name =a[i].realName?a[i].realName:a[i].loginName;
                        htmlVal+= '<a href="'+base+"search/wellTeacherHome?teacherId="+a[i].userId+'">'
                            +'<div class="con3Item">'
                            +'<img class="con3ItemLeft teacherPhoto" style="border-radius: 50%;" src="'+a[i].photo+'">'
                            +'<div class="con3ItemRight" style="width:5.8rem;padding:0.2rem;">'
                            +'<span class="con3ItemTitle" style="height: 0.5rem;line-height: 0.5rem;">讲师：'+name+'</span>'
                            +'<div class="con3ItemBody">'
                            +' <div class="con3ItemTime con3ItemTimeMan" style="width:100%">'
                            +'  <p style="width:100%">'+signature+'</p>'
                            +' </div>'
                            +'</div>'
                            +'</div>'
                            +'</div>'
                            +' </a>';
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
                    }, 1000);
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
     * 查询课程列表
     * @param keyword
     */
    function  searchCourses(pageNum,pageSize,allIscrollState,keyword) {
        if(pageNum == 1){
            $("#contentDiv").html("");
        }
        $.ajax({
            async: true,
            url: base+"search/home/searchCourses",
            data: {pageNum:pageNum,pageSize:pageSize,"keyword": keyword},
            type:"post",
            dataType: "json",
            success: function (data) {
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length > 0){
                    for (var i =0; i<a.length; i++){
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
                        var detailUrl =base+"course/courseDetail?courseType="+a[i].tyval+"&courseId="+a[i].courseId;
                        htmlVal+= '<a  href="'+detailUrl+'">'
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
                    $("#contentDiv").append(htmlVal);
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
                        // moreElement();
                        $('.more .pull_icon').removeClass('flip loading');
                        $('.more span').text('没有更多数据');
                    }
                    setTimeout(function () {
                        myscroll.refresh();
                    }, 1000);
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

    function pullUpAction(){
        setTimeout(function(){
            curpage++;
            var searchTypeName = $(".search .searchTypeName").html();
            if(searchTypeName =='教头'){
                searchTeachers(curpage,10,0, $(".search .searchBox input").val());
            }else if (searchTypeName =='课程'){
                searchCourses(curpage,10,0, $(".search .searchBox input").val());

            }else if (searchTypeName =='商品'){

            }
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            var searchTypeName = $(".search .searchTypeName").html();
            curpage =1;
            if(searchTypeName =='教头'){
                searchTeachers(curpage,10,0, $(".search .searchBox input").val());
            }else if (searchTypeName =='课程'){
                searchCourses(curpage,10,0, $(".search .searchBox input").val());

            }else if (searchTypeName =='商品'){

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

    });