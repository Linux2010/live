<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml"
      xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no"/>
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/personalData.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/commonZz.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}js/base.js" type="text/javascript"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/jquery.form.js"></script>
</head>
<body>
<div class="spinner">
    <div class="double-bounce1"></div>
    <div class="double-bounce2"></div>
    <div style="width:100%;height: 100%;text-align: center;line-height: 60px;font-weight: 600;"> 加载中...</div>
</div>
<div id="body_s" style="display: none;">
    <div class="con1">
        <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/user/setting" style="color:white"><</a></span><span
            style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">个人资料</span>
    </div>
    <div class="con4">
        <form method="post"  enctype="multipart/form-data" id="form_user_data">
            <div class="line3" style="justify-content: space-between">
                <div class="moreL">
                    <span class="s1">头像</span>
                    <!--<span class="nothing">无可用</span>-->
                </div>
                <div class="more" onclick="mask()" style="width:1.4rem;height:1.4rem;float:right;display: flex;flex-direction: column;overflow: hidden;align-items: center;border-radius: 50%;justify-content: space-around;">
                    <img src="${userInfo.photo!}" style="width:1.4rem;vertical-align: middle;margin:0 0.2rem;" id="img">
                </div>
            </div>
            <div class="line3">
                <div class="moreL"><span>用户名</span></div>
                <div class="more"><input type="text" id="userName" name="userName" value="${userInfo.userName!}"/></div>
            </div>
            <div class="line3">
                <div class="moreL"><span>推荐人</span></div>
                <div class="more"><span>${userInfo.commendname!}</span></div>
            </div>
            <div class="line3">
                <div class="moreL"><span>姓名</span></div>
                <div class="more"><input type="text" id="realName" name="realName" value="${userInfo.realName!}"/></div>
            </div>
            <div class="line3">
                <div class="moreL"><span>手机号码</span></div>
                <div class="more"><span>${userInfo.phone!}</span></div>
            </div>
            <div class="line3">
                <div class="moreL" style="width:30%;"><span>邮箱</span></div>
                <div class="more" style="width:70%;"><input type="text" id="email" name="email" value="${userInfo.email!}"/></div>
            </div>
            <div class="line3">
                <input type="hidden" value="${userInfo.countries!}" id="countries" name="countries"/>
                <input type="hidden" value="${userInfo.province!}" id="province" name="province"/>
                <input type="hidden" value="${userInfo.city!}" id="city" name="city"/>
                <input type="hidden" value="${userInfo.countriesId!}" id="countriesId" name="countriesId"/>
                <input type="hidden" value="${userInfo.provinceId!}" id="provinceId" name="provinceId"/>
                <input type="hidden" value="${userInfo.cityId!}" id="cityId" name="cityId"/>
                <div class="moreL" style="width: 30%"><span>所在地区</span></div>
                <div class="more" id="adress" style="width: 70%"><span id="add_"
                                                                       style="display: inline-block;width:93%;height: 0.5rem;"></span>>
                </div>
            </div>
            <!--<div class="line3">-->
            <!--<div class="moreL"><span >推荐人</span></div>-->
            <!--<div class="more"><span></span></div>-->
            <!--</div>-->
            <#--<input type="hidden" value="${userId}" id="userId" name="userId"/>-->

    </div>

    <div class="con5">
        <a href="${ctx}wap/user/signature">
        <div class="L">
            <p class="titleP1">个性签名：</p>
            <p class="titleP2">${userInfo.signature!}</p>
        </div>

            <div class="R">></div>
        </a>
    </div>
    <div class="mask">
        <div class="maskBox">
            <div class="line1">设置头像</div>
          <#--  <div class="line2"><input style="width: 4rem" id="image"  name="file" type="file" accept="image/*" multiple value="选择本地照片"></div>
            <div class="line2"><input style="width: 4rem;" id="camera"  name="file" type="file"  accept="image/*" capture="camera" value="拍照"></div>-->
            <div class="line2"><span id="imageBox" style="width: 4rem;display: block;text-align: center;margin: 0 auto;">相册</span><input style="display:none;width: 4rem" id="image"  name="file" type="file" accept="image/*" multiple value="选择本地照片"></div>
            <div class="line2"><span id="cameraBox" style="width: 4rem;display: block;text-align: center;margin: 0 auto;">相机</span><input style="display:none;width: 4rem;" id="camera"  name="file" type="file"  accept="image/*" capture="camera" value="拍照"></div>
            <div class="line3" onclick="closemask()">取消</div>
        </div>
    </div>
    <div class="button"
         style="width:90%;margin:0.5rem 5% 0;background-color: #ffa42f;color:white;font-size: 0.32rem;border-radius: 0.2rem;line-height: 0.7rem;text-align: center">
        保存修改
    </div>
    </form>
