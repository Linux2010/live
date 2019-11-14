var c = $("#coun").val()+" "+$("#pro").val()+" "+$("#city").val()
$("#add_").html(c);
function mask() {
    $(".mask").show();
}
function closemask() {
    $(".mask").hide();
}
/**修改个人信息*/
$(".button").on("click", function () {
/**拆分地址省市区Id*/
    var cou_id = regionId[0];
    var pro_id = regionId[1];
    var city_id = regionId[2];
 /**拆分地址省市区*/
    var add_detail = $("#add_").html();
    var strs = new Array(); //定义一数组
    strs = add_detail.split(" "); //字符分割
    var coun_name = strs[0];
    var pro_name = strs[1];
    var city_name = strs[2];
    $.ajax({
        async: false,
        timeout : 30000, //超时时间设置，单位毫秒
        type: "POST",
        url: base + "user/updateUser",
        data: {
            'userName': $("#userName").val(),
            'realName': $("#realName").val(),
            'email': $("#email").val(),
            'userId': $("#userId").val(),
            'countries': coun_name,
            'province': pro_name,
            'city': city_name,
            'coun_id': cou_id,
            'pro_id': pro_id,
            'city_id': city_id
        },
        dataType: "json",
        success: function (data) {
            alert(data.message);
            window.location.href = base + "user/userInfo?userId="+$("#userId").val();
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
})