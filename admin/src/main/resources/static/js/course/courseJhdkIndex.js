var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "courseJhdk/searchCList",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        loadtext: '正在加载...',
        colNames:['id','序号','课程封面','课程名称','课程内容','开始时间','结束时间','课程价格','会员价格','讲师名称','课程分类','详细地址','操作'],
        colModel:[
            {name: 'courseId', index: 'courseId', width: 10, editable: true, hidden:true, key: true},
            {name:'seqno',index:'seqno',width:20},
            {name:'courseCover',index:'courseCover',width:40,formatter:showCourseCover},
            {name:'courseTitle',index:'courseTitle',width:50},
            {name:'courseContent',index:'courseContent',width:25,formatter:showCourseContent},
            {name:'courseBeginTime',index:'courseBeginTime',width:40,formatter:showFormatDate},
            {name:'courseEndTime',index:'courseEndTime',width:25,formatter:showFormatDate},
            {name:'costPrice',index:'costPrice',width:20,formatter:showFreePrice},
            {name:'vipPrice',index:'vipPrice',width:20,formatter:showFreePrice},
            {name:'tname',index:'tname',width:30},
            {name:'tyname',index:'tyname',width:30},
            {name:'courseAddressDetail',index:'courseAddressDetail',width:50},
            {name:'id',index:'id',width:40,formatter:operFuc}
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
        multiselect: true,
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
            del: true,
            delicon : 'ace-icon fa fa-trash-o bigger-150',
            delfunc: delCourse,
            search:false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alertcap: '提示',
            alerttext:'请选择数据！',
            refreshtitle: '刷新列表'
        }
    );

    // 展示课程封面
    function showCourseCover(cellvalue, options, rowObject){
        if(!isEmpty(cellvalue)){
            return "<img src='"+cellvalue+"' style='width: 100px;'/>";
        }else{
            return "暂无封面";
        }
    }

    // 展示格式化后的日期
    function showFormatDate(cellvalue, options, rowObject){
        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm");
    }

    // 展示免费价格
    function showFreePrice(cellvalue, options, rowObject){
        if(cellvalue == 0){
            return "免费";
        }else{
            return cellvalue;
        }
    }

    // 查看课程题
    function showCourseTopic(cellvalue, options, rowObject){
        return "<a href='javascript:;' onclick='showCtDetail(\""+rowObject.courseId+"\")'>查看</a>";
    }

    // 查看课程内容
    function showCourseContent(cellvalue, options, rowObject){
        return "<a href='javascript:;' onclick='showContentDetail(\""+rowObject.courseId+"\")'>查看</a>";
    }

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        return '<a title="上传课程内容" href="javascript:void(0);" ' +
            'onclick="uploadC(\'' + rowObject.courseId + '\')" > ' +
            '<i class="ace-icon fa fa-cloud-upload bigger-200"></i></a>' +
            '<a title="编辑" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="editC(\'' + rowObject.courseId + '\')" > ' +
            '<i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>'+
            '<a title="删除" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="deleteC(\'' + rowObject.courseId + '\')" > ' +
            '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
    }

    // 模糊查询
    $("#course-search").bind("click",function(){
        var courseTitle = $("#courseTitle").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{courseTitle:courseTitle}
        }).trigger('reloadGrid');
    });

    // 进入课程发布页面
    $("#course-add").click(function(){
        location.href=base+"courseJhdk/courseJhdkAdd";
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

// 进入课程修改页面
function editC(courseId){
    location.href=base+"courseJhdk/courseJhdkEdit?courseId="+courseId;
}

// 上传课程内容
function uploadC(courseId){
    location.href=base+"courseJhdk/courseJhdkDetailUpload?courseId="+courseId
}

// 查看课程内容
function showContentDetail(courseId){
    location.href=base+"courseJhdk/courseJhdkDetail?courseId="+courseId
}

// 删除课程
function deleteC(courseId){
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则进行江湖大课的删除操作
            $.ajax({
                async: false,
                url: base + "course/removeC",
                data:{courseId:courseId},
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

// 批量删除课程
function delCourse(ids){
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则进行直播课程的删除操作
            $.ajax({
                async: false,
                url: base + "course/removeC?courseId="+ids,
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

// 上传课程考题
function uploadCt(id){
    location.href=base+"topic/course/";
}

// 查看课程考题
function showCtDetail(courseId){
    location.href=base+"courseJhdk/showCtDetail?courseId="+courseId;
}