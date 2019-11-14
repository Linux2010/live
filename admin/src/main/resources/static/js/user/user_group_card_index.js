// 声明关联前台的用户ID全局变量
var linkUserId = "";
$(function(){
    // 查询关联的前台用户ID
    $.ajax({
        async: false,
        type: "POST",
        url: base + "sysuser/searchLinkUserId",
        data: {userId : $("#userId").val()},
        dataType: "text",
        success: function (data) {
            if(data != null){
                linkUserId = data;
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });
});
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "userGroupCard/searchUgcList",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        loadtext: '正在加载...',
        postData:{userId:linkUserId},
        colNames:['编号','卡号','密码','类型','创建时间','激活状态','激活者用户名','激活者手机号','激活时间','操作'],
        colModel:[
            {name:'cardNo',index:'cardNo',width:80,formatter:showBhVal},
            {name:'cardNo',index:'cardNo',width:100},
            {name:'cardPassword',index:'cardPassword',width:100},
            {name:'cardType',index:'cardType',width:50,formatter:showCardType},
            {name:'createTime',index:'createTime',width:100,formatter:showFormatTime},
            {name:'status',index:'status',width:50,formatter:showStatus},
            {name:'activeUserName',index:'activeUserName',width:80},
            {name:'activePhone',index:'activePhone',width:100},
            {name:'activeTime',index:'activeTime',width:100,formatter:showFormatTime},
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
        if(rowObject.status == 1){
            return '已用';
        }else{
            return '未用';
        }
    }

    // 展示编号
    function showBhVal(cellvalue, options, rowObject){
        var bhVal = ""
        if(cellvalue != null){
            bhVal = cellvalue.substr(2,cellvalue.length-1);
        }
        return bhVal;
    }

    // 展示团购卡类型
    function showCardType(cellvalue, options, rowObject){
        if(cellvalue == 1){
            return "年卡";
        }else if(cellvalue == 2){
            return "月卡";
        }else{
            return "年卡";
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

    // 展示激活卡状态
    function showStatus(cellvalue, options, rowObject){
        if(cellvalue == 0){
            return "未激活";
        }else if(cellvalue == 1){
            return "已激活";
        }else{
            return "未激活";
        }
    }

    // 模糊查询
    $("#user-group-card-search").bind("click",function(){
        var userIdVal = linkUserId;
        var cardNoVal = $("#cardNo").val();
        var cardTypeVal = $("#cardType").val();
        var statusVal = $("#status").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{
                userId:userIdVal,
                cardNo:cardNoVal,
                cardType:cardTypeVal,
                status:statusVal
            }
        }).trigger('reloadGrid');
    });

    // 导出用户团购卡
    $("#user-group-card-export").click(function(){
        Modal.confirm({msg: "确认导出吗？",
            title: "提示",
            btnok: "确定",
            btncl: "取消"
        }).on( function (e) {
            if(e){// 如果点击了确认，则进行用户团购卡信息的导出操作
                $(".cancel").click();// 关闭确认弹出框
                var urlVal = base + "userGroupCard/exportUgcList?userId="+linkUserId
                    +"&cardNo="+$("#cardNo").val()
                    +"&cardType="+$("#cardType").val()
                    +"&status="+$("#status").val();
                window.open(urlVal);
            }
        });
    });

    // 显示用户团购卡添加窗口
    function addUgc(){
        $("#addModal-type").modal("show");// 打开模态窗口
    }

    // 添加用户团购卡
    $("#addUserGroupCard").click(function(){
        var cardTypeVal = 1;
        if($("#cardType1").prop("checked")){
            cardTypeVal = 1;
        }
        if($("#cardType2").prop("checked")){
            cardTypeVal = 2;
        }
        if(!verifyValue($("#cardNum").val())){
            return false;
        }
        $("#addUserGroupCard").attr("disabled",true);
        $("#addUserGroupCard").text("添加中...");
        $.ajax({
            async: true,
            type: "POST",
            url: base + "userGroupCard/addUgc",
            data: {
                cardNum:parseInt($("#cardNum").val()),
                cardType:cardTypeVal,
                userId:linkUserId
            },
            dataType: "json",
            success: function (data) {
                $("#addUserGroupCard").attr("disabled",false);
                $("#addUserGroupCard").text("添 加");
                if(data == 0){
                    Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定"});
                    emptyForm('add-form-courseTag');
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#addUserGroupCard").attr("disabled",false);
                $("#addUserGroupCard").text("添 加");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        })
    });

    // 校验对应数据
    function verifyValue(cardNum){
        var result = false;
        if(isEmpty(cardNum)){
            Modal.alert({msg: "添加数量不能为空！",title: "提示", btnok: "确定"});
            return result;
        }else if(isNaN(cardNum)){
            Modal.alert({msg: "请以数字的形式输入！",title: "提示", btnok: "确定"});
            return result;
        }else if(parseInt(cardNum) <= 0){
            Modal.alert({msg: "请输入大于0的整数！",title: "提示", btnok: "确定"});
            return result;
        }else if(parseInt(cardNum) > 480){
            Modal.alert({msg: "每种类型的卡一次最多可添加480张！",title: "提示", btnok: "确定"});
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

// 删除用户团购卡
function deleteUgcById(id){
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则进行用户团购卡信息的删除操作
            $.ajax({
                async: false,
                url: base + "userGroupCard/removeUgcById",
                data:{id:id},
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
            jQuery(grid_selector).trigger("reloadGrid");
        }
    });
}