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
<input type="hidden" id="userId" name="userId" value="${userId!}"/>
<input type="hidden" id="type" name="type" value="${type!}"/>
<input type="hidden" id="courseTopicExaminationId" name="courseTopicExaminationId" value="${courseTopicExaminationId!}"/>
<div class="con1">
   <a href="javascript:history.go(-1)"><span style="width: 20%;display: inline-block;"><</span></a><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">未答考题</span>
</div>
<div class="con5" id="courseTopicsAnswerList">
</div>

<div class="con3Con" >
    <div class="button"  onclick="subFuc()"  style="width:90%;margin:1rem 5% 0;background-color: #ffa42f;color:white;font-size: 0.32rem;border-radius: 0.2rem;line-height: 0.7rem;text-align: center">提交</div>
</div>
</body>
<script>
    $(function () {
        myTopicsAnswers($("#courseTopicExaminationId").val());
    });
    //提交考题
    function subFuc() {
        var data={};
        data["userId"] = $("#userId").val();
        data["courseTopicExaminationId"] = $("#courseTopicExaminationId").val();

        var answers=[];
        $('#topic').each(function () {
            var topicAnswer={};
            var $topic = $(this);
            var courseTopicId =$topic.attr("courseTopicId");
            var answer ="";
            $topic.find('input[type="radio"]:checked ').each(function (i,val) {
                if(i>0){
                    answer+=$(this).val()+",";
                }else{
                    answer = $(this).val();
                }
            });
         //   var answer=$topic.find('input[type="radio"]:checked ').val();
            topicAnswer["courseTopicId"] =courseTopicId;
            topicAnswer["answer"] =answer;
            answers.push(topicAnswer);

        });
        data["answerJsonStr"] =  JSON.stringify(answers);
        submitCourseTopicsAnswer(data);

    }



    /**
     * 我的答题-查看答题
     * @param userId 答题用户ID
     * @param courseTopicExaminationId  考卷业务ID
     * @return
     */
    function myTopicsAnswers(courseTopicExaminationId){
        $.ajax({
            async: false,
            url: base + "topic/myTopicsAnswers",
            data: {courseTopicExaminationId: courseTopicExaminationId,userId:$("#userId").val()},
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    var courseTopicsAnswerList = data.data;
                    if(courseTopicsAnswerList && courseTopicsAnswerList.length >0){
                        var html="";
                       $.each(courseTopicsAnswerList,function (i,topic) {
                            html+='<div class="line1" id="topic" courseTopicId="'+topic.courseTopicId+'" style="margin: 0.3rem 0.3rem;">'
                                    +'<div class="q">'+topic.topicNo+'、'+topic.topicName+'</div>'//
                                    +'<ul>'
                                    +' <li><input type="radio" name="topicValue'+topic.topicNo+'" value="A"/><label>A , '+topic.topicAvalue+'</label></li>'
                                    +'  <li><input type="radio" name="topicValue'+topic.topicNo+'"  value="B"/><label>B , '+topic.topicBvalue+'</label></li>'
                                    +' <li><input type="radio" name="topicValue'+topic.topicNo+'"  value="C"/><label>C , '+topic.topicCvalue+'</label></li>'
                                    +'<li><input type="radio" name="topicValue'+topic.topicNo+'"  value="D"/><label>D , '+topic.topicDvalue+'</label></li>'
                                    +'</ul>'
                                    +'</div>';
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
    /**
     * 课程考题-提交课程考题答案
     * @param courseTopicExaminationId 考题试卷业务ID
     * @param userId 提交考题答案的用户ID
     * @param answerJsonStr 用户提交的考题答案json字符串
     * @return
     */
    function submitCourseTopicsAnswer(data){
        $.ajax({
            async: false,
            url: base + "topic/submitCourseTopicsAnswer",
            data:data,
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    location.href= base+ "topic/my/myTopicsCourse";
                }else{
                    alert("请求失败："+data.message);
                }
            },
            error:function(data){
                alert("请求异常："+data.message);
            }
        });
    }
</script>
</html>