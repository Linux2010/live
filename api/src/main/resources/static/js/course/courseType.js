$(function(){
    // 展示课程一级分类列表
    $.ajax({
        async: true,
        type: "POST",
        url: $("#basePath").val()+"wap/course/searchCtList",
        data: {parentId:"-1",typeVal:$("#typeVal").val()},
        dataType: "json",
        success: function (data) {
            if(data != null && data.length > 0){
                var htmlVal = "";
                var imgSrc = $("#basePath").val()+"image/logo.png";
                for(var i = 0;i < data.length;i++){
                    if(data[i].typeUrl != null && data[i].typeUrl != ""){
                        imgSrc = data[i].typeUrl;
                    }
                    htmlVal += "<div class='yijicell'>" +
                        "<img class='img' src='"+imgSrc+"'><p class='p'>"+data[i].typeName+"</p></div>";
                }
                $(".yijiclassify").html(htmlVal);
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });
});