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

        url: base + "deliver/list",
        subGrid: false,
        subGridOptions: {
            plusicon: "ace-icon fa fa-plus center bigger-110 blue",
            minusicon: "ace-icon fa fa-minus center bigger-110 blue",
            openicon: "ace-icon fa fa-chevron-right center orange"
        },
        datatype: "json",
        height: 'auto',
        colNames: ['id','物流公司名称', '运输单号','订单类型','订单金额','用户名','下单时间'],
        colModel: [
            {name: 'deliNo', index: 'deliNo', width: 200, editable: true, hidden: true, key: true},
            {name: 'name', index: 'name', width: 200},
            {name: 'deliNo', index: 'deliNo', width: 200},
            {name: 'orderType', index: 'orderType', width: 200,formatter:function (cellvalue, options, rowObject){return type_(cellvalue)} },
            {name: 'orderMoney', index: 'orderMoney', width: 200},
            {name: 'userName', index: 'userName', width: 200},
            {name: 'createTime', index: 'createTime', width: 200,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
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
        caption: "订单的物流信息列表",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            edittext: '修改',
            edittitle: '修改单号',
            editfunc: edit,
            add: false,
            addtext: '添加',
            addtitle: '添加物流公司',
            //addfunc: add,
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

function edit(id) {
    var se_id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
    $("#updateModal-type").modal("show");
    $("#deliNo").val(se_id);
    editInt(se_id);
}
function editInt(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "deliver/selectByDeliNo",
        data: {'deliNo': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                $("#deliId").val(data.deliId);
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    })
}
$("#updateno").on("click",function () {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "deliver/update",
        data: {'deliId': $("#deliId").val(),'deliNo':$("#deliNo").val()},
        dataType: "json",
        success: function (data) {
            if (data == 1) {
                Modal.alert({
                    msg: "修改成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                $("#updateModal-type").modal("hide");
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
})
function type_(v) {
    if(v == 1){
        return "金额商品订单";
    }else if(v == 2){
        return "银两商品订单";
    }else{
        return "金额商品订单";
    }
}
