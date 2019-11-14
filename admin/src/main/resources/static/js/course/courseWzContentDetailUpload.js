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
                ue.ready(function() {// 编辑器初始化完成再赋值
                    if(data.courseContent == null || data.courseContent == ""){
                        ue.setContent("暂无内容");  // 赋值给UEditor
                    }else{
                        ue.setContent(data.courseContent);  // 赋值给UEditor
                    }
                });
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 上传课程内容
    $("#uploadBtn").click(function(){
        // 先验证非空项
        if(!verifyValue($("#courseId").val(),ue.getContent())){
            return false;
        }else{
            // 设置上传按钮不可用
            $("#uploadBtn").attr("disabled",true);
            $("#uploadBtn").text("上传中...");
            // 如果验证通过，则提交表单
            $.ajax({
                type:"post",
                data:{courseId:$("#courseId").val(),courseContent:ue.getContent()},
                dataType:"json",
                url:base+"courseWz/modifyCourseContent",
                success: function(data) {
                    // 设置上传按钮可用
                    $("#uploadBtn").attr("disabled",false);
                    $("#uploadBtn").text("上 传");
                    if(data == 0){
                        location.href=base+"courseWz/courseWzIndex";
                        // Modal.alert({msg : "上传成功！", title : "提示", btnok : "确定"});
                    }else{
                        Modal.alert({msg : "上传失败！", title : "提示", btnok : "确定"});
                    }
                },
                error:function(data){
                    // 设置上传按钮可用
                    $("#uploadBtn").attr("disabled",false);
                    $("#uploadBtn").text("上 传");
                    Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                }
            });
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"courseWz/courseWzIndex";
    });

    // 校验对应数据
    function verifyValue(courseId,courseContent){
        var result = false;
        if(isEmpty(courseId)){
            Modal.alert({msg: "课程ID不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseContent)){
            Modal.alert({msg: "课程内容不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        result = true;
        return result;
    }

});

var ue = UE.getEditor('container');
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'catchimage') {
        return $("#basePath").val()+'/UEditor/uploadImg';
    } else if (action == 'uploadvideo') {
        return 'http://a.b.com/video.php';
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
}