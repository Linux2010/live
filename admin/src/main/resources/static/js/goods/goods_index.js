
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
        var keyword = $("input[name=keyword]").val();
        $("#grid-table").jqGrid('setGridParam', {
            postData: {"keyword": encodeURI(keyword)},
        }).trigger('reloadGrid');
    });
    jQuery(grid_selector).jqGrid({
        url: base + "goods/searchGoodsList",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id', '主图','商品编号', '商品名称', '商品类别', '商品成本价', '市场价格', '会员价', '是否下架', '商品简介', '创建时间', '操作'],
        colModel: [
            {name: 'goodsId', index: 'goodsId', width: 200, editable: true, hidden: true, key: true},
            {name: 'mastImg', index: 'mastImg', width: 50,formatter: mastImg},
            {name: 'goodsNumber', index: 'goodsNumber', width: 90},
            {name: 'goodsName', index: 'goodsName', width: 90},
            {name: 'goodsType', index: 'courseName', width: 90,formatter: goodsType},
            {name: 'costPrice', index: 'costPrice', width: 90},
            {name: 'mktPrice', index: 'mktPrice', width: 90},
            {name: 'price', index: 'price', width: 90},
            {name: 'isSoldOut', index: 'isSoldOut', width: 90, formatter: isSoldOut},
            {name: 'intro', index: 'intro', width: 90, formatter: intro},
            {
                name: 'createTime',
                index: 'createTime',
                width: 90,
                formatter: function (cellvalue, options, rowObject) {
                    if (typeof(cellvalue) != "undefined") {
                        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                    } else {
                        return '';
                    }
                }
            },
            {name: 'goodsId', index: 'goodsId', width: 80, formatter: operFuc}
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
            editfunc: editGoods,
            edittext: "编辑",
            edittitle: "编辑商品",
            add: true,
            addtext: '添加',
            addtitle: "发布商品",
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc: addGoods,
            del: true,
            deltext: '删除',
            deltitle: '删除商品',
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
     * 发布商品
     * @param courseId 课程ID
     * @param courseName 课程名称
     */
    function addGoods() {
        location.href = base + "goods/goods_add_index";
    }

    /**
     *  编辑商品
     * @param id
     * @returns {boolean}
     */
    function editGoods(id) {
        var ids = $(grid_selector).jqGrid('getGridParam', "selarrrow");
        if (ids) {
            if (ids.length > 1) {
                Modal.alert({msg: "请选择一条数据进行编辑", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
        }
        location.href = base + "goods/goods_edit_index?goodsId=" + id;
    }


//商品详情
    function  intro(cellvalue, options, rowObject){
        return '<a  class="btn btn-white btn-sm btn-primary"  title="商品简介" href="javascript:void(0);" ' +
            'onclick="ueIntro(\'' + rowObject.goodsId + '\',\'' + rowObject.cellvalue + '\')" > ' +
            '编辑简介</a>';
    }
    //商品类型
    function goodsType(cellvalue, options, rowObject){ //商品类别标识(0:普通商品;1:银两商品)
        if(cellvalue == 1){
            return '银两商品';
        }
        if(cellvalue == 0){
            return '普通商品';
        }
    }
    //主图
    function mastImg(cellvalue, options, rowObject){
        if(cellvalue)
            return   '<a title="商品主图" href="'+cellvalue+'" ' +
                'target="_blank"><img src="' +cellvalue + '" style="width:35px;height: 34px;" alt="商品主图"> ' +
                '</img></a>';
        else
            return "无主图";
    }
    //是否下架0：下架;1：上架;
    function  isSoldOut(cellvalue, options, rowObject) {
        if(cellvalue == 1){
            return '上架';
        }
        if(cellvalue == 0){
            return '下架';
        }
    }

// 操作
    function operFuc(cellvalue, options, rowObject) {
        return '<a  class="btn btn-white btn-sm btn-primary"  title="商品详情" href="javascript:void(0);" ' +
            'onclick="detail(\'' + rowObject.goodsId + '\')" >商品详情 ' +
            '</a>&nbsp;&nbsp;&nbsp;&nbsp;';
            //+
           // '<a  class="btn btn-white btn-sm btn-primary"  title="规格管理" href="javascript:void(0);" ' +
           //'onclick="editGoodsSpec(\'' + rowObject.goodsId + '\',\'' + rowObject.specNum + '\')" >规格管理 ' +
           // '</a>';
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
                            url: base + "goods/removeGoods",
                            data:{goodsId:id},
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

//商品详情
function detail(goodsId){
    location.href = base + "goods/goods_detail_index?goodsId=" + goodsId;
}
//商品规格修改
function editGoodsSpec(goodsId,specNum){
    //判断该商品是否有规格,有规格跳转到修改页面，无规格跳转到添加页面
    if(specNum > 0){
        location.href = base + "goods/goods_spec_edit_index?goodsId=" + goodsId;
    }else{
        location.href = base + "goods/goods_spec_add_index?goodsId=" + goodsId;
    }
}
//打开商品详情页添加或修改商品详情
function ueIntro(goodsId,intro){
   // $("#goods-intro-Modal").modal("show");//打开模态窗口
   // $("#goods-intro-Modal").find("input[name=goodsIntro]").val(goodsId);
    location.href = base + "goods/goods_intro_index?goodsId=" + goodsId;

}