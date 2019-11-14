$(function () {
});

//邀请好友
function share(){
    var userId = $("#userId").val();
    var userName = $("#userName").val();
    var qrCodePage = $("#qrCodePage").val();//带二维码的分享页面
   // qrCodePage = base+"../api/login/showQrCodePage?qrCodeUrl=https://dh-demo.oss-cn-hangzhou.aliyuncs.com/qrcode/252685410947432b9d173f1673a06bea.jpeg&userName="+userName;
    //如果用户没有登录调到登录页面，后期可以带入分享页面帮助用户继续之前页面操作
    if(!userId || !userName || !qrCodePage){
        location.href='../wap/wx/login';
    }else{
        location.href=qrCodePage;
    }
}