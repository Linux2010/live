$(function(){

    // 显示课程考题
    $.ajax({
        async: true,
        type: "POST",
        url: base + "course/searchCtListByCId",
        data: {courseId : $("#courseId").val()},
        dataType: "json",
        success: function (data) {
            if(data != null && data.length > 0){
                var htmlVal = "";
                for(var i = 0;i < data.length;i++){
                    htmlVal += "<ul>"
                        + "<li>题目"+ data[i].topicNo+"："+data[i].topicName+"</li>"
                        + "<li>A."+data[i].topicAvalue+"</li>"
                        + "<li>B."+data[i].topicBvalue+"</li>"
                        + "<li>C."+data[i].topicCvalue+"</li>"
                        + "<li>D."+data[i].topicDvalue+"</li>"
                        + "<li>正确答案："+data[i].rightAnswer+"</li></ul>";
                }
                $("#ctDetail").append(htmlVal);
            }else{
                $("#ctDetail").append("暂无考题");
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        if($("#cType").val() == "spkc"){// 如果来自视频课程的查看链接，则直接返回视频课程列表页面
            location.href=base+"courseSp/courseSpIndex";
        }
        if($("#cType").val() == "wzkc"){// 如果来自文字课程的查看链接，则直接返回文字课程列表页面
            location.href=base+"courseWz/courseWzIndex";
        }
        if($("#cType").val() == "ypkc"){// 如果来自音频课程的查看链接，则直接返回音频课程列表页面
            location.href=base+"courseYp/courseYpIndex";
        }
        if($("#cType").val() == "zbkc"){// 如果来自直播课程的查看链接，则直接返回直播课程列表页面
            location.href=base+"courseZb/courseZbIndex";
        }
    });

});