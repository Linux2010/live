<#assign title="新闻资讯管理 > 商业模式"/>
<#assign menuId="45"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="nav-search" id="nav-search">
    <form class="form-search" id="form-search" method="post">
								<span class="input-icon">
									<input type="text" placeholder="标题" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keyword"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
        <input type="button" class="btn btn-blue btn-sm" value="查找" id="find"/>
    </form>
</div><!-- /.nav-search -->
<div class="col-xs-12" style="margin-top: 60px">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>

<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加标题</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-business" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="title_add" name="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>封面图片：</label>
                        <div class="col-xs-6">
                            <input type="file" id="photo_add" name="file" class="form-control"/>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_bussiness">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改标题</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-business" class="form-horizontal">
                    <input type="hidden" value="" name="businessId" id="tid"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="tname" name="title"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>封面图片：</label>
                        <div class="col-xs-6">
                            <input type="file" id="photo_update" name="file" class="form-control"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<#--add-end-->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script type="text/javascript">

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(function ($) {

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
                postData:{"keyword":keyword},
            }).trigger('reloadGrid');
        });

        jQuery(grid_selector).jqGrid({
            url : base + "/news/list",
            subGrid : false,
            datatype: "json",
            height: 'auto',
            loadtext: '正在加载...',
            colNames: ['ID','新闻标题','封面','新闻内容','创建时间','添加内容','操作' ],
            colModel: [
                {name: 'businessId', index: 'businessId', width: 200, editable: true, hidden: true, key: true},
                {name: 'title', index: 'title', width: 90},
                {name: 'photo', index: 'photo', width: 90, formatter: function (cellvalue, options, rowObject) {
                    if(cellvalue != null){
                        return"<img style='width: 60px' src='"+ cellvalue +"'>";
                    }else{
                        return "无图片";
                    }
                }
                },
                {name: 'content', index: 'content', width: 90, formatter:showNews},
                {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                    if(typeof(cellvalue) != "undefined"){
                        return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                    }else{
                        return '';
                    }
                }
                },
                {name: 'businessId', index: 'businessId', width: 90, formatter:add_new_content},
                {name:'businessId',index:'businessId',width:20, formatter : operFuc}

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

        //navButtons
        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                {

                    edit: true,
                    editfunc: edit_news,
                    add: true,
                    addfunc: add_news,
                    addicon : 'ace-icon fa fa-plus-circle purple',
                    del: false,
                    delfunc: del_news,
                    search:false,
                    refresh: true,
                    refreshicon: 'ace-icon fa fa-refresh green',
                    alertcap:'提示',
                    alerttext: '请选择一条记录'
                }
        )
        function del_news(){

        }
        function operFuc(cellvalue, options, rowData) {
            return   '<a title="删除" href="javascript:void(0);" ' +
                    'onclick="deleteCt(\'' + rowData.businessId + '\')" > ' +
                    '<i class="ace-icon fa fa-trash-o bigger-200"></i></a>';
        }
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
    <#--function deleteCt(id){-->
        <#--$.ajax({-->
            <#--async:false,-->
            <#--type: "POST",-->
            <#--url: "${ctx}/news/delOperation",-->
            <#--data:{"id":id.toString()},-->
            <#--dataType: "json",-->
            <#--success:function(data){-->
                <#--alert(data);-->
                <#--if (data == 1){-->
                    <#--alert("删除成功！");-->
                    <#--jQuery(grid_selector).trigger("reloadGrid");-->
                <#--}-->
            <#--},-->
            <#--error:function(data){-->
                <#--alert("系统异常，请稍后再试！");-->
            <#--}-->
        <#--});-->
    <#--}-->
    function deleteCt(businessId){
        debugger;
        Modal.confirm({msg: "确认删除吗？",
            title: "提示",
            btnok: "确定",
            btncl: "取消"
        }).on( function (e) {
            if(e){// 如果点击了确定，则进行标签信息的删除操作
                $.ajax({
                    async: false,
                    url: base + "news/delOperation",
                    data:{"businessId":businessId},
                    dataType: "json",
                    success: function (data) {
                        if(data == 0){
                            Modal.alert({msg: "删除失败！", title: "提示", btnok: "确定"});
                            jQuery(grid_selector).trigger("reloadGrid");
                        }else{
                            Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定"});
                        }
                    }
                })
                jQuery(grid_selector).trigger("reloadGrid");
            }
        });

    }

    //查询要修改的文章
    function selectById(id) {
        var news = "";
        $.ajax({
            async: false,
            url: "${ctx}/news/selectOperation?businessId=" + id,
            type: "post",
            dataType: "json",
            success: function (data) {
                news = data;
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        });
        return news;
    }

    function edit_news(id) {

        $("#updateModal-type").modal("show");
        var data = selectById(id);
        if (isEmpty(data)){
            return false;
        }
        $("#tid").val(data.businessId);
        $("#tname").val(data.title);
    }

    $("#update").click(function () {
        $("#update-form-business").ajaxSubmit({
            async: false,
            url: base+ "/news/updateNewsTitle",
            type: "post",
            success: function (data) {
                if ("success" == data.status) {
                    Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    $("#updateModal-type").modal("hide");
                    emptyForm("update-form-business");
                    jQuery(grid_selector).trigger("reloadGrid");

                }else {
                    Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error: function (data) {
                Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        });
    });




//添加操作
    function add_news() {
        $("#addModal-type").modal("show");
    }
    $("#add_bussiness").click(function () {
        var Title = $("#title_add").val();
         var photo1=$("#photo_add").val();
        if (isEmpty(Title)){
            alert("新闻标题不能为空！");
            return false;
        }
        $("#add-form-business").ajaxSubmit({
            async: false,
            type:"post",
            url:base+"/news/addNews",
         success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    emptyForm("add-form-business");
                    $("#addModal-type").modal("hide");
                    jQuery(grid_selector).trigger("reloadGrid");//重新加载
                }else {
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error: function (data) {
                Modal.alert(data.message);
            },
        });
    });

function showNews(cellvalyue, options, rowObject) {

    return "<a href='javascript:;' onclick='showNewSContent(\""+rowObject.businessId+"\")'>查看</a>";
}
function showNewSContent(businessId) {

    location.href = "${ctx}/news/newsContent11?businessId="+businessId;
}
//添加文章内容
//点击事件
    function add_new_content(cellvalue, options, rowObject) {
        return "<a href='javascript:;' onclick='addArticleContent(\""+rowObject.businessId+"\")'>添加内容</a>";
    }
//页面跳转
    function addArticleContent(businessId) {
        location.href = base+"news/add_news_content?businessId="+businessId;
    }



    // 初始化上传文件输入框
    $('#photo_add').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    $('#photo_update').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

</script>
<#include "../common/body_bottom.ftl"/>

















