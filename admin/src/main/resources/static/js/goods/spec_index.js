var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "spec/searchSpecList",
        subGrid : false,
        datatype: "json",
        postData:{specName:"",parentId:-1},
        height: 'auto',
        colNames:['规格名称','规格备注','是否显示','序号','操作'],
        colModel:[
            {name:'specName', index:'specName', width:100, formatter:linkChild},
            {name:'remark',index:'remark', width:30},
            {name:'status',index:'status', width:30, formatter:showStatusVal},
            {name:'seqno', index:'seqno', width:100},
            {name:'id',index:'id',width:20, formatter : operFuc}
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
            refreshicon: 'ace-icon fa fa-refresh green'
        }
    );

    // 展示状态的值
    function showStatusVal(cellvalue, options, rowObject){
        if(cellvalue == 0){
            return "否";
        }
        if(cellvalue == 1){
            return "是";
        }
    }

    // 展示子节点列表
    function linkChild(cellvalue, options, rowObject){
       var urlVal = base + "spec/spec_values_index?specId="+rowObject.specId;
       return '<a href="'+urlVal+'" >'+cellvalue+'</a>';
    }

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        debugger;
        return '<a title="编辑" href="javascript:void(0);" ' +
            'onclick="editCt(\'' + rowObject.specId + '\',\'' + rowObject.specName + '\',\'' + rowObject.status + '\',\'' + rowObject.seqno + '\',\'' + rowObject.remark + '\')" > ' +
            '<i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>'+
            '&nbsp;&nbsp;&nbsp;'+
            '<a title="删除" href="javascript:void(0);" ' +
            'onclick="deleteCt(\'' + rowObject.specId + '\')" > ' +
            '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
    }

    // 模糊查询
    $("#goods-spec-search").bind("click",function(){
        var specName =$("#searchForm").find("#specName").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{specName:specName}
        }).trigger('reloadGrid');
    });

    // 显示商品分类添加窗口
    $("#goods-spec-add").click(function(){
        $("#addModal-spec").modal("show");// 打开模态窗口
    });

    // 添加规格
    $("#addGoodsSpec").click(function(){
        var statusVal = "";
        if($("#addModal-spec").find("#status").first().prop("checked")){
            statusVal = "0";
        }
        if($("#addModal-spec").find("#status").last().prop("checked")){
            statusVal = "1";
        }
        debugger;
        var specName = $("#addModal-spec").find("#specName").val();
        if(!specName){
            Modal.alert({msg : "规格名称必填", title : "提示", btnok : "确定"});
            return false;
        }
        var remark = $("#addModal-spec").find("#remark").val();
        $("#addGoodsSpec").attr("disabled",true);
        var  seqno =  $("#addModal-spec").find("#seqno").val();
        $.ajax({
            async: true,
            type: "POST",
            url: base + "spec/addSpec",
            data: {
                specName:specName,
                status:statusVal,
                remark:remark,
                seqno:seqno
            },
            dataType: "json",
            success: function (data) {
                $("#addGoodsSpec").attr("disabled",false);
                if(data == 0){
                    Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定"});
                    emptyForm('add-form-goodsSpec');
                    $("#addModal-spec").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#addGoodsSpec").attr("disabled",false);
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
    });

    // 修改规格
    $("#updateGoodsSpec").click(function(){
        var statusVal = "";
        if($("#update-form-goodsSpec").find("#status").first().prop("checked")){
            statusVal = "0";
        }
        if($("#update-form-goodsSpec").find("#status").last().prop("checked")){
            statusVal = "1";
        }
        var specName = $("#update-form-goodsSpec").find("#specName").val();
        if( !specName){
            Modal.alert({msg: "规格名称必填！",title: "提示", btnok: "确定"});
            return false;
        }
        var remark =  $("#update-form-goodsSpec").find("#remark").val();
        var  seqno = $("#updateGoodsSpec").find("#seqno").val();
        $("#updateGoodsSpec").attr("disabled",true);
        $.ajax({
            async: true,
            type: "POST",
            url: base + "spec/modifySpec",
            data: {
                specId:$("#update-form-goodsSpec").find("#specId").val(),
                specName:specName,
                status:statusVal,
                remark:remark,
                seqno:seqno
            },
            dataType: "json",
            success: function (data) {
                $("#updateGoodsSpec").attr("disabled",false);
                if(data == 0){
                    Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定"});
                    emptyForm('update-form-goodsSpec');
                    $("#updateModal-spec").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#updateGoodsSpec").attr("disabled",false);
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
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
        });
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

});

// 显示修改规格窗口
function editCt(specId,specName,status,sqeno,remark){
    $("#updateModal-spec").modal("show");// 打开模态窗口
    $("#updateModal-spec").find("#specId").val(specId);
    $("#updateModal-spec").find("#specName").val(specName);
    if(status == 0){
        $("#updateModal-spec").find("#status").first().prop("checked",true);
    }
    if(status == 1){
        $("#updateModal-spec").find("#status").last().prop("checked",true);
    }
    $("#updateModal-spec").find("#seqno").val(sqeno);
    $("#updateModal-spec").find("#remark").val(remark);
}

// 删除规格
function deleteCt(specId){
    debugger;
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则执行删除操作
            $.ajax({
                async: false,
                url: base + "spec/removeSpec",
                data:{specId:specId},
                dataType: "json",
                success: function (data) {
                    if(data == 0){
                        Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定"});
                        jQuery(grid_selector).trigger("reloadGrid");
                    }else{
                        Modal.alert({msg: "删除失败！", title: "提示", btnok: "确定"});
                    }
                }
            });
        }
    });
    jQuery(grid_selector).trigger("reloadGrid");

}