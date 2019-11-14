var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "order/searchOrderList",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        loadtext: '正在加载...',
        colNames:['订单编号','订单类型','订单金额','订单时间','发货状态','支付状态','订单状态','用户名','操作'],
        colModel:[
            {name:'orderNo',index:'orderNo',width:40},
            {name:'orderType',index:'orderType',width:20,formatter:showOrderType},
            {name:'orderMoney',index:'orderMoney',width:20},
            {name:'orderTime',index:'orderTime',width:40,formatter:showFormatDate},
            {name:'orderStatus',index:'orderStatus',width:20,formatter:showOrderStatus},
            {name:'payStatus',index:'payStatus',width:20,formatter:showPayStatus},
            {name:'status',index:'status',width:20,formatter:showStatus},
            {name:'userName',index:'userName',width:20},
            {name:'orderId',index:'orderId',width:40,formatter:operFuc}
        ],
        jsonReader : {
            root:"list",
            page: "pageNum",
            total: "pages",
            records: "total"
        },
        pgtext : "第 {0} 页，共 {1}页",
        recordtext : "第 {0} 到 {1} 共 {2} 条",
        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        altRows: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        caption: "",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

    // 操作按钮，true为显示，false为隐藏，xxxfunc:调用函数,xxxicon:图标
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            add: false,
            addicon : 'ace-icon fa fa-plus-circle purple',
            del: false,
            delicon : ' ace-icon fa glyphicon glyphicon-trash',
            search:false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            refreshtitle: '刷新列表'
        }
    );

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        if(rowObject.status == 1){
            if(rowObject.payStatus == 2 && rowObject.orderStatus == 1){
                return '<a title="取消订单" href="javascript:void(0);"'+
                    'onclick="modifyOrderStatus(\''+rowObject.orderId+'\')">取消订单</a>'+
                    '<a title="详情" style="margin-left: 5px;" href="javascript:void(0);"'+
                    'onclick="showOrderDetail(\''+rowObject.orderId+'\',\''+rowObject.orderType+'\')">详情</a>'+
                    '<a style="margin-left: 5px;" title="发货" href="javascript:void(0);"'+
                    'onclick="modifyFHS(\''+rowObject.orderId+'\')">发货</a>';
            }else if(rowObject.payStatus != 1 && rowObject.orderStatus != 1){
                return '<a title="详情" href="javascript:void(0);"'+
                    'onclick="showOrderDetail(\''+rowObject.orderId+'\',\''+rowObject.orderType+'\')">详情</a>';
            }else{
                return '<a title="取消订单" href="javascript:void(0);"'+
                    'onclick="modifyOrderStatus(\''+rowObject.orderId+'\')">取消订单</a>'+
                    '<a title="详情" style="margin-left: 5px;" href="javascript:void(0);"'+
                    'onclick="showOrderDetail(\''+rowObject.orderId+'\',\''+rowObject.orderType+'\')">详情</a>';
            }
        }else{
            return '已取消<a title="详情" style="margin-left: 5px;" href="javascript:void(0);"'+
                'onclick="showOrderDetail(\''+rowObject.orderId+'\',\''+rowObject.orderType+'\')">详情</a>';
        }
    }

    // 模糊查询
    $("#course-tag-search").bind("click",function(){
        $("#grid-table").jqGrid('setGridParam',{
            postData:{
                orderNo:$("#orderNo").val(),
                orderType:$("#orderType").val(),
                status:$("#status").val(),
                orderStatus:$("#orderStatus").val(),
                payStatus:$("#payStatus").val(),
                userName:$("#userName").val(),
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val()
            }
        }).trigger('reloadGrid');
    });

    // 展示格式化后的日期
    function showFormatDate(cellvalue, options, rowObject){
        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
    }

    // 展示订单类型，订单类型：1是金额商品订单，2是银两商品订单
    function showOrderType(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "金额商品订单";
        }else if(cellvalue == 2){
            return "银两商品订单";
        }else{
            return "金额商品订单";
        }
    }

    // 展示发货状态，发货状态：1是待发货，2是待收货，3是待评价
    function showOrderStatus(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "待发货";
        }else if(cellvalue == 2){
            return "待收货";
        }else if(cellvalue == 3){
            return "待评价";
        }else{
            return "待发货";
        }
    }

    // 展示支付状态：支付状态：1是待付款，2是已付款，3是退款
    function showPayStatus(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "待付款";
        }else if(cellvalue == 2){
            return "已付款";
        }else if(cellvalue == 3){
            return "退款";
        }else{
            return "待付款";
        }
    }

    // 展示订单状态：订单状态：1是正常，2是已取消
    function showStatus(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "正常";
        }else if(cellvalue == 2){
            return "已取消";
        }else{
            return "正常";
        }
    }

    // 更新分页按钮
    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    // 初始化下拉日历
    $(".date-picker").datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD HH:mm:ss'
    });

});

// 取消订单
function modifyOrderStatus(orderId){
    Modal.confirm({msg: "确认取消吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则进行订单的取消操作
            $.ajax({
                async: false,
                url: base + "order/modifyOs",
                data:{orderId:orderId,status:2},
                dataType: "json",
                success: function (data) {
                    if(data == 0){
                        Modal.alert({msg: "取消成功！", title: "提示", btnok: "确定"});
                        jQuery(grid_selector).trigger("reloadGrid");
                    }else{
                        Modal.alert({msg: "取消失败！", title: "提示", btnok: "确定"});
                    }
                }
            })
            jQuery(grid_selector).trigger("reloadGrid");
        }
    });
}

// 发货
function modifyFHS(orderId){
    location.href = base + "order/orderFh?orderId="+orderId;
}

// 展示订单详情
function showOrderDetail(orderId,orderType){
    location.href = base + "order/orderDetail?orderId="+orderId+"&orderType="+orderType;
}