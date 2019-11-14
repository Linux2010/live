<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/classify.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
</head>
<body>
<#if goodsCatList??>
<div class="listL">
    <div id="wrapper1" style="height: 100%;width: 100%;">
        <div class="scroller">
            <ul>
                <#list goodsCatList as goodsCat>
                    <#if goodsCat_index == 0>
                        <li class="active">
                            <div class="item"><span>${goodsCat.catName}</span></div>
                            <input type="hidden" id="firstInput" name="parentId" value="${goodsCat.catId}"/>
                        </li>
                    <#else>
                        <li>
                            <div class="item"><span>${goodsCat.catName}</span></div>
                            <input type="hidden" name="parentId" value="${goodsCat.catId}"/>
                        </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div>
</div>
<div class="listR">
    <div id="wrapper2" style="height: 100%;width: 100%;">
        <div class="scroller">
            <ul>
                <li>
                    <div class="line">
                        <#--<a href="home/courseList.html">-->
                            <#--<div class="goods">-->
                                <#--<img class="img" src="${ctx}image/logo.png"/>-->
                                <#--<div class="p" id="firstName">前端1</div>-->
                            <#--</div>-->
                        <#--</a>-->
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
</#if>
<div class="bottom">
    <a href="${ctx}mall/index"><div class="b">
        <img src="${ctx}image/shophome1.png">
        <span>商城</span>
    </div></a>
    <a href="${ctx}mall/goodsType"><div class="b">
        <img src="${ctx}image/type.png">
        <span style="color:#ffa42f">分类</span>
    </div></a>
    <a href="shop/sale.html"><div class="b">
        <img src="${ctx}image/sale1.png">
        <span>特惠</span>
    </div></a>
    <a href="shop/car.html"><div class="b">
        <img src="${ctx}image/shopping1.png">
        <span>购物车</span>
    </div></a>
</div>
</body>
<script>
    $(function () {
        var myscroll1 = new iScroll("wrapper1");
        var myscroll2 = new iScroll("wrapper2");
        var ulli1 = $(".listL #wrapper1 ul li");
        var id=$("#firstInput").val();
        var listRLiLine = $(".listR ul li .line");
        //listRLiLine.html('');
        var html ="";
        $.ajax({
            async: false,
            url: "${ctx}wap/mall/twoForGoodsCat",
            data: {"parentId": id},
            dataType: "json",
            type: "post",
            success: function (data) {
                for (var i =0; i<data.length; i++){
                    html+='<a href="${ctx}wap/goods/goodsList?catId='+data[i].catId+'">'+
                                '<div class="goods">'+
                                '<div style="width:100%;display:flex;height:1.3rem;align-items:center;flex-direction:column;justify-content:space-around"><img class="img" src="'+data[i].imageUrl+'"/></div>'+
                                '<div class="p">'+data[i].catName+'</div>'+
                                '</div>'+
                            '</a>';

                }
                listRLiLine.html(html);
            },
            error: function (data) {
                alert("系统异常，请稍后再试！");
            }
        })

        ulli1.click(function () {
            ulli1.removeClass("active");
            $(this).addClass("active");
            id = $(this).children("input").val();
            html = "";
            $.ajax({
                async: false,
                url: "${ctx}wap/mall/twoForGoodsCat",
                data: {"parentId": id},
                dataType: "json",
                type: "post",
                success: function (data) {
                    for (var i =0; i<data.length; i++){
                        html+='<a href="${ctx}wap/goods/goodsList?catId='+data[i].catId+'">'+
                                '<div class="goods">'+
                                '<div style="width:100%;display:flex;height:1.3rem;align-items:center;flex-direction:column;justify-content:space-around"><img class="img" src="'+data[i].imageUrl+'"/></div>'+
                                '<div class="p">'+data[i].catName+'</div>'+
                                '</div>'+
                                '</a>';
                    }
                    listRLiLine.html(html);
                },
                error: function (data) {
                    alert("系统异常，请稍后再试！");
                }
            })
        })
    })

</script>
</html>