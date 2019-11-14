$(function(){

    // 显示文案内容
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
                if(data == null || data == ""){
                    $("#docContent").html("暂无内容");
                }else{
                    $("#docContent").html(data);
                }
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"sysDoc/showSdIndex";
    });

});