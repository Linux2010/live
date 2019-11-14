<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>激活码</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/vue.min.js"></script>
    <script src="${ctx}js/base.js"></script>
    <style>

        html,body{width:100%;}
        .con1{background-color: #22272a;padding:0.2rem;font-size: 0.34rem;line-height: 0.45rem;}
        .con1 span{color:white;}
        .line{padding:1rem 0.2rem 0.3rem;}
        .line input{width:100%;line-height: 0.5rem;padding:0.2rem 0;border: 0.03rem solid #ffa42f;text-align: center;font-size: 0.3rem;}
        .line.line2{padding-top:0;}
        .line.true{padding-top:0;}

        .line.true input {
            background-color: #ffa42f;
            height: 1.0rem;
        }
    </style>
</head>
<body>

<div class="con1">
    <a href="javascript:history.go(-1)"><span style="width: 20%;display: inline-block;"><</span></a><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">激活码</span>
    <div style="float: right;font-size: 0.28rem;color:white;"><a href="#" id="doc">说明</a></div>
</div>
    <div class="line line1"><input type="text" id="cardNo" placeholder="请输入激活账号"/></div>
    <div class="line line2"><input type="text" id="cardPassword"  placeholder="请输入密码"/></div>
    <div class="line true"><input type="button" id="activeCode" value="确定"/></div>
</body>
<script>
    $(function () {
        searchSdcByType();
    });
    $("#activeCode").click(function () {
        if(!$("#cardNo").val()){
            alert("请输入激活账号!");
            return false;
        }
        if(!$("#cardPassword").val()){
            alert("请输入密码!");
            return false;
        }
        $.ajax({
            async: false,
            type: "POST",
            url: base + "userGroupCard/activationGroupCard",
            data: {cardNo: $("#cardNo").val(),cardPassword:$("#cardPassword").val()},
            dataType: "json",
            success: function (data) {
               if(data.result == 1){
                   alert("激活成功!");
                   location.href =base+"user/set_to_my"
               }else{
                   alert(data.message);
                   $("#cardNo").val("");
                   $("#cardPassword").val("");
               }
            },
            error: function (data) {
                alert("激活失败"+data.message);
            }
        });

    });


    /**
     * 根据文案类型查询文案内容
     *
     * @param docType 文案类型：1.学分制度、2.激活码制度、3.邀请好友规则、4.积分制度
     * @return
     */
    function searchSdcByType(){
        $.ajax({
            async: false,
            url: base + "sysDoc/searchSdcByType",
            data: {docType: "2"},
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.result == 1 && data.message=="success"){
                    if(data.data){
                        $("#doc").attr("href",data.data);
                    };
                }else{
                    alert("请求失败:"+data.message);
                }
            },
            error: function (data) {
                alert("请求异常:"+data.message);
            },
        });
    }
</script>
</html>