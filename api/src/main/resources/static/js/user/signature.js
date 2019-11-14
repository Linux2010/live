/*修改签名*/
$(".sub").on("click",function () {
    $.ajax({
        async: false,
        type: "POST",
        url: base+"user/updateSignature",
        data: {'userId':$("#userId").val() ,'signature':$("#signature").val()},
        dataType: "json",
        success: function (data) {
            if(data.result == 1){
                alert("修改成功!");
                window.location.href=base + "user/userInfo?userId="+$("userId").val();
            }
        },
        error: function (data) {
            alert( "系统异常，请稍后重试");
        }
    })
})