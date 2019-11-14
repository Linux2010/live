<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/study.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="body_s" style="display: none;">
    <div class="con1">
        <div class="con1Select">
            <span class="active">今日</span>
            <span><a href="${ctx}wap/study/today_recomm">推荐</a></span>
            <span><a href="${ctx}wap/study/report_page">报告</a></span>
        </div>
    </div>
    <div style="width: 100%;height: 0.85rem;"></div>
    <div class="con2" style="position: relative;">
        <a id="lianjie"><img src="" alt="" style="width:7.5rem;height: 7rem;" id="postImg"/></a><div style="padding: 0.5rem;color: white;position: absolute;top: 0;max-height: 6rem;overflow: hidden;padding-top: 0.7rem;"><p id="title" style="font-size:0.35rem;color: white"></p><br><p id="content" style="color: white;font-size: 0.3rem"></p></div><div class="shareImg" style="position: absolute;bottom:1.5rem;right:1rem;"><div style="text-align: center;color:white;display: none" >分享</div></div>
    </div>
    <div class="con3">
        <div class="con3Tit">
            <span>今日课程</span><a href="${ctx}wap/study/today_course_more_page"><span style="float: right;">更多 》</span></a>
        </div>
        <div class="con3Con">
            <#if message == "你暂时还没有登录，请先登录!">
                <div class="flexCell">
                </div>
            </#if>
            <#if message == "success">
                <#if (todayrecomlist?size>0)>
                    <#list todayrecomlist as data>
                        <div class="flexCell">
                            <a href="${ctx}wap/course/courseDetail?courseType=${data.tyval!}&courseId=${data.courseId!}"><img src="${data.courseImg!}" style="width: 2.3rem;height: 1.677rem;"></a>
                            <div >${data.courseTitle!}</div>
                        </div>
                    </#list>
                <#elseif (todayrecomlist?size<=0)>

                </#if>
            </#if>
        </div>
    </div>
    <div class="con3">
        <div class="con3Tit">
            <span>今日学习</span><a href="${ctx}wap/study/my_today_course_more_page"><span style="float: right;">更多 》</span></a>
        </div>
        <div class="con3Con">
        <#if message == "你暂时还没有登录，请先登录!">
            <div class="flexCell">

            </div>
        </#if>
        <#if message == "success">
            <#if (todaystudy?size>0)>
                <#list todaystudy as data>
                    <div class="flexCell">
                        <a href="${ctx}wap/study/today_detail?courseId=${data.courseId!}"><img src="${data.courseImg!}" style="width: 2.3rem;height: 1.677rem;"></a>
                        <div >${data.courseTitle!}</div>
                    </div>
                </#list>
            <#else>
            </#if>
        </#if>
        </div>
    </div>
    <div class="con3">
        <div class="con3Tit">
            <span>好文推荐</span><a href="${ctx}wap/study/good_article_page"> <span style="float: right;">更多 》</span></a>
        </div>
        <div class="con3Con">
            <#if (goodarticlelist?size>0)>
                <#list goodarticlelist as data>
                    <div class="flexCell">
                        <a href="${data.contentUrl!}"><img src="${data.cover!}" style="width: 2.3rem;height: 1.677rem;"></a>
                        <div >${data.title!}</div>
                    </div>
                </#list>
            <#else>
            </#if>
        </div>
    </div>
    <div class="con3">
        <div class="con3Tit">
            <span>优质课程</span><a href="${ctx}wap/study/good_course_more_page"><span style="float: right;">更多 》</span></a>
        </div>
        <div class="con3Con">
            <#if (excecourlist?size>0)>
                <#list excecourlist as data>
                    <div class="flexCell">
                        <a href="${ctx}wap/course/courseDetail?courseType=${data.tyval!}&courseId=${data.courseId!}"><img style="width: 2.3rem;height: 1.677rem;" src="${data.courseImg!}"></a>
                        <div >${data.courseTitle!}</div>
                    </div>
                </#list>
            <#else>
            </#if>
        </div>
    </div>
    <div style="height: 1rem;width: 100%"></div>
    <div class="bottom">
        <a href="${ctx}wap/index">
            <div class="b">
                <img src="${ctx}image/home-norm2.png">
                <span>首页</span>
            </div>
        </a>
        <a href="${ctx}wap/study/study_today">
            <div class="b">
                <img src="${ctx}image/study_norm1.png">
                <span style="color: #ffa42f">学习</span>
            </div>
        </a>
        <a href="${ctx}wap/user/set_to_my">
            <div class="b">
                <img src="${ctx}image/my_norm2.png">
                <span>我的</span>
            </div>
        </a>
    </div>
</div>
</body>
<script>

    $(function(){
        $(".spinner").hide();
        $("#body_s").show();

    });

    $.ajax({
        async: false,
        type: "POST",
        url: base+"study/article_list",
        dataType: "json",
        success: function (data) {
            if (data.result == 1) {
                var post_data = data.data;
                $("#postImg").attr("src",post_data.img);
                $("#content").html(post_data.showContent);
                $("#title").html(post_data.title);
                $("#lianjie").attr("href",post_data.content);
            }
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })


</script>
</html>