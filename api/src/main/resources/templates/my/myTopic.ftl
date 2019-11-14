<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/zhuce.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <style>
        ul, ol, ul li, ol li {
            list-style: none;
            margin: 0.3rem 0.3rem;
        }
    </style>
</head>
<body>
<input type="hidden" id="type" name="type" value="${type!}"/>
<input type="hidden" id="courseTopicExaminationId" name="courseTopicExaminationId" value="${courseTopicExaminationId!}"/>
<div class="con1">
    <a href="javascript:history.go(-1)"><span style="width: 20%;display: inline-block;"><</span></a><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">查看答题</span>
</div>
<div class="con5" id="courseTopicsAnswerList">

</div>

</body>
<script>
    $(function () {
        myTopicsAnswers($("#courseTopicExaminationId").val());
    });

    /**
     * 我的答题-查看答题
     * @param courseTopicExaminationId  考卷业务ID
     * @return
     */
    function myTopicsAnswers(courseTopicExaminationId){
        $.ajax({
            async: false,
            url: base + "topic/myTopicsAnswers",
            data: {courseTopicExaminationId: courseTopicExaminationId,},
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    var courseTopicsAnswerList = data.data;
                    if(courseTopicsAnswerList && courseTopicsAnswerList.length >0){
                        var html="";
                       $.each(courseTopicsAnswerList,function (i,topic) {
                            var rightAnswer =topic.rightAnswer;
                           rightAnswer = rightAnswer.replace(",","");
                            var userChooseAnswer = topic.userChooseAnswer;
                            var flag_A = "";
                            var flag_B = "";
                            var flag_C = "";
                            var flag_D = "";
                            if(userChooseAnswer){
                                userChooseAnswer = userChooseAnswer.replace(",","");
                                    if("A".indexOf(userChooseAnswer)!=-1){
                                        flag_A ='checked="checked"';
                                    }
                                    if("B".indexOf(userChooseAnswer)!=-1){
                                        flag_B ='checked="checked"';
                                    }
                                    if("C".indexOf(userChooseAnswer)!=-1){
                                        flag_C ='checked="checked"';
                                    }
                                    if("D".indexOf(userChooseAnswer)!=-1){
                                        flag_D ='checked="checked"';
                                    }

                            }
                            html+='<div class="line1" style="margin: 0.3rem 0.3rem;">'
                                    +'<div class="q">'+topic.topicNo+'、'+topic.topicName+'</div>'
                                    +'<ul>'
                                    +' <li><input type="radio" '+flag_A+'/><label> A, '+topic.topicAvalue+'</label></li>'
                                    +'  <li><input type="radio" '+flag_B+'/><label> B, '+topic.topicBvalue+'</label></li>'
                                    +' <li><input type="radio" '+flag_C+'"/><label> C, '+topic.topicCvalue+'</label></li>'
                                    +'<li><input type="radio"  '+flag_D+'"/><label> D, '+topic.topicDvalue+'</label></li>'
                                    +'</ul>'
                                    +'</div>';

                           html+='<div class="con3Con" style=" margin: 0.3rem 0.5rem;">'+topic.answerRate+'</div>';
                       });
                       $("#courseTopicsAnswerList").html(html);
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
</script>
</html>