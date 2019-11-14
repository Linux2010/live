$(function () {
    var userId= $("#userId").val();
    $.ajax({
        async:false,
        type: "POST",
        url: base+"user/getUserLabelName",
        data:{'userId':userId},
        dataType: "json",
        success:function(data){
            if(data != null){
                console.log(data.expirationDate);
                $("#user_name").html(data.userName);
                 $("#nick_name").html(data.nickName);
                $("#real_name").html(data.realName);
                $("#address").html(data.address);
                $("#user_identity").html(userIdentity(data.userIdentity));
               if(data.userIdentity == 1){
                   $("#e_date").html("你暂时还不是会员用户");
               }else{
                  if(data.expirationDate == -1){
                      $("#e_date").html("永久会员");
                  }else{
                      $("#e_date").html(expirationDate(new Date(data.expirationDate).pattern("yyyy-MM-dd HH:mm")));
                  }
               }
                $("#label_name").html(label_(data.labelName));
                $("#photo").attr("src",data.photo);
            }
        },
        error:function(data){
            alert("系统异常，请稍后再试!");
        }
    })
})
var userIdentity = function(v){
    if (v == 1) {
        return "普通用户";
    } else if (v == 2) {
        return "会员用户";
    }else{
        return "";
    }
}
var expirationDate = function(v) {
    if (v == null) {
        return "您还不是会员用户";
    } else {
        return v;

    }
}
var label_ = function (v){
    if (v == "null,") {
        return "您还未匹配学习标签";
    } else {
        return v;

    }
}
$("#return_index").on("click",function () {
    window.location.href=base+"user/";
})