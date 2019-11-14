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
        var keyword=document.getElementById("nav-search-input").value;
        var status = document.getElementById("status").value;
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword),"status":status},
        }).trigger('reloadGrid');
    });
    $("#status").on("change",function () {
        var keyword=document.getElementById("nav-search-input").value;
        var status = document.getElementById("status").value;
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword),"status":status},
        }).trigger('reloadGrid');
    })

    jQuery(grid_selector).jqGrid({
        //direction: "rtl",

        url: base+"course_order/list",
        subGrid: false,
        datatype: "json",
        height: 421,
        colNames: ['id', '订单号','下单时间','订单金额','订单状态','课程标题','课程类别','用户名','查看'],
        colModel: [
            {name: 'courseOrderId', index: 'courseOrderId', width: 200, editable: true, hidden: true, key: true},
            {name: 'courseOrderId', index: 'courseOrderId', width: 140},
            {name: 'orderCreateTime', index: 'orderCreateTime', width: 140,formatter:function (cellvalue, options, rowObject) {
                if(isNotEmpty(cellvalue)){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'totalMoney', index: 'totalMoney', width: 70},
            {name: 'orderStatus', index: 'orderStatus', width: 70,formatter:function (cellvalue, options, rowObject){return order_status(cellvalue);}},
            {name: 'course.courseTitle', index: 'course.courseTitle', width: 200},
            {name: 'courseType.typeName', index: 'courseType.typeName', width: 100},
            {name: 'user.userName', index: 'user.userName', width: 100},
            {name:'courseOrderId',index:'courseOrderId', width:68, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    return "<button class='btn btn-white btn-info btn-bold' onclick='queryorder(\""+cellvalue+"\")'>查看 </button>";
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

        caption: "课程订单列表",
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
            refresh: true,
            search:false,
            refreshicon: 'ace-icon fa fa-refresh green',
            alerttext : "请选择需要操作的数据行!",
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

//var cover;
var grid_selector = "#grid-table";
function queryorder(id){
    $.ajax({
        async: false,
        type: "POST",
        url: base+"/course_order/get",
        data: {'corderid': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                $("#order_detail").modal("show");//打开页面
                //订单信息的显示
                $("#onum").html(data.courseOrderId);
                $("#odate").html(new Date(data.orderCreateTime).pattern("yyyy-MM-dd HH:mm:ss"));
                $("#ostatus").html(order_status(data.orderStatus));
                $("#mstatus").html(pay_status(data.payStatus));
                $("#pstatus").html(pay_type1(data.payType));
                $("#omoney").html(data.totalMoney);
                //课程信息的显示
                $("#ctitle").html(data.course.courseTitle);
                $("#cc").html(data.user.userName);
                $("#cover").attr("src",data.course.courseCover);
                $("#cstart").html(new Date(data.course.courseBeginTime).pattern("yyyy-MM-dd HH:mm"));
                $("#cpu").html(data.course.costPrice);
                $("#cvip").html(data.course.vipPrice);
                $("#cscore").html(data.course.courseIntegral);
                $("#ctype").html(data.courseType.typeName);
                $("#cintro").html(data.course.courseIntro);
                if(data.course.courseEndTime == null || data.course.courseEndTime == ""){
                    $("#course_end").css("display","none");
                    $("#cend").html("");
                }else{
                    $("#course_end").css("display","block");
                    $("#cend").html(new Date(data.course.courseEndTime).pattern("yyyy-MM-dd HH:mm"));
                }
                if(data.course.courseAddress == null || data.course.courseAddress == ""){
                    $("#course_addredd").css("display","none");
                    $("#caddress").html("");
                }else{
                    $("#course_addredd").css("display","block");
                    $("#caddress").html(data.course.courseAddress);
                }
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
var order_status = function(v){
    if (v == 0) {
        return "待付款";
    } else if (v == 1) {
        return "已付款";
    }
}
var pay_status = function(v){
    if (v == 0) {
        return "未付款";
    } else if (v == 1) {
        return "已付款";
    }else{
        return "";
    }
}
var pay_type = function(v){
    if (v == 0) {
        return "银两支付";
    } else if (v == 1) {
        return "支付宝支付";
    } else if (v == 2) {
        return "微信支付";
    } else if (v == 3) {
        return "Apple Pay支付";
    } else if (v == 4) {
        return "Andriod Pay支付";
    }else{
        return "";
    }
}
var pay_type1 = function(v){
    if (v == 0) {
        return "银两支付";
    } else if (v == 1) {
        return "支付宝支付";
    } else if (v == 2) {
        return "微信支付";
    } else if (v == 3) {
        return "Apple Pay支付";
    } else if (v == 4) {
        return "Andriod Pay支付";
    }else{
        return "暂时还未支付";
    }
}
var userIdentity = function(v){
    if (v == 1) {
        return "普通用户";
    } else if (v == 2) {
        return "会员用户";
    }else{
        return "";
    }
}