</div>
</body>
<script src="${ctx}js/city.js"></script>
<script>

    $(function(){
        $(".spinner").hide();
        $("#body_s").show();

    });
    var c = $("#countries").val()+" "+$("#province").val()+" "+$("#city").val()
    //修改个人头像框的显示
    $("#add_").html(c);
    function mask() {
        $(".mask").show();
    }
    function closemask() {
        $(".mask").hide();
    }
    $("#imageBox").click(function () {
        $("#image").click();
    })
    $("#cameraBox").click(function () {
        $("#camera").click();
    })

    <#--修改个人信息-->
    $(".button").click(function () {
        $(".spinner").show();
        $("#body_s").hide();
    <#--拆分地址省市区Id-->
        var cou_id = regionId[0];
        var pro_id = regionId[1];
        var city_id = regionId[2];
        $("#countriesId").val(cou_id);
        $("#provinceId").val(pro_id);
        $("#cityId").val(city_id);
    <#--拆分地址省市区Name-->
        var add_detail = $("#add_").html();
        var strs = new Array(); //定义一数组
        strs = add_detail.split(" "); //字符分割
        var coun_name = strs[0];
        var pro_name = strs[1];
        var city_name = strs[2];
        $("#countries").val(coun_name);
        $("#province").val(pro_name);
        $("#city").val(city_name);
        var user_name = $("#userName").val();
        if(user_name == null || user_name == "") {
            alert("用户名不能为空!");
            location.reload();
            return false;
        }
        var real_name = $("#realName").val();
        if(real_name == null || real_name == "") {
            alert("姓名不能为空!");
            location.reload();
            return false;
        }
       $("#form_user_data").ajaxSubmit({
            asyne: false,
            url: base+"user/updateUser",
            type: "post",
            dataType: "json",
            success: function (data) {
                if ("修改成功!" == data.message){
                    alert("修改成功!");
                    $(".spinner").hide();
                    $("#body_s").show();
                    window.location.href = "${ctx}wap/user/set_to_my";
                }else {
                    alert("修改失败!");
                    $(".spinner").hide();
                    $("#body_s").show();
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
                $(".spinner").hide();
                $("#body_s").show();
            }
        });

        //alert(document.getElementById("img").src+"图片的预览");
        //$("#form_user_data").submit();
       /*$.ajax({
            async: false,
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
                'city_id': city_id,
                'file':
            },
            dataType: "json",
            success: function (data) {
                alert(data.message);
                window.location.href = "wap/user/userInfo?userId=";
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        })*/
    })

    var img_name = $("#image").val();
    $("#image").on("click",function () {
        if($("#image").val()!= "选择本地照片"){
            if(img_name != ""){
                $(".mask").hide();
                $("#img").attr("src",img_name)
            }else {
                $(".mask").hide();
            }
        }else{
            $(".mask").show();
        }
    })
    $("#camera").on("click",function () {
        if($("#image").val()!= "拍照"){
            if(img_name != ""){
                $(".mask").hide();
                $("#img").attr("src",img_name)
            }else {
                $(".mask").hide();
            }
        }else{
            $(".mask").show();
        }
    })


    window.onload = function () {
        var fileTag = document.getElementById('image');
        var fileTag1 = document.getElementById('camera');
        fileTag.onchange = function () {
            var file = fileTag.files[0];
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    document.getElementById('img').setAttribute('src', fileReader.result);
                }
            };
            fileReader.readAsDataURL(file);
        };
        fileTag1.onchange = function () {
            var file = fileTag1.files[0];
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    document.getElementById('img').setAttribute('src', fileReader.result);
                }
            };
            fileReader.readAsDataURL(file);
        };
    };

</script>
</html>
