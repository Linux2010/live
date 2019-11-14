
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function ($) {

    var parent_column = $(grid_selector).closest('[class*="col-"]');
    //resize to fit page size
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', parent_column.width());
    });


    //resize on sidebar collapse/expand
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    });

    jQuery(grid_selector).jqGrid({
        //direction: "rtl",

        url: base+"goodsComment/searchGoodsCommentList",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','商品名称','用户名称','评论内容','评论时间','操作'],
        colModel: [
            {name: 'goodsCommentId', index: 'goodsCommentId',width: 200, editable: true, hidden: true, key: true},
            {name: 'goodsName', index: 'goodsName', width: 200},
            {name: 'reviewerName', index: 'reviewerName', width: 200},
            {name: 'goodsComment', index: 'goodsComment', width: 250,formatter:function (cellvalue, options, rowObject) {
               if(cellvalue.length >30){
                   return cellvalue.substr(0,30)+"...";
               }else{
                   return cellvalue;
               }
              }
            },
            {name: 'createTime', index: 'createTime', width: 150,formatter:function (cellvalue, options, rowObject) {
                if(isNotEmpty(cellvalue)){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name:'goodsCommentId',index:'goodsCommentId', width:130, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                    return "<button class='btn btn-white btn-info btn-bold' onclick='queryid(\""+cellvalue+"\")'>查看 </button>&nbsp;&nbsp;&nbsp;<button class='btn btn-white btn-warning btn-bold'  onclick='delcomm(\""+cellvalue+"\")'>删除 </button>";
                }
            }
        ],
        jsonReader: {
            root: "list",
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

        caption: "商品评论列表",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            add: false,
            del:false,
            search:false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alerttext : "请选择需要操作的数据行!",
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
function queryid(id){
    $.ajax({
        async: false,
        type: "POST",
        url: base+"goodsComment/searchGoodsCommentById",
        data: {'goodsCommentId': id},
        dataType: "json",
        success: function (data) {
            if (data != null) {
                //得到值显示
                $("#comm_find").modal("show");
                $("#reviewerName").html(data.reviewerName);
                $("#createTime").html(new Date(data.createTime).pattern("yyyy-MM-dd HH:mm:ss"));
                $("#goodsComment").html(data.goodsComment);
                $("input[name=goodsCommentId]").val(id);
                var replyList = searchGoodsCommentReplyBygoodsCommentId(id);
                if(!replyList){
                    $("#huifu").css("display","none");
                    $("#res_context").css("display","block");
                }else{
                    $.each(replyList,function(i,reply){

                        $("#huifu").css("display","block");
                        $("#res_context").css("display","none");
                        $("#replyContents").html(reply.replyContent);
                        $("#replyTime").html(new Date(reply.replyTime).pattern("yyyy-MM-dd HH:mm:ss"));
                    });
                }
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    })
}

function delcomm(id){
    var r = confirm("确定要删除此条评论吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: base+"/goodsComment/removeGoodsComment",
            data: {'goodsCommentId': id},
            dataType: "json",
            success: function (data) {
                if (data == 1) {
                    Modal.alert({
                        msg : "删除成功！",
                        title : "提示",
                        btnok : "确定",
                        btncl : "取消"
                    });
                    jQuery(grid_selector).trigger("reloadGrid");
                }else{
                    Modal.alert({
                        msg : "删除失败！",
                        title : "提示",
                        btnok : "确定",
                        btncl : "取消"
                    });
                }
            },
            error: function (data) {
                Modal.alert({
                    msg : "系统异常，请稍后再试!",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                });
            }
        });
    }
    else {
        alert("您取消了此次操作!");
    }
}

$("#response").on('click',function () {
    var res = $("#res_context input[name=replyContent]").val();
    if(res == null || res == undefined || res == ""){
        alert("回复的内容不能为空!");
        return;
    }
    $.ajax({
        async: false,
        type: "POST",
        url: base+"goodsComment/addGoodsCommentReply",
        data:$("#reply-form").serialize(),
        dataType: "json",
        success: function (data) {
            if (data == 1) {
                Modal.alert({
                    msg : "回复成功!",
                    title : "提示",
                    btnok : "确定",
                    btncl : "取消"
                })
                $("#comm_find").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }
        },
        error: function (data) {
            Modal.alert({
                msg : "系统异常，请稍后再试!",
                title : "提示",
                btnok : "确定",
                btncl : "取消"
            });
        }
    });
});

//查询商品评论下的回复列表
function searchGoodsCommentReplyBygoodsCommentId(goodsCommentId){
    var replyList=[];
    $.ajax({
        async: false,
        type: "POST",
        url: base+"goodsComment/searchGoodsCommentReplyBygoodsCommentId",
        data:{"goodsCommentId":goodsCommentId},
        dataType: "json",
        success: function (data) {
            replyList = data;
        },
        error: function (data) {
            replyList = data;
        }
    });
    return replyList;
}