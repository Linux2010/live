var topicIndex=0;
jQuery(function ($) {
    //添加考题
    $("#add-form-topic #addTopic").click(function () {
        ++topicIndex;
        //记录考题题号
        $("#topicTemplate").find("#topicNo").val(topicIndex);
        $("#topicTemplate").find(".topicIndex").text(topicIndex);
        var $topic = $($("#topicTemplate").prop("outerHTML")); //$("#topicTemplate").clone(true);
        $("#add-form-topic").append( $topic.css({"display":"block"}).prop("id","topic").addClass("topic topicIndex" + topicIndex));
    });
    //选择考题答案
    $(document).on("change",".topic input[type=checkbox]",function(){
        if($(this).is(":checked")){
            $(this).val(1);
        }else{
            $(this).val(0);
        }
        //展示正确答案
        $(this).parents(".topic").find("#rightAnwer").text(showRightAnswer($(this).parents(".topic")));
    });

    //添加考卷
    $("#addTopics").bind("click",function () {
        var course_topic_examination_id =  $("#add-form-topic").find("input[name=course_topic_examination_id]").val();
        var examinationName =  $("#add-form-topic").find("#examinationName").val();
        if(!examinationName){
            alert("考卷名称必填!");
            return false;
        }
        var isRegisterTopic = $("#add-form-topic").find('input[name="isRegisterTopic"]:checked ').val();
        var courseId =$("#add-form-topic").find("#courseId").val();

        if(!courseId){
            Modal.alert({msg: "课程必填!", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if($("#add-form-topic").find(".topic").length == 0){
            Modal.alert({msg: "请添加考题", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }

        //考卷内容
        var exam={};
        var topics=[];
        var flag=false;
        $("#add-form-topic").find(".topic").each(function() {//作废只能拿到一道题
            var course_topic_id =$(this).find("input[name=course_topic_id]").val();
            var  $obj = $(this); //$obj替换$(this)
            var topic = {};
            var topicNo = $obj.find("#topicNo").val();
            var topicName = $obj.find("#topicName").val();
            if (!topicName) {
                alert("考题名称必填");
                flag = true;
                return false;
            }
            var topicAvalue = $obj.find("#topicAvalue").val();
            var topicBvalue = $obj.find("#topicBvalue").val();
            var topicCvalue = $obj.find("#topicCvalue").val();
            var topicDvalue = $obj.find("#topicDvalue").val();
            if (!topicAvalue) {
                alert("第" + topicIndex + "题A选项值必填");
                flag = true;
                return false;
            }
            if (!topicBvalue) {
                alert("第" + topicIndex + "题B选项值必填");
                flag = true;
                return false;
            }
            if (!topicCvalue) {
                alert("第" + topicIndex + "题C选项值必填");
                flag = true;
                return false;
            }
            if (!topicDvalue) {
                alert("第" + topicIndex + "题D选项值必填");
                flag = true;
                return false;
            }
            var rightAnwer = $(this).find("#rightAnwer").text();
            if (!rightAnwer) {
                flag = true;
                alert("第" + topicIndex + "题,至少设置一个答案");
                return false;
            }
            topic["topicName"] = topicName;
            topic["topicAvalue"] = topicAvalue;
            topic["topicBvalue"] = topicBvalue;
            topic["topicCvalue"] = topicCvalue;
            topic["topicDvalue"] = topicDvalue;
            topic["rightAnwer"] = rightAnwer;
            topic["topicNo"] = topicNo;
            if(course_topic_id){topic["course_topic_id"] = course_topic_id;}
            topics.push(topic);
        });
        if(flag){
            return false;
        }
        exam["examinationName"] = examinationName;
        exam["isRegisterTopic"] = isRegisterTopic;
        exam["courseId"] = courseId;
        exam["topics"] = topics;
        if(course_topic_examination_id){exam["course_topic_examination_id"] = course_topic_examination_id;}
        submitAddTopics(exam);
    });
});

    //提交考卷与考题数据
    function submitAddTopics(exam){
        var url =base + "topic/insertTopics";
        exam = JSON.stringify(exam);
        $("#addTopics").attr("disabled",true);
        $("#addTopics").text("保存中...");
        $.ajax({
            async: false,
            type: "POST",
            url:url,
            data: {"data":exam},
            dataType: "json",
            success: function (data) {
                if(data==1){
                    if($("#redirectUrl").val()){
                        location.href=base+$("#redirectUrl").val();
                    }else{
                        history.go(-1);
                    }
                }else{
                    $("#addTopics").attr("disabled",false);
                    $("#addTopics").text("保 存");
                    Modal.alert({msg: "添加失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#addTopics").attr("disabled",false);
                $("#addTopics").text("保 存");
                alert("系统异常，请稍后再试!");
            }
        });
    }

    //正确答案
    function showRightAnswer($obj){
        var rightAnswer="";
        $obj.find('input[type=checkbox]:checked ').each(function(){
            var key = $(this).prop("id");
            switch(key){
                case "topicAvalueCheck":
                    rightAnswer += 'A,';
                    break;
                case "topicBvalueCheck":
                    rightAnswer += 'B,';
                    break;
                case "topicCvalueCheck":
                    rightAnswer += 'C,';
                    break;
                default:
                    rightAnswer += 'D,';
                    break;
            }
        });
        return rightAnswer;
    }

//删除历史考题div
function removeTopics(idTag){
    $(idTag).find(".topic").each(function (index,element) {
        $(this).remove();
    });
}

// 返回课程列表页面
function backCoursePage(){
    location.href=base+$("#redirectUrl").val();
}