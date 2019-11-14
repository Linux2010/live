<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院用户注册</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/user/conform.js"></script>
    <#--<script src="${ctx}js/tele86.js"></script>-->
    <script src="${ctx}js/picker.min.js"></script>
    <style>
        html,body{width:100%;height: 100%;padding: 0px;margin: 0px;overflow: hidden;}
        body{position: relative;background:url("${ctx}img/zhuce_bg.png") no-repeat;background-size: 100% 100%;}
        .body{width:100%;height:100%;box-sizing: border-box;padding-top: 1.3rem;background-color: rgba(0,0,0,0.5)}
        .body .photo{width:2rem;height:2rem;border-radius:50%;border:0.02rem solid #ffa42f;background: url("/img/tx.png");background-size:2rem 2rem;background-position: right top;margin: 0 auto;}
        .body .name{width:100%;text-align: center;line-height: 1rem;font-size: 0.34rem;color:white;}
        .body .tele{width:100%;text-align: center;line-height: 1rem;font-size: 0.3rem;margin-top: 1rem;}
        .body .tele span{width:2.1rem;text-align:right;display: inline-block;font-size: 0.34rem;vertical-align: middle;}
        .body .tele input{outline: none;border: 0.01rem solid #ffa42f;vertical-align: middle;height: 0.6rem;padding: 0 0.2rem;}
        .body .code{width:100%;text-align: center;line-height: 1rem;font-size: 0.3rem;margin-top: 0.4rem;}
        .body .code span{width:1.5rem;text-align:right;display: inline-block;font-size: 0.34rem;vertical-align: middle;line-height: 0.8rem;color: white}
        .body .code input{width:1.9rem;outline: none;border: 0.01rem solid #ffa42f;vertical-align: middle;height: 0.8rem;padding: 0 0.2rem;line-height: 0.8rem;text-align: center}
        .con3Con{width: 100%;text-align: center;}
        .con3Con .span1{text-align: center;  display: inline-block;  width: 1.3rem; background-color: #ffa42f;  vertical-align: middle; color: white;  line-height: 0.82rem;font-size: 0.25rem;}
        .con3Con .input1{text-align: center;  display: inline-block;  width: 3.4rem;  line-height: 0.79rem; border: 0.01rem solid #ffa42f;font-size: 0.25rem;}
        .body .code .send_code{display: inline-block; width:1.7rem;outline: none;border: 0.01rem solid #ffa42f;vertical-align: middle;height: 0.8rem;padding: 0 0.2rem; margin-left: 0.2rem;  font-size: 0.25rem;  line-height: 0.8rem;  background-color: #ccc;  color: slategray;}
        .body .code .send_code_Y{background-color: #ffa42f;color: white;}
        .body .code1{width:100%;text-align: center;line-height: 1rem;font-size: 0.3rem;margin-top: 0.3rem;}
        .body .code1 span{width:1.5rem;text-align:right;display: inline-block;font-size: 0.34rem;vertical-align: middle;line-height: 0.8rem;color: white}
        .body .code1 input{width:4.25rem;outline: none;border: 0.01rem solid #ffa42f;vertical-align: middle;height: 0.8rem;padding: 0 0.2rem;line-height: 0.8rem;text-align: center}
        .body .sub{width:100%;margin-top:1rem;}
        .body p{padding: 0.8rem 1rem;text-align: center;color:#fbfbfb;font-size: 0.26rem;}
        .body .sub span{display: block;width:4rem;line-height: 1rem;border-radius: 0.5rem;background-color: coral;color: white;text-align: center;margin: 0 auto;font-size: 0.4rem}
        .body .sub .span_Y{background-color: #ffa42f;}
        .m{display:none;width:60%;margin: 3rem 20% 0 20%;line-height: 2rem;text-align: center;font-size: 0.6rem;color:green;position: fixed;top:0;z-index: 50;    border: 0.05rem solid yellowgreen;  border-radius: 0.2rem;  background-color: white;}
    </style>
</head>
<body>
    <input type="hidden" id="userId" value="${userId}"/>
    <input type="hidden" id="photo" value="${photo}"/>
    <input type="hidden" id="userName" value="${userName}"/>
    <div class="body">
        <div class="photo"></div>
        <div class="name" >{{name}}</div>
        <div class="con3Con">
            <label style="width: 1.5rem;display: inline-block;text-align: right;font-size: 0.34rem;vertical-align: middle;color: white">手机号：</label>
            <span class="span1" id="sel_city">选择地区</span><input class="input1" v-model="tele" :disabled="disabled" type="text" placeholder="请输入手机号" id="phone">
        </div>
        <div class="code"><span>验证码：</span><input type="text" v-model="code" /><div class="send_code" v-on:click="time()" v-html="send_word" v-bind:class="{'send_code_Y':tele_T}"></div> </div>
        <div class="code1"><span>用户名：</span><input type="text" v-model="userName"/> </div>
        <div class="code1"><span>密&ensp;&ensp;码：</span><input type="password" v-model="password" id="password"/> </div>
        <div class="sub" v-on:click="sub(code_T&tele_T&btnState)"><span id="submitBtn" v-bind:class="{'span_Y':code_T&tele_T&btnState}">提交</span></div>
        <p>提交成功后<br/>用户<span style="color:orange">{{name}}</span>将成为您的推荐人</p>
    </div>
    <div class="m"></div>
</body>
<script type="text/javascript" src="${ctx}js/jquery.min.js"></script>
<script src="${ctx}js/vue.min.js"></script>
<script type="text/javascript">
    $(".body .photo").css("background-image","url(\'"+$("#photo").val()+"\')");// 给头像赋值
    var name = $("#userName").val();// 给用户名赋值
    var v1 = new Vue({
        el: '.body',
        data: {
            name: name,
            tele:"",
            disabled:false,
            code:"",
            send_word:"发送验证码",
            time_T:true,
            wait:60,
            password:"",
            userName:"",
            btnState:true
        },
        computed:{
            tele_T:function () {
                if(this.tele != null && this.tele != ""){
                    return true;
                }
            },
            code_T:function () {
                return this.code!=="";
            }
        },
        methods:{
            sub:function (a) {
                if (a){
                    // 提交表单
                    if($("#userId").val() == null || $("#userId").val() == ""){
                        alert("分享用户ID不能为空！");
                        return false;
                    }
                    if(this.tele == null || this.tele == ""){
                        alert("手机号不能为空！");
                        return false;
                    }
                    if(this.code == null || this.code == ""){
                        alert("验证码不能为空！");
                        return false;
                    }
                    if(this.password == null || this.password == ""){
                        alert("密码不能为空！");
                        return false;
                    }
                    if(this.userName == null || this.userName == ""){
                        alert("用户名不能为空！");
                        return false;
                    }
                    $("#submitBtn").text("提交中...");
                    this.btnState = false;
                    $.ajax({
                        type: "POST",
                        data:{
                            counttriesCode:$("#sel_city").text().substr(1,3),
                            phone:this.tele,
                            code:this.code,
                            password:this.password,
                            shareUserId:$("#userId").val(),
                            userName:this.userName
                        },
                        url: "${ctx}api/register/addShareUser",
                        dataType: "json",
                        success: function (msg) {
                            if(msg != null){
                                if(msg.data == 1){
                                    $("#submitBtn").text("提交");
                                    v1.btnState = true;
                                    alert("注册成功");
                                    login($("#phone").val());
                                }else{
                                    $("#submitBtn").text("提交");
                                    v1.btnState = true;
                                    alert(msg.message);
                                }
                            }else{
                                $("#submitBtn").text("提交");
                                v1.btnState = true;
                                alert("注册失败");
                            }
                        },
                        error:function(data){
                            $("#submitBtn").text("提交");
                            v1.btnState = true;
                            alert("系统异常，请稍后重试");
                        }
                    });
                }
            },
            time:function(o) {
                if(this.time_T){
                    this.time_T=false;
                    var reg_tele=/^1\d{10}$/;
                    if(this.tele_T){
                        $.ajax({
                            async: false,
                            type: "POST",
                            data:{
                                phone:this.tele,
                                countries_code:$("#sel_city").text().substr(1,3)
                            },
                            url: "${ctx}api/register/sendCode",
                            dataType: "json",
                            success: function (msg) {
                                if(msg != null){
                                    if(msg.message != "success"){
                                        alert("验证码发送失败");
                                    }
                                }else{
                                    alert("验证码发送失败");
                                }
                            },
                            error:function(data){
                                alert("系统异常，请稍后重试");
                            }
                        });
                        function t() {
                            if (v1.wait === 0) {
                                v1.disabled=false;
                                v1.time_T=true;
                                v1.send_word="发送验证码";
                                v1.wait = 60;
                            } else {
                                v1.disabled=true;
                                v1.send_word="重新发送("+v1.wait+")";
                                v1.wait--;
                                setTimeout(function(){t()},1000)
                            }
                        }
                        t();
                    }else {
                        this.time_T=true;
                    }
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

    function login(phoneNum) {
        window.location.href = "${ctx}wap/wx/registerAndLogin?phoneNum="+phoneNum;
    }
</script>
</html>