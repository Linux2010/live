$.ajax({
    async: false,
    type: "POST",
    url: base+"user/is_impl",
    dataType: "json",
    success: function (data) {
        var result =  data.data.money;
        $("#money").html(result+" 学分");
    },
    error: function (data) {
        alert("系统异常，请稍后重试");
    }
})