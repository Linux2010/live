<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/allSearch.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/iscroll.js" type="text/javascript"></script>
</head>
<body>
<div class="search">
    <div class="searchBox">
        <div class="searchType">
            <span class="searchTypeBtn">
                <span class="searchTypeName">教头</span><img src="${ctx}image/xiasanjiao.png">
            </span>
            <div class="searchTypeList" style="display: none">
                <ul>
                    <li style="border:none;"><img class="listImg" src="${ctx}image/book.png"><span class="list">课程</span></li>
                    <li><img class="listImg" src="${ctx}image/we.png"><span class="list">教头</span></li>
                    <li><img class="listImg" src="${ctx}image/pack.png"><span class="list">商品</span></li>
                </ul>
                <img src="${ctx}image/shangsanjiao.png" class="returnsanjiao"/>
            </div>
        </div>
        <input type="text"/>
    </div>
    <span class="searchBtn">搜索</span>
</div>
<div class="hotsearch">热搜</div>
<div class="searchCon">
    <p class="p">搜索结果</p>
    <div class="kong" style="display: none">
        <img src="${ctx}image/kong.png" style="width: 6.2rem;margin: 1rem auto;display: block;"/>
        <p style="width:100%;text-align: center;font-size: 0.26rem;line-height: 0.8rem;">没有搜索到内容</p>
    </div>
    <div class="searchs">
        <a>
            <div class="con3Item">
                <img class="con3ItemLeft" src="${ctx}image/kechenging1.png">
                <div class="con3ItemRight">
                    <span class="con3ItemTitle">时间不在于你拥有多少，而在于你怎于你怎于你怎样使用</span>
                    <div class="con3ItemBody">
                        <div class="con3ItemTime con3ItemTimeMan">
                            <p>发布人：艾克</p>
                            <p>3月24日 10:00 发布</p>
                        </div>
                        <div class="con3ItemTime con3ItemTimeMoney" style="text-align:right;">
                            <a><b>免费</b></a>
                            <!--<p>已预约人数：20</p>-->
                        </div>
                    </div>
                </div>
            </div>
        </a>
    </div>
</div>

<div class="searchClear">
    <span class="searchClearBtn">清除搜索内容</span>
</div>
</body>
<script>
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
    })
</script>
</html>