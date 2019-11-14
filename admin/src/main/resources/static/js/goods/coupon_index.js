
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function ($) {
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

    $("#find").on("click", function () {
        var couponName = $("input[name=couponName]").val();
        $("#grid-table").jqGrid('setGridParam', {
            postData: {"couponName": encodeURI(couponName)},
        }).trigger('reloadGrid');
    });
    jQuery(grid_selector).jqGrid({
        url: base + "coupon/searchCouponList",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','名称', '描述', '面值金额', '限制金额', '优惠劵类型', '开始时间', '有效期至', '使用对象', '使用范围','操作'],
        colModel: [
            {name: 'couponId', index: 'couponId', width: 200, editable: true, hidden: true, key: true},
            {name: 'couponName', index: 'couponName', width: 90},
            {name: 'couponDesc', index: 'couponDesc', width: 90},
            {name: 'couponValue', index: 'couponValue', width: 90},
            {name: 'limitMoney', index: 'limitMoney', width: 90},
            {name: 'limitMoneyDesc', index: 'limitMoneyDesc', width: 90},
            {name: 'couponBeginTime', index: 'couponBeginTime', width: 90,
                formatter: function (cellvalue, options, rowObject) {
                    if (typeof(cellvalue) != "undefined") {
                        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                    } else {
                        return '';
                    }
                }
            },
            {name: 'couponEndTime', index: 'couponEndTime', width: 90,
                formatter: function (cellvalue, options, rowObject) {
                    if (typeof(cellvalue) != "undefined") {
                        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                    } else {
                        return '';
                    }
                }
             },
           {name: 'id', index: 'id', width: 80, formatter: couponObject},
           {name: 'id', index: 'id', width: 80, formatter: couponFuc},
           {name: 'couponId', index: 'couponId', width: 80, formatter: operFuc}
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
        autowidth: true,
        autohight: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            editfunc: editCoupon,
            edittext: "编辑",
            edittitle: "编辑优惠劵",
            add: true,
            addtext: '添加',
            addtitle: "添加优惠劵",
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc: addCoupon,
            del: true,
            deltext: '删除',
            deltitle: '删除优惠劵',
            delfunc: del,
            search: false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh yellow',
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
        });
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

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

    /**
     * 添加优惠劵
     */
    function addCoupon() {
        location.href = base + "coupon/coupon_add_index";
    }

    /**
     *  编辑优惠劵
     * @param id
     * @returns {boolean}
     */
    function editCoupon(id) {
        var ids = $(grid_selector).jqGrid('getGridParam', "selarrrow");
        if (ids) {
            if (ids.length > 1) {
                Modal.alert({msg: "请选择一条数据进行编辑", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
        }
        location.href = base + "coupon/coupon_edit_index?couponId=" +id;
    }
    //主图
    function mastImg(cellvalue, options, rowObject){
        if(cellvalue)
            return   '<a title="优惠劵图片" href="'+cellvalue+'" ' +
                'target="_blank"><img src="' +cellvalue + '" style="width:100px;height: 34px;" alt="优惠劵图片"> ' +
                '</img></a>';
        else
            return "无优惠劵图片";
    }

    //使用对象
    function couponObject(cellvalue, options, rowObject) {
        return '<a  class="btn btn-white btn-sm btn-primary"  title="使用对象" href="javascript:void(0);" ' +
            'onclick="editCouponObject(\'' + rowObject.couponId + '\')" >使用对象 ' +
            '</a>';
    }
    //使用范围
    function couponFuc(cellvalue, options, rowObject) {
        if(rowObject.couponType == 2){ //全场优惠劵
            return "全部商品";
        }else{
            return '<a  class="btn btn-white btn-sm btn-primary"  title="使用范围" href="javascript:void(0);" ' +
                'onclick="editCouponGoods(\'' + rowObject.couponId + '\',\'' + rowObject.couponType + '\')" >使用范围 ' +
                '</a>';
        }
    }
    // 操作
    function operFuc(cellvalue, options, rowObject) {
        return '<a  class="btn btn-white btn-sm btn-primary"  title="优惠劵详情" href="javascript:void(0);" ' +
            'onclick="detail(\'' + rowObject.couponId + '\')" >查看详情 ' +
            '</a>';
    }

    //删除商品
    function del(ids){
        Modal.confirm({msg: "删除商品会清空该商品的库存，还要确认删除吗？",
            title: "提示",
            btnok: "确定",
            btncl: "取消"
        }).on( function (e) {
            if(e){// 如果点击了确定，则执行删除操作
                if (ids) {
                    ids = ids.toString().split(",");
                    $.each(ids, function (i, id) {
                        $.ajax({
                            async: false,
                            url: base + "coupon/removeCoupon",
                            data:{"couponId":id},
                            dataType: "json",
                            success: function (data) {
                                if(data == 1){
                                    Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定"});
                                    jQuery(grid_selector).trigger("reloadGrid");
                                }else{
                                    Modal.alert({msg: "删除失败！", title: "提示", btnok: "确定"});
                                }
                            }
                        });
                    });
                }
                jQuery(grid_selector).trigger("reloadGrid");
            }
        });

    }
});

//优惠劵详情
function detail(couponId){
    location.href = base + "coupon/coupon_detail_index?couponId=" + couponId;
}
//使用范围
function editCouponGoods(couponId,couponType){
        if(couponType == 0){ //优惠商品
            location.href = base + "coupon/coupon_goods_index?couponId=" + couponId;
        }else if(couponType ==1){ //优惠课程 //待定
        }

}
//使用对象
function editCouponObject(couponId){
        location.href = base + "coupon/coupon_user_index?couponId=" + couponId;

}