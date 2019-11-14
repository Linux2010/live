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
        //var keyword=document.getElementById("nav-search-input").value;
        var status = $("#status_add").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"status":encodeURI(status)},
        }).trigger('reloadGrid');
    });
    jQuery(grid_selector).jqGrid({
        url: base+"returnGoods/page",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','退货人', '商品名称', '退货金额', '创建时间', '处理时间', '状态', '操作'],
        colModel: [
            {name: 'returnGoodsId', index: 'returnGoodsId', width: 200, editable: true, hidden: true, key: true},
            {name: 'userRealName', index: 'userRealName', width: 90},
            {name: 'goodsName', index: 'goodsName', width: 90},
            {name: 'money', index: 'money', width: 60},
            {name: 'createTime', index: 'createTime', width: 80,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'handleTime', index: 'handleTime', width: 80,formatter:function (cellvalue, options, rowObject) {
                if (cellvalue == 0){
                    return '';
                }
                else{
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {name: 'status', index: 'status', width: 50, formatter: function (cellvalue, options, rowObject) {
                if (cellvalue ==1){
                    return '已打款';
                }else if (cellvalue == 2){
                    return '已拒绝';
                }else {
                    return '退款中';
                }
            }},
            {name: 'returnGoodsId', index: 'returnGoodsId', width: 50, formatter: operation}
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

            edit: false,
            add: false,
            addicon : 'ace-icon fa fa-plus-circle purple',
            del: true,
            delfunc: delReturnGoods,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alerttext: "请选择需要操作的数据行!",
            search: false
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

//进行删除操作
function delReturnGoods(returnGoodsId){
    console.log(returnGoodsId);
    $.ajax({
        async: false,
        url: base+"returnGoods/delReturnGoods",
        type: "post",
        data: {"returnGoodsId": returnGoodsId.toString()},
        dataType: "json",
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

function operation(cellvalue,options,rowObject) {

    if (rowObject.operationType == 3){
        return '<a title="同意" href="javascript:void(0);" ' +
            'onclick="agree(\'' + rowObject.returnGoodsId + '\')" > ' +
            '<i class="fa fa-check bigger-180" aria-hidden="true"></i></a>' +
            '<a title="拒绝" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="refuse(\'' + rowObject.returnGoodsId + '\')" > ' +
            '<i class="fa fa-times bigger-180" aria-hidden="true"></i></a>';
    }else {
        return '已处理';
    }
}

//同意退货操作
function agree(returnGoodsId) {
    console.log(returnGoodsId);
    $.ajax({
        async: false,
        url: base+"returnGoods/agree",
        type: "post",
        data: {"returnGoodsId": returnGoodsId},
        dataType: "json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "操作成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : "操作失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        }
    });
}

//拒绝退货操作
function refuse(returnGoodsId) {

    $("#updateModal-type").modal("show");
    $("#returnGoods_id").val(returnGoodsId);
    $("#sure").on("click",function () {
        var refuseReason = $("#refuse_reason").val();
        if (isEmpty(refuseReason)){
            alert("拒绝原因不能为空！");
            return false;
        }
        $.ajax({
            async: false,
            url: base+"returnGoods/refuse",
            type: "post",
            data: {"returnGoodsId": returnGoodsId, "refuseReason": refuseReason},
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg : "操作成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    emptyForm("update-form-returnGoods");
                    $("#updateModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else {
                    Modal.alert({msg : "操作失败！", title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error: function (data) {
                Modal.alert(data.message);
            }
        });
    })
}







