var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function($) {
    jQuery(grid_selector).jqGrid({
        url : base + "courseYp/searchCList",
        subGrid : false,
        datatype: "json",
        height: 'auto',
        loadtext: '正在加载...',
        colNames:['id','序号','课程封面','课程名称','课程内容','开始时间','课程价格','会员价格','讲师名称','课程分类','积分','课程题','操作'],
        colModel:[
            {name: 'courseId', index: 'courseId', width: 10, editable: true, hidden:true, key: true},
            {name:'seqno',index:'seqno',width:20},
            {name:'courseCover',index:'courseCover',width:40,formatter:showCourseCover},
            {name:'courseTitle',index:'courseTitle',width:50},
            {name:'courseContent',index:'courseContent',width:20,formatter:showCourseContent},
            {name:'courseBeginTime',index:'courseBeginTime',width:25,formatter:showFormatDate},
            {name:'costPrice',index:'costPrice',width:20,formatter:showFreePrice},
            {name:'vipPrice',index:'vipPrice',width:20,formatter:showFreePrice},
            {name:'tname',index:'tname',width:30},
            {name:'tyname',index:'tyname',width:30},
            {name:'courseIntegral',index:'courseIntegral',width:20},
            {name:'courseTopicId',index:'courseTopicId',width:20,formatter:showCourseTopic},
            {name:'id',index:'id',width:55,formatter:operFuc}
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

    // 查看课程内容
    function showCourseContent(cellvalue, options, rowObject){
        return "<a href='javascript:;' onclick='showVideo(\""+rowObject.videoId+"\")'>查看</a>";
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
        var returnStr = "";
        if(rowObject.ktCount == 0){
            returnStr = "无考题";
        }else{
            returnStr = "<a href='javascript:;' onclick='showCtDetail(\""+rowObject.courseId+"\")'>查看</a>";
        }
        return returnStr;
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
            '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>' +
            '<a title="上传课程考题" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="uploadCt(\'' + rowObject.courseId + '\',\''+rowObject.courseTitle+'\')" > ' +
            '<i class="ace-icon fa fa-cloud-upload bigger-200"></i></a>';
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
        location.href=base+"courseYp/courseYpAdd";
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

    // 初始化上传文件输入框
    $('#courseContent').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 点击上传按钮进行ajax提交form表单
    $("#uploadCourseYp").click(function(){
        // 先验证非空项
        if(!verifyValue($("#courseId").val(),$("#videoId").val(),$("#objName").val())){
            return false;
        }else{
            // 设置上传按钮不可用
            $("#uploadCourseYp").attr("disabled",true);
            $("#uploadCourseYp").text("上传中...");
            // 如果验证通过，则提交表单
            $("#upload-form-courseYp").ajaxSubmit({
                type:"post",
                dataType:"json",
                url:base+"course/modifyV",
                success: function(data) {
                    // 设置上传按钮可用
                    $("#uploadCourseYp").attr("disabled",false);
                    $("#uploadCourseYp").text("上 传");
                    if(data == 0){
                        $("#uploadModal-type").modal("hide");// 隐藏模态窗口
                        Modal.alert({msg : "上传成功！", title : "提示", btnok : "确定"});
                        jQuery(grid_selector).trigger("reloadGrid");
                    }else{
                        Modal.alert({msg : "上传失败！", title : "提示", btnok : "确定"});
                    }
                },
                error:function(data){
                    // 设置上传按钮可用
                    $("#uploadCourseYp").attr("disabled",false);
                    $("#uploadCourseYp").text("上 传");
                    Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                }
            });
        }
    });

    // 校验对应数据
    function verifyValue(courseId,videoId,objName){
        var result = false;
        if(isEmpty(courseId)){
            Modal.alert({msg: "课程ID不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(videoId)){
            Modal.alert({msg: "文件ID不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(objName)){
            Modal.alert({msg: "文件名称不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        result = true;
        return result;
    }

});

// 点击查看课程内容
function showVideo(vId){
    $.ajax({
        async: false,
        url: base + "course/getVideoUrl",
        data:{vId:vId},
        type: "post",
        success: function (data) {
            if(data != null && data != ""){
                $("#showModal-type").modal("show");// 打开模态窗口
                // 设置播放器播放路径
                var myPlayer = neplayer('my-video');
                myPlayer.setDataSource({src:data,type:"video/mp4"},{src:data,type:"video/x-flv"},{src:data,type:"application/x-mpegURL"});
                // 播放
                myPlayer.play();
            }else{
                Modal.alert({msg: "未上传课程内容！", title: "提示", btnok: "确定"});
            }
        }
    });
}

// 关闭视频窗口
function closeVideo(){
    neplayer('my-video').reset();// 重置播放器
    $("#showModal-type").modal("hide");// 关闭模态窗口
}

// 进入课程修改页面
function editC(courseId){
    location.href=base+"courseYp/courseYpEdit?courseId="+courseId;
}

// 删除课程
function deleteC(courseId){
    Modal.confirm({msg: "确认删除吗？",
        title: "提示",
        btnok: "确定",
        btncl: "取消"
    }).on( function (e) {
        if(e){// 如果点击了确定，则进行音频课程的删除操作
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
        if(e){// 如果点击了确定，则进行音频课程的删除操作
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

// 上传课程内容
function uploadC(courseId){
    // 把courseId赋给隐藏域
    $("#courseId").val(courseId);
    $("#uploadModal-type").modal("show");// 打开模态窗口
}

// 上传课程考题
function uploadCt(courseId,courseTitle){
    var examJson = selectTopicsByCourseTopicCourseId(courseId);
    if(examJson && examJson.courseTopicExaminationId ){
        Modal.alert({msg: "课程考题限定一套，请勿重复添加", title: "提示", btnok: "确定", btncl: "取消"});
        return false;
    }else{
        location.href=base+"topic/course/add?courseId="+courseId+"&courseName="+encodeURIComponent(courseTitle)+"&cType=ypkc";
    }
}

// 查看课程考题
function showCtDetail(courseId){
    location.href=base+"courseYp/showCtDetail?courseId="+courseId;
}

//查找课程考卷
function selectTopicsByCourseTopicCourseId(courseId){
    var examJson;
    $.ajax({
        async:false,
        url:base+"topic/selectTopicsByCourseTopicCourseId",
        dataType:"json",
        data:{"courseId":courseId},
        success:function(data){
            examJson=data;
        },
        error: function (data) {
            examJson=data;
        }
    });
    return examJson;
}