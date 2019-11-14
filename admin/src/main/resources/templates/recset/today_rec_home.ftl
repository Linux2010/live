<#assign title="今日推荐"/>
<#assign menuId="54"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div class="col-xs-12">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script type="text/javascript">
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

            url: "${ctx}/todayrec/list",
            subGrid: false,
            datatype: "json",
            height: 325,
            colNames: ['id', '标签名称','操作'],
            colModel: [
                {name: 'recLabType', index: 'recLabType', width: 200, editable: true, hidden: true, key: true},
                {name: 'recLabName', index: 'recLabName', width: 400},
                {name:'recLabType',index:'recLabType', width:800, fixed:true, sortable:false, resize:false,
                    formatter: function(cellvalue, options, rowObject) {
                        return "<i class='ace-icon fa fa-pencil-square-o bigger-200' onclick='tealist("+cellvalue
                                +")'></i>";
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
        var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
        //navButtons
        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                {
                    edit: true,
                    add: true,
                    addicon : 'ace-icon fa fa-plus-circle purple',
                    refresh: true,
                    refreshicon: 'ace-icon fa fa-refresh yellow',
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


    var grid_selector = "#grid-table";
    function tealist(id){
        var se_id = jQuery(grid_selector).jqGrid('getGridParam', 'selrow');
        console.log(se_id);
        if (se_id) {
            //推荐的教头列表
            if(se_id == 1){
                window.location.href="${ctx}/todayrec/tea/";
            } else if(se_id == 2){//推荐的课程列表
                window.location.href="${ctx}/todayrec/tea/cour";
            }
        }else{
            alert("请选择你要操作的数据行!");
        }
    }


</script>
<#include "../common/body_bottom.ftl"/>
