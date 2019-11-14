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
        url: base+"advertise/getMallAdvertise",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','广告名称','广告图片', '名称', '创建时间', '是否显示', '编辑'],
        colModel: [
            {name: 'adverId', index: 'adverId', width: 200, editable: true, hidden: true, key: true},
            {name: 'adverName', index: 'adverName', width: 90},
            {name: 'adverImg', index: 'adverImg', width: 90, formatter: function (cellvalue, options, rowObject) {
                return"<img style='width: 60px' src="+ rowObject.adverImg +">";
            }
            },
            {name: 'name', index: 'name', width: 90},
            {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'status', index: 'status', width: 90, formatter: function (cellvalue, options, rowObject) {

                if (rowObject.status == 1){
                    return "是";
                }
                if (rowObject.status == 2){
                    return "否";
                }
            }
            },
            {name: 'adverId', index: 'adverId', width: 90, formatter: editMall}
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
            /*editfunc: edit,*/
            add: false,
            /*addfunc: add,
            addicon : 'ace-icon fa fa-plus-circle purple',*/
            del: true,
            delfunc: del,
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




//查询要修改的广告
function selectById(adverId) {
    var adver_id = adverId.toString();
    var advertise = "";
    $.ajax({
        async: false,
        url: base+"advertise/selectAdverById",
        type: "post",
        data: {"adverId": adver_id},
        dataType: "json",
        success: function (data) {
            advertise = data;
        }
    });
    return advertise;
}

//进行删除广告
function del(adverId) {
    var adver_id = adverId.toString();
    $.ajax({
        async: false,
        url: base+"advertise/deleteAdver",
        type: "post",
        data: {"adverId": adver_id},
        dataType: "json",
        success: function (data) {
            if("success" == data.status){
                Modal.alert({msg : "删除成功！", title : "提示", btnok : "确定", btncl : "取消"});
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
            Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
        },
    });
}

function editMall(cellvalue, options, rowObject) {

    return '<a title="使用" style="margin-left: 10px;" href="javascript:void(0);" ' +
        'onclick="editMallImg(\'' + rowObject.adverId + '\')" > '+'<i class="ace-icon fa fa-pencil-square-o bigger-200"> ';
}
function editMallImg(adverId) {

    location.href = base+"advertise/updateMallImg?adverId="+adverId;
}
// 初始化上传文件输入框
$('#adver_img_add').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});

$('#adver_img_update').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});