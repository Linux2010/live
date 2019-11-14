<!DOCTYPE html>
<html lang="en" xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no"/>
    <title>教头学院</title>
    <link href="${ctx}css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}css/getpwd.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}js/conform.js"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}js/vue.min.js" type="text/javascript"></script>
    <#--<script src="${ctx}js/tele86.js"></script>-->
    <script src="${ctx}js/picker.min.js"></script>
    <script src="${ctx}js/base.js"></script>
</head>
<body>
<div class="con1">
    <span style="width: 20%;display: inline-block;"><a href="${ctx}wap/wx/login"><</a></span><span
        style="width: 60%;display: inline-block;margin: 0 auto;text-align: center;">找回支付密码</span>
</div>
<#--<div class="con2">
    <div class="con2Select">
        <span  class="active">手机号找回</span>
    </div>
</div>-->
<div class="con3">
    <div class="con3Con">
        <span class="span1" id="sel_city" style="width: 1.3rem">选择地区</span><input class="input1" type="text"
                                                                                  v-model="telVal"
                                                                                  :disabled="telDisabled"
                                                                                  placeholder="请输入手机号"
                                                                                  style="width: 4.2rem">
    </div>
    <div class="con3Con">
        <input class="input2" type="text" v-model="codeVal" placeholder="输入验证码"><span class="span2" v-html="codeImgVal"
                                                                                      v-bind:class="{inputcodeimg:telStates&codeImgValStates}"
                                                                                      v-on:click="getCode($event)"></span>
    </div>
    <div class="con3Con">
        <input type="button" v-model="registerVal" v-bind:class="{submit:telStates&codeStates}"
               v-on:click="sub(telStates&codeStates)">
    </div>
    <form style="display: none;" id="findpwd_from" action="${ctx}wap/user/findPayPwdToSecond" method="post">
        <input type="hidden" id="phone" name="phone"/>
        <input type="hidden" id="code" name="code"/>
        <input type="hidden" id="coun_code" name="coun_code"/>
    </form>
</div>
</body>
<script>
    var v1 = new Vue({
        el: '.con3',
        data: {
            telVal: '',
            telDisabled: false,
            codeVal: '',
            codeImgVal: '获取验证码',
            codeImgValStates: true,
            usernameVal: '',
            passwordVal: '',
            truePasswordVal: '',
            registerVal: '下一步',
            wait: 60,
            message: 'qwle'
        },
        computed: {
            telStates: function () {
                return this.telVal !== "";
            },
            codeStates: function () {
                return this.codeVal !== "";
            },
            usernameStates: function () {
                return this.usernameVal !== "";
            },
            passwordValStates: function () {
                return this.passwordVal !== "";
            },
            truePasswordValStates: function () {
                return this.truePasswordVal !== "" && (this.passwordVal === this.truePasswordVal);
            }
        },
        methods: {
            getCode: function (e) {
                var a = e.currentTarget;
                var coun_ = $("#sel_city").text();
                if (coun_ != "选择地区") {
                    if (this.wait === 60 && this.telStates) {
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: base + "user/send_code",
                            data: {'countries_code': $("#sel_city").text().substr(1, 3), 'phone': $(".input1").val()},
                            dataType: "json",
                            success: function (data) {
                                if (data.result == 1) {
                                    alert("验证码发送成功！");
                                }
                            },
                            error: function (data) {
                                alert("系统异常，请稍后重试");
                            }
                        })
                        this.codeImgValStates = false;
                        this.telDisabled = true;
                        time(a);
                    }
                } else {
                    alert("请选择地区!");
                    return;
                }
                function time(a) {
                    if (v1.wait === 0) {
                        v1.codeImgValStates = true;          //toyellow
                        v1.telDisabled = false;              //toyellow
                        v1.codeImgVal = "发送验证码";
                        v1.wait = 60;
                    } else {
                        v1.codeImgVal = "重新发送(" + v1.wait + ")";
                        v1.wait--;
                        setTimeout(function () {
                            time(a)
                        }, 1000)
                    }
                }//time
            },
            sub: function (a) {
                if (a) {
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: base + "user/checkCode",
                        data: {'code': $(".input2").val(), 'phone': $(".input1").val()},
                        dataType: "json",
                        success: function (data) {
                            if (data == 1) {
                                // window.location.href="wap/user/findPwdToSecond?phone="+$(".input1").val()+"&code="+$(".input2").val()+"&coun_code="+$("#sel_city").text().substr(1,3);
                                $("#phone").val($(".input1").val());
                                $("#code").val($(".input2").val());
                                $("#coun_code").val($("#sel_city").text().substr(1, 3));
                                $("#findpwd_from").submit();
                            } else {
                                alert("您输入的验证码不正确!");
                            }
                        },
                        error: function (data) {
                            alert("系统异常，请稍后重试");
                        }
                    })
                }
            }
        }
    })
</script>
<script>
    var con2Select = $(".con2 .con2Select span");
    con2Select.click(function () {
        con2Select.removeClass("active");
        $(this).addClass("active")
    })
</script>
<script>
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
    function creatList(obj, list) {
        obj.forEach(function (item, index, arr) {
            var temp = new Object();
            temp.text = item.name;
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
        selectedIndex: [0],
        title: '地址选择'
    });
    picker.on('picker.select', function (selectedVal, selectedIndex) {
        text = data[selectedIndex[0]].num;
        nameEl.innerText = text;
    });
    nameEl.addEventListener('click', function () {
        if (jsonStr.length < 1){
            alert("未设置地区区号");
        }
        picker.show();
    });

</script>
</html>
