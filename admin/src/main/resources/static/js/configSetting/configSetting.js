jQuery(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    var parent_column = $(grid_selector).closest('[class*="col-"]');



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
        var keyword=document.getElementById("nav-search-input").value;
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword)},
        }).trigger('reloadGrid');
    });

    jQuery(grid_selector).jqGrid({
        url: base+"configSetting/page",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','名称','数值','创建时间','备注'],
        colModel: [
            {name: 'configId', index: 'configId', width: 200, editable: true, hidden: true, key: true},
            {name: 'config_name', index: 'config_name', width: 90},
            {name: 'config_value', index: 'config_value', width: 90},
            {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'remark', index: 'remark', width: 90}
        ],
        jsonReader : {
            root:"list",
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

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            editfunc: edit,
            add: true,
            addfunc: add,
            addicon : 'ace-icon fa fa-plus-circle purple',
            del: true,
            delfunc: del,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alerttext: "请选择需要操作的数据行!"
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
});

var grid_selector = "#grid-table";


//打开添加设置模块窗口
function add() {
    $("#addModal-type").modal("show");
}

//进行添加设置操作
$("#add_configSetting").click(function () {
    var config_name = $("#config_name_add").val();
    var config_value = $("#config_value_add").val();
    var config_sign = $("#config_sign_add").val();
    var remark_add = $("#remark_add").val();

    if (isEmpty(config_name)){
        alert("名称不能为空！");
        return false;
    }
    if (isEmpty(config_value)){
        alert("数值不能为空！");
        return false;
    }
    if (isEmpty(config_sign)){
        alert("标识不能为空！");
    }

    $.ajax({
        url: base+"configSetting/addConfigSetting",
        type: "post",
        data: $("#add-form-configSetting").serialize(),
        dataType:"json",
        success: function (data) {
            if("success" == data.status){
                Modal.alert({
                    msg : "保存成功！",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                });
                emptyForm('add-form-configSetting');
                $("#addModal-type").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }else{
                Modal.alert({
                    msg : data.message,
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                });
            }
        },
        error: function (data) {
            $("#addModal-type").modal("hide");
        },
    });
});


//删除设置
function del(configId) {
    $.ajax({
        url: base+"configSetting/delConfigSettingById",
        data: {"configId": configId.toString()},
        type: "post",
        dataType:"json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "删除成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : "删除失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        }
    });
}




//查询要修改的设置
function selectById(configId) {
    var configSetting = "";
    $.ajax({
        async: false,
        url: base+"configSetting/selectConfigSettingById",
        type: "post",
        data: {"configId": configId},
        dataType: "json",
        success: function (data) {
            configSetting = data;
        },
        error: function (data) {
            alert("系统异常，请稍后再试！");
        }
    });
    return configSetting;
}

//打开修改设置模块窗口
function edit(configId) {
    $("#updateModal-type").modal("show");
    var configSetting = selectById(configId);
    if (isEmpty(configSetting)){
        return false;
    }
    $("#config_name_update").val(configSetting.config_name);
    $("#config_value_update").val(configSetting.config_value);
    $("#remark_update").val(configSetting.remark);
    $("#config_id_update").val(configSetting.configId);
}
//进行修改设置
$("#update_configSetting").click(function () {
    $.ajax({
        async: false,
        url: base+"configSetting/updateConfigSetting",
        type: "post",
        data: $("#update-form-configSetting").serialize(),
        dataType:"json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({
                    msg : "修改成功！",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                });
                jQuery(grid_selector).trigger("reloadGrid");
                $("#updateModal-type").modal("hide");
                emptyForm("update-form-configSetting");
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        }
    });
});