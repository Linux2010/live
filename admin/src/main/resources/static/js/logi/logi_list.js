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

        url: base + "logi/list",
        subGrid: false,
        subGridOptions: {
            plusicon: "ace-icon fa fa-plus center bigger-110 blue",
            minusicon: "ace-icon fa fa-minus center bigger-110 blue",
            openicon: "ace-icon fa fa-chevron-right center orange"
        },
        datatype: "json",
        height: 'auto',
        colNames: ['id','物流公司名称', '代码', '操作'],
        colModel: [
            {name: 'logiId', index: 'logiId', width: 200, editable: true, hidden: true, key: true},
            {name: 'name', index: 'name', width: 500},
            {name: 'code', index: 'code', width: 400},
            {
                name: 'logiId', index: 'logiId', width: 200, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    return "<i class='ace-icon fa fa-trash-o bigger-200' onclick='dellogi(\"" + cellvalue + "\")'></i>";
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
        caption: "物流公司列表",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            edittext: '修改',
            edittitle: '修改物流公司信息',
            editfunc: edit,
            add: true,
            addtext: '添加',
            addtitle: '添加物流公司',
            addfunc: add,
            addicon: 'ace-icon fa fa-plus-circle purple',
            del: false,
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
function dellogi(id) {
    var r = confirm("确定要删除此物流公司吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "logi/dellogi",
            data: {'logiId': id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
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
                    msg: "系统异常，请稍后再试!",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
            }
        })
    }
    else {
        alert("您取消了此次操作!");
    }
}
function add() {
    //打开模态窗体
    $("#addModal-type").modal("show");
}

$("#addlogi").on("click", function () {
    if ($("#name_id").val() == null || $("#name_id").val() == undefined || $("#name_id").val() == "") {
        alert("请您输入物流公司名称,此数值不能为空");
        return;
    }
    if ($("#code_id").val() == null || $("#code_id").val() == undefined || $("#code_id").val() == "") {
        alert("请您输入物流公司代码,此数值不能为空");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: base + "logi/addlogi",
        data: $("#logi-form-add").serialize(),
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "添加成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                emptyForm('logi-form-add');
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
function edit(id) {
    var se_id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    $("#updateModal-type").modal("show");
    $("#update_id").val(se_id);
    editInt(se_id);
}
function editInt(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "logi/selectById",
        data: {'logiId': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                $("#update_name").val(data.name);
                $("#update_code").val(data.code);
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    })
}

$("#updatelogi").on("click", function () {
    if ($("#update_name").val() == null || $("#update_name").val() == undefined || $("#update_name").val() == "") {
        alert("请您输入物流公司名称,此数值不能为空");
        return;
    }
    if ($("#update_code").val() == null || $("#update_code").val() == undefined || $("#update_code").val() == "") {
        alert("请您输入物流公司代码,此数值不能为空");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: base + "logi/updatelogi",
        data: $("#logi-form-update").serialize(),
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "修改成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                emptyForm('logi-form-update');
                $("#updateModal-type").modal("hide");
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
