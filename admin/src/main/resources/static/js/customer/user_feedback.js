var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "userfeedback/list",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        colNames:['id','用户id','内容','用户名','姓名','联系方式','处理状态','创建时间','操作'],
        colModel:[
            {name: 'feedbackId', index: 'feedbackId', width: 0, editable: true, hidden:true, key: true},
            {name: 'userId', index: 'userId', width: 0, editable: true, hidden:true, key: true},
            {name:'content',index:'content',width:90},
            {name:'userName',index:'userName', width:90},
            {name:'name',index:'name', width:90},
            {name:'phone',index:'phone', width:90},
            {name:'dealStatus',index:'dealStatus', width:90,formatter:function(cellvalue, options, rowObject) {return dealStatus(cellvalue);}},
            {name:'createTime',index:'createTime',width:90,formatter:function(cellvalue, options, rowObject) {
                if(isNotEmpty(cellvalue)){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }},
            {name:'feedbackId',index:'feedbackId', width:200, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                var dealStatus = rowObject.dealStatus;
                    if(dealStatus == '0'){
                        return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="edit(\''+cellvalue+'\')">解决</a>'+
                            '<a type="button" class="btn btn-white btn-sm btn-primary" style="margin-left: 10px;" onclick="deleteSet(\''+cellvalue+'\')">删除</a>';
                    }else{
                        return '<a type="button" class="btn btn-white btn-sm btn-primary" onclick="edit(\''+cellvalue+'\')">查看</a>'+
                            '<a type="button" class="btn btn-white btn-sm btn-primary" style="margin-left: 10px;" onclick="deleteSet(\''+cellvalue+'\')">删除</a>';
                    }
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
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        altRows: true,
        multiselect: false,
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
        caption: "",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

    //操作按钮，true为显示，false为隐藏，xxxfunc:调用函数,xxxicon:图标
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            editfunc: edit,
            add: false,
            addicon : 'ace-icon fa fa-plus-circle purple',
            //addfunc : add,
            del: false,
            delfunc: deleteSet,
            search:false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green'
        }
    )

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

    //模糊查询
    $("#user-search").bind("click",function(){
        var keyword = $("#user-keyword-1").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword)}
        }).trigger('reloadGrid');
    });

    //保存数据
    $("#addBtn").on('click',function(){

        if(!verifyValue($("#type_add").val())){
            return;
        }
        $("#addBtn").attr("disabled",true);

        $.ajax({
            async: true,
            type: "POST",
            url: base + "profitshareset/addSet",
            data: $("#form_add").serialize(),
            dataType: "json",
            success: function (data) {
                $("#addBtn").attr("disabled",false);
                if("success" == data.status){
                    Modal.alert({msg : "保存成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    emptyForm('form_add');
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error:function(data){
                $("#addBtn").attr("disabled",false);
                alert("系统异常，请稍后重试");
            }
        })
    })





    $("#updateBtn").click(function () {

        $.ajax({
            async: true,
            url: base + "profitshareset/updateSet",
            type: "POST",
            data: $("#form_update").serialize(),
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                    $("#updateModal-type").modal("hide");
                }else{
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        });
    });

    //查出对应的用户信息
    function editInt(id) {
        var data = "";
        $.ajax({
            async: false,
            type: "POST",
            url: base + "profitshareset/getSetting",
            data:{id:id},
            dataType: "json",
            success: function (wd) {
                if("success" == wd.status){
                    data = wd.data;
                }
            },
            error:function(wd){
                alert("系统异常，请稍后重试");
            }
        });
        return data;
    }

    //校验对应数据
    var verifyValue = function(val){
        var result = false;
        if(isEmpty(val)) {
            Modal.alert({msg: "分润类型不能为空！", title: "提示", btnok: "确定", btncl: "取消"});
            return result;
        }
        result = true;
        return result;
    }

    //类别值
    var typeValue = function(val){
        var str = "";
        if(1==val){
            str ="<span class=\"label\">课程分润</span>";
        }else if(2==val){
            str ="<span class=\"label\">商品分润</span>";
        }else if(3==val){
            str ="<span class=\"label\">会员分润</span>";
        }
        return str;
    }

    //是否开启值
    var dealStatus = function(val){
        var str = "";
        if(0==val){
            str ="<span class=\"label label-info\" >未处理</span>";
        }else if(1==val){
            str ="<span class=\"label label-grey arrowed-in-right arrowed-in\">已处理</span>";
        }else if(2 == val){
            str ="<span class=\"label label-grey arrowed-in-right arrowed-in\">已关闭</span>";
        }
        return str;
    }
});


//删除操作
function deleteSet(id) {
    if(!confirm("确认要删除此条数据？")){
        return;
    };
    $.ajax({
        async: false,
        url: base + "userfeedback/deleteRecord?id=" + id,
        dataType: "json",
        success: function (data) {
            Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定", btncl: "取消"});
            jQuery(grid_selector).trigger("reloadGrid");
        }
    })
    jQuery(grid_selector).trigger("reloadGrid");
}

//修改操作
function edit(id) {
    location.href = base + "userfeedback/add?businessId="+id;
}
