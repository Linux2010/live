jQuery(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


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
        query_condition();
    });
    function query_condition() {
        var keyword=document.getElementById("nav-search-input").value;
        var phone=document.getElementById("nav-search-input1").value;
        var  myselect=document.getElementById("identity");
        var index=myselect.selectedIndex;
        var identity = myselect.options[index].value;
        var  typeselect=document.getElementById("type");
        var typeindex=typeselect.selectedIndex;
        var type = typeselect.options[typeindex].value;
        var status1  = $("input[type='radio']:checked").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword),"phone":phone,"status":status1,"identity":identity,"type":type,"couponId":$("input[name=couponId]").val()},
        }).trigger('reloadGrid');
    }

    jQuery(grid_selector).jqGrid({
        //direction: "rtl",

        url: base+"user/getCouponUserPageForeach",
        subGrid: false,
        datatype: "json",
        postData:{"status":$("input[type='radio']:checked").val(),"couponId":$("input[name=couponId]").val()},
        height:  'auto',
        colNames: ['id', '用户名称','发放状态','是否发放','是否认领','手机号','邮箱','地区','积分','银两','用户类型','用户等级','注册时间','详情信息','账号状态','操作'],
        colModel: [
            {name: 'userId', index: 'userId', width: 200, editable: true, hidden: true, key: true},
            {name: 'userName', index: 'userName', width: 80},
            {name: 'couponNum', index: 'couponNum', width: 80, hidden: true},
            {name: 'couponNumStr', index: 'couponNumStr', width: 80,formatter:couponNum},
            {name: 'isClaim', index: 'isClaim', width: 80,formatter:isClaim},
            {name: 'phone', index: 'phone', width: 100},
            {name: 'email', index: 'email', width: 80},
            {name: 'address', index:  'address', width: 100},
            {name: 'score', index: 'score', width: 100},
            {name: 'money', index: 'phone', width: 100},
            {name: 'userType', index: 'userType', width: 90,formatter:function (cellvalue, options, rowObject){return userType(cellvalue);}},
            // {name: 'userIdentity', index: 'userIdentity', width: 120,formatter:function (cellvalue, options, rowObject){return userIdentity(cellvalue,rowObject);}},
            {name: 'userlevel.stuname', index: 'userlevel.stuname', width: 90,formatter:function (cellvalue, options, rowObject){return stuname(cellvalue);}},
            {name: 'createTime', index: 'createTime', width: 160,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'id', index: 'id', width: 120,formatter:function (cellvalue, options, rowObject){return "<a id='getLabel' class='label label-sm label-blue' onclick='getLabel(\""+rowObject.userId+"\")'>点击查看详情</a>"}},
            {name: 'status', index: 'status', width: 110,editable: false,formatter:function (cellvalue, options, rowObject){return userstatus(cellvalue,rowObject);}},
            {name:'userType',index:'userType', width:300, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    if(cellvalue == 1){
                        return "<a class='label label-sm label-blue'  style='margin-right:20px;' id='resetpwd' onclick='resetpwd(\"" +rowObject.id+ "\")'>重置登录密码</a>&nbsp;<a class='label label-sm label-blue' id='resetpay' onclick='resetpay(\""+rowObject.id+"\")'>重置支付密码</a>";
                    }else if(cellvalue == 2){
                        return "<a class='label label-sm label-blue' onclick='change(\"" +rowObject.id+ "\")'> <i class='ace-icon fa fa-exchange bigger-120 blue'></i>转换为教头</a><br><a class='label label-sm label-blue'  style='margin-right:20px;' id='resetpwd' onclick='resetpwd(\"" +rowObject.id+ "\")'>重置登录密码</a>&nbsp;<a class='label label-sm label-blue' id='resetpay' onclick='resetpay(\""+rowObject.id+"\")'>重置支付密码</a>";
                    }else{
                        return "<a class='label label-sm label-blue'  style='margin-right:20px;' id='resetpwd' onclick='resetpwd(\"" +rowObject.id+ "\")'>重置登录密码</a>&nbsp;<a class='label label-sm label-blue' id='resetpay' onclick='resetpay(\""+rowObject.id+"\")'>重置支付密码</a>";
                    }
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
        rowNum: 20,
        rowList: [20,50,100, 200, 300],
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
            var rows =  jQuery(grid_selector).jqGrid('getRowData'); //获取当前显示的记录
            $.each(rows,function (i,row) {
                if(row.couponNum ==1){
                    jQuery(grid_selector).jqGrid('setSelection',row.userId);
                }
            });
        },

        // caption: "用户列表",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            add: false,
            del: false,
            search: false,
            refresh: false,
            refreshicon: 'ace-icon fa fa-refresh green',
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
        })
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
    //是否发放了优惠劵
    function  couponNum(cellvalue, options, rowObject) {
        if(rowObject.couponNum == 1){
            return '<small class="red">发放了</small>';
        }
        if(rowObject.couponNum == 0){
            return '<small class="red">未发放</small>';
        }
    }
    //是否认领了该优惠劵
    function  isClaim(cellvalue, options, rowObject) { //是否认领:0：未认领；1：认领了
        if(rowObject.isClaim == 1){
            return '<small class="red">认领了</small>';
        }else{
            return '<small class="red">未认领</small>';;
        }
    }
    //发放优惠劵
    $("#addUserCoupon").click(function () {
        var ids = jQuery(grid_selector).jqGrid('getGridParam','selarrrow'); //获得选中行的ID数组
        if(ids && ids.length== 0){
            Modal.alert({msg: "至少选择一个用户！", title: "提示", btnok: "确定"});
        }
        console.log(ids);
        $.ajax({
            async: false,
            url: base + "coupon/addUserCoupon",
            data:{ids:ids.join(","),"couponId":$("input[name=couponId]").val()},
            dataType: "json",
            success: function (data) {
                if(data == 1){
                    Modal.alert({msg: "发放成功！", title: "提示", btnok: "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg: "发放失败！", title: "提示", btnok: "确定"});
                }
            },
            error:function (data) {
                Modal.alert({msg: "系统异常，操作失败！", title: "提示", btnok: "确定"});
            }
        });

    });
    //取消发放优惠劵
    $("#cancelUserCoupon").click(function () {
        var ids = jQuery(grid_selector).jqGrid('getGridParam','selarrrow'); //获得选中行的ID数组
        if(ids && ids.length== 0){
            Modal.alert({msg: "至少选择一个用户！", title: "提示", btnok: "确定"});
            return false;
        }
        console.log(ids);
        $.ajax({
            async: false,
            url: base + "coupon/cancelUserCoupon",
            data:{ids:ids.join(","),"couponId":$("input[name=couponId]").val()},
            dataType: "json",
            success: function (data) {
                if(data == 1){
                    Modal.alert({msg: "取消发放成功！", title: "提示", btnok: "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg: "取消发放失败！", title: "提示", btnok: "确定"});
                }
            },
            error:function (data) {
                Modal.alert({msg: "系统异常，操作失败！", title: "提示", btnok: "确定"});
            }
        });

    });

});

var grid_selector = "#grid-table";
function resetpwd(id){
        var r = confirm("确定要重置此用户的登录密码吗？");
        if (r == true) {
            $.ajax({
                async: false,
                type: "POST",
                url: base+"user/resetPwd",
                data: {'id': id.toString()},
                dataType: "json",
                success: function (data) {
                    if (data == 1) {
                        Modal.alert({msg : "重置成功", title : "提示", btnok : "确定"});
                        jQuery(grid_selector).trigger("reloadGrid");
                    }
                },
                error: function (data) {
                    Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                }
            })
        } else {
            alert("您取消了此次操作!");
        }
}



function change(id){
    var id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    var r = confirm("是否要升级为教头!");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"user/change",
            data: {'id': id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    Modal.alert({msg : "此用户已成为教头", title : "提示", btnok : "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    }
    else {
        alert("您取消了此次操作!");
    }
}

function userend(id,end){
    var r = confirm("确定要禁用此用户吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"user/userend",
            data: {'id': id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    Modal.alert({msg : "此用户禁用成功!", title : "提示", btnok : "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    }
    else {
        alert("您取消了此次操作!");
        $(end).prop("checked",true);
    }
}
function userstart(id,start){
    var r = confirm("确定要启用此用户吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"user/userstart",
            data: {'id': id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    Modal.alert({msg : "此用户启用成功!", title : "提示", btnok : "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    }
    else {
        alert("您取消了此次操作!");
        $(start).prop("checked",false);
    }

}

function resetpay(id){
    var id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    var r=confirm("确定要重置此用户的支付密码吗？");
    if (r==true) {
        $.ajax({
            async:false,
            type: "POST",
            url: base+"user/resetPay",
            data:{'id':id},
            dataType: "json",
            success:function(data){
                if(data == 1){
                    Modal.alert({msg : "重置成功", title : "提示", btnok : "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error:function(data){
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    }else
    {
        alert("您取消了此次操作!");
    }

}
var userType = function(v){
    if (v == 1) {
        return "教头";
    } else if (v == 2) {
        return "用户";
    }else{
        return "";
    }
}
var userIdentity = function(v,r){
    if (v == 1) {
        return "普通用户"+'&nbsp;&nbsp;&nbsp;'+"<a href='javascript:void(0)' onclick='change_vip(\"" +r.userId+ "\")'>变更为会员</a>";
    } else if (v == 2) {
        return "会员用户"+'&nbsp;&nbsp;&nbsp;'+"<a href='javascript:void(0)'  onclick='change_user(\"" +r.userId+ "\")'>变更为普通</a>";
    }else{
        return "";
    }
}
var labelname = function(v){
    if(null == v){
        return "";
    }else{
        return "<span class=\"label label-lg label-yellow arrowed-in arrowed-in-right\">"+v+"</span>";
    }
}
var stuname = function(v){
    if(null == v){
        return "";
    }else{
        return "<span class=\"label label-green arrowed-in\">"+v+"</span>";
    }
}

var userstatus = function(v,r){
        if (v == 1) {
            return "<input id='id-check-horizontal' type='checkbox' class='ace ace-switch ace-switch-6::after' checked='checked' onclick='userend(\""+r.id+"\",this)'/> <span class='lbl middle'></span>";
        } else if (v == 0) {
            return "<input id='id-check-horizontal1' type='checkbox' class='ace ace-switch ace-switch-6::before'  onclick='userstart(\""+r.id+"\",this)'/> <span class='lbl middle'></span>";
        }else{
            return "";
        }
    }
function checktype(id){
    var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');
    $.ajax({
        async:false,
        type: "POST",
        url: base+"user/checktype",
        data:{'id':sel_id},
        dataType: "json",
        success:function(data){
            console.log(data);
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    })
}

function change_vip(id){
    var r=confirm("确定要改变此用户的普通身份吗？");
    if(r == true){
        $.ajax({
            async:false,
            type: "POST",
            url: base+"user/change_identity",
            data:{'userIdentity':2,'userId':id},
            dataType: "json",
            success:function(data){
                if(data == 1){
                    Modal.alert({msg : "此用户的身份已经变更为会员用户", title : "提示", btnok : "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error:function(data){
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    }else{
        alert("您取消了此次操作!");
    }

}

function change_user(id){
    var r=confirm("确定要改变此用户的会员身份吗？");
    if(r == true){
        $.ajax({
            async:false,
            type: "POST",
            url: base+"user/change_identity",
            data:{'userIdentity':1,'userId':id},
            dataType: "json",
            success:function(data){
                if(data == 1){
                    Modal.alert({msg : "此用户的身份已经变更为普通用户!", title : "提示", btnok : "确定"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error:function(data){
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    }else{
        alert("您取消了此次操作!");
    }
}

function getLabel(label_id){
  //跳转到详情页面
   window.location.href=base+"user/user_detail?userId="+label_id;
}