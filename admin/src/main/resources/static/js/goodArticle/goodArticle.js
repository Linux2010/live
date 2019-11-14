jQuery(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


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


    $("#status_goodArticle").on("change",function () {
        var status = document.getElementById("status_goodArticle").value;
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"status":status},
        }).trigger('reloadGrid');
    })

    jQuery(grid_selector).jqGrid({
        //direction: "rtl",

        url: base+"goodArticle/allGoodArticle",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id', '标题','内容','封面', '序列值', '创建时间', '添加内容','操作'],
        colModel: [
            {name: 'goodArticleId', index: 'goodArticleId', width: 200, editable: true, hidden: true, key: true},
            {name: 'title', index: 'title', width: 90},
            {name: 'content', index: 'content', width: 50, formatter:showContent},
            {name: 'cover', index: 'cover', width: 90, formatter:function (cellvalue, options, rowObject) {
                return"<img style='width: 60px' src="+ rowObject.cover +">";
            }
            },
            {name: 'seqno', index: 'seqno', width: 60},
            {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'goodArticleId', index: 'goodArticleId', width :60, formatter: uploadGoodArticle},
            {name: 'status', index: 'status', width: 50, formatter: function (cellvalue, options, rowObject) {
                if (cellvalue ==1){
                    return '<a title="取消推荐" style="margin-left: 10px;" href="javascript:void(0);" ' +
                        'onclick="cancel(\'' + rowObject.goodArticleId + '\')" > '+'<i class="glyphicon glyphicon-remove bigger-130"></i>';
                }else {
                    return '<a title="推荐" style="margin-left: 10px;" href="javascript:void(0);" ' +
                        'onclick="recommend(\'' + rowObject.goodArticleId + '\')" > '+'<i class="glyphicon glyphicon-thumbs-up bigger-130"> ';
                }
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

        caption: "",
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            editfunc: edit,
            edit: true,
            edittext: '修改',
            edittitle: '修改推荐的好文序列值',
            add: true,
            addtext: '添加',
            addtitle: '推荐好文',
            addicon : 'ace-icon fa fa-plus-circle purple ',
            addfunc : add,
            del : true,
            deltext :'删除',
            deltitle : '取消推荐',
            delfunc: del,
            search:false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh green',
            alertcap:'提示',
            alerttext: '请选择一条记录'
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

//添加好文
function add() {
    $("#addModal-type").modal("show");
}
    $("#add_goodArticle").click(function () {
        if (!checkAll($("#title_add").val(), $("#cover_add").val(), $("#seqno_add").val())){
            return false;
        }
         $("#add-form-goodArticle").ajaxSubmit({
            asyne: false,
             url: base+"goodArticle/addGood",
             type: "post",
             dataType: "json",
             success: function (data) {
                 if ("success" == data.status){
                     Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定", btncl : "取消"});
                     emptyForm("add-form-goodArticle");
                     $("#addModal-type").modal("hide");
                     jQuery(grid_selector).trigger("reloadGrid");
                 }else {
                     Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定", btncl : "取消"});
                 }
             },
             error: function (data) {
                 Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
             },
         });
    })

//删除好文
function del(goodArticleId) {

    $.ajax({
        url: base+"goodArticle/delGood",
        type:"post",
        data:{"goodArticleId": goodArticleId.toString()},
        dataType: "json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "删除成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : "删除失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
        },
    });
}

//查询好文
function selectById(goodArticleId) {
    var goodArticle = "";
    $.ajax({
        async: false,
        url: base+"goodArticle/selectGood",
        data: {"goodArticleId" :goodArticleId},
        type: "post",
        dataType: "json",
        success: function (data) {
            goodArticle = data;
        },
        error: function (data) {
            alert("系统异常，请稍后再试！");
        }
    });
    return goodArticle;
}

//修改好文
function edit(goodArticleId) {
    $("#updateModal-type").modal("show");
    var data = selectById(goodArticleId);
    if (isEmpty(data)) {
        return false;
    }
    var title = data.title;
    var cover = data.cover;
    var seqno = data.seqno;
    $("#goodArticleId_update").val(goodArticleId);
    $("#goodArticleTitle_update").val(title);
    $("#goodArticlSeqno_update").val(seqno);
}
    $("#update_goodArticle").click(function () {
        $("#update-form-goodArticle").ajaxSubmit({
            url: base+"goodArticle/updateGood",
            type: "post",
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    emptyForm("update-form-goodArticle");
                    $("#updateModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");
                }else {
                    Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
            },
        });
    });




//上传好文推荐内容
function uploadGoodArticle(cellvalue, options, rowObject) {

    return "<a href='javascript:;' onclick='addArticleContent(\""+rowObject.goodArticleId+"\")'>添加文章内容</a>";
}

function addArticleContent(goodArticleId) {
    location.href = base+"goodArticle/addGoodArticleContent?goodArticleId="+goodArticleId;
}

//查看好文推荐内容
function showContent(cellvalue, options, rowObject) {
    return "<a href='javascript:;' onclick='showGoodArticleContent(\""+rowObject.goodArticleId+"\")'>查看</a>";
}

function showGoodArticleContent(goodArticleId) {
    location.href = base +"goodArticle/showContent?goodArticleId="+goodArticleId;
}


//校验参数
function checkAll(title,cover,seqno) {

    var result = false;
    if (isEmpty(title)){
        Modal.alert({msg: "好文标题不能为空！",title: "提示", btnok: "确定"});
        return result;
    }
    if (isEmpty(cover)){
        Modal.alert({msg: "好文封面不能为空！",title: "提示", btnok: "确定"});
        return result;
    }
    if (isEmpty(seqno)){
        Modal.alert({msg: "序列值不能为空！",title: "提示", btnok: "确定"});
        return result;
    }
    result = true;
    return result;
}


//取消推荐
function cancel(goodArticleId) {

    var message = confirm("您确定取消推荐吗?");
    if (message == true){
        $.ajax({
            async: false,
            url: base+ "goodArticle/cancel",
            type: "post",
            data: {"goodArticleId": goodArticleId},
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg : "取消成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }else {
                    Modal.alert({msg : "取消失败！", title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
            },
        });
    }else {
        alert("您取消了操作！");
    }
}


//推荐
function recommend(goodArticleId) {

    var message = confirm("您确定要推荐此文章吗?");
    if (message == true){
        $.ajax({
            async: false,
            url: base+ "goodArticle/recommend",
            type: "post",
            data: {"goodArticleId": goodArticleId},
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg : "推荐成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    jQuery(grid_selector).trigger("reloadGrid");
                }else {
                    Modal.alert({msg : "推荐失败！", title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
            },
        });
    }else {
        alert("您取消了操作！");
    }
}

// 初始化上传文件输入框
$('#cover_add').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});

$('#goodArticlCover_update').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});

