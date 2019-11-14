jQuery(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    var parent_column = $(grid_selector).closest('[class*="col-"]');



    //resize on sidebar collapse/expand
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    });



    $("#find").on("click",function(){
        var keyword=document.getElementById("nav-search-input").value;
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword)},
        }).trigger('reloadGrid');
    });

    jQuery(grid_selector).jqGrid({
        url: base+"shortVideo/getPage",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','视频名称', '类型', '查看', '创建时间', '是否显示', "操作"],
        colModel: [
            {name: 'videoId', index: 'videoId', width: 200, editable: true, hidden: true, key: true},
            {name: 'videoName', index: 'videoName', width: 90},
            {name: 'videoType', index: 'videoType', width: 90, formatter: function (cellvalue, options, rowObject) {
                if (cellvalue == 1){
                    return "ios";
                }else {
                    return "Android";
                }
            }
            },
            {name: 'fileId', index: 'fileId', width: 90, formatter: function (cellvalue, options, rowObject) {
                if (cellvalue == null){
                    return '未上传视频';
                }else {
                    return "<a href='javascript:;' onclick='videoLink(\""+rowObject.videoFile+"\")'>查看</a>";
                }
            }
            },
            {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'status', index: 'status', width: 90, formatter: function (cellvalue, options, rowObject) {
                if (cellvalue == 1){
                    return "使用中";
                }
                if (cellvalue == 2){
                    return "未使用";
                }
            }
            },
            {name: 'videoId', index: 'videoId', width: 90, formatter:uploadShortVideo}
        ],
        jsonReader : {
            root:"list",
            page: "pageNum",
            total: "pages",
            records: "total"
        },
        pgtext: "第 {0} 页，共 {1}页",
        recordtext: "第 {0} 到 {1} 共 {2} 条",
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        //toppager: true,
        multiselect: true,
        //multikey: "ctrlKey",
        multiboxonly: true,

        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },

        caption: "",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
           /* editfunc: edit,*/
            add: true,
            addfunc: add,
            addicon : 'ace-icon fa fa-plus-circle purple',
            del: true,
            delfunc: del,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alerttext: "请选择需要操作的数据行!",
            search: false
        }
    )

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

    $(document).one('ajaxloadstart.page', function (e) {
        $.jgrid.gridDestroy(grid_selector);
        $('.ui-jqdialog').remove();
    });


    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));
});

var grid_selector = "#grid-table";


function add() {
    $("#addModal-type").modal("show");
}
$("#addShortVideo").on("click", function () {

    if (isEmpty($("#add_videoName").val())){
        alert("视频名称不能为空！");
        return false;
    }
    $.ajax({
        async: false,
        url: base+"shortVideo/addVideo",
        type: "post",
        data: $("#add-form-shortVideo").serialize(),
        dataType: "json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定", btncl : "取消"});
                emptyForm("add-form-shortVideo");
                $("#addModal-type").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        }

    })
})


//上传视频内容
function uploadVideo(videoId) {
    $("#updateModal-type").modal("show");
    $("#video_id").val(videoId);
    var shortVideo = selectByVideoId(videoId);
    $("#file_id").val(shortVideo.fileId);
    $("#video_file").val(shortVideo.videoFile);
}
$("#updateVideo").on("click", function () {
    var fileId = $("#file_id").val();
    var videoFile = $("#video_file").val();
    if (isEmpty(fileId)){
        alert("文件id不能为空！");
        return false;
    }
    if (isEmpty(videoFile)){
        alert("文件名称不能为空！");
        return false;
    }
    $.ajax({
        async: false,
        url: base+"shortVideo/updateVideo",
        type: "post",
        data: {"fileId": fileId, "videoFile": videoFile, "videoId": $("#video_id").val()},
        dataType: "json",
        success: function (data) {
            if("success" == data.status){
                Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定", btncl : "取消"});
                $("#updateModal-type").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }else{
                Modal.alert({
                    msg : data.message,
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                });
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        }
    });
})

function selectByVideoId(videoId) {

    var result = null;
    $.ajax({
        async: false,
        url: base+"shortVideo/selectVideo",
        type: "post",
        data: {"videoId": videoId},
        dataType: "json",
        success: function (data) {
            result = data;
        }
    });
    return result;
}

//进行删除广告
function del(videoId) {
    var video_id = videoId.toString();
    $.ajax({
        async: false,
        url: base+"shortVideo/delVideo",
        type: "post",
        data: {"videoId": video_id},
        dataType: "json",
        success: function (data) {
            if("success" == data.status){
                Modal.alert({msg : "删除成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else{
                Modal.alert({
                    msg : data.message,
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                });
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        }
    });
}
function videoLink(videoFile) {
    window.open(videoFile,"_blank");
    /*$.ajax({
        async: false,
        url: base + "shortVideo/getVideoUrl",
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
        },
        error: function (data) {
            Modal.alert("系统异常，请稍后再试！");
        }
    });*/
}

// 关闭视频窗口
function closeVideo(){
    neplayer('my-video').reset();// 重置播放器
    $("#showModal-type").modal("hide");// 关闭模态窗口
}

function uploadShortVideo(cellvalue, options, rowObject){

    if (rowObject.status == 1){
        return '<a title="上传视频内容" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="uploadVideo(\'' + rowObject.videoId + '\')" > '+'<i class="ace-icon fa fa-cloud-upload bigger-200"> '+'</i></a>'
    }else {
        return '<a title="上传视频内容" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="uploadVideo(\'' + rowObject.videoId + '\')" > '+'<i class="ace-icon fa fa-cloud-upload bigger-200"> '+'</i></a>'+

            '<a title="使用该启动视频" style="margin-left: 10px;" href="javascript:void(0);" ' +
            'onclick="useThisVideo(\'' + rowObject.videoId + '\')" > '+'<i class="ace-icon fa fa fa-check bigger-180"> '+'</i></a>';
    }
}

function useThisVideo(videoId) {
    $.ajax({
        async: false,
        url: base+"shortVideo/updateVideoStatus",
        data: {"videoId": videoId},
        dataType:"json",
        type: "post",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "使用成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert("系统异常，请稍后再试！");
        }
    })
}
