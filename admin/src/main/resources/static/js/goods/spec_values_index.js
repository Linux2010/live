var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
debugger;
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "specValues/searchSpecValuesList",
        subGrid : false,
        datatype: "json",
        postData:{valuesName:"",specId:$("#specId").val()},
        height: 'auto',
        colNames:['规格值名称','类型','分类图片','规格值备注','是否显示','序号','操作'],
        colModel:[
            {name:'valuesName', index:'valuesName', width:100},
            {name:'type',index:'type', width:30, formatter:showType},
            {name:'imageUrl',index:'imageUrl', width:30, formatter:typeImg},
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

    //展示规格值
    function showType(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "文字";
        }
        if(cellvalue == 2){
            return "图片";
        }
    }

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        debugger;
        return '<a title="编辑" href="javascript:void(0);" ' +
            'onclick="editCt(\'' + rowObject.goodsSpecValuesId + '\',\'' + rowObject.valuesName + '\',\'' + rowObject.status + '\',\'' + rowObject.type + '\',\'' + rowObject.seqno + '\',\'' + rowObject.remark + '\')" > ' +
            '<i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>'+
            '&nbsp;&nbsp;&nbsp;'+
            '<a title="删除" href="javascript:void(0);" ' +
            'onclick="deleteCt(\'' + rowObject.goodsSpecValuesId + '\')" > ' +
            '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
    }

    // 模糊查询
    $("#spec-values-search").bind("click",function(){
        var valuesName = $("#searchForm").find("#valuesName").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{valuesName:valuesName,specId:$("#specId").val()}
        }).trigger('reloadGrid');
    });

    //选择规格值类型
    $("#addModal-specValues input[name=type]").change(function () {
        if($(this).is(":checked") && $(this).val() == 1){
            $("#addModal-specValues .sepecValueType").css({"display":"none"});
        }else{
            $("#addModal-specValues .sepecValueType").css({"display":"block"});
        }
    });

    //选择规格值类型
    $("#updateModal-specValues input[name=type]").change(function () {
        if($(this).is(":checked") && $(this).val() == 1){
            $("#updateModal-specValues .sepecValueType").css({"display":"none"});
        }else{
            $("#updateModal-specValues .sepecValueType").css({"display":"block"});
        }
    });

    $('#addModal-specValues #seqno').ace_spinner({value:1,min:1,max:10000,step:1, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});

    $('#updateModal-specValues #seqno').ace_spinner({value:1,min:1,max:10000,step:1, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});

    // 显示商品分类添加窗口
    $("#spec-values-add").click(function(){
        $("#addModal-specValues").modal("show");// 打开模态窗口
    });

    // 添加规格
    $("#addSpecValues").click(function(){
        $("#addSpecValues").attr("disabled",true);
        $("#add-form-specValues").ajaxSubmit({
            type:"post",
            dataType:"json",
            url:base+"specValues/addSpecValues",
            success: function(data) {
                $("#addSpecValues").attr("disabled",false);
                if(data == 0){
                    Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定"});
                    emptyForm('add-form-specValues');
                    $("#addModal-specValues").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#addSpecValues").attr("disabled",false);
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });

    });

    // 修改规格
    $("#updateSpecValues").click(function(){
        var statusVal = "";
        if($("#update-form-specValues").find("#status").first().prop("checked")){
            statusVal = "0";
        }
        if($("#update-form-specValues").find("#status").last().prop("checked")){
            statusVal = "1";
        }
        var type="" ;
        if($("#update-form-specValues").find("#type").first().prop("checked")){
            type = "1";
        }
        if($("#update-form-specValues").find("#type").last().prop("checked")){
            type = "2";
        }
        var valuesName = $("#update-form-specValues").find("#valuesName").val();
        if( !valuesName){
            Modal.alert({msg: "规格名称必填！",title: "提示", btnok: "确定"});
            return false;
        }
        var remark =  $("#update-form-specValues").find("#remark").val();
        var  seqno = $("#update-form-specValues").find("#seqno").val();
        $("#updateSpecValues").attr("disabled",true);
        $("#update-form-specValues").ajaxSubmit({
            type:"post",
            dataType:"json",
            url:base+"specValues/modifySpecValues",
            success: function(data) {
                $("#updateSpecValues").attr("disabled",false);
                if(data == 0){
                    Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定"});
                    emptyForm('update-form-goodsSpec');
                    $("#updateModal-specValues").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#updateSpecValues").attr("disabled",false);
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
        // $.ajax({
        //     async: true,
        //     type: "POST",
        //     url: base + "specValues/modifySpecValues",
        //     data: {
        //         goodsSpecValuesId:$("#update-form-specValues").find("#goodsSpecValuesId").val(),
        //         valuesName:valuesName,
        //         status:statusVal,
        //         type:type,
        //         remark:remark,
        //         seqno:seqno
        //     },
        //     dataType: "json",
        //     success: function (data) {
        //         $("#updateSpecValues").attr("disabled",false);
        //         if(data == 0){
        //             Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定"});
        //             emptyForm('update-form-goodsSpec');
        //             $("#updateModal-specValues").modal("hide");
        //             jQuery(grid_selector).trigger("reloadGrid");
        //         }else{
        //             Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定"});
        //         }
        //     },
        //     error:function(data){
        //         $("#updateSpecValues").attr("disabled",false);
        //         Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        //     }
        // });
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
function editCt(goodsSpecValuesId,valuesName,status,type,sqeno,remark){
    $("#updateModal-specValues").modal("show");// 打开模态窗口
    $("#update-form-specValues").find("#specId").val(specId);
    $("#update-form-specValues").find("#valuesName").val(valuesName);
    if(status == 0){
        $("#update-form-specValues").find("#status").first().prop("checked",true);
    }
    if(status == 1){
        $("#update-form-specValues").find("#status").last().prop("checked",true);
    }
    if(type == 1){
        $("#update-form-specValues").find("#type").first().prop("checked",true);
        $("#update-form-specValues").find(".sepecValueType").hide();
    }
    if(status == 2){
        $("#update-form-specValues").find("#type").last().prop("checked",true);
        $("#update-form-specValues").find(".sepecValueType").show();
    }
    $("#update-form-specValues").find("#seqno").val(sqeno);
    $("#update-form-specValues").find("#remark").val(remark);

    $("#update-form-specValues").find("#goodsSpecValuesId").val(goodsSpecValuesId);
}

// 删除规格值
function deleteCt(goodsSpecValuesId){
    debugger;
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则执行删除操作
            $.ajax({
                async: false,
                url: base + "specValues/removeSpecValues",
                data:{goodsSpecValuesId:goodsSpecValuesId},
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


// 展示状态的值
function typeImg(cellvalue, options, rowObject){
    if(cellvalue)
        return   '<a title="点击查看大图" href="javascript:void(0);" ' +
            'onclick="showImg(\'' +cellvalue + '\')" ><img src="' +cellvalue + '" style="width:50px;height: 30px;" alt="点击查看大图"> ' +
            '</img></a>';
    else
        return "无图片";
}

// 显示商品分类图片
function showImg(imageUrl){
    $("#imgModal-type").modal("show");// 打开模态窗口
    $("#imgModal-type").find("#img").attr("src",imageUrl);

}
// 返回
function back(){
    location.href = base + "spec/spec_index";
}