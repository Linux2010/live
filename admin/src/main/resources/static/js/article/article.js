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
        url: base+"/article/page",
        subGrid: false,
        datatype: "json",
        height: 'auto',
        colNames: ['id','文章标题', '内容','图片','创建时间', '是否使用','操作'],
        colModel: [
            {name: 'articleId', index: 'articleId', width: 200, editable: true, hidden: true, key: true},
            {name: 'title', index: 'title', width: 90},
            {name: 'content', index: 'content', width: 90, formatter:showArticleContent},
            {name: 'img', index: 'img', width: 90, formatter:function(cellvalue, options, rowObject){

                return "<img style='width: 60px' src='"+rowObject.img+"'>";
            }},
            {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'status', index: 'status', width: 50, formatter: function (cellvalue, options, rowObject) {
                if (cellvalue ==2){
                    return '正在使用';
                    /*return '<a title="取消使用" style="margin-left: 10px;" href="javascript:void(0);" ' +
                        'onclick="noUseThis(\'' + rowObject.articleId + '\')" > '+'<i class="glyphicon glyphicon-remove bigger-130"></i>';*/
                }else {
                    return '<a title="使用" style="margin-left: 10px;" href="javascript:void(0);" ' +
                        'onclick="useThis(\'' + rowObject.articleId + '\')" > '+'<i class="glyphicon glyphicon-thumbs-up bigger-130"> ';
                }
            }},
            {name: 'articleId', index: 'articleId', width: 90, formatter: addcontent}
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

            edit: true,
            editfunc: edit_article,
            add: true,
            addfunc: add_article,
            addicon : 'ace-icon fa fa-plus-circle purple',
            del: true,
            delfunc: del_article,
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
//进行添加操作
function add_article() {
    //window.location.href = "/article/richText";
    $("#addModal-type").modal("show");
}
$("#add_article").click(function () {
    var articleTitle = $("#article_title_add").val();
    var articleImg = $("#article_img_add").val();

    if (isEmpty(articleTitle)){
        alert("文章标题不能为空！");
        return false;
    }

    $("#add-form-article").ajaxSubmit({
        async: false,
        url: base+"article/addOperation",
        type: "post",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定", btncl : "取消"});
                emptyForm("add-form-article");
                $("#addModal-type").modal("hide");
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        },
    });
});

//进行删除操作
function del_article(articleId){
    $.ajax({
        async:false,
        type: "POST",
        url: base+"article/delOperation",
        data:{"articleId": articleId.toString()},
        dataType: "json",
        success: function(data){
            if ("success" == data.status){
                Modal.alert({msg : "删除成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : "删除失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function(data){
            alert("系统异常，请稍后再试！");
        }
    });
}


//查询要修改的文章
function selectById(articleId) {
    var article = "";
    $.ajax({
        async: false,
        url: base+"article/selectOperation",
        data: {"articleId" :articleId},
        type: "post",
        dataType: "json",
        success: function (data) {
            article = data;
        },
        error: function (data) {
            alert("系统异常，请稍后再试！");
        }
    });
    return article;
}

//进行修改操作
function edit_article(articleId) {
    $("#updateModal-type").modal("show");
    var data = selectById(articleId);
    if (isEmpty(data)){
        return false;
    }
    var article_title = data.title;
    $("#articleId").val(articleId);
    $("#article_title_update").val(article_title);
    $("#article_img_url").val(data.img);
}

$("#update_article").click(function () {
    $("#update-form-article").ajaxSubmit({
        url: base+"article/updateOperation",
        type: "post",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定", btncl : "取消"});
                $("#updateModal-type").modal("hide");
                emptyForm("update-form-article");
                jQuery(grid_selector).trigger("reloadGrid");
            }else{
                Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
        },
    });
});


//添加文章内容
function addcontent(cellvalue, options, rowObject) {
    return "<a href='javascript:;' onclick='addArticleContent(\""+rowObject.articleId+"\")'>添加文章内容</a>";
}

function addArticleContent(articleId) {
    location.href = base+"article/addArticleContent?articleId="+articleId;
}

//查看文章内容
function showArticleContent(cellvalue, options, rowObject) {
    return "<a href='javascript:;' onclick='showArticleDetail(\""+rowObject.articleId+"\")'>查看</a>";
}
function showArticleDetail(articleId) {
    location.href = base+"article/showArticleContent?articleId="+articleId;
}

/*//取消使用该海报
function noUseThis(articleId) {
    $.ajax({
        async: false,
        url: base+"article/noUseThis",
        type: "post",
        data: {"articleId": articleId},
        dataType: "json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "取消成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert({msg : "取消失败！", title : "提示", btnok : "确定", btncl : "取消"});
        },
    });
}*/

//使用该海报
function useThis(articleId) {

    $.ajax({
        async: false,
        url: base+"article/useThis",
        type: "post",
        data: {"articleId": articleId},
        dataType: "json",
        success: function (data) {
            if ("success" == data.status){
                Modal.alert({msg : "使用成功！", title : "提示", btnok : "确定", btncl : "取消"});
                jQuery(grid_selector).trigger("reloadGrid");
            }else {
                Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert(data.message);
        },
    });
}

// 初始化上传文件输入框
$('#article_img_add').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});

$('#article_img_update').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});