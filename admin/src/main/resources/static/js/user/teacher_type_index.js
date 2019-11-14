// 存放课程分类ID
var userTypeIdVal = "";
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "userType/searchUserTypeList",
        subGrid : false,
        datatype: "json",
        postData:{typeName:"",typeVal:1,parentId:-1},
        height: 'auto',
        colNames:['分类名称','分类级别','是否显示','操作'],
        colModel:[
            {name:'typeName', index:'typeName', width:100, formatter:linkChild},
            {name:'typeLevel',index:'typeLevel', width:30, formatter:typeLevel},
            {name:'status',index:'status', width:30, formatter:showStatusVal},
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
    // 展示分类级别的值
    function typeLevel(cellvalue, options, rowObject){
        if(cellvalue == 1){ //分类级别：1代表一级分类，2代表二级分类
            return "一级分类";
        }
        if(cellvalue == 2){
            return "二级分类";
        }
    }

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
        var urlVal = base + "userType/userTypeChildren?parentId="+rowObject.userTypeId;
        return '<a href="'+urlVal+'">'+cellvalue+'</a>';
    }

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        return '<a title="编辑" href="javascript:void(0);" ' +
            'onclick="editCt(\'' + rowObject.userTypeId + '\',\'' + rowObject.typeName + '\',\'' + rowObject.status + '\')" > ' +
            '<i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>'+
            '&nbsp;&nbsp;&nbsp;'+
            '<a title="删除" href="javascript:void(0);" ' +
            'onclick="deleteCt(\'' + rowObject.userTypeId + '\')" > ' +
            '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
    }

    // 模糊查询
    $("#user-type-search").bind("click",function(){
        var typeVal = 1;//$("#typeVal").val();
        var typeName = $("#typeName").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{typeName:typeName,typeVal:typeVal}
        }).trigger('reloadGrid');
    });

    // 显示教师分类添加窗口
    $("#user-type-add").click(function(){
        $("#addModal-type").modal("show");// 打开模态窗口
        $("#parentType").text($("#typeVal").find("option:selected").text());// 显示上级分类
    });

    // 添加教师分类
    $("#addUserType").click(function(){
        var statusVal = "";
        if($("#status_0_add").prop("checked")){
            statusVal = "0";
        }
        if($("#status_1_add").prop("checked")){
            statusVal = "1";
        }
        if(!verifyValue($("#typename_add").val(),statusVal)){
            return false;
        }
        $("#addUserType").attr("disabled",true);
        $.ajax({
            async: true,
            type: "POST",
            url: base + "userType/addUserType",
            data: {
                typeVal:1,
                typeName:$("#typename_add").val(),
                status:statusVal,
                parentId:-1,
                typeLevel:1
            },
            dataType: "json",
            success: function (data) {
                $("#addUserType").attr("disabled",false);
                if(data == 0){
                    Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定"});
                    emptyForm('add-form-userType');
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#addUserType").attr("disabled",false);
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    });

    // 修改教师分类
    $("#updateUserType").click(function(){
        var statusVal = "";
        if($("#status_0_update").prop("checked")){
            statusVal = "0";
        }
        if($("#status_1_update").prop("checked")){
            statusVal = "1";
        }
        if(!verifyValue($("#typename_update").val(),statusVal)){
            return false;
        }
        $("#updateUserType").attr("disabled",true);
        $.ajax({
            async: true,
            type: "POST",
            url: base + "userType/modifyUserType",
            data: {
                userTypeId:userTypeIdVal,
                typeName:$("#typename_update").val(),
                status:statusVal
            },
            dataType: "json",
            success: function (data) {
                $("#updateUserType").attr("disabled",false);
                if(data == 0){
                    Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定"});
                    emptyForm('update-form-userType');
                    $("#updateModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#updateUserType").attr("disabled",false);
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    });

    // 校验对应数据
    function verifyValue(typeName,status){
        var result = false;
        if(isEmpty(typeName)){
            Modal.alert({msg: "分类名称不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(status)){
            Modal.alert({msg: "显示状态不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        result = true;
        return result;
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

});

// 显示修改教师分类窗口
function editCt(userTypeId,typeName,status){
    $("#updateModal-type").modal("show");// 打开模态窗口
    userTypeIdVal = userTypeId;
    $("#typename_update").val(typeName);
    if(status == 0){
        $("#status_0_update").prop("checked",true);
    }
    if(status == 1){
        $("#status_1_update").prop("checked",true);
    }
}

// 删除教师分类
function deleteCt(userTypeId){
    debugger;
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则执行删除操作
            $.ajax({
                async: false,
                url: base + "userType/removeUserType",
                data:{userTypeId:userTypeId},
                dataType: "json",
                success: function (data) {
                    if(data == 0){
                        Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定"});
                        jQuery(grid_selector).trigger("reloadGrid");
                    }else{
                        Modal.alert({msg: "删除失败！", title: "提示", btnok: "确定"});
                    }
                }
            })
        }
    });
    jQuery(grid_selector).trigger("reloadGrid");

}