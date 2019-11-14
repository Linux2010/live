$(function(){

    // 显示课程内容
    $.ajax({
        async: true,
        type: "POST",
        url: base + "courseWz/searchCourseContentById",
        data: {courseId : $("#courseId").val()},
        dataType: "json",
        success: function (data) {
            if(data != null){
                // 赋值
                $("#courseTitle").text("课程名称："+data.courseTitle);
                if(data.courseContent == null || data.courseContent == ""){
                    $("#courseContent").html("暂无内容");
                }else{
                    $("#courseContent").html(data.courseContent);
                }
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"courseWz/courseWzIndex";
    });

});