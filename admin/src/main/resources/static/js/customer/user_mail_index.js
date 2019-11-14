var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function ($) {
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', parent_column.width());
    });
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    });
    $("#find").on("click",function(){
        var keyword=$("#keyword").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword)},
            rows:20
        }).trigger('reloadGrid');
    });

    jQuery(grid_selector).jqGrid({
        url: base + "mail/getUserPage?r="+Math.random(),
        subGrid: false,
        datatype: "json",
        height: 'auth',
        colNames: ['id', '用户名称','真实姓名','手机号','邮箱','地区','昵称','积分','银两','用户类型','用户身份','用户等级','注册时间','学习标签','操作'],
        colModel: [
            {name: 'userId', index: 'userId', width: 200, editable: true, hidden: true, key: true},
            {name: 'userName', index: 'userName', width: 90},
            {name: 'realName', index: 'realName', width: 90},
            {name: 'phone', index: 'phone', width: 90},
            {name: 'email', index: 'email', width: 90},
            {name: 'address', index:  'address', width: 190},
            {name: 'nickName', index: 'nickName', width: 90},
            {name: 'score', index: 'score', width: 60},
            {name: 'money', index: 'phone', width: 60},
            {name: 'userType', index: 'userType', width: 90,formatter:function (cellvalue, options, rowObject){return userType(cellvalue);}},
            {name: 'userIdentity', index: 'userIdentity', width: 100,formatter:function (cellvalue, options, rowObject){return userIdentity(cellvalue);}},
            {name: 'userlevel.stuname', index: 'userlevel.stuname', width: 90,formatter:function (cellvalue, options, rowObject){return stuname(cellvalue);}},
            {name: 'createTime', index: 'createTime', width: 160,
                formatter:function (cellvalue, options, rowObject) {
                    if(typeof(cellvalue) != "undefined"){
                        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                    }else{
                        return '';
                    }
                }
            },
            {name: 'stulabel.labelname', index: 'stulabel.labelname', width: 120,formatter:function (cellvalue, options, rowObject){return labelname(cellvalue);}},
            {name:'',index:'', width:90, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="sendMessage(\''+rowObject.userId+'\')">发送站内信</a>';
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
        rowList: [10,20,30],
        pager: pager_selector,
        altRows: true,
        //toppager: true,
        multiselect: false,
        //multikey: "ctrlKey",
        multiboxonly: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        caption: "站内信发送",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            add: false,
            del:false,
            search:false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh yellow',
            alerttext : "请选择需要操作的数据行!",
        }
    ).navButtonAdd(pager_selector,{ //新增群发按钮
        caption:"",
        buttonicon:"ace-icon fa fa-envelope-o yellow ",
        title:"Group Send",
        onClickButton: function(){
            sendMessagePageAll();
        },
        position:"last"
    }).navSeparatorAdd(pager_selector,{
        sepclass:"ui-separator",
        sepcontent:""
    }).navButtonAdd(pager_selector,{ //新增加载全部按钮
        caption:"",
        buttonicon:"ace-icon fa fa-folder-open yellow ",
        title:"Send All",
        onClickButton: function(){
            sendMessageAll();;
        },
        position:"last"
    }).navSeparatorAdd(pager_selector,{
        sepclass:"ui-separator",
        sepcontent:""
    }).navButtonAdd(pager_selector,{ //新增返回按钮
        caption:"",
        buttonicon:"ace-icon fa fa-exchange yellow ",
        title:"BACK",
        onClickButton: function(){
            back();
        },
        position:"last"
    })

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
});

var userType = function(v){
    if (v == 1) {
        return "<span class=\"label label-info arrowed-in-right arrowed\">教头</span>";
    } else if (v == 2) {
        return "<span class=\"label label-success arrowed\">用户</span>";
    }else{
        return '';
    }
}

var userIdentity = function(v){
    if (v == 1) {
        return "<span class=\"btn btn-white btn-danger btn-sm\">普通用户</span>";
    } else if (v == 2) {
        return "<span class=\"btn btn-white btn-inverse btn-sm\">会员用户</span>";
    }else{
        return '';
    }

}

var labelname = function(v){
    if(isEmpty(v)){
        return '';
    }
    return "<span class=\"label label-lg label-yellow arrowed-in arrowed-in-right\">"+v+"</span>";
}

var stuname = function(v){
    if(isEmpty(v)){
        return '';
    }
    return "<span class=\"label label-green arrowed-in\">"+v+"</span>";
}

var sendMessage = function(id){
    $("#relationId").val(id);
    $("#receiveUserId").val(id);
    $("#addModal").modal("show");
}

$("#sendMessageBtn").bind("click",function(){

    $("#content").val(ue.getContent());

    if(isEmpty($("#title").val())){
        Modal.alert({msg : "标题为空", title : "提示", btnok : "确定", btncl : "取消"});
        return;
    }

    if(isEmpty($("#intro").val())){
        Modal.alert({msg : "简介为空", title : "提示", btnok : "确定", btncl : "取消"});
        return;
    }

    if(isEmpty($("#content").val())){
        Modal.alert({msg : "内容为空", title : "提示", btnok : "确定", btncl : "取消"});
        return;
    }
    if(confirm("确认要发送本条信息？")){
        $("#sendMessage-form").ajaxSubmit({
            url:base+"mail/addRecord",
            //data:$("#sendMessage-form").serialize(),
            type:"POST",
            dateType:"json",
            success:function(data){
                if("success" == data.status){
                    Modal.alert({msg : "发送成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    $("#content").val("");
                    $("#userIds").val("");
                    $("#isSendAll").val("0");
                    $("#content").val("");
                    $("#addModal").modal("hide");
                }else{
                    Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error:function(data){
                Modal.alert("系统异常，请稍后重试！");
            }
        })
    }
})

var sendMessagePageAll = function(){
    var obj = $(grid_selector).jqGrid("getRowData");
    if(isNotEmpty(obj) && obj.length>0){
        if(confirm("确认要给本页的"+obj.length+"位会员发送站内信？")){
            var userIds ="";
            for(var str in obj){
                userIds += ","+obj[str].userId;
            }
            if(isNotEmpty(userIds)){
                userIds = userIds.substring(userIds.indexOf(",")+1);
            }
            $("#userIds").val(userIds);
            $("#addModal").modal("show");
        }
    }else{
        Modal.alert({msg :"数据为空！", title : "提示", btnok : "确定", btncl : "取消"});
    }
}


var sendMessageAll = function(){
    var records = $(grid_selector).getGridParam("records");
    if(isNotEmpty(records) && records>0){
        if(confirm("确认要给"+records+"位会员发送站内信？")){
            var keyword = $("#keyword").val();
            $("#search").val(keyword);
            $("#isSendAll").val("1");
            $("#addModal").modal("show");
        }
    }else{
        Modal.alert({msg :"数据为空！", title : "提示", btnok : "确定", btncl : "取消"});
    }
}

//获取当前表格的所有数据
function getJQAllData() {
    var o = jQuery(grid_selector);
    var rowNum = o.jqGrid('getGridParam', 'rowNum'); //获取显示配置记录数量
    var total = o.jqGrid('getGridParam', 'records'); //获取查询得到的总记录数量
    o.jqGrid('setGridParam', {rowNum: total }).trigger('reloadGrid');
}

var back = function(){
    location.href = base + "mail/record";
}
