$(".button").on("click",function () {
    window.location.href=base + "wx/logout";
})
$.ajax({
    async: false,
    type: "POST",
    url: base+"user/is_impl",
    data :{'userId':$("#userId").val()},
    dataType: "json",
    success: function (data) {
        var result =  data.data.is_complet;
        if(result == 1){
            $("#red_span").css("display","none")
        }else{

        }
    },
    error: function (data) {
        alert("系统异常，请稍后重试");
    }
})