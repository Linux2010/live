$(function(){

    // 展示课程订单
    $.ajax({
        async: true,
        type: "POST",
        url: $("#basePath").val()+"wap/course/searchCo",
        data: {courseId:$("#courseId").val()},
        dataType: "json",
        success: function (data) {
            if(data != null){
                var nameVal = data.realName;
                if(nameVal == null || nameVal == ""){
                    nameVal = data.userName;
                }
                var phoneVal = "";
                if(data.userPhone != null){
                    phoneVal = data.userPhone;
                }
                var mailVal = "";
                if(data.userEmail != null){
                    mailVal = data.userEmail;
                }
                $("#nameDiv").html("姓名："+nameVal);
                $("#phoneDiv").html("电话："+phoneVal);
                $("#mailDiv").html("邮箱："+mailVal);
                $("#tName").text(data.tName);
                $("#tPhoto").attr("src",data.tPhoto);
                $("#courseCover").attr("src",data.courseCover);
                $("#courseTitle").text(data.courseTitle);
                $("#courseBeginTime").html(new Date(data.courseBeginTime).pattern("yyyy-MM-dd HH:mm"));
                var costPriceVal = "免费";
                if(data.costPrice != 0){
                    costPriceVal = "¥"+data.costPrice;
                }
                $("#costPrice").html(costPriceVal);
                $("#totalMoney").html("合计："+costPriceVal);
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试！");
        }
    });

});

// 支付课程订单
function showPayCoPage(){
    location.href = $("#basePath").val()+"wap/course/showPayCoPage?courseType="+$("#courseType").val()+"&courseId="+$("#courseId").val();
}