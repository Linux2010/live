jQuery(function ($) {
    init();
    // 初始化上传文件输入框
    $('#photoFile').ace_file_input({
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
    $('#phone').bind('input propertychange', function() {
        var userJsonData = checkPhoneIsExist($("#countryCode").val(),$(this).val());
        if(userJsonData &&  userJsonData >0){
            Modal.alert({msg: "该手机号，国际上已存在", title: "提示", btnok: "确定", btncl: "取消"});
            $(this).val("");
        }
    });

    //选择国际手机区号
    $(document).on("change","#tele_num",function(){
        var countryCode =  $(this).children('option:selected').val();
        $("#countryCode").val(countryCode);

        if( !$("#countryCode").val().equals(countryCode)){
            var userJsonData = checkPhoneIsExist(countryCode,$('#phone').val());
            if(userJsonData &&  userJsonData >0){
                Modal.alert({msg: "该手机号，国际上已存在", title: "提示", btnok: "确定", btncl: "取消"});
                $(this).val("");
            }
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
    $("#updateUser").click(function(){
        var data={};
        var $obj = $("#update-form-user");
        var userTypeId = $obj.find("input[name=userTypeId]").val();
        var realName = $obj.find("input[name=realName]").val();
        var userName = $obj.find("input[name=userName]").val();
        var nickName = $obj.find("input[name=nickName]").val();
        var phone = $obj.find("input[name=phone]").val();
        var loginName = $obj.find("input[name=loginName]").val();
      /*  var password = $obj.find("input[name=password]").val();*/
        var seqno = $obj.find("input[name=seqno]").val();
        var status = $obj.find("input[type=radio][name=status]:checked").val();
      //  var identity =$obj.find("#identity").find("option:selected").val(); //myselect.options[index].value;
        var userIntro =updateContainer.getContent();
      //  var userIntrText = updateContainer.getContentTxt().substr(0,60);
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
    /*      if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
            Modal.alert({msg: "手机号格式错误", title: "提示", btnok: "确定", btncl: "取消"});
            obj.value ="";
            return false;
        }*/
        if(!loginName){
            Modal.alert({msg: "讲师账号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
      /*  if(!password){
            Modal.alert({msg: "讲师密码必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }*/
        if( !/^[0-9]*$/.test(seqno)){
            $obj.find("input[name=seqno]").val("")
            Modal.alert({msg: "序列号非法数字，请重新输入", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
         }
        if(!seqno ){
            Modal.alert({msg: "序列号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        /*if(!userIntro){
            Modal.alert({msg: "讲师简介必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }*/
        if(!$("#photoUrl").val() && !$("#photoFile").val()){
            Modal.alert({msg: "头像必传", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }

        if(!$("#rectanglePhotoUrl").val() && !$("#rectanglePhoto").val()){
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

        $("#updateUser").attr("disabled","disabled");
        $("#updateUser").text("信息上传中...");
        data["userTypeId"]=userTypeId;
        data["realName"]=realName;
        data["userName"]=userName;
        data["nickName"]=nickName;
        data["phone"]=phone;
        data["loginName"]=loginName;
      /*  data["password"]=password;*/
        data["seqno"]=seqno;
        data["userIntro"]=userIntro;
        data["userType"]=$obj.find("input[name=userType]").val();
        data["status"] = status;
        data["identity"] = 2;
        data["userId"] = $obj.find("input[name=userId]").val();
        data["userIntrText"] =userIntroText;
        data["photo"] =$("#photoUrl").val();
        data["countryCode"] =countryCode;
        data = JSON.stringify(data);
        $("#data").val(data);

        $("#update-form-user").ajaxSubmit({
            type:"post",
            dataType:"json",
            url:base+"user/updateTeacherUser",
            success: function(data) {
                $("#updateUser").attr("disabled",false);
                $("#updateUser").text("信息上传中...");
                if(data==1){
                    // $("#updateModal-type").modal("hide");
                    // Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    location.href = base + 'user/teacher/';
                }else{
                    $("#updateUser").attr("disabled",false);
                    $("#updateUser").text("重新上传");
                    //   $("#updateModal-type").modal("hide");
                    Modal.alert({msg: "修改失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error:function(data){
                $("#updateUser").attr("disabled",false);
                $("#updateUser").text("重新上传");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
    });
});
function init(){
    var userId= $("input[name=userId][type=hidden]").val();
    var data = editInt(userId);
    if(data == null && data == undefined){
        Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
        return;
    }

    tele_num();//国际手机区号
    initUpdateForm(data);


}
function initUpdateForm(data){
    var $obj =$("#updateModal-type");
    //教师分类combotree默认选中
    $obj.find("input[name=realName]").val(data.real_name);
    $obj.find("input[name=userName]").val(data.user_name);
    $obj.find("input[name=nickName]").val(data.nick_name);
    $obj.find("input[name=phone]").val(data.phone);
    $obj.find("input[name=loginName]").val(data.login_name);
    $obj.find("input[name=password]").val(data.password);
    $obj.find("input[name=seqno]").val(data.seqno);
    $obj.find("input[name=userId]").val(data.user_id);
    $obj.find("#countryCode").val(data.country_code);
    $("#photoUrl").val(data.photo);
    var leftwords = 500;
    if(data.user_intro_text){
        var length = data.user_intro_text.length;
        leftwords = leftwords - length;
    }
    document.getElementById("word_count").innerHTML =data.user_intro_text?data.user_intro_text.length:0;
    document.getElementById("word").innerHTML = leftwords;
    var status =data.status;
    if(status == 0){
        $obj.find("input[name=status]").first().attr("checked",true);
        $obj.find("input[name=status]").last().attr("checked",false);
    }else{
        $obj.find("input[name=status]").first().attr("checked",false);
        $obj.find("input[name=status]").last().attr("checked",true);
    }
    var identity = data.user_identity;
    if(identity) $obj.find("select[name=identity]").find("option[value="+identity+"]").attr("selected","selected");
    var userIntro = data.user_intro;
    updateContainer.ready(function() {
        if(userIntro)  updateContainer.setContent(userIntro);
    });
    var countryCode = data.country_code;
    if(countryCode){
        $obj.find("select[name=tele_num]").find("option[value="+countryCode+"]").attr("selected","selected");
    }

   //初始化教师分类combotree树
    initTeacherTypeComboTree("#updateModal-type",data);

}

//查出对应的用户信息
function editInt(id) {
    var userJson;
    $.ajax({
        async: false,
        type: "POST",
        url: base + "user/selectUserByUserId",
        data:{userId:id},
        dataType: "json",
        success: function (data) {
            userJson = data;
        },
        error:function(data){
            userJson = data;
            Modal.alert({msg: "系统异常，请稍后再试!", title: "提示", btnok: "确定", btncl: "取消"});
        }
    });
    return userJson;
}


//初始化教师分类comboTree
function initTeacherTypeComboTree(idTag,userData){
    $(idTag).find('#userTypeId').combotree ({
        url: base + 'userType/searchUserTypeByParentId?parentId=-1',
        onBeforeExpand:function(node) {
            $('#userTypeId').combotree("tree").tree("options").url = base + 'userType/searchUserTypeByParentId?parentId=' + node.id;
        },
        onClick:function(node){
        },
        onLoadSuccess:function(node,data){
            // 初始化默认值
            setDefaultDbSet(idTag,userData.user_type_id,userData.type_name);
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


// 设置下拉树的默认值
function setDefaultDbSet(idTag,user_type_id, type_name) {
    // 设置默认值
    var t = $(idTag).find('#userTypeId').combotree('tree');
    var defNode = t.tree("find", user_type_id);
    if (!defNode) {
        t.tree('append', {
            data: [{
                id: user_type_id,
                text: type_name
            }]
        });
        defNode = t.tree("find", user_type_id);
        t.tree('select', defNode.target);
        defNode.target.style.display = 'none';
    }
    $(idTag).find('#userTypeId').combotree('setValue', user_type_id);
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
        $("#tele_num").append('<option value="'+val.international.replace("+","")+'">'+val.regionName+'</option>');
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