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

    jQuery(grid_selector).jqGrid({
        //direction: "rtl",

        url: base + "plat/list",
        subGrid: false,
        subGridOptions: {
            plusicon: "ace-icon fa fa-plus center bigger-110 blue",
            minusicon: "ace-icon fa fa-minus center bigger-110 blue",
            openicon: "ace-icon fa fa-chevron-right center orange"
        },
        datatype: "json",
        height: 'auto',
        colNames: ['id','物流平台名称', '是否启用','代码','参数','操作'],
        colModel: [
            {name: 'platId', index: 'platId', width: 200, editable: true, hidden: true, key: true},
            {name: 'platformName', index: 'platformName', width: 300},
            {name: 'isOpen', index: 'isOpen', width: 200,formatter:function (cellvalue, options, rowObject){return qiyong(cellvalue);}},
            {name: 'code', index: 'code', width: 200},
            {name: 'config', index: 'config', width: 500},
            {
                name: 'isOpen', index: 'isOpen', width: 80, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue ==1){
                        return '<a title="禁用" style="margin-left: 10px;" href="javascript:void(0);" ' +
                            'onclick="status_end(\'' + rowObject.platId + '\')" > '+'<i class="glyphicon glyphicon-remove bigger-130"></i>';
                    }else {
                        return '<a title="启用" style="margin-left: 10px;" href="javascript:void(0);" ' +
                            'onclick="status_start(\'' + rowObject.platId + '\')" > '+'<i class="glyphicon glyphicon-thumbs-up bigger-130"> ';
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

        //editurl: base + "/todayrec/tea/usort",//修改序列值
        caption: "物流平台设置中心",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            edittext: '修改',
            edittitle: '修改物流平台信息',
            add: true,
            addtext: '添加',
            addtitle: '添加物流平台',
            addfunc: add,
            addicon: 'ace-icon fa fa-plus-circle purple',
            del: true,
            deltitle:'删除物流平台',
            deltext:'删除',
            delfunc :del,
            search: false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alertcap: '提示',
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
});

var grid_selector = "#grid-table";
function update(id) {
    $("#updateModal-type").modal("show");
    $("#update_id").val(id);
    editInt(id);
}
function editInt(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "plat/selectById",
        data: {'platId': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                var q = data.config;
                var w = eval('(' + q + ')');
                var str_ = [];
                for(value in w){
                    str_.push(w[value])
                }
                $("#id").val(str_[0]);
                $("#user").val(str_[1])
                $("#code").val(str_[2]);
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    })
}

function add() {
    //打开模态窗体
    $("#addModal-type").modal("show");
}

$("#addplat").on("click", function () {
    if ($("#name_id").val() == null || $("#name_id").val() == undefined || $("#name_id").val() == "") {
        alert("请您输入物流平台名称,此数值不能为空");
        return;
    }
    if ($("#code_id").val() == null || $("#code_id").val() == undefined || $("#code_id").val() == "") {
        alert("请您输入物流平台代码,此数值不能为空");
        return;
    }
    var status = $("input[name='status']:checked").val();
    var type = $("input[name='type']:checked").val();
    $.ajax({
        async: false,
        type: "POST",
        url: base + "plat/addplat",
        data: {'name':$("#name_id").val(),'code':$("#code_id").val(),'key':$("#key").val(),'customer':$("#customer").val(),'status':status,'type':type},
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "添加成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                emptyForm('plat-form-add');
                $("#addModal-type").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }
        },
        error: function (data) {
            Modal.alert({
                msg: "系统异常，请稍后再试！",
                title: "提示",
                btnok: "确定",
                btncl: "取消"
            })
        }
    })
})

var qiyong = function (v) {
    if(1 == v){
        return "已经启用";
    }else{
        return "未启用";
    }
}

function status_start(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "plat/update_plat_status",
        data: {'platId':id,'status':"1"},
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "启用成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                jQuery(grid_selector).trigger("reloadGrid");
            }
        },
        error: function (data) {
            Modal.alert({
                msg: "系统异常，请稍后再试！",
                title: "提示",
                btnok: "确定",
                btncl: "取消"
            })
        }
    })
}
function status_end(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "plat/update_plat_status",
        data: {'platId':id,'status':"0"},
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "禁用成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                jQuery(grid_selector).trigger("reloadGrid");
            }
        },
        error: function (data) {
            Modal.alert({
                msg: "系统异常，请稍后再试！",
                title: "提示",
                btnok: "确定",
                btncl: "取消"
            })
        }
    })
}

function del(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "plat/delplat",
        data: {'platId':id.toString()},
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "删除成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                jQuery(grid_selector).trigger("reloadGrid");
            }
        },
        error: function (data) {
            Modal.alert({
                msg: "系统异常，请稍后再试！",
                title: "提示",
                btnok: "确定",
                btncl: "取消"
            })
        }
    })
}

