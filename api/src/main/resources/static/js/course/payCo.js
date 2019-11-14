$(function () {
    if(!is_weixin()){
        $("#alipayli").show();
    }
});

// 返回页面
var returnUrl = encodeURIComponent(base+"course/showPayCoPage?courseId="+$("#courseId").val()+"&courseType="+$("#courseType").val());
var zhifutext=$(".zhifumimaBox .nums input[type=text]");
var zhifupassword=$(".zhifumimaBox .nums input[type=password]");
var toStr;var i;zhifutext.focus();
window.addEventListener('keyup', function () {
    zhifutext.focus();
    zhifupassword.val('');
    toStr=zhifutext.val()+'';
    for(i=0;i<toStr.length;i++){
        if(toStr[i]!==''&&toStr[i]!==null){
            zhifupassword.eq(i).val(toStr[i])
        }
    }
}, false);

function ensure() {
    if($("input[type='radio']:checked").val() == 0) {
        checkPayPassword(zhifutext.val());//校验支付密码
        $(".zhifumimaBox").hide();
        zhifupassword.val('');
        zhifutext.val('');
    }
}

function anensure() {
    $(".zhifumimaBox").hide();
    zhifupassword.val('');
    zhifutext.val('');
}

function payCo() {
    var payPassword = searchUserPayPass();
    if(!payPassword) {
        location.href = base+"search/setZhifuPwd?returnUrl="+returnUrl;// 设置支付密码页面
    }
    if(!$("input[type='radio']:checked").val()){
        alert("请选择一种支付方式");
        return false;
    }
    if($("input[type='radio']:checked").val() == 0){
        $(".zhifumimaBox").show();
        zhifutext.focus()
    }else if($("input[type='radio']:checked").val() == 1){
        $(".zhifumimaBox").show();
        addCo(1,requsetaliH5Url);
    }else if($("input[type='radio']:checked").val() == 2) {
        if( is_weixin()){
            addCo(2,requsetUrl);
        }else{
            addCo(2,requsetH5Url);// H5支付
        }
    }
}

function is_weixin(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}

// 生成课程订单
function addCo_(payType){
    var rewardResult = null;
    $.ajax({
        async: false,
        type: "POST",
        url: base + "course/addCo",
        data: {courseId: $("#courseId").val(),totalMoney:$("#totalMoney").val(),payType:payType},
        dataType: "json",
        success: function (data) {
            rewardResult = data;
        },
        error: function (data) {
            rewardResult = data;
        }
    });
    return rewardResult;
}

// 生成课程订单
function addCo(payType,callback){
    $.ajax({
        async: false,
        type: "POST",
        url: base + "course/addCo",
        data: {courseId: $("#courseId").val(),totalMoney:$("#totalMoney").val(),payType:payType},
        dataType: "json",
        success: function (data) {
            if(data.courseOrderNo){
                callback(data.courseOrderNo);
            }else{
                alert("支付失败");
                payFail();
            }
        },
        error: function (data) {
            alert("支付失败");
            payFail();
        }
    });
}

// 验证支付密码与学分支付，0代表没有支付密码，1代表支付密码不正确，2代表支付密码正确
function checkPayPassword(payPass){
    $.ajax({
        async: false,
        type: "POST",
        url: base + "course/searchUserPpCount",
        data: {payPass:payPass},
        dataType: "json",
        success: function (data) {
            if (data.data == 1) {
                alert("支付密码错误");
                return false;
            } else if (data.data == 0) {
                alert("请设置支付密码");
                return false;
            } else if (data.data == 2) {
                var payWay = $("input[type='radio']:checked").val();
                var rewardResult = addCo_(payWay);// 生成订单
                if (rewardResult == null) { // 支付失败
                    alert("支付失败");
                    payFail();
                }
                if (rewardResult.resultVal == 1) {
                    if (rewardResult.courseOrderNo) {
                        if (payWay == 0) {
                            alert("学分支付成功");
                            location.href = base+"course/courseDetail?courseType="  +$("#courseType").val() +"&courseId="+$("#courseId").val();
                        }
                    }
                } else if (rewardResult.resultVal == 0) {
                    alert(rewardResult.courseOrderNo);// 余额不足，暂时使用订单编号存储返回信息
                    return false;
                } else {
                    alert("支付失败");
                    payFail();
                }
            }
        },
        error: function (data) {
            alert(data.message);
        }
    });
}

// 支付失败跳转页面
function payFail(){
    location.href=base+"course/showPayCoFailPage?courseType="+$("#courseType").val()+"&courseId="+$("#courseId").val();
}

// 跳转到微信支付页面
function requsetUrl(orderId) {
    window.location.href=base+"wx/auth?orderId="+orderId+"&returnUrl="+returnUrl;
}

// 跳转到微信H5支付页面
function requsetH5Url(orderId) {
    location.href=base+"wx/topayWap?orderNo="+orderId+"&returnUrl="+returnUrl;
}

// 跳转到支付宝H5支付页面
function requsetaliH5Url(orderId) {
    location.href=base+"ali/topayWap?orderNo="+orderId+"&returnUrl="+returnUrl;
}

// 查找用户支付密码
function searchUserPayPass(){
    var payPassword = null;
    $.ajax({
        async: false,
        type: "POST",
        url: base + "course/searchUserPayPass",
        dataType: "json",
        success: function (data) {
            payPassword = data.data;
        },
        error: function (data) {
            payPassword = data.data;
        }
    });
    return payPassword;
}