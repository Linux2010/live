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
            }, 2 );
        }
    });

    jQuery(grid_selector).jqGrid({
        //direction: "rtl",

        url: base+"/newsComment/list",
        subGrid: false,
        datatype: "json",
        height: 325,
        colNames: ['id','新闻名称','评论用户名','评论内容','评论时间' ,'回复时间','操作'],
        colModel: [
            {name: 'id', index: 'id',width: 200, editable: true, hidden: true, key: true},
            {name: 'newsName', index: 'newsName', width: 110},
            {name: 'newsUsername', index: 'newsUsername', width: 100},
            {name: 'newsContent', index: 'newsContent', width: 200},
            {name: 'createTime', index: 'createTime', width: 150,formatter:showFormatDate},
            {name: 'replyTime', index: 'replyTime', width: 80,formatter:showFormatDate},
            {name:'id',index:'id', width:180, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    return "<button class='btn btn-white btn-info btn-bold' onclick='queryid("+cellvalue+")'> <i class='ace-icon fa fa-eye bigger-120 blue'></i>查看 </button>&nbsp;&nbsp;&nbsp;<button class='btn btn-white btn-warning btn-bold'  onclick='delcomm("+cellvalue+")'> <i class='ace-icon fa fa-trash-o bigger-120 orange'></i>删除 </button>";
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
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            add: false,
            refresh: true,
            del:false,
            refreshicon: 'ace-icon fa fa-refresh yellow',
            alerttext : "请选择需要操作的数据行!",
        }
    )

    // 展示格式化后的日期
    function showFormatDate(cellvalue, options, rowObject){
        if(cellvalue != null){
            return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm");
        }else{
            return "";
        }
    }

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
function queryid(id){
    $.ajax({
        async: false,
        type: "POST",
        url: base+"/newsComment/get",
        data: {'id': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                $("#comm_find").modal("show");
                $("#newsname").html(data.newsName);
                if(isNotEmpty(data.create_time)){
                    $("#createTime").html(new Date(data.create_time).pattern("yyyy-MM-dd HH:mm:ss"));
                }
                $("#newsContent").html(data.newsContent);
                $("#id").val(data.id);
                if(data.hcontent == null && data.htime == null){
                    $("#huifu").css("display","none");
                }else{
                    $("#huifu").css("display","block");
                    $("#hcontent").html(data.hcontent);
                    $("#htime").html(new Date(data.htime).pattern("yyyy-MM-dd HH:mm:ss"));
                }
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    })
}

function delcomm(id){
    var r = confirm("确定要删除此条评论吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"/newsComment/delete",
            data: {'id': id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    Modal.alert({
                        msg : "删除成功！",
                        title : "提示",
                        btnok : "确定",
                        btncl : "取消"
                    })
                    jQuery(grid_selector).trigger("reloadGrid");
                }
            },
            error: function (data) {
                Modal.alert({
                    msg : "系统异常，请稍后再试!",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
            }
        })
    }
    else {
        alert("您取消了此次操作!");
    }
}

$("#resp").on('click',function () {
    var res = $("#replayContent").val();
    if(res == null || res == undefined || res == ""){
        alert("回复的内容不能为空!");
        return;
    }
    var data =$("#reply-form").serialize();
    console.log("data:"+data);
    $.ajax({
        type: "POST",
        url: base+"/newsComment/addReply",
        data:data,
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg : "回复成功!",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
                $("#comm_find").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }
        },
        error: function (data) {
            Modal.alert({
                msg : "系统异常，请稍后再试!",
                title : "提示",
                btnok : "确定",
                btncl : "取消"
            })
        }
    })
})