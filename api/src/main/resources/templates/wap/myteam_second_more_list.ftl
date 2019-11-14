<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/myTeam.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/iscroll.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/myteam/?userId=${userId}"><</a></span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">我的团队</span>
</div>
<div class="con3" style="padding:0;">
    <#--<div class="con3Tit" style="top: 0;z-index: 2;width: 100%;background-color: white;left: 0;">-->
        <#--<span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;margin-left: 20%;color:#ffa42f;font-size: 0.34rem;">二级用户</span>-->
    <#--</div>-->
    <div style="width:100%;height: 0.0rem;"></div>
    <div id="wrapper">
        <div class="scroller">
            <div class="fresh"><i class="pull_icon"></i><span></span></div>
            <erjiuser v-for="data in datas['0']" v-bind:todo="data"></erjiuser>
            <div v-for="a in datas.length"><erjiuser v-for="item in datas[a]" v-bind:todo="item"></erjiuser></div>
            <div class="more" style="display: none"><i class="pull_icon"></i><span></span></div>
        </div>
    </div>
</div>
</body>
<script>

</script>
<script>
    var v1 = new Vue({
        el:'.con3',
        data:{
            datas:[]
        },
        components: {
            'erjiuser':{
                props: ['todo'],
                template: ''+
                '<div class="con3Con">'+
                '<div v-for="d in todo"><img v-bind:src="d.photo" style="box-sizing: border-box;border: 0.02rem solid #999"><img src="${ctx}image/v.png" v-if="d.userIdentity===2" style="margin-left: -0.3rem; margin-top: -0.25rem;vertical-align: middle;width: 0.4rem;height: 0.4rem;border:0"/><p>{{d.userName}}</p></div>'+
                '</div>'
            }
        },
        mounted:function(){

        },
        methods:{

        }
    })
    function sliceArray(array, size) {
        var result = [];
        for (var x = 0; x < Math.ceil(array.length / size); x++) {
            var start = x * size;
            var end = start + size;
            result.push(array.slice(start, end));
        }
        return result;
    }
    $(function () {
    var freshH=$(".fresh").height();
    var moreState=0;
    var freshState=0;
    var datas=[];
    var page=1;
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
        onRefresh:function(){
//            moreState=0;
//            freshState=0;
//            this.minScrollY=-freshH;
//            moreElement();
//            freshElement();
        }

    });
    /////////////////////////////////////////////////////////////////////////////////getData(x)////////////////////////////////////////////////////////////////////////////////////////////////////////
    function getDataMore(x) {
        $.ajax({
            async: false,
            url: base+"/myteam/get_second_more_list",
            data:{'pageNum':x},
            dataType: "json",
            type:"POST",
            success: function (data) {
                if(data.data.list.length===0){
                    moreState=4;
                    moreElement();
                }else{
                    datas.push(sliceArray(data.data.list,3));
                    v1.datas=datas;
                    v1.$nextTick(function () {
                        if($(".scroller").height()>=$("#wrapper").height()){$(".more").css("display","flex");}
                        myscroll.refresh();
                    })
                }
            },
            error: function (data) {
                console.log("系统异常,请稍后再试!");
                moreState=5;
                moreElement();
            }
        });
    }
    getDataMore(1);
    ///////////////////////////////////////////////////////////////////////////////////pullUpAction()/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    function pullUpAction(){
        setTimeout(function(){
            page++;
            getDataMore(page);

        }, 1000)
    }
    function pullDownAction(){
        setTimeout(function(){
            getDataFresh(1);
            function getDataFresh(x) {
                $.ajax({
                    async: false,
                    url: base+"/myteam/get_second_more_list",
                    data:{'pageNum':x},
                    dataType: "json",
                    type:"POST",
                    success: function (data) {
                        if(data.data.list.length===0){
                            freshState=4;
                            freshElement();
                        }else{
                            datas["0"]=sliceArray(data.data.list,3);
                            v1.datas=datas;
                            v1.$nextTick(function () {
                                if($(".scroller").height()>=$("#wrapper").height()){$(".more").css("display","flex");}
                                freshState=3;
                                freshElement();
                                setTimeout(function () {
                                    myscroll.refresh();
                                },500)
                            })
                        }
                    },
                    error: function (data) {
                        console.log("系统异常,请稍后再试!");
                        freshState=4;
                        freshElement();
                    }
                });
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
                $('.more  span').text('加载成功');
                break;
            case 4:
                $('.more .pull_icon').removeClass('flip loading');
                $('.more span').text('没有更多数据');
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
    })
</script>
</html>