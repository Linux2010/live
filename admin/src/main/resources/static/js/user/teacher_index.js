
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var introUE;
var ue;
var updateContainer;
jQuery(function ($) {

    init();
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    //resize to fit page size
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', parent_column.width());
    });

    //resize on sidebar collapse/expand
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    });
    $("#find").on("click",function(){
        var keyword=$("#form-search").find("input[name=keyword]").val();
        var phone=$("#form-search").find("#phone").val();
        var identity =$("#form-search").find("#identity").find("option:selected").val(); //myselect.options[index].value;
         var type = '1';//类型 1：教头，2：用户
        var status  = $("#form-search").find("input[type='radio'][name='status']:checked").val();
        console.log(status1);
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword),"phone":phone,"status":status,"identity":identity,"type":"1"},
        }).trigger('reloadGrid');
    });

    jQuery(grid_selector).jqGrid({
        url:base+ "user/getTeacherUsersPage",
        subGrid: false,
        datatype: "json",
        postData:{"keyword":"","phone":"","status":"1","identity":"2","type":"1"},//type 类型 1：教头，2：用户// user_identity用户身份1:普通用户2：会员用户 //status 0：禁用账号;1:启用账号
        height: 'auto',
        colNames: ['id', '讲师姓名','联系方式','讲师分类','开课数','讲师账号','讲师头像',"简介图片",'详细介绍','注册时间','操作'],
        colModel:[
            {name: 'userId', index: 'userId', width: 50, editable: true, hidden: true, key: true},
            {name: 'userName', index: 'userName', width: 100},
            {name: 'phone', index: 'phone', width: 100},
            {name: 'userTypeName', index: 'userTypeName', width: 100},
            {name: 'courseNumber', index:  'courseNumber', width: 100,formatter:function (cellvalue, options, rowObject){
                if(rowObject.courseList){
                    return rowObject.courseList.length;
                }else{
                    return 0;
                }
            }},
            {name: 'loginName', index: 'loginName', width: 100},
            {name:'photo',index:'photo', width:100, formatter:typeImg},
            {name: 'intro_img', index: 'intro_img', width: 100,
                formatter: function(cellvalue, options, rowObject) {
                    return '<a title="上传简介图片" href="javascript:void(0);" ' +
                        'onclick="intro_img(\'' + rowObject.userId + '\')" > ' +
                        '<i class="ace-icon fa fa-cloud-upload bigger-200"-></i></a>';
                }
            },
            {name: 'userIntro', index: 'userIntro', width: 100,
                formatter: function(cellvalue, options, rowObject) {
                    return '<a title="查看详细简介" href="javascript:void(0);" ' +
                        'onclick="intro(\'' + rowObject.userId + '\')" > ' +
                        '<i class="ace-icon fa fa-search-plus bigger-200"-></i></a>';
                }
            },
            {name: 'createTime', index: 'createTime', width: 150,formatter:function (cellvalue, options, rowObject){
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }},
            {name:'id',index:'id', width:90, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                   return '<a title="编辑" href="javascript:void(0);" ' +
                    'onclick="udpateTeacher(\'' + rowObject.userId + '\')" > ' +
                    '<i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>'+
                    '&nbsp;&nbsp;&nbsp;'+
                    '<a title="删除" href="javascript:void(0);" ' +
                    'onclick="del(\'' + rowObject.userId + '\')" > ' +
                    '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
                }
            }
        ],
        jsonReader: {
            root: "list",
            page: "pageNum",
            total: "pages",
            records: "total"
        },
        pgtext: "第 {0} 页，共 {1}页",
        recordtext: "第 {0} 到 {1} 共 {2} 条",
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        //toppager: true,
        multiselect: true,
        //multikey: "ctrlKey",
        multiboxonly: true,

        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },

        caption: "",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:0px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:0px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            editfunc : udpateTeacher,
            edittext: '修改',
            edititle:"编辑讲师用户",
            add: true,
            addtext: '添加',
            addtitle: "添加讲师用户",
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc : addTeacher,
            del: true,
            deltext:"删除",
            deltitle:"删除讲师用户",
            delfunc : del,
            search: false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh yellow',
            alertcap:'提示',
            alerttext: '请选择一条记录'
        }
    )

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        });
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

    $(document).one('ajaxloadstart.page', function (e) {
        $.jgrid.gridDestroy(grid_selector);
        $('.ui-jqdialog').remove();
    });


    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));


    $("#adduser").click(function(){
        var data={};
        var $obj = $("#add-form-user");
        var userTypeId = $obj.find("input[name=userTypeId]").val();
        var userName = $obj.find("input[name=userName]").val();
        var phone = $obj.find("input[name=phone]").val();
        var loginName = $obj.find("input[name=loginName]").val();
        var password = $obj.find("input[name=password]").val();
        var seqno = $obj.find("input[name=seqno]").val();
        var status = $obj.find("input[type=radio]:checked").val();
        var identity =$obj.find("#identity").find("option:selected").val(); //myselect.options[index].value;
        var userIntro =ue.getContent();
       if(!userTypeId){
           Modal.alert({msg: "请选择讲师分类", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
       if(!userName){
           Modal.alert({msg: "讲师姓名必填", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
       if(!phone){
            Modal.alert({msg: "讲师电话必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
       if(!loginName){
            Modal.alert({msg: "讲师账号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
       if(!password){
            Modal.alert({msg: "讲师密码必填", title: "提示", btnok: "确定", btncl: "取消"});
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
        data["userTypeId"]=userTypeId;
        data["userName"]=userName;
        data["phone"]=phone;
        data["loginName"]=loginName;
        data["password"]=password;
        data["seqno"]=seqno;
        data["userIntro"]=userIntro;
        data["userType"]=$obj.find("input[name=userType]").val();
        data["status"] = status;
        data["identity"] = identity;

        data = JSON.stringify(data);
       $.ajax({
            async: false,
            url: base + "user/insertTeacherUser",
            dataType: "json",
           type:"POST",
            data: {"data":data},
            success: function (data) {
                if(data==1){
                    $("#addModal-type").modal("hide");
                    Modal.alert({msg: "添加成功！", title: "提示", btnok: "确定", btncl: "取消"});
                }else{
                    $("#addModal-type").modal("hide");
                    Modal.alert({msg: "添加失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
                jQuery(grid_selector).trigger("reloadGrid");
            },
            error: function (data) {
               alert("系统异常，请稍后再试!");
            }
        });

    });

    $("#updateUser").click(function(){
        var data={};
        var $obj = $("#update-form-user");
        var userTypeId = $obj.find("input[name=userTypeId]").val();
        var userName = $obj.find("input[name=userName]").val();
        var phone = $obj.find("input[name=phone]").val();
        var loginName = $obj.find("input[name=loginName]").val();
        var password = $obj.find("input[name=password]").val();
        var seqno = $obj.find("input[name=seqno]").val();
        var status = $obj.find("input[type=radio][name=status]:checked").val();
        var identity =$obj.find("#identity").find("option:selected").val(); //myselect.options[index].value;
        var userIntro =updateContainer.getContent();
        alert("userIntro length: " +userIntro.length);
        if(!userTypeId){
            Modal.alert({msg: "请选择讲师分类", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if(!userName){
            Modal.alert({msg: "讲师姓名必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if(!phone){
            Modal.alert({msg: "讲师电话必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if(!loginName){
            Modal.alert({msg: "讲师账号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if(!password){
            Modal.alert({msg: "讲师密码必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if(!seqno){
            Modal.alert({msg: "序列号必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        /*if(!userIntro){
            Modal.alert({msg: "讲师简介必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }*/

        data["userTypeId"]=userTypeId;
        data["userName"]=userName;
        data["phone"]=phone;
        data["loginName"]=loginName;
        data["password"]=password;
        data["seqno"]=seqno;
        data["userIntro"]=userIntro;
        data["userType"]=$obj.find("input[name=userType]").val();
        data["status"] = status;
        data["identity"] = identity;
        data["userId"] = $obj.find("input[name=userId]").val();

        data = JSON.stringify(data);
        $.ajax({
            async: false,
            url: base + "user/updateTeacherUser",
            dataType: "json",
            type:"POST",
            data: {"data":data},
            success: function (data) {
                if(data==1){
                    $("#updateModal-type").modal("hide");
                    Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                }else{
                    $("#updateModal-type").modal("hide");
                    Modal.alert({msg: "修改失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
                jQuery(grid_selector).trigger("reloadGrid");
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });

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
        }   ,
        onLoadSuccess:function(node,data){
        }
    });
}

function add(){
    //重置form表单
    $("#addModal-type").find("form")[0].reset();
    //重置UE
    ue.setContent('');
    $("#addModal-type").modal("show");//打开模态窗口
    initTeacherTypeComboTree( "#addModal-type");
}

function edit(id){
    var ids=$(grid_selector).jqGrid('getGridParam', "selarrrow");
    if(ids){
        if(ids.length>1){
            Modal.alert({msg: "请选择一条数据进行编辑", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
    }
   // var rowData =   $(grid_selector).jqGrid('getRowData', id);
    //清空表单
    //重置form表单
    $("#updateModal-type").find("form")[0].reset();
    //重置UE
    updateContainer.setContent('');

    var data = editInt(id);
    if(data == null && data == undefined){
        Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
        return;
    }

    $("#updateModal-type").modal("show");
    initUpdateForm(data);
    initTeacherTypeComboTree( "#updateModal-type");
}
function initUpdateForm(data){
    var $obj =$("#updateModal-type");
    //教师分类combotree默认选中
    $obj.find("input[name=userName]").val(data.user_name);
    $obj.find("input[name=phone]").val(data.phone);
    $obj.find("input[name=loginName]").val(data.login_name);
    $obj.find("input[name=password]").val(data.password);
    $obj.find("input[name=seqno]").val(data.seqno);
    $obj.find("input[name=userId]").val(data.user_id);
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
    if(userIntro)  updateContainer.setContent(userIntro);
}

// 根据用户ID查询发布课程的数量
function searchMyCourseCount(id){
    var count=0;
    $.ajax({
        async: false,
        url: base + "user/searchMyCourseCount",
        dataType: "json",
        data: {"userId":id},
        type:"GET",
        success: function (data) {
            count = data;
        },
        error: function (data) {
            count = data;
        }
    });
    return count;
}

function del(ids){
    if(ids) {
        ids = ids.toString().split(",");
        if(ids.length > 1){
            Modal.alert({msg: "请选择一个讲师进行删除！", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
    }else{
        Modal.alert({msg: "至少选择一个用户！", title: "提示", btnok: "确定", btncl: "取消"});
        return false;
    }
    var  fbCourseNumber = searchMyCourseCount(ids[0]);
    var  confirmStr = "确认删除吗？";
    if(fbCourseNumber > 0){

        Modal.alert({msg: "该讲师已发布了"+fbCourseNumber+"个课程，删除该课程之后，才能删除该讲师", title: "提示", btnok: "确定", btncl: "取消"});
        return false;
    }
    Modal.confirm({msg:"确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，执行删除操作
                $.ajax({
                    async: false,
                    url: base + "user/delUserByUserId",
                    dataType: "json",
                    data: {"userId":ids[0]},
                    success: function (data) {
                        if(data==1){
                            Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定", btncl: "取消"});
                        }else{
                            Modal.alert({msg: "删除失败！", title: "提示", btnok: "确定", btncl: "取消"});
                        }
                    }
                });
               jQuery(grid_selector).trigger("reloadGrid");

            }
    });
}
function intro(id){
    var data = editInt(id);
    if(data == null && data == undefined){
        Modal.alert({msg: "数据请求失败！", title: "提示", btnok: "确定", btncl: "取消"});
        return;
    }
    $("#introModal-type").modal("show");
    var userIntro = data.user_intro;
    if(userIntro) $("#introId").html(userIntro);
        //introUE.setContent(userIntro);
}

//教头简介图片
function  intro_img(id) {
  location.href=base+"user/teacher_intro_img_index?userId="+id;
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

function resetpwd(id){
    var se_id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    var r = confirm("确定要重置此用户的登录密码吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "/user/resetPwd",
            data: {'id': id.toString()},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    alert("重置成功!");
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });
    } else {
        alert("您取消了此次操作!");
    }
}


function userend(id){
    var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');
    var r = confirm("确定要禁用此用户吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "/user/userend",
            data: {'id': sel_id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    alert("此用户已被禁用!");
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });
    }
    else {
        alert("您取消了此次操作!");
    }
}
function userstart(id){
    var id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');
    var r = confirm("确定要启用此用户吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "/user/userstart",
            data: {'id': sel_id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    alert("此用户已被启用!");
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });
    }
    else {
        alert("您取消了此次操作!");
    }
}

function resetpay(id){
    var id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    var r=confirm("确定要重置此用户的支付密码吗？");
    if (r==true) {
        $.ajax({
            async:false,
            type: "POST",
            url: base + "user/resetPay",
            data:{'id':id},
            dataType: "json",
            success:function(data){
                if(data == 1){
                    alert("重置成功!");
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error:function(data){
                alert("系统异常，请稍后再试!");
            }
        });
    }else
    {
        alert("您取消了此次操作!");
    }

}
//<#--判斷類型-->
var userType = function(v){
    if (v == 1) {
        return "<span class=\"label label-info arrowed-in-right arrowed\">教头</span>";
    } else if (v == 2) {
        return "<span class=\"label label-success arrowed\">用户</span>";
    }
}
var userIdentity = function(v){
    if (v == 1) {
        return "<span class=\"btn btn-white btn-danger btn-sm\">普通用户</span>";
    } else if (v == 2) {
        return "<span class=\"btn btn-white btn-inverse btn-sm\">会员用户</span>";
    }
}
var labelname = function(v){
    return "<span class=\"label label-lg label-yellow arrowed-in arrowed-in-right\">"+v+"</span>";
}
var stuname = function(v){
    return "<span class=\"label label-green arrowed-in\">"+v+"</span>";
}

var userstatus = function(v){
    var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');
    if (v == 1) {
        return "<input id='id-check-horizontal' type='checkbox' class='ace ace-switch ace-switch-6::after' checked='checked' onclick='userend("+sel_id+")'/> <span class='lbl middle'></span>";
    } else if (v == 0) {
        return "<input id='id-check-horizontal1' type='checkbox' class='ace ace-switch ace-switch-6::before'  onclick='userstart("+sel_id+")'/> <span class='lbl middle'></span>";
    }
}
//   <#--校验教头-->
function checktype(id){
    var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');
    $.ajax({
        async:false,
        type: "POST",
        url: base + "/user/checktype",
        data:{'id':sel_id},
        dataType: "json",
        success:function(data){
            console.log(data);
        },
        error:function(data){
            alert("系统异常，请稍后再试!");
        }
    });
}


// 跳转到添加教师也没
function addTeacher(){
    var urlVal = base + "user/teacher/addTeacher";
   // location.href=urlVal;
    window.open(urlVal,"_self");
}
// 跳转到添加教师也没
function udpateTeacher(id){
    var ids=$(grid_selector).jqGrid('getGridParam', "selarrrow");
    if(ids){
        if(ids.length>1){
            Modal.alert({msg: "请选择一条数据进行编辑", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
    }
    var urlVal = base + "user/teacher/updateTeacher?userId="+id;
   // location.href=urlVal;
    window.open(urlVal,"_self");
}


// 展示状态的值
function typeImg(cellvalue, options, rowObject){
    if(cellvalue)
    return   '<a title="点击查看大图" href="javascript:void(0);" ' +
        'onclick="showImg(\'' +cellvalue + '\')" ><img src="' +cellvalue + '" style="width:50px;height: 30px;" alt="点击查看大图"> ' +
        '</img></a>';
    else
        return "无头像";
}


// 显示课程分类图片
function showImg(userTypePicture){
    $("#imgModal-type").modal("show");// 打开模态窗口
    $("#imgModal-type").find("#img").attr("src",userTypePicture);

}
function init(){
    ue = UE.getEditor('container', {
        toolbars: [
            [
                'anchor', //锚点
                'undo', //撤销
                'redo', //重做
                'bold', //加粗
                'indent', //首行缩进
                //  'snapscreen', //截图
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
                // 'unlink', //取消链接
                // 'insertrow', //前插入行
                // 'insertcol', //前插入列
                // 'mergeright', //右合并单元格
                // 'mergedown', //下合并单元格
                // 'deleterow', //删除行
                // 'deletecol', //删除列
                // 'splittorows', //拆分成行
                // 'splittocols', //拆分成列
                // 'splittocells', //完全拆分单元格
                // 'deletecaption', //删除表格标题
                // 'inserttitle', //插入标题
                // 'mergecells', //合并多个单元格
                // 'deletetable', //删除表格
                // 'cleardoc', //清空文档
                // 'insertparagraphbeforetable', //"表格前插入行"
                'insertcode', //代码语言
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                // 'edittable', //表格属性
                // 'edittd', //单元格属性
                // 'link', //超链接
                // 'emotion', //表情
                // 'spechars', //特殊字符
                // 'searchreplace', //查询替换
                // 'insertvideo', //视频
                // 'help', //帮助
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
                // // 'insertframe', //插入Iframe
                // // 'imagenone', //默认
                // // 'imageleft', //左浮动
                // // 'imageright', //右浮动
                // // 'attachment', //附件
                // // 'imagecenter', //居中
                // // 'wordimage', //图片转存
                // 'lineheight', //行间距
                // 'edittip ', //编辑提示
                // 'customstyle', //自定义标题
                // 'autotypeset', //自动排版
                // 'touppercase', //字母大写
                // 'tolowercase', //字母小写
                // 'background', //背景
                // 'template', //模板
                // 'scrawl', //涂鸦
                // 'music', //音乐
                // 'inserttable', //插入表格
                // 'drafts', // 从草稿箱加载
                // 'charts', // 图表
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

    updateContainer = UE.getEditor('updateContainer', {
        toolbars: [
            [

                'anchor', //锚点
                'undo', //撤销
                'redo', //重做
                'bold', //加粗
                'indent', //首行缩进
                //  'snapscreen', //截图
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
                // 'unlink', //取消链接
                // 'insertrow', //前插入行
                // 'insertcol', //前插入列
                // 'mergeright', //右合并单元格
                // 'mergedown', //下合并单元格
                // 'deleterow', //删除行
                // 'deletecol', //删除列
                // 'splittorows', //拆分成行
                // 'splittocols', //拆分成列
                // 'splittocells', //完全拆分单元格
                // 'deletecaption', //删除表格标题
                // 'inserttitle', //插入标题
                // 'mergecells', //合并多个单元格
                // 'deletetable', //删除表格
                // 'cleardoc', //清空文档
                // 'insertparagraphbeforetable', //"表格前插入行"
                'insertcode', //代码语言
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                // 'edittable', //表格属性
                // 'edittd', //单元格属性
                // 'link', //超链接
                // 'emotion', //表情
                // 'spechars', //特殊字符
                // 'searchreplace', //查询替换
                // 'insertvideo', //视频
                // 'help', //帮助
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
                // // 'insertframe', //插入Iframe
                // // 'imagenone', //默认
                // // 'imageleft', //左浮动
                // // 'imageright', //右浮动
                // // 'attachment', //附件
                // // 'imagecenter', //居中
                // // 'wordimage', //图片转存
                // 'lineheight', //行间距
                // 'edittip ', //编辑提示
                // 'customstyle', //自定义标题
                // 'autotypeset', //自动排版
                // 'touppercase', //字母大写
                // 'tolowercase', //字母小写
                // 'background', //背景
                // 'template', //模板
                // 'scrawl', //涂鸦
                // 'music', //音乐
                // 'inserttable', //插入表格
                // 'drafts', // 从草稿箱加载
                // 'charts', // 图表
            ]
        ],
        autoHeightEnabled: true,
        autoFloatEnabled: false, //focus时自动清空初始化时的内容
        autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //默认的编辑区域高度
        initialFrameHeight:900});

    introUE = UE.getEditor('userIntroContainer', {
        toolbars:[[]],
        autoHeightEnabled: true,
        autoFloatEnabled: true
    });
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
}