<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/zhuce.css?v=1" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/swiper-3.3.1.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/swiper-3.3.1.min.js" type="text/javascript"></script>
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/vue.min.js"></script>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;" onclick="backFuc()"><</span><span style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">注册</span>
</div>
<div class="con3">
    <div class="con3Con">
        <label>手机号码</label><span class="span1" id="sel_city">选择地区</span>
        <input class="input1" type="text" v-model="telVal" :disabled="telDisabled" placeholder="请输入手机号" id="phone" onblur="checkUserPhone()">
    </div>
    <div class="con3Con">
        <label style="vertical-align: middle;"></label>
        <input class="input2" type="text" v-model="codeVal" placeholder="输入验证码">
        <span class="span2" v-html="codeImgVal" v-bind:class="{inputcodeimg:telStates&codeImgValStates}" v-on:click="getCode($event)"></span>
    </div>
    <div class="con3Con">
        <label>用户名</label><input type="text" v-model="usernameVal" id="userName" placeholder="输入用户名" onblur="checkUserName()">
    </div>
    <div class="con3Con">
        <label>密码</label><input type="password" v-model="passwordVal" placeholder="设置密码" id="password">
    </div>
    <div class="con3Con">
        <label>确认密码</label><input type="password" v-model="truePasswordVal" placeholder="确认密码">
    </div>
    <div class="con3Con">
        <label>推荐人账号</label><span class="span1" id="sel_city1">选择地区</span>
        <input class="input1" type="text" placeholder="推荐人手机号(选填)" id="recommendname">
    </div>
    <div class="con3Con">
        <input type="button" v-on:click="sub(telStates&codeStates&usernameStates&passwordValStates&truePasswordValStates&ing)" v-model="registerVal" v-bind:class="{submit:telStates&codeStates&usernameStates&passwordValStates&truePasswordValStates&ing}">
    </div>
</div>
<div class="con4">
    <div class="con4Con">
        <a href="${ctx}ios/user_agreement.html">注册代表您已经阅读并同意《教头学院协议》</a>
    </div>
