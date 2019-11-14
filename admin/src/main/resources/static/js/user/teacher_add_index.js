var ue;
jQuery(function ($) {
    ue = UE.getEditor('container', {
        toolbars: [
            [
                'anchor', //锚点
                'undo', //撤销
                'redo', //重做
                'bold', //加粗
                'indent', //首行缩进
                'italic', //斜体
                'underline', //下划线
                'strikethrough', //删除线
                'subscript', //下标
                'fontborder', //字符边框
                'superscript', //上标
                'formatmatch', //格式刷
                'source', //源代码
                'blockquote', //引用
                'pasteplain', //纯文本粘贴模式
                'selectall', //全选
                'print', //打印
                'preview', //预览
                'horizontal', //分隔线
                'removeformat', //清除格式
                'time', //时间
                'date', //日期
                'insertcode', //代码语言
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对齐
                'justifyjustify', //两端对齐
                'forecolor', //字体颜色
                'backcolor', //背景色
                'insertorderedlist', //有序列表
                'insertunorderedlist', //无序列表
                'fullscreen', //全屏
                'directionalityltr', //从左向右输入
                'directionalityrtl', //从右向左输入
                'rowspacingtop', //段前距
                'rowspacingbottom', //段后距
                'pagebreak' //分页
            ]
        ],
    autoHeightEnabled: true,
    autoFloatEnabled: false,
    //focus时自动清空初始化时的内容
    autoClearinitialContent:true,
    //关闭字数统计
    wordCount:false,
    //关闭elementPath
    elementPathEnabled:false,
    //默认的编辑区域高度
    initialFrameHeight:900});
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'catchimage') {
            return base+'UEditor/uploadImg';
        } else if (action == 'uploadvideo') {
            return 'http://a.b.com/video.php';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

    // 初始化上传文件输入框
    $('#photo').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 初始化上传文件输入框
    $('#rectanglePhoto').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });
    $('#loginName').bind('input propertychange', function() {
        var userJsonData = getUserByloginNam($(this).val())
        if(userJsonData &&  userJsonData >0){
            Modal.alert({msg: "该登陆名已存在", title: "提示", btnok: "确定", btncl: "取消"});
            $(this).val("");
        }
    });

    $('#phone').bind('input propertychange', function() {
        var userJsonData = checkPhoneIsExist($("#countryCode").val(),$(this).val());
        if(userJsonData &&  userJsonData >0){
            Modal.alert({msg: "该手机号，国际上已存在", title: "提示", btnok: "确定", btncl: "取消"});
            $(this).val("");
        }
    });
    tele_num();//国际手机区号

    //选择国际手机区号
    $(document).on("change","#tele_num",function(){
        var countryCode =  $(this).children('option:selected').val();
        $("#countryCode").val(countryCode);
        var userNum = checkPhoneIsExist(countryCode,$('#phone').val());
        if(userNum &&  userNum >0){
            Modal.alert({msg: "该手机号，国际上已存在", title: "提示", btnok: "确定", btncl: "取消"});
            $(this).val("");
        }
    });
    function checkPhoneIsExist(countryCode,phone){
        var userJsonData;
        $.ajax({
            async: false,
            url: base + "user/checkPhoneIsExist",
            dataType: "json",
            type:"POST",
            data: {"countryCode":countryCode,"phone":phone},
            success: function (data) {
                userJsonData = data;
            },
            error: function (data) {
                userJsonData = data;
            }
        });
        return userJsonData;
    }

    function getUserByloginNam(loginName){
        var userJsonData;
        $.ajax({
            async: false,
            url: base + "user/checkLoginName",
            dataType: "json",
            type:"POST",
            data: {"loginName":loginName},
            success: function (data) {
                userJsonData = data;
            },
            error: function (data) {
                userJsonData = data;
            }
        });
        return userJsonData;
    }
    initTeacherTypeComboTree("#addModal-type");
    $("#adduser").click(function(){
        var data={};
        var $obj = $("#add-form-user");
        var userTypeId = $obj.find("input[name=userTypeId]").val();
        var realName = $obj.find("input[name=realName]").val();
        var userName = $obj.find("input[name=userName]").val();
        var nickName = $obj.find("input[name=nickName]").val();
        var phone = $obj.find("input[name=phone]").val();
        var loginName = $obj.find("input[name=loginName]").val();
        var password = $obj.find("input[name=password]").val();
        var seqno = $obj.find("input[name=seqno]").val();
        var status = $obj.find("input[type=radio]:checked").val();
        //var identity =$obj.find("#identity").find("option:selected").val(); //myselect.options[index].value;
        var userIntro =ue.getContent();
        //var userIntrText = ue.getContentTxt().substr(0,60);
        var userIntroText = $("#userIntroText").val();
        var countryCode =$("#countryCode").val();
       if(!userTypeId){
           Modal.alert({msg: "请选择讲师分类", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
        if(!realName){
            Modal.alert({msg: "真实姓名必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
       if(!userName){
           Modal.alert({msg: "讲师姓名必填", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
       if(!nickName){
            Modal.alert({msg: "讲师姓名必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
       if(!phone){
            Modal.alert({msg: "讲师电话必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
      /* if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
            Modal.alert({msg: "手机号格式错误", title: "提示", btnok: "确定", btncl: "取消"});
            obj.value ="";
            return false;
        }*/
       if(!loginName){
            Modal.alert({msg: "讲师账号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
       if(!password){
            Modal.alert({msg: "讲师密码必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
        if( !/^[0-9]*$/.test(seqno)){
            $obj.find("input[name=seqno]").val("")
            Modal.alert({msg: "序列号非法数字，请重新输入", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
       if(!seqno){
            Modal.alert({msg: "序列号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
       /* if(!userIntro){
            Modal.alert({msg: "讲师简介必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
*/
       if(!$("#photo").val()){
           Modal.alert({msg: "头像必传", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
        if(!$("#rectanglePhoto").val()){
            Modal.alert({msg: "名优教头图片必传", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
       if(!countryCode){
           Modal.alert({msg: "国际区号必选", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }

        if(!userIntroText){
            Modal.alert({msg: "教师简介必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }

        $("#adduser").attr("disabled","disabled");
        $("#adduser").text("信息上传中...");
        data["userTypeId"]=userTypeId;
        data["realName"]=realName;
        data["userName"]=userName;
        data["nickName"]=nickName;
        data["phone"]=phone;
        data["loginName"]=loginName;
        data["password"]=password;
        data["seqno"]=seqno;
        data["userIntro"]=userIntro;
        data["userType"]=$obj.find("input[name=userType]").val();
        data["status"] = status;
        data["identity"] = 2;
        data["userIntrText"] =userIntroText;
        data["countryCode"] =countryCode;

        data = JSON.stringify(data);
        $("#data").val(data);

        $("#add-form-user").ajaxSubmit({
            type:"post",
            dataType:"json",
            url:base+"user/insertTeacherUser",
            success: function(data) {
                $("#adduser").attr("disabled",true);
                $("#adduser").text("信息上传中...");
                if(data==1){
                    // $("#addModal-type").modal("hide");
                    //  Modal.alert({msg: "添加成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    location.href = base + 'user/teacher/';
                }else{
                    $("#adduser").attr("disabled",false);
                    $("#adduser").text("重新上传");
                    //$("#addModal-type").modal("hide");
                    Modal.alert({msg: "添加失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error:function(data){
                $("#adduser").attr("disabled",false);
                $("#adduser").text("重新上传");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
     /*   data = JSON.stringify(data);
       $.ajax({
            async: false,
            url: base + "user/insertTeacherUser",
            dataType: "json",
           type:"POST",
            data: {"data":data},
            success: function (data) {
                if(data==1){
                  // $("#addModal-type").modal("hide");
                  //  Modal.alert({msg: "添加成功！", title: "提示", btnok: "确定", btncl: "取消"});
                     location.href = base + 'user/teacher/';
                }else{
                    //$("#addModal-type").modal("hide");
                    Modal.alert({msg: "添加失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
               alert("系统异常，请稍后再试!");
            }
        });*/

    });
});

//初始化教师分类comboTree
function initTeacherTypeComboTree(idTag){
    $(idTag).find('#userTypeId').combotree ({
        url: base + 'userType/searchUserTypeByParentId?parentId=-1',
        onBeforeExpand:function(node) {
            $('#userTypeId').combotree("tree").tree("options").url = base + 'userType/searchUserTypeByParentId?parentId=' + node.id;
        },
        onClick:function(node){
        }, //选择树节点触发事件
        onSelect : function(node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                Modal.alert({msg: "请选择二级分类", title: "提示", btnok: "确定", btncl: "取消"});
                //清除选中
                $(idTag).find('#userTypeId').combotree('clear');
                return false;
            }
        }
});
}
function tele_num(){
    var codeList;
    $.ajax({
        async: false,
        url: base + "user/get_countries_code_list",
        dataType: "json",
        type:"GET",
        success: function (data) {
            codeList = data;
        },
        error: function (data) {
            codeList = data;
        }
    });
    $.each(codeList,function(i,val){
        var selected = "";
        if(val.regionName === '中国'){
            selected ="selected=\"selected\"";
            $("#countryCode").val(val.international);
        }
        $("#tele_num").append('<option value="'+val.international.replace("+","")+'" '+ selected+' >'+val.regionName+'</option>');
    });
}

function checkWordNum(obj) {
    var maxLength = 500;
    if (obj.value.length > maxLength) {
        obj.value = obj.value.substring(0, maxLength);
    } else {
        var leftwords = maxLength - obj.value.length;
        document.getElementById("word_count").innerHTML = obj.value.length;
        document.getElementById("word").innerHTML = leftwords;
    }
}

function checkPhone(obj){
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(obj.value))){
        Modal.alert({msg: "手机号格式错误", title: "提示", btnok: "确定", btncl: "取消"});
        obj.value ="";
        return false;
    }
}
