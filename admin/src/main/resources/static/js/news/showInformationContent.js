$(function(){

    // 显示
    $.ajax({
        async: true,
        type: "POST",
        url: base + "inform/select",
        data: {informationId : $("#informationId").val()},
        dataType: "json",
        success: function (data) {
            if(data != null){

                if(data.content == null || data.content == ""){
                    $("#content").html("暂无内容");
                }else{
                    $("#content").html(data.content);
                }
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"inform/";
    });
});
