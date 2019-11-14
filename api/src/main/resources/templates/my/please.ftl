<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>邀请好友</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/please.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/iScroll-5.1.2.js"></script>
    <script src="${ctx}js/base.js"></script>
</head>
<body>
<#--<div class="title">长按图片保存到相册</div>-->
<div id="wrapper">
    <div class="iscroll">
        <ul class="ul" id="shareImgs">
            <#--<li class="li"><img src="${ctx}image/kechenging1.png" style="width:100%;height: 100%;padding:0 0.5rem;box-sizing: border-box;"/> </li>-->
            <#--<li class="li"><img src="${ctx}image/kechenging1.png" style="width:100%;height: 100%;padding:0 0.5rem;box-sizing: border-box;"/> </li>-->
            <#--<li class="li"><img src="${ctx}image/kechenging1.png" style="width:100%;height: 100%;padding:0 0.5rem;box-sizing: border-box;"/> </li>-->
        </ul>
    </div>
</div>
<div class="explain">
    <div class="tit">
        <span class="titL">使用说明</span><span class="titR">了解详情</span>
    </div>
    <div class="con" id="doc">
        <iframe src="" width="100%"  frameborder="no" border="0"></iframe>
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
        <#--<p>a</p>-->
    </div>
</div>
</body>
<script>

$(function () {
    //加载分享图片
    getPosterShareImages();

    console.log($("body").width())
    $(".li").width($("body").width()+"px");
    $(".iscroll").width($("ul").width()+"px");
    $(".explain").css("top",($("body").height()-$(".explain .tit").height())+"px");
    var myscroll1=new IScroll("#wrapper",{
        probeType:3,
        disablePointer: true,
        disableTouch:false,
        scrollX: true,
        scrollY: false,
        hScrollbar:false,
        vScrollbar:false,
        vScroll:false,
        click : true,
        snapThreshold:0.4,
        snap : '.li',
        momentum:false,
        deceleration:0.00001,
        resizePolling:60
    });


    var explainState = 1;
    $(".titR").click(function () {
        if(explainState === 1){
            explainState = 0;
            $(".explain").css("top",($("body").height()-$(".explain").height())+"px");
        }else{
            explainState = 1;
            $(".explain").css("top",($("body").height()-$(".explain .tit").height())+"px");
        }

        searchSdcByType();

    });





    /**
     * 邀请好友-获取海报分享图片
     * @param userId
     * @return
     */
    function getPosterShareImages(userId){
        $.ajax({
            async: false,
            timeout : 30000, //超时时间设置，单位毫秒
            url: base + "user/getPosterShareImages",
            data: {},
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    var imgs = data.data;
                    var html='';
                    if(imgs && imgs.length > 0){
                        $.each(imgs,function (i,img) {
                            html+='<li class="li"><img src="'+img+'" style="width:100%;height: 100%;padding:0 0.5rem;box-sizing: border-box;"/> </li>';
                        })
                    }
                    $("#shareImgs").append(html);
                }else{
                    alert("请求失败:"+data.message);
                }
            },
            error: function (data) {
                alert("请求异常:"+data.message);
            },
        });
    }
    /**
     * 根据文案类型查询文案内容
     *
     * @param docType 文案类型：1.学分制度、2.激活码制度、3.邀请好友规则、4.积分制度
     * @return
     */
    function searchSdcByType(){
        $.ajax({
            async: false,
            url: base + "sysDoc/searchSdcByContent",
            data: {docType: "3"},
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    if(data.data){
                        $("#doc").html(data.data)
                    };
                }else{
                    alert("请求失败:"+data.message);
                }
            },
            error: function (data) {
                alert("请求异常:"+data.message);
            },
        });
    }
});

</script>
</html>