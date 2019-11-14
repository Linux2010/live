jQuery(function($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(grid_selector).jqGrid({
        url : base + "/profitshare/relation/getRelationPage",
        subGrid : true,
        subGridOptions : {
            plusicon : "ace-icon fa fa-plus center bigger-110 blue",
            minusicon  : "ace-icon fa fa-minus center bigger-110 blue",
            openicon : "ace-icon fa fa-chevron-right center orange"
        },
        datatype: "json",
        colNames:['id','登录名','用户名','昵称','用户身份','手机号','操作'],
        colModel:[
            {name: 'relationId', index: 'relationId', width: 200, editable: true, hidden:true, key: true},
            {name:'loginName',index:'loginName',width:90},
            {name:'userName',index:'userName', width:90},
            {name:'nickName',index:'nickName', width:90},
            {name: 'userIdentity', index: 'userIdentity', width: 120,formatter:function (cellvalue, options, rowObject){return userIdentity(cellvalue,rowObject);}},
            {name:'phone',index:'phone', width:90},
            {name:'userId',index:'userId', width:90, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="editParentRaltion(\''+rowObject.relationId+'\')">修改上级</a>';
                }
            }
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
        rowNum:20,
        rowList:[10,20,30],
        pager : pager_selector,
        altRows: true,
        multiselect: true,
        //multikey: "ctrlKey",
        //toppager: true,
        multiboxonly: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },
        caption: "一级分润",
        autowidth: true,
        height:"100%",
        subGridRowExpanded: function(subgrid_id, row_id) { //Grid内部嵌套Grid
            console.info(subgrid_id);
            console.info(row_id);
            firstSubGridRowExpanded(subgrid_id, row_id);
        }
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

    //操作按钮，true为显示，false为隐藏，xxxfunc:调用函数,xxxicon:图标
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            editfunc: edit,
            add: true,
            addicon : 'ace-icon fa fa-plus-circle purple',
            addfunc : add,
            del: true,
            delfunc: del,
            search:false,
            refresh: true
        }
    );


    $(document).one('ajaxloadstart.page', function(e) {
        $.jgrid.gridDestroy(grid_selector);
        $('.ui-jqdialog').remove();
    });

    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if( ("title_html" in this.options) && this.options.title_html == true )
                title.html($title);
            else title.text($title);
        }
    }));
});

$("#user-search").bind("click",function(){
    var keyword = $("#user-keyword-1").val();
    $("#grid-table").jqGrid('setGridParam',{
        postData:{"keyword":keyword},

    }).trigger('reloadGrid');
});

var firstSubGridRowExpanded = function(subgrid_id, row_id){
     location.href = base + "profitshare/relation/pageskip?relationId=" + row_id + "&level=2";
}


//更新分页按钮
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

var userIdentity = function(v,r){
    if (v == 1) {
        return "普通用户";
    } else if (v == 2) {
        return "会员用户";
    }else{
        return "";
    }
}
var editParentRaltion = function (relationId) {
  //  if(isD(relationId)){
        location.href = base + "profitshare/relation/editParentRaltion?relationId="+relationId+"&level=1";
   // }
}
var add = function(){
    location.href = base + "profitshare/relation/stairAdd";
}

var edit = function(relationId){
    if(isD(relationId)){
        location.href = base + "profitshare/relation/getRelationByBusiId?businessId="+relationId+"&level=1";
    }
}

var del = function(relationId){
    if(isD(relationId)){
        $.ajax({
            async: false,
            url: base + "profitshare/relation/deleteRelation?relationId="+relationId,
            dataType: "json",
            success: function (data) {
                if("success" == data.status){
                    Modal.alert({msg: '操作成功', title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(".subgird").trigger("reloadGrid");
                }else{
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            }
        })
    }
}

var isD = function(relationId){
    var result = false;
    $.ajax({
        async: false,
        url: base + "profitshare/relation/isD?relationId="+relationId,
        dataType: "json",
        success: function (data) {
            if("success" == data.status){
                result = true;
            }else{
                Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
            }
        }
    })
    return result;
}





