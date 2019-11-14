<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/wellTeacherHome.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script type="text/javascript" src="${ctx}js/iscroll.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/iScroll-5.1.2.js"></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <style>
        html,body{width:100%;height: 100%;overflow: hidden;}
        #wrapper1{width:100%;height: 100%;overflow: hidden;}
    </style>
</head>
<body>
<input type="hidden" id="teacherId" name="teacherId" value="${teacherId!}"/>
<input type="hidden" id="userIdentity" name="userIdentity" value="${userIdentity!}"/>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
</div>
<div id="body_s" style="display: none;width:100%;height: 100%;">
    <div class="con9">
        <div class="con9Select">
           <a href="javascript:history.go(-1);" <span style="width: 10%;display: inline-block;" onclick="location.href = base+'index'"><</span></a>
            <span style="width: 90%;display: inline-block;margin: 0 auto;text-align: center;padding-right: 0.64rem;">教头详情</span>
        </div>
    </div>
    <div id="wrapper1" style="top: 0.87rem;position: fixed;">
        <div class="scroller">
            <div class="fresh"><i class="pull_icon"></i><span>上拉加载...</span></div>
            <div class="v1" style="padding:0 0 0.5rem 0">
                <div class="con1">
                    <img  class="imgC" v-bind:src="pic"/>
                    <div class="username">{{name}}</div>
                    <p class="say">{{say}}</p>
                    <div class="level">
                        <span class="levelLabel">等级：</span>{{level}}<span></span>
                    </div>
                    <div class="button">
                        <div class="cli guanzhu" style="border-left: 0;"  v-html="interest?'已关注':'+关注'" v-on:click="interesting()" v-bind:class="{active:interest}">{{interest}}</div><div class="cli dashang" v-on:click="rewarding(0)" style="border-right: 0;">￥打赏</div>
                    </div>
                </div>
                <div class="images">
                    <div id="wrapper">
                        <div id="scroller">
                            <ul class="category_nav">
                                <#--<#if teacherIntroImgs?? &&(teacherIntroImgs?size>0)>-->
                                    <#--<#list teacherIntroImgs as img>-->
                                        <#--<li class=""><a href="${img}"><img src="${img}"> </a></li>-->
                                    <#--</#list>-->
                                <#--</#if>-->

                            </ul>
                        </div>
                    </div>
                </div>
                <div class="mask1" v-show="reward">
                    <div class="mask1Box">
                        <div class="line0">打赏</div>
                        <div class="line1">
                            <div class="label">金额:（学分）</div>
                            <input type="number" v-model="shangjin"/>
                        </div>
                        <div class="line2"><div class="no" v-on:click="rewarding(0)">取消</div><div class="yes" style="float: right;text-align: center;background-color: #ffa42f;" v-on:click="rewarding(1)">确认</div> </div>
                    </div>
                </div>
                <div class="con4" style="margin:0;">
                    <div class="con4Title">
                        <img src="${ctx}image/lingxing.png" alt=""><span>教头介绍</span><img src="${ctx}image/lingxing.png" alt="">
                    </div>
                </div>
                <div class="con5">
                <img class="img1" v-bind:src="pic" style="border-radius: 50%">
                    <p class="name">{{name}}</p>
                    <div class="wordBox">
                        <p>{{present}}</p>
                    </div>
                    <span class="more"><a href="${ctx}home/profile.html">点击查看详情</a></span>
                </div>
                <div class="con2">
                    <div class="con2Select">
                        <span  v-bind:class="{active:select1==0}" v-on:click="select1ing(0)">动态</span>
                        <span v-bind:class="{active:select1==1}"  v-on:click="select1ing(1)">作品</span>
                        <span v-bind:class="{active:select1==2}" v-on:click="select1ing(2)">问答</span>
                    </div>
                </div>
                <div class="selectCon" >
                    <div class="sele0" v-if="select1===0">
                        <div class="sele0TimeCon" id="courseRelaseDynamicList">
                            <div class="sele0TimeLine"></div>
                            <div id="flag0">
                            </div>
                        </div>
                    </div>
                    <div class="sele1" v-else-if="select1===1">
                        <div class="con3" id="flag1">

                        </div>
                    </div>
                    <div class="flag2" id="page2" v-else>
                        <div class="jilu" v-if="select2 ==0" id="flag2">
                        </div>
                    </div>
                </div>

        </div>
            <div class="more"><i class="pull_icon"></i><span>上拉加载...</span></div>
        </div>
    </div>
    <div id="comments" style="display:none">
    <div style="width:100%;height: 1.06rem;" ></div>
    <div class="write" style="z-index: 999;" >
        <div class="f" id="bottomDiv">
            <div class="writeBox1">写下你的提问（100字以内）...</div>
        </div>
        <div class="b" style="display: none">
            <textarea class="writeBox4" rows="3" placeholder="写下你的提问（100字以内）..."></textarea>
            <span id="addComm">发送</span>
        </div>
    </div>
</div>
<#--</div>-->
</body>

<script>
    $(function(){
        $(".spinner").hide();
        $("#body_s").show();
    });

</script>
<#--<script src="${ctx}js/home/wellTeacherHome.js" type="text/javascript"></script>-->
<script src="${ctx}js/home/lWellTeacherHome.js" type="text/javascript"></script>

</html>