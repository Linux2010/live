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

        url: base + "recsetting/goodcourlist",
        subGrid: false,
        subGridOptions: {
            plusicon: "ace-icon fa fa-plus center bigger-110 blue",
            minusicon: "ace-icon fa fa-minus center bigger-110 blue",
            openicon: "ace-icon fa fa-chevron-right center orange"
        },
        datatype: "json",
        height: 'auto',
        colNames: [ 'id', '课程名称', '序列值', '操作'],
        colModel: [
            {name: 'recSetId', index: 'recSetId', width: 200, editable: true, hidden: true, key: true},
            {name: 'course.courseTitle', index: 'course.courseTitle', width: 500},
            {name: 'recSort', index: 'recSort', width: 400, editable: true, editoptions: {size: "20", maxlength: "30"}, editrules:{number:true}},
            {
                name: 'recSetId', index: 'recSetId', width: 200, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    return "<i class='ace-icon fa fa-trash-o bigger-200' onclick='del_course(\"" + cellvalue + "\")'></i>";
                }
            },
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
        caption: "优质课程推荐列表",
        autowidth: true
    });
    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            editfunc : edit,
            edittext : '修改',
            edittitle : '修改推荐的课程的序列值',
            add: true,
            addtext :'添加',
            addtitle:'推荐课程',
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc: add,
            del: false,
            search:false,
            refresh: true,
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
});

var grid_selector = "#grid-table";

function del_course(id) {
    var r = confirm("确定不推荐此课程了吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base + "recsetting/delcour",
            data: {'recSetId': id},
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
                    msg: "系统异常，请稍后再试!",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
            }
        })
    } else {
        alert("您取消了此次操作!");
    }
}
function add() {
    window.location.href = base + "recsetting/exceCourRec/list";
}
function edit(id) {
    $("#updateModal-type").modal("show");
    $("#recSetId_update").val(id);
    editInt(id);
}
function editInt(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: base + "recsetting/selectByRecSetId",
        data: {'recSetId': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                $("#recSort_update").val(data.recSort);
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    })
}
$("#updatesort").on("click",function () {
    if ($("#recSort_update").val() == null || $("#recSort_update").val() == undefined || $("#recSort_update").val() == "") {
        alert("请您输入序列值,此数值不能为空");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: base + "recsetting/usort",
        data: {'recSetId':  $("#recSetId_update").val(),"recSort":$("#recSort_update").val(),"num":5},
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                Modal.alert({
                    msg: "修改成功！",
                    title: "提示",
                    btnok: "确定",
                    btncl: "取消"
                })
                emptyForm('sort-form-update');
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