</div>
</body>
<script>
    // 检测手机号是否已经注册
    function checkUserPhone(){
        $.ajax({
            type: "POST",
            data:{
                cCode:$("#sel_city").text().substr(1,3),
                phone:$("#phone").val()
            },
            url: "${ctx}wap/wx/checkUserPhone",
            dataType: "text",
            success: function (msg) {
                if(msg == "1"){
                    alert("此手机号已注册");
                    return false;
                }
            },
            error:function(data){
                alert("系统异常，请稍后重试");
            }
        });
    }
    // 检测用户名是否重复
    function checkUserName(){
        $.ajax({
            type: "POST",
            data:{
                userName:$("#userName").val()
            },
            url: "${ctx}wap/wx/checkUserName",
            dataType: "text",
            success: function (msg) {
                if(msg == "1"){
                    alert("此用户名已被使用");
                    return false;
                }
            },
            error:function(data){
                alert("系统异常，请稍后重试");
            }
        });
    }
    var v1=new Vue({
        el:'.con3',
        data:{
            telVal:'',
            telDisabled:false,
            codeVal:'',
            codeImgVal:'获取验证码',
            codeImgValStates:true,
            usernameVal:'',
            passwordVal:'',
            truePasswordVal:'',
            registerVal:'注册',
            wait:60,
            ing:true
        },
        computed:{
            telStates:function () {
                if(this.telVal != null && this.telVal != ""){
                    return true;
                }
            },
            codeStates:function () {
                return this.codeVal!=="";
            },
            usernameStates:function () {
                return this.usernameVal!=="";
            },
            passwordValStates:function () {
                return this.passwordVal!=="";
            },
            truePasswordValStates:function () {
                return this.truePasswordVal!==""&&(this.passwordVal===this.truePasswordVal);
            }
        },
        methods:{
            sub:function (a) {
                if (a){
                    this.ing=false;
                    if($("#sel_city").text() == "选择地区"){
                        alert("请选择地区");
                        this.ing=true;
                        return false;
                    }
                    v1.registerVal = "注册中...";
                    $.ajax({
                        type: "POST",
                        data:{
                            counttries_code:$("#sel_city").text().substr(1,3),
                            phone:this.telVal,
                            code:this.codeVal,
                            password:this.passwordVal,
                            username:this.usernameVal,
                            recommencode:$("#sel_city1").text().substr(1,3),
                            recommendname:$("#recommendname").val()
                        },
                        url: "${ctx}api/register/simp_register",
                        dataType: "json",
                        success: function (msg) {
                            if(msg.message == "success"){
                                v1.ing=true;
                                v1.registerVal = "注册";
                                alert("注册成功");
                                window.location.href = "${ctx}wap/index";
                            }else{
                                v1.ing=true;
                                v1.registerVal = "注册";
                                alert(msg.message);
                            }
                        },
                        error:function(data){
                            v1.ing=true;
                            v1.registerVal = "注册";
                            alert("系统异常，请稍后重试");
                        }
                    });
                }
            },
            getCode:function (e) {
                var flagVal = true;
                if($("#sel_city").text() == "选择地区"){
                    alert("请选择地区！");
                    return false;
                }
                $.ajax({
                    async: false,
                    type: "POST",
                    data:{
                        cCode:$("#sel_city").text().substr(1,3),
                        phone:$("#phone").val()
                    },
                    url: "${ctx}wap/wx/checkUserPhone",
                    dataType: "text",
                    success: function (msg) {
                        if(msg == "1"){
                            alert("此手机号已注册");
                            flagVal = false;
                            return false;
                        }
                    },
                    error:function(data){
                        v1.ing=true;
                        v1.registerVal = "注册";
                        alert("系统异常，请稍后重试");
                    }
                });
                if(flagVal){
                    var a = e.currentTarget;
                    if(this.wait===60&&this.telStates){
                        $.ajax({
                            async: false,
                            type: "POST",
                            data: {
                                phone: this.telVal,
                                countries_code: $("#sel_city").text().substr(1, 3)
                            },
                            url: "${ctx}wap/wx/sendCode",
                            dataType: "json",
                            success: function (msg) {
                                if (msg != null) {
                                    if (msg.message != "success") {
                                        alert("验证码发送失败");
                                    }
                                } else {
                                    alert("验证码发送失败");
                                }
                            },
                            error: function (data) {
                                alert("系统异常，请稍后重试");
                            }
                        });
                        this.codeImgValStates=false;
                        this.telDisabled=true;
                        time(a);
                    }
                    function time(a) {
                        if (v1.wait === 0) {
                            v1.codeImgValStates=true;          //toyellow
                            v1.telDisabled=false;              //toyellow
                            v1.codeImgVal="发送验证码";
                            v1.wait = 60;
                        } else {
                            v1.codeImgVal="重新发送("+v1.wait+")";
                            v1.wait--;
                            setTimeout(function(){time(a)},1000)
                        }
                    }//time
                }
            }
        }
    });

    var jsonStr = "";
    $.ajax({
        url: "${ctx}wap/wx/countryNumber",
        type: "POST",
        dataType: "json",
        async: false,
        success: function (data) {
            if (data != null && data.length > 0 ) {
                for (var i = 0; i < data.length; i++) {
                    jsonStr += "{\"num\":\"+" + data[i].international + "\",\"name\":\"" + data[i].regionName + "\"},";
                }
                jsonStr = jsonStr.substring(0, jsonStr.length - 1);
                jsonStr = "[" + jsonStr + "]";
            }
        },
        error: function (data) {
            console.info("error");
        }
    })

    // 手机号区域
    var nameEl = document.getElementById('sel_city');
    function creatList(obj, list){
        obj.forEach(function(item, index, arr){
            var temp = new Object();
            temp.text = item.name

            ;
            temp.num = item.num;
            temp.value = index;
            list.push(temp);
        })
    }
    var data = [];
    var picker;
    var text;
    if(jsonStr.length>1){
        creatList(JSON.parse(jsonStr), data);
    }
    picker = new Picker({
        data: [data],
        selectedIndex:[0],
        title: '地址选择'
    });
    picker.on('picker.select', function (selectedVal, selectedIndex) {
        text = data[selectedIndex[0]].num;
        nameEl.innerText = text;
    });
    nameEl.addEventListener('click', function () {
        if(jsonStr.length < 1){
            alert("未设置地区区号");
            return;
        }
        picker.show();
    });

    // 推荐人手机号区域
    var nameEl1 = document.getElementById('sel_city1');
    var data1 = [];
    var picker1;
    var text1;
    if(jsonStr.length>1){
        creatList(JSON.parse(jsonStr), data1);
    }
    picker1 = new Picker({
        data: [data1],
        selectedIndex:[0],
        title: '地址选择'
    });
    picker1.on('picker.select', function (selectedVal, selectedIndex) {
        text1 = data1[selectedIndex[0]].num;
        nameEl1.innerText = text1;
    });
    nameEl1.addEventListener('click', function () {
        if(jsonStr.length < 1){
            alert("未设置地区区号");
            return;
        }
        picker1.show();
    });

    // 返回登录页面
    function backFuc(){
        location.href = "${ctx}wap/wx/login";
    }
    
    function login(phoneNum) {
        window.location.href = "${ctx}wap/wx/registerAndLogin?phoneNum="+phoneNum;
    }
</script>
</html>