var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "sysDoc/searchSdList",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        loadtext: '正在加载...',
        colNames:['文案类型','文案内容','创建时间','操作'],
        colModel:[
            {name:'docType',index:'docType',width:100,formatter:showDocType},
            {name:'docType',index:'docType',width:100,formatter:showDocContent},
            {name:'createTime',index:'createTime',width:100,formatter:showFormatTime},
            {name:'id',index:'id',width:30,formatter:operFuc}
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
        return '<a title="编辑" href="javascript:void(0);" ' +
            'onclick="modifySdc(\'' + rowObject.id + '\',\''+rowObject.docType+'\')" >编辑</a>';
    }

    // 展示文案类型
    function showDocType(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "学分制度";
        }else if(cellvalue == 2){
            return "激活码制度";
        }else if(cellvalue == 3){
            return "邀请好友规则";
        }else if(cellvalue == 4){
            return "积分制度";
        }else{
            return "";
        }
    }

    // 展示格式化时间
    function showFormatTime(cellvalue, options, rowObject){
        if(cellvalue == null || cellvalue == 0){
            return "";
        }else{
            return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
        }
    }

    // 模糊查询
    $("#user-group-card-search").bind("click",function(){
        var docTypeVal = $("#docType").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{
                docType:docTypeVal
            }
        }).trigger('reloadGrid');
    });

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

});

// 展示文案内容
function showDocContent(cellvalue, options, rowObject){
    var urlVal = base+"sysDoc/showSdDetail?docType="+rowObject.docType;
    return "<a href='"+urlVal+"'>查看</a>";
}

// 编辑系统文案
function modifySdc(id,docType){
    location.href=base+"sysDoc/showSdEdit?id="+id+"&docType="+docType;
}