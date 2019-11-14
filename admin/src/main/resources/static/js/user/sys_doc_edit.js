$(function(){

    // 显示系统文案内容
    $.ajax({
        async: true,
        type: "POST",
        url: base + "sysDoc/searchSdcByType",
        data: {docType : $("#docType").val()},
        dataType: "text",
        success: function (data) {
            if(data != null){
                // 赋值
                if($("#docType").val() == 1){
                    $("#docTypeSpan").text("学分制度");
                }else if($("#docType").val() == 2){
                    $("#docTypeSpan").text("激活码制度");
                }else if($("#docType").val() == 3){
                    $("#docTypeSpan").text("邀请好友规则");
                }else if($("#docType").val() == 4){
                    $("#docTypeSpan").text("积分制度");
                }else{
                    $("#docTypeSpan").text("");
                }
                ue.ready(function() {// 编辑器初始化完成再赋值
                    if(data == null || data == ""){
                        ue.setContent("暂无内容");  // 赋值给UEditor
                    }else{
                        ue.setContent(data);  // 赋值给UEditor
                    }
                });
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 保存文案内容
    $("#uploadBtn").click(function(){
        // 先验证非空项
        if(!verifyValue($("#id").val(),ue.getContent())){
            return false;
        }else{
            // 设置保存按钮不可用
            $("#uploadBtn").attr("disabled",true);
            $("#uploadBtn").text("保存中...");
            // 如果验证通过，则提交表单
            $.ajax({
                type:"post",
                data:{id:$("#id").val(),docContent:ue.getContent()},
                dataType:"text",
                url:base+"sysDoc/modifySdc",
                success: function(data) {
                    // 设置保存按钮可用
                    $("#uploadBtn").attr("disabled",false);
                    $("#uploadBtn").text("保 存");
                    if(data == 0){
                        location.href=base+"sysDoc/showSdIndex";
                    }else{
                        Modal.alert({msg : "保存失败！", title : "提示", btnok : "确定"});
                    }
                },
                error:function(data){
                    // 设置保存按钮可用
                    $("#uploadBtn").attr("disabled",false);
                    $("#uploadBtn").text("保 存");
                    Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                }
            });
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"sysDoc/showSdIndex";
    });

    // 校验对应数据
    function verifyValue(id,docContent){
        var result = false;
        if(isEmpty(id)){
            Modal.alert({msg: "文案ID不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(docContent)){
            Modal.alert({msg: "文案内容不能为空！",title: "提示", btnok: "确定"});
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