var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    init();
    jQuery(grid_selector).jqGrid({
        url : base + "user/searchUserTeacherIntroImgList",
        subGrid : false,
        datatype: "json",
        postData:{userId:$("#teacherId").val()},
        height: 'auto',
        colNames:['id','图片序号','图片','操作'],
        colModel:[
            {name: 'id', index: 'id', width: 50, editable: true, hidden: true, key: true},
            {name:'seqno', index:'seqno', width:50},
            {name:'url',index:'url', width:100, formatter:typeImg},
            {name:'teacherIntroImgId',index:'teacherIntroImgId',width:20, formatter : operFuc}
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
    function typeImg(cellvalue, options, rowObject){
       return   '<a title="点击查看大图" href="javascript:void(0);" ' +
           'onclick="showImg(\'' +cellvalue + '\')" ><img src="' +cellvalue + '" style="width:50px;height: 30px;" alt="点击查看大图"> ' +
           '</img></a>';
    }

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        return  '<a title="删除" href="javascript:void(0);" ' +
            'onclick="deleteCt(\'' + rowObject.teacherIntroImgId + '\')" > ' +
            '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
    }
    // 返回
    $("#teacher-introImg-back").click(function(){
        location.href = base + "user/teacher/";
    });

    // 显示教师分类添加窗口
    $("#teacher-introImg-add").click(function(){
        $("#addModal-type").modal("show");// 打开模态窗口
    });

    // 添加教师分类
    $("#addTeacherIntroImg").click(function(){
        $("#addTeacherIntroImg").attr("disabled",true);

        $("#add-form-introImg").ajaxSubmit({
            type:"post",
            dataType:"json",
            url:base+"user/insertUserTeacherIntroImg",
            success: function(data) {
                $("#addTeacherIntroImg").attr("disabled",false);
                if(data == 1){
                    Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定"});
                    emptyForm('add-form-introImg');
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定"});
                }
            },
            error:function(data){
                $("#addTeacherIntroImg").attr("disabled",false);
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
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

});


// 显示教头简介图片
function showImg(imageUrl){
    $("#imgModal-type").modal("show");// 打开模态窗口
    $("#imgModal-type").find("#img").attr("src",imageUrl);

}

// 删除教头简介图片
function deleteCt(teacherIntroImgId){
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则执行删除操作
            if (teacherIntroImgId) {
                $.ajax({
                    async: false,
                    url: base + "user/deleteUserTeacherIntroImg",
                    data:{teacherIntroImgId:teacherIntroImgId},
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
            }
            jQuery(grid_selector).trigger("reloadGrid");
        }
    });

}


function init(){

    $('#addModal-type').find("input[name=seqno]").ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });




    // 初始化上传文件输入框
    $('#addModal-type').find('#imageUrl').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });
}