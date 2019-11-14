<!doctype html>
<html>
<head>
    <meta charset="utf-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>jQuery-webcam-js</title>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
</head>
<body>


<!-- JiaThis Button END -->
<#--<div id="nativeShare" style="border: 1px solid red"></div>-->

</body>
<script>
    $.ajax({
        async: false,
        type: "POST",
        url: "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=74_tn9oWNvgxeIFJo_OGuSPhsEr_IMLUBy2G_6OKD93vanLSXgCA1oB1zsxPEG6X355Nihz437ctseT-e2epV8-Ecq03oQ1FHn9oGXNQm8JdNxrh8OKUC1RSLhRDmK6JFBJbAAATPX",
        data:{ "touser":"oJLdx1EqeaE45_4UtBg8VGZ2BRsE",
            "template_id":"hCKBlNGW6t75G-FGdWt_FvmWG8oxjnkA2_36_P2sEwk",
            "url":"http://weixin.qq.com/download",
            "miniprogram":{
                "appid":"wx2f3027943ae38987",
                "pagepath":"index?foo=bar"
            },"data":{
                "first": {
                    "value":"恭喜你购买成功！",
                    "color":"#173177"
                },
                "keynote1":{
                    "value":"巧克力",
                    "color":"#173177"
                },
                "keynote2": {
                    "value":"39.8元",
                    "color":"#173177"
                },
                "keynote3": {
                    "value":"2014年9月22日",
                    "color":"#173177"
                },
                "remark":{
                    "value":"欢迎再次购买！",
                    "color":"#173177"
                }
            }
            },
        dataType: "json",
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            alert("系统异常，请稍后重试");
        }
    })
</script>
</html>