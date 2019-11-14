<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/courseList.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
</head>
<body>
    <div class="search" style="position: absolute;z-index: 10;top: 0;background-color: #f5f5f5;">
        <div class="searchBox">
            <input type="text" id="keyword"/>
            <span style="cursor: pointer" id="searchBox">搜索</span>
        </div>
        <div class="allSort">
            <span class="sort active" id="allGoods">综合</span>
            <span class="sort" id="saleNumber">销量</span>
            <span class="sort priceSort">价格<img src="${ctx}image/sort_price.png"></span>
        </div>
    </div>
    <div id="wrapper" style="z-index: 1;top:1.7rem;">
        <div class="scroller">
            <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
            <div class="con3"></div>
            <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
        </div>
    </div>
</body>
<script>
    $(function () {
        var freshH=$(".fresh").height();
        var moreState=0;
        var freshState=0;
        var myscroll = new iScroll("wrapper",{//不要动
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
                //////////////////////////////////////////////////////////////////////fresh-more////////////////////////////////////////////////////////////////////////////////////
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
                ////////////////////////////////////////////////////////////////////////fresh-more//////////////////////////////////////////////////////////////////////////////////
                if (moreState===1) {
                    moreState=2;
                    moreElement();
                    pullUpAction();
                }
            },
            onRefresh:function(){}
        });
        var curpage=2;
        var sort=$(".allSort .sort");
        var priceSortState=0;
        var index=0;
        var pageSize=20;
        $(".priceSort").click(function () {
            if(priceSortState===0){
                $(this).children("img").attr("src","${ctx}image/sort_price_down.png");priceSortState=1;index=3
            }else{
                $(this).children("img").attr("src","${ctx}image/sort_price_up.png");priceSortState=0;index=2
            }
        })
        sort.click(function () {
            if($(this).index()===0||$(this).index()===1){
                $(".priceSort").children("img").attr("src","${ctx}image/sort_price.png");
                index = $(this).index();
            }
            sort.removeClass("active");
            $(this).addClass("active");
            $(".con3").html("");
            myscroll.y=0;myscroll.refresh();
            addData(1,pageSize,index,$("#keyword").val(),1)/////////////////////////////排序种类  0 1 2 3 //////////////
        })
        ////////////////////////////////////////////加载数据
        addData(1,pageSize,index,$("#keyword").val(),1);////////////////////////////初始化=加载第一页////////////////////
        function pullUpAction(){
            setTimeout(function(){
                addData(curpage,pageSize,index,$("#keyword").val(),0);////////////////////加载下一页//////////////
            }, 1000)
        }
        function pullDownAction(){
            setTimeout(function(){
                $.ajax({
                    url:"${ctx}wap/goods/detailsByCatId",
                    dataType:'json',
                    data:{"catId": '${catId}'},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        console.log(data.length);
                        if (data.length >=10){
                            addData(1,10,index,$("#keyword").val(),1);
                        }else {
                            return;
                            //addData(1,data.length,index,$("#keyword").val(),1);
                        }
                    }
                })
                //addData(1,pageSize,index,$("#keyword").val(),1);////////////////////刷新第一页///////////////
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
                    $('.more  span').text('加载成功');
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

        /////////////////////搜索/////////////
        $("#searchBox").click(function () {
            addData(1,pageSize,index,$("#keyword").val(),1)
        })
        function addData(pageNum,pageSize,sequenc,keyword,allIscrollState) {
            console.log(pageNum+" "+pageSize+" "+sequenc+" "+keyword+" "+allIscrollState);
            $.ajax({
                url:"${ctx}wap/goods/list",
                type:'get',
                dataType:'json',
                data:{"pageNum":pageNum, "pageSize": pageSize, "catId": '${catId}', "sequenc": sequenc,"keyword":keyword},
                success:function(data){
                    if(data.length>0) {
                        //刷新开始,下拉刷新，请求默认数据
                        var html = "";
                        for (var i = 0; i < data.length; i++) {
                            html += '<a href="${ctx}goods/detail?goodsId=' + data[i].goodsId + '">' +
                                    '<div class="con3Item">' +
                                    '<img class="con3ItemLeft" src="' + data[i].mastImg + '">' +
                                    '<div class="con3ItemRight">' +
                                    '<span class="con3ItemTitle">' + data[i].goodsName + '</span>' +
                                    '<div class="con3ItemBody">' +
                                    '<div class="con3ItemTime con3ItemTimeMan">' +
                                    '<p>发布人：艾克</p>' +
                                    '<p>3月24日 10:00 发布</p>' +
                                    '</div>' +
                                    '<div class="con3ItemTime con3ItemTimeMoney" style="text-align:right;">' +
                                    '<a><b>免费</b></a>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</a>';
                        }
                        $(".con3").append(html);
                        /////////////////////下边的代码不要动/////////////////////
                        if($("#wrapper").height()>$(".scroller").height()){
                            $(".more").hide();
                        }else{
                            $(".more").show();
                        }
                        if(allIscrollState){
                            freshState = 3;
                            freshElement();
                        }else{
                            curpage++;
                            moreState=3;
                            moreElement();
                            myscroll.refresh();
                        }
                        setTimeout(function () {
                            myscroll.refresh();
                        }, 1000)
                    }else{
                        $('.more .pull_icon').removeClass('flip loading');
                        $('.more span').text('没有更多数据了');
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
            })
        }
    })
</script>
</html>